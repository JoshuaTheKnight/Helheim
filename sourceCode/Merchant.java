import java.util.ArrayList;
import java.util.Scanner;
/* 
Code by Joshua Knight
Made for CS&141 class at Olympic College
*/
public class Merchant 
{
	//arraylist for the generated weapons
	static ArrayList<Weapon> shopListWeapon = new ArrayList<Weapon>(3);
	
	static Scanner console = new Scanner(System.in);
	
	//main hub for the merchant, other methods return to this method
	public static void enterShop(Player player)
	{
		int choice;
		
		Terminal.clearScreen();
		player.hud();
		
		System.out.println("\"Welcome to my shop! Feel free to peruse my wares\" the friendly merchant exclaims.\n" + player.getName() + " starts looking around");
		Terminal.spacer();
		System.out.println("1) Buy Health Potion. Cost: 20 gold\n2) View Weapons\n3) Upgrade Your Weapon. Cost: 175 gold\n4) Back");
		
		choice = console.nextInt();
		Terminal.spacer();
		
		while(choice < 1 || choice > 5)
		{
			System.out.println("Invalid input, please enter a number associated with your options");
			choice = console.nextInt();
			Terminal.spacer();
		}
		
		switch(choice)
		{
		//checks if player has enough for potion
		case 1:
			if(player.getGold() < 20)
			{
				System.out.println("Not enough gold");
				console.nextLine();
				console.nextLine();
				enterShop(player);
				break;
			}
			else
			{
				player.setHealthPotions(player.getHealthPotions() + 1);
				player.setGold(player.getGold() - 20);
				System.out.println("Purchased Health Potion");
				console.nextLine();
				Terminal.sleep(1);
				System.out.println();
				Terminal.enterToContinue();
				console.nextLine();
				enterShop(player);
				break;
			}
			
		//sends player to view generated weapons
		case 2:
			viewWeapons(player);
			break;
			
		//checks to see if player can get weapon upgrade
		case 3:
			if(player.getGold() < 175)
			{
				System.out.println("Not enough gold");
				console.nextLine();
				console.nextLine();
				enterShop(player);
				break;
			}
			else
			{
				player.upgradeWeapon();
				player.setGold(player.getGold() - 200);
				System.out.println("Purchased Weapon Upgrade");
				console.nextLine();
				Terminal.sleep(1);
				System.out.println();
				Terminal.enterToContinue();
				console.nextLine();
				enterShop(player);
				break;
			}
		
		case 4:
			//sends player back to the intermission area
			Intermission.beforeDungeonIntermission(player);
			break;
		}
	}
	
	private static void viewWeapons(Player player)
	{
		int choice;
		int buy;
		
		Terminal.clearScreen();
		player.hud();
		
		System.out.println("\"These are the weapons I am offering, feel free to hold them, but if you break it, you buy it\" The merchant says.");
		Terminal.spacer();
		
		//displays the shop weapons
		for(int i = 0; i < shopListWeapon.size(); i++)
		{
			System.out.println((i + 1) + ") " + shopListWeapon.get(i).getName());
		}
		
		//modular back button in case I want to add more selections
		System.out.println((shopListWeapon.size() + 1) + ") " + "Back");
		
		choice = console.nextInt() - 1;
		Terminal.spacer();
		
		while(choice < 0 || choice > shopListWeapon.size())
		{
			System.out.println("Invalid input, enter a number associated with your options");
			choice = console.nextInt() - 1;
			Terminal.spacer();
		}
		
		//if input equals the size of weapon list, then they chose the modular back button
		if(choice == shopListWeapon.size())
		{
			enterShop(player);
		}
		else
		{
			//displays chosen weapon stats and confirms that they want to buy it
			System.out.println(shopListWeapon.get(choice).toString());
			Terminal.spacer();
			System.out.println("1) Buy Weapon: " + shopListWeapon.get(choice).getValue() + " Gold" + "\n2) Back");
			buy = console.nextInt();
			Terminal.spacer();
			
			while(buy < 1 || buy > 2)
			{
				System.out.println("Invalid input, enter a number associated with your options");
				buy = console.nextInt();
				Terminal.spacer();
			}
			
			switch(buy)
			{
			//checks if player has more gold then the value of the weapon
			case 1:
				if(player.getGold() < shopListWeapon.get(choice).getValue())
				{
					System.out.println("Not Enough Gold");
					console.nextLine();
					console.nextLine();
					viewWeapons(player);
					break;
				}
				else
				{
					//player's previous weapon goes into shop list
					shopListWeapon.add(player.getWeapon());
					
					player.equip(shopListWeapon.get(choice));
					System.out.println("Successfully Equipped " + player.getWeapon().getName());
					player.setGold(player.getGold() - player.getWeapon().getValue());
					
					shopListWeapon.remove(choice);
					
					console.nextLine();
					Terminal.sleep(1);
					System.out.println();
					Terminal.enterToContinue();
					console.nextLine();
					Terminal.clearScreen();
					viewWeapons(player);
					break;
				}
				
			case 2:
				//sends back to view weapon screen
				viewWeapons(player);
				break;
			}
		}
	}
	
	//randomly generates the random weapons (very random)
	public static void generateWeapons()
	{
		for(int i = 0; i < 3; i++)
		{
			if(shopListWeapon.size() < 3)
			{
				shopListWeapon.add(null);
			}
			shopListWeapon.set(i, new Weapon());
		}
	}
}
