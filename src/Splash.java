package business_game;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class Splash extends JFrame {
	ImageIcon imgIcon;
	JLabel lblImage;
	JProgressBar jpb;
	Splash(){
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		imgIcon=new ImageIcon("images/monopoly.jpg");
		imgIcon=new ImageIcon(imgIcon.getImage().getScaledInstance(570, 470, Image.SCALE_DEFAULT));
		lblImage=new JLabel(imgIcon);
		add(lblImage);
		jpb=new JProgressBar(JProgressBar.HORIZONTAL,0,100);
		jpb.setBackground(Color.cyan);
		jpb.setForeground(Color.blue);
		add(jpb,"South");
		setSize(600,500);
		setLocationRelativeTo(null);
		setVisible(true);
		for(int i=0;i<=100;i++) {
			jpb.setValue(i);
			try {
				Thread.sleep(50);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		dispose();
		new MainFrame();
		pack(); 
	}	
	public static void main(String[] args) {
		new Splash();
	}

}

