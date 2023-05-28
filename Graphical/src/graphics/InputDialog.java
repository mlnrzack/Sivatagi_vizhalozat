package graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;


public class InputDialog extends JPanel {

	JLabel inptMsg;
	public static final String[] LABEL_TEXTS = { "Bemenet", "Kimenet"};
	public static final int COLS = 2;
	private Map<String, JTextField> labelFieldMap = new HashMap<>();
	InputDialog(String msg){

		inptMsg = new JLabel();
		inptMsg.setText(msg);
		add(inptMsg);
		setLayout(new GridBagLayout());
		for (int i = 0; i < LABEL_TEXTS.length; i++) {
			String labelTxt = LABEL_TEXTS[i];
			add(new JLabel(labelTxt), createGbc(0, i));

			JTextField textField = new JTextField(COLS);
			labelFieldMap.put(labelTxt, textField);
			add(textField, createGbc(1, i));


			setBorder(BorderFactory.createTitledBorder("Add meg a be- és kimenetet az indexek alapján"));
		}	

	}
	public static GridBagConstraints createGbc(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 1.0;
		gbc.weighty = gbc.weightx;
		if (x == 0) {
			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(3, 3, 3, 8);
		} else {
			gbc.anchor = GridBagConstraints.LINE_END;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(3, 3, 3, 3);
		}
		return gbc;
	}

	public String getText(String labelText) {
		JTextField textField = labelFieldMap.get(labelText);
		if (textField != null) {
			return textField.getText();
		} else {
			throw new IllegalArgumentException(labelText);
		}
	}

	public static String[] createAndShowGui(String msg) {
		InputDialog mainPanel = new InputDialog(msg);

		int optionType = JOptionPane.DEFAULT_OPTION;
		int messageType = JOptionPane.PLAIN_MESSAGE;
		Icon icon = null;
		String[] options = { "Ok", "Mégse" };
		Object initialValue = options[0];
		int reply = JOptionPane.showOptionDialog(null, mainPanel,
				"Pumpa Beállítása", optionType, messageType, icon, options,
				initialValue);
		String[] res = {};
		if (reply == 0) {
			System.out.println("Választék:");

			int i = 0;
			for (String labelText : LABEL_TEXTS) {
				res[i++] = mainPanel.getText(labelText);

				System.out.printf("%12s: %s%n", labelText,
						mainPanel.getText(labelText));


			}
		}
		else {
			res = new String[]{"",""};

			
		}
		return res;
	}
}

