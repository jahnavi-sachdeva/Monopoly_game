package business_game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class AboutUs extends JFrame 
{
	JTextField txttitle;
	JTextArea txtdesc;
	JButton btnBack;
	JScrollPane jsp;
	AboutUs()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800,600);
		Color clr=new Color (191, 31, 31);
		Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
		d.width=(d.width-getSize().width)/2;
		d.height=(d.height-getSize().height)/2;
		//setLocation(0,0);	// to locate at center of screen
		setLocation(d.width,d.height);
		setBackground(clr);
		setResizable(false);
		setUndecorated(true);
		
		txttitle= new JTextField("Monopoly India");
		Font f= new Font("Ravie",Font.BOLD,45);
		txttitle.setFont(f);
		txttitle.setBackground(clr);
		txttitle.setForeground(Color.white);
		txttitle.setHorizontalAlignment(JTextField.CENTER);
		txttitle.setEditable(false);
		add(txttitle,BorderLayout.NORTH);
		
		txtdesc=new JTextArea();
		txtdesc.setText("Welcome to my new project, \"Monopoly India\".Monopoly, real-estate board game for two players, in which the player's goal is to remain financially solvent while forcing opponents into bankruptcy by buying and developing pieces of property.This Project aims to let the players play the game MONOPOLY Digitally.\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"ABOUT SOFTWARE :  The project, Monopoly India is a web-based application that allows the playsr to play the game, digitally.Interactive GUI and the ability to buy the state, make hotel and rent the propert to resell it. Wheel and deal as you build your fortune playing an exciting game of MONOPOLY. Buy up whole neighborhoods, charge rent, and watch your empire grow. It's all about making deals and making money. But don't land in Jail! Go broke, and you'll lose everything.\r\n" + 
				"Keep playing!!\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"Developed By : Jahnavi Sachdeva \r\n" + 
				"Date : 05-07-2019");
		txtdesc.setEditable(false);
		Font f1= new Font("Times New Roman",Font.BOLD,25);
		txtdesc.setMargin(new Insets(10, 20, 10, 20)); 
		txtdesc.setFont(f1);
		txtdesc.setBackground(clr);
		txtdesc.setForeground(Color.white);
		txtdesc.setLineWrap(true);
		jsp=new JScrollPane(txtdesc);
		jsp.setBounds(5, 5, 100, 100);
		add(jsp);
		
		btnBack=new JButton("Back");
		btnBack.setBounds(100,550,150,50);
		setStyleButton(btnBack);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainFrame();
				
			}
		});
		add(btnBack,BorderLayout.SOUTH);
		setVisible(true);
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
	
	/*public static void main(String args[])
	{
		new AboutUs();
	}*/
}
