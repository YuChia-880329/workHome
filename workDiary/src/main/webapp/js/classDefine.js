
	// js/variable.js
	
	class Component{
		
		get jquery(){
			
			return $('#' + this.id);
		}
		get html(){
			
			return this.jquery.html();
		}
		get element(){
			
			return this.jquery[0];
		}
	}
	class ValuedComponent extends Component{
		
		get value(){
			
			return this.jquery.val();
		}
		
		set value(value){
			
			this.jquery.val(value);
		}
	}
	class DiaryObj extends ValuedComponent{
		
		count;
		tr;
		
		constructor(count, tr){
			
			super();
			this.count = count;
			this.tr = tr;
		}
		
		get id(){
			
			return this.idPrefix + this.count;
		}
	}
	class DiarySelectObject extends DiaryObj{
		
		addOption(option){
			
			this.jquery.append(option.html);
		}
		
		check(){
			
			return this.value != '0';
		}
		
		static getDefaultOption(){
			
			return new DiarySelectObject.Option(0, '請選擇');
		}
		
		toDefault(){
			
			this.jquery.empty();
			this.jquery.append(DiarySelectObject.getDefaultOption().html);
		}
		
		get options(){
			
			var optionNodes = this.element.querySelectorAll('option');
			var options = [];
			
			for(var i=0; i<optionNodes.length; i++){
				
				var optionNode = optionNodes[i];
				options[i] = new DiarySelectObject.Option(optionNode.val(), optionNode.text());
			}
		}
		
		
		
		static Option = class{
			
			constructor(value, text){
				
				this.value = value;
				this.text = text;
			}
			
			get html(){
				
				return '<option value="' + this.value + '">' + this.text + '</option>';
			}
		}
	}
	
	
	class DiaryProject extends DiarySelectObject{
		
		get idPrefix(){
			
			return diaryProjectIdPrefix;
		}
	}
	class DiaryPhase extends DiarySelectObject{
		
		get idPrefix(){
			
			return diaryPhaseIdPrefix;
		}
		
		
	}
	class DiaryWork extends DiarySelectObject{
		
		get idPrefix(){
			
			return diaryWorkIdPrefix;
		}
	}
	class DiaryText extends DiaryObj{
		
		get idPrefix(){
			
			return diaryTextIdPrefix;
		}
	}
	class DiaryHour extends DiaryObj{
		
		
		check(){
			
			var v = this.value;
			
			if(v==null || v.trim()=='' || isNaN(v) || parseFloat(v)<0)
				return false;
			
			return true;
		}
		
		get idPrefix(){
			
			return diaryHourIdPrefix;
		}
		get originalHour(){
			
			return this.jquery.data(originalHourDataName);
		}
		set originalHour(originalHour){
			
			this.jquery.data(originalHourDataName, originalHour);
		}
	}
	
	class DiaryTr extends Component{
		
		count;
		project;
		phase;
		work;
		text;
		hour;
		body;

		constructor(count, body){
			
			super();
			this.count = count;
			this.jquery.data(countDataName, count);
			this.project = new DiaryProject(count, this);
			this.phase = new DiaryPhase(count, this);
			this.work = new DiaryWork(count, this);
			this.text = new DiaryText(count, this);
			this.hour = new DiaryHour(count, this);
			this.body = body;
		}
		
		setColor(colorString){
			
			this.jquery.css('background-color', colorString);
		}
		
		trToDiaryContentVo(dateString){
			
			var vo = {};
			vo.count = this.count;
			vo.date = dateString;
			vo.projectId = this.project.value;
			vo.phaseId = this.phase.value;
			vo.workId = this.work.value;
			vo.text = this.text.value;
			vo.workHour = this.hour.value;
			vo.status = this.status;
			
			return vo;
		}
		
		checkWhenSend(){
			
			if(!this.project.check()){
				
				alert('請選擇專案');
				return false;
			}else if(!this.phase.check()){
				
				alert('請選擇階段');
				return false;
			}else if(!this.work.check()){
				
				alert('請選擇工作項目');
				return false;
			}
			if(!this.hour.check()){
				
				alert('未輸入工時或輸入格式錯誤');
				return false;
			}
			return true;
		}
		
		static trsToDiaryContentVos(diaryTrs, dateString){
			
			var vos = [];
			
			for(var i=0; i<diaryTrs.length; i++){
				
				vos[i] = diaryTrs[i].trToDiaryContentVo(dateString);
			}
			
			return vos;
		}
		
		get id(){
			
			return diaryTrIdPrefix + this.count;
		}
		
		get status(){
			
			return this.jquery.data(statusDataName);
		}
		
		
		set status(status){
			
			this.jquery.data(statusDataName, status);
		}
		/*static getDiaryTrByJson(trJson){
			
			return new DiaryTr(trJson.count, trJson.projectId, trJson.phaseId, 
						trJson.workId, trJson.text, trJson.workHour, trJson.status);
		}*/
	}
	
	
	class DiaryBody extends Component{
		
		addTr;
		
		checkTrsWhenSend(){
			
			var trList = this.trs;
			
			for(var i=0; i<trList.length; i++){
				
				if(!trList[i].checkWhenSend())
					return false;
			}
			return true;
		}
		
		get id(){
			
			return diaryBodyId;
		}
		
		get trs(){
			
			var trsNodes = this.element.querySelectorAll('tr');
			var ans = [];
			
			for(var i=0; i<trsNodes.length; i++){
				
				var trNode = trsNodes[i];
				ans[i] = new DiaryTr($(trNode).data(countDataName), this);
			}
			return ans;
		}
		
		get selectedTrCount(){
			
			return this.jquery.data(selectedTrCountDataName);
		}
		
		set selectedTrCount(selectedTrCount){
			
			this.jquery.data(selectedTrCountDataName, selectedTrCount);
		}
		
	}
	
	
	
	class Template extends Component{
		
		get id(){
			
			return diaryTmplId;
		}
		
		get clone(){
			
			return new TemplateClone(this);
		}
		
	}
	
	class TemplateCloneComponent{
		
		idPrefix;
		id;
		templateClone;
		
		constructor(idPrefix){
			
			this.idPrefix = idPrefix;
			this.id = idPrefix + 'tmpl';
		}
		
		renameId(count){
			
			var countId = this.idPrefix + count;
			this.jquery.attr('id', countId);
			this.id = countId;
		}
		
		get element(){
			
			return this.templateClone.queryElement('#' + this.id);
		}
		get jquery(){
			
			return $(this.element);
		}
	}
	class TemplateTr extends TemplateCloneComponent{
		
		templateProject;
		templatePhase;
		templateWork;
		templateText;
		templateHour;
		
		constructor(){
			
			super(diaryTrIdPrefix);
			this.templateProject = new TemplateCloneComponent(diaryProjectIdPrefix);
			this.templatePhase = new TemplateCloneComponent(diaryPhaseIdPrefix);
			this.templateWork = new TemplateCloneComponent(diaryWorkIdPrefix);
			this.templateText = new TemplateCloneComponent(diaryTextIdPrefix);
			this.templateHour = new TemplateCloneComponent(diaryHourIdPrefix);
		}
		
		renameId(count){
			
			super.renameId(count);
			this.templateProject.renameId(count);
			this.templatePhase.renameId(count);
			this.templateWork.renameId(count);
			this.templateText.renameId(count);
			this.templateHour.renameId(count);
		}
	}
	
	class TemplateClone{
		
		element;
		templateTr;
		
		constructor(template){
			
			this.element = template.jquery.clone()[0];
			this.templateTr = new TemplateTr();
			this.templateTr.templateClone = this;
			this.templateTr.templateProject.templateClone = this;
			this.templateTr.templatePhase.templateClone = this;
			this.templateTr.templateWork.templateClone = this;
			this.templateTr.templateText.templateClone = this;
			this.templateTr.templateHour.templateClone = this;
		}
		
		queryAllNodes(selector){
			
			return this.content.querySelectorAll(selector);
		}
		queryElement(selector){
			
			return this.content.querySelector(selector);
		}
		
		renameId(count){
			
			this.templateTr.renameId(count);
		}
		
		
		get jquery(){
			
			return $(this.element);
		}
		get html(){
			
			return this.jquery.html();
		}
		get content(){
			
			return this.element.content;
		}
		
	}
	
	
	class Btn extends Component{

		constructor(id){
			
			super();
			this.id = id;
		}
		
		disable(){
			
			this.jquery.prop('disabled', true);
		}
		able(){
			
			this.jquery.prop('disabled', false);
		}
		
	}
	
	class Calendar extends ValuedComponent{

		onSelect;
		
		static dateFormat = 'yy/mm/dd';
		
		static parseDate(dateString){
			
			return $.datepicker.parseDate(Calendar.dateFormat, dateString);
		}
		get id(){
			
			return calendarDivId;
		}
		
		get value(){
			
			return super.value;
		}
		set value(value){
			
			this.jquery.datepicker('setDate', value);
		}
		
	}
	
	class TextComponent extends Component{
		
		get text(){
			
			return this.jquery.text();
		}
		set text(text){
			
			this.jquery.text(text);
		}
	}
	class DateText extends TextComponent{
		
		get id(){
			
			return dateTextDivId;
		}
	}
	class WorkHourText extends TextComponent{
		
		get id(){
			
			return workHourTextSpanId;
		}
	}
	class DiaryTrStatusText extends TextComponent{
		
		setTextColor(colorString){
			
			this.jquery.css('color', colorString);
		}
		
		get id(){
			
			return diaryTrStatusTextId;
		}
	}
	
	
	
/*	let diaryBody = new DiaryBody();
	
	let template = new Template();
	
	let createBtn = new Btn(createBtnId);
	let deleteBtn = new Btn(deleteBtnId);
	let clearBtn = new Btn(clearBtnId);
	let resetBtn = new Btn(resetBtnId);
	
	let saveBtn = new Btn(saveBtnId);
	let sentBtn = new Btn(sentBtnId);
	
	let calendar = new Calendar();
	let dateText = new DateText();
	let workHourText = new WorkHourText();
	let diaryTrStatusText = new DiaryTrStatusText(); */
	