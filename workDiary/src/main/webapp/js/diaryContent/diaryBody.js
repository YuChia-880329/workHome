
	// js/classDefine.js
	// js/diaryContent/diaryBody/diaryTr/diaryProject.js
	// js/diaryContent/diaryBody/diaryTr/diaryPhase.js

	function diaryBodyPrepare(){
		
		diaryBody.addTr = diaryBodyAddTr;
		diaryBody.addStartUpTr = diaryBodyAddStartUpTr;
	}
	function diaryBodyAddTr(count, optionsJson){
		
		var templateClone = template.clone;
		templateClone.renameId(count);
		this.jquery.append(templateClone.html);
		
		var diaryTr = diaryBody.getTr(count);
		addTrDiaryProjectPrepare(diaryTr, optionsJson);
		diaryProjectPrepare(diaryTr);
		diaryPhasePrepare(diaryTr);
	}
	
	function addTrDiaryProjectPrepare(diaryTr, optionsJson){
		
		var project = diaryTr.project;
		var optionsObj = JSON.parse(optionsJson);
		optionsObj.forEach(function(currenValue){
			
			var option = new DiarySelectObject.Option(currenValue.project_id, currenValue.project_name);
			project.addOption(option);
		});
	}
	
	function diaryBodyAddStartUpTr(startUpTrsJson, optionsJson){
		
		var startUpTrs = JSON.parse(startUpTrsJson);
		startUpTrs.forEach(function(currentValue){
			
			var count = currentValue.count;
			diaryBody.addTr(count, optionsJson);
			var diaryTr = diaryBody.getTr(count);
			
			diaryTr.project.value = currentValue.projectId;
			diaryPhaseAddOptionsVOs(currentValue.phase_vos, diaryTr.phase);
			diaryTr.phase.value = currentValue.phaseId;
			diaryWorkAddOptionsVOs(currentValue.phase_vos[diaryTr.phase.value-1].work_vos, diaryTr.work);
			diaryTr.work.value = currentValue.workId;
			diaryTr.text.value = currentValue.text;
			diaryTr.hour.value = currentValue.workHour;
			diaryTr.status = currentValue.status;
		});
	}