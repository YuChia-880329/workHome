package memory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import bean.dto.DiaryContentDTO;

class MemoryObjAndDTOTransformer {

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
	
	
	List<DiaryContentDTO> memoryObjSetToDtoList(Set<DiaryContentMemoryObj> objList) {
		
		return objList.stream().map(obj -> memoryObjToDto(obj)).collect(Collectors.toList());
	}
	
	Set<DiaryContentMemoryObj> dtoListToMemoryObjSet(List<DiaryContentDTO> dtoList) {
		
		return dtoList.stream().map(dto -> dtoToMemoryObj(dto)).collect(Collectors.toSet());
	}
}
