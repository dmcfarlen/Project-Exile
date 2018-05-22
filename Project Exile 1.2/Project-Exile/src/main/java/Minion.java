public class Minion {
    private String name;
    private int defense;
    private int baseAttack;
    private double critMultiplier;
    private int health;
    private int critChance;
    private String[] avaliableLoot;

    public static void main(String[] args) 
    {
        playMusic();
    }

    public Minion(String n, int d, int bA, int cM, int h, int cC, String[] aL) 
    {
        name = n;
        defense = d;
        baseAttack = bA;
        critMultiplier = cM;
        health = h;
        critChance = cC;
        avaliableLoot = aL;
    }

    public String getName()
    {
        return name;
    }

    public int getHealth()
    {
        return health;
    }

    public int getAtk() 
    {
        int atk = baseAttack;

        if((int)(Math.random()*critChance) >= 6) 
        {
            atk = (int) Math.round(baseAttack*critMultiplier);
        }

        return atk;
    }

    public String getLoot() 
    {
        int temp = (int) Math.random()*(avaliableLoot.length);
        return avaliableLoot[temp];
    }

    public int attack() 
    {
        return getAtk();
    }

    public void attacked(int damage) 
    {
        health -= (damage - defense);
        died();
    }
    public String died() 
    {
        if(health <= 0) 
        {
            return getLoot();
        }
        return "notdead";
    }

    public static void playMusic() 
    {
    }
}