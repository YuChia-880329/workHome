package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.model.ProjectModel;
import util.DBUtil;

public class ProjectModelDAO {

	public static final String URL = "projChange";
	
	private Connection connection = DBUtil.getInstance().getConnection();

	private static final ProjectModelDAO INSTANCE = new ProjectModelDAO();
	private ProjectModelDAO() {
		
	}
	
	public static ProjectModelDAO getInstance() {
		
		return INSTANCE;
	}
	
	
	public List<ProjectModel> searchAll(){
		
		String sql = "SELECT PROJECT_ID, PROJECT_NAME FROM DIARY_PROJECT";
		List<ProjectModel> models = new ArrayList<>();
		
		try(Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);){
			
			while(rs.next()) {
				
				ProjectModel model = new ProjectModel();
				model.setProjectId(rs.getInt("PROJECT_ID"));
				model.setProjectName(rs.getString("PROJECT_NAME"));
				models.add(model);
			}
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		return models;
	}
	
	public ProjectModel searchById(int projectId) {
		
		String sql = "SELECT PROJECT_ID, PROJECT_NAME FROM DIARY_PROJECT WHERE PROJECT_ID = " + projectId;
		ProjectModel model = null;
		
		try(Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);){
			
			while(rs.next()) {
				
				model = new ProjectModel();
				model.setProjectId(rs.getInt("PROJECT_ID"));
				model.setProjectName(rs.getString("PROJECT_NAME"));
			}
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		return model;
	}
}
