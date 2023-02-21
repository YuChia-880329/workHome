
	// js/diaryBody/diaryTr/diaryWork.js
	
	class DiaryPhaseService{
		
		static getSameTrWork(phase){
			
			return phase.tr.work;
		}
		
		static addOption(phase, option){
			
			phase.addOption(option);
			phase.jquery.append(option.html);
		}
	}
	
	function diaryPhaseChangeHandler(){
		
		diaryPhaseChangeAjax(this);
	}
	
	function diaryPhaseChangeAjax(thisPhase){
	
		var ajaxUrl = 'phaseChange';
		var ajaxData = {
			diary_phase_id : thisPhase.value
		}
		var work = DiaryPhaseService.getSameTrWork(thisPhase);
		
		$.ajax({
			url : ajaxUrl,
			method : 'GET',
			data : ajaxData,
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			dataType : 'json',
			success : function(data){
				
				diaryPhaseChangeAjaxSuccessFctn(work, data);
			}
		});
	}
	function diaryPhaseChangeAjaxSuccessFctn(work, data){
		
		data.forEach(function(currentValue){
			
			var option = new DiarySelectObject.Option(currentValue.work_id, currentValue.work_name);
			DiaryWorkService.addOption(work, option);
		});
	}