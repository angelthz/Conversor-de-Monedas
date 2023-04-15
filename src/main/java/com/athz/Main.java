package com.athz;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.athz.view.MainView;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class Main {
	public static void main(String[] args) {
		FlatMacLightLaf.setup();
		new MainView();
	}
}
