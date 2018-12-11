import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class HelpPanel extends JPanel{
//도움말 화면 클래스
	private ImageIcon icon;
	JButton btnreturn = new JButton("<");//뒤로 가기 버튼
	
	public HelpPanel(CardLayout card) {
		//도움말 화면 설정
		// TODO Auto-generated constructor stub
		setBackground(Color.white);
		setPreferredSize(new Dimension(600,800));
		setLayout(null);
		icon = new ImageIcon("img/help_background.png"); 
		
		
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

	
	public void paintComponent(Graphics page) {
//도움말 화면 그리기
	super.paintComponent(page);
	page.drawImage(icon.getImage(),0,0,this);
	}
}
