package executor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import bean.dto.DiaryContentDTO;
import bean.vo.DiaryContentVO;
import dao.DiaryContentModelDAO;
import enumeration.DiaryContentStatus;
import executor.transform.DiaryContentModelAndDiaryContentDTOTransformer;
import executor.transform.DiaryContentVOAndDiaryContentDTOTransformer;
import memory.SentCloneDiaryContentMemoryDealer;
import memory.SentDiaryContentMemoryDealer;

public class SendDiaryContentExecutor {

	private SentCloneDiaryContentMemoryDealer sentCloneMemoryDealer = SentCloneDiaryContentMemoryDealer.getInstance();
	private SentDiaryContentMemoryDealer sentMemoryDealer = SentDiaryContentMemoryDealer.getInstance();
	private DiaryContentModelDAO dao = DiaryContentModelDAO.getInstance();
	private DiaryContentVOAndDiaryContentDTOTransformer voDtoTransformer = DiaryContentVOAndDiaryContentDTOTransformer.getInstance();
	private DiaryContentModelAndDiaryContentDTOTransformer modelDtoTransformer = DiaryContentModelAndDiaryContentDTOTransformer.getInstance();
	private GroupingDiaryContentDTOExecutor groupingExecutor = GroupingDiaryContentDTOExecutor.getInstance();
	
	private static final SendDiaryContentExecutor INSTANCE = new SendDiaryContentExecutor();
	
	private SendDiaryContentExecutor() {
	}
	
	public static SendDiaryContentExecutor getInstance() {
		
		return INSTANCE;
	}
	
	public void send(List<DiaryContentVO> vos) {
		
		if(vos.size() > 0) {
			
			Map<Date, List<DiaryContentDTO>> map = 
					groupingExecutor.group(voDtoTransformer.voListToDtoList(vos));
			
			for(Date date : map.keySet()) {
				
				List<DiaryContentDTO>[] array = groupingExecutor.sortSameDateDTOs(map.get(date));
				
				
				array[0].stream().forEach(dto -> dto.setStatus(DiaryContentStatus.SENT));
				sentCloneMemoryDealer.update(date, array[0]);
				
				array[1].stream().forEach(dto -> dto.setStatus(DiaryContentStatus.SENT));
				sentCloneMemoryDealer.add(date, array[1]);
				
				sentCloneMemoryDealer.add(date, array[2]);
				

				List<DiaryContentDTO> originalSentList = sentMemoryDealer.searchByDate(date);
				
				for(DiaryContentDTO dto : originalSentList) {
					
					if(!sentCloneMemoryDealer.contains(date, dto)) {
						
						dao.delete(modelDtoTransformer.dtoToModel(dto));
					}
				}
				
				List<DiaryContentDTO> sentCloneList = sentCloneMemoryDealer.searchByDate(date);
				for(DiaryContentDTO dto : sentCloneList) {
					
					if(!sentMemoryDealer.contains(date, dto)) {
						
						dao.add(modelDtoTransformer.dtoToModel(dto));
					}
				}
				
				sentMemoryDealer.update(date, sentCloneList);
			}
		}
	}
}
