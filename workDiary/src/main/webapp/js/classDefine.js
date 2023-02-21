
	class DiaryObj{
		
		count;
		value;
		idPrefix;
		tr;
		
		constructor(count, value, idPrefix){
			
			this.count = count;
			this.value = value;
			this.idPrefix = idPrefix;
		}
		
		get id(){
			
			return this.idPrefix + this.count;
		}
		get jquery(){
			
			return $('#' + this.id);
		}
		
	}
	class DiarySelectObject extends DiaryObj{
		
		options;
		
		constructor(count, value, idPrefix){
			
			super(count, value, idPrefix);
		}
		
		addOption(option){
			
			options[options.length] = option;
		}
		optionForEach(forEachFctn){
			
			options.forEach(forEachFctn);
		}
		
		static Option = class{
			
			constructor(value, name){
				
				this.value = value;
				this.name = name;
			}
			
			get html(){
				
				return '<option value="' + this.value + '">' + this.name + '</option>';
			}
		}
	}
	class DiaryProject extends DiarySelectObject{
		
		constructor(count, value){
			
			super(count, value, diaryProjectIdPrefix);
		}
	}
	class DiaryPhase extends DiarySelectObject{
		
		constructor(count, value){
			
			super(count, value, diaryPhaseIdPrefix);
		}
	}
	class DiaryWork extends DiarySelectObject{
		
		constructor(count, value){
			
			super(count, value, diaryWorkIdPrefix);
		}
	}
	class DiaryText extends DiaryObj{
		
		constructor(count, value){
			
			super(count, value, diaryTextIdPrefix);
		}
	}
	class DiaryHour extends DiaryObj{
		
		constructor(count, value){
			
			super(count, value, diaryHourIdPrefix);
		}
	}
	
	class DiaryTr{
		
		count;
		project;
		phase;
		work;
		text;
		hour;
		status;
		body;
		
		constructor(count, projectValue, phaseValue, workValue, textValue, hourValue, status){
			
			this.count = count;
			this.project = new DiaryProject(count, projectValue);
			this.project.tr = this;
			this.phase = new DiaryPhase(count, phaseValue);
			this.phase.tr = this;
			this.work = new DiaryWork(count, workValue);
			this.work.tr = this;
			this.text = new DiaryText(count, textValue);
			this.text.tr = this;
			this.hour = new DiaryHour(count, hourValue);
			this.hour.tr = this;
			this.status = status;
		}
		
		get id(){
			
			return diaryTrIdPrefix + this.count;
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
		
		trs;
		
		constructor(trs){
			
			this.trs = trs;
			this.trs.forEach(function(currentValue){
				
				currentValue.body = this;
			});
			this.selectedId = '';
		}
		
		addTr(tr){
			
			trs[trs.length] = tr;
		}
		trForEach(forEachFctn){
			
			trs.forEach(forEachFctn);
		}
		get id(){
			
			return diaryBodyId;
		}
		get jquery(){
			
			return $('#' + this.id);
		}
		
		get selectedTr(){
			
			for(var i=0; i<diaryTrs.length; i++){
				
				var diaryTr = diaryTrs[i];
				if(diaryTr.id == selectedId)
					return diaryTr;
			}
		}
	}
	
	
	let diaryBody = new DiaryBody([]);
	
	