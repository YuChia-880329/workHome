
	// js/classDefine.js
	// js/diaryContent/diaryBody/diaryTr/diaryWork.js
	
	function diaryPhasePrepare(diaryTr){
		
		diaryTr.phase.jquery.change(function(){
			
			diaryPhaseChangeAjax(diaryTr);
		});
	}
	
	function diaryPhaseChangeAjax(diaryTr){
	
		var ajaxUrl = 'phaseChange';
		var ajaxData = {
			diary_phase_id : diaryTr.phase.value
		}
		
		$.ajax({
			url : ajaxUrl,
			method : 'GET',
			data : ajaxData,
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			dataType : 'json',
			success : function(data){
				
				diaryPhaseChangeAjaxSuccessFctn(diaryTr, data);
			}
		});
	}
	function diaryPhaseChangeAjaxSuccessFctn(diaryTr, data){
		
		diaryTr.work.toDefault();
		diaryWorkAddOptionsVOs(data, diaryTr.work);
	}
	
	function diaryPhaseAddOptionsVOs(optionsVOs, phase){
		
		optionsVOs.forEach(function(currentValue){
			
			var option = new DiarySelectObject.Option(currentValue.phase_id, currentValue.phase_name);
			phase.addOption(option);
		});
	}
	
	