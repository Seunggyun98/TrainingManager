package GUITest;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
			
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new FlowLayout());
			JButton btnSelect = new JButton("선택");
			btnPanel.add(btnSelect);
			add(btnPanel,BorderLayout.SOUTH);
			btnSelect.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int row = table.getSelectedRow();
					int selectID = Integer.valueOf((String) table.getModel().getValueAt(row,0));
					String selectName = (String) table.getModel().getValueAt(row,1);
					JOptionPane.showMessageDialog(null,selectName+"님의 워크아웃 정보를 확인합니다.");
					new WorkoutGUI(selectID);
					setVisible(false);
				}
				
			});
	
			
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


/**
 * 트레이너의 정보를 담는 클래스
 * @author 승균
 *
 */
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
	

}
