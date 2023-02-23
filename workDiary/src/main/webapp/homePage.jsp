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
	
		<title>工作日誌員工系統</title>
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
							<b>工作日誌維護</b>
						</font>
					</p>
					<table border="1" width="100%" border-color="#0000FF">
						<tr>
							<td width="118" bgcolor="#9999FF">部門：</td>
							<td width="72">開發中心</td>
							<td width="88" bgcolor="#9999FF">員工編號：</td>
							<td width="61">164</td>
							<td width="119" bgcolor="#9999FF">姓名：</td>
							<td width="77">李昱賞</td>
							<td width="54" bgcolor="#9999FF">職稱：</td>
							<td width="211">程式設計師</td>
						</tr>
						<tr>
							<td width="118" bgcolor="#9999FF" height="23">工作日誌日期：</td>
							<td width="72" height="23">
								<div id="date_text_div"></div>
							</td>
							<td bgcolor="#9999FF" height="23">狀態：</td>
							<td height="23">
								<span id="diary_tr_status_text" style="color: #FF0000">未送交</span>
							</td>
							<td bgcolor="#9999FF" height="23">本日工時總計：</td>
							<td colspan="3" height="23">
								<span id="work_hour_text_span" style="margin-left: 5px;margin-right: 5px;">0</span>小時
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<div align="right">
									<input type="button" id="create_btn" value="新增" />
									<input type="button" id="delete_btn" value="刪除" disabled />
									<input type="button" id="clear_btn" value="清除" disabled />
									<input type="button" id="reset_btn" value="重設" />
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
								<th width="15%">專案</th>
								<th width="15%">階段</th>
								<th width="21%">工作項目</th>
								<th width="33%">工作內容</th>
								<th width="7%">工時</th>
							</tr>
						</thead>
	
						<tbody id="diary_body">
						</tbody>
	
						<tfoot>
							<tr>
								<td colspan="6">
									<div align="center">
										<input type="button" id="save_btn" value="儲存" />
										<input type="button" id="send_btn" value="送交" />
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
						<option value="0" >請選擇</option>
					</select>
				</td>
				<td>
					<select id="diary_phase_tmpl">
						<option value="0">請選擇</option>
					</select>
				</td>
				<td>
					<select id="diary_work_tmpl">
						<option value="0">請選擇</option>
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