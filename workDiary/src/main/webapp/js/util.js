
	class DiaryBodyAddTrUtil{
		
		static diaryProjectAddOptionsVOs(optionsVOs, diaryProject){
		
			optionsVOs.forEach(function(currentValue){
				
				var option = new DiarySelectObject.Option(currentValue.project_id, currentValue.project_name);
				diaryProject.addOption(option);
			});
		}
	}
	class DiaryTrClickUtil{
		
		static statusTextChange(statusText, status){
			
			if(status == '1'){
				
				statusText.setTextColor(trStatusVariables.status1Color);
				statusText.text = trStatusVariables.status1String;
			}else if(status == '2'){
				
				statusText.setTextColor(trStatusVariables.status2Color);
				statusText.text = trStatusVariables.status2String;
			}else if(status == '3'){
				
				statusText.setTextColor(trStatusVariables.status3Color);
				statusText.text = trStatusVariables.status3String;
			}
		}
	}
	class DiaryProjectChangeUtil{
		
		static diaryProjectChangeAjax(diaryProject){
	
			var ajaxUrl = projectChangeUrl;
			var ajaxData = {
				diary_project_id : diaryProject.value
			}
			
			$.ajax({
				url : ajaxUrl,
				method : 'GET',
				data : ajaxData,
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
				dataType : 'json',
				success : function(data){
					
					diaryProject.tr.phase.toDefault();
					diaryProject.tr.work.toDefault();
					DiaryProjectChangeUtil.diaryPhaseAddOptionsVOs(data, diaryProject.tr.phase);
				}
			});
		}
		
		static diaryPhaseAddOptionsVOs(optionsVOs, diaryPhase){
		
			optionsVOs.forEach(function(currentValue){
				
				var option = new DiarySelectObject.Option(currentValue.phase_id, currentValue.phase_name);
				diaryPhase.addOption(option);
			});
		}
	}
	class DiaryPhaseChangeUtil{
		
		static diaryPhaseChangeAjax(diaryPhase){
	
			var ajaxUrl = phaseChangeUrl;
			var ajaxData = {
				diary_phase_id : diaryPhase.value
			}
			
			$.ajax({
				url : ajaxUrl,
				method : 'GET',
				data : ajaxData,
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
				dataType : 'json',
				success : function(data){
					
					diaryPhase.tr.work.toDefault();
					DiaryPhaseChangeUtil.diaryWorkAddOptionsVOs(data, diaryPhase.tr.work);
				}
			});
		}
		static diaryWorkAddOptionsVOs(optionsVOs, diaryWork){
		
			optionsVOs.forEach(function(currentValue){
				
				var option = new DiarySelectObject.Option(currentValue.work_id, currentValue.work_name);
				diaryWork.addOption(option);
			});
		}
	}
	class DiaryHourFocusoutUtil{
		
		static changeWorkHourText(workHourText, originValueStr, curValueStr){
			
			var hourTextStr = workHourText.text;
			
			var hour = DiaryHourFocusoutUtil.str1MinusStr2(hourTextStr, originValueStr);
			var curValue = DiaryHourFocusoutUtil.round(parseFloat(curValueStr));
			hour = DiaryHourFocusoutUtil.round(hour+curValue);
			
			workHourText.text = hour;
		}
		static str1MinusStr2(str1, str2){
			
			var num1 = parseFloat(str1);
			var num2 = parseFloat(str2);
			
			var roundNum1 = DiaryHourFocusoutUtil.round(num1);
			var roundNum2 = DiaryHourFocusoutUtil.round(num2);
			return DiaryHourFocusoutUtil.round(roundNum1-roundNum2);
		}
		static round(num){
			
			return Math.round(num*10)/10;
		}
	}
	class SaveBtnClickUtil{
		
		static sendSaveRequest(trs, dateString){
			
			var datas = DiaryTr.trsToDiaryContentVos(trs, dateString);
			
			var dataValue = JSON.stringify(datas).replaceAll('"', '\"');
			
			var formId = 'send_form';
			var dataName = 'diary_contents';
			var form = '<form action="' + saveUrl + '" method="POST" id="' + formId + '">';
			form += '<input type="hidden" name="' + dataName + '" value=\'' + dataValue + '\' />';
			form += '</form>';
			
			$(document.body).append(form);
			$('#send_form').submit();
		}
	}
	class SendBtnClickUtil{
		
		static sendSendRequest(trs, dateString){
			
			var datas = DiaryTr.trsToDiaryContentVos(trs, dateString);
			
			var dataValue = JSON.stringify(datas).replaceAll('"', '\"');
			
			var formId = 'send_form';
			var dataName = 'diary_contents';
			var form = '<form action="' + sendUrl + '" method="POST" id="' + formId + '">';
			form += '<input type="hidden" name="' + dataName + '" value=\'' + dataValue + '\' />';
			form += '</form>';
			
			$(document.body).append(form);
			$('#send_form').submit();
		}
	}