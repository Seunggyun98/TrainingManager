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
			System.out.println("1.� �߰�\t2.������ ��ȭ\t3.� Ȯ��\t4.����");
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
					System.out.println("�ٽ� �������ּ���.");
					break;
				}
				
				//4�� ����
				System.exit(0);
			}catch(InputMismatchException e) {
				System.out.println("�޴� ������ �߸��Ǿ����ϴ�. �ٽ� �������ּ���.");
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
				System.out.println(idx+". ���� : "+e.getTargetMuscle()+" ���� : "+e.getExName()+" ��Ʈ : "+e.getSet()+" Ƚ�� : "+e.getReps()+" ���� : "+e.getWeight());
			}	
		}
	}
	*/
	public WorkoutMenu() {
		
	}
	
	
	
	 
	
	
}
