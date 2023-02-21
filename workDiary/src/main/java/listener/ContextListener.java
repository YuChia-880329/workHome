package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import executor.StartPrepareDiaryContentExecutor;

public class ContextListener implements ServletContextListener {

	private StartPrepareDiaryContentExecutor prepareDiaryContentExecutor = StartPrepareDiaryContentExecutor.getInstance();
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		prepareDiaryContentExecutor.prepare();
	}
}
