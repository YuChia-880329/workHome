package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.vo.DiaryContentVO;
import executor.SendDiaryContentExecutor;
import util.GsonUtil;

@SuppressWarnings("serial")
public class SendDataServlet extends HttpServlet {

	// url
	public static final String URL = "send";
	private static final String REDIRECT_HOMEPAGE_URL = GoHomePageServlet.URL;
		
	// parameter
	private static final String REQ_PARAM_DIARY_CONTENTS = "diary_contents";
		
	
	private Gson gson = GsonUtil.getGson();
	
	
	private SendDiaryContentExecutor sendExecutor = SendDiaryContentExecutor.getInstance();
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String diaryContentsJson = req.getParameter(REQ_PARAM_DIARY_CONTENTS);
		
		
		DiaryContentVO[] voArray = gson.fromJson(diaryContentsJson, DiaryContentVO[].class);
		
		if(voArray.length > 0)
			sendExecutor.send(new ArrayList<>(Arrays.asList(voArray)));
		
		resp.sendRedirect(REDIRECT_HOMEPAGE_URL);
	}
}
