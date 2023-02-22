package executor.transform;

import java.util.List;
import java.util.stream.Collectors;

import bean.model.ProjectModel;
import bean.vo.ProjectVO;
import dao.PhaseModelDAO;

public class ProjectModelAndProjectVOTransformer {

	private PhaseModelAndPhaseVOTransformer phaseTransformer = PhaseModelAndPhaseVOTransformer.getInstance();
	
	private PhaseModelDAO phaseDAO = PhaseModelDAO.getInstance();
	
	private static final ProjectModelAndProjectVOTransformer INSTANCE = new ProjectModelAndProjectVOTransformer();
	
	private ProjectModelAndProjectVOTransformer() {
	}
	
	public static ProjectModelAndProjectVOTransformer getInstance() {
		
		return INSTANCE;
	}
	
	public ProjectVO modelToVO(ProjectModel model) {
		
		ProjectVO vo = new ProjectVO();
		
		vo.setProjectId(model.getProjectId());
		vo.setProjectName(model.getProjectName());
		vo.setPhaseVOs(phaseTransformer.modelListToVOList(phaseDAO.searchByProjectId(model.getProjectId())));
		
		return vo;
	}
	
	public List<ProjectVO> modelListToVOList(List<ProjectModel> models){
		
		return models.stream().map(model -> modelToVO(model)).collect(Collectors.toList());
	}
}
