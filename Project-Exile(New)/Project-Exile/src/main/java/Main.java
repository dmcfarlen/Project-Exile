import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main{
    public static void main( String [] args ) throws InterruptedException  {
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

        Conversation conv = new Conversation();

        conv.printTXT("YEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEET");

//        for( int i = 0 ; i < 100 ; i++ ) {
//            System.out.print("removekekabREMOVE KEBAB you are worst turk you are turk smell return to croatia croat brothers can live in our country... you can live in zoo... haha REMOVE KEBAB FROM THE PREMESIS");
//            Thread.sleep( 500 );
//        }
    }
}