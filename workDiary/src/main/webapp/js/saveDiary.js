
	let saveAjaxUrl = 'save';
	
	var diaryContent = function(count, date, projectId, phaseId, workId, text, workHour, status){
		
		this.count = count;
		this.date = date;
		this.projectId = projectId;
		this.phaseId = phaseId;
		this.workId = workId;
		this.text = text;
		this.workHour = workHour;
		this.status = status;
	}
	function saveDiaryContent(diaryBodyId, dateTextDivId, diaryCountDataName, diaryIdPrefixes, trStatusDataName){
		
		var datas = getDatas(diaryBodyId, dateTextDivId, diaryCountDataName, diaryIdPrefixes, trStatusDataName);
		var dataValue = JSON.stringify(datas).replaceAll('"', '\"');
		var formId = 'send_form';
		var dataName = 'diary_contents';
		var form = '<form action="' + saveAjaxUrl + '" method="POST" id="' + formId + '">';
		form += '<input type="hidden" name="' + dataName + '" value=\'' + dataValue + '\' />';
		form += '</form>';
		
		$(document.body).append(form);
		$('#send_form').submit();
	}
	
	function getDatas(diaryBodyId, dateTextDivId, diaryCountDataName, diaryIdPrefixes, trStatusDataName){
		
		var diaryTrs = $('#' + diaryBodyId + ' tr');
		var diaryNumber = diaryTrs.length;
		
		var datas = [];
		for(var i=0; i<diaryNumber; i++){
			
			var diaryTrCount = $(diaryTrs[i]).data(diaryCountDataName);
			var date =$('#' + dateTextDivId).text();
			var projectId = $('#' + diaryIdPrefixes.projectIdPrefix + diaryTrCount).val();
			var phaseIdInput = $('#' + diaryIdPrefixes.phaseIdPrefix + diaryTrCount).val();
			var workIdInput = $('#' + diaryIdPrefixes.workIdPrefix + diaryTrCount).val();
			var textInput = $('#' + diaryIdPrefixes.textIdPrefix + diaryTrCount).val();
			var workHourInput = $('#' + diaryIdPrefixes.hourIdPrefix + diaryTrCount).val();
			var diaryTrStatus = $(diaryTrs[i]).data(trStatusDataName);
			
			var data = new diaryContent(diaryTrCount, date, projectId, phaseIdInput, 
						workIdInput, textInput, workHourInput, diaryTrStatus);
			
			datas[i] = data;
		}
		return datas;
	}
	
	
