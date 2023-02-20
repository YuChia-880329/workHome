package executor.transform;

import bean.dto.DiaryContentDTO;
import bean.vo.DiaryContentVO;
import enumeration.DiaryContentStatus;

public class DiaryContentVOToDiaryContentDTOExecutor {

	private static final DiaryContentVOToDiaryContentDTOExecutor INSTANCE = new DiaryContentVOToDiaryContentDTOExecutor();
	
	private DiaryContentVOToDiaryContentDTOExecutor() {
	}
	
	public static DiaryContentVOToDiaryContentDTOExecutor getInstance() {
		
		return INSTANCE;
	}
	
	public DiaryContentDTO transform(DiaryContentVO diaryContentVO, DiaryContentStatus status){
		
		DiaryContentDTO diaryContentDTO = new DiaryContentDTO();
		
		diaryContentDTO.setId(diaryContentVO.getCount());
		diaryContentDTO.setProjectId(diaryContentVO.getPhaseId());
		diaryContentDTO.setPhaseId(diaryContentVO.getPhaseId());
		diaryContentDTO.setWorkId(diaryContentVO.getWorkId());
		diaryContentDTO.setText(diaryContentVO.getText());
		diaryContentDTO.setWorkHour(diaryContentVO.getWorkHour());
		diaryContentDTO.setStatus(status);
		
		return diaryContentDTO;
	}
}
