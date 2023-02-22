
	// variables.js
	// js/other/hourText.js
	
	function diaryHourPrepare(diaryHour){
		
		diaryHour.onFocus = function(){
			
			dairyHourFocus(diaryHour);
		}
		diaryHour.onFocusOut = function(){
			
			diaryHourFocusOut(diaryHour);
		}
		
		diaryHour.jquery.focus(diaryHour.onFocus);
		diaryHour.jquery.focusout(diaryHour.onFocusOut);
	}
	
	function dairyHourFocus(diaryHour){
		
		diaryHour.originalHour = diaryHour.value;
	}
	function diaryHourFocusOut(diaryHour){
		
		changeWorkHourText(diaryHour.originalHour, diaryHour.value);
		diaryHour.value = round(parseFloat(diaryHour.value));
	}