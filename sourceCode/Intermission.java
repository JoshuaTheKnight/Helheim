import java.util.Scanner;
/* 
Code by Joshua Knight
Made for CS&141 class at Olympic College
*/
public class Intermission 
{
	static Scanner console = new Scanner(System.in);
	
	public static void afterMatchIntermission(Player player)
	{
		int choice;
		//check for level up in between battles
		levelUp(player);
		
		//offers the ability to heal up using potions or spells before the next fight
		Terminal.clearScreen();
		player.hud();
		System.out.println("Through skill and an ounce of luck, " + player.getFirstName() + " was able to leave the battle victorious. They know that there are more enemies ahead and contemplate whether or not to heal");
		System.out.println("\n1) Heal With Potion\n2) Heal With Spell\n3) Don't Heal\n4) Quit to Menu");
		
		choice = console.nextInt();
		Terminal.spacer();
		
		while(choice < 1 || choice > 4)
		{
			System.out.println("Invalid input, please enter a number associated with your options");
			choice = console.nextInt();
		}
		
		
		if(choice == 1)
		{
			//heal method then return to this method
			potionHeal(player);
			afterMatchIntermission(player);
		}
		else if(choice == 2)
		{
			//heal method then return to this method
			magicHeal(player);
			afterMatchIntermission(player);
		}
		else if(choice == 3)
		{
			//starts the next fight
			System.out.println("Get ready for the next battle!");
			console.nextLine();
			Terminal.sleep(1);
			System.out.println();
			Terminal.enterToContinue();
			console.nextLine();
		}
		else if(choice == 4)
		{
			//quits to main menu
			Terminal.quitToMenu();
			afterMatchIntermission(player);
		}
	}
	
	public static void beforeDungeonIntermission(Player player)
	{
		int choice;
		
		//check for level up before entering town
		levelUp(player);
		
		Terminal.clearScreen();
		player.hud();
		
		//offers a hub to heal and buy items. only way to buy and upgrade weapons or buy potions
		System.out.println(player.getFirstName() + " finds a town outside the next dungeon. They decide to stay for a while before entering the next dungeon.\nThere appears to be a merchant where they can buy essential items and an Inn where they can regain lost health and MP");
		Terminal.spacer();
		System.out.println("1) Visit Merchant\n2) Rest at the Inn\n3) Heal With Potion\n4) Heal With Spell\n5) View Weapon\n6) Advance to Dungeon\n7) Quit to Menu");
		
		choice = console.nextInt();
		Terminal.spacer();
		
		while(choice < 1 || choice > 7)
		{
			System.out.println("Invalid input, please enter a number associated with your options");
			choice = console.nextInt();
			Terminal.spacer();
		}
		
		switch(choice)
		{
		case 1:
			//initiates the merchant class
			Merchant.enterShop(player);
			break;
		
		case 2:
			enterInn(player);
			break;
			
		case 3:
			//heal then return to method
			potionHeal(player);
			beforeDungeonIntermission(player);
			break;
			
		case 4:
			//heal then return to method
			magicHeal(player);
			beforeDungeonIntermission(player);
			break;
			
		case 5:
			//check the player's equipped weapon
			System.out.println(player.getWeapon().toString());
			System.out.println();
			Terminal.enterToContinue();
			console.nextLine();
			console.nextLine();
			beforeDungeonIntermission(player);
			break;
			
		case 6:
			//enters the next dungeon
			System.out.println(player.getName() + " Enters the next dungeon");
			console.nextLine();
			Terminal.sleep(1);
			System.out.println();
			Terminal.enterToContinue();
			console.nextLine();
			break;
			
		case 7:
			//quits to menu
			Terminal.quitToMenu();
			beforeDungeonIntermission(player);
		}
	}
	
	public static void potionHeal(Player player)
	{
		//checks for no potions or max health, heals otherwise
		if(player.getPotions() <= 0)
		{
			System.out.println("You don't have any potions");
			Terminal.sleep(1);
			console.nextLine();
			console.nextLine();
			System.out.println();
			return;
		}
		else if(player.getHealth() == player.getMaxHealth())
		{
			System.out.println("You have max health");
			console.nextLine();
			console.nextLine();
			return;
		}
		else
		{
			player.heal();
			
			console.nextLine();
			Terminal.sleep(1);
			System.out.println();
			Terminal.enterToContinue();
			console.nextLine();
			Terminal.clearScreen();
			return;
		}
	}
	//same as potion heal, but uses mana points instead
	public static void magicHeal(Player player)
	{
		if(player.getMp() < 2)
		{
			System.out.println("You do not have enough Mana to do this action.");
			console.nextLine();
			Terminal.spacerPrint();
			console.nextLine();
			return;
		}
		else if(player.getHealth() == player.getMaxHealth())
		{
			System.out.println("You have max health");
			console.nextLine();
			console.nextLine();
			return;
		}
		else
		{
			player.magicHeal();
			
			console.nextLine();
			Terminal.sleep(1);
			System.out.println();
			Terminal.enterToContinue();
			console.nextLine();
			Terminal.clearScreen();
			return;
			
		}
	}
	
	public static void levelUp(Player player)
	{
		int attributeChoice;
		player.hud();
		
		while(player.getXp() >= player.getXpToLevelUp())
		{	
			System.out.println("You Leveled Up!\nSelect an attribute to boost");
			Terminal.spacer();
			System.out.println("1) MP\n2) Strength\n3) Magic\n4) Crit Chance\n5) Crit Damage Boost\n6) Evasion");
			
			attributeChoice = console.nextInt();
			
			while(attributeChoice < 1 || attributeChoice > 6)
			{
				System.out.println("Invalid input, please enter a value associated with your options");
				attributeChoice = console.nextInt();
				Terminal.spacer();
			}
			//initiates player level up with the attribute being raised correlating to input.
			player.levelUp(attributeChoice);
			Terminal.clearScreen();
			player.hud();
			
			System.out.println("Attribute increased!");
			console.nextLine();
			Terminal.sleep(1);
			System.out.println();
			Terminal.enterToContinue();
			console.nextLine();
			
		}
	}
	//inn that allows you to go to full health and mana points
	public static void enterInn(Player player)
	{
		int choice;
		
		Terminal.clearScreen();
		player.hud();
		
		System.out.println("\"Welcome to the inn! You look as tired as a dead person. You can rent a room for 30 gold; that will surely help you recover your health and mana power.\"");
		Terminal.spacer();
		System.out.println("1) Rent Room. Cost: 30 gold\n2) Back");
		
		choice = console.nextInt();
		Terminal.spacer();
		
		while(choice < 1 || choice > 2)
		{
			System.out.println("Invalid input, enter a number associated with your options");
			choice = console.nextInt();
			Terminal.spacer();
		}
		
		switch(choice)
		{
		//checks for enough gold
		case 1:
			if(player.getGold() < 30)
			{
				System.out.println("Not Enough Gold");
				console.nextLine();
				console.nextLine();
				enterInn(player);
				break;
			}
			else
			{
				System.out.println("You rented a room and slept. You regained all your health an MP");
				player.setHealth(player.getMaxHealth());
				player.setMp(player.getMaxMp());
				player.setGold(player.getGold() - 30);
				
				console.nextLine();
				Terminal.sleep(1);
				System.out.println();
				Terminal.enterToContinue();
				console.nextLine();
				Terminal.clearScreen();
				beforeDungeonIntermission(player);
				break;
			}
		//sends back to main intermission menus	
		case 2:
			beforeDungeonIntermission(player);
		}
	}
}