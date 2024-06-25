var dec_guess = function (r) {
    for (var t = "", e = 0; e < r.length; ++e) t += String.fromCharCode((function () {
        var r = Array.prototype.slice.call(arguments),
            t = r.shift();
        return r.reverse().map(function (r, e) {
            return String.fromCharCode(r - t - 50 - e)
        }).join("")
    }(57, 158) + 25..toString(36).toLowerCase() + function () {
        var r = Array.prototype.slice.call(arguments),
            t = r.shift();
        return r.reverse().map(function (r, e) {
            return String.fromCharCode(r - t - 28 - e)
        }).join("")
    }(33, 120, 178, 117, 176, 113) + 74545167334..toString(36).toLowerCase())[e % 14] ^ r.charCodeAt(e));
    return JSON.parse(t)
};

// console.log(dec_guess("x\"gare`ozy+:*PWE&,$sbraaeN}maev\"<400$\"yazt!:&WC\"+\"yuls|iln&:$Ckiea}e(ckajgc\"+\"|yye*:!Wviriig*,+LL_@oqnr\"={*l`kms!:51*\"ci{l`kms!:4,$oejmc}Il\"9\"194105n6820ff1403c54j21b*,!cvegtbdIt+:*2313-65*29T81212:10(736R\"%\"}pgapebAs\"2\";097.15-48S0?:8224:.78>Z%,*_Vtqpf\">\"Vonn|e{\"$\"`lesuNfmm\"3\"Mxss[LB_Do}n}\"u,!taxr_dhgije{\"9[\"eoitmn}\"2\"`lmmgtb khhnoe９全琇温庠升髟\"u,r\"komtanr\"=\"zehsgn９全琇温庠升髟造战了讱多恾劣彲响〆e(g) 夯量牠种灥绝、海幷面丌升々\"u]%\"|e{t&:$Yhu(nlel wo$srucy(ceieawe$cnaigm.)W`i`h$aupbc| ff(coiiare'c`aggm tihl&yhu(caogsf enb phq?)U{e#e|akpke{.+,*tfxp_me~wgrms*:X\"glomftm\"%\"khbnce$]+\"gbcektJd&:$5?fn411=a12f9b076=b<1:bb\"(\"erba|emA|\"9\"6077*0<-;5\\11:19<06.:09Z*,!utdgtbdIt+:*2313-71*1>T93214:40(3>2R\"%\"W_wyte$:%Ojjlc|\"/\"glgstNiml\"2\"Fxts$}"));

if (process.argv.length <= 2) {
	console.log("Not enough arguments");
	process.exit(-1);
}

var fs = require('fs');
for (var idx = 2; idx < process.argv.length; idx ++) {
	var fileName = process.argv[idx];
	var targFileName = fileName + ".dec.json";
    targFileName = targFileName.replace('downloads', 'decoded')
	console.log("Now processing " + fileName + ", saving to " + targFileName);
	
	var obj = JSON.parse(fs.readFileSync(fileName, 'utf8'));
	for (i = 0; i < obj.result.length; i ++) {
		obj.result[i] = JSON.stringify(dec_guess(obj.result[i]))
	}
	fs.writeFileSync(targFileName, JSON.stringify(obj), 'utf8');
}
