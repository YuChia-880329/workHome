package memory;

import java.util.Date;
import java.util.List;
import java.util.Map;

import bean.dto.DiaryContentDTO;

public class SentDiaryContentMemoryDealer extends DiaryContentMemoryDealer {

	private static final SentDiaryContentMemoryDealer INSTANCE = new SentDiaryContentMemoryDealer();
	
	private SentDiaryContentMemoryDealer() {
	}
	
	public static SentDiaryContentMemoryDealer getInstance() {
		
		return INSTANCE;
	}
	
	
	public Map<Date, List<DiaryContentDTO>> searchAll(){
		
		return super.searchAll(memory::getAllSentDiaryContentMemory);
	}
	public List<DiaryContentDTO> searchByDate(Date date){
		
		return super.searchByDate(date, memory::getSentDiaryContentMemoryByDate);
	}
	public void add(Date date, List<DiaryContentDTO> list) {
		
		super.add(date, list, memory::saveInSentDiaryContentMemory);
	}
	public void update(Date date, List<DiaryContentDTO> list) {
		
		super.update(date, list, memory::updateInSentDiaryContentMemory);
	}
	public void delete(Date date) {
		
		super.delete(date, memory::deleteInSentDiaryContentMemory);
	}
	public boolean contains(Date date, DiaryContentDTO dto) {
		
		return super.contains(date, dto, memory::containsInSent);
	}
}
