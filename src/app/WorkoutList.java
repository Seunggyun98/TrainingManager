package app;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


/**
 * 각 회원이 가지고 있는 워크아웃 리스트를 저장하는 클래스
 * @author 승균
 *
 */
class WorkoutList implements Comparable<WorkoutList>{
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


	@Override
	public int compareTo(WorkoutList w) {

		return this.exerciseDate.compareTo(w.exerciseDate);
	}
}


/**
 * 날짜를 저장하는 클래스
 * @author 승균
 *
 */
class Date implements Comparable<Date>{
	//년도
	private int year;
	//달
	private int month;
	//일
	private int day;
	
	public Date() {};
	
	public Date(int year, int month, int day) {
		this.year=year;
		this.month=month;
		this.day=day;
	}

	/**
	 * 날짜를 비교하기위한 equals 메서드 오버라이딩
	 */
	@Override
	public boolean equals(Object otherObject){
		if(this==otherObject) return true;
		if(otherObject==null) return false;
		if(getClass()!=otherObject.getClass()) return false;
		
		Date other = (Date)otherObject;
		return other.getYear() == this.year && other.getMonth() == this.month && other.getDay() == this.day;
	}
	/**
	 * equals메소드와 함께 hashCode메소드 오버라이딩
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + year;
		result = prime * result + month;
		result = prime * result + day;
		return result;
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
	
	
	/**
	 * Comparable 인터페이스의 메서드를 오버라이드
	 * 날짜를 기준으로 워크아웃을 정렬하기 위함 
	 */
	@Override
	public int compareTo(Date other) {
		if(this.year<other.year) return -1;
		else if(this.year>other.year) return 1;
		else {
			if(this.month<other.month) return -1;
			else if (this.month>other.month) return 1;
			else {
				if(this.day<other.day) return -1;
				else if(this.day>other.day) return 1;
				else return 0;
			}
		}	
	}
}
