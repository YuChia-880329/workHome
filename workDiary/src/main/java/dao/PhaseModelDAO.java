package dao;

import java.util.ArrayList;
import java.util.List;

import bean.model.PhaseModel;

public class PhaseModelDAO {

	private List<PhaseModel> list;

	private static final PhaseModelDAO INSTANCE = new PhaseModelDAO();
	private PhaseModelDAO() {
		
		PhaseModel phase1 = new PhaseModel(1, "內部", 1);
		PhaseModel phase2 = new PhaseModel(2, "外部", 1);
		PhaseModel phase3 = new PhaseModel(3, "休假中", 2);
		
		list = new ArrayList<>();
		list.add(phase1);
		list.add(phase2);
		list.add(phase3);
	}
	
	public static PhaseModelDAO getInstance() {
		return INSTANCE;
	}
	
	public PhaseModel search(int phaseId) {
		
		return list.get(0);
	}
	public List<PhaseModel> searchByProjectId(int projectId){
		
		List<PhaseModel> newList = new ArrayList<>();
		if(projectId == 1) {
			newList.add(list.get(0));
			newList.add(list.get(1));
		}else if(projectId == 2) {
			newList.add(list.get(2));
		}
		
		return newList;
	}
	public List<PhaseModel> searchAll(){
		
		return list;
	}
}
