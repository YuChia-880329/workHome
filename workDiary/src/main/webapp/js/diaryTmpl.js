	
	function getDiaryContent(tmplId, diaryCount, diaryIdPrefixes){
		
		var diaryTmplClone = getTmplClone(tmplId);
		setDiaryProjIdName(diaryTmplClone, diaryCount, diaryIdPrefixes);
		setDiaryPhaseIdName(diaryTmplClone, diaryCount, diaryIdPrefixes);
		setDiaryWorkIdName(diaryTmplClone, diaryCount, diaryIdPrefixes);
		setDiaryTextIdName(diaryTmplClone, diaryCount, diaryIdPrefixes);
		setDiaryHourIdName(diaryTmplClone, diaryCount, diaryIdPrefixes);
		setDiaryTrId(diaryTmplClone, diaryCount, diaryIdPrefixes);
		
		setTrData(diaryTmplClone, diaryCount, diaryIdPrefixes);
		
		var diaryHtml = diaryTmplClone.html();
		return diaryHtml;
	}
	
	function getTmplClone(tmplId){
		
		var diaryTmpl = $('#' + tmplId);
		var diaryTmplClone = diaryTmpl.clone();
		return diaryTmplClone;
	}
	
	function setDiaryProjIdName(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.projectIdPrefix);
		setDiaryName(diaryTmplClone, diaryCount, diaryIdPrefixes.projectIdPrefix)
	}
	function setDiaryPhaseIdName(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.phaseIdPrefix);
		setDiaryName(diaryTmplClone, diaryCount, diaryIdPrefixes.phaseIdPrefix)
	}
	function setDiaryWorkIdName(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.workIdPrefix);
		setDiaryName(diaryTmplClone, diaryCount, diaryIdPrefixes.workIdPrefix)
	}
	function setDiaryTextIdName(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.textIdPrefix);
		setDiaryName(diaryTmplClone, diaryCount, diaryIdPrefixes.textIdPrefix)
	}
	function setDiaryHourIdName(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.hourIdPrefix);
		setDiaryName(diaryTmplClone, diaryCount, diaryIdPrefixes.hourIdPrefix)
	}

	function setDiaryTrId(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		setDiaryId(diaryTmplClone, diaryCount, diaryIdPrefixes.trIdPrefix);
	}
	
	function setDiaryId(diaryTmplClone, diaryCount, idPrefix){
		
		var id = idPrefix + diaryCount;
		var target = diaryTmplClone[0].content.querySelector('#' + idPrefix + 'tmpl');
		$(target).attr('id', id);
	}
	function setDiaryName(diaryTmplClone, diaryCount, idPrefix){
		
		var id = idPrefix + diaryCount;
		var name = id;
		var target = diaryTmplClone[0].content.querySelector('#' + id);
		$(target).attr('name', name);
	}
	
	
	function setTrData(diaryTmplClone, diaryCount, diaryIdPrefixes){
		
		var diaryTrElement = diaryTmplClone[0].content.querySelector('#' + diaryIdPrefixes.trIdPrefix + diaryCount);
		var diaryTr = $(diaryTrElement);
	}