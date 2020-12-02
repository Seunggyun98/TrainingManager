package GUITest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class WorkoutGUI {
    private int id;
    public WorkoutGUI(int id) {
       this.id=id;
       main(this.id);
    }
    public void main(int id) {
        this.id = id;
        EventQueue.invokeLater(() -> {
            WorkoutFrame workoutFrame = new WorkoutFrame(id);
            workoutFrame.setTitle("Trainning Manager");
            workoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            workoutFrame.setVisible(true);

        });
    }
}
class DateSelect extends JFrame{

   Date selectedDate;
   String[] colNames = new String[] {"Year", "Month", "Day"};
   DefaultTableModel model = new DefaultTableModel(colNames, 0){
      public boolean isCellEditable(int i, int c){ return false; }
   };
   JTable table = new JTable(model);
   JScrollPane scrollPane = new JScrollPane(table);
   public DateSelect(int id) {
      ArrayList<WorkoutList> workoutlist = null;
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setAlwaysOnTop(true);
      setBounds(400, 200, 500, 300);
      JTable table = new JTable(model);
      JScrollPane scrollPane = new JScrollPane(table);
      
      JPanel panel = new JPanel();
      JButton add=new JButton("검색");
      panel.add(add);
      add(scrollPane, BorderLayout.CENTER);
      //add(panel);
      for(int i=0;i<Main.memberSet.size();i++) {
         if(Main.memberSet.get(i).getId()==id) {
            workoutlist = ((Trainee)Main.memberSet.get(i)).listOfWorkOut();
            break;
         }
      }
      String[] row=new String[3];
      if(workoutlist.size()==0) {
         JOptionPane.showMessageDialog(null,"운동리스트가 아직 없습니다.");
      }
      else {
      for(int i=0;i<workoutlist.size();i++) {
         Date date=workoutlist.get(i).getDate();
         row[0]=Integer.toString(date.getYear());
         row[1]=Integer.toString(date.getMonth());
         row[2]=Integer.toString(date.getDay());
         model.addRow(row);
         }

      add(panel,BorderLayout.SOUTH);
      setVisible(true);

      }
      add.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            WorkoutFrame.model.setNumRows(0);
            int row = table.getSelectedRow();
            Date date=new Date(Integer.valueOf((String) table.getModel().getValueAt(row,0)),Integer.valueOf((String)table.getModel().getValueAt(row,1)),Integer.valueOf((String)table.getModel().getValueAt(row,2)));
            int findID=0;
            
            for(Member m : Main.memberSet) {
               if(m.getId()==id) {
                  break;
               }
               findID++;
            }

            ArrayList<WorkoutList> list = ((Trainee)Main.memberSet.get(findID)).listOfWorkOut();
            int idx=0;
            int searchTag=0;
            for(int j = 0;j<list.size();j++) {
               if(list.get(j).getDate().equals(date))
               {
                  idx=j;
                  //System.out.println(list.get(idx).getExerciseList().size());
                  for(int i=0; i<list.get(idx).getExerciseList().size();i++) {
                     String[] row1 = new String[8];
                     row1[0] = list.get(idx).getExerciseList().get(i).getTargetMuscle();
                     row1[1] = list.get(idx).getExerciseList().get(i).getExName();
                     row1[2] = String.valueOf(list.get(idx).getExerciseList().get(i).getSet());
                     row1[3] = String.valueOf(list.get(idx).getExerciseList().get(i).getReps());
                     row1[4] = String.valueOf(list.get(idx).getExerciseList().get(i).getWeight());
                     row1[5] = String.valueOf(list.get(idx).getDate().getYear());
                     row1[6] = String.valueOf(list.get(idx).getDate().getMonth());
                     row1[7] = String.valueOf(list.get(idx).getDate().getDay());
                     /*for(String s:row) {
                        System.out.println(s);
                     }*/
                     WorkoutFrame.model.addRow(row1);
                  }
                  searchTag=1;
                  
               }
            }
            if(searchTag==0) {
               model.setNumRows(0);
               JOptionPane.showMessageDialog(null, "해당 날짜에 운동 정보가 없습니다.");
            }
            setVisible(false);
         }      
       });
   }
   
}

class WorkoutFrame extends JFrame{

    static String[] exerciseText = new String[] {"부위","이름","세트","횟수","중량","년","월","일"};
    static DefaultTableModel model = new DefaultTableModel(exerciseText,0);
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 500;
    private int id; 
    JTextField textField;
    static JTextField exerciseDateYear = new JTextField(4);
    static JTextField exerciseDateMonth = new JTextField(2);
    static JTextField exerciseDateDay = new JTextField(2);

   
   
    public WorkoutFrame(int id){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        //setResizable(false);
        this.id=id;
        Container contentPlane = this.getContentPane();
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("WORKOUT PAGE");
        topPanel.add(label);
        contentPlane.add(topPanel,BorderLayout.NORTH);

        
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);
        contentPlane.add(scrollPane,BorderLayout.CENTER);
        

        //오른쪽 버튼 패널
        JPanel rightPanel = new JPanel();
        JPanel textPanel = new JPanel();

        textPanel.setLayout(new GridLayout(10,1));
        JLabel exerciseLabel = new JLabel("운동 부위");
        JTextField exercise = new JTextField(4);
        JLabel exerciseNameLabel = new JLabel("운동 이름");
        JTextField exerciseName = new JTextField(4);
        JLabel exerciseSetLabel = new JLabel("세트");
        JTextField exerciseSet = new JTextField(4);
        JLabel exerciseRepsLabel = new JLabel("횟수");
        JTextField exerciseReps = new JTextField(4);
        JLabel exerciseWeightLabel = new JLabel("중량");
        JTextField exerciseWeight = new JTextField(4);
        JLabel exerciseDateLabel = new JLabel("날짜");
        JLabel exerciseDateYearLabel = new JLabel("년");
        JLabel exerciseDateMonthLabel = new JLabel("월");
        JLabel exerciseDateDayLabel = new JLabel("일");

        JPanel datePanel = new JPanel();

        datePanel.add(exerciseDateYear);
        datePanel.add(exerciseDateYearLabel);
        datePanel.add(exerciseDateMonth);
        datePanel.add(exerciseDateMonthLabel);
        datePanel.add(exerciseDateDay);
        datePanel.add(exerciseDateDayLabel);

        textPanel.add(exerciseLabel);
        textPanel.add(exercise);
        textPanel.add(exerciseNameLabel);
        textPanel.add(exerciseName);
        textPanel.add(exerciseSetLabel);
        textPanel.add(exerciseSet);
        textPanel.add(exerciseRepsLabel);
        textPanel.add(exerciseReps);
        textPanel.add(exerciseWeightLabel);
        textPanel.add(exerciseWeight);
        textPanel.add(exerciseDateLabel);
        textPanel.add(datePanel);
        JPanel btnPanel = new JPanel();
        JButton searchExercise = new JButton("운동 찾기");
        JButton addExercise = new JButton("운동 추가");
        JButton saveExercise = new JButton("운동 저장");
        JButton deleteExercise = new JButton("워크아웃 삭제");
        
        
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(addExercise);
        btnPanel.add(searchExercise);
        btnPanel.add(saveExercise);
        btnPanel.add(deleteExercise);
        
        rightPanel.setLayout(new GridLayout(2,2));;
        rightPanel.add(textPanel);
        rightPanel.add(btnPanel);
        contentPlane.add(rightPanel,BorderLayout.EAST);
        
        
        
        //운동 추가 버튼 action
        addExercise.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            //입력된 값 회원-워크아웃리스트-해당 날짜 워크아웃-운동리스트-추가
            //입력된 값 테이블에 출력
            String[] rows = new String[8];
            //문자
            rows[0] = exercise.getText();
            rows[1] = exerciseName.getText();
            //숫자입력받아야함
            rows[2] = exerciseSet.getText();
            rows[3] = exerciseReps.getText();
            rows[4] = exerciseWeight.getText();
            rows[5] = exerciseDateYear.getText();
            rows[6] = exerciseDateMonth.getText();
            rows[7] = exerciseDateDay.getText();
            int tag = 0;
            for(String s : rows) {
               if(s.length()==0) {
                  tag=1;
                  break;
               }
            }
            if(tag==1) {
               //입력안한 칸이 존재. 알림창. 모두 입력해주세요.
                JOptionPane.showMessageDialog(null, "빠짐없이 입력해주세요.");
            }
            else {
               
               try {
                  Exercise ex = new Exercise(exerciseName.getText(),exercise.getText(),Integer.valueOf(exerciseSet.getText()),Integer.valueOf(exerciseReps.getText()),Double.valueOf(exerciseWeight.getText()));
                  Date date = new Date(Integer.valueOf(exerciseDateYear.getText()),Integer.valueOf(exerciseDateMonth.getText()),Integer.valueOf(exerciseDateDay.getText()));
                  
                  //텍스트 필드 값 제거
                  exercise.setText("");
                  exerciseName.setText("");
                  exerciseSet.setText("");
                  exerciseReps.setText("");
                  exerciseWeight.setText("");
                  exerciseDateYear.setText("");
                  exerciseDateMonth.setText("");
                  exerciseDateDay.setText("");
                  
                  
                  int idx=0;
                  for(Member m : Main.memberSet) {
                     
                     if(m.getId()==id) {
                        break;
                     }
                     idx++;
                  }
                  
                  //회원(id) - 워크아웃리스트 - 해당 날짜 워크아웃 -운동리스트의 운동 객체, 날짜객체에 날짜 추가
                  ((Trainee)Main.memberSet.get(idx)).addWorkout(date,ex);
                  model.setNumRows(0);
                  ArrayList<WorkoutList> list = ((Trainee)Main.memberSet.get(idx)).listOfWorkOut();
                  idx=0;
                  for(int j = 0;j<list.size();j++) {
                     if(list.get(j).getDate().equals(date))
                     {
                        idx=j;
                        //System.out.println(list.get(idx).getExerciseList().size());
                        for(int i=0; i<list.get(idx).getExerciseList().size();i++) {
                           String[] row1 = new String[8];
                           row1[0] = list.get(idx).getExerciseList().get(i).getTargetMuscle();
                           row1[1] = list.get(idx).getExerciseList().get(i).getExName();
                           row1[2] = String.valueOf(list.get(idx).getExerciseList().get(i).getSet());
                           row1[3] = String.valueOf(list.get(idx).getExerciseList().get(i).getReps());
                           row1[4] = String.valueOf(list.get(idx).getExerciseList().get(i).getWeight());
                           row1[5] = String.valueOf(list.get(idx).getDate().getYear());
                           row1[6] = String.valueOf(list.get(idx).getDate().getMonth());
                           row1[7] = String.valueOf(list.get(idx).getDate().getDay());
                           /*for(String s:row) {
                              System.out.println(s);
                           }*/
                           WorkoutFrame.model.addRow(row1);
                        }
                        
                        
                     }
                  }
                
                  try {
                     Database.saveWorkout();
                  } catch (IOException e1) {
                     e1.printStackTrace();
                  }
               }catch(NumberFormatException err) {
                  JOptionPane.showMessageDialog(null, "횟수, 세트수, 중량, 날짜는 숫자로 입력해주세요");
               }
            }
         }
           
        });
        
        //찾기 버튼 action
        searchExercise.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            {
            	new DateSelect(id);
            }
            //날짜가 같은 WorkoutList에서 getExercise
                  //id회원의 workoutList의 workout 중 date가 같은 workout의 운동 리스트의 운동
         }
           
        });
        
        saveExercise.addActionListener(new ActionListener(){

         @Override
         public void actionPerformed(ActionEvent arg0) {
            try {
               Database.saveWorkout();
               JOptionPane.showMessageDialog(null, "워크아웃이 저장되었습니다.");
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
           
        });
    
    
        deleteExercise.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            ArrayList<Integer> rows = new ArrayList<>();
            String year = exerciseDateYear.getText();
            String month = exerciseDateMonth.getText();
            String day = exerciseDateDay.getText();
            try {
            rows.add(Integer.valueOf(exerciseDateYear.getText()));
            rows.add(Integer.valueOf(exerciseDateMonth.getText()));
            rows.add(Integer.valueOf(exerciseDateDay.getText()));
            int tag=0; int tag2=0;
            
               for(int i=0;i<3;i++) {
                  if(i==0&&(rows.get(i)>2099||rows.get(i)<1900)) {
                     tag2=1;
                     throw new Exception();
                  
                  }
                  else if(i==1&&(rows.get(i)>12||rows.get(i)<1)) {
                     tag2=1;
                     throw new Exception();
                  
                  }
                  else if(i==2&&(rows.get(i)>31||rows.get(i)<1)) {
                     tag2=1;
                     throw new Exception();
                  
                  }
                  else{
                     if(rows.get(i)==null) {
                        tag=1;
                     }
                  }
               }
               
               if(tag2!=1) {
                  Date date = new Date(Integer.valueOf(exerciseDateYear.getText()),Integer.valueOf(exerciseDateMonth.getText()),Integer.valueOf(exerciseDateDay.getText()));
                     
                   //id회원의 workoutList의 workout 중 date가 같은 workout의 운동 리스트의 운동
                  int findID=0;
                  
                  for(Member m : Main.memberSet) {
                     if(m.getId()==id) {
                        break;
                     }
                     findID++;
                  }

                  ArrayList<WorkoutList> list = ((Trainee)Main.memberSet.get(findID)).listOfWorkOut();
                  int idx=0;
                  int searchTag=0;
                  for(int j = 0;j<list.size();j++) {
                     if(list.get(j).getDate().equals(date))
                     {
                        list.remove(j);
                        JOptionPane.showMessageDialog(null, "해당 날짜의 워크아웃이 삭제되었습니다."
                              + " 저장 버튼을 꼭 눌러주세요!");
                        model.setNumRows(0);
                        searchTag=1;
                        
                     }
                  }
                  if(searchTag==0) {
                     model.setNumRows(0);
                     JOptionPane.showMessageDialog(null, "해당 날짜에 운동 정보가 없습니다.");
                  }
               }
            }catch(NumberFormatException err) {
               JOptionPane.showMessageDialog(null, "날짜를 빈칸 없이 입력해주세요.");
            }
            catch(Exception err) {   
               JOptionPane.showMessageDialog(null, "날짜를 올바르게 입력해주세요.");
            }
            
         }
           
           
        });
        
    
    }
    
   

}

