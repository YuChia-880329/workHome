	
	function getDiaryContent(tmplId, diaryCount, diaryIdPrefixes){
		
		var diaryTmplClone = getTmplClone(tmplId);
		setDiaryProjId(diaryTmplClone, diaryCount, diaryIdPrefixes);
		setDiaryPhaseId(diaryTmplClone, diaryCount, diaryIdPrefixes);
		setDiaryWorkId(diaryTmplClone, diaryCount, diaryIdPrefixes);
		setDiaryTextId(diaryTmplClone, diaryCount, diaryIdPrefixes);
		setDiaryHourId(diaryTmplClone, diaryCount, diaryIdPrefixes);
		setDiaryStatusId(diaryTmplClone, diaryCount, diaryIdPrefixes);
		setDiaryTrId(diaryTmplClone, diaryCount, diaryIdPrefixes);
		
		var diaryHtml = diaryTmplClone.html();
		return diaryHtml;
	}
	
	function getTmplClone(tmplId){
		
		var diaryTmpl = $('template#' + tmplId);
		var diaryTmplClone = diaryTmpl.clone();
		return diaryTmplClone;
	}
	
	function setDiaryProjId(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.projectIdPrefix);
	}
	function setDiaryPhaseId(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.phaseIdPrefix);
	}
	function setDiaryWorkId(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.workIdPrefix);
	}
	function setDiaryTextId(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.textIdPrefix);
	}
	function setDiaryHourId(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.hourIdPrefix);
	}
	function setDiaryStatusId(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.statusIdPrefix);
	}
	
	function setDiaryTrId(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.trIdPrefix);
	}
	
	function setDiaryId(diaryTmplClone, diaryCount, idPrefix){
		
		var id = idPrefix + diaryCount;
		var target = diaryTmplClone[0].content.querySelector('#' + idPrefix + 'tmpl');
		$(target).attr('id', id);
	}