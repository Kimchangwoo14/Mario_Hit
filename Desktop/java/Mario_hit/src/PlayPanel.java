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
//����ȭ�� Ŭ����
	private ImageIcon Hit = new ImageIcon("img/hit.png");

	//ĳ���� ����� ���� ����{
	private Image [] charactersImage = new Image[6]; // ĳ���͵��� ���� Image�迭
	private Image [] characterImage = new Image[7];  // ȭ�鿡 ������ ĳ���� Imag�迭
	private Rectangle[] character_position=new Rectangle[9]; // ĳ���� x,y��ǥ ���� Rectangle �迭
	private String[] stringsImage = {"","img/red_mario.png", "img/red_mushroom.png"
    						,"img/green_mario.png"	, "img/green_mushroom.png"
    						, "img/flower.png"};
    private int [] left=new int[7]; //ȭ�鿡 ������ ĳ������ x
    private int [] top=new int[7]; //ȭ�鿡 ������ ĳ������ y
    //}
    
	// ĳ���� ������ �κ� ��ư {
    private JButton [] btn = new JButton[9];
	//}
    
	

    private int [] goal_score = new int[5];    //�� �������� ��ǥ ����
    
    //���ȭ���� ���� ImageIcon{
	private ImageIcon icon;// �� ȭ���� �޴� ImageIcon
	private ImageIcon game;//���� ȭ��
	private ImageIcon next;//���� ���������� �Ѿ�� ȭ��
	private ImageIcon lose;//���� ���� �� ȭ��
	private ImageIcon wait;//�Ͻ����� ȭ��
	//}
	
	// ���� ȭ�� �����ϴ� �󺧵�{
	private JLabel level; // �������� ���
	private JLabel timer; // �ð� ���
	private JLabel score; // ���� ���
	private JLabel combo; // �޺� Ƚ�� ���
	//}
	
	//���� ������ ���� ������{
	private int set_time; //Ÿ�̸� �ð�
	private int set_lv; // ��������
	private int delay; // ���� �ӵ�
	private int max; // ȭ�鿡 ������ ĳ���� �ִ� ��
	private int cnt_score; //���� ����
	private int combo_hit; //�޺� ��
	private int total_score; // ���� ����
	private int stopped=0;// ���� ���� ���� ����
	
	
	int x = 0,y = 0; //x��ǥ,y��ǥ
	
	private Clicked clickL; //��ư Ŭ�� ������
	
	//}
	
	//���� ��ư{
	private JButton confirm; // ���� ���������� �Ѿ�� Ȯ�� ��ư
	private JButton restart; // ���� ���� �� ����� ��ư
	private JButton home; // ���� ���� �� Ȩ ��ư
	private JButton start; //�Ͻ����� �� ���� ���� ��ư
	private JButton pause_home; // �Ͻ����� �� Ȩ ��ư
	private JButton pause;//�Ͻ����� ��ư

	//}
	
	
	
	private ImageIcon next_stageicon;//���� �������� ��ư �̹���
	private ImageIcon home_loseicon;//Ȩ���� ���ư��� ��ư �̹���
	private ImageIcon restart_loseicon;//����� ��ư �̹���
	private ImageIcon stop_icon;//�Ͻ����� ��ư �̹���
	private ImageIcon start_icon;//���۹�ư
	private ImageIcon pause_home_icon; // �Ͻ����� �� Ȩ ��ư �̹���
	
	
	private Sound_hit hit_music = new Sound_hit(); //������ ȿ����
	
	
	private int [] prev_position = new int [9];//���� ��ġ ���� �迭
	private int [] prev_position_use = new int [9];//���� ��ġ�� ���Ҷ� ���� ����� �迭
	private int prev_max;//���� �ִ밪
	
	public PlayPanel(CardLayout card,SoundPlayer music) {
		

		score = new JLabel();
		combo = new JLabel();
		level = new JLabel();
		timer = new JLabel();
		
		
		//�г� ����{
				setPreferredSize(new Dimension(600,800));
				setLayout(null);
				//}
				
				//���� ��� ����{
				game = new ImageIcon("img/Game_background.png"); //���� ���
				next = new ImageIcon("img/next_background.png"); //���� �������� ���
				lose  =new ImageIcon("img/lose_background.png"); //�й� ���
				wait = new ImageIcon("img/pause_background.png");
				//}
				
				icon = game; //���� ������� ����
		
		Init(music);//�ʱ� ����
 
		
		//Ÿ�̸�
		timer.setText( Integer.toString(set_time));
		timer.setFont(new Font("Verdana",Font.BOLD, 40));
		timer.setForeground(Color.white);
		timer.setBounds(40,10,60,40);	
		add(timer);
		
		//��������
		level.setText("Lv: " + set_lv);
		level.setFont(new Font("Verdana",Font.BOLD, 40));
		level.setForeground(Color.white);
		level.setBounds(450,10,120,40);	
		add(level);

		//����
		score.setText("Score: " + cnt_score);
		score.setFont(new Font("Verdana",Font.BOLD, 20));
		score.setForeground(Color.white);
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setBounds(0,80,600,20);	
		add(score);
		
		//�޺�
		combo.setText("Combo: " + combo_hit);
		combo.setFont(new Font("Verdana",Font.BOLD, 20));
		combo.setHorizontalAlignment(SwingConstants.CENTER);
		combo.setForeground(Color.white);
		combo.setBounds(0,100,600,20);	
		add(combo);
		//}
		
		//ĳ���� ��ǥ �����ϴ� rectangle����{
		for(int i=0;i<9;i++){
            character_position[i]=new Rectangle();
        }
		character_position[0].setRect(45,195,130,300);	// 1�� �� 1�� 
        character_position[1].setRect(245,195,350,300); // 1�� �� 2��
        character_position[2].setRect(455,195,560,300);	// 1�� �� 3��
        character_position[3].setRect(45,395,130,500);	// 2�� �� 1�� 
        character_position[4].setRect(250,395,330,500);	// 2�� �� 2��
        character_position[5].setRect(455,395,530,500);	// 2�� �� 3��
        character_position[6].setRect(45,600,130,700); 	// 3�� �� 1��
        character_position[7].setRect(250,600,330,700); // 3�� �� 2��
        character_position[8].setRect(455,600,530,700);	// 3�� �� 3��
        //}
   
   	for(int i=0;i<6;i++) {//charactersImage�迭�� ĳ���� �̹��� ����
   		charactersImage[i] = Toolkit.getDefaultToolkit().getImage(stringsImage[i]);
   	}
 
   	clickL = new Clicked();//Ŭ�� ������ �߰�
   	
   	//ĳ���� ��ư �߰�
   	for(int i=0;i<9;i++) {
   		btn[i] = new JButton();
   		btn[i].setBorderPainted(false); 
   		btn[i].setFocusPainted(false); 
   		btn[i].setContentAreaFilled(false);
   		btn[i].addMouseListener(clickL);
   		add(btn[i]);
   	}
   	
   		//ĳ���� ��ư ��ġ ����{
   		btn[0].setBounds(45,195,100,100); 	//1�� �� 1��
   		btn[1].setBounds(245,195,100,100); 	//1�� �� 2��
   		btn[2].setBounds(455,195,100,100);	//1�� �� 3��
   		btn[3].setBounds(45,395,100,100);		//2�� �� 1�� 
   		btn[4].setBounds(250,395,100,100);	//2�� �� 2��
   		btn[5].setBounds(455,395,100,100);	//2�� �� 3��
   		btn[6].setBounds(45,600,100,100); 	//3�� �� 1��
   		btn[7].setBounds(250,600,100,100); 	//3�� �� 2��
   		btn[8].setBounds(455,600,100,100);	//3�� �� 3��
   	
		
		//showcharacter();
		
		
		//��ư �̹��� ����
		next_stageicon= new ImageIcon("img/next_stage_play_button.png");
		home_loseicon= new ImageIcon("img/main_lose.png");
		restart_loseicon= new ImageIcon("img/regame_lose.png");
		stop_icon= new ImageIcon("img/stop.png");
		start_icon = new ImageIcon("img/start_wait.png");
		pause_home_icon = new ImageIcon("img/home_wait.png");
		//Ȯ�� ��ư ����(��ư Ȱ��ȭX)
		confirm=new JButton(next_stageicon);
		confirm.setBounds(193, 438, 216, 143);
		confirm.setVisible(false);
		confirm.setBackground(Color.WHITE);
		confirm.setEnabled(false);
		confirm.setBorderPainted(false); 
		confirm.setFocusPainted(false); 
		confirm.setContentAreaFilled(false);
		add(confirm);
		
	
		//����� ��ư ����(��ư Ȱ��ȭX)
		restart=new JButton(restart_loseicon);
		restart.setBounds(324, 367, 156, 207);
		restart.setVisible(false);
		restart.setBackground(Color.WHITE);
		restart.setEnabled(false);
		restart.setBorderPainted(false); 
		restart.setFocusPainted(false); 
		restart.setContentAreaFilled(false);
		add(restart);
			
		//Ȩ ��ư ����(��ư Ȱ��ȭX)
		home=new JButton(home_loseicon);
		home.setBounds(148, 367, 111, 207);
		home.setVisible(false);
		home.setBorderPainted(false); 
		home.setFocusPainted(false); 
		home.setContentAreaFilled(false);
		home.setEnabled(false);
		home.setBackground(Color.WHITE);
		add(home);
		
		//���۹�ư(��ư Ȱ��ȭX)
		start=new JButton(start_icon);
		start.setBounds(348, 372, 124, 156);
		start.setVisible(false);
		start.setBackground(Color.WHITE);
		start.setEnabled(false);
		start.setBorderPainted(false); 
		start.setFocusPainted(false); 
		start.setContentAreaFilled(false);
		add(start);
		
		//�Ͻ� ���� ��ư
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
			//Ȩ ��ư Ŭ�� ��
			public void actionPerformed(ActionEvent e) {
				music.Stop();//���� ���߱�
				music.setMusic("sound/mainbgm.wav");//mainbgm���
				card.show(getRootPane().getContentPane(),"1");
				getRootPane().repaint(); //�ʱ�ȭ������ ���ư�
			
				
            }
		});
		
   		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//���� ��ư Ŭ���� 
				
				//Ȩ, ���� ��ư ��Ȱ��ȭ
				pause_home.setEnabled(false);
				start.setEnabled(false);
				
				start.setVisible(false);
				pause_home.setVisible(false);
				
				for(int i=0;i<9;i++) {
					btn[i].setEnabled(true);
				}
				
				
				icon = game;//����ȭ������ ����

				stopped = 0;//���� �簳
				pause.setEnabled(true); //�Ͻ�������ư Ȱ��ȭ
				music.waitStart();
				repaint();
				
            }
		});
   		pause.addActionListener(new ActionListener(){
			//�Ͻ����� ��ư Ŭ�� ��
			public void actionPerformed(ActionEvent e) {
				music.waitStop();
				stopped = 1;//���� ���߱�
			
				//ĳ���� ��ư ��Ȱ��ȭ{
				for(int i=0;i<9;i++) {
					btn[i].setEnabled(false);
					btn[i].setIcon(null);
				}//}
		
				//ĳ���� ��� null{
				for(int i=0;i<max;i++)
				characterImage[i]=charactersImage[0];
				//	}
			
				icon=wait; // ��� ȭ�� ��ȯ
				pause.setEnabled(false); //�Ͻ�������ư ��Ȱ��ȭ
				pause_home.setEnabled(true); //Ȩ��ư Ȱ��ȭ
				pause_home.setVisible(true); //Ȩ ��ư ���̱�
				start.setEnabled(true); //����� ��ư Ȱ��ȭ
				start.setVisible(true); //����� ��ư ���̱�
			
				repaint();
            }
		});
   		

		home.addActionListener(new ActionListener(){
			//Ȩ ��ư Ŭ�� ��
			public void actionPerformed(ActionEvent e) {
				music.Stop();//���� ���߱�
				music.setMusic("sound/mainbgm.wav");//mainbgm���
				card.show(getRootPane().getContentPane(),"1");
				getRootPane().repaint(); //�ʱ�ȭ������ ���ư�
			
				
            }
		});
		restart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//����� ��ư Ŭ���� 
				//Ȩ, ����� ��ư ��Ȱ��ȭ
				home.setEnabled(false);
				restart.setEnabled(false);
				
				restart.setVisible(false);
				home.setVisible(false);
				
				for(int i=0;i<9;i++) {
					btn[i].setEnabled(true);
				}
				
				
				icon = game;//����ȭ������ ����

				//���� ĳ���� �ʱ�ȭ{
				for(int i=0; i<7;i++) {
					characterImage[i]=charactersImage[0];
				}
				//}
				
				
				Init(music); //���� ���� �� �ʱ�ȭ
		
				repaint();
				
            }
		});
		
		
		//Ÿ�̸�
		Timer m_timer = new Timer();
		TimerTask m_task = new TimerTask() {
			@Override
			public void run() {
				if(stopped!=1) {
					downtime();//Ÿ�̸� �ð� ����
					//�� ĳ���� ��ư Ȱ��ȭ
					for(int i=0;i<9;i++) {
						btn[i].setIcon(null);
					}//}
	    			setImage();//ĳ���� ���
	    			
				}
				
				if(set_time <= 0) { // Ÿ�̸Ӱ� �� ������
					//ĳ���� ��ư ��Ȱ��ȭ{
					for(int i=0;i<9;i++) {
						btn[i].setEnabled(false);
						btn[i].setIcon(null);
					}//}
			
					
					//ĳ���� ��� null{
					for(int i=0;i<max;i++)
					characterImage[i]=charactersImage[0];
					//	}
				
					set_time = 60;	//Ÿ�̸� �ʱ�ȭ
					cnt_score += combo_hit*100;// ���� ���� ���
					total_score+=cnt_score;		// �� ���� ���
					
					music.Stop();//���� ���߱�
					
					stopped=1; // Ÿ�̸� ��ž\
					 
					 if(cnt_score>=goal_score[set_lv-1]) {
						 
						 set_lv++;
						if(set_lv>5) {
							//Clear ��
							
							ClearPanel clear = new ClearPanel(card,total_score,music); //ClearPanel ����
			                 getRootPane().getContentPane().add("5",clear);// card�� ClearPanel ����
							card.show(getRootPane().getContentPane(),"5"); //Ŭ����ȭ������ �Ѿ
							getRootPane().repaint(); //Ŭ����ȭ������ �Ѿ
						}
						else {
							//next stage
							music.setMusic("sound/stage_clearbgm.wav");//�������� Ŭ����bgm ���
							//ĳ���� ��ư ��Ȱ��ȭ{
							for(int i=0;i<9;i++) {
								btn[i].setEnabled(false);
								btn[i].setIcon(null);
							}//}
							//// ���� �������� ��� �� ���� �� ����{
							JLabel chapter = new JLabel(""+set_lv); 
							chapter.setBounds(370, 310, 40, 40);
							chapter.setFont(new Font("Verdana",Font.BOLD, 40));
							chapter.setForeground(Color.white);
							add(chapter);
							//}
							
							icon=next; //���ȭ�� ���� �������� �Ѿ�� ȭ������ ��ȯ
							
							repaint();
							confirm.setEnabled(true);//���� �������� �Ѿ�� Ȯ�� ��ư Ȱ��ȭ
							confirm.setVisible(true);
							
							confirm.addActionListener(new ActionListener(){
								//���� �������� Ȯ�� ��ư ���� ��
								public void actionPerformed(ActionEvent e) {
									chapter.setVisible(false);//���� �������� ��� �� false
									confirm.setEnabled(false);//���� �������� Ȯ�� ��ư false
									confirm.setVisible(false);
									icon = game; // ����ȭ������ ��ȯ
									
									delay=delay-100;//���� �ӵ� ����
									
									cnt_score = 0; //���� �ʱ�ȭ
									combo_hit = 0; //�޺� �� �ʱ�ȭ 
								
								 	score.setText("Score: " + cnt_score);//�������� ���� �ʱ�ȭ�� ������ ���
									combo.setText("Combo: " + combo_hit);//�������� �޺� �ʱ�ȭ�� ������ ���
									level.setText("Lv: " + set_lv);//�������� ���� ���� ������ ���

									//��ư Ȱ��ȭ
									for(int i=0;i<9;i++) {
										btn[i].setEnabled(true);
									}//}
									
									
									music.Stop();
									music.setMusic("sound/gamebgm.wav");//����bgm���
									
									repaint();
									stopped=0; // �����
					       
					            }
							});
						}
						
					}
					else if(cnt_score<goal_score[set_lv-1]) {
						//lose

						music.setMusic("sound/failbgm.wav");//���ӽ���bgm ���

						icon=lose; // ���� ���� ȭ�� ��ȯ
						
						home.setEnabled(true); //Ȩ��ư Ȱ��ȭ
						home.setVisible(true); //Ȩ ��ư ���̱�
						restart.setEnabled(true); //����� ��ư Ȱ��ȭ
						restart.setVisible(true); //����� ��ư ���̱�
					
						repaint();
					
					}
				}
				
			}
		};
		m_timer.schedule(m_task, 10, delay);//delay���� �ݺ�

	}

	
	public void setImage(){
	    //���� ĳ���� �̹���
		
		
		
		for(int i=0; i<prev_max; i++) { // ǥ�� �ʱ�ȭ ���� ��ġ ���� ����
			characterImage[i]=charactersImage[0];//ĳ���� �̹��� �ʱ�ȭ
		}
		
		for(int i=0; i<9; i++) { // ǥ�� �ʱ�ȭ ���� ��ġ ���� ����
			prev_position_use[i] = prev_position[i]; //���� ��ġ ������ ���� ����� �迭�� ����
			prev_position[i] = 0; //��ġ ���� �ʱ�ȭ
		}
		
		
		if(set_lv==1)max=1; //�������� 1 ĳ���� �ִ� �� 
		if(set_lv==2)max=2; //�������� 2 ĳ���� �ִ� ��
		if(set_lv==3)max=3; //�������� 3 ĳ���� �ִ� �� 
		if(set_lv==4)max=4; //�������� 4 ĳ���� �ִ� ��
		if(set_lv==5)max=7; //�������� 5 ĳ���� �ִ� ��
		int no=0;//� ĳ���͸� ǥ������ �����ϴ� ����
		
		
		
		int [] n= new int[max];
		
		for(int i=0;i<max;i++) { //�ִ�� ��ŭ �������� ĳ���� ����
			//�� �������� ���� ������ ĳ���� ����{
		    if(set_lv==1) {no=(int)(Math.random()*2)+1;} // �������� 1 ���� ������/���� ����
		    else if(set_lv==2) {no=(int)(Math.random()*4);} //��������2 ���� ������/��������/�������
		    else if(set_lv==3) {no=(int)(Math.random()*5);} //���� ������ ��� ĳ����
		    else if(set_lv==4) {no=(int)(Math.random()*6);} //��� ĳ����
		    else if(set_lv==5) {no=(int)(Math.random()*6);} //��� ĳ����
		     //}
		    while(true) {//ĳ���� ��ǥ ��ġ�� �ʰ� ���� 
		    	int check_overlap=0;
		    	n[i]=(int)(Math.random()*9); //ĳ���Ͱ� ǥ��� ��ġ �������� ����
		    for(int j=0;j<i;j++) {//�� ������ ��ġ�°� Ȯ��
		    	if(n[j]==n[i]) {
		    		check_overlap=1;
		    	}
		    }
		    if(check_overlap==0) break;//������ ���� ����� ��ġ�� ������ break
		    }
		  
		    
		    if(prev_position_use[n[i]]!=1) {//������ ���Դ� ��ġ�� �ƴҶ� ĳ���� ǥ��
		    characterImage[i]=charactersImage[no];  //���� ĳ���� �������� ��ġ
		    left[i]=(int) character_position[n[i]].getX(); //ĳ����x�� ����
		    top[i]=(int) character_position[n[i]].getY(); //ĳ����y�� ����

		   if(no != 0 ) prev_position[n[i]] = 1;
		    
		    }
		}
		prev_max = max;
		repaint();

	}


	public void downtime() {//Ÿ�̸� ����
		set_time--;
		timer.setText(Integer.toString(set_time));//�����ִ� Ÿ�̸� �� ����
	}

	
	public void Init(SoundPlayer music) {//���� �ʱ� ����
		
		music.Stop();//���� ���߱�
		music.setMusic("sound/gamebgm.wav");//����bgm ���

		//���� �� ����{
		stopped = 0;
		set_time = 60;
		set_lv = 1;
		delay=1000;
		max=1;
		cnt_score=0;
		combo_hit=0;
		total_score=0;
		//}
		
		//�������� �� ��ǥ ���� ����{
		goal_score[0] = 100;//��������1
		goal_score[1] = 100;//��������2
		goal_score[2] = 100;//��������3
		goal_score[3] = 100;//��������4
		goal_score[4] = 100;//��������5
		//}
		
		//�������� label ����{
		
	
   		
   		
	}
   		

	public void paintComponent(Graphics page) {
		//���� �̹��� �׸�
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
				// ���� ��ư�� �ش��ϴ� ĳ���� �̹��� ���� �� 
				// �ش� ĳ���� �޺�++/����++/�ð�--
							
				hit_music.startmusic();//������ ȿ���� ���
				
			if(characterImage[i]==charactersImage[1]) {
				//���� ������
				combo_hit+=1; //�޺�+1
				cnt_score+=100;//����+100
				
			}
			if(characterImage[i]==charactersImage[2]) {
				//���� ����
				combo_hit+=3;//�޺�+3
				cnt_score+=200;//����+200
			}
			if(characterImage[i]==charactersImage[3]) {
				//�ʷ� ������
				combo_hit+=2;//�޺�+2
				cnt_score+=150;//����+150
			}
			if(characterImage[i]==charactersImage[4]) {
				//�ʷ� ����
				combo_hit+=5;//�޺�+5
				cnt_score+=500;//����+500
			}
			if(characterImage[i]==charactersImage[5]) {
				// ��
				combo_hit=0; //�޺� �ʱ�ȭ
				set_time-=5;  // time -5
				if(set_time<0) set_time = 0; // Ÿ���� 0���� ������ 0����
				timer.setText(Integer.toString(set_time));//�������� Ÿ�� �� ����
			}
			characterImage[i] = charactersImage[0]; // ���� �̹��� �������� �ʱ�ȭ
			combo.setText("Combo: " + combo_hit);//�������� �޺� �� ����
			score.setText("Score: " + cnt_score);//�������� ���� �� ����
			repaint(); 
			}
			//}

			
			private class Clicked implements MouseListener{ //ĳ���� Ŭ�� ������
				public void mouseClicked(MouseEvent event) {}


				public void mousePressed(MouseEvent event) {
					JButton obj = (JButton)event.getSource(); //Ŭ���� ��ư ���� obj�� �ޱ�
					obj.setEnabled(false); // Ŭ���� ��ư ��Ȱ��ȭ
					//new Thread() {
						//public void run() {
					int i;
					for(i=0;i<max;i++) {
						if(left[i]==obj.getX()&&top[i]==obj.getY()) break; // Ŭ���� ��ġ ��ǥ ã��
					}
					
					if(characterImage[i]==charactersImage[0]) { // ��ġ�� ĳ���Ͱ� null�� ���
						// ����
						combo_hit=0; // �޺� �ʱ�ȭ 
						combo.setText("Combo: " + combo_hit);//�������� �޺� �� ����
						
					}
					else { // null�� �ƴ� ���
					obj.setIcon(Hit); // ��ġ�� hit�̹��� ǥ��
					cal_score(i); // ���� ��� 
					}
					obj.setEnabled(true); // Ŭ���� ��ư Ȱ��ȭ
					//}}.start();	
					
				}
				public void mouseReleased(MouseEvent event) {}
				public void mouseEntered(MouseEvent event) {}
				public void mouseExited(MouseEvent event) {}
				
				
			}
			
			
			


		
			
		
	
			
}