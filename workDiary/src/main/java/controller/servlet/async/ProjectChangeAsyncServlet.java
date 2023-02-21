package controller.servlet.async;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.PhaseModelDAO;
import executor.transform.PhaseModelAndPhaseVOTransformer;
import util.GsonUtil;

@SuppressWarnings("serial")
public class ProjectChangeAsyncServlet extends HttpServlet {

	// url
	public static final String URL = "projChange";
	
	
	// parameter
	private static final String REQ_PARAM_DIARY_PROJECT_ID = "diary_project_id";
	
	
	private PhaseModelAndPhaseVOTransformer transformer = PhaseModelAndPhaseVOTransformer.getInstance();
	
	private PhaseModelDAO phaseModelDAO = PhaseModelDAO.getInstance();
	
	
	private Gson gson = GsonUtil.getGson();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String projectIdStr = req.getParameter(REQ_PARAM_DIARY_PROJECT_ID);
		int projectId = Integer.parseInt(projectIdStr);
		
		
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append(gson.toJson(transformer.modelListToVOList(phaseModelDAO.searchByProjectId(projectId))));
	}
	
}
