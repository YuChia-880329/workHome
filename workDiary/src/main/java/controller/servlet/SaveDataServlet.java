package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.dto.DiaryContentDTO;
import bean.vo.DiaryContentVO;
import enumeration.DiaryContentStatus;
import executor.transform.DiaryContentVOToDiaryContentDTOExecutor;
import memory.SavedDiaryContentMemoryDealer;

@SuppressWarnings("serial")
public class SaveDataServlet extends HttpServlet {

	private static final String REQ_PARAM_DIARY_TR_COUNTS = "diary_tr_counts";
	
	private static final String DIARY_DATE_PREFIX = "diary_date_";
	private static final String DIARY_PROJECT_PREFIX = "diary_project_";
	private static final String DIARY_PHASE_PREFIX = "diary_phase_";
	private static final String DIARY_WORK_PREFIX = "diary_work_";
	private static final String DIARY_TEXT_PREFIX = "diary_text_";
	private static final String DIARY_HOUR_PREFIX = "diary_hour_";
	
	private static final String REDIRECT_HOMEPAGE_URL = "startUp";
	
	private SavedDiaryContentMemoryDealer savedDiaryContentMemoryDealer = SavedDiaryContentMemoryDealer.getInstance();
	private DiaryContentVOToDiaryContentDTOExecutor diaryContentVOToDiaryContentDTOExecutor = DiaryContentVOToDiaryContentDTOExecutor.getInstance();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String diaryTrCountsStr = req.getParameter(REQ_PARAM_DIARY_TR_COUNTS);
		
		String[] diaryTrCounts = diaryTrCountsStr.split(",");
		
		List<DiaryContentDTO> dtos = new ArrayList<>();
		for(String diaryTrCount : diaryTrCounts) {
			
			DiaryContentVO diaryContentVO = new DiaryContentVO();
			diaryContentVO.setCount(Integer.parseInt(diaryTrCount));
			diaryContentVO.setProjectId(Integer.parseInt(req.getParameter(DIARY_PROJECT_PREFIX + diaryTrCount)));
			diaryContentVO.setPhaseId(Integer.parseInt(req.getParameter(DIARY_PHASE_PREFIX + diaryTrCount)));
			diaryContentVO.setWorkId(Integer.parseInt(req.getParameter(DIARY_WORK_PREFIX + diaryTrCount)));
			diaryContentVO.setText(req.getParameter(DIARY_TEXT_PREFIX + diaryTrCount));
			diaryContentVO.setWorkHour(Double.parseDouble(req.getParameter(DIARY_HOUR_PREFIX + diaryTrCount)));
			
			dtos.add(diaryContentVOToDiaryContentDTOExecutor.transform(diaryContentVO, DiaryContentStatus.SAVED));
		}
		
		if(diaryTrCounts.length > 0) {
			
			savedDiaryContentMemoryDealer.update(req.getParameter(DIARY_DATE_PREFIX + diaryTrCounts[0]), dtos);
		}
		
		
		resp.sendRedirect(REDIRECT_HOMEPAGE_URL);
	}
	
	
}
