import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Test {
	
	public static void main(String[] args) throws IOException {
//		System.out.println(new Test().canConstruct("aa", "aab"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(br.readLine());
		StringBuffer sb = new StringBuffer(br.readLine());
		sb.setCharAt(0, '9');
		System.out.println(sb.toString());
		br.close();
	}
	
	public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> mag = new HashMap<>();
        for (int i = 0; i < magazine.length(); i ++) {
        	if (!mag.containsKey(magazine.charAt(i))) mag.put(magazine.charAt(i), 1);
        	else mag.put(magazine.charAt(i), mag.get(magazine.charAt(i)) + 1);
        }
        
//        for (int key : mag.keySet()) {
//        	System.out.println("key: " + key + "; val: " + mag.get(key));
//        }
        
        for (int i = 0; i < ransomNote.length(); i ++) {
//        	System.out.println("Now proceeding: " + ransomNote.charAt(i));
        	if (!mag.containsKey(ransomNote.charAt(i))) return false;
        	
        	int save = mag.get(ransomNote.charAt(i));
        	if (save <= 1) mag.remove(ransomNote.charAt(i));
        	else mag.put(ransomNote.charAt(i), save - 1);
        }
        return true;
    }
}
