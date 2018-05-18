import java.util.*;
import java.util.concurrent.TimeUnit;
public class Conversation 
{
    Jukebox n = new Jukebox();
    public void printTXT(String t)
    {
        Jukebox n = new Jukebox();
        TimeUnit u = TimeUnit.MILLISECONDS;
        long time = 165;
        for(char ch:t.toCharArray())
        {
            n.playSound("Boss Minions.wav");
            System.out.print(ch);
            try
            {
            u.sleep(time);
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}