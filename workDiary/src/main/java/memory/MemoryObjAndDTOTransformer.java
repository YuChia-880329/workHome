package memory;

import bean.dto.DiaryContentDTO;

public class MemoryObjAndDTOTransformer {

	private static final MemoryObjAndDTOTransformer INSTANCE = new MemoryObjAndDTOTransformer();
	
	private MemoryObjAndDTOTransformer() {
		
	}
	
	static MemoryObjAndDTOTransformer getInstance() {
		
		return INSTANCE;
	}
	
	
	DiaryContentDTO memoryObjToDto(DiaryContentMemoryObj obj) {
		
		return obj.getDiaryContentDTO();
	}
	
	DiaryContentMemoryObj dtoToMemoryObj(DiaryContentDTO dto) {
		
		return new DiaryContentMemoryObj(dto);
	}
	
	
	DiaryContentDTO memoryObjToDto(DiaryContentMemoryObj obj) {
		
		return obj.getDiaryContentDTO();
	}
	
	DiaryContentMemoryObj dtoToMemoryObj(DiaryContentDTO dto) {
		
		return new DiaryContentMemoryObj(dto);
	}
}
