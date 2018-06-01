import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.*;

public class Main
{
	private static Conversation text = new Conversation();
	private static Player player;
	private static int count = 0;
	private static Scanner input;
	private static ItemList item;

	public static void main(String [] args) throws InterruptedException, JsonParseException, JsonMappingException, IOException
	{
		input = new Scanner(System.in);
		item =  new ItemList();
		//text.printTXT("You wake up with a throbbing headache, seeing a forest aflame in front of your eyes.\nYou pass out again.\nDarkness.\n\nYou wake up to see yourself surrounded by the charred remains of the forest. \"What happened?\" you think to yourself.\nYou walk to the edge of a cliff to see a broken sign... \"One mile to Hoggiest Warts\", your home town.\nYou look up to see only a burning city.\n");

		text.printTXT("Choose an option");
		System.out.println("1. New\n2. Load");
		int c = nextInt();

		if(c == 1)
		{
			//text.printTXT("You run down to the town only to be stopped by a dying beggar. His wounds are dire, he will not last long! In a weak voice, he asks...\n");

			newSave();
			if(count == 1)
				dungeon2();
		}
		else if(c == 2)
		{
			load();
			dungeon1();
		}
		else if(c >= 3)
		{
			System.out.println("Pick an actual option next time");
			System.exit(0);
		}
	}
	//TODO make a stat point system
	public static void newSave() throws JsonParseException, JsonMappingException, IOException
	{
		input = new Scanner(System.in);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String name = null;



		System.out.println("\"What is your name?\"");
		System.out.print("Enter name: ");
		while(name == null && name != "")
		{
			name = input.nextLine();
			player = new Player(name);
			player.addConsume(item.getStartPotion());
			player.addEquip(item.getStartSword());
		}

		mapper.writeValue(new File("save.json"), player);
		count++;

		//text.printTXT("\"" + player.name + ", you will be the savior of this town. The dragon ogre has destroyed our town. Avenge the town and your family.\" He hands you a rusty blade and two health potions.\nYou glance at the blade to see a glimmer of gold under the layers of red rust.\nYou see a path of destruction heading to the hills. You follow the path.\nAfter miles of running, you come across a bird legged hut. The path leads inside.\nYou hear an evil whisper from within.");
	}

	public static void load() throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();

		player = mapper.readValue(new File("save.json"), Player.class);
	}
	public static void save() throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		mapper.writeValue(new File("save.json"), player);

		System.out.println("Game saved successfully");
	}

	public static void dungeon1() throws IOException
	{
		Combat combat;
		ItemList loot = new ItemList();
		Minion minion1 = new Minion("Green Hag", 3, 2, 2, 10, 2, loot.ironSword, loot.badPotion, player);
		Minion minion2 = new Minion("Sea Hag", 3, 2, 2, 10, 2, loot.ironSword, loot.badPotion, player);
		Minion minion3 = new Minion("Night Hag", 3, 2, 2, 10, 2, loot.ironSword, loot.potion, player);
		Boss boss1 = new Boss("Baba Yaga the Haggiest Hag", 3, 2, 2, 10, 2, loot.steelSword, loot.goodPotion, player);
		Minion[] minionList = {minion1, minion2, minion3, boss1};
		text.printTXT("You enter the dungeon, where a coven of hags reside. (Make sure to equip your sword before progressing into the dungeon!)\n");
		inventory(2);
		for(int i = 0; i < minionList.length; i++) {
			combat = new Combat(minionList[i], player);
			if(minionList[i].getClass().getName().equals("Minion"))
				text.printTXT("You encounter a " + minionList[i].getName());
			if(minionList[i].getClass().getName().equals("Boss"))
				text.printTXT("You encounter the dungeon boss, " + minionList[i].getName());
			inventory(combat.fight());
		}


		text.printTXT("Baba Yaga speaks with her dying breath, \"Thank you for freeing me, this is the first time I've felt peace in a long time...\"\nA map falls out of her hand as she dies.\nYou pick up the map, unrolling it. Suprisingly, there's a dotted path leading to the next village that the dragon ogre planned on destroying.\nYou decide to head there for clues.\n");
	}

	public static void dungeon2() throws IOException
	{
		Combat combat;
		ItemList loot = new ItemList();
		Minion minion1 = new Minion("Kobold Footman", 3, 2, 2, 10, 2, loot.goldSword, loot.potion, player);
		Minion minion2 = new Minion("Kobold Footman", 3, 2, 2, 10, 2, loot.goldSword, loot.potion, player);
		Minion minion3 = new Minion("Kobold Knight", 3, 2, 2, 10, 2, loot.goldArmor, loot.potion, player);
		Minion minion4 = new Minion("Kobold Knight", 3, 2, 2, 10, 2, loot.goldArmor, loot.potion, player);
		Minion minion5 = new Minion("Kobold Footman", 3, 2, 2, 10, 2, loot.goldSword, loot.potion, player);
		Boss boss1 = new Boss("Magmon the Golem", 3, 2, 2, 10, 2, loot.goldGolemGauntlet, loot.goodPotion, player);
		Minion[] minionList = {minion1, minion2, minion3, minion4, minion5, boss1};
		//text.printTXT("As you leave the town, you feel something warming up in your pocket.\nYou look down to see a faint glow, and immediately reach into your pocket.\nYour map! As it glows, a new path appears.\nYou decide to follow it, believing it will lead you closer to the dragon ogre.\nUnlike the last path of destruction, this path is nice and there is even a trail of gold plated torches lighting the way!\nYou follow the path to the mouth of a cave. You look in to see a gold furnished cave, with lava flowing from every orifice.\nProceeding cautiously, you enter your next dungeon.\nYou enter the dungeon. Here, deep underground, the kobolds defend their treasures.");
		inventory(2);
		for(int i = 0; i < minionList.length; i++) {
			combat = new Combat(minionList[i], player);
			if(minionList[i].getClass().getName().equals("Minion"))
				text.printTXT("You encounter a " + minionList[i].getName());
			if(minionList[i].getClass().getName().equals("Boss"))
			{
				text.printTXT("You kill all the kobolds, feeling let down because there are no more clues to lead you to the dragon ogre.\nAs you move to a pile of gold coins to make up for your seemingly pointless quest, you hear the roar of a mechanical engine, and the floor beneath you begins to tremble.\nYou see a massive dwarven golem rising out of the pile of gold coins.");
				text.printTXT("You encounter the dungeon boss, " + minionList[i].getName());
			}
			inventory(combat.fight());
		}


		text.printTXT("With your final blow, the sputtering golem's knees give out.\nHe falls back into the lava, producing a horrific metalic screech.\nAfter a good two minutes, he is melted.\nA small crystal bobs out of the top of the pool of lava. Puzzling to you, you grab for your sword, and fish the crystal out of the lava.\nThe crystal is, shockingly, cool to the touch. As you peer into the crystal, you see movement! The map begins to glow once more, revealing yet another path.\nYou're off to the next village, armed with your newfound crystal...");
	}

	/*
    public static void dungeon3()
    {
        Combat combat;
        String[] loot = new String[5];
        Minion minion1 = new Minion("Helmed Horror", 3, 2, 2, 10, 2, new String[4]);
        Minion minion2 = new Minion("Hellhound", 3, 2, 2, 10, 2, new String[4]);
        Minion minion3 = new Minion("Hellhound", 3, 2, 2, 10, 2, new String[4]);
        Minion minion4 = new Minion("Helmed Horror", 3, 2, 2, 10, 2, new String[4]);
        Minion minion5 = new Minion("Hellhound", 3, 2, 2, 10, 2, new String[4]);
        Boss boss1 = new Boss("Azaks the Mind Flayer", 3, 2, 2, 10, 2, new String[4]);
        Minion[] minionList = {minion1, minion2, minion3, minion4, boss1};
        text.printTXT("You enter the dungeon. This is where Azaks, a satanic mind flayer, summons his demonic creatures.");
        inventory(2);
        for(int i = 0; i < minionList.length; i++) {
            combat = new Combat(minionList[i], player);
            if(minionList[i].getClass().getName().equals("Minion"))
                text.printTXT("You encounter a " + minionList[i].getName());
            if(minionList[i].getClass().getName().equals("Boss"))
                text.printTXT("You encounter the dungeon boss, " + minionList[i].getName());
            inventory(combat.fight());
        }


        text.printTXT("You won the fight");
    }


    public static void dungeon4()
    {
        Combat combat;
        String[] loot = new String[5];
        Minion minion1 = new Minion("Kobold Footman", 3, 2, 2, 10, 2, new String[4]);
        Minion minion2 = new Minion("Kobold Knight", 3, 2, 2, 10, 2, new String[4]);
        Minion minion3 = new Minion("Kobold Knight", 3, 2, 2, 10, 2, new String[4]);
        Minion minion4 = new Minion("Kobold Footman", 3, 2, 2, 10, 2, new String[4]);
        Boss boss1 = new Boss("Gux the Greedy", 3, 2, 2, 10, 2, new String[4]);
        Minion[] minionList = {minion1, minion2, minion3, minion4, boss1};
        text.printTXT("You enter the dungeon. Here, deep underground, the kobolds defend their treasures.");
        inventory(2);
        for(int i = 0; i < minionList.length; i++) {
            combat = new Combat(minionList[i], player);
            if(minionList[i].getClass().getName().equals("Minion"))
                text.printTXT("You encounter a " + minionList[i].getName());
            if(minionList[i].getClass().getName().equals("Boss"))
                text.printTXT("You encounter the dungeon boss, " + minionList[i].getName());
            inventory(combat.fight());
        }


        text.printTXT("You won the fight");
    }
	 */
	public static void finalBoss()
	{

	}

	public static void secretDungeon()
	{

	}

	public static void bossRush()
	{

	}

	public static void town()
	{
		//TODO make inn method and exit method

	}

	//TODO Inventory should do something similar to save, but allow you to use and access the inventory
	public static void inventory(int dead) throws IOException
	{
		input = new Scanner(System.in);

		if(dead == 1)
		{
			System.exit(0);
		}

		if(dead == 2)
		{
			System.out.println("Inventory \n1.Consumeable \n2.Equipment \n3.Stats \n4.Save \n5.Exit");
			int choice = nextInt();

			if(choice == 1)
			{
				if(player.consume.size() > 0)
				{
					player.printConsume();
					System.out.println("\n0/Exit");
					int c = nextInt();

					if(c == 0)
						inventory(dead);
					else
					{
						System.out.println(player.consume.get(c-1).getName() + "\n" +
								player.consume.get(c-1).getEffect());

						System.out.println("\nDo you want to use the item?" + "\n" +
								"1.Yes   " + "2.No");
						int i = nextInt();

						if(i == 1)
						{
							player.heal(c);
							System.out.println("The item was used\n");
						}

						inventory(dead);
					}
				}
				else
				{
					System.out.println("You dont have any consumables\n");
					inventory(dead);
				}
			}
			if(choice == 2)
			{
				if(player.equip.size() > 0)
				{
					player.printEquip();
					System.out.println("\n0.Exit");
					int c = nextInt();

					if(c == 0)
						inventory(dead);
					else
					{
						System.out.println(player.equip.get(c-1).getName() + "\n" +
								player.equip.get(c-1).getEffect());

						System.out.println("\nDo you want to equip the item?" + "\n" +
								"1.Yes   " + "2.No");
						int i = nextInt();

						if(i == 1)
						{
							player.equip(c);
							System.out.println("The item was equipped\n");
						}

						inventory(dead);
					}
				}
				else
				{
					System.out.println("You dont have any equipment\n");
					inventory(dead);
				}
			}
			if(choice == 3)
			{
				System.out.println("Name:    " + player.name + "\n" +
						"Health:  " + player.health + "\\" + player.maxHealth + "\n" +
						"Attack:  " + player.attack + "\n" +
						"Defense: " + player.defense);
				inventory(dead);
			}
			if(choice == 4)
			{
				save();
				inventory(dead);
			}
		}

		if(dead == 3)
			System.out.println("How!?!");
	}

	public static int nextInt()
	{
		int c = input.nextInt();
		input.nextLine();

		return c;
	}
}