import com.fasterxml.jackson.annotation.JsonIgnore;

public class Player 
{
    public String name;
    public int maxHealth;
    public int health;
    public int mana;
    public int exp;
    public int defense;
    public int level;
    public int attack;
    public double critChance;
    public double critMult;
    @JsonIgnore
    private boolean death;


    public int getAtk() 
    {
        int atk = attack;

        if((int)(Math.random()*critChance) >= 6) 
        {
            atk = (int) Math.round(attack*critMult);
        }

        return atk;
    }

    public int attack() 
    {
        return getAtk();
    }

    public String dead()
    {
        return "You Died!";
    }

    public int getHealth()
    {
        return health;
    }
    
    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void attacked(int damage) 
    {
        health -= (damage - defense);
    }

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

    public void setAttack(int attack)
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
    

    public void setDeath(boolean death)
    {
        this.death = death;
    }
}