
	// js/variables.js
	// js/classDefine.js
	
	function calendarPrepare(currentDateString){
		
		$.datepicker.setDefaults({
			'dateFormat' : Calendar.dateFormat
		});
		
		calendar.onSelect = calendarOnSelectHandler;
		calendar.jquery.datepicker({
			onSelect : calendar.onSelect
		});
		
		calendar.value = Calendar.parseDate(currentDateString);
		dateText.text = currentDateString;
	}
	
	function calendarOnSelectHandler(dateText){
		
		var url = homePageUrl + '?date=' + dateText;
		location.assign(url);
	}