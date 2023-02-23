package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.model.PhaseModel;
import util.DBUtil;

public class PhaseModelDAO {

	private Connection connection = DBUtil.getInstance().getConnection();
	
	private static final PhaseModelDAO INSTANCE = new PhaseModelDAO();
	private PhaseModelDAO() {
		
	}
	
	public static PhaseModelDAO getInstance() {
		return INSTANCE;
	}
	
	public List<PhaseModel> searchAll(){
		
		String sql = "SELECT PHASE_ID, PHASE_NAME, PROJECT_ID FROM DIARY_PHASE";
		List<PhaseModel> models = new ArrayList<>();
		
		try(Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql)){
			
			while(rs.next()) {
				
				PhaseModel model = new PhaseModel();
				model.setPhaseId(rs.getInt("PHASE_ID"));
				model.setPhaseName(rs.getString("PHASE_NAME"));
				model.setProjectId(rs.getInt("PROJECT_ID"));
				models.add(model);
			}
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		return models;
	}
	public PhaseModel searchById(int phaseId) {
		
		String sql = "SELECT PHASE_ID, PHASE_NAME, PROJECT_ID FROM DIARY_PHASE WHERE PHASE_ID = " + phaseId;
		PhaseModel model = null;
		
		try(Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);){
			
			while(rs.next()) {
				
				model = new PhaseModel();
				model.setPhaseId(rs.getInt("PHASE_ID"));
				model.setPhaseName(rs.getString("PHASE_NAME"));
				model.setProjectId(rs.getInt("PROJECT_ID"));
			}
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		return model;
	}
	public List<PhaseModel> searchByProjectId(int projectId){
		
		String sql = "SELECT PHASE_ID, PHASE_NAME, PROJECT_ID FROM DIARY_PHASE WHERE PROJECT_ID = " + projectId;
		List<PhaseModel> models = new ArrayList<>();
		
		try(Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql);){
			
			while(rs.next()) {

				PhaseModel model = new PhaseModel();
				model.setPhaseId(rs.getInt("PHASE_ID"));
				model.setPhaseName(rs.getString("PHASE_NAME"));
				model.setProjectId(rs.getInt("PROJECT_ID"));
				models.add(model);
			}
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		return models;
	}
	
}
