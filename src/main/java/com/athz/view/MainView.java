package com.athz.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.athz.config.Configuration;
import com.athz.config.Log;
import com.athz.controller.ExchangeRateController;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SpringLayout;
import javax.swing.border.MatteBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView extends JFrame {
	private final int MAX_WIDTH = 400;
	private final int MAX_HEIGHT = 600;

	private JTextField localCurrencyTextField;
	private JLabel localFlag;
	private JLabel localCurrencyTag;
	private JLabel localCurrencyName;

	private JTextField foreignCurrencyTextField;
	private JLabel foreignFlag;
	private JLabel foreignCurrencyTag;
	private JLabel foreignCurrencyName;

	JComboBox<Object> currenciesListCombo;

	private Map<String, ImageIcon> flagsList;
	private ExchangeRateController exchangeController;
	private Configuration config;
	private NumberFormat numberFormat;

	/**
	 * Create the frame.
	 */
	public MainView() {
		initConf();
		initGUI();

		currenciesListCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = currenciesListCombo.getSelectedItem().toString();
				// System.out.println(selected);
				Log.logger.info("Moneda seleccionada: "+selected);
				if (currenciesListCombo.getSelectedIndex() > 0) {
					foreignCurrencyTextField.setEnabled(true);
					foreignCurrencyTag.setText(selected);
					foreignCurrencyName.setText(config.getConfigurationProperty(selected));
					foreignFlag.setIcon(flagsList.get(selected));

					if (currenciesListCombo.getName().equals("toFor")) {
						convertCurrency(localCurrencyTextField, foreignCurrencyTextField);
					} else
						convertCurrency(foreignCurrencyTextField, localCurrencyTextField);
				} else {
					clearTextFields();
				}

			}
		});

		localCurrencyTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (currenciesListCombo.getSelectedIndex() > 0 && !localCurrencyTextField.getText().isEmpty()) {
					convertCurrency(localCurrencyTextField, foreignCurrencyTextField);
				} else
					foreignCurrencyTextField.setText("0.0");
				currenciesListCombo.setName("toFor");
			}
		});

		foreignCurrencyTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (currenciesListCombo.getSelectedIndex() > 0 && !foreignCurrencyTextField.getText().isEmpty()) {
					convertCurrency(foreignCurrencyTextField, localCurrencyTextField);
				} else
					localCurrencyTextField.setText("0.0");
				currenciesListCombo.setName("toLoc");
			}
		});

		this.setVisible(true);
	}

	private void initConf() {
		this.config = new Configuration();
		String base = config.getConfigurationProperty("api.base");
		Log.logger.info("Moneda base: "+base);
		this.exchangeController = new ExchangeRateController(base);
		this.numberFormat = NumberFormat.getInstance();
		this.numberFormat.setMaximumFractionDigits(2);
		this.numberFormat.setMinimumFractionDigits(2);
	}

	private void initGUI() {
		this.flagsList = new HashMap<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setTitle(config.getConfigurationProperty("gui.title"));
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
		// panel_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 120,
		// 215)));
		getContentPane().add(curreinciesPanel);

		JPanel foreignPanel = new JPanel();
		foreignPanel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 120, 215)));
		springLayout.putConstraint(SpringLayout.NORTH, foreignPanel, 6, SpringLayout.SOUTH, curreinciesPanel);
		springLayout.putConstraint(SpringLayout.WEST, foreignPanel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, foreignPanel, 245, SpringLayout.SOUTH, curreinciesPanel);
		SpringLayout sl_curreinciesPanel = new SpringLayout();
		curreinciesPanel.setLayout(sl_curreinciesPanel);

		currenciesListCombo = new JComboBox<Object>();
		currenciesListCombo.setName("toFor");
		currenciesListCombo.addItem("Selecciona una opcion");
//		currencyController.getCurrencies().forEach(moneda -> {
//			currenciesListCombo.addItem(moneda.getTag());
//			flagsList.put(moneda.getTag(), new ImageIcon("../Conversor-de-monedas/src/main/resources/com/athz/assets/"+moneda.getTag()+".png"));
//		});
		flagsList.put("blank", new ImageIcon());
		exchangeController.getExchangeRates().getRates().forEach((k, v) -> {
			currenciesListCombo.addItem(k);
			flagsList.put(k, new ImageIcon( config.getConfigurationProperty("path.images") + k) );
		});

		sl_curreinciesPanel.putConstraint(SpringLayout.NORTH, currenciesListCombo, 10, SpringLayout.NORTH,
				curreinciesPanel);
		sl_curreinciesPanel.putConstraint(SpringLayout.WEST, currenciesListCombo, 100, SpringLayout.WEST,
				curreinciesPanel);
		sl_curreinciesPanel.putConstraint(SpringLayout.SOUTH, currenciesListCombo, 48, SpringLayout.NORTH,
				curreinciesPanel);
		sl_curreinciesPanel.putConstraint(SpringLayout.EAST, currenciesListCombo, -100, SpringLayout.EAST,
				curreinciesPanel);
		/*
		 * currenciesListCombo.addItem("Selecciona una moneda");
		 * currenciesListCombo.addItem("USD"); currenciesListCombo.addItem("EUR");
		 */
		curreinciesPanel.add(currenciesListCombo);
		springLayout.putConstraint(SpringLayout.EAST, foreignPanel, 0, SpringLayout.EAST, localPanel);
		SpringLayout sl_localPanel = new SpringLayout();
		localPanel.setLayout(sl_localPanel);

		localFlag = new JLabel();
		sl_localPanel.putConstraint(SpringLayout.NORTH, localFlag, 0, SpringLayout.NORTH, localPanel);
		sl_localPanel.putConstraint(SpringLayout.SOUTH, localFlag, 100, SpringLayout.NORTH, localPanel);
		localFlag.setIcon(new ImageIcon(config.getConfigurationProperty("path.images") + config.getConfigurationProperty("api.base")));
		//
		
		localFlag.setHorizontalAlignment(SwingConstants.CENTER);
		sl_localPanel.putConstraint(SpringLayout.WEST, localFlag, 10, SpringLayout.WEST, localPanel);
		sl_localPanel.putConstraint(SpringLayout.EAST, localFlag, 354, SpringLayout.WEST, localPanel);
		localPanel.add(localFlag);

		localCurrencyTextField = new JTextField();
		localCurrencyTextField.setName("local");
		sl_localPanel.putConstraint(SpringLayout.NORTH, localCurrencyTextField, 70, SpringLayout.SOUTH, localFlag);
		sl_localPanel.putConstraint(SpringLayout.WEST, localCurrencyTextField, 50, SpringLayout.WEST, localFlag);
		sl_localPanel.putConstraint(SpringLayout.SOUTH, localCurrencyTextField, -10, SpringLayout.SOUTH, localPanel);
		sl_localPanel.putConstraint(SpringLayout.EAST, localCurrencyTextField, -50, SpringLayout.EAST, localFlag);
		localCurrencyTextField.setBackground(new Color(246, 246, 246));
		localCurrencyTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		localCurrencyTextField.setText("0.00");
		localCurrencyTextField.setHorizontalAlignment(SwingConstants.CENTER);
		localPanel.add(localCurrencyTextField);
		localCurrencyTextField.setColumns(10);
		localCurrencyTextField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 120, 215)));

		localCurrencyTag = new JLabel(config.getConfigurationProperty("api.base"));
		localCurrencyTag.setFont(new Font("Tahoma", Font.PLAIN, 32));
		localCurrencyTag.setHorizontalAlignment(SwingConstants.CENTER);
		sl_localPanel.putConstraint(SpringLayout.NORTH, localCurrencyTag, 6, SpringLayout.SOUTH, localFlag);
		sl_localPanel.putConstraint(SpringLayout.WEST, localCurrencyTag, 10, SpringLayout.WEST, localPanel);
		sl_localPanel.putConstraint(SpringLayout.SOUTH, localCurrencyTag, 40, SpringLayout.SOUTH, localFlag);
		sl_localPanel.putConstraint(SpringLayout.EAST, localCurrencyTag, 0, SpringLayout.EAST, localFlag);
		localPanel.add(localCurrencyTag);

		localCurrencyName = new JLabel(config.getConfigurationProperty(config.getConfigurationProperty("api.base")));
		localCurrencyName.setHorizontalAlignment(SwingConstants.CENTER);
		sl_localPanel.putConstraint(SpringLayout.NORTH, localCurrencyName, 6, SpringLayout.SOUTH, localCurrencyTag);
		sl_localPanel.putConstraint(SpringLayout.WEST, localCurrencyName, 0, SpringLayout.WEST, localFlag);
		sl_localPanel.putConstraint(SpringLayout.EAST, localCurrencyName, 0, SpringLayout.EAST, localFlag);
		localPanel.add(localCurrencyName);
		getContentPane().add(foreignPanel);
		SpringLayout sl_foreignPanel = new SpringLayout();
		foreignPanel.setLayout(sl_foreignPanel);

		foreignFlag = new JLabel();
		sl_foreignPanel.putConstraint(SpringLayout.WEST, foreignFlag, 10, SpringLayout.WEST, foreignPanel);
		sl_foreignPanel.putConstraint(SpringLayout.SOUTH, foreignFlag, 0, SpringLayout.SOUTH, foreignPanel);
		sl_foreignPanel.putConstraint(SpringLayout.EAST, foreignFlag, 354, SpringLayout.WEST, foreignPanel);
		foreignFlag.setHorizontalAlignment(SwingConstants.CENTER);
		foreignPanel.add(foreignFlag);

		foreignCurrencyTag = new JLabel();
		sl_foreignPanel.putConstraint(SpringLayout.SOUTH, foreignCurrencyTag, 50, SpringLayout.NORTH, foreignPanel);
		foreignCurrencyTag.setFont(new Font("Tahoma", Font.PLAIN, 32));
		foreignCurrencyTag.setHorizontalAlignment(SwingConstants.CENTER);
		sl_foreignPanel.putConstraint(SpringLayout.NORTH, foreignFlag, 87, SpringLayout.SOUTH, foreignCurrencyTag);
		sl_foreignPanel.putConstraint(SpringLayout.NORTH, foreignCurrencyTag, 10, SpringLayout.NORTH, foreignPanel);
		sl_foreignPanel.putConstraint(SpringLayout.WEST, foreignCurrencyTag, 10, SpringLayout.WEST, foreignPanel);
		sl_foreignPanel.putConstraint(SpringLayout.EAST, foreignCurrencyTag, -10, SpringLayout.EAST, foreignPanel);
		foreignPanel.add(foreignCurrencyTag);

		foreignCurrencyName = new JLabel();
		foreignCurrencyName.setHorizontalAlignment(SwingConstants.CENTER);
		sl_foreignPanel.putConstraint(SpringLayout.NORTH, foreignCurrencyName, 6, SpringLayout.SOUTH,
				foreignCurrencyTag);
		sl_foreignPanel.putConstraint(SpringLayout.WEST, foreignCurrencyName, 0, SpringLayout.WEST, foreignFlag);
		sl_foreignPanel.putConstraint(SpringLayout.EAST, foreignCurrencyName, 0, SpringLayout.EAST, foreignFlag);
		foreignPanel.add(foreignCurrencyName);

		foreignCurrencyTextField = new JTextField();
		foreignCurrencyTextField.setName("Extranjera");
		foreignCurrencyTextField.setEnabled(false);
		foreignCurrencyTextField.setHorizontalAlignment(SwingConstants.CENTER);
		foreignCurrencyTextField.setText("0.00");
		foreignCurrencyTextField.setBackground(new Color(246, 246, 246));
		foreignCurrencyTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		sl_foreignPanel.putConstraint(SpringLayout.NORTH, foreignCurrencyTextField, 6, SpringLayout.SOUTH,
				foreignCurrencyName);
		sl_foreignPanel.putConstraint(SpringLayout.WEST, foreignCurrencyTextField, 50, SpringLayout.WEST, foreignPanel);
		sl_foreignPanel.putConstraint(SpringLayout.SOUTH, foreignCurrencyTextField, 60, SpringLayout.SOUTH,
				foreignCurrencyName);
		sl_foreignPanel.putConstraint(SpringLayout.EAST, foreignCurrencyTextField, -50, SpringLayout.EAST,
				foreignPanel);
		foreignPanel.add(foreignCurrencyTextField);
		foreignCurrencyTextField.setColumns(10);
		foreignCurrencyTextField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 120, 215)));

	}

	private void clearTextFields() {
		foreignCurrencyTag.setText("");
		foreignCurrencyName.setText("");
		foreignFlag.setIcon(flagsList.get("blank"));
		foreignCurrencyTextField.setText("0.0");
		foreignCurrencyTextField.setEnabled(false);
	}

	private void convertCurrency(JTextField local, JTextField foreign) {
		Double amount = Double.valueOf(local.getText());
		String target = String.valueOf(currenciesListCombo.getSelectedItem());
		Double result = local.getName().equals("local") ? exchangeController.toForeignCurrency(amount, target)
				: exchangeController.toLocaleCurrency(amount, target);
		String resFormated = String.valueOf(numberFormat.format(result));
		// System.out.println(resFormated);
		Log.logger.info("Resultado: "+resFormated);
		foreign.setText(resFormated);
	}

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
}
