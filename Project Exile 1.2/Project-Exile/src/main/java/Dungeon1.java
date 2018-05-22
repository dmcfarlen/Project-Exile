import com.fasterxml.jackson.annotation.JsonIgnore;

public class Dungeon1 implements Dungeon 
{
    @JsonIgnore
    public String nameOfDungeon;
    @JsonIgnore
    public int numberOfMinions;
    @JsonIgnore
    public int levelOfDifficulty;
    @JsonIgnore
    public String[] avaliableLoot;
    @JsonIgnore
    public Conversation dialogue;
    @JsonIgnore
    public Minion[] minionList;
    public boolean completed;
    
    public Dungeon1(String n, int num, String[] loot, Minion[] minions, boolean comp)
    {
        dialogue = new Conversation();
        nameOfDungeon = n;
        numberOfMinions = num;
        avaliableLoot = loot;
        minionList = minions;
        completed = comp;
    }
    public void fightMinion(Minion minion) 
    {

    }

    public void playDialogue(String txt) 
    {
        dialogue.printTXT(txt);
    }

    @JsonIgnore
    public String getLoot() 
    {
        return null;
    }

    public void playMusic()
    {
        
    }

    public void remote() 
    {

    }

    public void setCompleted(boolean completed) 
    {
        this.completed = completed;
    }

    public String isCompleted() 
    {
        if(completed)
            return "Dungeon 1 has been completed";
        else
            return "Dungeon 1 has not been completed";
    }

}