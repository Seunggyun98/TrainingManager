package app;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 * Main클래스의 GUI에서 회원가입을 눌렀을 경우 띄워주는 GUI클래스이다.
 * 회원과 트레이너를 선택하고 이름, 성별, 나이, 아이디 그리고 중복체크를 한다.
 * @author 태홍
 *
 */
class signframe extends JFrame{
      String signSex;
      

      private void setSex(int signSex) {
         if(signSex==1)
         this.signSex="male";
         else this.signSex="female";
      }
      private String getSex() {
         return signSex;
      }
      int signType=1;
      private void setSignType(int signType) {
         this.signType=signType;
      }
      private int getSignType() {
         return signType;
      }
      int duplechk=0;
      private int getDup() {
         return duplechk;
      }
      private void setDup(int duplechk) {
         this.duplechk=duplechk;
      }
      int dupledid=0;
      private void setDupid(int dupledid) {
         this.dupledid=dupledid;
      }
      private int getDupid() {
         return dupledid;
      }
      public signframe()
      {
	    	setTitle("Training Manager");
	        boolean duplicateChk = false;
	        Panel p = new Panel();
	        Label lab1=new Label("아 이 디 : ");
	        TextField txt1=new TextField(10);
	        JButton duple=new JButton("중복확인");
	        Label lab2=new Label("이 름 : ");
	        TextField txt2=new TextField(10);
	        Label lab3=new Label("성 별 : ");
	        JRadioButton male = new JRadioButton("남");
	        JRadioButton female = new JRadioButton("여");
	        male.setSelected(true);
	        ButtonGroup sex = new ButtonGroup();
	        sex.add(male);
	        sex.add(female);
	        Label lab4=new Label("직 책 : ");
	        JRadioButton trainer = new JRadioButton("트레이너");
	        JRadioButton trainee = new JRadioButton("회원");
	        trainee.setSelected(true);
	        ButtonGroup member = new ButtonGroup();
	        member.add(trainer);
	        member.add(trainee);
	        Label lab5=new Label("나 이 : ");
	        TextField txt5=new TextField(10);
	        JButton signin=new JButton("회원가입");
	        p.add(lab1);
	        p.add(txt1);
	        p.add(duple);
	        p.add(lab2);
	        p.add(txt2);
	        p.add(lab3);
	        p.add(male);
	        p.add(female);
	        p.add(lab4);
	        p.add(trainee);
	        p.add(trainer);
	        p.add(lab5);
	        p.add(txt5);
	        p.add(signin);
	        add(p);
	        p.setLayout(null);
	        lab1.setBounds(30, 30, 50, 22);
	        txt1.setBounds(100, 30, 120, 22);
	        duple.setBounds(230, 30, 90, 30);
	        lab2.setBounds(30, 70, 50, 22);
	        txt2.setBounds(100, 70, 120, 22);
	        lab3.setBounds(30, 110, 50, 22);
	        male.setBounds(100, 110, 60, 22);
	        female.setBounds(170, 110, 60, 22);
	        lab4.setBounds(30, 150, 50, 22);
	        trainer.setBounds(100, 150, 100, 22);
	        trainee.setBounds(170, 150, 60, 22);
	        lab5.setBounds(30, 190,50, 22);
	        txt5.setBounds(100, 190, 120, 22);
	        signin.setBounds(120, 240, 100, 30);
	        setSize(400, 400);
	        setVisible(true);
	        int duplechecked=0;
        
	       //회원 가입 버튼에 대한 ActionListener
           signin.addActionListener( new ActionListener() {
        	   
                public void actionPerformed(ActionEvent e) { 
                /*
	        	 * 중복체크를 해주는 부분이다.
	             * 1. 만약 텍스트가 중복체크가 된 아이디가 아닌 경우에 중복확인 먼저 해돌라는 메세지를 출력한다.
	             * 2. 또한 중복체크가 성공했어도 중복체크 성공한 아이디가 아니라 다른 아이디면 중복확인을 먼저 하라는 메세지를 출력한다.
	             */
                   if(getDup()==0||(getDupid()!=(Integer.valueOf(txt1.getText())))) {
                      JOptionPane.showMessageDialog(null, "중복확인을 먼저 하셔야 합니다.");
                   }
                   else {
                	  // 중복확인이 잘 된 경우에는 다른 텍스트박스에서 내용을 가져와서 변수에 담아준다.
                      try {
                      int signId = getDupid();
                      String signName = txt2.getText();
                      if(male.isSelected()) setSex(1);
                      else setSex(2);
                      if(trainer.isSelected()) setSignType(1);
                      else setSignType(2);
                      int signAge=Integer.valueOf(txt5.getText());
                      
                      //트레이너로 회원가입 한 경우
                      if(getSignType()==1) {
                         Member member = new Trainer(signId,signName,getSex(),signAge,getSignType());
                         Main.memberSet.add(member);
                         try {
                            Database.saveMember();
                         }catch(Exception err) {
                            System.out.println("멤버 저장 실패");
                         }try {
                         Database.saveTrainee();
                         }catch(Exception err) {
                            System.out.println("Trainee저장 실패");
                         }
                         JOptionPane.showMessageDialog(null, "회원가입에 성공하셨습니다!");
                         }
                      
                    //회원으로 회원가입 한 경우
                      if(getSignType()==2) {
                         Trainee trainee=new Trainee(signId,signName,getSex(),signAge,getSignType());
                         //트레이너를 선택 할 수 있는 창을 새로 띄워준다.
                         new trainerList(trainee); 
                      }
                      setVisible(false);
                      }catch(Exception e1) {
                    	  //빈칸이 있을경우 오류메세지를 출력해준다.
                         JOptionPane.showMessageDialog(null, "빈칸 또는 올바르지 않은 입력이 있습니다.");
                      }
                   }
                }
           } );
           
           
           //중복확인 버튼 ActionListener.
           duple.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                   try {
                	 //아이디가 이미 존재하는 경우
                      if(Register.duplicateCheck(Integer.valueOf(txt1.getText()))==true) {
                        JOptionPane.showMessageDialog(null, "이미 존재하는 아이디 입니다. 다시 시도해주세요.");                  
                        }
                     else{
                    	//숫자가 4자리 초과인 경우
                        if(txt1.getText().length()>4) {
                           JOptionPane.showMessageDialog(null, "id는 4자리 이하 숫자여야 합니다.");                     
                        }
                        //정상적인 아이디일 경우
                        else 
                           {JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
                        setDup(1);
                        setDupid(Integer.valueOf(txt1.getText()));
                           }
                     }
                   }catch(Exception e1) {
                	 //문자인 경우
                      JOptionPane.showMessageDialog(null,"숫자로 된 id를 입력해 주십시오.");
                   }
                }
           } );
   }
}


/**
 * 회원으로 회원가입 했을 경우에 담당 트레이너를 선택해야 하는 GUI클래스.
 * @author 태홍
 *
 */
class trainerList extends JFrame{
   private static int tag=0;  
   private static int trainerId=0;
   
   public static int getTag() {
      return tag;
   }
   private void setTag(int tag) {
      this.tag=tag;
   }
 
   public static int getTrainerId() {
      return trainerId;
   }
   private void setTrainerId(int trainerId) {
      this.trainerId=trainerId;
   }
   
   public trainerList(Trainee trainee) 
   {
	  setTitle("Trainning Manager");
      setTrainerId(0);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setAlwaysOnTop(true);
      setBounds(200, 100, 400, 200);
      String[] colNames = new String[] {"Id", "Name", "Gender"};
      DefaultTableModel model = new DefaultTableModel(colNames, 0){
         public boolean isCellEditable(int i, int c)
         { 
        	 return false; 
         }
      };
      
      JTable table = new JTable(model);
      JScrollPane scrollPane = new JScrollPane(table);
      add(scrollPane, BorderLayout.CENTER);
      JPanel bottomPanel = new JPanel();
      bottomPanel.setLayout(new GridLayout(2, 1));
      JTextField trainId = new JTextField(6);
      JButton btn = new JButton("확 인");
      JPanel panel2 = new JPanel();

      panel2.add(new JLabel("ID"));
      panel2.add(trainId);
      panel2.add(btn);
      
      bottomPanel.add(panel2);   
      String[] arr=new String[3];
      //트레이너의 아이디와 이름 성별을 띄워준다.
      for(int i=0;i<Main.memberSet.size();i++) {
         if(Main.memberSet.get(i).getType() == 1) {
            arr[0]=Integer.toString(Main.memberSet.get(i).getId());
            arr[1]=Main.memberSet.get(i).getName();
            arr[2]=Main.memberSet.get(i).getSex();
            model.addRow(arr);
         }
      }
      
      //확인 버튼 ActionListener
      btn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               setTag(0);
               if(trainId.getText().length()>4) {
            	   JOptionPane.showMessageDialog(null, "id는 4자리 이하 숫자여야 합니다.");                     
               }
              else {
	               try {
		               for(int i=0;i<Main.memberSet.size();i++) {
		                 if(Main.memberSet.get(i).getType()==1&&Main.memberSet.get(i).getId()==Integer.valueOf(trainId.getText())) {
		                    setTrainerId(Integer.valueOf(trainId.getText()));
		                    Register.matchTrainer(getTrainerId(),trainee);
		                    break;
		                 }
		               }
	               }catch(Exception e1) {
	                  JOptionPane.showMessageDialog(null,"숫자로 된 id를 입력해 주십시오.");
	                  setTag(1);
	               }
	               
	               if(getTrainerId()==0) {
	                  if(getTag()==0)
	                  JOptionPane.showMessageDialog(null,"등록되지 않은 아이디 입니다.");
	               }
	               else{
	                  //trainerID.setID(getTrainerId());
	                  JOptionPane.showMessageDialog(null,"회원가입에 성공하셨 습니다.\n트레이너의 아이디는"+getTrainerId()+"입니다.");
	                  trainee.setTrainer(getTrainerId());
	                  Main.memberSet.add(trainee);
	                  try {
	                	  //맴버가 추가되면 DB에 저장한다.
	                      Database.saveMember();
	                   }catch(Exception err) {
	                      System.out.println("멤버 저장 실패");
	                   }
	                  try {
	                	  //트레이너의 회원목록 DB에 저장한다.
	                      Database.saveTrainee();
	                  }catch(Exception err) {
	                      System.out.println("Trainee저장 실패");
	                  }
	                  setVisible(false);
	               }
               }
     }
} );
        add(bottomPanel,BorderLayout.SOUTH);      
      setVisible(true);
   }
}

/**
 * 회원가입을 동작에 대한 클래스
 * @author 태홍
 *
 */
class Register {
   public void RegisterRun() {
      new signframe();
   }
   
   
   /**
    * 입력한 아이디에 대해서 중복체크를 한다
    * @param id 가입할 때 입력한 아이디
    * @return 이미 존재하는 아이디인지 아닌지에 대한 True/False
    */
   static boolean duplicateCheck(int id) {
      
      try {
      for(int i=0;i<Main.memberSet.size();i++) {
         int search = Main.memberSet.get(i).getId();
         if(id == search) {
            return true;
         }
      }
      }catch(NoSuchElementException e) {
         return false;
      }
      return false;
   }
   
   /**
    * 트레이너에 회원을 매치시켜준다.
    * @param trainerID 회원가입시에 입력받은 트레이너 아이디
    * @param trainee 회원가입을 하려는 회원
    */
   static void matchTrainer(int trainerID, Trainee trainee) {
      for(int i =0;i<Main.memberSet.size();i++) {
         if(Main.memberSet.get(i).getId() == trainerID) {
            ((Trainer)Main.memberSet.get(i)).addTrainee(trainee);
            break;
         }
      }
   }
}

