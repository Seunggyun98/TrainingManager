package GUITest;

/**
 * 운동 데이터를 담는 클래스
 * @author 승균
 *
 */
class Exercise {
	private String exName;
	private String targetMuscle;
	private int reps;
	private int set;
	private double weight;
	
	public Exercise(String exName, String targetMuscle, int reps, int set, double weight) {
		this.exName = exName;
		this.targetMuscle = targetMuscle;
		this.reps = reps;
		this.set = set;
		this.weight=weight;
	}
	
	public String getExName() {
		return exName;
	}
	public void setExName(String exName) {
		this.exName = exName;
	}
	public String getTargetMuscle() {
		return targetMuscle;
	}
	public void setTargetMuscle(String targetMuscle) {
		this.targetMuscle = targetMuscle;
	}
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}
	public int getSet() {
		return set;
	}
	public void setSet(int set) {
		this.set = set;
	}
	public double getWeight() {
		return this.weight;
	}
	public void setWeight(double weight) {
		this.weight=weight;
	}
	
}
