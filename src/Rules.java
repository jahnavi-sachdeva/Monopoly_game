package business_game;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;
public class Rules extends JFrame 
{ 
	JLabel lblTitle;
	JButton btnBack;
	JTextArea txtRules;
	JScrollPane jsp;
	Rules()
	{
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
		
		lblTitle=new JLabel("\tRules:");
		lblTitle.setBackground(clr);
		lblTitle.setOpaque(true);
		Font f= new Font("Ravie",Font.BOLD,40);
		lblTitle.setFont(f);
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		//lblTitle.setAlignmentY(RIGHT_ALIGNMENT);
		lblTitle.setForeground(Color.white);
		add(lblTitle,BorderLayout.NORTH);
		
		txtRules=new JTextArea("Each player rolls the dice to see who goes first. The person who rolls 6 goes first.\r\n" + "\r\n"+
				"Whenever you land on a land that no one owns, you can buy it from the bank. If you do not want to buy it then you can simplymove on .All of the prices for the land are on the board. "+"\r\n"+ 
				"Once you own the land, players must pay a rent if they are waiting on your land.\r\n" + "\r\n"+
				"If you roll doubles (on number 6) you get to roll again.\r\n" + "\r\n" +
				"If you enter into the Jail, you will miss a chance.\r\n"+"\r\n"+
				"if you get three ^s they got cancelled.\r\n"+"\r\n"+
				"For StartUp Grant and Income Tax you need to pay Rs. 100 and Rs.50 respectively\r\n"
				);
		   
		txtRules.setEditable(false);
		Font f1= new Font("Times New Roman",Font.BOLD,25);
		txtRules.setMargin(new Insets(10, 20, 10, 20)); 
		txtRules.setFont(f1);
		txtRules.setBackground(clr);
		txtRules.setForeground(Color.white);
		txtRules.setLineWrap(true);
		jsp=new JScrollPane(txtRules);
		jsp.setBounds(5,5,100,100);
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
		//pack();
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
	new Rules();
}*/
}



