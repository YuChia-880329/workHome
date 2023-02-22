
	// js/classDefine.js
	
	function diaryWorkAddOptionsVOs(optionsVOs, work){
		
		optionsVOs.forEach(function(currentValue){
			
			var option = new DiarySelectObject.Option(currentValue.work_id, currentValue.work_name);
			work.addOption(option);
		});
	}