package business_game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListDataListener;

import java.util.*;
public class Board extends JFrame{
	Thread t1;
	JPanel panBoard,panSidePanel;
	JButton btnP1,btnP2,btnBank;
	JButton btnPlayer1,btnPlayer2,btnExit;
	ImageIcon imgPlayer1,imgPlayer2,imgDice;
	JLabel lblDice,lblAmountPlayer1,lblAmountPlayer2,lblAmountBank;
	int boardX[]= {560,365,245,125, 18, 18, 18, 18, 18, 18, 18,125,245,365,560,560,560,560,560,560};
	int boardY[]= {700,700,700,700,700,580,480,380,280,180,20, 20, 20, 20, 20,120,280,380,480,580};
	int indexPlayer1=0,indexPlayer2=0,coinWidth=50,coinHeight=50,initAmount=5000,bankInitAmount=20000,rentPer=50,hotelRentPer=75,hotelPer=200;
	String stateName[]= {"start","jammu & kashmir","himachal pradesh","punjab","income tax","haryana","rajasthan","uttar pradesh","bihar","assam","jail","madhya pradesh","jharkhand","maharashtra","startup grant","chhatisgarh","west bengal","andhra pradesh","karnataka","tamil nadu"};
	int price[]= {50,400,200,500,50,500,600,800,500,400,0,600,400,1000,100,400,800,800,800,800};
	int owner[]= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	int hotel[]= {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	JList<String> lstStateP1,lstHotelP1,lstStateP2,lstHotelP2;
	DefaultListModel<String> modelStateP1,modelHotelP1,modelStateP2,modelHotelP2;
	JScrollPane jspStateP1,jspHotelP1,jspStateP2,jspHotelP2;
	boolean flagChanceSkipP1,flagChanceSkipP2,sixP1,sixP2,six2P1,six2P2,six3P1,six3P2;
	int cnt;
	Board(){
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				if(t1!=null)
					t1.stop();
				dispose();
			}
		});
		panBoard=new MyJPanel();
		panBoard.setLayout(null);
		
		imgPlayer1=new ImageIcon("images/blue.png");
		imgPlayer2=new ImageIcon("images/green.png");
		btnPlayer1=new JButton(imgPlayer1);
		btnPlayer1.setContentAreaFilled(false);
		btnPlayer1.setBorder(BorderFactory.createEmptyBorder());
		btnPlayer1.setFocusable(false);
		btnPlayer1.setSize(coinWidth,coinHeight);
		btnPlayer1.setLocation(boardX[0],boardY[0]);
		
		btnPlayer2=new JButton(imgPlayer2);
		btnPlayer2.setContentAreaFilled(false);
		btnPlayer2.setBorder(BorderFactory.createEmptyBorder());
		btnPlayer2.setFocusable(false);
		btnPlayer2.setSize(coinWidth,coinHeight);
		btnPlayer2.setLocation(boardX[0],boardY[0]);
		
		panBoard.add(btnPlayer1);
		panBoard.add(btnPlayer2);
		
		imgDice=new ImageIcon("images/animated_dice.gif");
		lblDice=new JLabel(imgDice);
		lblDice.setBounds(340,430,150,150);
		lblDice.setVisible(false);
		panBoard.add(lblDice);
		add(panBoard);
		
		panSidePanel=new JPanel();
		panSidePanel.setLayout(new GridLayout(11,1));
		panSidePanel.setBackground(Color.cyan);
		btnP1=new JButton("Player 1");
		btnP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flagChanceSkipP1==true) {
					JOptionPane.showMessageDialog(Board.this,"Due to jail your current chance cancelled");
					flagChanceSkipP1=false;
					btnP1.setEnabled(false);
					btnP2.setEnabled(true);
				}
				else
					dice(1);
				
			}
		});
		btnP2=new JButton("Player 2");
		btnP2.setEnabled(false);
		btnP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flagChanceSkipP2==true) {
					flagChanceSkipP2=false;
					JOptionPane.showMessageDialog(Board.this,"Due to jail your current chance cancelled");
					btnP2.setEnabled(false);
					btnP1.setEnabled(true);
				}
				else
					dice(2);
			}
		});
		btnExit=new JButton("Quit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int ans=JOptionPane.showConfirmDialog(Board.this, "Do you want to quit the game...","Monopoly",JOptionPane.YES_NO_OPTION);
				if(ans==JOptionPane.YES_OPTION)
					dispose();
			}
		});
		btnBank=new JButton("Bank");
		lblAmountBank=new JLabel(bankInitAmount+"");
		
		lblAmountPlayer1=new JLabel(initAmount+"");
		lblAmountPlayer1.setFont(new Font(Font.SERIF,Font.BOLD,40));
		lblAmountPlayer2=new JLabel(initAmount+"");
		lblAmountPlayer2.setFont(new Font(Font.SERIF,Font.BOLD,40));
		
		lstStateP1=new JList<>();
		lstHotelP1=new JList<>();
		lstStateP2=new JList<>();
		lstHotelP2=new JList<>();
		jspStateP1=new JScrollPane(lstStateP1);
		jspHotelP1=new JScrollPane(lstHotelP1);
		jspStateP2=new JScrollPane(lstStateP2);
		jspHotelP2=new JScrollPane(lstHotelP2);
		
		modelStateP1=new DefaultListModel<>();
		modelHotelP1=new DefaultListModel<>();
		modelStateP2=new DefaultListModel<>();
		modelHotelP2=new DefaultListModel<>();
		lstStateP1.setModel(modelStateP1);
		lstHotelP1.setModel(modelHotelP1);
		lstStateP2.setModel(modelStateP2);
		lstHotelP2.setModel(modelHotelP2);

		panSidePanel.add(btnExit);
		panSidePanel.add(btnBank);
		panSidePanel.add(lblAmountBank);

		panSidePanel.add(btnP1);
		panSidePanel.add(lblAmountPlayer1);
		panSidePanel.add(jspStateP1);
		panSidePanel.add(jspHotelP1);
		
		panSidePanel.add(btnP2);
		panSidePanel.add(lblAmountPlayer2);
		panSidePanel.add(jspStateP2);
		panSidePanel.add(jspHotelP2);
		add(panSidePanel,"East");
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyCode()==KeyEvent.VK_ESCAPE) {
					int ans=JOptionPane.showConfirmDialog(Board.this, "Do you want to quit the game...","Monopoly",JOptionPane.YES_NO_OPTION);
					if(ans==JOptionPane.YES_OPTION)
						dispose();
				}
			}
		});
		
		setFocusable(true);
		setUndecorated(true);
		setSize(880,760);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	void dice(int player) {
		t1=new Thread() {
			public void run() {
				ImageIcon ii=new ImageIcon("images/animated_dice.gif");
				lblDice.setIcon(ii);
				Random r1=new Random();
				int n=r1.nextInt(6);
				n++;
				/*if(cnt<1) {
					cnt++;
					n=6;
				}
				else
					n=4;*/
				lblDice.setVisible(true);
				slow(3000);
				ii=new ImageIcon("images/"+n+".png");
				lblDice.setIcon(ii);
				revalidate();
				if(n==6) {
					if(player==1) {
						if(six2P1==true) {
							six3P1=true;
						}
						else if(sixP1==true) {
							six2P1=true;
						}
						else
							sixP1=true;
					}
					else {
						if(six2P2==true) {
							six3P2=true;
						}
						else if(sixP2==true) {
							six2P2=true;
						}
						else
							sixP2=true;
					}
					if(six3P1!=true && six3P2!=true) {
						JOptionPane.showMessageDialog(Board.this, "You got 6 so throw the dice again...");
						return;
					}
				}
				if(player==1) {
					btnP1.setEnabled(false);
					btnP2.setEnabled(true);
					if(six3P1==true) {
						six3P1=false;
						JOptionPane.showMessageDialog(Board.this, "You got 3 sixes so all are cancelled...");
						return;
					}
					else if(six2P1==true) {
						six2P1=false;
						n+=12;
						moveCoin(btnPlayer1,n);
					}
					else if(sixP1==true) {
						sixP1=false;
						n+=6;
						moveCoin(btnPlayer1,n);
					}
					else {
						moveCoin(btnPlayer1,n);
					}
					indexPlayer1=(indexPlayer1+n)%boardX.length;
					takeAction(1,indexPlayer1);
				}
				else {
					btnP2.setEnabled(false);
					btnP1.setEnabled(true);
					if(six3P2==true) {
						six3P2=false;
						JOptionPane.showMessageDialog(Board.this, "You got 3 sixes so all are cancelled...");
						return;
					}
					else if(six2P2==true) {
						six2P2=false;
						n+=12;
						moveCoin(btnPlayer2,n);
					}
					else if(sixP2==true) {
						sixP2=false;
						n+=6;
						moveCoin(btnPlayer2,n);
					}
					else {
						moveCoin(btnPlayer2,n);
					}
					indexPlayer2=(indexPlayer2+n)%boardX.length;
					takeAction(2,indexPlayer2);
				}
			}
		};
		t1.start();
	}
	public static void main(String[] args) {
		new Board();
	}
	void takeAction(int player,int index) {
		int amt=0;
		if(stateName[index].equalsIgnoreCase("start")) {
			amt=price[index];
			JOptionPane.showMessageDialog(this, "You will get " + amt + " as start bonus");
			changeAmount(player, amt, 1);
			changeAmountBank(amt, -1);
		}
		else if(stateName[index].equalsIgnoreCase("startup grant")) {
			amt=price[index];
			JOptionPane.showMessageDialog(this, "You will get " + amt + " as startup grant");
			changeAmount(player, amt, 1);
			changeAmountBank(amt, -1);
		}
		else if(stateName[index].equalsIgnoreCase("income tax")) {
			amt=price[index];
			JOptionPane.showMessageDialog(this, "You have to pay " + amt + " as income tax");
			changeAmount(player, amt, -1);
			changeAmountBank(amt, 1);
		}
		else if(stateName[index].equalsIgnoreCase("jail")) {
			if(player==1)
				flagChanceSkipP1=true;
			else
				flagChanceSkipP2=true;
		}
		else if(hotel[index]==0 && owner[index]==0) {
			if(showBalance(player)>=price[index]) {
				int ans=JOptionPane.showConfirmDialog(this,"Do you want to purchase the state");
				if(ans==JOptionPane.YES_OPTION) {
					amt=price[index];
					changeAmount(player, amt, -1);
					changeAmountBank(amt, 1);
					owner[index]=player;
					if(player==1)
						modelStateP1.addElement(stateName[index]);
					else
						modelStateP2.addElement(stateName[index]);
				}
				else {
					JOptionPane.showMessageDialog(this, "You can move...");
				}
			}
		}
		else if(hotel[index]==0 && owner[index]!=0) {
			if(owner[index]!=player) {
				amt=price[index]*rentPer/100;
				JOptionPane.showMessageDialog(this, amt+" will be deducted from your account as state rent...");
				changeAmount(player, amt, -1);
				changeAmount(owner[index], amt, 1);
			}
			else {
				int ans=JOptionPane.showConfirmDialog(this,"Do you want to construct a hotel at your state...");
				if(ans==JOptionPane.YES_OPTION) {
					amt=price[index]*hotelPer/100;
					JOptionPane.showMessageDialog(this, amt+" will be deducted from your account as hotel construction cost...");
					changeAmount(player, amt, -1);
					hotel[index]=player;
					if(player==1)
						modelHotelP1.addElement(stateName[index]);
					else
						modelHotelP2.addElement(stateName[index]);
				}
			}
		}
		else if(hotel[index]!=0) {
			if(hotel[index]!=player) {
				amt=price[index]*hotelRentPer/100;
				JOptionPane.showMessageDialog(this, amt+" will be deducted from your account as hotel rent...");
				changeAmount(player, amt, -1);
				changeAmount(hotel[index], amt, 1);
			}
		}
	}
	int showBalance(int player) {
		int bal=0;
		if(player==1) 
			bal=Integer.parseInt(lblAmountPlayer1.getText());
		else
			bal=Integer.parseInt(lblAmountPlayer2.getText());
		return bal;
	}
	void changeAmount(int player,int amount,int deductOrAdd) {
		if(player==1) {
			int bal=Integer.parseInt(lblAmountPlayer1.getText());
			bal=bal+amount*deductOrAdd;
			lblAmountPlayer1.setText(bal+"");
		}
		else {
			int bal=Integer.parseInt(lblAmountPlayer2.getText());
			bal=bal+amount*deductOrAdd;
			lblAmountPlayer2.setText(bal+"");			
		}
	}
	void changeAmountBank(int amount,int deductOrAdd) {
		int bal=Integer.parseInt(lblAmountBank.getText());
		bal=bal+amount*deductOrAdd;
		lblAmountBank.setText(bal+"");	
	}
	void moveCoin(JButton btnCoin,int n) {
		int index=initPos(btnCoin);
		int srcX=boardX[index],srcY=boardY[index],destX,destY;
		int i=index+1;
		for(int cnt=1;cnt<=n;cnt++) {
			destX=boardX[i];
			destY=boardY[i];
			if(srcX==destX) {
				if(srcY<destY) {
					for(int j=srcY;j<=destY;j++) {
						btnCoin.setLocation(srcX,j);
						slow(10);
					}
				}
				else {
					for(int j=srcY;j>=destY;j--) {
						btnCoin.setLocation(srcX,j);
						slow(10);
					}
				}
			}
			else if(srcY==destY){
				if(srcX<destX) {
					for(int j=srcX;j<=destX;j++) {
						btnCoin.setLocation(j,srcY);
						slow(10);
					}
				}
				else {
					for(int j=srcX;j>=destX;j--) {
						btnCoin.setLocation(j,srcY);
						slow(10);
					}
				}
			}
		
			srcX=destX;
			srcY=destY;
			if(i==boardX.length-1)
				i=0;
			else
				i++;
		}
	}
	int initPos(JButton btnCoin) {
		if(btnCoin==btnPlayer1)
			return indexPlayer1;
		else
			return indexPlayer2;
	}
	void slow(int time) {
		try {
			Thread.sleep(time);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class MyJPanel extends JPanel{
	public void paintComponent(Graphics g) {
		Dimension panSize=getSize();
		ImageIcon ii=new ImageIcon("images/business.png");
		g.drawImage(ii.getImage(), 0,0,612,756,null);
	}
}
