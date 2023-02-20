
	function deleteDiaryTr(diaryTrId, diaryBodyId, trStatusDataName, seletedTrDataName, 
				trStatusMeaning, deleteBtnId, clearBtnId, diaryTrStatusTextId, 
				workHourTextSpanId, curHour, string1MinusString2){
		
		var trStatus = $('#' + diaryTrId).data(trStatusDataName);
		
		var trStatusString = getTrStatusString(trStatus, trStatusMeaning);
		
		var ans = confirm('確定要刪除列嗎? 列狀態 : ' + trStatusString);
		
		if(ans){
			
			$('#' + diaryTrId).remove();
			$('#' + diaryBodyId).data(seletedTrDataName, '');
			$('#' + deleteBtnId).prop('disabled', true);
			$('#' + clearBtnId).prop('disabled', true);
			resetDiaryTrStatusText(diaryTrStatusTextId, trStatusMeaning);
			setDiaryHourText(workHourTextSpanId, curHour, string1MinusString2);
			
		}
	}
	
	function clearDiaryTr(diaryTrId, diaryIdPrefixes, trStatusDataName, diaryCountDataName, trStatusMeaning, diaryTrStatusTextId, 
				resetDiaryProjectFctn, resetDiaryPhaseFctn, resetDiaryWorkFctn, resetDiaryTextFctn, resetDiaryHourFctn, 
				workHourTextSpanId, curHour, string1MinusString2){
		
		var trStatus = $('#' + diaryTrId).data(trStatusDataName);
		
		var trStatusString = getTrStatusString(trStatus, trStatusMeaning);
		
		var ans = confirm('確定要清除列嗎? 列狀態 : ' + trStatusString);
		
		if(ans){
			
			var diaryTrCount = $('#' + diaryTrId).data(diaryCountDataName);
			resetDiaryProjectFctn(diaryIdPrefixes.projectIdPrefix + diaryTrCount);
			resetDiaryPhaseFctn(diaryIdPrefixes.phaseIdPrefix + diaryTrCount);
			resetDiaryWorkFctn(diaryIdPrefixes.workIdPrefix + diaryTrCount);
			resetDiaryTextFctn(diaryIdPrefixes.textIdPrefix + diaryTrCount);
			resetDiaryHourFctn(diaryIdPrefixes.hourIdPrefix + diaryTrCount);
			
			$('#' + diaryTrId).data(trStatusDataName, '1');
			resetDiaryTrStatusText(diaryTrStatusTextId, trStatusMeaning);
			setDiaryHourText(workHourTextSpanId, curHour, string1MinusString2);
		}
	}
	
	function resetDiary(diaryBodyId, diaryTrStatusTextId, workHourTextSpanId, trStatusMeaning, deleteBtnId, clearBtnId, diaryBodyId, seletedTrDataName){
		
		var ans = confirm('確定要全部重製嗎?');
		
		if(ans){
			
			$('#' + diaryBodyId).empty();
			resetDiaryTrStatusText(diaryTrStatusTextId, trStatusMeaning);
			$('#' + workHourTextSpanId).text('0');
			$('#' + deleteBtnId).prop('disabled', true);
			$('#' + clearBtnId).prop('disabled', true);
			$('#' + diaryBodyId).data(seletedTrDataName, '');
		}
	}
	
	function getTrStatusString(trStatus, trStatusMeaning){
		
		if(trStatus == '1'){
			return trStatusMeaning.status1;
		}else if(trStatus == '2'){
			return trStatusMeaning.status2;
		}else if(trStatus == '3'){
			return trStatusMeaning.status3;
		}
	}
	function resetDiaryTrStatusText(diaryTrStatusTextId, trStatusMeaning){
		
		$('#' + diaryTrStatusTextId).text(trStatusMeaning.status1);
		$('#' + diaryTrStatusTextId).css('color', '#FF0000');
	}
	function setDiaryHourText(workHourTextSpanId, hour, string1MinusString2){
		
		var workHourText = $('#' + workHourTextSpanId).text();
		var result = string1MinusString2(workHourText, hour);
		$('#' + workHourTextSpanId).text(result);
	}
	