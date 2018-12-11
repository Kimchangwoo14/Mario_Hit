import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;


public class RankingPanel extends JPanel{
//도움말 화면 클래스
	private ImageIcon icon;
	JButton btnreturn = new JButton("<");//뒤로 가기 버튼
	calRanking rank; //랭크 계산을 위한 calRanking class 
	private JLabel [] Rank_text = new JLabel[5]; // 계산한 랭크 순으로 출력 위한 JLabel
	public RankingPanel(CardLayout card) {
		//도움말 화면 설정
		// TODO Auto-generated constructor stub
		int count=0;
		setBackground(Color.white);
		setPreferredSize(new Dimension(600,800));
		setLayout(null);
		icon = new ImageIcon("img/ranking_background.png"); 
		
		
		for(int i=0;i<5;i++) {// JLabel 설정
			Rank_text[i] = new JLabel();
			Rank_text[i].setFont(new Font("Verdana",Font.BOLD, 40));
			Rank_text[i].setForeground(Color.white);
			Rank_text[i].setHorizontalAlignment(SwingConstants.CENTER);
			add(Rank_text[i]);
		}
		Rank_text[0].setBounds(65,120,476,72);	
		Rank_text[1].setBounds(65,240,476,72);
		Rank_text[2].setBounds(65,360,476,72);
		Rank_text[3].setBounds(65,485,476,72);
		Rank_text[4].setBounds(65,605,476,72);
		
		printrank();//랭크 계산하여 JLabel에 값 추가
		
		//뒤로가기 버튼 설정
		btnreturn.setFont(new Font("Verdana",Font.BOLD, 30));
		btnreturn.setBounds(-5, 15, 80, 50);
		btnreturn.setBackground(Color.WHITE);
		btnreturn.setForeground(Color.WHITE);
		btnreturn.setBorderPainted(false); 
		btnreturn.setFocusPainted(false); 
		btnreturn.setContentAreaFilled(false);
		
		add(btnreturn);// 도움말 화면에 뒤로가기 버튼 추가
		
		btnreturn.addActionListener(new ActionListener(){
			//뒤로가기 버튼 클릭 시 StartPanel으로 이동
			public void actionPerformed(ActionEvent e) {
                 card.show(getRootPane().getContentPane(),"1"); //초기화면으로 넘어감
				getRootPane().repaint(); 
                 
            }
		});
		
		
	}

	public void printrank() {//랭크 계산하여 JLabel에 값 추가
		rank = new calRanking(); 
		rank.fileread();// 랭킹 값을 계산해옴
		
		
		for(int i=0;i<5;i++) {
			Rank_text[i].setText(""+rank.Rank[i]);//계산한 랭크 값 각 JLabel에 값 추가
		}
		
		
		
	}
	public void paintComponent(Graphics page) {
//도움말 화면 그리기
	super.paintComponent(page);
	page.drawImage(icon.getImage(),0,0,this);
	}
}
