
	let dateFormat = 'yy/mm/dd';

	function initWorkDate(workDateDivId, dateTextDivId){
		
		$.datepicker.setDefaults({
			'dateFormat' : dateFormat
		});
		var workDateDiv = $('#' + workDateDivId);
		
		workDateDiv.datepicker({
			onSelect : function(dateText){
				setWorkDateText(dateTextDivId, dateText);
			}
		});
		
		var today = new Date();
		workDateDiv.datepicker('setDate', today);
		setWorkDateText(dateTextDivId, workDateDiv.val());
	}
	
	function setWorkDateText(dateTextDivId, dateText) {
		
		$('#' + dateTextDivId).text(dateText);
	}