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

public class KeyEventHandler
{

	static String buffer = "";
	static String japanese = "";
	public static void printText(JLabel label, char c)
	{
		buffer += Character.toString(c);
		
		//copies text to clipboard when enter key is pressed and resets japanese string
		if(c == KeyEvent.VK_ENTER)
		{
		StringSelection stringSelection = new StringSelection(japanese);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		japanese = "";
		buffer = "";
		}

		//TODO: handle backspace
		if(c == KeyEvent.VK_BACK_SPACE)
		{
			System.out.println("BACKSPACE DETECTED");
		}

		if(JapaneseKeyCodes.toJapanese.get(buffer) != null)
		{
			japanese += JapaneseKeyCodes.toJapanese.get(buffer); 
			buffer = "";
		} 

		label.setText(japanese);
	}


	//MESSY PROGRAMMING -- <!Make Backup First!> Clean this up when it is working
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("English to Hiragana");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int w = 800;
		int h = 800;
		frame.setSize(w,h);
		JLabel toJapanese = new JLabel("");
		toJapanese.setBounds(400,400,w,h);
		toJapanese.setFont(new Font(toJapanese.getName(), Font.PLAIN, 36));
		frame.add(toJapanese);

		//initialize hashmap dictionary
		JapaneseKeyCodes.populateMap();

		Container contentPane = frame.getContentPane();

		KeyListener listener = new KeyListener() 
		{

			@Override
			public void keyPressed(KeyEvent event)
			{
				printEventInfo(" Key Pressed ", event);
			}

			@Override
			public void keyReleased(KeyEvent event)
			{
				printEventInfo(" Key Released ", event);				

			}

			//TODO: add to a string buffer the key that has been typed -- this will be used later

			//LOGIC: If the next character is a vowel and the current character in the buffer is a consonant -- conver to consonant and vowel hiragana vers.
			//		 If the next character and the current character in the buffer are vowels -- covert both to vowel hiragana
			//		 remove characters that have been converted
			// 		 if no next character -- convert individual
			//COLLISIONS: "n" hiragana 
			//Solutions: Polling and creating a time variable and "feeling" how quickly it should convert
			//GOALS:
			//	Current -> Create a conversion system to hiragana;
			//		Data Structures involved -- Dictionary/Hashmap

			@Override
			public void keyTyped(KeyEvent event)
			{
				printText(toJapanese, event.getKeyChar());
				printEventInfo(" Key Typed ", event);
			}

			private void printEventInfo(String str, KeyEvent e)
			{
				System.out.println(str);

				int keyCode = e.getKeyCode();

			    System.out.println("   Code: " + KeyEvent.getKeyText(keyCode));
 
			    System.out.println("   Char: " + e.getKeyChar());
 
			    int mods = e.getModifiersEx();
 
			    System.out.println("    Mods: " + KeyEvent.getModifiersExText(mods));
 
				System.out.println("    Location: " + keyboardLocation(e.getKeyLocation()));
 
			    System.out.println("    Action? " + e.isActionKey());
 
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

		JTextField textField = new JTextField();

		textField.addKeyListener(listener);
		contentPane.add(textField, BorderLayout.NORTH);
		frame.setVisible(true);

	}

}