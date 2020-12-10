package app;


import javax.swing.*;

/**
 * 로그인 버튼을 눌렀을 때 동작에 대한 클래스
 * @author 태홍
 *
 */
class LogIn {
	
	/**
	 * 로그인에 대한 동작
	 * {@code tag}는 아이디가 {@code mainid}인 회원이 존재하는지에 대한 pivot역할을 한다
	 * @param mainid 로그인을 시도하려는 아이디
	 * @return 로그인에 대한 결과
	 */
	public int login(int mainid) {
		int tag=0;
		int id=mainid;				
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
