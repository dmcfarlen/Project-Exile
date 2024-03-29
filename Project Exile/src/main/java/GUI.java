import java.awt.Font;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import com.almasb.fxgl.app.FXGL;

/**
 * @author MatthewSheldon
 */

public class GUI{
	@SuppressWarnings("resource")
	public static void main( String [] args ) throws InterruptedException, IOException  {
		// Remove "Desktop will be used by default error"      
		System.out.println(FXGL.isDesktop());

		// Create the basic window and set it to be in the middle of the screen
		JFrame frame = new JFrame();
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// make the frame half the height and width
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		
		// center the jframe on screen
		frame.setLocation((width/2) - 400, (height/2)-300);

		frame.setVisible(true);
		frame.setFont(new Font("Serif", Font.ITALIC, 14));
		// Add the title at the top of the screen
		frame.add(new JLabel("                                                                                                              "
				+ "Project: Exile "), BorderLayout.NORTH); // Put a title at the top, middle of the window.

		// Allows the user to be able to interact with the "console"
		JTextArea textArea = new JTextArea();
		// Change Background Color
		textArea.setBackground(new Color(160, 159, 157));
		// Change Color, font, and '|' of the text in the output
		textArea.setFont(new Font("Serif", Font.ITALIC, 14)); // Set the font of the user input to be Serif Italic
		textArea.setForeground(new Color(127, 91, 44));
		textArea.setCaretColor(new Color(255, 255, 255));
		Window window = new Window(textArea, Integer.MAX_VALUE); // Send the "Window" class the TextArea object so it can be used.

		PrintStream ps = new PrintStream( window );
		PipedInputStream pin = new PipedInputStream();
		PipedOutputStream pout = new PipedOutputStream(pin);
		textArea.setEditable(false);

		// Changes how the computer reads and displays text
		System.setOut(ps);
		System.setErr(ps);
		System.setIn(pin);
		JTextField field = new JTextField();
		//Changes the Text Color and the '|' color where the character will be placed
		field.setForeground(new Color(124, 54, 0));
		field.setCaretColor(new Color(255, 255, 255));
		// Background color of user input window section
		field.setBackground(new Color(168, 165, 161));
		// Colors for highlighted text -- easier to see
		field.setSelectionColor(new Color(255, 255, 255));
		field.setSelectedTextColor(new Color(0, 0, 0));

		field.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if(e.getKeyChar() == '\n')
				{
					try
					{
						System.out.println(); //Doesn't print out user input, instead leaves a blank line (makes it look cleaner)
						//System.out.println(field.getDocument().getText(0, field.getDocument().getLength()));
						pout.write((field.getDocument().getText(0, field.getDocument().getLength()) + "\n").getBytes());
						field.getDocument().remove(0, field.getDocument().getLength());
					}
					catch (IOException ex)
					{
						Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
					}
					catch (BadLocationException ex)
					{
						Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}
				);

		frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
		frame.getContentPane().add(field, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible( true );
		frame.setSize(800,600);

		// Switch over to the game
		Main.main(args);
	}
}