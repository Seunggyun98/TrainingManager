package GUITest;
import java.util.*;

 class WorkoutMenu {

	public void main(Member member) {
		
		Scanner in = new Scanner(System.in);
		int menu=0;
		while(menu!=4) {
			menu=0;
			System.out.println("\n----------------------------------------------------");
			System.out.println("-------------------WORK OUT PAGE--------------------");
			System.out.println("----------------------------------------------------\n");
			System.out.println("1.운동 추가\t2.몸무게 변화\t3.운동 확인\t4.종료");
			try{
				menu = in.nextInt();
				switch(menu) {
				case 1:
					if(member.getType()==2) {
						((Trainee)member).addWorkout();
					}else if (member.getType()==1) {
						((Trainer)member).addWorkout();
					}
					
					break;
				case 2:
					
					break;
				case 3:
					//SelectWorkOut(member);
					((Trainee)member).getWorkoutList();
					break;
				default:
					System.out.println("다시 선택해주세요.");
					break;
				}
				
				//4를 선택
				System.exit(0);
			}catch(InputMismatchException e) {
				System.out.println("메뉴 선택이 잘못되었습니다. 다시 선택해주세요.");
			}
		}
	}
	/*private void SelectWorkOut(Member member) {
		int idx = 1;
		Date date = new Date(2020,10,22);
		if(member.getType()==2) {
			WorkoutList exerciseList = ((Trainee)member).getWorkoutList();
			System.out.println(exerciseList.getDate().toString());
			for(Exercise e : exerciseList.getExerciseList()) {
				System.out.println(idx+". 부위 : "+e.getTargetMuscle()+" 종목 : "+e.getExName()+" 세트 : "+e.getSet()+" 횟수 : "+e.getReps()+" 무게 : "+e.getWeight());
			}	
		}
	}
	*/
	public WorkoutMenu() {
		
	}
	
	
	
	 
	
	
}
