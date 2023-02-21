package memory;

import java.util.Date;
import java.util.List;
import java.util.Map;

import bean.dto.DiaryContentDTO;

public class SentCloneDiaryContentMemoryDealer extends DiaryContentMemoryDealer {

	private static final SentCloneDiaryContentMemoryDealer INSTANCE = new SentCloneDiaryContentMemoryDealer();
	
	private SentCloneDiaryContentMemoryDealer() {
	}
	
	public static SentCloneDiaryContentMemoryDealer getInstance() {
		
		return INSTANCE;
	}
	
	
	public Map<Date, List<DiaryContentDTO>> searchAll(){
		
		return super.searchAll(memory::getAllSentCloneDiaryContentMemory);
	}
	public List<DiaryContentDTO> searchByDate(Date date){
		
		return super.searchByDate(date, memory::getSentCloneDiaryContentMemoryByDate);
	}
	public void add(Date date, List<DiaryContentDTO> list) {
		
		super.add(date, list, memory::saveInSentCloneDiaryContentMemory);
	}
	public void update(Date date, List<DiaryContentDTO> list) {
		
		super.update(date, list, memory::updateInSentCloneDiaryContentMemory);
	}
	public void delete(Date date) {
		
		super.delete(date, memory::deleteInSentCloneDiaryContentMemory);
	}
	public boolean contains(Date date, DiaryContentDTO dto) {
		
		return super.contains(date, dto, memory::containsInSentClone);
	}
}
