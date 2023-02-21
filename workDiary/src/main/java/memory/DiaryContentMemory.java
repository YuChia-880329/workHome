package memory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import bean.dto.DiaryContentDTO;

class DiaryContentMemory {

	private Map<LocalDate, Set<DiaryContentMemoryObj>> savedDiaryContentMemory;
	private Map<LocalDate, Set<DiaryContentMemoryObj>> sentDiaryContentMemory;
	private Map<LocalDate, Set<DiaryContentMemoryObj>> sentDiaryContentMemoryClone;
	
	private static final DiaryContentMemory INSTANCE = new DiaryContentMemory();
	
	private DiaryContentMemory() {
		
		savedDiaryContentMemory = new HashMap<>();
		sentDiaryContentMemory = new HashMap<>();
		sentDiaryContentMemoryClone = new HashMap<>();
	}
	
	public static DiaryContentMemory getInstance() {
		
		return INSTANCE;
	}
	
	
	
	Map<LocalDate, Set<DiaryContentMemoryObj>> getAllSavedDiaryContentMemory(){
		
		return getAllDiaryContentMemory(savedDiaryContentMemory);
	}
	Set<DiaryContentMemoryObj> getSavedDiaryContentMemoryByDate(LocalDate date){
		
		return getDiaryContentMemoryByDate(date, savedDiaryContentMemory);
	}
	void saveInSavedDiaryContentMemory(LocalDate date, Set<DiaryContentMemoryObj> set) {
		
		saveDiaryContentMemory(date, set, savedDiaryContentMemory);
	}
	void updateInSavedDiaryContentMemory(LocalDate date, Set<DiaryContentMemoryObj> set) {
		
		updateDiaryContentMemory(date, set, savedDiaryContentMemory);
	}
	void deleteInSavedDiaryContentMemory(LocalDate date) {
		
		deleteDiaryContentMemory(date, savedDiaryContentMemory);
	}
	boolean containsInSaved(LocalDate date, DiaryContentMemoryObj obj) {
		
		return contains(date, obj, savedDiaryContentMemory);
	}
	
	
	Map<LocalDate, Set<DiaryContentMemoryObj>> getAllSentDiaryContentMemory(){
		
		return getAllDiaryContentMemory(sentDiaryContentMemory);
	}
	Set<DiaryContentMemoryObj> getSentDiaryContentMemoryByDate(LocalDate date){
		
		return getDiaryContentMemoryByDate(date, sentDiaryContentMemory);
	}
	void saveInSentDiaryContentMemory(LocalDate date, Set<DiaryContentMemoryObj> set) {
		
		saveDiaryContentMemory(date, set, sentDiaryContentMemory);
	}
	void updateInSentDiaryContentMemory(LocalDate date, Set<DiaryContentMemoryObj> set) {
		
		updateDiaryContentMemory(date, set, sentDiaryContentMemory);
	}
	void deleteInSentDiaryContentMemory(LocalDate date) {
		
		deleteDiaryContentMemory(date, sentDiaryContentMemory);
	}
	boolean containsInSent(LocalDate date, DiaryContentMemoryObj obj) {
		
		return contains(date, obj, sentDiaryContentMemory);
	}
	
	
	Map<LocalDate, Set<DiaryContentMemoryObj>> getAllSentCloneDiaryContentMemory(){
		
		return getAllDiaryContentMemory(sentDiaryContentMemoryClone);
	}
	Set<DiaryContentMemoryObj> getSentCloneDiaryContentMemoryByDate(LocalDate date){
		
		return getDiaryContentMemoryByDate(date, sentDiaryContentMemoryClone);
	}
	void saveInSentCloneDiaryContentMemory(LocalDate date, Set<DiaryContentMemoryObj> set) {
		
		saveDiaryContentMemory(date, set, sentDiaryContentMemoryClone);
	}
	void updateInSentCloneDiaryContentMemory(LocalDate date, Set<DiaryContentMemoryObj> set) {
		
		updateDiaryContentMemory(date, set, sentDiaryContentMemoryClone);
	}
	void deleteInSentCloneDiaryContentMemory(LocalDate date) {
		
		deleteDiaryContentMemory(date, sentDiaryContentMemoryClone);
	}
	boolean containsInSentClone(LocalDate date, DiaryContentMemoryObj obj) {
		
		return contains(date, obj, sentDiaryContentMemoryClone);
	}
	
	
	void resetMemory(LocalDate date, boolean forSure) {
		
		if(forSure) {
			
			savedDiaryContentMemory.put(date, new HashSet<>());
			sentDiaryContentMemory.put(date, new HashSet<>()); 
			sentDiaryContentMemoryClone.put(date, new HashSet<>()); 
		}
	}



	private Map<LocalDate, Set<DiaryContentMemoryObj>> getAllDiaryContentMemory(Map<LocalDate, Set<DiaryContentMemoryObj>> memory){
		
		return memory;
	}
	private Set<DiaryContentMemoryObj> getDiaryContentMemoryByDate(LocalDate date, Map<LocalDate, Set<DiaryContentMemoryObj>> memory){
		
		return memory.get(date);
	}
	private void saveDiaryContentMemory(LocalDate date, Set<DiaryContentMemoryObj> set, Map<LocalDate, Set<DiaryContentMemoryObj>> memory) {
		
		Set<DiaryContentMemoryObj> objs = memory.get(date);
		
		if(objs == null)
			objs = new HashSet<>();
		
		objs.addAll(set);
		memory.put(date, set);
	}
	private void updateDiaryContentMemory(LocalDate date, Set<DiaryContentMemoryObj> set, Map<LocalDate, Set<DiaryContentMemoryObj>> memory) {
		
		memory.put(date, set);
	}
	private void deleteDiaryContentMemory(LocalDate date, Map<LocalDate, Set<DiaryContentMemoryObj>> memory) {
		
		memory.remove(date);
	}
	private boolean contains(LocalDate date, DiaryContentMemoryObj obj, Map<LocalDate, Set<DiaryContentMemoryObj>> memory) {
		
		return memory.get(date).contains(obj);
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

