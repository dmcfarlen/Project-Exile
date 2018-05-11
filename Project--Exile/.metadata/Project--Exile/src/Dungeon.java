
public interface Dungeon {
	public String nameOfDungeon = "";
	public int numberOfMinions = 0;
	public int levelOfDifficulty = 1;
	public String[] avaliableLoot = new String[0];
	public Conversation dialogue = new Conversation();
	public Minion[] minionList = new Minion[0];
	
	public void fightMinion(Minion minion);
	public void playDialogue();
	public String getLoot();
	public void playMusic();
	public void remote();
}
