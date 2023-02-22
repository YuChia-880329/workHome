package executor.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bean.model.WorkModel;
import bean.vo.WorkVO;

public class WorkModelAndWorkVOTransformer {

	private static final WorkModelAndWorkVOTransformer INSTANCE = new WorkModelAndWorkVOTransformer();
	
	private WorkModelAndWorkVOTransformer() {
	}
	
	public static WorkModelAndWorkVOTransformer getInstance() {
		
		return INSTANCE;
	}
	
	public WorkVO modelToVO(WorkModel model) {
		
		WorkVO vo = new WorkVO();
		
		if(model == null)
			return vo;
		vo.setWorkId(model.getWorkId());
		vo.setWorkName(model.getWorkName());
		
		return vo;
	}
	
	public List<WorkVO> modelListToVOList(List<WorkModel> models){
		
		if(models == null)
			return new ArrayList<>();
		return models.stream().map(model -> modelToVO(model)).collect(Collectors.toList());
	}
}
