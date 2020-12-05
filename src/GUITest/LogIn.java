package GUITest;


import javax.swing.*;

class LogIn {
	
	public int login(int mainid) {
		int tag=0;
		int id=0;
			id = mainid;				
		for(int i=0;i<Main.memberSet.size();i++) {
			int search = Main.memberSet.get(i).getId();
			if(id == search) {
				tag=1;
			    JOptionPane.showMessageDialog(null, Main.memberSet.get(i).getName()+"님 환영합니다.");
			    //트레이너로 로그인 시에 회원을 선택한다.
				if(Main.memberSet.get(i).getType()==1) Trainer.TrainerRun(id);
				//회원으로 로그인 시에 워크아웃 페이지로 넘어간다.
				else {
					new WorkoutGUI(id);
					return 0;
				}
			}
		}
		if(tag==0) {
			JOptionPane.showMessageDialog(null, "등록되지 않은 아이디입니다.");
			return -1;
		}else {
			return 0;
		}
	}

}
