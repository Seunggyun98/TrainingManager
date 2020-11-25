package GUITest;

import java.util.*;

class Register {
	
	public Register() {
		
	}
	public boolean register(){
		Scanner in = new Scanner(System.in);
		int check=1;
		int id=0; int age=0; String name=""; String sex="";int type=0;
		int myTrainer=0;int remainPT=0;
		while(check==1) {
			System.out.println("회원가입");
			System.out.print("사용할 id를 입력해주세요(4자리 숫자 : ");
			
			try{
				id = in.nextInt();
				if(id<=0||id>=10000) {
					throw new Exception();
				}
				else {
					boolean duplicateChk=duplicateCheck(id);
					if(duplicateChk==true) {
						System.out.println("이미 존재하는 아이디 입니다. 다시 시도해주세요.");
						register();
					}else {
						in.nextLine();
						System.out.print("이름을 입력해주세요 : ");
						name = in.nextLine();
						
						int sexSelect=0;
						while(sexSelect==0){
							System.out.println("성별을 선택해주세요  1. 남자\t2. 여자");
							sexSelect = in.nextInt();
							switch(sexSelect) {
							case 1:
								sex = "male";
								sexSelect = 1;
								break;
							case 2:
								sex = "female";
								sexSelect = 1;
								break;	
							default:
								System.out.println("다시 시도하세요.");
							}
						}
						int ageSelect = 0;
						while(ageSelect ==0) {
							try{
								System.out.print("나이를 입력해주세요 :");
								
								age = in.nextInt();
								if(age <=0 || age>100) {
									throw new Exception();
								}else {
									ageSelect=1;
								}
							}catch(InputMismatchException e) {
								System.out.println("나이는 숫자로 입력해주세요");
							}catch(Exception e) {
									System.out.println("나이는 1세 이상, 100세 이하 입니다. 다시 입력해주세요");
								}
						}
                        int typeSelect=0;
                        while(typeSelect==0){
                            try{
                                System.out.println("트레이너인지 회원인지 선택해주십시오(트레이너 : 1 / 회원 : 2) : ");
                                type=in.nextInt();
                                if(type!=1&&type!=2){
                                    throw new Exception();
                                }
                                else{
                                	if(type==2){   
                                		//트레이너 선택 -> 트레이너 리스트 출력 -> 원하는 트레이너 아이디 입력
                                            System.out.println("트레이너(아이디)를 선택해주세요. ");
                                            //System.out.println("size : "+Main.memberSet.size());
                                            for(Member m : Main.memberSet){
                                                if(m.getType()==1) {
                                                	System.out.println("트레이너 아이디 : "+m.getId()+" 이름 : "+m.getName());
                                                }
                                            }
                                            System.out.print("선택입력 : ");
                                            myTrainer = in.nextInt();
                                            int checkTrainer=0;
                                            for(Member m : Main.memberSet) {
                                            	if(m.getId()==myTrainer) {
                                            		checkTrainer=1;
                                            		break;
                                            	}
                                            }
                                            if(checkTrainer==0) {
                                            	throw new Exception("트레이너를 잘 못 선택하셨습니다. 다시 시도해주세요.");
                                            }
                                            System.out.print("신청하실 PT 횟수를 적어주세요 : ");
                                            remainPT = in.nextInt(); 
                                        }
                                    typeSelect=1;
                                }
                            }catch(InputMismatchException e) {
								System.out.println("잘 못된 입력입니다. 다시 입력해주세요");
                            }catch(Exception e){
                                System.out.println("잘못 된 입력입니다. 다시 입력해주세요");
                            }
                        }
						
						System.out.println("입력된 정보");
						
						System.out.printf("ID : %04d, 이름 : %s, 성별 : %s, 나이 : %d, 타입 : %d\n",id,name,sex,age,type);
						System.out.println("1. 입력된 정보로 가입\t2. 다시 입력");
						int checkSelect=0;
						while(checkSelect ==0) {
							try {
								switch(in.nextInt()) {
									case 1:
										checkSelect=1;
										check=0;
										return true;
									
									case 2:
										name="";sex="";age=0;id=0;type=0;
										checkSelect=1;
										System.out.println("새로운 정보로 회원가입을 다시 시도합니다.");
										in.nextLine();
										break;
									default:
										throw new InputMismatchException();
								}
							}
							catch(InputMismatchException e) {
								System.out.println("다시 입력해주세요");
							}
						}
					}
				}
				}
				catch(Exception registerErr) {
					System.out.println("id는 4자리 숫자입니다. 다시 시도해주세요");
					this.register();
				}
			}
		if(type ==1 ) {
			Member member = new Trainer(id,sex,name,age,type);
		}
        if(type==2) {
			Member member = new Trainee(id,sex,name,age,type,myTrainer,remainPT);
			matchTrainer(myTrainer,(Trainee)member);
        }
		Member member = new Member(id,sex,name,age,type);
		Main.memberSet.add(member);
		return true;

			
	}
	private boolean duplicateCheck(int id) {
		
		try {
		for(int i=0;i<Main.memberSet.size();i++) {
			int search = Main.memberSet.get(i).getId();
			if(id == search) {
				return true;
			}
		}
		}catch(NoSuchElementException e) {
			System.out.println("사용할 수 있는 아이디 입니다. 입력된 아이디로 회원가입을 합니다.");
			return false;
		}
		return false;
	}
	
	private void matchTrainer(int trainerID, Trainee trainee) {
		for(int i =0;i<Main.memberSet.size();i++) {
			if(Main.memberSet.get(i).getId() == trainerID) {
				((Trainer)Main.memberSet.get(i)).addTrainee(trainee);
			}
		}
	}
}
