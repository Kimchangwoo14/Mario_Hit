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
//�ʱ�ȭ�� Ŭ����
	private ImageIcon icon;
	private ImageIcon starticon;
	private ImageIcon helpicon;
	private ImageIcon rankingicon;
	private JButton btnstart;//���� ���� ��ư
	private JButton btnhelp;//���� ��ư
	private JButton btnranking;//���� ��ư
	

	
	public StartPanel(CardLayout card,SoundPlayer music) {
		
		// TODO Auto-generated constructor stub
		
		
		music.setMusic("sound/mainbgm.wav");//mainbgm ���
		
		

		
		// �ʱ�ȭ�� ����
		setBackground(Color.white);
		setPreferredSize(new Dimension(600,800));
		setLayout(null);
		icon = new ImageIcon("img/mario_background.png");
		starticon = new ImageIcon("img/start.png");
		helpicon = 	new ImageIcon("img/help.png");
		rankingicon = new ImageIcon("img/ranking.png");

		
		// ���� ��ư ����
		btnstart = new JButton(starticon);
		btnstart.setForeground(Color.white);
		btnstart.setBounds(200, 580, 200, 60);
		btnstart.setBackground(Color.WHITE);
		btnstart.setBorderPainted(false); 
		btnstart.setFocusPainted(false); 
		btnstart.setContentAreaFilled(false);
		
		add(btnstart); // �ʱ�ȭ�鿡 ���� ��ư �߰�
		
		//���� ��ư ����
		btnhelp = new JButton(helpicon);
		btnhelp.setBounds(200, 650, 200, 40);
		btnhelp.setBackground(Color.WHITE);
		btnhelp.setForeground(Color.white);
		btnhelp.setBorderPainted(false); 
		btnhelp.setFocusPainted(false); 
		btnhelp.setContentAreaFilled(false);
		
		add(btnhelp); // �ʱ�ȭ�鿡 ���� ��ư �߰�
		
		//��ŷ��ư �߰�
		btnranking = new JButton(rankingicon);
		btnranking.setBounds(200, 700, 200, 40);
		btnranking.setBackground(Color.WHITE);
		btnranking.setForeground(Color.white);
		btnranking.setBorderPainted(false); 
		btnranking.setFocusPainted(false); 
		btnranking.setContentAreaFilled(false);
		add(btnranking);
		
		btnstart.addActionListener(new ActionListener(){
			//���� ��ư Ŭ�� �� Playpanel�� �̵�
			public void actionPerformed(ActionEvent e) {
                 PlayPanel play = new PlayPanel(card,music); //���� panel ����
                 getRootPane().getContentPane().add("4",play);// card�� ����panel ����
                 card.show(getRootPane().getContentPane(),"4"); //����ȭ������ �Ѿ
				getRootPane().repaint();

            }
		});
		
		btnhelp.addActionListener(new ActionListener(){
			//���� ��ư Ŭ�� �� HelpPanel�� �̵�
			public void actionPerformed(ActionEvent e) {
                 card.show(getRootPane().getContentPane(),"2"); //����ȭ������ �Ѿ
				getRootPane().repaint(); 
                 
            }
		});
		btnranking.addActionListener(new ActionListener(){
			//��ũ ��ư Ŭ�� �� RankingPanel�� �̵�
			public void actionPerformed(ActionEvent e) {
				RankingPanel p3 = new RankingPanel(card);
				 getRootPane().getContentPane().add("3",p3);// card�� ��ŷpanel ����
                card.show(getRootPane().getContentPane(),"3"); //��ŷȭ������ �Ѿ
				getRootPane().repaint(); 
                 
            }
		});
		
	}

	
	public void paintComponent(Graphics page) {
		//�ʱ�ȭ�� �׸���
	super.paintComponent(page);
	page.drawImage(icon.getImage(),0,0,this);

	}
}

