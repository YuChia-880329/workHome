	
	let projectChangeUrl = 'projChange';
	let phaseChangeUrl = 'phaseChange';
	
	function projectChangedAjax(projectValue, phaseSelectId){
	
		var ajaxData = {
			'diary_project_id' : projectValue
		}
		
		diaryContentChangedAjax(projectChangeUrl, ajaxData, phaseSelectId, appendDiaryPhase);
	}
	function phaseChangedAjax(phaseValue, workSelectId){
	
		var ajaxData = {
			'diary_phase_id' : phaseValue
		}
		
		diaryContentChangedAjax(phaseChangeUrl, ajaxData, workSelectId, appendDiaryWork);
	}
	
	function diaryContentChangedAjax(ajaxUrl, ajaxData, selectId, successFctn){
	
		$.ajax({
			url : ajaxUrl,
			method : 'GET',
			data : ajaxData,
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			dataType : 'json',
			success : function(data){
				successFctn(data, selectId);
			}
		});
	}
	
	var optionTmpl = function(id, name){
		
		this.optionStr = '<option value="' + id + '">' + name + '</option>';
	}
	function appendDiaryPhase(diaryPhases, phaseSelectId){
		
	
		diaryPhases.forEach(function(currentValue){
			
			var option = new optionTmpl(currentValue.phase_id, currentValue.phase_name);
			$('#' + phaseSelectId).append(option.optionStr);
		});
	}
	function appendDiaryWork(diaryWorks, workSelectId){
		
		
		diaryWorks.forEach(function(currentValue){
			
			var option = new optionTmpl(currentValue.work_id, currentValue.work_name);
			$('#' + workSelectId).append(option.optionStr);
		});
	}