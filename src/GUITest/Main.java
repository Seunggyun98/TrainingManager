package GUITest;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 프로그램 실행시 실행되는 메인클래스
 * 프로그램을 실행하면 데이터베이스에서 정보를 불러온다.
 * @author 승균
 *
 */
public class Main {
	//트레이너와 회원들의 명단을 담고 있는 리스트
	static List<Member> memberSet = new ArrayList<>();
	public static void main(String[] args) {
		
		//전체 명단을 불러온 후 성공 여부를 보여줌
		
		//명단 파일의 경로
		Path path = Paths.get("src/Database/list/Member.csv");
		File memberList = new File(path.toUri());
		try {
			Database.readMember(memberList);
			System.out.println("회원 명단 불러오기 성공!");
			System.out.println("회원 : "+memberSet.size()+"명");
		} catch (Exception e) {
			System.out.println("명단 불러오기 실패!");
		}
		
		
		//회원들의 워크아웃을 불러온다.
		for(int i=0;i<memberSet.size();i++) {
			try {
				
				String wname=Main.memberSet.get(i).getId()+".csv";
				Path wpath = Paths.get("src/Database/workout/"+wname);
				File wfile = new File(wpath.toUri());
				Database.readWorkout(wfile);
		
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//트레이너의 담당 회원 명단을 불러온다.
		for(int i =0;i<memberSet.size();i++) {
			if(Main.memberSet.get(i).getType()==1) {
				try {
					String tname=Main.memberSet.get(i).getId()+".csv";
					
					Path tpath = Paths.get("src/Database/traineeList/"+tname);
					File tfile = new File(tpath.toUri());
					Database.readTrainee(tfile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		//GUI실행
		new TextField1();
		
	}

}

class TextField1 extends JFrame{
	   static int id=-1;
	   static int login_cnt = 0;
	   public TextField1() 
	   {
	      setTitle("Training Manager");
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      LogIn login = new LogIn();
	      Register register = new Register();
	      Panel p = new Panel();
	      Label lab=new Label("아 이 디    : ");
	      TextField txt=new TextField("",4);
	      JButton Blogin=new JButton("로그인");
	      JButton BsignIn=new JButton("회원가입");
	      p.add(lab);
	      p.add(txt);
	      add(p);
	      p.add(BsignIn);
	      p.add(Blogin); 
	      p.setLayout(null);
	      lab.setBounds(20,30,70,22);
	      txt.setBounds(100,30,90,22);
	      BsignIn.setBounds(10, 60, 90, 30);
	      Blogin.setBounds(110,60,90,30);
	      setSize(250,200);
	      setVisible(true);
	        Blogin.addActionListener( new ActionListener() {
	             public void actionPerformed(ActionEvent e) { 
	            if(txt.getText().length()>=5) {
	            	JOptionPane.showMessageDialog(null,"id는 4글자 아래만 가능합니다.");
	            }
	            else try {
	            	if(login.login(Integer.valueOf(txt.getText()))==0)
	            		setVisible(false);
	            	
	  	       }catch(Exception e1) {
	  	           JOptionPane.showMessageDialog(null,"숫자로 된 id를 입력해 주십시오.");
	  	       }
	          }
	  } );
	        BsignIn.addActionListener( new ActionListener() {
	             public void actionPerformed(ActionEvent e) { 
	                Register r = new Register();
	                r.RegisterRun();
	          }
	  } );  
	}
}