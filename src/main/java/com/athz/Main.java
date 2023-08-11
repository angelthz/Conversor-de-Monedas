package com.athz;

import com.athz.view.MainView;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class Main {
	public static void main(String[] args) {
		FlatMacLightLaf.setup();
		new MainView();
	}
}
