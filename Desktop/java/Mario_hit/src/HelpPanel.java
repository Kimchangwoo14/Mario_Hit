import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class HelpPanel extends JPanel{
//���� ȭ�� Ŭ����
	private ImageIcon icon;
	JButton btnreturn = new JButton("<");//�ڷ� ���� ��ư
	
	public HelpPanel(CardLayout card) {
		//���� ȭ�� ����
		// TODO Auto-generated constructor stub
		setBackground(Color.white);
		setPreferredSize(new Dimension(600,800));
		setLayout(null);
		icon = new ImageIcon("img/help_background.png"); 
		
		
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

	
	public void paintComponent(Graphics page) {
//���� ȭ�� �׸���
	super.paintComponent(page);
	page.drawImage(icon.getImage(),0,0,this);
	}
}
