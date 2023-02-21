
	// js/diaryBody/diaryTr/diaryPhase.js
	
	class DiaryProjectService{
		
		static getSameTrPhase(project){
			
			return project.tr.phase;
		}
		
		static addOption(project, option){
			
			project.addOption(option);
			project.jquery.append(option.html);
		}
	}
	
	function diaryProjectChangeHandler(){
		
		diaryProjectChangeAjax(this);
	}
	
	function diaryProjectChangeAjax(thisProject){
	
		var ajaxUrl = 'projChange';
		var ajaxData = {
			diary_project_id : thisProject.value
		}
		var phase = DiaryProjectService.getSameTrPhase(thisProject);
		
		$.ajax({
			url : ajaxUrl,
			method : 'GET',
			data : ajaxData,
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			dataType : 'json',
			success : function(data){
				
				diaryProjectChangeAjaxSuccessFctn(phase, data);
			}
		});
	}
	function diaryProjectChangeAjaxSuccessFctn(phase, data){
		
		data.forEach(function(currentValue){
			
			var option = new DiarySelectObject.Option(currentValue.phase_id, currentValue.phase_name);
			DiaryPhaseService.addOption(phase, option);
		});
	}
	