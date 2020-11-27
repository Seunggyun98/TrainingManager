package GUITest;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

class LogIn extends JFrame {
	static int loginId;
	public static int getId() {
		return loginId;
	}
	private void setId(int id) {
		this.loginId=id;
	}
	public void login(int mainid) {
		//LogIn login = new LogIn();
		Scanner in = new Scanner(System.in);
		//idSet불러오기
		int login_cnt = 0;
		int tag=0;
			int id=0;
				id = mainid;				
				for(int i=0;i<Main.memberSet.size();i++) {
					int search = Main.memberSet.get(i).getId();
					if(id == search) {
						// type으로 트레이너 -> 회원 선택
						//회원 -> 워크아웃 페이지로 이동
						tag=1;
		                JOptionPane.showMessageDialog(null, Main.memberSet.get(i).getName()+"님 환영합니다.");
						if(Main.memberSet.get(i).getType()==1) Trainer.TrainerRun(id);
						else {
							WorkoutGUI menu = new WorkoutGUI(id);
						}//trainee부분 실행
						//리턴 삭제
					}
				}
				if(tag==0) {
					JOptionPane.showMessageDialog(null, "등록되지 않은 아이디입니다.");
				}
	}

}
