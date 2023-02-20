<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
		<link rel="stylesheet" href="css\jquery-ui.min.css"></link>
	
		<script src="js\jquery-3.6.3.min.js"></script>
		<script src="js\jquery-ui.min.js"></script>
		
		<script src="js\diaryContentChange.js"></script>
		<script src="js\diaryToDefault.js"></script>
		<script src="js\diaryTmpl.js"></script>
		<script src="js\workDate.js"></script>
		<script src="js\hourChange.js"></script>
		<script src="js\selectDiary.js"></script>
		<script src="js\deleteClearAndReset.js"></script>
		<script src="js\saveDiary.js"></script>
	
		<style type="text/css">
			div.ui-datepicker {
				font-size: 10px;
			}
		</style>
		<script type="text/javascript">

			// id
			let diaryIdPrefixes = {
				'projectIdPrefix' : 'diary_project_',
				'phaseIdPrefix' : 'diary_phase_',
				'workIdPrefix' : 'diary_work_',
				'textIdPrefix' : 'diary_text_',
				'hourIdPrefix' : 'diary_hour_',
				'trIdPrefix' : 'diary_tr_'
			}

			let createBtnId = 'create_btn';
			let deleteBtnId = 'delete_btn';
			let clearBtnId = 'clear_btn';
			let resetBtnId = 'reset_btn';
			
			let saveBtnId = 'save_btn';
			
			let dateTextDivId = 'date_text_div';
			let workDateDivId = 'work_date_div';
			let workHourTextSpanId = 'work_hour_text_span';
			let selectedDiaryTrIdInputId = 'selected_diary_tr_id_input';

			let diaryTemplateId = 'diary_tmpl';
			let diaryBodyId = 'diary_body';
			let diaryTrStatusTextId = 'diary_tr_status_text';
			
			// data
			let trStatusDataName = 'trStatus';
			let diaryCountDataName = 'diaryCount';
			let seletedTrDataName = 'seletedTr';
			
			let trStatusMeaning = {
				'status1' : '未送交',
				'status2' : '已儲存',
				'status3' : '已送交'
			};
			
			let diaryCount = 1;
			
			$(document).ready(function () {

				initWorkDate(workDateDivId, dateTextDivId);
				$('#' + createBtnId).click(createBtnClicked);
				$('#' + diaryBodyId).data(seletedTrDataName, '');
				
				$('#' + deleteBtnId).click(deleteBtnClicked);
				$('#' + clearBtnId).click(clearBtnClicked);
				$('#' + resetBtnId).click(resetBtnClicked);
				
				$('#' + saveBtnId).click(saveBtnClicked);

			});

			function createBtnClicked(){

				var diaryContent = getDiaryContent(diaryTemplateId, diaryCount, diaryIdPrefixes, trStatusDataName, diaryCountDataName);
				$('#' + diaryBodyId).append(diaryContent);

				var diaryProjectId = diaryIdPrefixes.projectIdPrefix + diaryCount;
				var diaryPhaseId = diaryIdPrefixes.phaseIdPrefix + diaryCount;
				var diaryWorkId = diaryIdPrefixes.workIdPrefix + diaryCount;
				var diaryHourId = diaryIdPrefixes.hourIdPrefix + diaryCount;
				var diaryTrId = diaryIdPrefixes.trIdPrefix + diaryCount;

				$('#' + diaryTrId).data(trStatusDataName, '1');
				$('#' + diaryTrId).data(diaryCountDataName, diaryCount);
				$('#' + diaryProjectId).change(function(){
					diaryProjectChanged(diaryProjectId, diaryPhaseId, diaryWorkId);
				});
				$('#' + diaryPhaseId).change(function(){
					diaryPhaseChanged(diaryPhaseId, diaryWorkId);
				});
				diaryHourInputSetting(diaryHourId, workHourTextSpanId);
				selectDiaryTrSetting(diaryTrId, diaryBodyId, deleteBtnId, clearBtnId, seletedTrDataName);
				mouseenterChangeColor(diaryTrId);
				mouseleaveChangeColor(diaryTrId);
				
				diaryCount++;
			}
			
			function diaryProjectChanged(diaryProjectId, diaryPhaseId, diaryWorkId){

				diaryPhaseToDefault(diaryPhaseId);
				diaryWorkToDefault(diaryWorkId);

				var projectValue = $('#' + diaryProjectId).val();
				var dataJson = projectChangedAjax(projectValue, diaryPhaseId);
			}
			function diaryPhaseChanged(diaryPhaseId, diaryWorkId){

				diaryWorkToDefault(diaryWorkId);

				var phaseValue = $('#' + diaryPhaseId).val();
				var dataJson = phaseChangedAjax(phaseValue, diaryWorkId);
			}
			
			function deleteBtnClicked(){
				
				var selectedTrId = $('#' + diaryBodyId).data(seletedTrDataName);
				var selectedTrCount = $('#' + selectedTrId).data(diaryCountDataName);
				var hour = $('#' + diaryIdPrefixes.hourIdPrefix + selectedTrCount).val();
					
				deleteDiaryTr(selectedTrId, diaryBodyId, trStatusDataName, seletedTrDataName, 
						trStatusMeaning, deleteBtnId, clearBtnId, diaryTrStatusTextId,
						workHourTextSpanId, hour, str1MinusStr2);
			}
			function clearBtnClicked(){
				
				var selectedTrId = $('#' + diaryBodyId).data(seletedTrDataName);
				var selectedTrCount = $('#' + selectedTrId).data(diaryCountDataName);
				var hour = $('#' + diaryIdPrefixes.hourIdPrefix + selectedTrCount).val();
				
				clearDiaryTr(selectedTrId, diaryIdPrefixes, trStatusDataName, diaryCountDataName, trStatusMeaning, diaryTrStatusTextId,
						diaryProjectToDefault, diaryPhaseToDefault, diaryWorkToDefault, diaryTextToDefault, diaryHourToDefault, 
						workHourTextSpanId, hour, str1MinusStr2);
			}
			function resetBtnClicked(){
				
				resetDiary(diaryBodyId, diaryTrStatusTextId, workHourTextSpanId, trStatusMeaning, deleteBtnId, clearBtnId, diaryBodyId, seletedTrDataName);
			}
			
			function saveBtnClicked(){
				
				saveAjax(diaryBodyId, dateTextDivId, diaryCountDataName, diaryIdPrefixes, trStatusDataName);
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
					<div id="work_date_div"></div>
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
										<input type="button" value="送交" />
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
						
						<c:forEach var="projectVO" items="${projectVOs}">
							<option value="${projectVO.projectId }">${projectVO.projectName }</option>
						</c:forEach>
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
					<input type="text" id="diary_hour_tmpl" size="1"  value="0.0">
				</td>
			</tr>
		</template>
	</body>
	
</html>