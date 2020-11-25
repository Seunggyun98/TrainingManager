package project;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class WorkoutList {
	private ArrayList<Exercise> exerciseList = new ArrayList<>();
	private Date exerciseDate;
	private double weight;
	
	public WorkoutList() {
		addWorkout();
	}
	
	public WorkoutList(int dy,int dm,int dd, double weight) {
		this.weight=weight;
		this.exerciseDate =  new Date(dy,dm,dd);
	}
	public void addExercise(Exercise exercise) {
		exerciseList.add(exercise);
	}
	
	public void addWorkout() {
		System.out.println("날짜 입력 : yyyy mm dd");
		int year,month,day;
		Scanner in = new Scanner(System.in);
		year = in.nextInt();
		month = in.nextInt();
		day = in.nextInt();
		System.out.println("몸무게 입력 : ");
		double w = in.nextDouble();
		exerciseDate = new Date(year,month, day);
		this.weight = w;
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
					System.out.println("부위 종목이름 횟수 세트수 무게: ");
					targetMuscle = in.nextLine();
					exName = in.nextLine();
					reps = in.nextInt();
					set = in.nextInt();
					weight = in.nextDouble();
					System.out.println("부위 : "+targetMuscle+" 종목이름 : "+exName+" 횟수 : "+reps+" 세트수 : "+set+" 무게 : "+weight);
					System.out.println("입력하신 정보가 맞으면 '1'을, 다시 입력하시려면 '0'을 입력해주세요 : ");
					confirm = in.nextInt();
				}
				Exercise add = new Exercise(exName,targetMuscle,reps,set,weight);
				exerciseList.add(add);
				
				System.out.print("운동을 추가하시려면 0, 완료하시려면 1 을 입력해주세요 : ");
				end=in.nextInt();
				
			}catch(InputMismatchException e) {
				System.out.println("입력 형식을 지켜주세요. 다시 시도해주세요.");
			}
		}
	}
	
	public void getExercise(int idx) {
		System.out.println("날짜 : "+this.exerciseDate.toString());
		System.out.println("몸무게 : "+this.weight);
		System.out.println("----------------------루틴-------------------------");
		for(Exercise e : exerciseList) {
			System.out.println(e.getTargetMuscle()+" "+e.getExName()+" "+e.getSet()+"세트 "+e.getReps()+"회 "+e.getWeight()+"kg");
		}
	}
	
	public Date getDate() {
		return this.exerciseDate;
	}
	public ArrayList<Exercise> getExerciseList(){
		return this.exerciseList;
	}
	public double getWeight() {
		return this.weight;
	}
	
	
}

class Date{
	int year;
	int month;
	int day;
	public Date(int year, int month, int day) {
		this.year=year;
		this.month=month;
		this.day=day;
	}
	
	@Override
	public String toString() {
		String toString = this.year+"/"+this.month+"/"+this.day;
		return toString;
	}
	
}
