import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.border.EmptyBorder;




public class PlayPanel extends JPanel{
//게임화면 클래스
	private ImageIcon Hit = new ImageIcon("img/hit.png");

	//캐릭터 출력을 위한 변수{
	private Image [] charactersImage = new Image[6]; // 캐릭터들이 들어가는 Image배열
	private Image [] characterImage = new Image[7];  // 화면에 나오는 캐릭터 Imag배열
	private Rectangle[] character_position=new Rectangle[9]; // 캐릭터 x,y좌표 저장 Rectangle 배열
	private String[] stringsImage = {"","img/red_mario.png", "img/red_mushroom.png"
    						,"img/green_mario.png"	, "img/green_mushroom.png"
    						, "img/flower.png"};
    private int [] left=new int[7]; //화면에 나오는 캐릭터의 x
    private int [] top=new int[7]; //화면에 나오는 캐릭터의 y
    //}
    
	// 캐릭터 나오는 부분 버튼 {
    private JButton [] btn = new JButton[9];
	//}
    
	

    private int [] goal_score = new int[5];    //각 스테이지 목표 점수
    
    //배경화면을 위한 ImageIcon{
	private ImageIcon icon;// 각 화면을 받는 ImageIcon
	private ImageIcon game;//게임 화면
	private ImageIcon next;//다음 스테이지로 넘어가는 화면
	private ImageIcon lose;//게임 실패 시 화면
	private ImageIcon wait;//일시정지 화면
	//}
	
	// 각종 화면 구성하는 라벨들{
	private JLabel level; // 스테이지 출력
	private JLabel timer; // 시간 출력
	private JLabel score; // 점수 출력
	private JLabel combo; // 콤보 횟수 출력
	//}
	
	//각종 설정을 위한 변수들{
	private int set_time; //타이머 시간
	private int set_lv; // 스테이지
	private int delay; // 게임 속도
	private int max; // 화면에 나오는 캐릭터 최대 수
	private int cnt_score; //현재 점수
	private int combo_hit; //콤보 수
	private int total_score; // 최종 점수
	private int stopped=0;// 게임 진행 제어 변수
	
	
	int x = 0,y = 0; //x좌표,y좌표
	
	private Clicked clickL; //버튼 클릭 리스너
	
	//}
	
	//각종 버튼{
	private JButton confirm; // 다음 스테이지로 넘어가는 확인 버튼
	private JButton restart; // 게임 실패 시 재시작 버튼
	private JButton home; // 게임 실패 시 홈 버튼
	private JButton start; //일시정지 후 게임 시작 버튼
	private JButton pause_home; // 일시정지 시 홈 버튼
	private JButton pause;//일시정지 버튼

	//}
	
	
	
	private ImageIcon next_stageicon;//다음 스테이지 버튼 이미지
	private ImageIcon home_loseicon;//홈으로 돌아가기 버튼 이미지
	private ImageIcon restart_loseicon;//재시작 버튼 이미지
	private ImageIcon stop_icon;//일시정지 버튼 이미지
	private ImageIcon start_icon;//시작버튼
	private ImageIcon pause_home_icon; // 일시정지 시 홈 버튼 이미지
	
	
	private Sound_hit hit_music = new Sound_hit(); //때릴때 효과음
	
	
	private int [] prev_position = new int [9];//이전 위치 저장 배열
	private int [] prev_position_use = new int [9];//이전 위치와 비교할때 실제 사용할 배열
	private int prev_max;//이전 최대값
	
	public PlayPanel(CardLayout card,SoundPlayer music) {
		

		score = new JLabel();
		combo = new JLabel();
		level = new JLabel();
		timer = new JLabel();
		
		
		//패널 설정{
				setPreferredSize(new Dimension(600,800));
				setLayout(null);
				//}
				
				//각종 배경 설정{
				game = new ImageIcon("img/Game_background.png"); //게임 배경
				next = new ImageIcon("img/next_background.png"); //다음 스테이지 배경
				lose  =new ImageIcon("img/lose_background.png"); //패배 배경
				wait = new ImageIcon("img/pause_background.png");
				//}
				
				icon = game; //게임 배경으로 설정
		
		Init(music);//초기 설정
 
		
		//타이머
		timer.setText( Integer.toString(set_time));
		timer.setFont(new Font("Verdana",Font.BOLD, 40));
		timer.setForeground(Color.white);
		timer.setBounds(40,10,60,40);	
		add(timer);
		
		//스테이지
		level.setText("Lv: " + set_lv);
		level.setFont(new Font("Verdana",Font.BOLD, 40));
		level.setForeground(Color.white);
		level.setBounds(450,10,120,40);	
		add(level);

		//점수
		score.setText("Score: " + cnt_score);
		score.setFont(new Font("Verdana",Font.BOLD, 20));
		score.setForeground(Color.white);
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setBounds(0,80,600,20);	
		add(score);
		
		//콤보
		combo.setText("Combo: " + combo_hit);
		combo.setFont(new Font("Verdana",Font.BOLD, 20));
		combo.setHorizontalAlignment(SwingConstants.CENTER);
		combo.setForeground(Color.white);
		combo.setBounds(0,100,600,20);	
		add(combo);
		//}
		
		//캐릭터 좌표 저장하는 rectangle설정{
		for(int i=0;i<9;i++){
            character_position[i]=new Rectangle();
        }
		character_position[0].setRect(45,195,130,300);	// 1번 행 1번 
        character_position[1].setRect(245,195,350,300); // 1번 행 2번
        character_position[2].setRect(455,195,560,300);	// 1번 행 3번
        character_position[3].setRect(45,395,130,500);	// 2번 행 1번 
        character_position[4].setRect(250,395,330,500);	// 2번 행 2번
        character_position[5].setRect(455,395,530,500);	// 2번 행 3번
        character_position[6].setRect(45,600,130,700); 	// 3번 행 1번
        character_position[7].setRect(250,600,330,700); // 3번 행 2번
        character_position[8].setRect(455,600,530,700);	// 3번 행 3번
        //}
   
   	for(int i=0;i<6;i++) {//charactersImage배열에 캐릭터 이미지 저장
   		charactersImage[i] = Toolkit.getDefaultToolkit().getImage(stringsImage[i]);
   	}
 
   	clickL = new Clicked();//클릭 리스너 추가
   	
   	//캐릭터 버튼 추가
   	for(int i=0;i<9;i++) {
   		btn[i] = new JButton();
   		btn[i].setBorderPainted(false); 
   		btn[i].setFocusPainted(false); 
   		btn[i].setContentAreaFilled(false);
   		btn[i].addMouseListener(clickL);
   		add(btn[i]);
   	}
   	
   		//캐릭터 버튼 위치 설정{
   		btn[0].setBounds(45,195,100,100); 	//1번 행 1번
   		btn[1].setBounds(245,195,100,100); 	//1번 행 2번
   		btn[2].setBounds(455,195,100,100);	//1번 행 3번
   		btn[3].setBounds(45,395,100,100);		//2번 행 1번 
   		btn[4].setBounds(250,395,100,100);	//2번 행 2번
   		btn[5].setBounds(455,395,100,100);	//2번 행 3번
   		btn[6].setBounds(45,600,100,100); 	//3번 행 1번
   		btn[7].setBounds(250,600,100,100); 	//3번 행 2번
   		btn[8].setBounds(455,600,100,100);	//3번 행 3번
   	
		
		//showcharacter();
		
		
		//버튼 이미지 설정
		next_stageicon= new ImageIcon("img/next_stage_play_button.png");
		home_loseicon= new ImageIcon("img/main_lose.png");
		restart_loseicon= new ImageIcon("img/regame_lose.png");
		stop_icon= new ImageIcon("img/stop.png");
		start_icon = new ImageIcon("img/start_wait.png");
		pause_home_icon = new ImageIcon("img/home_wait.png");
		//확인 버튼 설정(버튼 활성화X)
		confirm=new JButton(next_stageicon);
		confirm.setBounds(193, 438, 216, 143);
		confirm.setVisible(false);
		confirm.setBackground(Color.WHITE);
		confirm.setEnabled(false);
		confirm.setBorderPainted(false); 
		confirm.setFocusPainted(false); 
		confirm.setContentAreaFilled(false);
		add(confirm);
		
	
		//재시작 버튼 설정(버튼 활성화X)
		restart=new JButton(restart_loseicon);
		restart.setBounds(324, 367, 156, 207);
		restart.setVisible(false);
		restart.setBackground(Color.WHITE);
		restart.setEnabled(false);
		restart.setBorderPainted(false); 
		restart.setFocusPainted(false); 
		restart.setContentAreaFilled(false);
		add(restart);
			
		//홈 버튼 설정(버튼 활성화X)
		home=new JButton(home_loseicon);
		home.setBounds(148, 367, 111, 207);
		home.setVisible(false);
		home.setBorderPainted(false); 
		home.setFocusPainted(false); 
		home.setContentAreaFilled(false);
		home.setEnabled(false);
		home.setBackground(Color.WHITE);
		add(home);
		
		//시작버튼(버튼 활성화X)
		start=new JButton(start_icon);
		start.setBounds(348, 372, 124, 156);
		start.setVisible(false);
		start.setBackground(Color.WHITE);
		start.setEnabled(false);
		start.setBorderPainted(false); 
		start.setFocusPainted(false); 
		start.setContentAreaFilled(false);
		add(start);
		
		//일시 정지 버튼
   		pause = new JButton(stop_icon);
   		pause.setBounds(530,80,50,50);
   		pause.setBorderPainted(false); 
   		pause.setFocusPainted(false); 
   		pause.setContentAreaFilled(false);
   		add(pause);
   		
	
   		pause_home=new JButton(pause_home_icon);
   		pause_home.setBounds(132, 372, 122, 155);
   		pause_home.setVisible(false);
   		pause_home.setBackground(Color.WHITE);
   		pause_home.setEnabled(false);
   		pause_home.setBorderPainted(false); 
   		pause_home.setFocusPainted(false); 
   		pause_home.setContentAreaFilled(false);
		add(pause_home);
   		
		
		pause_home.addActionListener(new ActionListener(){
			//홈 버튼 클릭 시
			public void actionPerformed(ActionEvent e) {
				music.Stop();//음악 멈추기
				music.setMusic("sound/mainbgm.wav");//mainbgm재생
				card.show(getRootPane().getContentPane(),"1");
				getRootPane().repaint(); //초기화면으로 돌아감
			
				
            }
		});
		
   		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//시작 버튼 클릭시 
				
				//홈, 시작 버튼 비활성화
				pause_home.setEnabled(false);
				start.setEnabled(false);
				
				start.setVisible(false);
				pause_home.setVisible(false);
				
				for(int i=0;i<9;i++) {
					btn[i].setEnabled(true);
				}
				
				
				icon = game;//게임화면으로 변경

				stopped = 0;//게임 재개
				pause.setEnabled(true); //일시정지버튼 활성화
				music.waitStart();
				repaint();
				
            }
		});
   		pause.addActionListener(new ActionListener(){
			//일시정지 버튼 클릭 시
			public void actionPerformed(ActionEvent e) {
				music.waitStop();
				stopped = 1;//게임 멈추기
			
				//캐릭터 버튼 비활성화{
				for(int i=0;i<9;i++) {
					btn[i].setEnabled(false);
					btn[i].setIcon(null);
				}//}
		
				//캐릭터 출력 null{
				for(int i=0;i<max;i++)
				characterImage[i]=charactersImage[0];
				//	}
			
				icon=wait; // 대기 화면 전환
				pause.setEnabled(false); //일시정지버튼 비활성화
				pause_home.setEnabled(true); //홈버튼 활성화
				pause_home.setVisible(true); //홈 버튼 보이기
				start.setEnabled(true); //재시작 버튼 활성화
				start.setVisible(true); //재시작 버튼 보이기
			
				repaint();
            }
		});
   		

		home.addActionListener(new ActionListener(){
			//홈 버튼 클릭 시
			public void actionPerformed(ActionEvent e) {
				music.Stop();//음악 멈추기
				music.setMusic("sound/mainbgm.wav");//mainbgm재생
				card.show(getRootPane().getContentPane(),"1");
				getRootPane().repaint(); //초기화면으로 돌아감
			
				
            }
		});
		restart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//재시작 버튼 클릭시 
				//홈, 재시작 버튼 비활성화
				home.setEnabled(false);
				restart.setEnabled(false);
				
				restart.setVisible(false);
				home.setVisible(false);
				
				for(int i=0;i<9;i++) {
					btn[i].setEnabled(true);
				}
				
				
				icon = game;//게임화면으로 변경

				//게임 캐릭터 초기화{
				for(int i=0; i<7;i++) {
					characterImage[i]=charactersImage[0];
				}
				//}
				
				
				Init(music); //게임 세팅 값 초기화
		
				repaint();
				
            }
		});
		
		
		//타이머
		Timer m_timer = new Timer();
		TimerTask m_task = new TimerTask() {
			@Override
			public void run() {
				if(stopped!=1) {
					downtime();//타이머 시간 감소
					//각 캐릭터 버튼 활성화
					for(int i=0;i<9;i++) {
						btn[i].setIcon(null);
					}//}
	    			setImage();//캐릭터 출력
	    			
				}
				
				if(set_time <= 0) { // 타이머가 다 됬을시
					//캐릭터 버튼 비활성화{
					for(int i=0;i<9;i++) {
						btn[i].setEnabled(false);
						btn[i].setIcon(null);
					}//}
			
					
					//캐릭터 출력 null{
					for(int i=0;i<max;i++)
					characterImage[i]=charactersImage[0];
					//	}
				
					set_time = 60;	//타이머 초기화
					cnt_score += combo_hit*100;// 최종 점수 계산
					total_score+=cnt_score;		// 총 점수 계산
					
					music.Stop();//음악 멈추기
					
					stopped=1; // 타이머 스탑\
					 
					 if(cnt_score>=goal_score[set_lv-1]) {
						 
						 set_lv++;
						if(set_lv>5) {
							//Clear 시
							
							ClearPanel clear = new ClearPanel(card,total_score,music); //ClearPanel 생성
			                 getRootPane().getContentPane().add("5",clear);// card에 ClearPanel 삽입
							card.show(getRootPane().getContentPane(),"5"); //클리어화면으로 넘어감
							getRootPane().repaint(); //클리어화면으로 넘어감
						}
						else {
							//next stage
							music.setMusic("sound/stage_clearbgm.wav");//스테이지 클리어bgm 재생
							//캐릭터 버튼 비활성화{
							for(int i=0;i<9;i++) {
								btn[i].setEnabled(false);
								btn[i].setIcon(null);
							}//}
							//// 다음 스테이지 출력 라벨 선언 및 설정{
							JLabel chapter = new JLabel(""+set_lv); 
							chapter.setBounds(370, 310, 40, 40);
							chapter.setFont(new Font("Verdana",Font.BOLD, 40));
							chapter.setForeground(Color.white);
							add(chapter);
							//}
							
							icon=next; //배경화면 다음 스테이지 넘어가느 화면으로 전환
							
							repaint();
							confirm.setEnabled(true);//다음 스테이지 넘어가는 확인 버튼 활성화
							confirm.setVisible(true);
							
							confirm.addActionListener(new ActionListener(){
								//다음 스테이지 확인 버튼 누를 시
								public void actionPerformed(ActionEvent e) {
									chapter.setVisible(false);//다음 스테이지 출력 라벨 false
									confirm.setEnabled(false);//다음 스테이지 확인 버튼 false
									confirm.setVisible(false);
									icon = game; // 게임화면으로 전환
									
									delay=delay-100;//게임 속도 조절
									
									cnt_score = 0; //점수 초기화
									combo_hit = 0; //콤보 수 초기화 
								
								 	score.setText("Score: " + cnt_score);//보여지는 점수 초기화된 값으로 출력
									combo.setText("Combo: " + combo_hit);//보여지는 콤보 초기화된 값으로 출력
									level.setText("Lv: " + set_lv);//보여지는 레벨 현재 레벨로 출력

									//버튼 활성화
									for(int i=0;i<9;i++) {
										btn[i].setEnabled(true);
									}//}
									
									
									music.Stop();
									music.setMusic("sound/gamebgm.wav");//게임bgm재생
									
									repaint();
									stopped=0; // 재실행
					       
					            }
							});
						}
						
					}
					else if(cnt_score<goal_score[set_lv-1]) {
						//lose

						music.setMusic("sound/failbgm.wav");//게임실패bgm 재생

						icon=lose; // 게임 실패 화면 전환
						
						home.setEnabled(true); //홈버튼 활성화
						home.setVisible(true); //홈 버튼 보이기
						restart.setEnabled(true); //재시작 버튼 활성화
						restart.setVisible(true); //재시작 버튼 보이기
					
						repaint();
					
					}
				}
				
			}
		};
		m_timer.schedule(m_task, 10, delay);//delay마다 반복

	}

	
	public void setImage(){
	    //랜덤 캐릭터 이미지
		
		
		
		for(int i=0; i<prev_max; i++) { // 표출 초기화 이전 위치 정보 저장
			characterImage[i]=charactersImage[0];//캐릭터 이미지 초기화
		}
		
		for(int i=0; i<9; i++) { // 표출 초기화 이전 위치 정보 저장
			prev_position_use[i] = prev_position[i]; //이전 위치 정보를 실제 사용할 배열에 저장
			prev_position[i] = 0; //위치 정보 초기화
		}
		
		
		if(set_lv==1)max=1; //스테이지 1 캐릭터 최대 수 
		if(set_lv==2)max=2; //스테이지 2 캐릭터 최대 수
		if(set_lv==3)max=3; //스테이지 3 캐릭터 최대 수 
		if(set_lv==4)max=4; //스테이지 4 캐릭터 최대 수
		if(set_lv==5)max=7; //스테이지 5 캐릭터 최대 수
		int no=0;//어떤 캐릭터를 표출할지 결정하는 변수
		
		
		
		int [] n= new int[max];
		
		for(int i=0;i<max;i++) { //최대수 만큼 랜덤으로 캐릭터 설정
			//각 스테이지 마다 나오는 캐릭터 설정{
		    if(set_lv==1) {no=(int)(Math.random()*2)+1;} // 스테이지 1 빨간 마리오/빨간 버섯
		    else if(set_lv==2) {no=(int)(Math.random()*4);} //스테이지2 빨간 마리오/빨간버섯/녹색버섯
		    else if(set_lv==3) {no=(int)(Math.random()*5);} //꽃을 제외한 모든 캐릭터
		    else if(set_lv==4) {no=(int)(Math.random()*6);} //모든 캐릭터
		    else if(set_lv==5) {no=(int)(Math.random()*6);} //모든 캐릭터
		     //}
		    while(true) {//캐릭터 좌표 겹치지 않게 설정 
		    	int check_overlap=0;
		    	n[i]=(int)(Math.random()*9); //캐릭터가 표출될 위치 랜덤으로 생성
		    for(int j=0;j<i;j++) {//그 이전과 겹치는가 확인
		    	if(n[j]==n[i]) {
		    		check_overlap=1;
		    	}
		    }
		    if(check_overlap==0) break;//이전에 나온 값들과 겹치지 않으면 break
		    }
		  
		    
		    if(prev_position_use[n[i]]!=1) {//이전에 나왔던 위치가 아닐때 캐릭터 표출
		    characterImage[i]=charactersImage[no];  //게임 캐릭터 랜덤으로 배치
		    left[i]=(int) character_position[n[i]].getX(); //캐릭터x값 설정
		    top[i]=(int) character_position[n[i]].getY(); //캐릭터y값 설정

		   if(no != 0 ) prev_position[n[i]] = 1;
		    
		    }
		}
		prev_max = max;
		repaint();

	}


	public void downtime() {//타이머 감소
		set_time--;
		timer.setText(Integer.toString(set_time));//보여주는 타이머 값 변경
	}

	
	public void Init(SoundPlayer music) {//게임 초기 설정
		
		music.Stop();//음악 멈추기
		music.setMusic("sound/gamebgm.wav");//게임bgm 재생

		//각종 값 설정{
		stopped = 0;
		set_time = 60;
		set_lv = 1;
		delay=1000;
		max=1;
		cnt_score=0;
		combo_hit=0;
		total_score=0;
		//}
		
		//스테이지 별 목표 점수 설정{
		goal_score[0] = 100;//스테이지1
		goal_score[1] = 100;//스테이지2
		goal_score[2] = 100;//스테이지3
		goal_score[3] = 100;//스테이지4
		goal_score[4] = 100;//스테이지5
		//}
		
		//보여지는 label 설정{
		
	
   		
   		
	}
   		

	public void paintComponent(Graphics page) {
		//각종 이미지 그림
	super.paintComponent(page);
	page.drawImage(icon.getImage(),0,0,this);
	page.drawImage(characterImage[0],left[0],top[0],100,100,this);
	page.drawImage(characterImage[1],left[1],top[1],100,100,this);
	page.drawImage(characterImage[2],left[2],top[2],100,100,this);
	page.drawImage(characterImage[3],left[3],top[3],100,100,this);
	page.drawImage(characterImage[4],left[4],top[4],100,100,this);
	page.drawImage(characterImage[5],left[5],top[5],100,100,this);
	page.drawImage(characterImage[6],left[6],top[6],100,100,this);
	}
	
			
			public void cal_score(int i) {  
				// 눌린 버튼에 해당하는 캐릭터 이미지 구별 후 
				// 해당 캐릭터 콤보++/점수++/시간--
							
				hit_music.startmusic();//때릴때 효과음 재생
				
			if(characterImage[i]==charactersImage[1]) {
				//빨간 마리오
				combo_hit+=1; //콤보+1
				cnt_score+=100;//점수+100
				
			}
			if(characterImage[i]==charactersImage[2]) {
				//빨간 버섯
				combo_hit+=3;//콤보+3
				cnt_score+=200;//점수+200
			}
			if(characterImage[i]==charactersImage[3]) {
				//초록 마리오
				combo_hit+=2;//콤보+2
				cnt_score+=150;//점수+150
			}
			if(characterImage[i]==charactersImage[4]) {
				//초록 버섯
				combo_hit+=5;//콤보+5
				cnt_score+=500;//점수+500
			}
			if(characterImage[i]==charactersImage[5]) {
				// 꽃
				combo_hit=0; //콤보 초기화
				set_time-=5;  // time -5
				if(set_time<0) set_time = 0; // 타임이 0보다 낮을시 0으로
				timer.setText(Integer.toString(set_time));//보여지는 타임 값 변경
			}
			characterImage[i] = charactersImage[0]; // 눌린 이미지 공백으로 초기화
			combo.setText("Combo: " + combo_hit);//보여지는 콤보 값 변경
			score.setText("Score: " + cnt_score);//보여지는 점수 값 변경
			repaint(); 
			}
			//}

			
			private class Clicked implements MouseListener{ //캐릭터 클릭 리스너
				public void mouseClicked(MouseEvent event) {}


				public void mousePressed(MouseEvent event) {
					JButton obj = (JButton)event.getSource(); //클릭된 버튼 정보 obj로 받기
					obj.setEnabled(false); // 클릭된 버튼 비활성화
					//new Thread() {
						//public void run() {
					int i;
					for(i=0;i<max;i++) {
						if(left[i]==obj.getX()&&top[i]==obj.getY()) break; // 클릭된 위치 좌표 찾기
					}
					
					if(characterImage[i]==charactersImage[0]) { // 위치의 캐릭터가 null일 경우
						// 공백
						combo_hit=0; // 콤보 초기화 
						combo.setText("Combo: " + combo_hit);//보여지는 콤보 값 변경
						
					}
					else { // null이 아닐 경우
					obj.setIcon(Hit); // 위치에 hit이미지 표시
					cal_score(i); // 점수 계산 
					}
					obj.setEnabled(true); // 클린된 버튼 활성화
					//}}.start();	
					
				}
				public void mouseReleased(MouseEvent event) {}
				public void mouseEntered(MouseEvent event) {}
				public void mouseExited(MouseEvent event) {}
				
				
			}
			
			
			


		
			
		
	
			
}