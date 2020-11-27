package GUITest;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


class TrainerGUI extends JFrame{
		static ArrayList<Trainee> traineelist;
		static int trainerId=0;
		public static ArrayList<Trainee> getTraineeList() {
			return traineelist;
		}
		private void setTraineeList(ArrayList<Trainee> traineelist) {
			this.traineelist=traineelist;
		}
		public static int getTrainerId() {
			return trainerId;
		}
		private void setTrainerId(int trainerId) {
			this.trainerId=trainerId;
		}
		public TrainerGUI(int id){
			setTrainerId(id);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setAlwaysOnTop(true);
			setBounds(400, 200, 500, 300);
			String[] colNames = new String[] {"Id", "Name", "Gender","Age"};
			DefaultTableModel model = new DefaultTableModel(colNames, 0);
			JTable table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);
			add(scrollPane, BorderLayout.CENTER);	
			String[] arr=new String[4];
			
			for(int i=0;i<Main.memberSet.size();i++) {
				if(Main.memberSet.get(i).getId()==getTrainerId()) {
					setTraineeList(((Trainer)Main.memberSet.get(i)).getTraineeList());
					break;
				}
			}
			if(getTraineeList()==null) {
				JOptionPane.showMessageDialog(null,"담당중인 회원 목록이 없습니다.");
			}
			else {
				for(int i=0;i<getTraineeList().size();i++) {
					arr[0]=Integer.toString(getTraineeList().get(i).getId());
					arr[1]=getTraineeList().get(i).getName();
					arr[2]=getTraineeList().get(i).getSex();
					arr[3]=Integer.toString(getTraineeList().get(i).getAge());
					model.addRow(arr);
			}
				setVisible(true);
			}
	}
}

class Trainer extends Member{
	private ArrayList<Trainee> traineeList = new ArrayList<>();
	private int traineeNum = traineeList.size();
	static int trainerId;
	
	public static void TrainerRun(int id) {
		new TrainerGUI(id);
	}

	public Trainer(int id, String name, String sex, int age, int type) {
		super(id, name, sex, age, type);
	
	}
	public ArrayList<Trainee> getTraineeList() {
		if(traineeNum==0) {
			return null;
		}else {
			return traineeList;
		}
	}
	
	public void addTrainee(Trainee trainee) {
		this.traineeList.add(trainee);
		traineeNum=traineeList.size();
	}
	
	public int getTraineeNum() {
		return traineeNum;
	}
	
	
	public Trainee getTrainee(int id) {
		for(Trainee t : traineeList) {
			if(t.getId()==id) {
				System.out.println("ID : "+ t.getId() +" 이름 : "+ t.getName()+" 나이 : "+t.getAge() + " 성별 : "+t.getSex());
				return t;
			}
		}
		return null;
	}
	
	public void addWorkout() {
		Scanner in = new Scanner(System.in);
		System.out.println("담당 회원 목록");
		this.getTraineeList();
		System.out.println();
		System.out.print("회원을 선택해주세요. ID : ");
		Trainee trainee = getTrainee(in.nextInt());
		//memberSet에서 접근해야함.
		trainee.addWorkout();
	}
}
