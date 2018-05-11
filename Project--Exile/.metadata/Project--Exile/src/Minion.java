public class Minion {
	private String name;
	private int defense;
	private int baseAttack;
	private double critMultiplier;
	private int health;
	private int critChance;
	private String[] avaliableLoot;

	public Minion(String n, int d, int bA, int cM, int h, int cC, String[] aL) {
		name = n;
		defense = d;
		baseAttack = bA;
		critMultiplier = cM;
		health = h;
		critChance = cC;
		avaliableLoot = aL;
	}
	public int getCrit() {
		if((int)(Math.random()*critChance) >= 6) {
			return (int) Math.round(baseAttack*critMultiplier);
		}
		return 0;
	}
	public String getLoot() {
		int temp = (int) Math.random()*(avaliableLoot.length);
		return avaliableLoot[temp];
	}
	public int attack() {
		return baseAttack + getCrit();
	}
	public void attacked(int damage) {
		health-=damage;
		died();
	}
	public String died() {
		if(health <= 0) {
			return getLoot();
		}
		return "notdead";
	}
}
