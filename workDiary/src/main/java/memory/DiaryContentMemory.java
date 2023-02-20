package memory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import bean.dto.DiaryContentDTO;

class DiaryContentMemory {

	private Map<String, Set<DiaryContentMemoryObj>> savedDiaryContentMemory;
	private Map<String, Set<DiaryContentMemoryObj>> sentDiaryContentMemory;
	private Map<String, Set<DiaryContentMemoryObj>> sentDiaryContentMemoryClone;
	
	private static final DiaryContentMemory INSTANCE = new DiaryContentMemory();
	
	private DiaryContentMemory() {
		
		savedDiaryContentMemory = new HashMap<>();
		sentDiaryContentMemory = new HashMap<>();
		sentDiaryContentMemoryClone = new HashMap<>();
	}
	
	public static DiaryContentMemory getInstance() {
		
		return INSTANCE;
	}
	
	
	
	Map<String, Set<DiaryContentMemoryObj>> getAllSavedDiaryContentMemory(){
		
		return savedDiaryContentMemory;
	}
	Set<DiaryContentMemoryObj> getSavedDiaryContentMemoryByDate(String date){
		
		return savedDiaryContentMemory.get(date);
	}
	void saveInSavedDiaryContentMemory(String date, Set<DiaryContentMemoryObj> set) {
		
		Set<DiaryContentMemoryObj> objs = savedDiaryContentMemory.get(date);
		
		if(objs == null)
			objs = new HashSet<>();
		
		objs.addAll(set);
		savedDiaryContentMemory.put(date, set);
	}
	void updateInSavedDiaryContentMemory(String date, Set<DiaryContentMemoryObj> set) {
		
		savedDiaryContentMemory.put(date, set);
	}
	void deleteInSavedDiaryContentMemory(String date) {
		
		savedDiaryContentMemory.remove(date);
	}
	boolean containsInSaved(String date, DiaryContentMemoryObj obj) {
		
		return savedDiaryContentMemory.get(date).contains(obj);
	}
	
	
	Map<String, Set<DiaryContentMemoryObj>> getAllSentDiaryContentMemory(){
		
		return sentDiaryContentMemory;
	}
	Set<DiaryContentMemoryObj> getSentDiaryContentMemoryByDate(String date){
		
		return sentDiaryContentMemory.get(date);
	}
	void saveInSentDiaryContentMemory(String date, Set<DiaryContentMemoryObj> set) {
		
		Set<DiaryContentMemoryObj> objs = sentDiaryContentMemory.get(date);
		
		if(objs == null)
			objs = new HashSet<>();
		
		objs.addAll(set);
		sentDiaryContentMemory.put(date, set);
	}
	void updateInSentDiaryContentMemory(String date, Set<DiaryContentMemoryObj> set) {
		
		sentDiaryContentMemory.put(date, set);
	}
	void deleteInSentDiaryContentMemory(String date) {
		
		sentDiaryContentMemory.remove(date);
	}
	boolean containsInSent(String date, DiaryContentMemoryObj obj) {
		
		return sentDiaryContentMemory.get(date).contains(obj);
	}
	
	
	Map<String, Set<DiaryContentMemoryObj>> getAllSentCloneDiaryContentMemory(){
		
		return sentDiaryContentMemoryClone;
	}
	Set<DiaryContentMemoryObj> getSentCloneDiaryContentMemoryByDate(String date){
		
		return sentDiaryContentMemoryClone.get(date);
	}
	void saveInSentCloneDiaryContentMemory(String date, Set<DiaryContentMemoryObj> set) {
		
		Set<DiaryContentMemoryObj> objs = sentDiaryContentMemoryClone.get(date);
		
		if(objs == null)
			objs = new HashSet<>();
		
		objs.addAll(set);
		sentDiaryContentMemoryClone.put(date, set);
	}
	void updateInSentCloneDiaryContentMemory(String date, Set<DiaryContentMemoryObj> set) {
		
		sentDiaryContentMemoryClone.put(date, set);
	}
	void deleteInSentCloneDiaryContentMemory(String date) {
		
		sentDiaryContentMemoryClone.remove(date);
	}
	boolean containsInSentClone(String date, DiaryContentMemoryObj obj) {
		
		return sentDiaryContentMemoryClone.get(date).contains(obj);
	}
	

	
	void resetMemory(String date, boolean forSure) {
		
		if(forSure) {
			
			savedDiaryContentMemory.put(date, new HashSet<>());
			sentDiaryContentMemory.put(date, new HashSet<>()); 
			sentDiaryContentMemoryClone.put(date, new HashSet<>()); 
		}
	}
}

class DiaryContentMemoryObj{
	
	private DiaryContentDTO diaryContentDTO;
	
	DiaryContentMemoryObj(DiaryContentDTO diaryContentDTO) {
		this.diaryContentDTO = diaryContentDTO;
	}
	
	

	public DiaryContentDTO getDiaryContentDTO() {
		return diaryContentDTO;
	}
	
	
	public void setDiaryContentDTO(DiaryContentDTO diaryContentDTO) {
		this.diaryContentDTO = diaryContentDTO;
	}



	@Override
	public int hashCode() {
		return diaryContentDTO.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiaryContentMemoryObj other = (DiaryContentMemoryObj) obj;
		return other.diaryContentDTO.getId() == this.diaryContentDTO.getId();
	}
}

