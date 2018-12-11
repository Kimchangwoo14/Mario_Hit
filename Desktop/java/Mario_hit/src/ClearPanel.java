import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ClearPanel extends JPanel{
//�ʱ�ȭ�� Ŭ����
	private ImageIcon icon;
	JButton clear_home;//clearȨ��ư ����
	JLabel score=new JLabel();//���� ���� ����ϴ� �� ����
	private ImageIcon homeicon;//Ȩ��ư �̹���
	calRanking rank;
	int [] sort_rank = new int [6];//���� 5������� ������ �̹� ������ ���Ͽ� ��ũ �ֽ�ȭ�� ���� �迭
	public ClearPanel(CardLayout card, int total_score,SoundPlayer music){
		
		// TODO Auto-generated constructor stub
		music.Stop();
		music.setMusic("sound/clearbgm.wav");//Ŭ����bgm���
		
		//��ũ ���{
		rank = new calRanking();
		rank.fileread(); // ���� ��ŷ ������ �о� ��
		for(int i=0;i<5;i++) {
			sort_rank[i] = rank.Rank[i];//������ �迭�� ���� ��ŷ �� ����
		}
		sort_rank[5] = total_score; // �̹� ���� ����
	   
		for(int i=0;i<6;i++) {//�������� ����
			for(int j=i+1;j<6;j++) {
			if(sort_rank[i]<sort_rank[j]) {
				int temp = sort_rank[i];	
				sort_rank[i] = sort_rank[j];
				sort_rank[j]=temp;
			}
			}
		}
	    for(int i=0;i<5;i++) {//���� 5�� ���� ������ ��ũ ������ ����
	    	System.out.println(sort_rank[i]);
			rank.Rank[i]=sort_rank[i];
		}
	    
	    rank.filewrite();//����� ��ũ�� ���Ͽ� ����
	//}
		// �ʱ�ȭ�� ����
		setBackground(Color.white);
		setPreferredSize(new Dimension(600,800));
		setLayout(null);
		icon = new ImageIcon("img/clear_background.png");
		
		//���� ���� �� ����
		score.setText( "Score : "+Integer.toString(total_score)+"!!");
		score.setForeground(Color.white);
		score.setFont(new Font("Verdana",Font.BOLD, 40));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setBounds(0,10,600,40);	
		add(score);
		homeicon = new ImageIcon("img/main_button_clear.png");
		
		//Ŭ���� �� Ȩ��ư
		clear_home=new JButton(homeicon);
		clear_home.setBounds(233, 386, 142, 100);
		clear_home.setBorderPainted(false); 
		clear_home.setFocusPainted(false); 
		clear_home.setContentAreaFilled(false);
		clear_home.setBackground(Color.WHITE);
		add(clear_home);
		clear_home.addActionListener(new ActionListener(){
			//Ȩ ��ư Ŭ�� ��
			public void actionPerformed(ActionEvent e) {
				
				music.Stop();
				music.setMusic("sound/mainbgm.wav");
				
				card.show(getRootPane().getContentPane(),"1");
				getRootPane().repaint(); //�ʱ�ȭ������ ���ư�
				
            }
		});
		
	}

	
	public void paintComponent(Graphics page) {
		//�ʱ�ȭ�� �׸���
	super.paintComponent(page);
	page.drawImage(icon.getImage(),0,0,this);
	}
}
