package graphics;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class InputDialog extends JPanel
{
	
	String[] _msg;
	ArrayList<JLabel> textLabels;
	JPanel labelPanel;
	JPanel textPanel;
	public static final String[] LABEL_TEXTS = { "Bemenet", "Kimenet"};
	public static int COLS = 2;
	private String _title;
	private Map<String, JTextField> labelFieldMap = new HashMap<>();
	
	InputDialog(int inputSize,String[] msg, String title)
	{
		COLS = inputSize;
		_title = title;
		_msg = msg;
		textLabels = new ArrayList<JLabel>();
		labelPanel = new JPanel();
		textPanel = new JPanel();
		setLayout(new GridLayout(3,0));
		for(String s: _msg) {
			textLabels.add(new JLabel(s));
			
		}
		for(JLabel l: textLabels)
		{
			labelPanel.add(l);
		}
		this.add(labelPanel);
		
		for (int i = 0; i < COLS; i++) {
			String labelTxt = LABEL_TEXTS[i];
			JTextField textField = new JTextField(COLS);
			labelFieldMap.put(labelTxt, textField);
			
			textPanel.add(new JLabel(labelTxt));
			textPanel.add(textField);
			//setBorder(BorderFactory.createTitledBorder(_title));
		}	
		this.add(textPanel);
	}
	
	public static GridBagConstraints createGbc(int x, int y) 
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 1.0;
		gbc.weighty = gbc.weightx;
		if (x == 0) 
		{
			gbc.anchor = GridBagConstraints.LINE_START;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.insets = new Insets(3, 3, 3, 8);
		}
		else
		{
			gbc.anchor = GridBagConstraints.LINE_END;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(3, 3, 3, 3);
		}
		return gbc;
	}

	public String getText(String labelText)
	{
		JTextField textField = labelFieldMap.get(labelText);
		if (textField != null)
			return textField.getText();
		else
			throw new IllegalArgumentException(labelText);
	}

	public String[] createAndShowGui() 
	{
		//InputDialog mainPanel = new InputDialog(COLS,msg,_title);
		
		int optionType = JOptionPane.DEFAULT_OPTION;
		int messageType = JOptionPane.PLAIN_MESSAGE;
		Icon icon = null;
		String[] options = { "Ok", "Mégse" };
		Object initialValue = options[0];
		int reply = JOptionPane.showOptionDialog(null, this,
				"Add meg a be- és kimeneti indexeket", optionType, messageType, icon, options,
				initialValue);
		String[] res = new String[COLS];
		if (reply == 0) 
		{
			System.out.println("Választék:");

			int i = 0;
			if(COLS > 1) {
				for (String labelText : LABEL_TEXTS)
				{
					if(!labelFieldMap.get(labelText).getText().equals(""))
					res[i] = labelFieldMap.get(labelText).getText();
					else {
						res[i] = "";
					}
					System.out.printf("%12s: %s%n", labelText,
							this.getText(labelText));
					i++;
				}
			}
			else {
				res[0] = labelFieldMap.get(LABEL_TEXTS[0]).getText();
			}
			
		}
		else
		{
			for(int i = 0; i < COLS; i++)
				res[i] = "";
		}
		
		return res;
	}
}