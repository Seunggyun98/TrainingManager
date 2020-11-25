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
			System.out.println("��� ȸ�� ����� ������ϴ�.");
			return null;
		}else {
			for(Trainee t : traineeList) {
				System.out.println("ID : "+ t.getId() +" �̸� : "+ t.getName()+" ���� : "+t.getAge() + " ���� : "+t.getSex());
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
				System.out.println("ID : "+ t.getId() +" �̸� : "+ t.getName()+" ���� : "+t.getAge() + " ���� : "+t.getSex());
				return t;
			}
		}
		return null;
	}
	
	public void addWorkout() {
		Scanner in = new Scanner(System.in);
		System.out.println("��� ȸ�� ���");
		this.getTraineeList();
		System.out.println();
		System.out.print("ȸ���� �������ּ���. ID : ");
		Trainee trainee = getTrainee(in.nextInt());
		//memberSet���� �����ؾ���.
		trainee.addWorkout();
	}
}
