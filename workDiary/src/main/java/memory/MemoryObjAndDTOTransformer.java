package memory;

import java.util.ArrayList;
import java.util.HashSet;
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
		
		if(obj == null)
			return new DiaryContentDTO();
		return obj.getDiaryContentDTO();
	}
	
	DiaryContentMemoryObj dtoToMemoryObj(DiaryContentDTO dto) {
		
		return new DiaryContentMemoryObj(dto);
	}
	
	
	List<DiaryContentDTO> memoryObjSetToDtoList(Set<DiaryContentMemoryObj> objList) {
		
		if(objList == null)
			return new ArrayList<>();
		return objList.stream().map(obj -> memoryObjToDto(obj)).collect(Collectors.toList());
	}
	
	Set<DiaryContentMemoryObj> dtoListToMemoryObjSet(List<DiaryContentDTO> dtoList) {
		
		if(dtoList == null)
			return new HashSet<>();
		return dtoList.stream().map(dto -> dtoToMemoryObj(dto)).collect(Collectors.toSet());
	}
}
