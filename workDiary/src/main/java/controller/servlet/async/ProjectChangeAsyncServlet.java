package controller.servlet.async;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.model.PhaseModel;
import bean.vo.PhaseVO;
import dao.PhaseModelDAO;

@SuppressWarnings("serial")
public class ProjectChangeAsyncServlet extends HttpServlet {

	private static final String REQ_PARAM_DIARY_PROJECT_ID = "diary_project_id";
	
	private Gson gson = new GsonBuilder().create();
	
	private PhaseModelDAO phaseModelDAO = PhaseModelDAO.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String projectIdStr = req.getParameter(REQ_PARAM_DIARY_PROJECT_ID);
		int projectId = Integer.parseInt(projectIdStr);
		
		List<PhaseVO> phaseVOs = new ArrayList<>();
		for(PhaseModel phaseModel : phaseModelDAO.searchByProjectId(projectId)) {
			
			phaseVOs.add(phaseModelToPhaseVO(phaseModel));
		}
		
		String jsonStr = gson.toJson(phaseVOs);
		
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append(jsonStr);
	}
	
	
	private PhaseVO phaseModelToPhaseVO(PhaseModel phaseModel) {
		
		PhaseVO phaseVO = new PhaseVO();
		phaseVO.setPhaseId(phaseModel.getPhaseId());
		phaseVO.setPhaseName(phaseModel.getPhaseName());
		return phaseVO;
	}
}
