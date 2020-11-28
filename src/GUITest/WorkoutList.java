package GUITest;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class WorkoutList {
	private ArrayList<Exercise> exerciseList;
	private Date exerciseDate;
	
	public WorkoutList() {
		this.exerciseList = new ArrayList<>();
		this.exerciseDate = new Date();
	}
	
	public WorkoutList(int dy,int dm,int dd) {
		this.exerciseDate =  new Date(dy,dm,dd);
	}
	
	public void addExercise(Exercise exercise) {
		exerciseList.add(exercise);
	}
	
	
	public void addWorkout(Date date, Exercise ex) {
		this.exerciseDate = date;
		this.exerciseList.add(ex);
	}
	
	
	public String getExercise() {
		String ex = new String();
		System.out.println("날짜 : "+this.exerciseDate.toString());
		ex+="날짜 : "+this.exerciseDate.toString()+"\n";
		System.out.println("----------------------루틴-------------------------");
		for(Exercise e : exerciseList) {
			System.out.println(e.getTargetMuscle()+" "+e.getExName()+" "+e.getSet()+"세트 "+e.getReps()+"회 "+e.getWeight()+"kg");
			ex +=e.getTargetMuscle()+" "+e.getExName()+" "+e.getSet()+"세트 "+e.getReps()+"회 "+e.getWeight()+"kg"+"\n";
		}
		return ex;
	}
	
	public Date getDate() {
		return this.exerciseDate;
	}
	public void setDate(int y,int m,int d) {
		this.exerciseDate = new Date(y,m,d);
	}
	
	public ArrayList<Exercise> getExerciseList(){
		return this.exerciseList;
	}
	public void setExerciseList(ArrayList<Exercise> exList) {
		this.exerciseList = exList;
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


	public boolean equals(Date date){
		return date.getYear() == this.year && date.getMonth() == this.month && date.getDay() == this.day;
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
