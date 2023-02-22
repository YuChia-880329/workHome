package executor.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import bean.model.PhaseModel;
import bean.vo.PhaseVO;
import dao.WorkModelDAO;

public class PhaseModelAndPhaseVOTransformer {

	private WorkModelAndWorkVOTransformer workTransformer = WorkModelAndWorkVOTransformer.getInstance();
	
	private WorkModelDAO workDAO = WorkModelDAO.getInstance();
	
	private static final PhaseModelAndPhaseVOTransformer INSTANCE = new PhaseModelAndPhaseVOTransformer();
	
	private PhaseModelAndPhaseVOTransformer() {
	}
	
	public static PhaseModelAndPhaseVOTransformer getInstance() {
		
		return INSTANCE;
	}
	
	public PhaseVO modelToVO(PhaseModel model) {
		
		PhaseVO vo = new PhaseVO();
		
		if(model == null)
			return vo;
		vo.setPhaseId(model.getPhaseId());
		vo.setPhaseName(model.getPhaseName());
		vo.setWorkVOs(workTransformer.modelListToVOList(workDAO.searchByPhaseId(model.getPhaseId())));
		return vo;
	}
	
	public List<PhaseVO> modelListToVOList(List<PhaseModel> models){
		
		if(models == null)
			return new ArrayList<>();
		return models.stream().map(model -> modelToVO(model)).collect(Collectors.toList());
	}
}
