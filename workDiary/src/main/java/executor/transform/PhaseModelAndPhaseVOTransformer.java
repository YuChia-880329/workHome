package executor.transform;

import java.util.List;
import java.util.stream.Collectors;

import bean.model.PhaseModel;
import bean.vo.PhaseVO;

public class PhaseModelAndPhaseVOTransformer {

	private static final PhaseModelAndPhaseVOTransformer INSTANCE = new PhaseModelAndPhaseVOTransformer();
	
	private PhaseModelAndPhaseVOTransformer() {
	}
	
	public static PhaseModelAndPhaseVOTransformer getInstance() {
		
		return INSTANCE;
	}
	
	public PhaseVO modelToVO(PhaseModel model) {
		
		PhaseVO vo = new PhaseVO();
		
		vo.setPhaseId(model.getPhaseId());
		vo.setPhaseName(model.getPhaseName());
		
		return vo;
	}
	
	public List<PhaseVO> modelListToVOList(List<PhaseModel> models){
		
		return models.stream().map(model -> modelToVO(model)).collect(Collectors.toList());
	}
}
