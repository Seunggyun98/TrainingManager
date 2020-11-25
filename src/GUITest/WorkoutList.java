package GUITest;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class WorkoutList {
	private ArrayList<Exercise> exerciseList = new ArrayList<>();
	private Date exerciseDate = new Date();
	
	public WorkoutList() {
		addWorkout();
	}
	
	public WorkoutList(int dy,int dm,int dd) {
		this.exerciseDate =  new Date(dy,dm,dd);
	}
	
	public void addExercise(Exercise exercise) {
		exerciseList.add(exercise);
	}
	
	public void addWorkout() {
		System.out.println("��¥ �Է� : yyyy mm dd");
		int year,month,day;
		Scanner in = new Scanner(System.in);
		year = in.nextInt();
		month = in.nextInt();
		day = in.nextInt();
		System.out.println("������ �Է� : ");
		double w = in.nextDouble();
		exerciseDate = new Date(year,month, day);
		setExercise();
	}
	public void setExercise() {
		Scanner in = new Scanner(System.in);
		int end=0;
		while(end==0) {
			try {
				String exName = null,targetMuscle = null;
				int reps = 0,set = 0;
				double weight = 0;
				int confirm = 0;
				while(confirm == 0){
					System.out.println("���� �����̸� Ƚ�� ��Ʈ�� ����: ");
					targetMuscle = in.nextLine();
					exName = in.nextLine();
					reps = in.nextInt();
					set = in.nextInt();
					weight = in.nextDouble();
					System.out.println("���� : "+targetMuscle+" �����̸� : "+exName+" Ƚ�� : "+reps+" ��Ʈ�� : "+set+" ���� : "+weight);
					System.out.println("�Է��Ͻ� ������ ������ '1'��, �ٽ� �Է��Ͻ÷��� '0'�� �Է����ּ��� : ");
					confirm = in.nextInt();
				}
				Exercise add = new Exercise(exName,targetMuscle,reps,set,weight);
				exerciseList.add(add);
				
				System.out.print("��� �߰��Ͻ÷��� 0, �Ϸ��Ͻ÷��� 1 �� �Է����ּ��� : ");
				end=in.nextInt();
				
			}catch(InputMismatchException e) {
				System.out.println("�Է� ������ �����ּ���. �ٽ� �õ����ּ���.");
			}
		}
	}
	
	public String getExercise() {
		String ex = new String();
		System.out.println("��¥ : "+this.exerciseDate.toString());
		ex+="��¥ : "+this.exerciseDate.toString()+"\n";
		System.out.println("----------------------��ƾ-------------------------");
		for(Exercise e : exerciseList) {
			System.out.println(e.getTargetMuscle()+" "+e.getExName()+" "+e.getSet()+"��Ʈ "+e.getReps()+"ȸ "+e.getWeight()+"kg");
			ex +=e.getTargetMuscle()+" "+e.getExName()+" "+e.getSet()+"��Ʈ "+e.getReps()+"ȸ "+e.getWeight()+"kg"+"\n";
		}
		return ex;
	}
	
	public Date getDate() {
		return this.exerciseDate;
	}
	public ArrayList<Exercise> getExerciseList(){
		return this.exerciseList;
	}
	
	public void setDate(int y,int m,int d) {
		this.exerciseDate = new Date(y,m,d);
	}

}

class Date{
	private int year;
	private int month;
	private int day;
	public Date() {};
	public Date(int year, int month, int day) {
		this.year=year;
		this.month=month;
		this.day=day;
	}

	@Override
	public boolean equals(Object object){
		return ((Date) object).getYear() == this.year && ((Date) object).getMonth() == this.month && ((Date) object).getDay() == this.day;
	}

	@Override
	public String toString() {
		return this.year+"/"+this.month+"/"+this.day;
	}
	public int getYear(){
		return this.year;
	}
	public int getMonth(){
		return this.month;
	}
	public int getDay(){
		return this.day;
	}
}
