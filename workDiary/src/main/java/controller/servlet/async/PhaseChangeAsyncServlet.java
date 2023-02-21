package controller.servlet.async;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.WorkModelDAO;
import executor.transform.WorkModelAndWorkVOTransformer;
import util.GsonUtil;

@SuppressWarnings("serial")
public class PhaseChangeAsyncServlet extends HttpServlet {

	// url
	public static final String URL = "phaseChange";
	
	
	// parameter
	private static final String REQ_PARAM_DIARY_PHASE_ID = "diary_phase_id";
	
	
	private WorkModelAndWorkVOTransformer transformer = WorkModelAndWorkVOTransformer.getInstance();
	
	private WorkModelDAO workModelDAO = WorkModelDAO.getInstance();
	
	
	private Gson gson = GsonUtil.getGson();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String phaseIdStr = req.getParameter(REQ_PARAM_DIARY_PHASE_ID);
		int phaseId = Integer.parseInt(phaseIdStr);
		
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append(gson.toJson(transformer.modelListToVOList(workModelDAO.searchByPhaseId(phaseId))));
	}	
}
