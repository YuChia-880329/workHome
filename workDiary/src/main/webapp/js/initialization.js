
	/*---------------------------------- init -----------------------------------*/
	
	function diaryBodyInit(diaryBody){
		
		diaryBody.addTr = diaryBodyAddTrFctn;
	}
	function diaryTrInit(diaryTr, workHourText, deleteBtn, clearBtn, diaryTrStatusText){
		
		diaryTr.jquery.mouseenter(function(){
			
			diaryTrOnMouseenterHandler(diaryTr);
		});
		
		diaryTr.jquery.mouseleave(function(){
			
			diaryTrOnMouseleaveHandler(diaryTr);
		});
		diaryTr.jquery.click(function(){
			
			diaryTrOnClickHandler(diaryTr, deleteBtn, clearBtn, diaryTrStatusText);
		});
		
		diaryProjectInit(diaryTr.project);
		diaryPhaseInit(diaryTr.phase);
		diaryHourInit(diaryTr.hour, workHourText);
	}
	function diaryProjectInit(diaryProject){

		diaryProject.jquery.change(function(){
			
			diaryProjectOnChangeHandler(diaryProject);
		});
	}
	function diaryPhaseInit(diaryPhase){

		diaryPhase.jquery.change(function(){
			
			diaryPhaseOnChangeHandler(diaryPhase);
		});
	}
	function diaryHourInit(diaryHour, workHourText){
		
		diaryHour.jquery.focus(function(){
			
			diaryHourOnFocusHandler(diaryHour, workHourText)
		});
		diaryHour.jquery.focusout(function(){
			
			diaryHourOnFocusoutHandler(diaryHour, workHourText)
		});
	}
	
	
	function createBtnInit(createBtn, diaryBody, optionsJson, template, workHourText, deleteBtn, clearBtn, diaryTrStatusText){
		
		createBtn.jquery.click(function(){
			
			createBtnOnClickHandler(diaryBody, optionsJson, template, workHourText, deleteBtn, clearBtn, diaryTrStatusText);
		});
	}
	function deleteBtnInit(deleteBtn, diaryBody, workHourText, deleteBtn, clearBtn, diaryTrStatusText){
		
		deleteBtn.jquery.click(function(){
			
			deleteBtnOnClickHandler(diaryBody, workHourText, deleteBtn, clearBtn, diaryTrStatusText);
		});
	}
	function clearBtnInit(clearBtn, diaryBody, workHourText, optionsJson){
		
		clearBtn.jquery.click(function(){
			
			clearBtnOnClickHandler(diaryBody, workHourText, optionsJson);
		});
	}
	function resetBtnInit(resetBtn, diaryBody, workHourText, deleteBtn, clearBtn, diaryTrStatusText){
		
		resetBtn.jquery.click(function(){
			
			resetBtnOnClickHandler(diaryBody, workHourText, deleteBtn, clearBtn, diaryTrStatusText);
		});
	}
	function saveBtnInit(saveBtn, diaryBody, dateText){
		
		saveBtn.jquery.click(function(){
			
			saveBtnOnClickHandler(diaryBody, dateText);
		});
	}
	function sendBtnInit(sendBtn, diaryBody, dateText){
		
		sendBtn.jquery.click(function(){
			
			sendBtnOnClickHandler(diaryBody, dateText);
		});
	}
	
	
	
	function calendarInit(calendar){
		
		calendar.onSelect = calendarOnSelectHandler;
	}
	
	
	/*---------------------------------- init function -----------------------------------*/
	
	function diaryBodyAddTrFctn(count, optionsJson, template, workHourText, deleteBtn, clearBtn, diaryTrStatusText){

		var templateClone = template.clone;
		templateClone.renameId(count);
		this.jquery.append(templateClone.html);
		
		var diaryTr = new DiaryTr(count, this);
		diaryTrInit(diaryTr, workHourText, deleteBtn, clearBtn, diaryTrStatusText);
		
		DiaryBodyAddTrUtil.diaryProjectAddOptionsVOs(JSON.parse(optionsJson), diaryTr.project);
		diaryTr.status = '1';
	}
	function diaryTrOnMouseenterHandler(diaryTr){
		
		diaryTr.setColor(mouseenterColorString);
	}
	function diaryTrOnMouseleaveHandler(diaryTr){
		
		diaryTr.setColor(mouseleaveColorString);
	}
	function diaryTrOnClickHandler(diaryTr, deletedBtn, clearBtn, diaryTrStatusText){
		
		diaryTr.setColor(mouseClickColorString);
		diaryTr.jquery.off('mouseenter');
		diaryTr.jquery.off('mouseleave');
				
		var originalSeletedTrCount = diaryTr.body.selectedTrCount;
		
		if(originalSeletedTrCount != ''){
			
			var originalTr = new DiaryTr(originalSeletedTrCount, diaryTr.body);
			
			if(originalTr.id != diaryTr.id){
			
				originalTr.setColor(mouseleaveColorString);
				
				originalTr.jquery.mouseenter(function(){
						
					diaryTrOnMouseenterHandler(originalTr);
				});
				originalTr.jquery.mouseleave(function(){
					
					diaryTrOnMouseleaveHandler(originalTr);
				});
				
				DiaryTrClickUtil.statusTextChange(diaryTrStatusText, diaryTr.status);
			}
		}else{
					
			deletedBtn.able();
			clearBtn.able();
			DiaryTrClickUtil.statusTextChange(diaryTrStatusText, diaryTr.status);
		}
		
		diaryTr.body.selectedTrCount = diaryTr.count;
		
	}
	function diaryProjectOnChangeHandler(diaryProject){
		
		DiaryProjectChangeUtil.diaryProjectChangeAjax(diaryProject);
	}
	function diaryPhaseOnChangeHandler(diaryPhase){
		
		DiaryPhaseChangeUtil.diaryPhaseChangeAjax(diaryPhase);
	}
	function diaryHourOnFocusHandler(diaryHour){
		
		diaryHour.originalHour = diaryHour.value;
	}
	function diaryHourOnFocusoutHandler(diaryHour, workHourText){
		
		if(!diaryHour.check()){
			
			alert("未輸入工時或輸入格式錯誤");
			diaryHour.value = diaryHour.originalHour;
			return;
		}
		DiaryHourFocusoutUtil.changeWorkHourText(workHourText, diaryHour.originalHour, diaryHour.value);
		diaryHour.value = DiaryHourFocusoutUtil.round(parseFloat(diaryHour.value));
	}
	
	
	function createBtnOnClickHandler(diaryBody, optionsJson, template, workHourText, deleteBtn, clearBtn, diaryTrStatusText){
		
		largestCount++;
		diaryBody.addTr(largestCount, optionsJson, template, workHourText, deleteBtn, clearBtn, diaryTrStatusText);
	}
	function deleteBtnOnClickHandler(diaryBody, workHourText, deleteBtn, clearBtn, diaryTrStatusText){
		
		var diaryTr = new DiaryTr(diaryBody.selectedTrCount, diaryBody);
		
		var status = diaryTr.status;
		var statusString;
		
		if(status == '1')
			statusString = trStatusVariables.status1String;
		else if(status == '2')
			statusString = trStatusVariables.status2String;
		else if(status == '3')
			statusString = trStatusVariables.status3String;
			
			
		var ans = confirm('確定要刪除嗎? 狀態為 : ' + statusString);
		
		if(ans){
			
			var deletedHour = diaryTr.hour.value;
			DiaryHourFocusoutUtil.changeWorkHourText(workHourText, deletedHour, '0');
			
			diaryTr.jquery.remove();
			
			diaryBody.selectedTrCount = '';
			deleteBtn.disable();
			clearBtn.disable();
			DiaryTrClickUtil.statusTextChange(diaryTrStatusText, '1');
		}
	}
	function clearBtnOnClickHandler(diaryBody, workHourText, optionsJson){
		
		var diaryTr = new DiaryTr(diaryBody.selectedTrCount, diaryBody);
		
		var status = diaryTr.status;
		var statusString;
		
		if(status == '1')
			statusString = trStatusVariables.status1String;
		else if(status == '2')
			statusString = trStatusVariables.status2String;
		else if(status == '3')
			statusString = trStatusVariables.status3String;
			
			
		var ans = confirm('確定要清除嗎? 狀態為 : ' + statusString);
		
		if(ans){
			
			diaryTr.project.toDefault();
			diaryTr.phase.toDefault();
			diaryTr.work.toDefault();
			diaryTr.text.value = '';
			DiaryHourFocusoutUtil.changeWorkHourText(workHourText, diaryTr.hour.value, '0')
			diaryTr.hour.value = '0';
			
			DiaryBodyAddTrUtil.diaryProjectAddOptionsVOs(JSON.parse(optionsJson), diaryTr.project);
		}
	}
	function resetBtnOnClickHandler(diaryBody, workHourText, deleteBtn, clearBtn, diaryTrStatusText){
		
		var ans = confirm('確定要重製嗎?');
		
		if(ans){
			
			diaryBody.jquery.empty();
			workHourText.text = '0';
			deleteBtn.disable();
			clearBtn.disable();
			DiaryTrClickUtil.statusTextChange(diaryTrStatusText, '1');
		}
	}
	function saveBtnOnClickHandler(diaryBody, dateText){
		
		if(confirm('確定要儲存嗎?')){
			
			if(!diaryBody.checkTrsWhenSend())
				return;
			SaveBtnClickUtil.sendSaveRequest(diaryBody.trs, dateText.text);
		}
		
	}
	function sendBtnOnClickHandler(diaryBody, dateText, ){
		
		if(confirm('確定要送出嗎?')){
			
			if(!diaryBody.checkTrsWhenSend())
				return;
				
			var workHourString = workHourText.text;
			if(parseFloat(workHourString) < leastHour){
				
				alert('工時總計未滿' + leastHour + '小時');
				return;
			}
			
			SendBtnClickUtil.sendSendRequest(diaryBody.trs, dateText.text);
		}
		
	}
	function calendarOnSelectHandler(dateText){
		
		var url = homePageUrl + '?date=' + dateText;
		location.assign(url);
	}