
	// js/classDefine.js
	// js/diaryContent/diaryBody/diaryTr/diaryPhase.js
	
	function diaryProjectPrepare(diaryTr){
		
		var diaryProject = diaryTr.project;
		diaryProject.onChange = function(){
			
			diaryProjectChangeAjax(diaryTr);
		}
		diaryProject.jquery.change(diaryProject.onChange);
	}
	
	function diaryProjectChangeAjax(diaryTr){
	
		var ajaxUrl = projectChangeUrl;
		var ajaxData = {
			diary_project_id : diaryTr.project.value
		}
		
		$.ajax({
			url : ajaxUrl,
			method : 'GET',
			data : ajaxData,
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			dataType : 'json',
			success : function(data){
				
				diaryProjectChangeAjaxSuccessFctn(diaryTr, data);
			}
		});
	}
	function diaryProjectChangeAjaxSuccessFctn(diaryTr, data){
		
		diaryTr.phase.toDefault();
		diaryTr.work.toDefault();
		diaryPhaseAddOptionsVOs(data, diaryTr.phase);
	}
	