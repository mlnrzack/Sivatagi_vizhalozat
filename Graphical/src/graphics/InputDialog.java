package graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;


public class InputDialog extends JPanel {

	JLabel inptMsg;
	String _msg;
	public static final String[] LABEL_TEXTS = { "Bemenet"};
	public static int COLS = 2;
	private String _title;
	private Map<String, JTextField> labelFieldMap = new HashMap<>();
	InputDialog(int inputSize,String msg, String title){
		COLS = inputSize;
		_title = title;
		_msg = msg;
		inptMsg = new JLabel();
		
		
		inptMsg.setText(msg);
		add(inptMsg, createGbc(0, 1));
		setLayout(new GridBagLayout());
		for (int i = 0; i < COLS; i++) {
			String labelTxt = (i + 1) + ". " + LABEL_TEXTS[i];
			add(new JLabel(labelTxt), createGbc(0, i));

			JTextField textField = new JTextField(COLS);
			labelFieldMap.put(labelTxt, textField);
			add(textField, createGbc(1, i));


			setBorder(BorderFactory.createTitledBorder(_title));
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

	public String[] createAndShowGui() {
		//InputDialog mainPanel = new InputDialog(COLS,msg,_title);
		
		int optionType = JOptionPane.DEFAULT_OPTION;
		int messageType = JOptionPane.PLAIN_MESSAGE;
		Icon icon = null;
		String[] options = { "Ok", "Mégse" };
		Object initialValue = options[0];
		int reply = JOptionPane.showOptionDialog(null, this,
				_msg, optionType, messageType, icon, options,
				initialValue);
		String[] res = new String[COLS];
		if (reply == 0) {
			System.out.println("Választék:");

			int i = 0;
			for (String labelText : LABEL_TEXTS) {
				res[i++] = this.getText(labelText);

				System.out.printf("%12s: %s%n", labelText,
						this.getText(labelText));
			}
		}
		else {
			for(int i = 0; i < COLS; i++)
			{
				res[i] = "";
			}
		}
		return res;
	}
}

