package dao;

import java.util.ArrayList;
import java.util.List;

import bean.model.WorkModel;

public class WorkModelDAO {
	
	private List<WorkModel> list;

	private static final WorkModelDAO INSTANCE = new WorkModelDAO();
	private WorkModelDAO() {
		
		WorkModel work1 = new WorkModel(1, "新進人員教育訓練", 1);
		WorkModel work2 = new WorkModel(2, "外部教育訓練", 2);
		WorkModel work3 = new WorkModel(3, "公假", 3);
		
		list = new ArrayList<>();
		list.add(work1);
		list.add(work2);
		list.add(work3);
	}
	
	public static WorkModelDAO getInstance() {
		return INSTANCE;
	}
	
	public WorkModel search(int workId) {
		
		return list.get(0);
	}
	public List<WorkModel> searchByPhaseId(int phaseId){
		
		List<WorkModel> newList = new ArrayList<>();
		if(phaseId == 1) {
			newList.add(list.get(0));
		}else if(phaseId == 2) {
			newList.add(list.get(1));
		}else if(phaseId == 3) {
			newList.add(list.get(2));
		}
		
		return newList;
	}
	public List<WorkModel> searchAll(){
		
		return list;
	}
}
