package GUITest;

import java.util.*;


/**
 * 회원들의 정보를 담는 클래스
 * @author 승균
 *
 */
class Trainee extends Member {
	//trainer id
	private int myTrainer; 
	private ArrayList<WorkoutList> workoutList = new ArrayList<>();
	
	public Trainee(int id, String name, String sex, int age, int type) {
		super(id, name, sex, age, type);
	}
	
	public Trainee(int id, String name, String sex, int age, int type, int TrainerID) {
		super(id, name, sex, age, type);
		this.myTrainer = TrainerID;

	}
	
	public int getTrainer() {
		return myTrainer;
	}
	
	public void setTrainer(int TrainerID) {
		this.myTrainer = TrainerID;
	}

	public void loadWorkout(WorkoutList add) {
		this.workoutList.add(add);
	}
	
	public ArrayList<WorkoutList> listOfWorkOut(){
		return this.workoutList;
	}
	
	/**
	 * 입력한 날짜의 운동을 추가하는 메서드
	 * 해당 날짜에 이미 워크아웃이 존재한다면 해당 워크아웃에 추가한다. 워크아웃이 존재하지 않는다면 {@code WorkoutList}객체를 만들어 저장한다.
	 * @param date 운동 날짜
	 * @param ex 추가할 운동
	 */
	public void addWorkout(Date date,Exercise ex) {
		int idx=0;
		int tag = 1;
		
		for(WorkoutList w : workoutList) {
			if(w.getDate().equals(date)) {
				this.workoutList.get(idx).addExercise(ex);
				tag=0;
				break;
			}
			idx++;
		}
		//해당 날짜의 워크아웃이 없으면 워크아웃리스트 객체를 새로 생성하여 추가한다.
		if(tag ==1) {
			this.workoutList.add(new WorkoutList());
			this.workoutList.get(this.workoutList.size()-1).addWorkout(date,ex);
		}
	}
	

}
