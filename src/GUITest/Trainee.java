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
	
	public Trainee(int id, String name, String sex, int age, int type, int TrainerID, int remainPT) {
		super(id, name, sex, age, type);
		this.myTrainer = TrainerID;
		this.remainPT=remainPT;
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
	
	public void addWorkout() {
		WorkoutList add = new WorkoutList();
		workoutList.add(add);
	}
	
	public void loadWorkout(WorkoutList add) {
		this.workoutList.add(add);
	}
	
	public WorkoutList getWorkoutList(Date date){
		for(WorkoutList wl : workoutList){
			if(wl.getDate().equals(date)){
				return wl;
			}
		}
		return null;
	}
	public ArrayList<WorkoutList> listOfWorkOut(){
		return this.workoutList;
	}



}
