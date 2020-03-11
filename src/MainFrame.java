package business_game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

public class MainFrame extends JFrame{
	JButton btnStart,btnRules,btnAboutUs,btnQuit;
	JLabel lblMainMenu;
	MainFrame()
	{	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		setSize(800,600);
		Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
		d.width=(d.width-getSize().width)/2;
		d.height=(d.height-getSize().height)/2;
		//setLocation(0,0);	// to locate at center of screen
		setLocation(d.width,d.height);
		setResizable(false);
		setUndecorated(true);
		setContentPane(new JLabel((new ImageIcon(new ImageIcon("images/mainframe.png").getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT)))));  
		
		lblMainMenu = new JLabel("MAIN MENU");
		lblMainMenu.setBounds(350,225,500,50);
		//setStyleHead(lblMainMenu);
		Font f = new Font(Font.SANS_SERIF,Font.BOLD+Font.ITALIC,40);
		lblMainMenu.setFont(f);
		lblMainMenu.setForeground(Color.RED);
		//add(lblMainMenu);

		btnStart= new JButton("Start");
		btnStart.setBounds(350,300,250,50);
		setStyleButton(btnStart);
		add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
	  	  		new Board();
				
			}
		});
		btnRules= new JButton("Rules");
		btnRules.setBounds(350,400,250,50);
		setStyleButton(btnRules);
		add(btnRules);
		btnRules.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
	  	  		new Rules();
				
			}
		});
		
		btnAboutUs= new JButton("About Us");
		btnAboutUs.setBounds(350,500,275,50);
		setStyleButton(btnAboutUs);
		add(btnAboutUs);
		btnAboutUs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
	  	  		new AboutUs();
				
			}
		});
		
		btnQuit= new JButton("Quit");
		btnQuit.setBounds(100,550,150,50);
		setStyleButton(btnQuit);
		add(btnQuit);
		btnQuit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
	  	  		
				
			}
		});
		setVisible(true);
	}

public static void main(String args[])
{
	new MainFrame();
}

void setStyleButton(JButton cmp){
	Font fontStyle= new Font("Ravie",Font.BOLD,34);
    cmp.setFont(fontStyle);
    Color fontClr= new Color(219, 216, 212,255);  // 4th argument is the opacity
    cmp.setForeground(fontClr); 
    fontClr= new Color(112, 34, 37);
    cmp.setBackground(fontClr);
    cmp.setBorderPainted(false);
    cmp.setFocusPainted(false);
}
}
