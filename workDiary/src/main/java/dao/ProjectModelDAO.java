package dao;

import java.util.ArrayList;
import java.util.List;

import bean.model.ProjectModel;

public class ProjectModelDAO {

	public static final String URL = "projChange";
	
	private List<ProjectModel> list;

	private static final ProjectModelDAO INSTANCE = new ProjectModelDAO();
	private ProjectModelDAO() {
		
		ProjectModel project1 = new ProjectModel(1, "教育訓練");
		ProjectModel project2 = new ProjectModel(2, "休假");
		
		list = new ArrayList<>();
		list.add(project1);
		list.add(project2);
	}
	
	public static ProjectModelDAO getInstance() {
		return INSTANCE;
	}
	
	
	public List<ProjectModel> searchAll(){
		
		return list;
	}
	
	public ProjectModel search(int projectId) {
		
		return list.get(0);
	}
}
