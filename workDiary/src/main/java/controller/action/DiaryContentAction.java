package controller.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.model.PhaseModel;
import bean.model.WorkModel;
import bean.vo.PhaseVO;
import bean.vo.WorkVO;
import dao.PhaseModelDAO;
import dao.WorkModelDAO;


public class DiaryContentAction extends DispatchAction {
	
	private static final String REQ_PARAM_DIARY_PROJECT_ID = "diary_project_id";
	private static final String REQ_PARAM_DIARY_PHASE_ID = "diary_phase_id";
	
	private Gson gson = new GsonBuilder().create();
	
	private PhaseModelDAO phaseModelDAO = PhaseModelDAO.getInstance();
	private WorkModelDAO workModelDAO = WorkModelDAO.getInstance();
	
	public ActionForward getPhases(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String projectIdStr = request.getParameter(REQ_PARAM_DIARY_PROJECT_ID);
		int projectId = Integer.parseInt(projectIdStr);
		
		List<PhaseVO> phaseVOs = new ArrayList<>();
		for(PhaseModel phaseModel : phaseModelDAO.searchByProjectId(projectId)) {
			
			phaseVOs.add(phaseModelToPhaseVO(phaseModel));
		}
		
		response.getWriter().append(gson.toJson(phaseVOs));
		
		return mapping.findForward("homePage");
	}
	public ActionForward getWorks(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String workIdStr = request.getParameter(REQ_PARAM_DIARY_PHASE_ID);
		int workId = Integer.parseInt(workIdStr);
		
		List<WorkVO> workVOs = new ArrayList<>();
		for(WorkModel workModel : workModelDAO.searchByPhaseId(workId)) {
			
			workVOs.add(workModelToWorkVO(workModel));
		}
		
		response.getWriter().append(gson.toJson(workVOs));
		
		return mapping.findForward("homePage");
	}
	
	private PhaseVO phaseModelToPhaseVO(PhaseModel phaseModel) {
		
		PhaseVO phaseVO = new PhaseVO();
		phaseVO.setPhaseId(phaseModel.getPhaseId());
		phaseVO.setPhaseName(phaseModel.getPhaseName());
		return phaseVO;
	}
	private WorkVO workModelToWorkVO(WorkModel workModel) {
		
		WorkVO workVO = new WorkVO();
		workVO.setWorkId(workModel.getWorkId());
		workVO.setWorkName(workModel.getWorkName());
		return workVO;
	}	
}
