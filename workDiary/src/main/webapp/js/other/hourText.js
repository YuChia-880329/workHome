
	// js/classDefine.js
	
	function changeWorkHourText(originValueStr, curValueStr){
		
		var hourTextStr = workHourText.text;
		
		var hour = str1MinusStr2(hourTextStr, originValueStr);
		var curValue = round(parseFloat(curValueStr));
		hour = round(hour+curValue);
		
		workHourText.text = hour;
	}
	function str1MinusStr2(str1, str2){
		
		var num1 = parseFloat(str1);
		var num2 = parseFloat(str2);
		
		var roundNum1 = round(num1);
		var roundNum2 = round(num2);
		return round(roundNum1-roundNum2);
	}
	function round(num){
		
		return Math.round(num*10)/10;
	}