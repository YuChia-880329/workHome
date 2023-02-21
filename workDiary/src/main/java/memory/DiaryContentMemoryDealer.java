package memory;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import bean.dto.DiaryContentDTO;
import util.DateUtil;

public abstract class DiaryContentMemoryDealer {

	protected DiaryContentMemory memory = DiaryContentMemory.getInstance();
	private MemoryObjAndDTOTransformer transformer = MemoryObjAndDTOTransformer.getInstance();
	
	
	protected Map<Date, List<DiaryContentDTO>> searchAll(Supplier<Map<LocalDate, Set<DiaryContentMemoryObj>>> getAllSup){
		
		Map<LocalDate, Set<DiaryContentMemoryObj>> map =  getAllSup.get();
		Map<Date, List<DiaryContentDTO>> ans = new HashMap<>();
		
		for(LocalDate localDate : map.keySet()) {
			Set<DiaryContentMemoryObj> set = map.get(localDate);
			ans.put(DateUtil.localDateToDate(localDate), transformer.memoryObjSetToDtoList(set));
		}
		
		return ans;
	}
	protected List<DiaryContentDTO> searchByDate(Date date, Function<LocalDate, Set<DiaryContentMemoryObj>> getByDateFctn){
		
		LocalDate localDate = DateUtil.dateToLocalDate(date);
		Set<DiaryContentMemoryObj> set = getByDateFctn.apply(localDate);
		return transformer.memoryObjSetToDtoList(set);
	}
	protected void add(Date date, List<DiaryContentDTO> list, BiConsumer<LocalDate, Set<DiaryContentMemoryObj>> saveBiConsumer) {
		
		LocalDate localDate = DateUtil.dateToLocalDate(date);
		saveBiConsumer.accept(localDate, transformer.dtoListToMemoryObjSet(list));
	}
	protected void update(Date date, List<DiaryContentDTO> list, BiConsumer<LocalDate, Set<DiaryContentMemoryObj>> updateBiConsumer) {
		
		LocalDate localDate = DateUtil.dateToLocalDate(date);
		updateBiConsumer.accept(localDate, transformer.dtoListToMemoryObjSet(list));
	}
	protected void delete(Date date, Consumer<LocalDate> deleteConsumer) {
		
		deleteConsumer.accept(DateUtil.dateToLocalDate(date));
	}
	protected boolean contains(Date date, DiaryContentDTO dto, BiFunction<LocalDate, DiaryContentMemoryObj, Boolean> containBiFctn) {
		
		LocalDate localDate = DateUtil.dateToLocalDate(date);
		return containBiFctn.apply(localDate, transformer.dtoToMemoryObj(dto));
	}
}
