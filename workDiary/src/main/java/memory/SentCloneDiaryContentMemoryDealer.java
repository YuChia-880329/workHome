package memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bean.dto.DiaryContentDTO;

public class SentCloneDiaryContentMemoryDealer {

	private DiaryContentMemory memory = DiaryContentMemory.getInstance();
	private MemoryObjAndDTOTransformer transformer = MemoryObjAndDTOTransformer.getInstance();

	private static final SentCloneDiaryContentMemoryDealer INSTANCE = new SentCloneDiaryContentMemoryDealer();
	
	private SentCloneDiaryContentMemoryDealer() {
	}
	
	public static SentCloneDiaryContentMemoryDealer getInstance() {
		
		return INSTANCE;
	}
	
	
	public Map<String, List<DiaryContentDTO>> searchAll(){
		
		Map<String, Set<DiaryContentMemoryObj>> map =  memory.getAllSentCloneDiaryContentMemory();
		Map<String, List<DiaryContentDTO>> ans = new HashMap<>();
		
		for(String str : map.keySet()) {
			
			ans.put(str, transformer.memoryObjSetToDtoList(map.get(str)));
		}
		
		return ans;
	}
	public List<DiaryContentDTO> searchByDate(String date){
		
		Set<DiaryContentMemoryObj> set = memory.getSentCloneDiaryContentMemoryByDate(date);
		return transformer.memoryObjSetToDtoList(set);
	}
	public void add(String date, List<DiaryContentDTO> list) {
		
		memory.saveInSentCloneDiaryContentMemory(date, transformer.dtoListToMemoryObjSet(list));
	}
	public void update(String date, List<DiaryContentDTO> list) {
		
		memory.updateInSentCloneDiaryContentMemory(date, transformer.dtoListToMemoryObjSet(list));
	}
	public void delete(String date) {
		
		memory.deleteInSentCloneDiaryContentMemory(date);
	}
	public boolean contains(String date, DiaryContentDTO dto) {
		
		return memory.containsInSent(date, transformer.dtoToMemoryObj(dto));
	}
}
