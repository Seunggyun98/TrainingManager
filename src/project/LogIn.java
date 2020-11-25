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
		
		panel.add(new JLabel("���̵�  "));
		text = new JTextField(15);
		text.addActionListener(listener);
		panel.add(text);
		
		
		button = new JButton("�α���");
		button.addActionListener(listener);
		panel.add(button);
		add(panel);
		setVisible(true);
		*/
	}
	
	public int login(List<Member> memberSet) {
		//LogIn login = new LogIn();
		Scanner in = new Scanner(System.in);
		//idSet�ҷ�����
		int login_cnt = 0;
		while(login_cnt<10) {
			int id=0;
			try {	
				System.out.println("ID�� �Է����ּ��� (0 : ���α׷� ����) : "); 
				
				try{
					id = in.nextInt();
					if(id==0) {
						System.out.println("���α׷��� �����մϴ�.");
						System.exit(0);
					}else {
						if(id<=0||id>=10000) {
							throw new Exception();
						}
					}
				}catch(Exception e) {
					System.out.println("id�� 4�ڸ� �Դϴ�. �ٽ� �Է����ּ���.");
					this.login(memberSet);
				}
				
				for(int i=0;i<memberSet.size();i++) {
					int search = memberSet.get(i).getId();
					if(id == search) {
						// type���� Ʈ���̳� -> ȸ�� ����
						//ȸ�� -> ��ũ�ƿ� �������� �̵�
						//helloMember(id);
						//���� ����
						return id;
					}
				}
				
			}catch(InputMismatchException e) {
				System.out.println("ID�� 4�ڸ� �����Դϴ�. �ٽ� �Է����ּ���.");
			}catch(NoSuchElementException e) {
				System.out.println("�������� �ʴ� ���̵� �Դϴ�. �ٽ� �õ����ּ���.");
				this.login(memberSet);
			}
			
		}
		System.out.println("�α��� ����! �ٽ� �α��� ���ּ���");
		return 0;		
	}
	
	public void helloMember(int id) {
		for(Member m : Main.memberSet) {
			if(m.getId()==id) {
				System.out.println(m.getName()+"�� ȯ���մϴ�.");
				if(m.getType()==1) {
					//Ʈ���̳ʴ� ȸ�� ����ȭ������ �̵�
					workout menu = new workout();
					Scanner sel = new Scanner(System.in);
					((Trainer)m).getTraineeList();
					System.out.print("ȸ���� �������ּ��� ID :");
					menu.main(((Trainer)m).getTrainee(sel.nextInt()));
				}
				else if (m.getType() == 2) {
					//ȸ���� �ٷ� ��ũ�ƿ� �������� �̵�
					workout menu = new workout();
					menu.main(m);
				}
			}
		}
	}

}
