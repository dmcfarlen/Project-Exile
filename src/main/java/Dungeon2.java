import com.fasterxml.jackson.annotation.JsonIgnore;

public class Dungeon2 implements Dungeon 
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
	
	public Dungeon2(String n, int num, int lev, String[] loot, Conversation diag, Minion[] minions, boolean comp)
	{
		dialogue = new Conversation();
		nameOfDungeon = n;
		numberOfMinions = num;
		levelOfDifficulty = lev;
		avaliableLoot = loot;
		dialogue = diag;
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
			return "Dungeon 2 has been completed";
		else
			return "Dungeon 2 has not been completed";
	}

}
