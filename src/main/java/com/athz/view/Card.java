package com.athz.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Window;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument.Content;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatXcodeDarkIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class Card extends JPanel {
	//private JPanel cardContainer;
	private JPanel leftContent;
	private JPanel rightContent;
	private GridLayout layout;
	
	public Card() {
		//cardContainer = new JPanel();
		this.layout = new GridLayout(1,2);
		//this.layout.setHgap(10);
		this.setLayout(layout);
		this.leftContent = new JPanel();
		this.rightContent = new JPanel();
		
		leftContent.setLayout(new GridLayout(1,1));
		leftContent.setBorder(new EmptyBorder(0,10,5,0));
 
		leftContent.setBounds(50,50,50,50);
		
		
		rightContent.setLayout(new GridLayout(1,1));
		rightContent.setBorder(new EmptyBorder(0,1,5,10));
		
		JLabel leftLabel = new JLabel("Izquierda");
		leftLabel.setOpaque(true);
		leftLabel.setBackground(new Color(82,93,115));
		
		
		JLabel rightLabel = new JLabel("Derecha");
		rightLabel.setOpaque(true);
		rightLabel.setBackground(new Color(82,93,115));
		
		this.leftContent.add(leftLabel);
		this.rightContent.add(rightLabel);

		this.add(leftContent);
		this.add(rightContent);
	}
	
	public static void main(String[] args) {
		FlatMacLightLaf.setup();
		JFrame f = new JFrame();
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		Container c = f.getContentPane();
		
		
		
		JComboBox<String> options = new JComboBox<String>();
		options.addItem("Selecciona una opcion");
		options.addItem("Euro");
		options.addItem("Dolar");
		options.addItem("Yuan");
		top.add(options);
		top.setBackground(new Color(245,245,245));
		top.setBounds(5,5,50,50);
		
		bottom.setLayout(new GridLayout(4,1));
		bottom.add(new Card());
		bottom.add(new Card());
		bottom.add(new Card());
		bottom.add(new Card());
		
		f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		f.add(top);
		f.add(bottom);
		f.setSize(480,640);
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		f.setVisible(true);
	}
}
