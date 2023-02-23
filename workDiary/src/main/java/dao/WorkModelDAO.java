package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.model.WorkModel;
import util.DBUtil;

public class WorkModelDAO {
	
	private Connection connection = DBUtil.getInstance().getConnection();
	
	private static final WorkModelDAO INSTANCE = new WorkModelDAO();
	private WorkModelDAO() {
		
	}
	
	public static WorkModelDAO getInstance() {
		return INSTANCE;
	}
	
	public List<WorkModel> searchAll(){
		
		String sql = "SELECT WORK_ID, WORK_NAME, PHASE_ID FROM DIARY_WORK";
		List<WorkModel> models = null;
		
		try(Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql)){
			
			models = new ArrayList<>();
			while(rs.next()) {
				
				WorkModel model = new WorkModel();
				model.setWorkId(rs.getInt("WORK_ID"));
				model.setWorkName(rs.getString("WORK_NAME"));
				model.setPhaseId(rs.getInt("PHASE_ID"));
				models.add(model);
			}
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		return models;
	}
	
	public WorkModel searchById(int workId) {
		
		String sql = "SELECT WORK_ID, WORK_NAME, PHASE_ID FROM DIARY_WORK WHERE WORK_ID = " + workId;
		WorkModel model = null;
		
		try(Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);){
			
			while(rs.next()) {
				
				model = new WorkModel();
				model.setWorkId(rs.getInt("WORK_ID"));
				model.setWorkName(rs.getString("WORK_NAME"));
				model.setPhaseId(rs.getInt("PHASE_ID"));
			}
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		return model;
	}
	public List<WorkModel> searchByPhaseId(int phaseId){
		
		String sql = "SELECT WORK_ID, WORK_NAME, PHASE_ID FROM DIARY_WORK WHERE PHASE_ID = " + phaseId;
		List<WorkModel> models = new ArrayList<>();
		
		try(Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);){
			
			while(rs.next()) {

				WorkModel model = new WorkModel();
				model.setWorkId(rs.getInt("WORK_ID"));
				model.setWorkName(rs.getString("WORK_NAME"));
				model.setPhaseId(rs.getInt("PHASE_ID"));
				models.add(model);
			}
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		return models;
	}
	
}
