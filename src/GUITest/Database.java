package GUITest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Database {
	public Database() {
		
	}
	public static void saveMember() throws IOException {
		String fname="Member.csv";
		File file = new File(fname);
		//파일 출력 한글 인코딩 
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "MS949");
		BufferedWriter bw = new BufferedWriter(OutputStreamWriter);

		bw.newLine();
		for(int i=0;i<Main.memberSet.size();i++) {
			if(Main.memberSet.get(i).getType()==1) {
				bw.write(+Main.memberSet.get(i).getId()+","+Main.memberSet.get(i).getName()+","+Main.memberSet.get(i).getType()+","+Main.memberSet.get(i).getAge()+","+Main.memberSet.get(i).getSex());
				bw.newLine();
			}else {
				bw.write(+Main.memberSet.get(i).getId()+","+Main.memberSet.get(i).getName()+","+Main.memberSet.get(i).getType()+","+Main.memberSet.get(i).getAge()+","+Main.memberSet.get(i).getSex());
				bw.newLine();
			}
		}
		System.out.println("-------------------");
		System.out.println("Member Save Complete");
		System.out.println("-------------------");
		bw.close();
		
	}
	
	
	public static void saveWorkout() throws IOException {
		
		for(int i = 0; i<Main.memberSet.size();i++) {
			//각 회원별로 파일에 데이터베이스 저장
			if(Main.memberSet.get(i).getType()==2) {
				String fname=Main.memberSet.get(i).getId()+".csv";
				File file = new File(fname);
				//파일 출력 한글 인코딩 
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "MS949");
				BufferedWriter bw = new BufferedWriter(OutputStreamWriter);
				bw.write(Main.memberSet.get(i).getId());
				bw.newLine();
				ArrayList<WorkoutList> workoutList = ((Trainee)Main.memberSet.get(i)).listOfWorkOut();
				for(WorkoutList wl : workoutList) {
					Date wlDate = wl.getDate();
					ArrayList<Exercise> wlExList = wl.getExerciseList();
					bw.write(wlDate.getYear()+","+wlDate.getMonth()+","+wlDate.getDay()+",");
					for(Exercise ex : wlExList) {
						bw.write(ex.getExName()+","+ex.getTargetMuscle()+","+ex.getReps()+","+ex.getSet()+","+ex.getWeight());
					}
					bw.newLine();
				}
				
				System.out.println("-------------------");
				System.out.println("Workout Save Complete");
				System.out.println("-------------------");
				bw.close();
			}
			
		}
	}
	
	public static void readMember(File file) throws Exception {
		try {
			BufferedReader br =null;
			br = new BufferedReader(new FileReader(file));
			
			String line = "";
			while((line=br.readLine())!=null) {
				List<String> tmp = new ArrayList<String>();
				String arr[] = line.split(",");
				tmp=Arrays.asList(arr);
				int fId = 0;
				String fName = "";
				int fType=0;
				int fAge=0;
				String fSex="";
				int fTrainer=0;
				int fRemainPT=0;
				for(int i=0;i<tmp.size();i++) {
						switch(i) {
							case 0:
								fId = Integer.valueOf(tmp.get(i));
								break;
							case 1:
								fName = tmp.get(i);
								break;
							case 2:
								fType = Integer.valueOf(tmp.get(i));
								break;
							case 3:
								fAge = Integer.valueOf(tmp.get(i));
								break;
							case 4:
								fSex = tmp.get(i);
								break;
							case 5:
								if(fType==1) break;
								fTrainer = Integer.valueOf(tmp.get(i));
								break;
							case 6:
								if(fType==1) break;
								fRemainPT = Integer.valueOf(tmp.get(i));
								break;
						}
					
				}
				if(fType ==1 ) {
					Main.memberSet.add(new Trainer(fId,fName,fSex,fAge,1));		
				}else if(fType==2) {
					Main.memberSet.add(new Trainee(fId,fName,fSex,fAge,2,fTrainer));
				}
			}
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new Exception("회원 명단 불러오기를 실패하였습니다.");		
		}
	}
	
	
	public static void readWorkout(File file) throws Exception {
		try {
			Scanner sc = new Scanner(file);
			
			String temp;
			String[] splited;
			
			
			int id = Integer.valueOf(sc.nextLine().split(",")[0]);
			int idx=0;
			int t=0;
			for(Member m : Main.memberSet) {
				if(m.getId() == id) idx = t;
				t++;	
			}
			
			
			while(sc.hasNextLine()) {
				WorkoutList tList;
				ArrayList<Exercise> exList=new ArrayList<>();
				
				int exSize = Integer.valueOf(sc.nextLine().split(",")[0]);
				
				//System.out.println("exSize "+ exSize);
				
				int dd=0,dm=0,dy=0;
				temp = sc.nextLine();
				splited = temp.split(",");
				dy = Integer.valueOf(splited[0]);
				dm = Integer.valueOf(splited[1]);
				dd = Integer.valueOf(splited[2]);
				
				for(int i=0;i<exSize;i++) {
					 Exercise tExercise;
					String eName="", eTarget = ""; int reps=0,set=0; double eWeight=0;
					temp =sc.nextLine();
					splited = temp.split(",");
					for(int j=0;j<splited.length;j++) {
						switch(j) {
						case 0:
							eName = splited[j];
							break;
						case 1:
							eTarget = splited[j];
							break;
						case 2:
							reps = Integer.valueOf(splited[j]);
							break;
						case 3:
							set = Integer.valueOf(splited[j]);
							break;
						case 4:
							eWeight = Double.valueOf(splited[j]);
							break;
						}
					}
					tExercise = new Exercise(eName,eTarget,reps,set,eWeight);
					//System.out.println(tExercise.toString());
					exList.add(tExercise);
				}
				tList = new WorkoutList(dy, dm, dd);
				//List.setDate(dy, dm, dd);
				tList.setExerciseList(exList);
				((Trainee)Main.memberSet.get(idx)).loadWorkout(tList);
				
				//System.out.println(((Trainee)memberSet.get(idx)).getWorkoutList(new Date(dy,dm,dd)).toString());
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//throw new Exception(" failed to load");		
		}
	}

}
