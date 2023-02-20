package executor.transform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.dto.DiaryContentDTO;
import bean.vo.DiaryContentVO;
import enumeration.DiaryContentStatus;

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
		dto.setProjectId(Integer.parseInt(vo.getPhaseId()));
		dto.setPhaseId(Integer.parseInt(vo.getPhaseId()));
		dto.setWorkId(Integer.parseInt(vo.getWorkId()));
		dto.setText(vo.getText());
		dto.setWorkHour(Double.parseDouble(vo.getWorkHour()));
		dto.setStatus(DiaryContentStatus.getStatus(vo.getStatus()));
		
		return dto;
	}
	
	public DiaryContentVO dtoToVo(String date, DiaryContentDTO dto) {
		
		DiaryContentVO vo = new DiaryContentVO();
		vo.setCount(String.valueOf(dto.getId()));
		vo.setDate(date);
		vo.setProjectId(String.valueOf(dto.getProjectId()));
		vo.setPhaseId(String.valueOf(dto.getPhaseId()));
		vo.setWorkId(String.valueOf(dto.getWorkId()));
		vo.setText(String.valueOf(dto.getText()));
		vo.setWorkHour(String.valueOf(dto.getWorkHour()));
		vo.setStatus(dto.getStatus().getCode());
		
		return vo;
	}
	
	
	public List<DiaryContentDTO> voListToDtoList(List<DiaryContentVO> vos) {
		
		List<DiaryContentDTO> list = new ArrayList<>();
		
		for(DiaryContentVO vo : vos) {
			
			list.add(voToDto(vo));
		}
		
		return list;
	}
	
	public Map<String, List<DiaryContentDTO>> voListToDtoMap(List<DiaryContentVO> vos) {
		
		Map<String, List<DiaryContentDTO>> map = new HashMap<>();
		
		
		for(DiaryContentVO vo : vos) {
			
			String date = vo.getDate();
			List<DiaryContentDTO> list = map.get(date);
			
			if(list == null)
				list = new ArrayList<>();
			
			list.add(voToDto(vo));
			map.put(date, list);
		}
		
		return map;
	}
	
	public List<DiaryContentVO> dtoMapToVoList(Map<String, List<DiaryContentDTO>> dtoMap) {
		
		List<DiaryContentVO> list = new ArrayList<>();
		
		for(String date : dtoMap.keySet()) {
			for(DiaryContentDTO dto : dtoMap.get(date)) {
				
				list.add(dtoToVo(date, dto));
			}
		}
		
		return list;
	}
	
	public List<DiaryContentVO> dtoListToVoList(String date, List<DiaryContentDTO> dtos) {
		
		List<DiaryContentVO> list = new ArrayList<>();
		
		for(DiaryContentDTO dto : dtos) {
			
			list.add(dtoToVo(date, dto));
		}
		
		return list;
	}
}
