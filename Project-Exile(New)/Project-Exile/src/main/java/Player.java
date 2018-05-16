import com.fasterxml.jackson.annotation.JsonIgnore;

public class Player 
{
	public String name;
	public int health;
	public int mana;
	public int exp;
	public int defense;
	public int level;
	public int attack;
	public double critChance;
	public double critMult;
	public boolean completed;
	@JsonIgnore
	private Dungeon1 test = new Dungeon1();
	@JsonIgnore
	private boolean death;
	
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void setHealth(int health) 
	{
		this.health = health;
	}
	
	public void setMana(int mana) 
	{
		this.mana = mana;
	}
	
	public void setExp(int exp) 
	{
		this.exp = exp;
	}
	
	public void setDefense(int defense)
	{
		this.defense = defense;
	}
	
	public void setLevel(int level) 
	{
		this.level = level;
	}
	
	public void setAtack(int attack)
	{
		this.attack = attack;
	}
	
	public void setCritChance(double critChance)
	{
		this.critChance = critChance;
	}
	
	public void setCritMult(double critMult)
	{
		this.critMult = critMult;
	}

	public void setCompleted(boolean completed)
	{
		this.completed = completed;
	}
	
	public void setDungeonCompleted(boolean comp)
	{
		test.setCompleted(comp);
	}
	
	public String getDungeonCompleted()
	{
		return test.isCompleted();
	}
	
	public void setDeath(boolean death)
	{
		this.death = death;
	}
}
