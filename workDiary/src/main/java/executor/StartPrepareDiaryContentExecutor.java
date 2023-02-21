package executor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import bean.dto.DiaryContentDTO;
import bean.model.DiaryContentModel;
import dao.DiaryContentModelDAO;
import executor.transform.DiaryContentModelAndDiaryContentDTOTransformer;
import memory.SentCloneDiaryContentMemoryDealer;
import memory.SentDiaryContentMemoryDealer;

public class StartPrepareDiaryContentExecutor {

	private DiaryContentModelDAO diaryContentModelDAO = DiaryContentModelDAO.getInstance();
	private DiaryContentModelAndDiaryContentDTOTransformer transformer = DiaryContentModelAndDiaryContentDTOTransformer.getInstance();
	private GroupingDiaryContentDTOExecutor groupingExecutor = GroupingDiaryContentDTOExecutor.getInstance();
	private SentDiaryContentMemoryDealer memoryDealer = SentDiaryContentMemoryDealer.getInstance();
	private SentCloneDiaryContentMemoryDealer cloneMemoryDealer = SentCloneDiaryContentMemoryDealer.getInstance();
	
	private static final StartPrepareDiaryContentExecutor INSTANCE = new StartPrepareDiaryContentExecutor();
	
	private StartPrepareDiaryContentExecutor() {

	}
	
	public static StartPrepareDiaryContentExecutor getInstance() {
		
		return INSTANCE;
	}
	
	
	public void prepare() {
		
		List<DiaryContentModel> models = diaryContentModelDAO.searchAll();
		List<DiaryContentDTO> dtos = transformer.modelListToDtoList(models);
		
		Map<Date, List<DiaryContentDTO>> map = groupingExecutor.group(dtos);
		
		for(Date date : map.keySet()) {

			memoryDealer.add(date, map.get(date));
			cloneMemoryDealer.add(date, map.get(date));
		}
	}
}
