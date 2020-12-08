import java.util.ArrayList;
import java.util.Scanner;
/* 
Code by Joshua Knight
Made for CS&141 class at Olympic College
*/
public class Battle 
{
	static Scanner console = new Scanner(System.in);
	//scale level dictates the stats of the enemies, and the battle type dictates whether it is a standard battle, boss battle, or final boss battle
	public static void battleStart(Player player, int getScaleLevel, String battleType)
	{
		//ArrayList allows for the ability to remove enemies at will.
		Terminal.clearScreen();
		ArrayList<Enemy> enemies = Enemy.generateEnemy(3);
		
		if(battleType.equals("Boss"))
		{
			//Changes the 3 common enemies to one boss
			enemies.clear();
			enemies.add(new Boss());
		}
		
		if(battleType.equals("Final Boss"))
		{
			//initiates the final boss method in boss class in order to get final boss stats
			Boss boss = new Boss();
			boss.finalBoss();
			
			enemies.clear();
			enemies.add(boss);
		}
		//scales enemies
		for(int i = 0; i < enemies.size(); i++)
		{
			enemies.get(i).scale(getScaleLevel);
		}
		
		boolean stillFighting = true;
		player.hud();
		Enemy.displayEnemies(enemies);
		
		while(stillFighting == true)
		{
			Terminal.spacer();
			System.out.println("It is now " + player.getName() + "'s turn");
			playerTurn(enemies, player);
			//checks if any enemies remain after player turn, if there aren't, then the player won
			if(enemies.size() == 0)
			{
				//returns to main
				stillFighting = false;
				Terminal.clearScreen();
				return;
			}
			
			System.out.println("\nEnemy Phase\n");
			
			Terminal.sleep(1);
			//loops enemy turn for each enemy in array list
			for(int i = 0; i < enemies.size(); i++)
			{
				Terminal.spacer();
				System.out.println("It is now " + enemies.get(i).getName() +  "'s Turn");
				enemyTurn(enemies, player, i);
				//Checks player health after every enemy turn, if player health is 0, then player lost
				if(player.getHealth() <= 0)
				{
					//runs a death message, then returns to main
					stillFighting = false;
					Terminal.clearScreen();
					StoryBeats.deathMessage(player, enemies.get(i));
					Terminal.sleep(3);
					console.nextLine();
					return;
				}
			}
		}
	}
	
	public static void playerTurn(ArrayList<Enemy> enemies, Player player)
	{
		int actionChoice;
		//asks for player action
		System.out.println("Choose an action\n1) Physical Attack\n2) Magic\n3) Heal");
		actionChoice = console.nextInt();
		Terminal.spacer();
		while(actionChoice < 1 || actionChoice > 3)
		{
			//prevents faulty data
			System.out.println("Invalid input, enter a number associated with your options");
			actionChoice = console.nextInt();
			Terminal.spacer();
		}
		
		if(actionChoice == 1)
		{
			//takes the enemies array list, player, and type of attack where 'P' stands for physical and 'M' stands for magic
			playerAttack(enemies, player, 'P');
		}
		else if(actionChoice == 2)
		{
			int magicChoice;
			//asks what magic the player wants to use
			System.out.println("Choose your spell\n1) Magic Attack MP Cost: 3\n2) Magic Heal MP Cost: 2\n3) Back");
			magicChoice = console.nextInt();
			Terminal.spacer();
			
			while(magicChoice < 1 || magicChoice > 3)
			{
				System.out.println("Invalid input, enter a number associated with your options");
				magicChoice = console.nextInt();
				Terminal.spacer();
			}
			
			if(magicChoice == 1)
			{
				playerAttack(enemies, player, 'M');
			}
			else if(magicChoice == 2)
			{
				magicHeal(player, enemies);
			}
			else if(magicChoice == 3)
			{
				//back button, sends player back into the initial player turn prompt
				playerTurn(enemies, player);
			}
		}
		else if(actionChoice == 3)
		{
			playerHeal(player, enemies);
		}
	}
	
	public static void playerAttack(ArrayList<Enemy> enemies, Player player, char typeOfAttack)
	{
		int opponents = enemies.size();
		int enemyChoice;
		
		//asks what enemy the player wants to attack and gets input.
		System.out.println("Who do you want to attack?");
		
		for(int i = 0; i < opponents; i++)
		{
			System.out.println(i + 1 + ") " + enemies.get(i).getName());
		}
		
		System.out.println((enemies.size() + 1) + ") Back");
		
		enemyChoice = console.nextInt() - 1;
		while(enemyChoice < 0 || enemyChoice > enemies.size())
		{
			System.out.println("Invalid input. Please enter a number associated with your options");
			enemyChoice = console.nextInt() - 1;
			Terminal.spacer();
		}
		
		Terminal.spacer();
		
		if(enemyChoice == enemies.size())
		{
			//if the input equals the size of the array list, then it means that it is the back button and brings the player back to initial turn prompt
			playerTurn(enemies, player);
		}
		else
		{
			//typeOfAttack variable allows the same attack method to be used for both physical and magic attacks
			enemies.get(enemyChoice).takeDamage(player, typeOfAttack);
			Terminal.sleep(1);
			console.nextLine();
			
			//checks for dead enemy
			if(enemies.get(enemyChoice).getHealth() <= 0)
			{
				System.out.println(enemies.get(enemyChoice).getName() + " is dead");
				Terminal.sleep(1);
				player.setGold(player.getGold() + enemies.get(enemyChoice).dropGold());
				Terminal.sleep(1);
				player.setXp(player.getXp() + enemies.get(enemyChoice).dropXp());
				enemies.remove(enemyChoice);
				Terminal.sleep(1);
			}
			System.out.println();
			System.out.println("Press Enter to Continue...");
			console.nextLine();
		}
		
		Terminal.clearScreen();
		player.hud();
		Enemy.displayEnemies(enemies);
	}
	
	public static void playerHeal(Player player, ArrayList<Enemy> enemies)
	{
		//checks for potions
		if(player.getPotions() <= 0)
		{
			System.out.println("You don't have any potions");
			Terminal.sleep(1);
			console.nextLine();
			console.nextLine();
			System.out.println();
			playerTurn(enemies, player);
		}
		//checks if player already has max health
		else if(player.getHealth() == player.getMaxHealth())
		{
			System.out.println("You have max health");
			console.nextLine();
			Terminal.spacerPrint();
			console.nextLine();
			playerTurn(enemies, player);
		}
		//heals otherwise
		else
		{
			player.heal();
			
			Terminal.sleep(1);
			System.out.println();
			Terminal.enterToContinue();
			console.nextLine();
			console.nextLine();
			Terminal.clearScreen();
			player.hud();
			Enemy.displayEnemies(enemies);
		}
	}
	
	//similar to the previous heal method, just uses mana points instead of potions
	public static void magicHeal(Player player, ArrayList<Enemy> enemies)
	{
		if(player.getMp() < 2)
		{
			System.out.println("You do not have enough Mana to do this action.");
			console.nextLine();
			Terminal.spacerPrint();
			Terminal.enterToContinue();
			console.nextLine();
			playerTurn(enemies, player);
		}
		else if(player.getHealth() == player.getMaxHealth())
		{
			System.out.println("You have max health");
			console.nextLine();
			Terminal.spacerPrint();
			console.nextLine();
			playerTurn(enemies, player);
		}
		else
		{
			player.magicHeal();
			
			Terminal.sleep(1);
			System.out.println();
			Terminal.enterToContinue();
			console.nextLine();
			console.nextLine();
			Terminal.clearScreen();
			player.hud();
			Enemy.displayEnemies(enemies);
		}
	}
	
	//runs the enemy turn
	public static void enemyTurn(ArrayList<Enemy> enemies, Player player, int whoseTurn)
	{
		player.takeDamage(enemies.get(whoseTurn));
		System.out.println();
		Terminal.sleep(1);
		Terminal.enterToContinue();
		console.nextLine();
		Terminal.clearScreen();
		player.hud();
		Enemy.displayEnemies(enemies);
	}
}