package de.slag.common.util;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GuiUtils {

	public static JDialog generateDialog(String title, String text) {
		final JOptionPane pane = new JOptionPane(text);
		final JDialog d = pane.createDialog((JFrame) null, title);
		d.setLocation(10, 10);
		d.setVisible(true);
		return d;
	}

}
