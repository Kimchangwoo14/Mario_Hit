
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

 

public class Project extends JFrame{
	//���� frame �����ϴ� Ŭ����
	StartPanel p1; //�ʱ�ȭ�� panel
	HelpPanel p2;// ���� panel

	Image img;    //Ŀ�� �̹��� 
	Cursor cursor; //Ŀ�� 
	

	CardLayout card;// panel �̵��� ���� card
	public Project() {

		
		SoundPlayer music=new SoundPlayer(); // ���� ����ϴ� ��ü ���� 
		
	
		card = new CardLayout(); //panel �̵��� ���� card ����
		p1 = new StartPanel(card,music);//�ʱ�ȭ�� panel ����
		p2 = new HelpPanel(card);// ���� panel ����

		
		setLayout(card);

		add("1",p1); // card�� �ʱ�ȭ�� �߰�
		add("2",p2); // card�� ����ȭ�� �߰�

		
		//frame ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,800);
		setResizable(false);
		
		Toolkit tk = Toolkit.getDefaultToolkit();  //Ŀ�� ���� 
		img = tk.getImage("img/hammer.png");       //Ŀ�� �̹��� hammer ����
	    Point point = new Point(0,0);              //Ŀ���� ����ġ�� ��ġ 
	    cursor = tk.createCustomCursor(img,point,"hammer");  //Ŀ�� Ŀ���� (�̹���,��ġ,�̸�)
	    setCursor(cursor);       //Ŀ���̹��� ���� 

		setVisible(true);
		pack();

	}
	
	

	public static void main(String[] args) {//����
		// TODO Auto-generated method stub
	
		Project mario = new Project(); //����

	}
	

	
	
}