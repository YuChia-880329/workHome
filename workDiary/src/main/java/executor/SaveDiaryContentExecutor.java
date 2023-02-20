package executor;

import java.util.ArrayList;
import java.util.List;

import bean.vo.DiaryContentVO;
import executor.transform.DiaryContentVOAndDiaryContentDTOTransformer;
import memory.SavedDiaryContentMemoryDealer;
import memory.SentCloneDiaryContentMemoryDealer;

public class SaveDiaryContentExecutor {

	private DiaryContentVOAndDiaryContentDTOTransformer transformer = DiaryContentVOAndDiaryContentDTOTransformer.getInstance();
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
			
			String date = vos.get(0).getDate();
			List<DiaryContentVO>[] array = sort(vos);
			savedMemoryDealer.update(date, transformer.voListToDtoList(array[0]));
			savedMemoryDealer.add(date, transformer.voListToDtoList(array[1]));
			sentCloneMemoryDealer.update(date, transformer.voListToDtoList(array[2]));
		}
	}
	
	public List<DiaryContentVO>[] sort(List<DiaryContentVO> vos){
		
		@SuppressWarnings("unchecked")
		List<DiaryContentVO>[] array = new List[3];
		
		List<DiaryContentVO> status1List = new ArrayList<>();
		List<DiaryContentVO> status2List = new ArrayList<>();
		List<DiaryContentVO> status3List = new ArrayList<>();
		array[0] = status1List;
		array[1] = status2List;
		array[2] = status3List;
		
		for(DiaryContentVO vo : vos) {
			
			String status = vo.getStatus();
			array[Integer.parseInt(status)-1].add(vo);
		}
		return array;
	}
}
