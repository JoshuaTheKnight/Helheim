import java.util.Scanner;
/* 
Code by Joshua Knight
Made for CS&141 class at Olympic College
*/
public class Main 
{
	static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		boolean stillPlaying = true;
		
		//where the magic happens
		while(stillPlaying == true)
		{
			Terminal.clearScreen();
			title();
			Terminal.spacer();
			
			Player player = new Player();
			
			StoryBeats.intro(player);
			console.nextLine();
			Terminal.enterToContinue();
			console.nextLine();
			Terminal.clearScreen();
			
			for(int i = 1; i <= 5; i++)
			{
				player.hud();
				System.out.println(player.getName() + " enters dungeon #" + i);
				System.out.println();
				Terminal.sleep(1);
				Terminal.enterToContinue();
				console.nextLine();
				
				for(int j = 0; j < 2; j++)
				{
					Battle.battleStart(player, i, "Standard");
					if(player.getHealth() == 0)
					{
						//sends back to menu on death
						main(args);
					}
					
					Intermission.afterMatchIntermission(player);
				}
				
				Terminal.clearScreen();
				player.hud();
				System.out.println("A Boss Approaches, Prepare Yourself");
				System.out.println();
				Terminal.sleep(1);
				Terminal.enterToContinue();
				console.nextLine();
				
				Battle.battleStart(player, i, "Boss");
				if(player.getHealth() == 0)
				{
					main(args);
				}
				//generates new weapons each town visit
				Merchant.generateWeapons();
				Intermission.beforeDungeonIntermission(player);
			}
			
			Terminal.clearScreen();
			player.hud();
			System.out.println("After numerous trials " + player.getFirstName() + " has made it to the end, their final target: a god. They stand strong; the motivation of life leads them into the fray");
			System.out.println();
			Terminal.sleep(1);
			Terminal.enterToContinue();
			console.nextLine();
			
			Battle.battleStart(player, 6, "Final Boss");
			if(player.getHealth() == 0)
			{
				main(args);
			}
			
			Terminal.clearScreen();
			StoryBeats.ending(player);
			System.out.println();
			Terminal.sleep(1);
			System.out.println("The End...");
			console.nextLine();
		}
	}
	
	//ascii art made by http://patorjk.com/software/taag/#p=testall&f=Big%20Money-sw&t=HELHEIM
	public static void title()
	{
		int choice;
		
		System.out.println("\r\n"
				+ "  _    _ ______ _      _    _ ______ _____ __  __ \r\n"
				+ " | |  | |  ____| |    | |  | |  ____|_   _|  \\/  |\r\n"
				+ " | |__| | |__  | |    | |__| | |__    | | | \\  / |\r\n"
				+ " |  __  |  __| | |    |  __  |  __|   | | | |\\/| |\r\n"
				+ " | |  | | |____| |____| |  | | |____ _| |_| |  | |\r\n"
				+ " |_|  |_|______|______|_|  |_|______|_____|_|  |_|\r\n"
				+ "                                                  \r\n"
				+ "                                                  \r\n"
				+ "");
		Terminal.spacer();
		System.out.println("1) Play\n2) Exit");
		
		choice = console.nextInt();
		
		while(choice < 1 || choice > 2)
		{
			System.out.println("Invalid input, enter a number associated with your options");
			choice = console.nextInt();
		}
		
		if(choice == 2)
		{
			//exists entire program
			System.exit(0);
		}
	}
}