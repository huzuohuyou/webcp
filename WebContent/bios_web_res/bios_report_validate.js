String.prototype.trim = function () {
	return this.replace(/^\s*(.+)/gi,"$1").replace(/\s*$/gi,"");
};	

function bios_isArray(o) {   
	return Object.prototype.toString.apply(o) == "[object Array]";  	
}
  
function bios_checkNull(obj, message, vObj){
	if(obj){
		if(obj.value.trim() == "") {
			alert(message);
			try{
				if(vObj)
					vObj.focus();
				else
					obj.focus();	
			}catch(e){
			}
			return false;	
		}
		return true;
	}else
		return true;
}

function bios_checkTrue(boolExp, obj, message){
	if(!boolExp){
		alert(message);
		try{
			obj.select();	
		}catch(e){
		}
		return false;	
	}
	return true;
}

function bios_checkNum(obj, sObj, fmt){  
	if(isNaN(obj.value)){
		if(sObj)
			sObj.value = obj.value;
		obj.select();
		return false;
	}
	if(sObj){
		sObj.value = obj.value.toString().trim();
		obj.value = bios_fmtNum(sObj.value, fmt);
	}
	return true;
} 

function bios_setRadioVal(obj){
	if(obj){
		var radio = document.all["radio_" + obj.name];
		for(var i=0;i<radio.length;i++){
			if(radio[i].checked){
				obj.value = radio[i].value;
				break;
			}	
		}
	}
}

function bios_setChkVal(obj){
	if(obj){
		var val = "";
		for(var i=0;;i++){
			var chk = document.all['chk' + i + '_' + obj.name];
			if(chk){
				if(chk.checked){
					if(val != "")
						val += ",";
					val += chk.value;
				}
			}else{
				break;
			}
		}
		obj.value = val;
	}
}

function bios_getInt(dataObj){
	dataObj = parseInt(dataObj);
	if(isNaN(dataObj))
		return 0;
	return dataObj;
}

function bios_getFloat(dataObj){
	dataObj = parseFloat(dataObj);
	if(isNaN(dataObj))
		return 0;
	return dataObj;
}

function bios_sum(arr){
	if(bios_isArray(arr)){
		var rtn = 0;
		for(var i=0;i<arr.length;i++){
			if(!isNaN(arr[i]))
				rtn += arr[i];
		}
		return rtn;
	}else{
		if(!isNaN(arr))
			return arr;
		return 0;
	}
}

function bios_avg(arr){
	if(bios_isArray(arr)){
		return bios_sum(arr)/arr.length;
	}else{
		if(!isNaN(arr))
			return arr;
		return 0;
	}
}

function bios_focusNum(vObj, obj) {
	vObj.value = obj.value;
	vObj.select();
}

function bios_fmtNum(num,pattern){
	if(!num || num == "")
		return "";
	if(isNaN(num) || num == "Infinity")
		return "-";
		
	var re=/%/;
	var percent = "";
	if(re.test(pattern)) {
		num = num*100;
		percent = "%"
	}
	num = num.toString();
	
	var pre = pattern.substring(0, pattern.indexOf('#'));
	if(num.charAt(0) == '-') {
		pre += '-';
		num = num.substr(1);
	}
  var strarr = num.split('.');
  var fmtarr = pattern?pattern.split('.'):[''];
  var retstr='';

  var str = strarr[0];
  var fmt = fmtarr[0];
  var i = str.length-1;  
  var comma = false;
  for(var f=fmt.length-1;f>=0;f--){
    switch(fmt.substr(f,1)){
      case '#':
        if(i>=0 ) retstr = str.substr(i--,1) + retstr;
        break;
      case '0':
        if(i>=0) retstr = str.substr(i--,1) + retstr;
        else retstr = '0' + retstr;
        break;
      case ',':
        comma = true;
        retstr=','+retstr;
        break;
    }
  }
  if(i>=0){
    if(comma){
      var l = str.length;
      for(;i>=0;i--){
        retstr = str.substr(i,1) + retstr;
        if(i>0 && ((l-i)%3)==0) retstr = ',' + retstr; 
      }
    }
    else retstr = str.substr(0,i+1) + retstr;
  }

  retstr = retstr+'.';

  str=strarr.length>1?strarr[1]:'';
  fmt=fmtarr.length>1?fmtarr[1]:'';
  i=0;
  for(var f=0;f<fmt.length;f++){
    switch(fmt.substr(f,1)){
      case '#':
        if(i<str.length) retstr+=str.substr(i++,1);
        break;
      case '0':
        if(i<str.length) retstr+= str.substr(i++,1);
        else retstr+='0';
        break;
    }
  }
  return pre + retstr.replace(/^,+/,'').replace(/\.$/,'') + percent;
}