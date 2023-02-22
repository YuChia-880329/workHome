
	// js/classDefine.js
	// js/diaryContent/diaryBody/diaryTr/diaryPhase.js
	
	function diaryProjectPrepare(diaryTr){
		
		diaryTr.project.jquery.change(function(){
			
			diaryProjectChangeAjax(diaryTr);
		});
	}
	
	function diaryProjectChangeAjax(diaryTr){
	
		var ajaxUrl = 'projChange';
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
	