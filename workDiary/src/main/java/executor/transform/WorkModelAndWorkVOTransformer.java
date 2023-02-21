package executor.transform;

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
		
		vo.setWorkId(model.getWorkId());
		vo.setWorkName(model.getWorkName());
		
		return vo;
	}
	
	public List<WorkVO> modelListToVOList(List<WorkModel> models){
		
		return models.stream().map(model -> modelToVO(model)).collect(Collectors.toList());
	}
}
