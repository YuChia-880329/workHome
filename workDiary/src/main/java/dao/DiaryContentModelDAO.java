package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.model.DiaryContentModel;
import util.DBUtil;
import util.DateUtil;

public class DiaryContentModelDAO {

	private List<DiaryContentModel> list;
	
	private Connection connection = DBUtil.getInstance().getConnection();
	
	private static final DiaryContentModelDAO INSTANCE = new DiaryContentModelDAO();
	
	private DiaryContentModelDAO() {
		
		list = new ArrayList<>();
		
		Date date = new Date();
		DiaryContentModel model1 = new DiaryContentModel(1, DateUtil.utilDateToSqlDate(date), 1, 1, 1, "111", 2.3);
		DiaryContentModel model2 = new DiaryContentModel(2, DateUtil.utilDateToSqlDate(date), 1, 2, 2, "122", 1.4);
		DiaryContentModel model3 = new DiaryContentModel(3, DateUtil.utilDateToSqlDate(date), 2, 3, 3, "233", 3);
		
		list.add(model1);
		list.add(model2);
		list.add(model3);
	}
	
	public static DiaryContentModelDAO getInstance() {
		
		return INSTANCE;
	}
	
	
	public List<DiaryContentModel> searchAll(){
		
		String sql = "SELECT CONTENT_ID, CONTENT_DATE, PROJECT_ID, PHASE_ID, WORK_ID, TEXT, WORK_HOUR FROM DIARY_CONTENT";
		List<DiaryContentModel> models = null;
		
		try(Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql)){
			
			models = new ArrayList<>();
			while(rs.next()) {
				
				DiaryContentModel model = new DiaryContentModel();
				model.setId(rs.getInt("CONTENT_ID"));
				model.setDate(rs.getDate("CONTENT_DATE"));
				model.setProjectId(rs.getInt("PROJECT_ID"));
				model.setPhaseId(rs.getInt("PHASE_ID"));
				model.setWorkId(rs.getInt("WORK_ID"));
				model.setText(rs.getString("TEXT"));
				model.setWorkHour(rs.getDouble("WORK_HOUR"));
				models.add(model);
			}
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		return models;
	}
	
	public DiaryContentModel searchById(int id) {
		
		String sql = "SELECT CONTENT_ID, CONTENT_DATE, PROJECT_ID, PHASE_ID, WORK_ID, TEXT, WORK_HOUR FROM DIARY_CONTENT WHERE CONTENT_ID = " + id;
		DiaryContentModel model = null;
		
		try(Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sql)){
			
			
			while(rs.next()) {
				
				model = new DiaryContentModel();
				model.setId(rs.getInt("CONTENT_ID"));
				model.setDate(rs.getDate("CONTENT_DATE"));
				model.setProjectId(rs.getInt("PROJECT_ID"));
				model.setPhaseId(rs.getInt("PHASE_ID"));
				model.setWorkId(rs.getInt("WORK_ID"));
				model.setText(rs.getString("TEXT"));
				model.setWorkHour(rs.getDouble("WORK_HOUR"));
			}
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		return model;
	}
	
	public int add(DiaryContentModel model) {
		
		String sql = "INSERT INTO DIARY_CONTENT (CONTENT_ID, CONTENT_DATE, PROJECT_ID, PHASE_ID, WORK_ID, TEXT, WORK_HOUR) VALUES(DIARY_CONTENT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setDate(1, model.getDate());
			statement.setInt(2, model.getProjectId());
			statement.setInt(3, model.getPhaseId());
			statement.setInt(4, model.getWorkId());
			statement.setString(5, model.getText());
			statement.setDouble(6, model.getWorkHour());
			
			return statement.executeUpdate();
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		return 0;
	}
	
	public int update(DiaryContentModel model) {
		
		String sql = "UPDATE DIARY_CONTENT SET CONTENT_DATE = ?, PROJECT_ID = ?, PHASE_ID = ?, WORK_ID = ?, TEXT = ?, WORK_HOUR = ? WHERE CONTENT_ID = ?";
		
		try(PreparedStatement statement = connection.prepareStatement(sql);){
			
			statement.setDate(1, model.getDate());
			statement.setInt(2, model.getProjectId());
			statement.setInt(3, model.getPhaseId());
			statement.setInt(4, model.getWorkId());
			statement.setString(5, model.getText());
			statement.setDouble(6, model.getWorkHour());
			statement.setInt(7, model.getId());
			
			return statement.executeUpdate();
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		return 0;
	}
	
	public int delete(DiaryContentModel model) {
		
		String sql = "DELETE FROM DIARY_CONTENT WHERE CONTENT_ID = " + model.getId();
		
		try(PreparedStatement statement = connection.prepareStatement(sql);){
			
			return statement.executeUpdate();
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		
		return 0;
	}
}
