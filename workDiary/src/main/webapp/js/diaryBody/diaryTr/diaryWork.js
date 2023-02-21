
	
	class DiaryWorkService{
		
		static addOption(work, option){
			
			work.addOption(option);
			work.jquery.append(option.html);
		}
	}
	