import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;


public class RankingPanel extends JPanel{
//���� ȭ�� Ŭ����
	private ImageIcon icon;
	JButton btnreturn = new JButton("<");//�ڷ� ���� ��ư
	calRanking rank; //��ũ ����� ���� calRanking class 
	private JLabel [] Rank_text = new JLabel[5]; // ����� ��ũ ������ ��� ���� JLabel
	public RankingPanel(CardLayout card) {
		//���� ȭ�� ����
		// TODO Auto-generated constructor stub
		int count=0;
		setBackground(Color.white);
		setPreferredSize(new Dimension(600,800));
		setLayout(null);
		icon = new ImageIcon("img/ranking_background.png"); 
		
		
		for(int i=0;i<5;i++) {// JLabel ����
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
		
		printrank();//��ũ ����Ͽ� JLabel�� �� �߰�
		
		//�ڷΰ��� ��ư ����
		btnreturn.setFont(new Font("Verdana",Font.BOLD, 30));
		btnreturn.setBounds(-5, 15, 80, 50);
		btnreturn.setBackground(Color.WHITE);
		btnreturn.setForeground(Color.WHITE);
		btnreturn.setBorderPainted(false); 
		btnreturn.setFocusPainted(false); 
		btnreturn.setContentAreaFilled(false);
		
		add(btnreturn);// ���� ȭ�鿡 �ڷΰ��� ��ư �߰�
		
		btnreturn.addActionListener(new ActionListener(){
			//�ڷΰ��� ��ư Ŭ�� �� StartPanel���� �̵�
			public void actionPerformed(ActionEvent e) {
                 card.show(getRootPane().getContentPane(),"1"); //�ʱ�ȭ������ �Ѿ
				getRootPane().repaint(); 
                 
            }
		});
		
		
	}

	public void printrank() {//��ũ ����Ͽ� JLabel�� �� �߰�
		rank = new calRanking(); 
		rank.fileread();// ��ŷ ���� ����ؿ�
		
		
		for(int i=0;i<5;i++) {
			Rank_text[i].setText(""+rank.Rank[i]);//����� ��ũ �� �� JLabel�� �� �߰�
		}
		
		
		
	}
	public void paintComponent(Graphics page) {
//���� ȭ�� �׸���
	super.paintComponent(page);
	page.drawImage(icon.getImage(),0,0,this);
	}
}
