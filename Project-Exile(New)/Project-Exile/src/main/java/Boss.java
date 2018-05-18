import com.fasterxml.jackson.annotation.JsonIgnore;

public class Boss extends Minion
{
    @JsonIgnore
    public Conversation dialogue;

    public Boss(String n, int d, int bA, int cM, int h, int cC, String[] aL)
    {
        super(n, d, bA, cM, h, cC, aL);
        dialogue = new Conversation();

    }

    public void playIntroDialogue()
    {
        dialogue.printTXT("");
    }

    public static void playMusic()
    {

    }

    public static void revive()
    {

    }
}