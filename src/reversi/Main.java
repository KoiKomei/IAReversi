package reversi;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.net.URL;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;




public class Main {
	JFrame jframe=new JFrame("REVERSI");

	
	JPanel panelContainer= new JPanel();
	JPanel menu= new JPanel();
	Reversi stupid=new Reversi("encodings/stoooopid");
	Reversi facile=new Reversi("encodings/easy");
	Reversi normale=new Reversi("encodings/medium");
	Reversi difficile=new Reversi("encodings/finalHard");
	
	JButton oof=new JButton("Stupido");
	JButton easy=new JButton("Facile");
	JButton medium=new JButton("Normale");
	JButton hard=new JButton("Difficile");
	JButton back0=new JButton("Menu");
	JButton back1=new JButton("Menu");
	JButton back2=new JButton("Menu");
	JButton back3=new JButton("Menu");
	
	CardLayout container=new CardLayout();
	
	
	public Main() {

		panelContainer.setLayout(container);
		menu.add(oof);
		menu.add(easy);
		menu.add(medium);
		menu.add(hard);
		stupid.add(back0);
		facile.add(back1);
		normale.add(back2);
		difficile.add(back3);
		panelContainer.add(menu, "1");
		panelContainer.add(stupid, "2");
		panelContainer.add(facile, "3");
		panelContainer.add(normale, "4");
		panelContainer.add(difficile, "5");
	
		container.show(panelContainer, "1");
		
		oof.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 jframe.setSize(800,800);
				container.show(panelContainer, "2");
				
		
			}
	
		});
		
		easy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				 jframe.setSize(800,800);
				container.show(panelContainer, "3");
				
				
			}
			
		});
		medium.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 jframe.setSize(800,800);
				container.show(panelContainer, "4");
				
				
			}
			
		});
		hard.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 jframe.setSize(800,800);
				container.show(panelContainer, "5");
				
		
			}
	
		});
		
		back0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				stupid.restart();
				
			
				container.show(panelContainer, "1");
				jframe.pack();
				
			}
			
		});
		
		back1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				facile.restart();
				
			
				container.show(panelContainer, "1");
				jframe.pack();
				
			}
			
		});
		back2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				normale.restart();
				container.show(panelContainer, "1");
				jframe.pack();
				
			}
			
		});
		back3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				difficile.restart();
				container.show(panelContainer, "1");
				jframe.pack();
				
			}
			
		});
		
		jframe.add(panelContainer);
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.setBackground(Color.LIGHT_GRAY);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        
	
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
			}
		});
	}

}
