package display;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerGUI {

	private JFrame frameJukebox;
	private JTextField pathField;
	private JButton openButton;
	private JButton btnOpen_1;
	private File songFile;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					PlayerGUI window = new PlayerGUI("ADHD.mp3");
					window.frameJukebox.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public PlayerGUI(String fileName) {
		initialize(fileName);
	}
	private void initialize(String fileName) {
		frameJukebox = new JFrame();
		frameJukebox.setTitle("Jukebox Player");
		frameJukebox.setBounds(100, 100, 300, 135);
		frameJukebox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameJukebox.setLocationRelativeTo(null);
		frameJukebox.getContentPane().setLayout(null);

		JButton startButton = new JButton("Start");
		startButton.setFont(new Font("Times New Roman", Font.BOLD, 36));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Play Audio
				try {
					Player p = new Player(new FileInputStream(songFile));
					p.play();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No File Selected!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		startButton.setBounds(10, 42, 264, 50);
		frameJukebox.getContentPane().add(startButton);

		pathField = new JTextField();
		pathField.setForeground(Color.DARK_GRAY);
		pathField.setEditable(false);
		pathField.setText("Song Path");
		pathField.setBounds(10, 11, 165, 20);
		frameJukebox.getContentPane().add(pathField);
		pathField.setColumns(10);

		openButton = new JButton("Open");
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open(fileName);
			}
		});
		openButton.setBounds(185, 10, 89, 23);
		frameJukebox.getContentPane().add(openButton);

		btnOpen_1 = new JButton("Open");
		btnOpen_1.setBounds(185, 10, 89, 23);
		frameJukebox.getContentPane().add(btnOpen_1);
	}
	private void open(String fileName) {
		try {
			JFileChooser chooser = new JFileChooser();
			songFile = new File((fileName));
			pathField.setText(songFile.getAbsolutePath());

			if(!songFile.getName().endsWith(".mp3")) {
				JOptionPane.showMessageDialog(null, "Invalid File Type Selected!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
