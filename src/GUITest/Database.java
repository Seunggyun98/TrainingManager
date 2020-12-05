package GUITest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * 프로그램을 종료하고 재실행 하였을 때 정보들을 저장하고 불러오는 작업을 하는 클래스
 * @author 승균
 *
 */
public class Database {
	
	/**
	 * 회원과 트레이너를 담고있는 {@code memberSet}의 정보를 csv파일로 저장한다.
	 * 파일 명은 Member.csv이고, 저장 위치는 TrainingManager/src/Database/list 이다.
	 * @throws IOException
	 */
	public static void saveMember() throws IOException {
		String fname="Member.csv";
		Path path = Paths.get("src/Database/list/"+fname);
		File file = new File(path.toUri());
		//파일 출력 한글 인코딩 
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "MS949");
		BufferedWriter bw = new BufferedWriter(OutputStreamWriter);

		for(int i=0;i<Main.memberSet.size();i++) {
			if(Main.memberSet.get(i).getType()==1) {
				bw.write(Main.memberSet.get(i).getId()+","+Main.memberSet.get(i).getName()+","+Main.memberSet.get(i).getType()+","+Main.memberSet.get(i).getAge()+","+Main.memberSet.get(i).getSex());
				bw.newLine();
			}else {
				bw.write(Main.memberSet.get(i).getId()+","+Main.memberSet.get(i).getName()+","+Main.memberSet.get(i).getType()+","+Main.memberSet.get(i).getAge()+","+Main.memberSet.get(i).getSex()+","+((Trainee)Main.memberSet.get(i)).getTrainer());
				bw.newLine();
			}
		}
		bw.close();	
	}
	
	/**
	 * 각 회원들의 워크아웃 정보를 저장하는 메소드
	 * 파일명은 아이디.csv, 저장 위치는 TrainingManager/src/Database/workout
	 * @throws IOException
	 */
	public static void saveWorkout() throws IOException {
		
		for(int i = 0; i<Main.memberSet.size();i++) {
			//각 회원별로 파일에 데이터베이스 저장
			if(Main.memberSet.get(i).getType()==2) {
				//파일명 :  회원아이디.csv
				String fname=Main.memberSet.get(i).getId()+".csv";
				Path path = Paths.get("src/Database/workout/"+fname);
				File file = new File(path.toUri());
				//파일 출력 한글 인코딩 
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "MS949");
				BufferedWriter bw = new BufferedWriter(OutputStreamWriter);
				//첫줄에 아이디 입력
				bw.write(String.valueOf(Main.memberSet.get(i).getId()));
				bw.newLine();
				
				//다음줄부터 (운동개수\n날짜\n운동리스트\n)입력
				ArrayList<WorkoutList> workoutList = ((Trainee)Main.memberSet.get(i)).listOfWorkOut();
				for(WorkoutList wl : workoutList) {
					try {
						Date wlDate = wl.getDate();
						ArrayList<Exercise> wlExList = wl.getExerciseList();
						bw.write(String.valueOf(wlExList.size()));
						bw.newLine();
						bw.write(wlDate.getYear()+","+wlDate.getMonth()+","+wlDate.getDay()+",");
						bw.newLine();
						for(Exercise ex : wlExList) {
							bw.write(ex.getExName()+","+ex.getTargetMuscle()+","+ex.getReps()+","+ex.getSet()+","+ex.getWeight());
							bw.newLine();
						}
					}catch(NullPointerException err) {
						continue;
					}
				}
				bw.close();
			}
		}
	}
	
	/**
	 * 각 트레이너들이 담당하는 회원목록을 저장한다.
	 * 파일 이름은 트레이너 아이디.csv, 경로는 TrainingManager/src/Database/traineeList/
	 * @throws Exception
	 */
	public static void saveTrainee() throws Exception{
		
		for(int i = 0; i<Main.memberSet.size();i++) {
			//각 회원별로 파일에 데이터베이스 저장
			if(Main.memberSet.get(i).getType()==1) {
				//파일명 :  회원아이디.csv
				
				String fname=Main.memberSet.get(i).getId()+".csv";
				Path path = Paths.get("src/Database/traineeList/"+fname);
				File file = new File(path.toUri());
				//파일 출력 한글 인코딩 
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "MS949");
				BufferedWriter bw = new BufferedWriter(OutputStreamWriter);
				//첫줄에 아이디 입력
				bw.write(String.valueOf(Main.memberSet.get(i).getId()));
				bw.newLine();
				
				ArrayList<Trainee> tList = ((Trainer)Main.memberSet.get(i)).getTraineeList();
				for(Trainee t : tList) {
					bw.write(t.getId()+","+t.getName()+","+t.getSex()+","+t.getAge()+","+t.getType());
					bw.newLine();
				}
				bw.close();
			}
		}
		
	}
	
	/**
	 * 저장되어있는 각 트레이너의 회원 목록파일을 불러와 트레이너 객체에 저장한다.
	 * @param file 각 트레이너 아이디로 생성되어있는 CSV파일
	 */
	public static void readTrainee(File file){
		try {
			Scanner sc = new Scanner(file);
		//첫줄은 트레이너 아이디
		int id = Integer.valueOf(sc.nextLine().split(",")[0]);
		
			//id를 가진 member index찾기
			int idx=-1;
			for(int i =0;i<Main.memberSet.size();i++) {
				if(id==Main.memberSet.get(i).getId()) {
					idx=i;
				}
			}
			while(sc.hasNextLine()) {
				String[] temp=sc.nextLine().split(",");
				
				int traineeID=Integer.valueOf(temp[0]);
				String traineeName=temp[1];
				String traineeSex=temp[2];
				int traineeAge=Integer.valueOf(temp[3]);
				int type = Integer.valueOf(temp[4]);
				Trainee trainee = new Trainee(traineeID,traineeName,traineeSex,traineeAge,type);
				((Trainer)Main.memberSet.get(idx)).addTrainee(trainee);
			}
		}catch(NoSuchElementException err) {
			
		} catch (FileNotFoundException e) {
		
		}
	}
	
	
	/**
	 * 프로그램 실행 시 회원 목록파일을 읽어와 {@code memberSet}에 저장한다.
	 * @param file
	 * @throws Exception
	 */
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
						}
				}
				if(fType ==1 ) {
					Main.memberSet.add(new Trainer(fId,fName,fSex,fAge,1));		
				}else if(fType==2) {
					Main.memberSet.add(new Trainee(fId,fName,fSex,fAge,2,fTrainer));
				}
			}
	
		} catch (FileNotFoundException e) {
			throw new Exception("회원 명단 불러오기를 실패하였습니다.");		
		}
	}
	
	/**
	 * 각 회원들의 워크아웃 파일을 읽어와 회원의 워크아웃리스트에 저장한다.
	 * @param file 회원아이디.csv 파일
	 * @throws Exception
	 */
	public static void readWorkout(File file) throws Exception {
		try {
			Scanner sc = new Scanner(file);
			String temp;
			String[] splited;
			
			int id = Integer.valueOf(sc.nextLine().split(",")[0]);
			int idx=-1;
			for(int i=0;i<Main.memberSet.size();i++) {
				if(id==Main.memberSet.get(i).getId()) {
					idx = i;
				}
			}
			while(sc.hasNextLine()) {
				WorkoutList tList;
				ArrayList<Exercise> exList=new ArrayList<>();
				splited = sc.nextLine().split(",");
				int exSize = Integer.valueOf(splited[0]);
				
				splited = sc.nextLine().split(",");
				Date date = new Date(Integer.valueOf(splited[0]),Integer.valueOf(splited[1]),Integer.valueOf(splited[2]));
			    //id회원의 workoutList의 workout 중 date가 같은 workout의 운동 리스트의 운동
				
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
				tList= new WorkoutList(date.getYear(), date.getMonth(), date.getDay());
				tList.setExerciseList(exList);
				((Trainee)Main.memberSet.get(idx)).loadWorkout(tList);
			}
		}catch(Exception e ) {
		}
	}
}
