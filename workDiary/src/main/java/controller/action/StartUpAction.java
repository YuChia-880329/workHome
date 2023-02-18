package controller.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bean.model.ProjectModel;
import bean.vo.ProjectVO;
import dao.ProjectModelDAO;

public class StartUpAction extends Action {

	
	private static final String REQ_ATTR_PROJECT_VOS = "projectVOs";
	
	private ProjectModelDAO projectModelDAO = ProjectModelDAO.getInstance();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<ProjectVO> projectVOs = new ArrayList<>();
		
		for(ProjectModel projectModel : projectModelDAO.searchAll()) {
			
			projectVOs.add(projectModelToProjectVO(projectModel));
		}
		request.setAttribute(REQ_ATTR_PROJECT_VOS, projectVOs);
		
		return mapping.findForward("homePage");
	}
	
	private ProjectVO projectModelToProjectVO(ProjectModel projectModel) {
		
		ProjectVO projectVO = new ProjectVO();
		projectVO.setProjectId(projectModel.getProjectId());
		projectVO.setProjectName(projectModel.getProjectName());
		return projectVO;
	}

}
