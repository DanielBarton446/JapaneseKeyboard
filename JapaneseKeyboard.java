import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class JapaneseKeyboard
{

	static String buffer = ""; //used in printText
	static String japanese = ""; //used for label to display japanese
	static JTextField textField = new JTextField(); //input

	public static void printText(JLabel label, char c)
	{

		//Handle Backspacing
		if (c == KeyEvent.VK_BACK_SPACE) 
		{
				if (buffer.length() == 0)
				{
					//remove last japanese character
					String lastChar = Character.toString(japanese.charAt(japanese.length() - 1));
					String temp = "";
					for(int i = 0; i < japanese.length() - 1; i++)
						temp += japanese.charAt(i);
					japanese = temp;
					//search keys for value of last japanese character
					String key = JapaneseKeyCodes.getKey(lastChar);
					//remove last character of key and add that to buffer
					temp = "";
					for (int j = 0; j < key.length() - 1; j++)
						temp +=key.charAt(j);
					buffer = temp;
				}
				else
				{
					char[] cBuffer = buffer.toCharArray();
					buffer = "";
					for (int i = 0; i < cBuffer.length - 1; i++) 
						buffer += cBuffer[i];			
				}
		}
		else 
		{
			buffer += Character.toString(c);
		}

		//copies text to clipboard when enter key is pressed and resets japanese string -- make into own helper function?
		if(c == KeyEvent.VK_ENTER)
		{
			StringSelection stringSelection = new StringSelection(japanese);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			textField.setText("");
			japanese = "";
			buffer = "";
		}
		


		if(JapaneseKeyCodes.toHiragana.get(buffer) != null)
		{
			japanese += JapaneseKeyCodes.toHiragana.get(buffer); 
			buffer = "";
		} 

		label.setText(japanese);
	}


	public static void main(String[] args)
	{
		JFrame frame = new JFrame("English to Hiragana");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int w = 800;
		int h = 800;
		frame.setSize(w,h);
		JLabel toHiragana = new JLabel("");
		toHiragana.setBounds(400,400,w,h);
		toHiragana.setFont(new Font(toHiragana.getName(), Font.PLAIN, 36));
		frame.add(toHiragana);

		//initialize hashmap dictionary
		JapaneseKeyCodes.populateMap();

		Container contentPane = frame.getContentPane();

		KeyListener listener = new KeyListener() 
		{

			@Override
			public void keyPressed(KeyEvent event)
			{
			}

			@Override
			public void keyReleased(KeyEvent event)
			{
			}

			@Override
			public void keyTyped(KeyEvent event)
			{
				printText(toHiragana, event.getKeyChar());
				printEventInfo(" Key Typed ", event);
			}

			private void printEventInfo(String str, KeyEvent e)
			{
				System.out.println(str);
				System.out.println("   Char: " + e.getKeyChar());
 
			}

			private String keyboardLocation(int keybrd)
			{
				switch(keybrd)
				{
					case KeyEvent.KEY_LOCATION_RIGHT:
					  	return "Right";
 
  					case KeyEvent.KEY_LOCATION_LEFT:
 						return "Left";
 
					case KeyEvent.KEY_LOCATION_NUMPAD:
 						return "NumPad";
 					
 					case KeyEvent.KEY_LOCATION_STANDARD:
						return "Standard";
 					
 					case KeyEvent.KEY_LOCATION_UNKNOWN:	
  						default:
 							return "Unknown";
 
			    }
 

			}
		};


		textField.addKeyListener(listener);
		contentPane.add(textField, BorderLayout.NORTH); //adds textfield to northmost position of contentpane
		frame.setVisible(true);

	}

}