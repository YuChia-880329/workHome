
	let saveAjaxUrl = 'save';
	
	
	function saveAjax(diaryBodyId, dateTextDivId, diaryCountDataName, diaryIdPrefixes, trStatusDataName){
		
		var datas = getDatas(diaryBodyId, dateTextDivId, diaryCountDataName, diaryIdPrefixes, trStatusDataName, saveAjaxUrl);
		$(document.body).append(datas);
		$('#send_form').submit();
	}
	
	function getDatas(diaryBodyId, dateTextDivId, diaryCountDataName, diaryIdPrefixes, trStatusDataName, actionUrl){
		
		var diaryTrs = $('#' + diaryBodyId + ' tr');
		var diaryNumber = diaryTrs.length;
		
		var diaryTrCounts = [];
		var datas = '<form action="' + actionUrl + '" method="POST" id="send_form">';
		for(var i=0; i<diaryNumber; i++){
			
			var diaryTrCount = $(diaryTrs[i]).data(diaryCountDataName);
			
			var dateInput = getInput('diary_date_', diaryTrCount, $('#' + dateTextDivId).text());
			var projectIdInput = getInput(diaryIdPrefixes.projectIdPrefix, diaryTrCount, 
							$('#' + diaryIdPrefixes.projectIdPrefix + diaryTrCount).val());
			var phaseIdInput = getInput(diaryIdPrefixes.phaseIdPrefix, diaryTrCount,
			 				$('#' + diaryIdPrefixes.phaseIdPrefix + diaryTrCount).val());
			var workIdInput = getInput(diaryIdPrefixes.workIdPrefix, diaryTrCount,
			 				$('#' + diaryIdPrefixes.workIdPrefix + diaryTrCount).val());
			var textInput = getInput(diaryIdPrefixes.textIdPrefix, diaryTrCount,
			 				$('#' + diaryIdPrefixes.textIdPrefix + diaryTrCount).val());
			var workHourInput = getInput(diaryIdPrefixes.hourIdPrefix, diaryTrCount,
			 				$('#' + diaryIdPrefixes.hourIdPrefix + diaryTrCount).val());
			
			datas += dateInput;
			datas += projectIdInput;
			datas += phaseIdInput;
			datas += workIdInput;
			datas += textInput;
			datas += workHourInput;
			
			diaryTrCounts[i] = diaryTrCount;
		}
		datas += '<input type="hidden" name="diary_tr_counts" value="' + diaryTrCounts + '" />';
		datas += '</form>';
		return datas;
	}
	
	function getInput(IdPrefix, diaryTrCount, value){
		
		var input = '<input type="hidden" name="' + IdPrefix + diaryTrCount + '" value="' + value + '" />'
		return input;
	}
