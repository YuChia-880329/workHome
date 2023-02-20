package memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bean.dto.DiaryContentDTO;

public class SavedDiaryContentMemoryDealer {
	
	
	private DiaryContentMemory memory = DiaryContentMemory.getInstance();
	private MemoryObjAndDTOTransformer transformer = MemoryObjAndDTOTransformer.getInstance();

	private static final SavedDiaryContentMemoryDealer INSTANCE = new SavedDiaryContentMemoryDealer();
	
	private SavedDiaryContentMemoryDealer() {
	}
	
	public static SavedDiaryContentMemoryDealer getInstance() {
		
		return INSTANCE;
	}
	
	
	public Map<String, List<DiaryContentDTO>> searchAll(){
		
		Map<String, Set<DiaryContentMemoryObj>> map =  memory.getAllSavedDiaryContentMemory();
		Map<String, List<DiaryContentDTO>> ans = new HashMap<>();
		
		for(String str : map.keySet()) {
			
			ans.put(str, transformer.memoryObjSetToDtoList(map.get(str)));
		}
		
		return ans;
	}
	public List<DiaryContentDTO> searchByDate(String date){
		
		Set<DiaryContentMemoryObj> set = memory.getSavedDiaryContentMemoryByDate(date);
		return transformer.memoryObjSetToDtoList(set);
	}
	public void add(String date, List<DiaryContentDTO> list) {
		
		memory.saveInSavedDiaryContentMemory(date, transformer.dtoListToMemoryObjSet(list));
	}
	public void update(String date, List<DiaryContentDTO> list) {
		
		memory.updateInSavedDiaryContentMemory(date, transformer.dtoListToMemoryObjSet(list));
	}
	public void delete(String date) {
		
		memory.deleteInSavedDiaryContentMemory(date);
	}
	public boolean contains(String date, DiaryContentDTO dto) {
		
		return memory.containsInSaved(date, transformer.dtoToMemoryObj(dto));
	}
	
}
