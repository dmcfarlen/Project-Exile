import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main{
    private static Conversation text = new Conversation();
    private static Player player;

    public static void main( String [] args ) throws InterruptedException 
    {
        JFrame frame = new JFrame();
        frame.add( new JLabel(" Outout" ), BorderLayout.NORTH );

        JTextArea ta = new JTextArea();
        TextAreaOutputStream taos = new TextAreaOutputStream( ta, 60 );
        PrintStream ps = new PrintStream( taos );
        System.setOut( ps );
        System.setErr( ps ); 
        
        frame.add( new JScrollPane( ta )  );

        frame.pack();
        frame.setVisible( true );
        frame.setSize(800,600);
        
        Load load = new Load();
        player = load.player;
        dungeon1();
    }
    
    public static void dungeon1() {
        String[] loot = new String[5];
        Minion minion1 = new Minion("Troll", 3, 2, 2, 10, 2, new String[4]);
        Minion minion2 = new Minion("Troll", 3, 2, 2, 10, 2, new String[4]);
        Minion minion3 = new Minion("Troll", 3, 2, 2, 10, 2, new String[4]);
        Minion[] minionList = {minion1, minion2, minion3};
        Dungeon1 d1 = new Dungeon1("Dungeon1", 3, loot, minionList, false);
        text.printTXT("You enter the dungeon");
        text.printTXT("You fight a troll");
        for(int i = 0; i < minionList.length; i++) {
            Combat combat = new Combat(minionList[i], player);
        }
    }
}