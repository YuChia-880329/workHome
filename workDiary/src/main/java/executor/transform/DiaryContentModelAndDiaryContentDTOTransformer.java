package executor.transform;

import java.util.List;
import java.util.stream.Collectors;

import bean.dto.DiaryContentDTO;
import bean.model.DiaryContentModel;
import enumeration.DiaryContentStatus;
import util.DateUtil;

public class DiaryContentModelAndDiaryContentDTOTransformer {

	private static final DiaryContentModelAndDiaryContentDTOTransformer INSTANCE = new DiaryContentModelAndDiaryContentDTOTransformer();
	
	private DiaryContentModelAndDiaryContentDTOTransformer() {
	}
	
	public static DiaryContentModelAndDiaryContentDTOTransformer getInstance() {
		
		return INSTANCE;
	}
	
	public DiaryContentModel dtoToModel(DiaryContentDTO dto) {
		
		DiaryContentModel model = new DiaryContentModel();
		
		model.setId(dto.getId());
		model.setDate(DateUtil.utilDateToSqlDate(dto.getDate()));
		model.setProjectId(dto.getProjectId());
		model.setPhaseId(dto.getPhaseId());
		model.setWorkId(dto.getWorkId());
		model.setText(dto.getText());
		model.setWorkHour(dto.getWorkHour());
		
		return model;
	}
	
	public DiaryContentDTO modelToDto(DiaryContentModel model) {
		
		DiaryContentDTO dto = new DiaryContentDTO();
		
		dto.setId(model.getId());
		dto.setDate(DateUtil.sqlDateToUtilDate(model.getDate()));
		dto.setProjectId(model.getProjectId());
		dto.setPhaseId(model.getPhaseId());
		dto.setWorkId(model.getWorkId());
		dto.setText(model.getText());
		dto.setWorkHour(model.getWorkHour());
		dto.setStatus(DiaryContentStatus.SENT);
		
		return dto;
	}
	
	public List<DiaryContentModel> dtoListToModelList(List<DiaryContentDTO> dtos){
		
		return dtos.stream().map(dto -> dtoToModel(dto)).collect(Collectors.toList());
	}
	
	public List<DiaryContentDTO> modelListToDtoList(List<DiaryContentModel> models){
		
		return models.stream().map(model -> modelToDto(model)).collect(Collectors.toList());
	}
}
