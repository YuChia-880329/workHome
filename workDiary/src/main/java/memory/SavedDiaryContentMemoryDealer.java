package memory;

import java.util.List;

import bean.dto.DiaryContentDTO;

public class SavedDiaryContentMemoryDealer {
	
	
	private DiaryContentMemory memory = DiaryContentMemory.getInstance();

	private static final SavedDiaryContentMemoryDealer INSTANCE = new SavedDiaryContentMemoryDealer();
	
	private SavedDiaryContentMemoryDealer() {
	}
	
	public static SavedDiaryContentMemoryDealer getInstance() {
		
		return INSTANCE;
	}
	
	
	public List<DiaryContentDTO> searchAll(){
		
		return memory.getAllDiaryContentMemory();
	}
	public List<DiaryContentDTO> searchByDate(String date){
		
		return memory.getDiaryContentMemoryByDate(date);
	}
	public void add(String date, List<DiaryContentDTO> list) {
		
		memory.saveDiaryContentMemory(date, list);
	}
	public void update(String date, List<DiaryContentDTO> list) {
		
		memory.saveDiaryContentMemory(date, list);
	}
	public void delete(String date) {
		
		memory.deleteDiaryContentMemory(date);
	}
	
}
