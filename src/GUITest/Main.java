package GUITest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	static List<Member> memberSet = new ArrayList<>();
	static String[][] member = new String[1000][7];
	public static void main(String[] args) {
		new TextField1();
		Path path = Paths.get("src/Member.csv");
		File memberList = new File(path.toUri());
		try {
			Database.readMember(memberList);
			System.out.println("회원 명단 불러오기 성공!");
			System.out.println("회원 : "+memberSet.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<memberSet.size();i++) {
			try {
				String id = String.valueOf(memberSet.get(i).getId())+".csv";
				Path woPath = Paths.get("src/"+id);
				File workout = new File(woPath.toUri());
				Database.readWorkout(workout);
		
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//5262회원에 운동 추가하고 시작
		Member me = findById(5262);
		Date date = new Date(2020,10,22);
		Exercise ex = new Exercise("벤치프레스", "가슴", 5,4,75);
		((Trainee)me).addWorkout(date, ex);
		
	}
	
		
	public static Member findById(int id) {
		Member found = new Member();
		for(Member m : memberSet) {
			if(m.getId()==id) {
				found = m;
			}
		}
		return found;
	}

}

class TextField1 extends JFrame{
	   static int id=-1;
	   static int login_cnt = 0;
	   public TextField1() 
	   {
	      
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      LogIn login = new LogIn();
	      Register register = new Register();
	      Panel p = new Panel();
	      Label lab=new Label("아 이 디 : ");
	      TextField txt=new TextField("ID",4);
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
	            login.login(Integer.valueOf(txt.getText()));
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