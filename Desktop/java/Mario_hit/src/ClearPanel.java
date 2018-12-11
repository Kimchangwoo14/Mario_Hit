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
//초기화면 클래스
	private ImageIcon icon;
	JButton clear_home;//clear홈버튼 생성
	JLabel score=new JLabel();//최종 점수 출력하는 라벨 생성
	private ImageIcon homeicon;//홈버튼 이미지
	calRanking rank;
	int [] sort_rank = new int [6];//상위 5등까지의 점수와 이번 점수와 비교하여 랭크 최신화를 위한 배열
	public ClearPanel(CardLayout card, int total_score,SoundPlayer music){
		
		// TODO Auto-generated constructor stub
		music.Stop();
		music.setMusic("sound/clearbgm.wav");//클리어bgm재생
		
		//랭크 계산{
		rank = new calRanking();
		rank.fileread(); // 현재 랭킹 파일을 읽어 옴
		for(int i=0;i<5;i++) {
			sort_rank[i] = rank.Rank[i];//정렬할 배열에 현재 랭킹 값 넣음
		}
		sort_rank[5] = total_score; // 이번 점수 넣음
	   
		for(int i=0;i<6;i++) {//내림차순 정렬
			for(int j=i+1;j<6;j++) {
			if(sort_rank[i]<sort_rank[j]) {
				int temp = sort_rank[i];	
				sort_rank[i] = sort_rank[j];
				sort_rank[j]=temp;
			}
			}
		}
	    for(int i=0;i<5;i++) {//상위 5등 까지 점수만 랭크 점수에 넣음
	    	System.out.println(sort_rank[i]);
			rank.Rank[i]=sort_rank[i];
		}
	    
	    rank.filewrite();//계산한 랭크를 파일에 저장
	//}
		// 초기화면 설정
		setBackground(Color.white);
		setPreferredSize(new Dimension(600,800));
		setLayout(null);
		icon = new ImageIcon("img/clear_background.png");
		
		//최종 점수 라벨 설정
		score.setText( "Score : "+Integer.toString(total_score)+"!!");
		score.setForeground(Color.white);
		score.setFont(new Font("Verdana",Font.BOLD, 40));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setBounds(0,10,600,40);	
		add(score);
		homeicon = new ImageIcon("img/main_button_clear.png");
		
		//클리어 시 홈버튼
		clear_home=new JButton(homeicon);
		clear_home.setBounds(233, 386, 142, 100);
		clear_home.setBorderPainted(false); 
		clear_home.setFocusPainted(false); 
		clear_home.setContentAreaFilled(false);
		clear_home.setBackground(Color.WHITE);
		add(clear_home);
		clear_home.addActionListener(new ActionListener(){
			//홈 버튼 클릭 시
			public void actionPerformed(ActionEvent e) {
				
				music.Stop();
				music.setMusic("sound/mainbgm.wav");
				
				card.show(getRootPane().getContentPane(),"1");
				getRootPane().repaint(); //초기화면으로 돌아감
				
            }
		});
		
	}

	
	public void paintComponent(Graphics page) {
		//초기화면 그리기
	super.paintComponent(page);
	page.drawImage(icon.getImage(),0,0,this);
	}
}
