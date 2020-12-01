package GUITest;
import java.time.LocalDate;
import java.util.*;

class Trainee extends Member {

	private int myTrainer; //trainer id를 담고있음
	private int remainPT; 
	private ArrayList<WorkoutList> workoutList = new ArrayList<>();
	
	public Trainee(int id, String name, String sex, int age, int type) {
		super(id, name, sex, age, type);
	}
	
	public Trainee(int id, String name, String sex, int age, int type, int TrainerID) {
		super(id, name, sex, age, type);
		this.myTrainer = TrainerID;

	}
	
	public void setRemainPT(int pt) {
		this.remainPT = pt;
	}
	public void minusPT() {
		this.remainPT = this.remainPT -1;
	}
	
	public int getTrainer() {
		return myTrainer;
	}
	
	public void setTrainer(int TrainerID) {
		this.myTrainer = TrainerID;
	}
	
	public void addWorkout() {
		WorkoutList add = new WorkoutList();
		workoutList.add(add);
	}
	
	public void loadWorkout(WorkoutList add) {
		this.workoutList.add(add);
	}
	
	public String getWorkoutList(){
		WorkoutList wlist = new WorkoutList(0,0,0);
		for(WorkoutList wl : workoutList) {
			wlist = wl;
		}
		return wlist.getExercise();
	}
	public ArrayList<WorkoutList> listOfWorkOut(){
		return this.workoutList;
	}
	public void addWorkout(Date date,Exercise ex) {
		//워크아웃리스트 - 해당 날짜 워크아웃
		int idx=0;
		int tag = 1;
		//System.out.println(this.workoutList.size());
		for(WorkoutList w : workoutList) {
			if(w.getDate().equals(date)) {
				this.workoutList.get(idx).addExercise(ex);
				tag=0;
				break;
			}
			idx++;
		}
		if(tag ==1) {
			this.workoutList.add(new WorkoutList());
			this.workoutList.get(this.workoutList.size()-1).addWorkout(date,ex);
		}
	}
	
	public void deleteWorkout(Date date) {
		for(int i =0;i<workoutList.size();i++) {
			if(workoutList.get(i).getDate().equals(date)) {
				workoutList.get(i).deleteWorkout();
				break;
			}
		}
	}

}
