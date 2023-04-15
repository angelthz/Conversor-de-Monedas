package com.athz.tests;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.DropMode;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JSeparator;

public class MainView extends JFrame {
	private final int MAX_WIDTH = 400;
	private final int MAX_HEIGHT = 600;
	private JTextField localCurrencyTextField;
	private JTextField foreignCurrencyTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatMacLightLaf.setup();
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setTitle("Conversor de Monedas");
		this.setSize(this.MAX_WIDTH, this.MAX_HEIGHT);
		this.setResizable(false);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JPanel localPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, localPanel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, localPanel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, localPanel, 245, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, localPanel, -10, SpringLayout.EAST, getContentPane());
		localPanel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 120, 215)));
		getContentPane().add(localPanel);
		
		JPanel curreinciesPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, curreinciesPanel, 6, SpringLayout.SOUTH, localPanel);
		springLayout.putConstraint(SpringLayout.WEST, curreinciesPanel, 0, SpringLayout.WEST, localPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, curreinciesPanel, 64, SpringLayout.SOUTH, localPanel);
		springLayout.putConstraint(SpringLayout.EAST, curreinciesPanel, 0, SpringLayout.EAST, localPanel);
		//panel_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 120, 215)));
		getContentPane().add(curreinciesPanel);
		
		JPanel foreignPanel = new JPanel();
		foreignPanel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 120, 215)));
		springLayout.putConstraint(SpringLayout.NORTH, foreignPanel, 6, SpringLayout.SOUTH, curreinciesPanel);
		springLayout.putConstraint(SpringLayout.WEST, foreignPanel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, foreignPanel, 245, SpringLayout.SOUTH, curreinciesPanel);
		SpringLayout sl_curreinciesPanel = new SpringLayout();
		curreinciesPanel.setLayout(sl_curreinciesPanel);
		
		JComboBox currenciesListCombo = new JComboBox();
		sl_curreinciesPanel.putConstraint(SpringLayout.WEST, currenciesListCombo, 100, SpringLayout.WEST, curreinciesPanel);
		sl_curreinciesPanel.putConstraint(SpringLayout.EAST, currenciesListCombo, -100, SpringLayout.EAST, curreinciesPanel);
		currenciesListCombo.addItem("Selecciona una moneda");
		currenciesListCombo.addItem("USD");
		currenciesListCombo.addItem("EUR");
		
		sl_curreinciesPanel.putConstraint(SpringLayout.NORTH, currenciesListCombo, 10, SpringLayout.NORTH, curreinciesPanel);
		sl_curreinciesPanel.putConstraint(SpringLayout.SOUTH, currenciesListCombo, 48, SpringLayout.NORTH, curreinciesPanel);
		curreinciesPanel.add(currenciesListCombo);
		springLayout.putConstraint(SpringLayout.EAST, foreignPanel, 0, SpringLayout.EAST, localPanel);
		SpringLayout sl_localPanel = new SpringLayout();
		localPanel.setLayout(sl_localPanel);
		
		JLabel localFlag = new JLabel();
		sl_localPanel.putConstraint(SpringLayout.NORTH, localFlag, 0, SpringLayout.NORTH, localPanel);
		sl_localPanel.putConstraint(SpringLayout.SOUTH, localFlag, 100, SpringLayout.NORTH, localPanel);
		localFlag.setIcon(new ImageIcon("../conversr-de-monedas/src/main/resources/com/athz/assets/mexico.png"));
		localFlag.setHorizontalAlignment(SwingConstants.CENTER);
		sl_localPanel.putConstraint(SpringLayout.WEST, localFlag, 10, SpringLayout.WEST, localPanel);
		sl_localPanel.putConstraint(SpringLayout.EAST, localFlag, 354, SpringLayout.WEST, localPanel);
		localPanel.add(localFlag);
		
		localCurrencyTextField = new JTextField();
		sl_localPanel.putConstraint(SpringLayout.NORTH, localCurrencyTextField, 70, SpringLayout.SOUTH, localFlag);
		sl_localPanel.putConstraint(SpringLayout.WEST, localCurrencyTextField, 85, SpringLayout.WEST, localFlag);
		sl_localPanel.putConstraint(SpringLayout.SOUTH, localCurrencyTextField, -10, SpringLayout.SOUTH, localPanel);
		sl_localPanel.putConstraint(SpringLayout.EAST, localCurrencyTextField, -85, SpringLayout.EAST, localFlag);
		localCurrencyTextField.setBackground(new Color(246, 246, 246));
		localCurrencyTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		localCurrencyTextField.setText("$");
		localCurrencyTextField.setHorizontalAlignment(SwingConstants.CENTER);
		localPanel.add(localCurrencyTextField);
		localCurrencyTextField.setColumns(10);
		localCurrencyTextField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 120, 215)));
		
		JLabel localCurrencyTag = new JLabel("MXN");
		localCurrencyTag.setFont(new Font("Tahoma", Font.PLAIN, 32));
		localCurrencyTag.setHorizontalAlignment(SwingConstants.CENTER);
		sl_localPanel.putConstraint(SpringLayout.NORTH, localCurrencyTag, 6, SpringLayout.SOUTH, localFlag);
		sl_localPanel.putConstraint(SpringLayout.WEST, localCurrencyTag, 10, SpringLayout.WEST, localPanel);
		sl_localPanel.putConstraint(SpringLayout.SOUTH, localCurrencyTag, 40, SpringLayout.SOUTH, localFlag);
		sl_localPanel.putConstraint(SpringLayout.EAST, localCurrencyTag, 0, SpringLayout.EAST, localFlag);
		localPanel.add(localCurrencyTag);
		
		JLabel localCurrencyName = new JLabel("Peso Mexicano");
		localCurrencyName.setHorizontalAlignment(SwingConstants.CENTER);
		sl_localPanel.putConstraint(SpringLayout.NORTH, localCurrencyName, 6, SpringLayout.SOUTH, localCurrencyTag);
		sl_localPanel.putConstraint(SpringLayout.WEST, localCurrencyName, 0, SpringLayout.WEST, localFlag);
		sl_localPanel.putConstraint(SpringLayout.EAST, localCurrencyName, 0, SpringLayout.EAST, localFlag);
		localPanel.add(localCurrencyName);
		getContentPane().add(foreignPanel);
		SpringLayout sl_foreignPanel = new SpringLayout();
		foreignPanel.setLayout(sl_foreignPanel);
		
		JLabel foreignFlag = new JLabel();
		sl_foreignPanel.putConstraint(SpringLayout.WEST, foreignFlag, 10, SpringLayout.WEST, foreignPanel);
		sl_foreignPanel.putConstraint(SpringLayout.SOUTH, foreignFlag, 0, SpringLayout.SOUTH, foreignPanel);
		sl_foreignPanel.putConstraint(SpringLayout.EAST, foreignFlag, 354, SpringLayout.WEST, foreignPanel);
		foreignFlag.setHorizontalAlignment(SwingConstants.CENTER);
		foreignFlag.setIcon(new ImageIcon("../conversr-de-monedas/src/main/resources/com/athz/assets/usa.png"));
		foreignPanel.add(foreignFlag);
		
		JLabel foreignCurrencyTag = new JLabel("USD");
		sl_foreignPanel.putConstraint(SpringLayout.SOUTH, foreignCurrencyTag, 50, SpringLayout.NORTH, foreignPanel);
		foreignCurrencyTag.setFont(new Font("Tahoma", Font.PLAIN, 32));
		foreignCurrencyTag.setHorizontalAlignment(SwingConstants.CENTER);
		sl_foreignPanel.putConstraint(SpringLayout.NORTH, foreignFlag, 87, SpringLayout.SOUTH, foreignCurrencyTag);
		sl_foreignPanel.putConstraint(SpringLayout.NORTH, foreignCurrencyTag, 10, SpringLayout.NORTH, foreignPanel);
		sl_foreignPanel.putConstraint(SpringLayout.WEST, foreignCurrencyTag, 10, SpringLayout.WEST, foreignPanel);
		sl_foreignPanel.putConstraint(SpringLayout.EAST, foreignCurrencyTag, -10, SpringLayout.EAST, foreignPanel);
		foreignPanel.add(foreignCurrencyTag);
		
		JLabel foreignCurrencyName = new JLabel("United States Dollar");
		foreignCurrencyName.setHorizontalAlignment(SwingConstants.CENTER);
		sl_foreignPanel.putConstraint(SpringLayout.NORTH, foreignCurrencyName, 6, SpringLayout.SOUTH, foreignCurrencyTag);
		sl_foreignPanel.putConstraint(SpringLayout.WEST, foreignCurrencyName, 0, SpringLayout.WEST, foreignFlag);
		sl_foreignPanel.putConstraint(SpringLayout.EAST, foreignCurrencyName, 0, SpringLayout.EAST, foreignFlag);
		foreignPanel.add(foreignCurrencyName);
		
		foreignCurrencyTextField = new JTextField();
		foreignCurrencyTextField.setHorizontalAlignment(SwingConstants.CENTER);
		foreignCurrencyTextField.setText("52.2525");
		foreignCurrencyTextField.setEditable(false);
		foreignCurrencyTextField.setBackground(new Color(246, 246, 246));
		foreignCurrencyTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		sl_foreignPanel.putConstraint(SpringLayout.NORTH, foreignCurrencyTextField, 6, SpringLayout.SOUTH, foreignCurrencyName);
		sl_foreignPanel.putConstraint(SpringLayout.WEST, foreignCurrencyTextField, 85, SpringLayout.WEST, foreignPanel);
		sl_foreignPanel.putConstraint(SpringLayout.SOUTH, foreignCurrencyTextField, 60, SpringLayout.SOUTH, foreignCurrencyName);
		sl_foreignPanel.putConstraint(SpringLayout.EAST, foreignCurrencyTextField, -85, SpringLayout.EAST, foreignPanel);
		foreignPanel.add(foreignCurrencyTextField);
		foreignCurrencyTextField.setColumns(10);
		foreignCurrencyTextField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 120, 215)));
	}
}
