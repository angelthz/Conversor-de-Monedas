package com.athz.tests;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class VistaDemo extends JFrame {
	private JPanel localPanel;
	private JPanel foreignPanel;
	private JComboBox<String> currencyComboList;

	
	public VistaDemo() {
		Container content = this.getContentPane();
		content.setLayout(new GridLayout(2,1));
		
		localPanel = new JPanel();
		localPanel.setLayout(new GridLayout(5,1));
		foreignPanel = new JPanel();
		
		currencyComboList = new JComboBox<>();
		currencyComboList.addItem("Dolares");
		currencyComboList.addItem("Euros");
		
		JLabel localFlag = new JLabel();
		JLabel localName = new JLabel("Pesos Mexicano");
		JLabel localDenom = new JLabel("MXN");
		
		localFlag.setIcon(new ImageIcon("../conversr-de-monedas/src/main/resources/com/athz/assets/mexico.png"));
		localFlag.setVerticalAlignment(SwingConstants.CENTER);
		localFlag.setHorizontalAlignment(SwingConstants.CENTER);
		
		localFlag.setOpaque(true);
		localFlag.setBackground(new Color(44,44,44));
		
		localPanel.add(localFlag);
		localPanel.add(localName);
		localPanel.add(localDenom);
		localPanel.add(currencyComboList);
		content.add(localPanel);
		content.add(foreignPanel);
		
		this.setTitle("Conversor de Moneda");
		this.setResizable(false);
		this.setSize(420,540);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		FlatMacLightLaf.setup();
		new VistaDemo();
	}
}
