import java.util.Scanner;
/* 
Code by Joshua Knight
Made for CS&141 class at Olympic College
*/

//class full of useful terminal actions
public class Terminal 
{
	static Scanner console = new Scanner(System.in);
	
	//spacer for neatness
	public static void spacer()
	{
		System.out.println("-----------------------------------");
	}
	
	//sometimes I didn't need a println statement, just a print
	public static void spacerPrint()
	{
		System.out.print("-----------------------------------");
	}
	
	//thank you so much for the clearscreen method, it was a life saver
	public static void clearScreen() 
	{
		try
		{//windows
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} 
		catch(Exception e)
		{//mac and linux
			try
			{
				new ProcessBuilder("clear").inheritIO().start().waitFor();
			}
			catch(Exception e2)
			{
				
			}
		}
	}
	
	//used this a ton so decided to make a dedicated method
	public static void enterToContinue()
	{
		System.out.println("Press Enter to Continue...");
	}
	
	//this also was a life saver
	public static void sleep(int seconds)
	{
		try
		{
			Thread.sleep(1000 * seconds);
		} 
		catch(InterruptedException ex) 
		{
			Thread.currentThread().interrupt();
		}
	}
	
	//didn't use this, but decided to add it
	public static void clearLine(int length)
	{
		for(int i =0; i < length;i++)
		{
			System.out.print("\b \b"); // backspace, blank, backspace
		}
		System.out.flush();
	}
	
	//quit method
	public static void quitToMenu()
	{
		System.out.println("Are you sure?\n1) Yes\n2) No");
		
		int choice = console.nextInt();
		
		while(choice < 1 || choice > 2)
		{
			System.out.println("Invalid input, enter a number associated with your options");
			choice = console.nextInt();
		}
		
		if(choice == 1)
		{
			Main.main(null);
		}
	}
}
