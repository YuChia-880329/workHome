package memory;

import java.util.Date;
import java.util.List;
import java.util.Map;

import bean.dto.DiaryContentDTO;

public class SavedDiaryContentMemoryDealer extends DiaryContentMemoryDealer {
	
	private static final SavedDiaryContentMemoryDealer INSTANCE = new SavedDiaryContentMemoryDealer();
	
	private SavedDiaryContentMemoryDealer() {
	}
	
	public static SavedDiaryContentMemoryDealer getInstance() {
		
		return INSTANCE;
	}
	
	
	public Map<Date, List<DiaryContentDTO>> searchAll(){
		
		return super.searchAll(memory::getAllSavedDiaryContentMemory);
	}
	public List<DiaryContentDTO> searchByDate(Date date){
		
		return super.searchByDate(date, memory::getSavedDiaryContentMemoryByDate);
	}
	public void add(Date date, List<DiaryContentDTO> list) {
		
		super.add(date, list, memory::saveInSavedDiaryContentMemory);
	}
	public void update(Date date, List<DiaryContentDTO> list) {
		
		super.update(date, list, memory::updateInSavedDiaryContentMemory);
	}
	public void delete(Date date) {
		
		super.delete(date, memory::deleteInSavedDiaryContentMemory);
	}
	public boolean contains(Date date, DiaryContentDTO dto) {
		
		return super.contains(date, dto, memory::containsInSaved);
	}
	
}
