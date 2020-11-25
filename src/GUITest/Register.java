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
			System.out.println("ȸ������");
			System.out.print("����� id�� �Է����ּ���(4�ڸ� ���� : ");
			
			try{
				id = in.nextInt();
				if(id<=0||id>=10000) {
					throw new Exception();
				}
				else {
					boolean duplicateChk=duplicateCheck(id);
					if(duplicateChk==true) {
						System.out.println("�̹� �����ϴ� ���̵� �Դϴ�. �ٽ� �õ����ּ���.");
						register();
					}else {
						in.nextLine();
						System.out.print("�̸��� �Է����ּ��� : ");
						name = in.nextLine();
						
						int sexSelect=0;
						while(sexSelect==0){
							System.out.println("������ �������ּ���  1. ����\t2. ����");
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
								System.out.println("�ٽ� �õ��ϼ���.");
							}
						}
						int ageSelect = 0;
						while(ageSelect ==0) {
							try{
								System.out.print("���̸� �Է����ּ��� :");
								
								age = in.nextInt();
								if(age <=0 || age>100) {
									throw new Exception();
								}else {
									ageSelect=1;
								}
							}catch(InputMismatchException e) {
								System.out.println("���̴� ���ڷ� �Է����ּ���");
							}catch(Exception e) {
									System.out.println("���̴� 1�� �̻�, 100�� ���� �Դϴ�. �ٽ� �Է����ּ���");
								}
						}
                        int typeSelect=0;
                        while(typeSelect==0){
                            try{
                                System.out.println("Ʈ���̳����� ȸ������ �������ֽʽÿ�(Ʈ���̳� : 1 / ȸ�� : 2) : ");
                                type=in.nextInt();
                                if(type!=1&&type!=2){
                                    throw new Exception();
                                }
                                else{
                                	if(type==2){   
                                		//Ʈ���̳� ���� -> Ʈ���̳� ����Ʈ ��� -> ���ϴ� Ʈ���̳� ���̵� �Է�
                                            System.out.println("Ʈ���̳�(���̵�)�� �������ּ���. ");
                                            //System.out.println("size : "+Main.memberSet.size());
                                            for(Member m : Main.memberSet){
                                                if(m.getType()==1) {
                                                	System.out.println("Ʈ���̳� ���̵� : "+m.getId()+" �̸� : "+m.getName());
                                                }
                                            }
                                            System.out.print("�����Է� : ");
                                            myTrainer = in.nextInt();
                                            int checkTrainer=0;
                                            for(Member m : Main.memberSet) {
                                            	if(m.getId()==myTrainer) {
                                            		checkTrainer=1;
                                            		break;
                                            	}
                                            }
                                            if(checkTrainer==0) {
                                            	throw new Exception("Ʈ���̳ʸ� �� �� �����ϼ̽��ϴ�. �ٽ� �õ����ּ���.");
                                            }
                                            System.out.print("��û�Ͻ� PT Ƚ���� �����ּ��� : ");
                                            remainPT = in.nextInt(); 
                                        }
                                    typeSelect=1;
                                }
                            }catch(InputMismatchException e) {
								System.out.println("�� ���� �Է��Դϴ�. �ٽ� �Է����ּ���");
                            }catch(Exception e){
                                System.out.println("�߸� �� �Է��Դϴ�. �ٽ� �Է����ּ���");
                            }
                        }
						
						System.out.println("�Էµ� ����");
						
						System.out.printf("ID : %04d, �̸� : %s, ���� : %s, ���� : %d, Ÿ�� : %d\n",id,name,sex,age,type);
						System.out.println("1. �Էµ� ������ ����\t2. �ٽ� �Է�");
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
										System.out.println("���ο� ������ ȸ�������� �ٽ� �õ��մϴ�.");
										in.nextLine();
										break;
									default:
										throw new InputMismatchException();
								}
							}
							catch(InputMismatchException e) {
								System.out.println("�ٽ� �Է����ּ���");
							}
						}
					}
				}
				}
				catch(Exception registerErr) {
					System.out.println("id�� 4�ڸ� �����Դϴ�. �ٽ� �õ����ּ���");
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
			System.out.println("����� �� �ִ� ���̵� �Դϴ�. �Էµ� ���̵�� ȸ�������� �մϴ�.");
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
