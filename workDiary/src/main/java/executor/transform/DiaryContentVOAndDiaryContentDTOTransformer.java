package executor.transform;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import bean.dto.DiaryContentDTO;
import bean.vo.DiaryContentVO;
import enumeration.DiaryContentStatus;
import util.DateUtil;

public class DiaryContentVOAndDiaryContentDTOTransformer {

	private static final DiaryContentVOAndDiaryContentDTOTransformer INSTANCE = new DiaryContentVOAndDiaryContentDTOTransformer();
	
	private DiaryContentVOAndDiaryContentDTOTransformer() {
	}
	
	public static DiaryContentVOAndDiaryContentDTOTransformer getInstance() {
		
		return INSTANCE;
	}
	
	public DiaryContentDTO voToDto(DiaryContentVO vo){
		
		DiaryContentDTO dto = new DiaryContentDTO();
		
		dto.setId(Integer.parseInt(vo.getCount()));
		try {
			
			dto.setDate(DateUtil.stringToDate(vo.getDate()));
		} catch (ParseException ex) {
			
			System.out.println(ex.getMessage());
			dto.setDate(new Date());
		}
		dto.setProjectId(Integer.parseInt(vo.getPhaseId()));
		dto.setPhaseId(Integer.parseInt(vo.getPhaseId()));
		dto.setWorkId(Integer.parseInt(vo.getWorkId()));
		dto.setText(vo.getText());
		dto.setWorkHour(Double.parseDouble(vo.getWorkHour()));
		dto.setStatus(DiaryContentStatus.getStatus(vo.getStatus()));
		
		return dto;
	}
	
	public DiaryContentVO dtoToVo(DiaryContentDTO dto) {
		
		DiaryContentVO vo = new DiaryContentVO();
		vo.setCount(String.valueOf(dto.getId()));
		vo.setDate(DateUtil.dateToString(dto.getDate()));
		vo.setProjectId(String.valueOf(dto.getProjectId()));
		vo.setPhaseId(String.valueOf(dto.getPhaseId()));
		vo.setWorkId(String.valueOf(dto.getWorkId()));
		vo.setText(String.valueOf(dto.getText()));
		vo.setWorkHour(String.valueOf(dto.getWorkHour()));
		vo.setStatus(dto.getStatus().getCode());
		
		return vo;
	}
	
	
	public List<DiaryContentDTO> voListToDtoList(List<DiaryContentVO> vos) {
		
		return vos.stream().map(vo -> voToDto(vo)).collect(Collectors.toList());
	}
	

	public List<DiaryContentVO> dtoListToVoList(List<DiaryContentDTO> dtos) {
		
		return dtos.stream().map(dto -> dtoToVo(dto)).collect(Collectors.toList());
	}
}
