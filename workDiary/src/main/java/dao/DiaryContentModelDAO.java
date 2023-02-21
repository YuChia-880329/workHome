package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.model.DiaryContentModel;
import util.DateUtil;

public class DiaryContentModelDAO {

	private List<DiaryContentModel> list;
	
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
		
		return list;
	}
	
	public DiaryContentModel searchById(int id) {
		
		for(DiaryContentModel model : list) {
			
			if(model.getId() == id)
				return model;
		}
		
		return null;
	}
	
	public void add(DiaryContentModel model) {
		
		list.add(model);
	}
	
	public DiaryContentModel update(DiaryContentModel model) {
		
		DiaryContentModel oldModel = searchById(model.getId());
		
		if(oldModel != null)
			list.remove(oldModel);
		
		list.add(model);
		return oldModel;
	}
	
	public DiaryContentModel delete(DiaryContentModel model) {
		
		DiaryContentModel oldModel = searchById(model.getId());
		
		if(oldModel != null)
			list.remove(oldModel);
		return oldModel;
	}
}
