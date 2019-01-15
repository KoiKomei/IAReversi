package reversi;
import java.awt.Color;

import javax.swing.JFrame;
import java.awt.Graphics;




public class Main {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jframe=new JFrame("FINESTRA");
		Reversi r=new Reversi();
		jframe.getContentPane().add(r);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.setBackground(Color.LIGHT_GRAY);
        jframe.setSize(800,800);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
	}

}
