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
	
		<style type="text/css">
			div.ui-datepicker {
				font-size: 10px;
			}
		</style>
		<script type="text/javascript">
			$(document).ready(function () {
				$("#work_date").datepicker({
					dateFormat : 'yy/m/d',
					onSelect: function (dateText) {
						console.log(dateText);
						
					}
				});
			});
			
			function diaryProjectChanged(){
				
				var ajaxUrl = 'diaryContent.do?action=getPhases';
			
				$.ajax({
					url : ajaxUrl,
					data : 
				});
			}
			function appendDiaryPhase(diaryPhases){
				
				var optionTmpl = function(phaseId, phaseName){
					
					optionStr : '<option value="' + phaseId + '">' + phaseName + '</option>'
				}
				
				
				diaryPhases.foreach(function(currentValue){
					
					var option = new optionTmpl(currentValue.phase_id, currentValue.phase_name);
					$("#diary_phase").html(option);
				});
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
							<td width="72" height="23"></td>
							<td bgcolor="#9999FF" height="23">狀態：</td>
							<td height="23">
								<font color="#FF0000">
									<span>未送交</span>
								</font>
							</td>
							<td bgcolor="#9999FF" height="23">本日工時總計：</td>
							<td colspan="3" height="23"></td>
						</tr>
						<tr>
							<td colspan="8">
								<div align="right">
									<input type="button" value="新增" />
									<input type="button" value="刪除" />
									<input type="button" value="清除" />
									<input type="button" value="重設" />
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
					<div id="work_date"></div>
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
	
						<tbody id="detailList">
							<tr style="background-color: rgb(255, 255, 255);">
								<td>
									<select id="diary_project">
										<option value="0" >請選擇</option>
										
										<c:forEach var="projectVO" items="${projectVOs}">
											<option value="${projectVO.projectId }">${projectVO.projectName }</option>
										</c:forEach>
									</select>
								</td>
								<td>
									<select id="diary_phase">
										<option value="0">請選擇</option>
									</select>
								</td>
								<td>
									<select id="diary_work">
										<option value="0">請選擇</option>
									</select>
								</td>
								<td>
									<div align="center">
										<input type="text" size="27">
									</div>
								</td>
								<td>
									<input type="text" size="1" value="0.0">
								</td>
							</tr>
							
						</tbody>
	
						<tfoot>
							<tr>
								<td colspan="6">
									<div align="center">
										<input type="button" value="儲存" />
										<input type="button" value="送交" />
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
	
				</td>
			</tr>
		</table>
	
	</body>

</html>