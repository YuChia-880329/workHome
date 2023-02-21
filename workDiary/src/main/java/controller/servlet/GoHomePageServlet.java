package controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProjectModelDAO;
import executor.HomeDiaryContentPrepareExecutor;
import executor.transform.ProjectModelAndProjectVOTransformer;
import util.GsonUtil;

@SuppressWarnings("serial")
public class GoHomePageServlet extends HttpServlet {
	
	// url
	public static final String URL = "homePage";
	private static final String FORWARD_URL = "homePage.jsp";

	
	// attribute
	private static final String REQ_ATTR_PROJECT_VOS = "projectVOs";
	private static final String REQ_ATTR_DIARY_CONTENT_VOS = "diaryContentVOsJson";
	
	
	private HomeDiaryContentPrepareExecutor homeDiaryContentPrepareExecutor = HomeDiaryContentPrepareExecutor.getInstance();
	
	private ProjectModelAndProjectVOTransformer transformer = ProjectModelAndProjectVOTransformer.getInstance();
	
	private ProjectModelDAO projectModelDAO = ProjectModelDAO.getInstance();
	
	
	private Gson gson = GsonUtil.getGson();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute(REQ_ATTR_PROJECT_VOS, transformer.modelListToVOList(projectModelDAO.searchAll()));
		req.setAttribute(REQ_ATTR_DIARY_CONTENT_VOS, gson.toJson(homeDiaryContentPrepareExecutor.getDiaryVOsFromMemory()));
		
		req.getRequestDispatcher(FORWARD_URL).forward(req, resp);
	}

}
