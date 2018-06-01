public class ItemList
{
    private Consumeable startPotion = new Consumeable("Beggar's health potion", "Heals 10 health", 2, 10);
    private Equipment startSword = new Equipment("Rusty blade", "Attack 5", "weapon", 1, 5);
    
    public Consumeable badPotion = new Consumeable("Lesser health potion", "Heals 10 health", 1, 15);
    public Consumeable potion = new Consumeable("Health potion", "Heals 15 health", 1, 15);
    public Consumeable goodPotion = new Consumeable("Greater health potion", "Heals 25 health", 1, 25);
    
    public Equipment ironSword = new Equipment("Iron blade", "Attack 7", "weapon", 1, 7);
    public Equipment steelSword = new Equipment("Steel blade", "Attack 9", "weapon", 1, 9);
    public Equipment goldSword = new Equipment("Gold imbued blade", "Attack 11", "weapon", 1, 11);
    public Equipment goldGolemGauntlet = new Equipment("Golden Golem Gauntlet", "Attack 12", "weapon", 1, 12);
    public Equipment dragonSword = new Equipment("Dragonsteel blade", "Attack 13", "weapon", 1, 13);
    public Equipment hellfireSword = new Equipment("Hellfire steel blade", "Attack 15", "weapon", 1, 15);
    
    public Equipment chainArmor = new Equipment("Chainmail armor", "Defense 2", "armor", 1, 2);
    public Equipment plateArmor = new Equipment("Plate armor", "Defense 5", "armor", 1, 5);
    public Equipment goldArmor = new Equipment("Gold imbued plate armor", "Defense 7", "armor", 1, 7);
    public Equipment dragonsteelArmor = new Equipment("Dragonsteel plate armor", "Defense 8", "armor", 1, 8);
    public Equipment satanBlessedArmor = new Equipment("Satan's own blessed plate armor", "Defense 12", "armor", 1, 12);
    
    public Consumeable getStartPotion()
    {
        return startPotion;
    }
    
    public Equipment getStartSword()
    {
        return startSword;
    }
}