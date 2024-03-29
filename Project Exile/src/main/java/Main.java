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

	public static void main(String [] args) throws InterruptedException, JsonParseException, JsonMappingException, IOException
	{
		input = new Scanner(System.in);
		text.printTXT("You wake up with a throbbing headache, seeing a forest aflame in front of your eyes.\nYou pass out again.\nDarkness.\n\nYou wake up to see yourself surrounded by the charred remains of the forest. \"What happened?\" you think to yourself.\nYou walk to the edge of a cliff to see a broken sign... \"One mile to Hoggiest Warts\", your home town.\nYou look up to see only a burning city.\n");

		text.printTXT("Choose an option");
		System.out.println("1. New\n2. Load");
		int c = nextInt();

		if(c == 1)
		{
			text.printTXT("You run down to the town only to be stopped by a dying beggar. His wounds are dire, he will not last long! In a weak voice, he asks...\n");

			newSave();
			if(count == 1)
			{
				dungeon1();
			}
		}
		else if(c == 2)
		{
			load();
			if(player.town == 1)
				town1();
			else if(player.town == 2)
				town2();
			else if(player.town == 3)
				town3();
		}
		else if(c >= 3)
		{
			System.out.println("Pick an actual option next time");
			System.exit(0);
		}
	}

	public static void newSave() throws JsonParseException, JsonMappingException, IOException
	{
		input = new Scanner(System.in);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		String name = null;



		System.out.println("\"What is your name?\"");
		System.out.print("Enter name...");
		while(name == null && name != "")
		{
			name = input.nextLine();
			player = new Player(name);
			player.addConsume(ItemList.startPotion);
			player.addEquip(ItemList.startSword);
		}

		mapper.writeValue(new File("save.json"), player);
		count++;

		text.printTXT("\"" + player.name + ", you will be the savior of this town. The dragon ogre has destroyed our town. Avenge the town and your family.\" He hands you a rusty blade and two health potions.\nYou glance at the blade to see a glimmer of gold under the layers of red rust.\nYou see a path of destruction heading to the hills. You follow the path.\nAfter miles of running, you come across a bird legged hut. The path leads inside.\nYou hear an evil whisper from within.");
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
		Minion minion1 = new Minion("Green Hag", 3, 2, 2, 10, 2, ItemList.ironSword, ItemList.badPotion, player);
		Minion minion2 = new Minion("Sea Hag", 2, 3, 2, 7, 2, ItemList.ironSword, ItemList.badPotion, player);
		Minion minion3 = new Minion("Night Hag", 0, 4, 2, 5, 2, ItemList.ironSword, ItemList.potion, player);
		Boss boss1 = new Boss("Baba Yaga the Haggiest Hag", 3, 3, 2, 10, 2, ItemList.steelSword, ItemList.goodPotion, player);
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
		town1();
	}

	public static void dungeon2() throws IOException
	{
		Combat combat;
		Minion minion1 = new Minion("Kobold Footman", 3, 3, 2, 8, 2, ItemList.goldSword, ItemList.potion, player);
        Minion minion2 = new Minion("Kobold Footman", 3, 3, 2, 8, 2, ItemList.goldSword, ItemList.potion, player);
        Minion minion3 = new Minion("Kobold Knight", 4, 3, 2, 12, 2, ItemList.goldArmor, ItemList.potion, player);
        Minion minion4 = new Minion("Kobold Knight", 4, 3, 2, 12, 2, ItemList.goldArmor, ItemList.potion, player);
        Minion minion5 = new Minion("Kobold Footman", 3, 3, 2, 8, 2, ItemList.goldSword, ItemList.potion, player);
        Boss boss1 = new Boss("Magmon the Golem", 5, 4, 2, 13, 2, ItemList.goldGolemGauntlet, ItemList.goodPotion, player);
		Minion[] minionList = {minion1, minion2, minion3, minion4, minion5, boss1};
		text.printTXT("As you leave the town, you feel something warming up in your pocket.\nYou look down to see a faint glow, and immediately reach into your pocket.\nYour map! As it glows, a new path appears.\nYou decide to follow it, believing it will lead you closer to the dragon ogre.\nUnlike the last path of destruction, this path is nice and there is even a trail of gold plated torches lighting the way!\nYou follow the path to the mouth of a cave. You look in to see a gold furnished cave, with lava flowing from every orifice.\nProceeding cautiously, you enter your next dungeon.\nYou enter the dungeon. Here, deep underground, the kobolds defend their treasures.");
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
		town2();
	}


	public static void dungeon3() throws IOException
	{
		Combat combat;
		Scanner input = new Scanner(System.in);
		Minion minion1 = new Minion("Posessed Villager", 4, 3, 2, 10, 2, ItemList.steelSword, ItemList.badPotion, player);
		Minion minion2 = new Minion("Posessed Villager", 4, 3, 2, 10, 2, ItemList.steelSword, ItemList.badPotion, player);
		Minion minion3 = new Minion("Hellhound", 2, 4, 2, 8, 2, ItemList.dragonSword, ItemList.potion, player);
		Minion minion4 = new Minion("Posessed Villager", 4, 3, 2, 10, 2, ItemList.steelSword, ItemList.badPotion, player);
		Minion minion5 = new Minion("Posessed Villager", 4, 3, 2, 10, 2, ItemList.steelSword, ItemList.badPotion, player);
		Minion minion6 = new Minion("Posessed Villager", 4, 3, 2, 10, 2, ItemList.steelSword, ItemList.badPotion, player);
		Minion minion7 = new Minion("Animated Armor", 5, 2, 2, 11, 2, ItemList.dragonsteelArmor, ItemList.goodPotion, player);
		Minion minion8 = new Minion("Posessed Villager", 4, 3, 2, 10, 2, ItemList.steelSword, ItemList.badPotion, player);
		Minion minion9 = new Minion("Hellhound", 2, 4, 2, 8, 2, ItemList.dragonSword, ItemList.potion, player);
		Minion minion10 = new Minion("Animated Armor", 5, 2, 2, 11, 2, ItemList.dragonsteelArmor, ItemList.goodPotion, player);
		Boss boss1 = new Boss("Stephan the Blacksmith", 3, 5, 2, 12, 2, ItemList.dragonSword, ItemList.goodPotion, player);
		Minion[] minionList = {minion1, minion2, minion3, minion4, minion5, minion6, minion7, minion8, minion9, minion10, boss1};
		text.printTXT("As you walk out of the town you are immediately bashed by a spade which one of the many armed townsfolk wields.\nPicking yourself up you turn to look at the mob like people who slowly surround you on the path.\nWith deep regret you decide that the only way out of this situation is to fight and draw your sword.");
		inventory(2);
		for(int i = 0; i < minionList.length; i++) {
			combat = new Combat(minionList[i], player);
			if(i == 6)
			{
				text.printTXT("After having killed many of the town's inhabitants, you see a small boy running through the carnage, directly towards you.\nThoughts race through your mind. He doesn't look posessed, but he could be.\nDo you kill this boy?\n");
				System.out.print("Kill the boy?\n1. Kill\n2. Don't");
				int choice = input.nextInt();

				if(choice == 1)
				{
					secretDungeon();
				}

				if(choice == 2)
				{
					text.printTXT("You decide to not kill the boy. He runs off, and the fighting continues.");
				}
			}

			if(minionList[i].getClass().getName().equals("Minion"))
				text.printTXT("You encounter a " + minionList[i].getName());
			if(minionList[i].getClass().getName().equals("Boss"))
			{
				text.printTXT("");
				text.printTXT("You encounter the dungeon boss, " + minionList[i].getName());
			}
			inventory(combat.fight());
		}


		text.printTXT("As you run out of the village you feel a new burning sensation.\nIt's the crystal!\nAfter struggling you pull it out of your sac, the rock is smoking and the fluid like purple color has changed to a cracking black.\nWatching the stone as it splits the purple liquid pours out into a puddle.\nThe puddle acts as a portal which allows a young ogre dragon to crawl out. He immediately thanks you saying, “My father locked me in that wretched prison many months ago before his tirade began. He started to act strange and when I spoke out against his plans to take over all of legionfall he imprisoned me in that rock. Placing me inside of the golem for safekeeping, when you killed the golem it was up to me to break free”\nHe introduces himself saying “My name is Clathorms, than you for freeing me.”\nBoth of you proceed to his fathers lair determined to end his tirade.");
		town3();
	}

	public static void town1() throws IOException
	{
		input = new Scanner(System.in);
		if(count == 0)
		{
			text.printTXT("You enter the seemingly empty town greeted with a howl from the wind.\nYou walk forward to the local shop and knock only to hear the words from a scared women, \"Go away Baba, we know what you seek and do not have it.\"\nTaken aback you explain how you have slayed the hag and seek shelter. The door unlocks and you walk in.");
			count++;
		}
		text.printTXT("\nTown of Estebon\n1.Inn \n2.Shop \n3. Move on");
		player.printGold();
		int choice = nextInt();

		if(choice == 1)
		{
			System.out.println("\"Welcome to my inn, rent a room or get out.\" A harried looking woman speaks. \n1.Rent a room \n2.Exit");
			int c = nextInt();

			if(c == 1 && player.gold >= 5)
			{    
				System.out.println("You drift off into a deep sleep.");

				player.gold -= 5;
				player.health = player.maxHealth;
				player.town = 1;
				save();
				town1();
			}
			else
			{
				System.out.println("You dont have enough money for the room");
				town1();
			}	

			if(c == 2)
			{
				System.out.println("You leave the inn.");
				town1();
			}
		}
		if(choice == 2)
		{
			System.out.println("\"What can I get you my fine sir\" says the sweaty shopkeeper. \n1.Buy \n2.Sell");
			int c = nextInt();

			if(c == 1)
			{
				System.out.println("\n1. Steel Sword ( 3 Gold )");
				System.out.println("2. Plate Armor ( 4 Gold )");
				System.out.println("3. Lesser Healing Potion ( 2 Gold )");	

				System.out.println("Choose item: ");
				choice = input.nextInt();

				if(choice == 1)
				{
					if(player.gold >= 3)
					{
						player.gold -= 3;
						System.out.println("Thank you for your patronage!");
						player.addEquip(ItemList.steelSword);
						town1();
					}

					else
					{
						System.out.println("You don't have the funds for that!");
						town1();
					}
				}

				if(choice == 2)
				{
					if(player.gold >= 4)
					{
						player.gold -= 4;
						System.out.println("Thank you for your patronage!");
						player.addEquip(ItemList.plateArmor);
						town1();
					}

					else
					{
						System.out.println("You don't have the funds for that!");
						town1();
					}
				}

				if(choice == 3)
				{
					if(player.gold >= 2)
					{
						player.gold -= 2;
						System.out.println("Thank you for your patronage!");
						player.addConsume(ItemList.badPotion);
						town1();
					}

					else
					{
						System.out.println("You don't have the funds for that!");
						town1();
					}
				}
			}
		}

		if(choice == 3)
		{
			dungeon2();
		}
	}

	public static void town2() throws IOException
	{
		input = new Scanner(System.in);
		if(count == 0)
		{
			text.printTXT("");
			count++;
		}
		text.printTXT("\nTown of Gathalamor\n1.Inn \n2.Shop \n3. Move on");
		player.printGold();
		int choice = nextInt();

		if(choice == 1)
		{
			System.out.println("\"Busy night... but there's always room for another!\" A cheerful dwarf with red hair says. \n1.Rent a room \n2.Exit");
			int c = nextInt();

			if(c == 1 && player.gold >= 5)
			{	
				System.out.println("You drift off into a deep sleep.");

				player.gold -= 5;
				player.health = player.maxHealth;
				save();
				town2();
			}
			else
			{
				System.out.println("You dont have enough money for the room");
				town2();
			}	

			if(c == 2)
			{
				System.out.println("You leave the inn.");
				town2();
			}
		}
		if(choice == 2)
		{
			System.out.println("\"What can I get you my fine sir\" says the sweaty shopkeeper. \n1.Buy \n2.Sell");
			int c = nextInt();

			if(c == 1)
			{
				System.out.println("\n1. Gold Sword ( 7 Gold )");
				System.out.println("2. Gold Plate Armor ( 10 Gold )");
				System.out.println("3. Healing Potion ( 3 Gold )");	

				System.out.println("Choose item: ");
				choice = input.nextInt();

				if(choice == 1)
				{
					if(player.gold >= 7)
					{
						player.gold -= 7;
						System.out.println("Thank you for your patronage!");
						player.addEquip(ItemList.goldSword);
						town2();
					}

					else
					{
						System.out.println("You don't have the funds for that!");
						town2();
					}
				}

				if(choice == 2)
				{
					if(player.gold >= 10)
					{
						player.gold -= 10;
						System.out.println("Thank you for your patronage!");
						player.addEquip(ItemList.goldArmor);
						town2();
					}

					else
					{
						System.out.println("You don't have the funds for that!");
						town2();
					}
				}

				if(choice == 3)
				{
					if(player.gold >= 3)
					{
						player.gold -= 3;
						System.out.println("Thank you for your patronage!");
						player.addConsume(ItemList.potion);
						town2();
					}

					else
					{
						System.out.println("You don't have the funds for that!");
						town2();
					}
				}
			}
		}

		if(choice == 3)
		{
			dungeon3();
		}
	}

	public static void town3() throws IOException
	{
		input = new Scanner(System.in);
		if(count == 0)
		{
			text.printTXT("");
			count++;
		}
		text.printTXT("\nTown of Claethorpes\n1.Inn \n2.Shop \n3. Move on");
		player.printGold();
		int choice = nextInt();

		if(choice == 1)
		{
			System.out.println("\"Hello.\" A blonde haired male says plainly. \n1.Rent a room \n2.Exit");
			int c = nextInt();

			if(c == 1 && player.gold >= 5)
			{	
				System.out.println("You drift off into a deep sleep.");

				player.gold -= 5;
				player.health = player.maxHealth;
				save();
				town3();
			}
			else
			{
				System.out.println("You dont have enough money for the room");
				town3();
			}	

			if(c == 2)
			{
				System.out.println("You leave the inn.");
				town3();
			}
		}
		if(choice == 2)
		{
			System.out.println("\"What can I get you my fine sir?\" says the sweaty scrub shopkeeper. \n1.Buy \n2.Sell");
			int c = nextInt();

			if(c == 1)
			{
				System.out.println("\n1. Dragonsteel sword ( 15 Gold )");
				System.out.println("2. Dragonsteel armor ( 17 Gold )");
				System.out.println("3. Greater healing potion ( 5 Gold )");	

				System.out.println("Choose item: ");
				choice = input.nextInt();

				if(choice == 1)
				{
					if(player.gold >= 15)
					{
						player.gold -= 15;
						System.out.println("Thank you for your patronage!");
						player.addEquip(ItemList.dragonSword);
						town3();
					}

					else
					{
						System.out.println("You don't have the funds for that!");
						town3();
					}
				}

				if(choice == 2)
				{
					if(player.gold >= 17)
					{
						player.gold -= 17;
						System.out.println("Thank you for your patronage!");
						player.addEquip(ItemList.dragonsteelArmor);
						town3();
					}

					else
					{
						System.out.println("You don't have the funds for that!");
						town3();
					}
				}

				if(choice == 3)
				{
					if(player.gold >= 5)
					{
						player.gold -= 5;
						System.out.println("Thank you for your patronage!");
						player.addConsume(ItemList.goodPotion);
						town3();
					}

					else
					{
						System.out.println("You don't have the funds for that!");
						town3();
					}
				}
			}
		}

		if(choice == 3)
		{
			finalBoss();
		}
	}

	public static void finalBoss() throws IOException
	{
		Combat combat;
		Boss boss1 = new Boss("Azaks the Mind Flayer", 5, 5, 2, 12, 2, ItemList.satanBlessedArmor, ItemList.goodPotion, player);
		Boss boss2 = new Boss("The Dragon Ogre", 6, 5, 2, 15, 2, ItemList.hellfireSword, ItemList.goodPotion, player);
		Minion[] minionList = {boss1, boss2};
		text.printTXT("After what feels like an eternity, you finally arrive, only to be greeted by a looming blank cliff face.\nPuzzled, Clathorms says “He has resided here for centuries, but the gate is gone!”.\nAs those words escape his mouth the floor underneath you shatters like glass.\nBoth of you fall through and land with a thud.\nThe marble floor is cold and dusty.\nAs you look up your eyes meet the monstrous eyes of the dragon ogre.\nHe chuckles and says in a deep voice “Fools… Both seek revenge, but neither shall be satisfied!”\nThe monstrous creature leaps toward the both of you, swinging his mighty blood stained axe.\nYou narrowly dodge the axe while Clathorms takes the blow with ease.\nThe father and son exchange mighty blows until Clathorms is knock unconscious.\nThe battered dragon ogre charges your way, and he's not looking to talk.");
		inventory(2);
		for(int i = 0; i < minionList.length; i++) {
			combat = new Combat(minionList[i], player);

			if(minionList[i].getClass().getName().equals("Minion"))
				text.printTXT("You encounter a " + minionList[i].getName());
			if(minionList[i].getClass().getName().equals("Boss"))
			{
				text.printTXT("");
				text.printTXT("You encounter a dungeon boss, " + minionList[i].getName());
			}
			inventory(combat.fight());
		}


		text.printTXT("You've done it, you managed to kill the dragon ogre.\nYou run to clathorms who has just awoken, “Don't haste the fight is not over yet.”.\nHe points back to the corpse of his father which is rising.\nThe dragon ogre which has seemingly come back from the dead has a red liquid pouring out of every pore.\nHe lets out another mighty roar and you run back to finish the job.\n\nThe vile dragon ogre lays dead for good.\nYou start to turn to face clathorm, but everything fades to black and white noise fills your ears.");
		bossRush();
	}

	public static void bossRush() throws IOException
	{
		Combat combat;
		Minion minion1 = new Minion("Baba Yaga the Haggiest Hag", 3, 3, 2, 10, 2, ItemList.steelSword, ItemList.goodPotion, player);
		Minion minion2 = new Minion("Magmon the Golem", 6, 4, 2, 15, 2, ItemList.goldGolemGauntlet, ItemList.goodPotion, player);
		Minion minion3 = new Minion("Stephan the Blacksmith", 3, 5, 2, 12, 2, ItemList.dragonSword, ItemList.goodPotion, player);
		Minion minion4 = new Minion("Azaks the Mind Flayer", 5, 5, 2, 12, 2, ItemList.satanBlessedArmor, ItemList.goodPotion, player);
		Minion minion5 = new Minion("The Dragon Ogre", 6, 5, 2, 15, 2, ItemList.hellfireSword, ItemList.goodPotion, player);
		Boss boss1 = new Boss("Darasyth", 3, 2, 2, 10, 2, ItemList.hellfireSword, ItemList.goodPotion, player);
		Minion[] minionList = {minion1, minion2, minion3, minion4, minion5, boss1};
		text.printTXT("You wake up with a throbbing headache, seeing a forest aflame in front of your eyes.\nYou pass out again.\nDarkness.\nYou wake up to see yourself surrounded by the charred remains of the forest.\"What happened?\" you think to yourself.\nYou walk to the edge of a cliff to see a broken sign... \"One mile to Hoggiest Warts\", your hometown.\nYou look up to see a burning city.\n“I've been here before”, you whisper to yourself.\nTurning around to see some of the enemy's you've met before and a new creature.\nThe new creature loomed over, Baba Yaba, the dwarfen golem, and oddly clathorms.\nAll of them looked empty and showed no emotion.\n“I am Darasyth, and these are some of the poor soles you've encountered...\nYou have been the only soul whose efforts have hindered my plans.\nThe creatures all close in around you as Darasyth falls into a trances like state.\n\nBattered and out of breath you look up at Darasyth.\nThe tips of the tentacles on his head look charred now and he is enraged.\n“I will kill you with my own hands now” he says before swiping at you sending bolts of lighting towards your face.\n\n");
		inventory(2);
		for(int i = 0; i < minionList.length; i++) {
			combat = new Combat(minionList[i], player);

			if(minionList[i].getClass().getName().equals("Minion"))
				text.printTXT("You encounter a " + minionList[i].getName());
			if(minionList[i].getClass().getName().equals("Boss"))
			{
				text.printTXT("");
				text.printTXT("You encounter the final boss, " + minionList[i].getName());
			}
			inventory(combat.fight());
		}


		text.printTXT("You stand at the verge of death over the body of Darasyth.\nWith his dying breath he curses you, and moments after he dies everything fades to black.\n\nYou wake up with a throbbing headache, seeing a forest in front of your eyes.\nYou pass out again.\nDarkness.\nYou wake up to see yourself surrounded by the forest.\nYou walk to the edge of a cliff to see a sign... \"One mile to Hoggiest Warts\", your hometown.\nYou run into town to see the beggar at the gate, he asks if you have anything to spare.\nYou give him the sword he once gave you and continue into town.\nHere you find your family and you greet them like you have seen them in years.\nMany hours later you rummage through you satchel to find a scorched map, and a crystal similar to the one your ally Clathorms came from.\nHappy and confused you tuck into bed, waiting for what lies next...");
		System.exit(0);
	}

	public static void secretDungeon() throws IOException
	{
		Combat combat;
		Boss boss1 = new Boss("Stites the EVIL", 8, 8, 2, 25, 2, ItemList.dragonSword, ItemList.goodPotion, player);
		Minion[] minionList = {boss1};
		
		text.printTXT("YOU ENCOUNTER STITES THE EVIL!!!");

		combat = new Combat(minionList[0], player);
		text.printTXT("How did you win?");
		town3();
	}


	public static void inventory(int dead) throws IOException
	{
		input = new Scanner(System.in);

		if(dead == 1)
		{
			System.exit(0);
		}

		if(dead == 2)
		{
			System.out.println("Inventory \n1.Consumeable \n2.Equipment \n3.Stats \n4.Exit");
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