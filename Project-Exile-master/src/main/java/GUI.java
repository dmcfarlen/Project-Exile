import java.awt.Font;
import java.awt.BorderLayout;
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

public class GUI{

	@SuppressWarnings("resource")
	public static void main( String [] args ) throws InterruptedException, IOException  {
		// Create the basic window
		JFrame frame = new JFrame();
		frame.setFont(new Font("Serif", Font.ITALIC, 14));
		frame.add(new JLabel("                                                                                                              "
				+ "Project: Exile "), BorderLayout.NORTH); // Put a title at the top, middle of the window.

		// Allows the user to be able to interact with the "console"
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 14)); // Set the font of the user input to be Serif Italic 
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

		field.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyTyped(KeyEvent e) 
			{
				if(e.getKeyChar() == '\n') 
				{
					try 
					{
						System.out.println();
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