package memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import bean.dto.DiaryContentDTO;

public class DiaryContentMemory {

	private Map<String, Set<DiaryContentMemoryObj>> diaryContentMemory;
	
	private static final DiaryContentMemory INSTANCE = new DiaryContentMemory();
	
	private DiaryContentMemory() {
		
		diaryContentMemory = new HashMap<>();
	}
	
	public static DiaryContentMemory getInstance() {
		
		return INSTANCE;
	}
	
	List<DiaryContentDTO> getAllDiaryContentMemory(){
		
		List<DiaryContentDTO> diaryContentDTOs = new ArrayList<>();
		
		for(String date : diaryContentMemory.keySet()) {
			
			Set<DiaryContentMemoryObj> set = diaryContentMemory.get(date);
			
			for(DiaryContentMemoryObj obj : set) {
				
				diaryContentDTOs.add(obj.getDiaryContentDTO());
			}
		}
		return diaryContentDTOs;
	}
	List<DiaryContentDTO> getDiaryContentMemoryByDate(String date){
		
		List<DiaryContentDTO> list = new ArrayList<>();
		Set<DiaryContentMemoryObj> set = diaryContentMemory.get(date);
		
		for(DiaryContentMemoryObj obj : set) {
			
			list.add(obj.getDiaryContentDTO());
		}
		return list;
	}
	void saveDiaryContentMemory(String date, List<DiaryContentDTO> diaryContentDTOs) {
		
		Set<DiaryContentMemoryObj> set = diaryContentMemory.get(date);
		
		for(DiaryContentDTO dto : diaryContentDTOs) {
			
			if(set == null)
				set = new HashSet<>();
			set.add(new DiaryContentMemoryObj(dto));
		}
		diaryContentMemory.put(date, set);
	}
	void deleteDiaryContentMemory(String date) {
		
		diaryContentMemory.remove(date);
	}
	
	private static class DiaryContentMemoryObj{
		
		private DiaryContentDTO diaryContentDTO;
		
		private DiaryContentMemoryObj(DiaryContentDTO diaryContentDTO) {
			this.diaryContentDTO = diaryContentDTO;
		}
		
		private DiaryContentDTO getDiaryContentDTO() {
			return diaryContentDTO;
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
}

