import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
//초기화면 클래스
	private ImageIcon icon;
	private ImageIcon starticon;
	private ImageIcon helpicon;
	private ImageIcon rankingicon;
	private JButton btnstart;//게임 시작 버튼
	private JButton btnhelp;//도움말 버튼
	private JButton btnranking;//도움말 버튼
	

	
	public StartPanel(CardLayout card,SoundPlayer music) {
		
		// TODO Auto-generated constructor stub
		
		
		music.setMusic("sound/mainbgm.wav");//mainbgm 재생
		
		

		
		// 초기화면 설정
		setBackground(Color.white);
		setPreferredSize(new Dimension(600,800));
		setLayout(null);
		icon = new ImageIcon("img/mario_background.png");
		starticon = new ImageIcon("img/start.png");
		helpicon = 	new ImageIcon("img/help.png");
		rankingicon = new ImageIcon("img/ranking.png");

		
		// 시작 버튼 설정
		btnstart = new JButton(starticon);
		btnstart.setForeground(Color.white);
		btnstart.setBounds(200, 580, 200, 60);
		btnstart.setBackground(Color.WHITE);
		btnstart.setBorderPainted(false); 
		btnstart.setFocusPainted(false); 
		btnstart.setContentAreaFilled(false);
		
		add(btnstart); // 초기화면에 시작 버튼 추가
		
		//도움 버튼 설정
		btnhelp = new JButton(helpicon);
		btnhelp.setBounds(200, 650, 200, 40);
		btnhelp.setBackground(Color.WHITE);
		btnhelp.setForeground(Color.white);
		btnhelp.setBorderPainted(false); 
		btnhelp.setFocusPainted(false); 
		btnhelp.setContentAreaFilled(false);
		
		add(btnhelp); // 초기화면에 도움말 버튼 추가
		
		//랭킹버튼 추가
		btnranking = new JButton(rankingicon);
		btnranking.setBounds(200, 700, 200, 40);
		btnranking.setBackground(Color.WHITE);
		btnranking.setForeground(Color.white);
		btnranking.setBorderPainted(false); 
		btnranking.setFocusPainted(false); 
		btnranking.setContentAreaFilled(false);
		add(btnranking);
		
		btnstart.addActionListener(new ActionListener(){
			//시작 버튼 클릭 시 Playpanel로 이동
			public void actionPerformed(ActionEvent e) {
                 PlayPanel play = new PlayPanel(card,music); //게임 panel 생성
                 getRootPane().getContentPane().add("4",play);// card에 게임panel 삽입
                 card.show(getRootPane().getContentPane(),"4"); //게임화면으로 넘어감
				getRootPane().repaint();

            }
		});
		
		btnhelp.addActionListener(new ActionListener(){
			//도움말 버튼 클릭 시 HelpPanel로 이동
			public void actionPerformed(ActionEvent e) {
                 card.show(getRootPane().getContentPane(),"2"); //도움말화면으로 넘어감
				getRootPane().repaint(); 
                 
            }
		});
		btnranking.addActionListener(new ActionListener(){
			//랭크 버튼 클릭 시 RankingPanel로 이동
			public void actionPerformed(ActionEvent e) {
				RankingPanel p3 = new RankingPanel(card);
				 getRootPane().getContentPane().add("3",p3);// card에 랭킹panel 삽입
                card.show(getRootPane().getContentPane(),"3"); //랭킹화면으로 넘어감
				getRootPane().repaint(); 
                 
            }
		});
		
	}

	
	public void paintComponent(Graphics page) {
		//초기화면 그리기
	super.paintComponent(page);
	page.drawImage(icon.getImage(),0,0,this);

	}
}

