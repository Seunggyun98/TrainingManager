package project;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;

class LogIn extends JFrame {
	private JButton button;
	private JTextField text, result;
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource()== button || e.getSource()==text) {
				String id = text.getText();
				result.setText(""+id);
			}
		}	
	}
	
	
	public LogIn() {
		/*setSize(300,150);
		setTitle("LOG IN");
		
		JPanel panel = new JPanel();
		
		ButtonListener listener = new ButtonListener();
		
		panel.add(new JLabel("아이디  "));
		text = new JTextField(15);
		text.addActionListener(listener);
		panel.add(text);
		
		
		button = new JButton("로그인");
		button.addActionListener(listener);
		panel.add(button);
		add(panel);
		setVisible(true);
		*/
	}
	
	public int login(List<Member> memberSet) {
		//LogIn login = new LogIn();
		Scanner in = new Scanner(System.in);
		//idSet불러오기
		int login_cnt = 0;
		while(login_cnt<10) {
			int id=0;
			try {	
				System.out.println("ID를 입력해주세요 (0 : 프로그램 종료) : "); 
				
				try{
					id = in.nextInt();
					if(id==0) {
						System.out.println("프로그램을 종료합니다.");
						System.exit(0);
					}else {
						if(id<=0||id>=10000) {
							throw new Exception();
						}
					}
				}catch(Exception e) {
					System.out.println("id는 4자리 입니다. 다시 입력해주세요.");
					this.login(memberSet);
				}
				
				for(int i=0;i<memberSet.size();i++) {
					int search = memberSet.get(i).getId();
					if(id == search) {
						// type으로 트레이너 -> 회원 선택
						//회원 -> 워크아웃 페이지로 이동
						//helloMember(id);
						//리턴 삭제
						return id;
					}
				}
				
			}catch(InputMismatchException e) {
				System.out.println("ID는 4자리 숫자입니다. 다시 입력해주세요.");
			}catch(NoSuchElementException e) {
				System.out.println("존재하지 않는 아이디 입니다. 다시 시도해주세요.");
				this.login(memberSet);
			}
			
		}
		System.out.println("로그인 실패! 다시 로그인 해주세요");
		return 0;		
	}
	
	public void helloMember(int id) {
		for(Member m : Main.memberSet) {
			if(m.getId()==id) {
				System.out.println(m.getName()+"님 환영합니다.");
				if(m.getType()==1) {
					//트레이너는 회원 선택화면으로 이동
					workout menu = new workout();
					Scanner sel = new Scanner(System.in);
					((Trainer)m).getTraineeList();
					System.out.print("회원을 선택해주세요 ID :");
					menu.main(((Trainer)m).getTrainee(sel.nextInt()));
				}
				else if (m.getType() == 2) {
					//회원은 바로 워크아웃 페이지로 이동
					workout menu = new workout();
					menu.main(m);
				}
			}
		}
	}

}
