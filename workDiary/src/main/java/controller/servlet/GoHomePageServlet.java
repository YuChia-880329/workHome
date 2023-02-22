package controller.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProjectModelDAO;
import executor.HomeDiaryContentPrepareExecutor;
import executor.transform.ProjectModelAndProjectVOTransformer;
import util.DateUtil;
import util.GsonUtil;

@SuppressWarnings("serial")
public class GoHomePageServlet extends HttpServlet {
	
	// url
	public static final String URL = "homePage";
	private static final String FORWARD_URL = "homePage.jsp";

	
	// parameter
	private static final String REQ_PARAM_DATE = "date";
	// attribute
	private static final String REQ_ATTR_PROJECT_VOS = "projectVOsJson";
	private static final String REQ_ATTR_DIARY_CONTENT_VOS = "diaryContentVOsJson";
	private static final String REQ_ATTR_DATE = "currentDate";
	
	
	private HomeDiaryContentPrepareExecutor homeDiaryContentPrepareExecutor = HomeDiaryContentPrepareExecutor.getInstance();
	
	private ProjectModelAndProjectVOTransformer transformer = ProjectModelAndProjectVOTransformer.getInstance();
	
	private ProjectModelDAO projectModelDAO = ProjectModelDAO.getInstance();
	
	
	private Gson gson = GsonUtil.getGson();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String dateStr = req.getParameter(REQ_PARAM_DATE);
		
		Date date = null;
		try {
			
			if(dateStr==null || dateStr.equals("")) {
				date = new Date();
			}else
				date = DateUtil.stringToDate(dateStr);
		} catch (ParseException ex) {
			
			System.out.println(ex.getMessage());
		}
		
		req.setAttribute(REQ_ATTR_PROJECT_VOS, gson.toJson(transformer.modelListToVOList(projectModelDAO.searchAll())));
		req.setAttribute(REQ_ATTR_DIARY_CONTENT_VOS, gson.toJson(homeDiaryContentPrepareExecutor.getDiaryVOsFromMemory(date)));
		req.setAttribute(REQ_ATTR_DATE, DateUtil.dateToString(date));
		
		req.getRequestDispatcher(FORWARD_URL).forward(req, resp);
	}

}
