	
	function diaryProjectToDefault(proId){
		
		$('#' + proId).val(0);
	}
	function diaryPhaseToDefault(phaseId){

		var diaryPhaseDefault = '<option value="0" >請選擇</option>';
		var diaryPhase = $('#' + phaseId);
		diaryPhase.empty();
		diaryPhase.html(diaryPhaseDefault);
	}
	function diaryWorkToDefault(workId){

		var diaryWorkDefault = '<option value="0" >請選擇</option>';
		var diaryWork = $('#' + workId);
		diaryWork.empty();
		diaryWork.html(diaryWorkDefault);
	}
	function diaryTextToDefault(textId){

		$('#' + textId).val('');
	}
	function diaryHourToDefault(hourId){

		$('#' + hourId).val('0.0');
	}
	