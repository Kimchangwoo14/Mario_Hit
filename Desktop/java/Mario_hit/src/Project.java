
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

 

public class Project extends JFrame{
	//메인 frame 구성하는 클래스
	StartPanel p1; //초기화면 panel
	HelpPanel p2;// 도움말 panel

	Image img;    //커서 이미지 
	Cursor cursor; //커서 
	

	CardLayout card;// panel 이동을 위한 card
	public Project() {

		
		SoundPlayer music=new SoundPlayer(); // 음악 재생하는 객체 생성 
		
	
		card = new CardLayout(); //panel 이동을 위한 card 생성
		p1 = new StartPanel(card,music);//초기화면 panel 생성
		p2 = new HelpPanel(card);// 도움말 panel 생성

		
		setLayout(card);

		add("1",p1); // card에 초기화면 추가
		add("2",p2); // card에 도움말화면 추가

		
		//frame 설정
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,800);
		setResizable(false);
		
		Toolkit tk = Toolkit.getDefaultToolkit();  //커서 변경 
		img = tk.getImage("img/hammer.png");       //커서 이미지 hammer 저장
	    Point point = new Point(0,0);              //커서가 가르치는 위치 
	    cursor = tk.createCustomCursor(img,point,"hammer");  //커서 커스텀 (이미지,위치,이름)
	    setCursor(cursor);       //커서이미지 적용 

		setVisible(true);
		pack();

	}
	
	

	public static void main(String[] args) {//메인
		// TODO Auto-generated method stub
	
		Project mario = new Project(); //실행

	}
	

	
	
}