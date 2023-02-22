
	// js/classDefine.js
	// js/diaryContent/diaryBody/diaryTr/diaryWork.js
	
	function diaryPhasePrepare(diaryTr){
		
		var diaryPhase = diaryTr.phase;
		diaryPhase.onChange = function(){
			
			diaryPhaseChangeAjax(diaryTr);
		}
		diaryPhase.jquery.change(diaryPhase.onChange);
	}
	
	function diaryPhaseChangeAjax(diaryTr){
	
		var ajaxUrl = phaseChangeUrl;
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
	
	