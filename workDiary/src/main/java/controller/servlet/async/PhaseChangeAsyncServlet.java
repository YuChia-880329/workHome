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

import bean.model.WorkModel;
import bean.vo.WorkVO;
import dao.WorkModelDAO;

@SuppressWarnings("serial")
public class PhaseChangeAsyncServlet extends HttpServlet {

	private static final String REQ_PARAM_DIARY_PHASE_ID = "diary_phase_id";
	
	private Gson gson = new GsonBuilder().create();
	
	private WorkModelDAO workModelDAO = WorkModelDAO.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String workIdStr = req.getParameter(REQ_PARAM_DIARY_PHASE_ID);
		int workId = Integer.parseInt(workIdStr);
		
		List<WorkVO> workVOs = new ArrayList<>();
		for(WorkModel workModel : workModelDAO.searchByPhaseId(workId)) {
			
			workVOs.add(workModelToWorkVO(workModel));
		}
		
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append(gson.toJson(workVOs));
	}
	
	private WorkVO workModelToWorkVO(WorkModel workModel) {
		
		WorkVO workVO = new WorkVO();
		workVO.setWorkId(workModel.getWorkId());
		workVO.setWorkName(workModel.getWorkName());
		return workVO;
	}	
}
