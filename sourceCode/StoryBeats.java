/* 
   Code by Joshua Knight
   Made for CS&141 class at Olympic College
*/
public class StoryBeats 
{
	//methods that hold story text
	public static void deathMessage(Player player, Enemy enemy)
	{
		System.out.println("And with one last killing blow from a " + enemy.getName() + ", " + player.getName() + "'s second chance at life has faded away. They are now destined to reside in Helheim for eternity");
	}
	
	public static void intro(Player player)
	{
		System.out.println(player.getName() + " wakes up in Helheim, a victim of premature death. \nThey are filled with dread. They had a whole life ahead of them; friends and family that they will never see again.");
		System.out.println("All seemed lost until a man approached " + player.getFirstName() + " and told them that Hel is granting a safe passage to midgard for anyone who clears Helheim's dungeons and slays the god at the edge of the realm.");
		System.out.println("The man says that if " + player.getFirstName() + " were to accept this task, they would be granted mortality once more, for only the living can slay the monters that terrorize the realm. \nDeath during this quest means an eternity in Helheim.");
		System.out.println("The man expresses his confidence in " + player.getFirstName() + "'s abilities and wishes them luck before leaving.");
		System.out.println("This quest is nigh impossible, but the reward is too good to pass up. " + player.getFirstName() + " gathers the little scraps they could find and begins their journey");
		System.out.println();
		Terminal.sleep(1);
	}
	
	public static void ending(Player player)
	{
		System.out.println("It's over....");
		System.out.println();
		System.out.println(player.getFirstName() + " has completed the task, yet nothing happens, not even a mention of their achievement.");
		System.out.println("Suddenly, the man you met when you first entered Helheim greets you. He reveals himself to be Loki, the trickster god");
		System.out.println("\nAnd what a trick he pulled\n");
		System.out.println("All of this was an illusion: the dungeons, the enemies, the god " + player.getFirstName() + " killed, their second chance at mortality. \nThis was all a test to see if " + player.getFirstName() + " was worthy enough to lead a legion of Loki's soldiers agains the Aesir");
		System.out.println("And they passed. \n\nRagnarok is coming, \n\nAnd " + player.getName() + " is leading the opposition");
	}
}
