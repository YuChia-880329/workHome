package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.model.ProjectModel;
import bean.vo.ProjectVO;
import dao.ProjectModelDAO;

@SuppressWarnings("serial")
public class StartUpServlet extends HttpServlet {

	private static final String REQ_ATTR_PROJECT_VOS = "projectVOs";
	
	private static final String FORWARD_URL = "homePage.jsp";
	
	private ProjectModelDAO projectModelDAO = ProjectModelDAO.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<ProjectVO> projectVOs = new ArrayList<>();
		
		for(ProjectModel projectModel : projectModelDAO.searchAll()) {
			
			projectVOs.add(projectModelToProjectVO(projectModel));
		}
		req.setAttribute(REQ_ATTR_PROJECT_VOS, projectVOs);
		
		req.getRequestDispatcher(FORWARD_URL).forward(req, resp);
	}
	
	private ProjectVO projectModelToProjectVO(ProjectModel projectModel) {
		
		ProjectVO projectVO = new ProjectVO();
		projectVO.setProjectId(projectModel.getProjectId());
		projectVO.setProjectName(projectModel.getProjectName());
		return projectVO;
	}
}
