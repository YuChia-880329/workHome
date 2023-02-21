
	let diaryIdPrefixes = {
		'projectIdPrefix' : 'diary_project_',
		'phaseIdPrefix' : 'diary_phase_',
		'workIdPrefix' : 'diary_work_',
		'textIdPrefix' : 'diary_text_',
		'hourIdPrefix' : 'diary_hour_',
		'trIdPrefix' : 'diary_tr_'
	}
	
	
	class DiaryProject{
		
		onChange;
		
		constructor(count, value){
			
			this.count = count;
			this.value = value;
		}
		
		get id(){
			
			return diaryIdPrefixes.projectIdPrefix + this.count;
		}
		get jquery(){
			
			return $('#' + this.id);
		}
		
	}
	class DiaryPhase{
		
		onChange;
		
		constructor(count, value){
			
			this.count = count;
			this.value = value;
		}
		
		get id(){
			
			return diaryIdPrefixes.phaseIdPrefix + this.count;
		}
		get jquery(){
			
			return $('#' + this.id);
		}
		
	}
	class DiaryWork{
		
		constructor(count, value){
			
			this.count = count;
			this.value = value;
		}
		
		get id(){
			
			return diaryIdPrefixes.workIdPrefix + this.count;
		}
		get jquery(){
			
			return $('#' + this.id);
		}
		
	}
	class DiaryText{
		
		constructor(count, value){
			
			this.count = count;
			this.value = value;
		}
		
		get id(){
			
			return diaryIdPrefixes.textIdPrefix + this.count;
		}
		get jquery(){
			
			return $('#' + this.id);
		}
		
	}
	class DiaryHour{
		
		onFocus;
		onFocusOut;
		
		constructor(count, value){
			
			this.count = count;
			this.value = value;
		}
		
		get id(){
			
			return diaryIdPrefixes.hourIdPrefix + this.count;
		}
		get jquery(){
			
			return $('#' + this.id);
		}
	}
	
	class DiaryTr{
		
		onMouseEnter;
		onMouseLeave;
		
		constructor(count, projectValue, phaseValue, workValue, textValue, hourValue, status){
			
			this.project = new DiaryProject(count, projectValue);
			this.phase = new DiaryProject(count, phaseValue);
			this.work = new DiaryProject(count, workValue);
			this.text = new DiaryProject(count, textValue);
			this.hour = new DiaryProject(count, hourValue);
			this.status = status;
		}
		
		get id(){
			
			return diaryIdPrefixes.trIdPrefix + this.count;
		}
		get jquery(){
			
			return $('#' + this.id);
		} 
		
		static getDiaryTrByJson(trJson){
			
			return new DiaryTr(trJson.count, trJson.projectId, trJson.phaseId, 
						trJson.workId, trJson.text, trJson.workHour, trJson.status);
		}
	}
	
	
	class DiaryBody{
		
		constructor(diaryTrs){
			
			this.diaryTrs = diaryTrs;
			this.selectedId = '';
		}
		
		get id(){
			
			return 'diary_body';
		}
		get jquery(){
			
			return $('#' + this.id);
		}
		
		get selectedTr(){
			
			for(var i=0; i<diaryTrs.length; i++){
				
				if(diaryTrs[i].id == selectedId)
					return diaryTrs[i];
			}
		}
	}
	