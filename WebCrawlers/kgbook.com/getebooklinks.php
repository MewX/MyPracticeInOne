<?php
function getFromNetForever($url, $time = 10) {
	$html = "";
	while(1) {
		if(($html = file_get_contents($url)) !== FALSE)
			break;
		
		if(-- $time <= 0)
			return FALSE;
		echo "Failed in '$url', time $time, sleep 5s.\n";
		sleep(5);
	}
	return $html;
}

$dbBook = "book.db";
if (!file_exists($dbBook)) {
	if (!($fp = fopen($dbBook, "w+"))) {
		exit(error_code(-1, LN));
	}
	fclose($fp);
	
	// create table
	$db = new PDO("sqlite:book.db");
	$db->exec("create table book(id integer, name text, author text, type text, info text, image text);"); // base64 image
	$db = null;
}

for($idx = 1; $idx <= 67; $idx ++) {
	// get content
	$url = 'http://www.kgbook.com/list/index' . ($idx == 1 ? '' : '_' . $idx) . '.html';
	$html = '';
	if(($html = getFromNetForever($url)) === FALSE) {
		continue;
	}
	//$html = file_get_contents($url);
	
	// filter url
	$index = $end = $count = 0;
	while(1) {
		$index = strpos($html, '<h3 class="list-title">', $end ) + 23;
		$index = strpos($html, '"', $index) + 1;
		if(!$index || $index < $end) break;
		
		$end = strpos($html, '"', $index);
		if($end - $index < 0) break;
		
		$novelURL = substr($html, $index, $end - $index);
        if($novelURL[0] == '/') $novelURL = "http://kgbook.com" . $novelURL;
		echo $novelURL . "\n";
		$index = $end;
		
		// loop for content and download
		$novelInfo = '';
		if(($novelInfo = getFromNetForever($novelURL)) !== FALSE) {
			// get id
			$idBeg = strrpos($novelURL, '/') + 1;
			$id = substr($novelURL, $idBeg, strrpos($novelURL, '.') - $idBeg);
			
			// get type
			$typeBeg = strrpos(substr($novelURL, 0, $idBeg - 1), '/') + 1;
			$type = substr($novelURL, $typeBeg, $idBeg - 1 - $typeBeg);
			
			// get name
			$nameBeg = strpos($novelInfo, 'class="news_title"') + 19;
			$name = substr($novelInfo, $nameBeg, strpos($novelInfo, '</h1>', $nameBeg) - $nameBeg);
			
			// get image
			$imageBeg = strpos($novelInfo, 'src="', $nameBeg) + 5;
			$image = "http://www.kgbook.com" . substr($novelInfo, $imageBeg, strpos($novelInfo, "\"", $imageBeg) - $imageBeg);
			
			// get author
			$authorBeg = strpos($novelInfo, '<li>作者：') + 4 + strlen('作者：');
			$author = substr($novelInfo, $authorBeg, strpos($novelInfo, '</li>', $authorBeg) - $authorBeg);
			
			// get info
			$infoBeg = strpos($novelInfo, '<h3>简介：</h3><p>') + 12 + strlen('简介：');
			$info = str_replace(array("\r\n", "\r", "\n", "<br />"), "\\n", substr($novelInfo, $infoBeg, strpos($novelInfo, '</p>', $infoBeg) - $infoBeg));
			$info = str_replace("\\n\\n", "\\n", $info);
			$info = str_replace("\\n\\n", "\\n", $info);
			
			// get image source
			$imageSrc = getFromNetForever($image);
			$imageContent = base64_encode($imageSrc === FALSE ? "" : $imageSrc);
			
// 			echo "insert into book values (" . addslashes($id) .",'" . addslashes($type) . "','"
//  					. addslashes($name) . "','" . addslashes($author) . "','" . addslashes($info) . "','img');";
 			$db = new PDO("sqlite:book.db");
 			$db->exec("insert into book values (" . addslashes($id) .",'" . addslashes($type) . "','"
 					. addslashes($name) . "','" . addslashes($author) . "','" . addslashes($info) . "','$imageContent');");
 			$db = null;
			
			// download
			$dlBeg = $dlEnd = 0;
			while(1) {
                // get download url
				$dlBeg = strpos($novelInfo, 'DownSys', $dlEnd) - 20;
				if($dlBeg < $dlEnd) break;
				$dlEnd = strpos($novelInfo, "\"", $dlBeg);
				if($dlBeg > $dlEnd) reak;
				$dlLink = substr($novelInfo, $dlBeg, $dlEnd - $dlBeg);
				$dlLink = str_replace("&amp;", "&", $dlLink);
                
                // get download type
                $dlType = substr($novelInfo, $dlEnd + 2, strpos($novelInfo, "</a>", $dlEnd) - ($dlEnd + 2));
				
                echo $dlLink . "\n";
                //echo "wget -O \"dl/" . $id . "." . $dlType . "\" \"" . $dlLink . "\"";
                system("wget -N -O \"dl/" . $id . "." . $dlType . "\" \"" . $dlLink . "\"");
			}
		}
		else
			echo "error load: $novelURL\n";
	}
}
$db = null;
?>
