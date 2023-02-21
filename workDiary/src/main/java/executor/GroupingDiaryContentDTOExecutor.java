package executor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import bean.dto.DiaryContentDTO;
import enumeration.DiaryContentStatus;

public class GroupingDiaryContentDTOExecutor {

	private static final GroupingDiaryContentDTOExecutor INSTANCE = new GroupingDiaryContentDTOExecutor();
	
	private GroupingDiaryContentDTOExecutor() {
	}
	
	public static GroupingDiaryContentDTOExecutor getInstance() {
		
		return INSTANCE;
	}
	
	public Map<Date, List<DiaryContentDTO>> group(List<DiaryContentDTO> dtos){
		
		Map<Date, List<DiaryContentDTO>> map = new HashMap<>();
		
		for(DiaryContentDTO dto : dtos) {
			
			List<DiaryContentDTO> list = map.get(dto.getDate());
			
			if(list == null)
				list = new ArrayList<>();
			
			list.add(dto);
			map.put(dto.getDate(), list);
		}
		
		return map;
	}
	
	public List<DiaryContentDTO> unGroup(Map<Date, List<DiaryContentDTO>> map){
		
		return map.keySet().stream()
				.flatMap(date -> map.get(date).stream())
				.collect(Collectors.toList());
	}
	
	public List<DiaryContentDTO>[] sortSameDateDTOs(List<DiaryContentDTO> dtos){
		
		@SuppressWarnings("unchecked")
		List<DiaryContentDTO>[] array = new List[3];
		
		List<DiaryContentDTO> status1List = new ArrayList<>();
		List<DiaryContentDTO> status2List = new ArrayList<>();
		List<DiaryContentDTO> status3List = new ArrayList<>();
		array[0] = status1List;
		array[1] = status2List;
		array[2] = status3List;
		
		for(DiaryContentDTO dto : dtos) {
			
			if(dto.getStatus() == DiaryContentStatus.NOT_SAVED) {
				
				array[0].add(dto);
			}else if(dto.getStatus() == DiaryContentStatus.SAVED) {
				
				array[1].add(dto);
			}else if(dto.getStatus() == DiaryContentStatus.SENT) {
				
				array[2].add(dto);
			}
		}
		return array;
	}
}
