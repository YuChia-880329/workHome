package executor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import bean.dto.DiaryContentDTO;
import bean.vo.DiaryContentVO;
import enumeration.DiaryContentStatus;
import executor.transform.DiaryContentVOAndDiaryContentDTOTransformer;
import memory.SavedDiaryContentMemoryDealer;
import memory.SentCloneDiaryContentMemoryDealer;

public class SaveDiaryContentExecutor {

	private DiaryContentVOAndDiaryContentDTOTransformer transformer = DiaryContentVOAndDiaryContentDTOTransformer.getInstance();
	private GroupingDiaryContentDTOExecutor groupingExecutor = GroupingDiaryContentDTOExecutor.getInstance();
	private SavedDiaryContentMemoryDealer savedMemoryDealer = SavedDiaryContentMemoryDealer.getInstance();
	private SentCloneDiaryContentMemoryDealer sentCloneMemoryDealer = SentCloneDiaryContentMemoryDealer.getInstance();
	
	private static final SaveDiaryContentExecutor INSTANCE = new SaveDiaryContentExecutor();
	
	private SaveDiaryContentExecutor() {
	}
	
	public static SaveDiaryContentExecutor getInstance() {
		
		return INSTANCE;
	}
	
	public void save(List<DiaryContentVO> vos) {
		
		if(vos.size() > 0) {
			
			Map<Date, List<DiaryContentDTO>> map = 
					groupingExecutor.group(transformer.voListToDtoList(vos));
			
			for(Date date : map.keySet()) {
				
				List<DiaryContentDTO>[] array = groupingExecutor.sortSameDateDTOs(map.get(date));
				
				if(array[0].size() > 0) {
					
					array[0].stream().forEach(dto -> dto.setStatus(DiaryContentStatus.SAVED));
					savedMemoryDealer.update(date, array[0]);
				}else if(array[1].size() > 0) {
					
					savedMemoryDealer.add(date, array[1]);
				}else if(array[2].size() > 0) {
					
					sentCloneMemoryDealer.update(date, array[2]);
				}
			}
		}
	}
}
