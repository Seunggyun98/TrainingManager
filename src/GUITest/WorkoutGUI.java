package GUITest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


class WorkoutFrame extends JFrame{
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 500;
    private int id;

    JTextField textField;
    public WorkoutFrame(int id){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        //setResizable(false);
        this.id=id;
        Container contentPlane = this.getContentPane();
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("WORKOUT PAGE");
        topPanel.add(label);
        contentPlane.add(topPanel,BorderLayout.NORTH);

        
        String[] exerciseText = new String[] {"����","�̸�","��Ʈ","Ƚ��","�߷�","��","��","��"};
        DefaultTableModel model = new DefaultTableModel(exerciseText,0);
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);
        contentPlane.add(scrollPane,BorderLayout.CENTER);
        
        //Container center = new Container();
        //JPanel leftPanel = new JPanel();
        //leftPanel.setLayout(new BorderLayout());
        //textField = new JTextField("�ؽ�Ʈ �ʵ�");
        //leftPanel.add(textField,BorderLayout.CENTER);
        //contentPlane.add(leftPanel,BorderLayout.CENTER);

        //������ ��ư �г�
        JPanel rightPanel = new JPanel();
        JPanel textPanel = new JPanel();

        //textPanel.setLayout(new GridLayout(5,2));
        textPanel.setLayout(new GridLayout(10,1));
        JLabel exerciseLabel = new JLabel("� ����");
        JTextField exercise = new JTextField(4);
        JLabel exerciseNameLabel = new JLabel("� �̸�");
        JTextField exerciseName = new JTextField(4);
        JLabel exerciseSetLabel = new JLabel("��Ʈ");
        JTextField exerciseSet = new JTextField(4);
        JLabel exerciseRepsLabel = new JLabel("Ƚ��");
        JTextField exerciseReps = new JTextField(4);
        JLabel exerciseWeightLabel = new JLabel("�߷�");
        JTextField exerciseWeight = new JTextField(4);
        JLabel exerciseDateLabel = new JLabel("��¥");
        JLabel exerciseDateYearLabel = new JLabel("��");
        JLabel exerciseDateMonthLabel = new JLabel("��");
        JLabel exerciseDateDayLabel = new JLabel("��");

        JPanel datePanel = new JPanel();
        JTextField exerciseDateYear = new JTextField(4);
        JTextField exerciseDateMonth = new JTextField(2);
        JTextField exerciseDateDay = new JTextField(2);
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
        JButton addExercise = new JButton("� �߰�");
        JButton searchExercise = new JButton("� ã��");

        JPanel emptyPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(addExercise);
        btnPanel.add(searchExercise);
        rightPanel.setLayout(new GridLayout(2,2));;
        rightPanel.add(textPanel);
        rightPanel.add(btnPanel);
        contentPlane.add(rightPanel,BorderLayout.EAST);
        
        addExercise.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//�Էµ� �� ȸ��-��ũ�ƿ�����Ʈ-�ش� ��¥ ��ũ�ƿ�-�����Ʈ-�߰�
				//�Էµ� �� ���̺� ���
				String[] rows = new String[8];
				//����
				rows[0] = exercise.getText();
				rows[1] = exerciseName.getText();
				//�����Է¹޾ƾ���
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
					//�Է¾��� ĭ�� ����. �˸�â. ��� �Է����ּ���.
					 JOptionPane.showMessageDialog(null, "�������� �Է����ּ���.");
				}
				else {
					
					try {
					Exercise ex = new Exercise(exerciseName.getText(),exercise.getText(),Integer.valueOf(exerciseSet.getText()),Integer.valueOf(exerciseReps.getText()),Double.valueOf(exerciseWeight.getText()));
					Date date = new Date(Integer.valueOf(exerciseDateYear.getText()),Integer.valueOf(exerciseDateMonth.getText()),Integer.valueOf(exerciseDateDay.getText()));
					
					//�ؽ�Ʈ �ʵ� �� ����
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
					model.addRow(rows);
					//ȸ��(id) - ��ũ�ƿ�����Ʈ - �ش� ��¥ ��ũ�ƿ� -�����Ʈ�� � ��ü, ��¥��ü�� ��¥ �߰�
					((Trainee)Main.memberSet.get(idx)).addWorkout(date,ex);
					((Trainee)Main.memberSet.get(idx)).getWorkoutList();
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "Ƚ��, ��Ʈ��, �߷�, ��¥�� ���ڷ� �Է����ּ���");
					}
				}
			}
        	
        });
        
        searchExercise.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.setNumRows(0);
				int throwTag=0;
				//��¥�� ���� WorkoutList���� getExercise
				try {
					ArrayList<String> rows = new ArrayList<>();
					rows.add(exerciseDateYear.getText());
					rows.add(exerciseDateMonth.getText());
					rows.add(exerciseDateDay.getText());
					int tag=0;
					for(String s : rows) {
						if(s.length()==0) {
							tag=1;
							break;
						}
					}
					if(tag==1) {
						//�Է¾��� ĭ�� ����. �˸�â. ��� �Է����ּ���.
						throwTag=1;
						throw new Exception();
					}
					
						Date date = new Date(Integer.valueOf(exerciseDateYear.getText()),Integer.valueOf(exerciseDateMonth.getText()),Integer.valueOf(exerciseDateDay.getText()));
						
			       //idȸ���� workoutList�� workout �� date�� ���� workout�� � ����Ʈ�� �
					int findID=0;
					
					for(Member m : Main.memberSet) {
						
						if(m.getId()==id) {
							break;
						}
						findID++;
					}
					
					
					ArrayList<WorkoutList> list = ((Trainee)Main.memberSet.get(findID)).listOfWorkOut();
					int idx=0;
					for(int i = 0;i<list.size();i++) {
						if(list.get(i).getDate().equals(date))
							{
								idx=i;
								break;
							}
					}
					for(int i=0; i<list.get(idx).getExerciseList().size();i++) {
						String[] row = new String[8];
						row[0] = list.get(idx).getExerciseList().get(i).getExName();
						row[1] = list.get(idx).getExerciseList().get(i).getTargetMuscle();
						row[2] = String.valueOf(list.get(idx).getExerciseList().get(i).getSet());
						row[3] = String.valueOf(list.get(idx).getExerciseList().get(i).getReps());
						row[4] = String.valueOf(list.get(idx).getExerciseList().get(i).getWeight());
						row[5] = String.valueOf(date.getYear());
						row[6] = String.valueOf(date.getMonth());
						row[7] = String.valueOf(date.getDay());
						model.addRow(row);
					}
				}catch(Exception e1) {
					if(throwTag==1)JOptionPane.showMessageDialog(null, "��¥�� �������� �Է����ּ���.");
					else {
						JOptionPane.showMessageDialog(null, "��¥�� ���ڷ� �Է����ּ���");
					}
				}
		       
				
			}
        	
        });
        
    }
   

}


