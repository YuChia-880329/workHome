package executor;

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
	private GroupingDiaryContentDTOExecutor gorupingExecutor = GroupingDiaryContentDTOExecutor.getInstance();
	
	private static final HomeDiaryContentPrepareExecutor INSTANCE = new HomeDiaryContentPrepareExecutor();
	
	private HomeDiaryContentPrepareExecutor() {

	}
	
	public static HomeDiaryContentPrepareExecutor getInstance() {
		
		return INSTANCE;
	}
	
	
	public List<DiaryContentVO> getDiaryVOsFromMemory(){
		
		List<DiaryContentDTO> list = gorupingExecutor.unGroup(savedMemoryDealer.searchAll());
		list.addAll(gorupingExecutor.unGroup(cloneMemoryDealer.searchAll()));
		return transformer.dtoListToVoList(list);
	}
}
