package project;
import java.util.*;

class Trainer extends Member{
	private ArrayList<Trainee> traineeList = new ArrayList<>();
	private int traineeNum = traineeList.size();
	
	public Trainer(int id, String name, String sex, int age, int type) {
		super(id, name, sex, age, type);
	
	}
	public ArrayList<Trainee> getTraineeList() {
		if(traineeNum==0) {
			System.out.println("담당 회원 목록이 비었습니다.");
			return null;
		}else {
			for(Trainee t : traineeList) {
				System.out.println("ID : "+ t.getId() +" 이름 : "+ t.getName()+" 나이 : "+t.getAge() + " 성별 : "+t.getSex());
			}
			return traineeList;
		}
	}
	
	public void addTrainee(Trainee trainee) {
		this.traineeList.add(trainee);
	}
	
	public int getTraineeNum() {
		return traineeNum;
	}
	
	
	public Trainee getTrainee(int id) {
		for(Trainee t : traineeList) {
			if(t.getId()==id) {
				System.out.println("ID : "+ t.getId() +" 이름 : "+ t.getName()+" 나이 : "+t.getAge() + " 성별 : "+t.getSex());
				return t;
			}
		}
		return null;
	}
	
	public void addWorkout() {
		Scanner in = new Scanner(System.in);
		System.out.println("담당 회원 목록");
		this.getTraineeList();
		System.out.println();
		System.out.print("회원을 선택해주세요. ID : ");
		Trainee trainee = getTrainee(in.nextInt());
		//memberSet에서 접근해야함.
		trainee.addWorkout();
	}
}
