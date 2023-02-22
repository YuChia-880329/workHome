package executor;

import java.util.Date;
import java.util.List;

import bean.dto.DiaryContentDTO;
import bean.vo.DiaryContentVO;
import executor.transform.DiaryContentVOAndDiaryContentDTOTransformer;
import memory.SavedDiaryContentMemoryDealer;
import memory.SentCloneDiaryContentMemoryDealer;

public class HomeDiaryContentPrepareExecutor {

	private SavedDiaryContentMemoryDealer savedMemoryDealer = SavedDiaryContentMemoryDealer.getInstance();
	private SentCloneDiaryContentMemoryDealer cloneMemoryDealer = SentCloneDiaryContentMemoryDealer.getInstance();
	private DiaryContentVOAndDiaryContentDTOTransformer transformer = DiaryContentVOAndDiaryContentDTOTransformer.getInstance();
	
	private static final HomeDiaryContentPrepareExecutor INSTANCE = new HomeDiaryContentPrepareExecutor();
	
	private HomeDiaryContentPrepareExecutor() {

	}
	
	public static HomeDiaryContentPrepareExecutor getInstance() {
		
		return INSTANCE;
	}
	
	
	public List<DiaryContentVO> getDiaryVOsFromMemory(Date date){
		
		List<DiaryContentDTO> list = savedMemoryDealer.searchByDate(date);
		list.addAll(cloneMemoryDealer.searchByDate(date));
		return transformer.dtoListToVoList(list);
	}
}
