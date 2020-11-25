package project;

import java.util.Scanner;

class AddTrainee {
	static Scanner in = new Scanner(System.in);
	public static Trainee add() {
		System.out.println("추가할 회원 아이디를 입력해주세요 : ");
		int addID = in.nextInt();
		Trainee toAdd = null;
		for(Member m : Main.memberSet) {
			if(m.getId() == addID) {
				toAdd = (Trainee)m;
			}
		}
		
		return toAdd;
	}
}
