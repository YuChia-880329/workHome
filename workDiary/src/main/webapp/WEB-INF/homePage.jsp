<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
		<link rel="stylesheet" href="css\jquery-ui.min.css"></link>
	
		<script src="js/jquery-3.6.3.min.js"></script>
		<script src="js/jquery-ui.min.js"></script>
		
		<script src="js/variables.js"></script>
		<script src="js/classDefine.js"></script>
		<script src="js/util.js"></script>
		<script src="js/initialization.js"></script>
		
		<style type="text/css">
			div.ui-datepicker {
				font-size: 10px;
			}
		</style>
		<script type="text/javascript">

			let diaryBody = new DiaryBody();

			let template = new Template();
			
			let createBtn = new Btn(createBtnId);
			let deleteBtn = new Btn(deleteBtnId);
			let clearBtn = new Btn(clearBtnId);
			let resetBtn = new Btn(resetBtnId);
			
			let saveBtn = new Btn(saveBtnId);
			let sendBtn = new Btn(sendBtnId);
			
			let calendar = new Calendar();
			let dateText = new DateText();
			let workHourText = new WorkHourText();
			let diaryTrStatusText = new DiaryTrStatusText();
			
			
			let projectVOsJson = '${projectVOsJson}';
			let diaryContentVOsJson = '${diaryContentVOsJson}';
			let currentDateJson = '${currentDate}';
			
			
			$(document).ready(function(){
				
				init();
				startUp();
			});
			
			
			function init(){
				
				diaryBodyInit(diaryBody);
				createBtnInit(createBtn, diaryBody, projectVOsJson, template, workHourText, deleteBtn, clearBtn, diaryTrStatusText);
				deleteBtnInit(deleteBtn, diaryBody, workHourText, deleteBtn, clearBtn, diaryTrStatusText);
				clearBtnInit(clearBtn, diaryBody, workHourText, projectVOsJson);
				resetBtnInit(resetBtn, diaryBody, workHourText, deleteBtn, clearBtn, diaryTrStatusText);
				saveBtnInit(saveBtn, diaryBody, dateText);
				sendBtnInit(sendBtn, diaryBody, dateText);
				calendarInit(calendar);
			}
			
			function startUp(){
				
				diaryBody.selectedTrCount = '';
				diaryBodyAddStartUpTr();
				calendarSetUp();
			}
			
			
			
			function diaryBodyAddStartUpTr(){
				
				var diaryContents = JSON.parse(diaryContentVOsJson);
				var projectOptions = JSON.parse(projectVOsJson);
				var hourTotal = 0;
				
				diaryContents.forEach(function(currentValue){
					
					var count = currentValue.count;
					diaryBody.addTr(count, projectVOsJson, template, workHourText, deleteBtn, clearBtn, diaryTrStatusText);
					var diaryTr = new DiaryTr(count);
					
					
					diaryTr.project.value = currentValue.projectId;
					
					var currentDiaryProject = getCurrentDiaryProject(projectOptions, diaryTr.project.value);
					DiaryProjectChangeUtil.diaryPhaseAddOptionsVOs(currentDiaryProject.phase_vos, diaryTr.phase);
					diaryTr.phase.value = currentValue.phaseId;
					
					var currentDiaryPhase = getCurrentDiaryPhase(currentDiaryProject.phase_vos, diaryTr.phase.value);
					DiaryPhaseChangeUtil.diaryWorkAddOptionsVOs(currentDiaryPhase.work_vos, diaryTr.work);
					diaryTr.work.value = currentValue.workId;
					diaryTr.text.value = currentValue.text;
					var hourValue = DiaryHourFocusoutUtil.round(parseFloat(currentValue.workHour));
					diaryTr.hour.value = hourValue;
					diaryTr.status = currentValue.status;
					
					hourTotal += hourValue;
					hourTotal = DiaryHourFocusoutUtil.round(hourTotal);
					
					if(largestCount < count)
						largestCount = count;
				});
				workHourText.text = hourTotal;
			}
			
			function getCurrentDiaryProject(projectOptions, currentProjectValue){
				
				for(var i=0; i<projectOptions.length; i++){
					
					if(projectOptions[i].project_id == currentProjectValue)
						return projectOptions[i];
				}
			}
			
			function getCurrentDiaryPhase(phasesOptions, currentPhaseValue){
				
				for(var i=0; i<phasesOptions.length; i++){
					
					if(phasesOptions[i].phase_id == currentPhaseValue)
						return phasesOptions[i];
				}
			}
			
			function calendarSetUp(){
				
				$.datepicker.setDefaults({
					'dateFormat' : Calendar.dateFormat
				});
				
				calendar.jquery.datepicker();
				calendar.jquery.datepicker('option', 'onSelect', calendar.onSelect);
				
				calendar.value = Calendar.parseDate(currentDateJson);
				dateText.text = currentDateJson;
			}
			
		</script>
	
		<title>????????????????????????</title>
	</head>
	
	<body>
	
		<p>
			<img border="0" src="#" width="1000" height="100">
		</p>
	
		<table border="0" width="850" height="100%">
			<tr>
				<td width="996" colspan="3">
					<p>
						<font size="4" color="#FF6600">
							<b>??????????????????</b>
						</font>
					</p>
					<table border="1" width="100%" border-color="#0000FF">
						<tr>
							<td width="118" bgcolor="#9999FF">?????????</td>
							<td width="72">????????????</td>
							<td width="88" bgcolor="#9999FF">???????????????</td>
							<td width="61">164</td>
							<td width="119" bgcolor="#9999FF">?????????</td>
							<td width="77">?????????</td>
							<td width="54" bgcolor="#9999FF">?????????</td>
							<td width="211">???????????????</td>
						</tr>
						<tr>
							<td width="118" bgcolor="#9999FF" height="23">?????????????????????</td>
							<td width="72" height="23">
								<div id="date_text_div"></div>
							</td>
							<td bgcolor="#9999FF" height="23">?????????</td>
							<td height="23">
								<span id="diary_tr_status_text" style="color: #FF0000">?????????</span>
							</td>
							<td bgcolor="#9999FF" height="23">?????????????????????</td>
							<td colspan="3" height="23">
								<span id="work_hour_text_span" style="margin-left: 5px;margin-right: 5px;">0</span>??????
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<div align="right">
									<input type="button" id="create_btn" value="??????" />
									<input type="button" id="delete_btn" value="??????" disabled />
									<input type="button" id="clear_btn" value="??????" disabled />
									<input type="button" id="reset_btn" value="??????" />
								</div>
							</td>
						</tr>
	
					</table>
	
				</td>
			</tr>
			<tr>
				<td width="100%" colspan="3">
					<hr noshade color="#FF0000">
				</td>
			</tr>
			<tr>
				<td width="200" valign="top">
					<div id="calendar_div"></div>
				</td>
				<td width="800" valign="top" colspan="2">
					<table border="1" width="100%" border-color="#0000FF">
						<thead>
							<tr height="28" bgcolor="#9999FF">
								<th width="15%">??????</th>
								<th width="15%">??????</th>
								<th width="21%">????????????</th>
								<th width="33%">????????????</th>
								<th width="7%">??????</th>
							</tr>
						</thead>
	
						<tbody id="diary_body">
						</tbody>
	
						<tfoot>
							<tr>
								<td colspan="6">
									<div align="center">
										<input type="button" id="save_btn" value="??????" />
										<input type="button" id="send_btn" value="??????" />
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</td>
			</tr>
		</table>
	
		
		<template id="diary_tmpl">
			<tr id="diary_tr_tmpl">
				<td>
					<select id="diary_project_tmpl">
						<option value="0" >?????????</option>
					</select>
				</td>
				<td>
					<select id="diary_phase_tmpl">
						<option value="0">?????????</option>
					</select>
				</td>
				<td>
					<select id="diary_work_tmpl">
						<option value="0">?????????</option>
					</select>
				</td>
				<td>
					<div align="center">
						<input type="text" id="diary_text_tmpl" size="27">
					</div>
				</td>
				<td>
					<input type="text" id="diary_hour_tmpl" size="1"  value="0">
				</td>
			</tr>
		</template>
	</body>
	
</html>