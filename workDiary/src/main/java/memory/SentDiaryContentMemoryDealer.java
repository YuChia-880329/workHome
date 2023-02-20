package memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bean.dto.DiaryContentDTO;

public class SentDiaryContentMemoryDealer {

	
	private DiaryContentMemory memory = DiaryContentMemory.getInstance();
	private MemoryObjAndDTOTransformer transformer = MemoryObjAndDTOTransformer.getInstance();

	private static final SentDiaryContentMemoryDealer INSTANCE = new SentDiaryContentMemoryDealer();
	
	private SentDiaryContentMemoryDealer() {
	}
	
	public static SentDiaryContentMemoryDealer getInstance() {
		
		return INSTANCE;
	}
	
	
	public Map<String, List<DiaryContentDTO>> searchAll(){
		
		Map<String, Set<DiaryContentMemoryObj>> map =  memory.getAllSentDiaryContentMemory();
		Map<String, List<DiaryContentDTO>> ans = new HashMap<>();
		
		for(String str : map.keySet()) {
			
			ans.put(str, transformer.memoryObjSetToDtoList(map.get(str)));
		}
		
		return ans;
	}
	public List<DiaryContentDTO> searchByDate(String date){
		
		Set<DiaryContentMemoryObj> set = memory.getSentDiaryContentMemoryByDate(date);
		return transformer.memoryObjSetToDtoList(set);
	}
	public void add(String date, List<DiaryContentDTO> list) {
		
		memory.saveInSentDiaryContentMemory(date, transformer.dtoListToMemoryObjSet(list));
	}
	public void update(String date, List<DiaryContentDTO> list) {
		
		memory.updateInSentDiaryContentMemory(date, transformer.dtoListToMemoryObjSet(list));
	}
	public void delete(String date) {
		
		memory.deleteInSentDiaryContentMemory(date);
	}
	public boolean contains(String date, DiaryContentDTO dto) {
		
		return memory.containsInSentClone(date, transformer.dtoToMemoryObj(dto));
	}
}
