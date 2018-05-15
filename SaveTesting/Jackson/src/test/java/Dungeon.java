
public class Dungeon {
	public boolean completed;
	
	public void setCompleted(boolean completed)
	{
		this.completed = completed;
	}
	
	public String isCompleted()
	{
		if(completed)
			return "Dungeon is completed";
		else
			return "Dungeon is not completed";
	}
}
