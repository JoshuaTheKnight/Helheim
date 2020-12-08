import java.util.ArrayList;
import java.util.Random;
/* 
Code by Joshua Knight
Made for CS&141 class at Olympic College
*/
public class Enemy extends Entity
{
	Random random = new Random();
	String[] enemyNames = {"Draugr", "Jotunn", "Troll", "Ogre", "Werewolf", "Dark Elf", "Berzerker", "Revenant"};
	
	public Enemy(String name, int health, int strength, double critChance, double critDamageBoost, int evasion)
	{		
		super(name, health, strength, critChance, critDamageBoost, evasion);
	}
	
	public Enemy()
	{
		this(null, 0, 0, 0, 0, 0);
		generateName(enemyNames);
		generateStats();
	}
	
	//generates random name and generates random stats
	protected void generateName(String[] enemyNames)
	{
		this.setName(enemyNames[random.nextInt(enemyNames.length)]);
	}
	
	private void generateStats()
	{
		this.setHealth(random.nextInt(20) + 15);
		this.setMaxHealth(this.getHealth());
		this.setStrength(3 + random.nextInt(4) + 1);
		this.setCritChance(random.nextInt(10) + 1);
		this.setCritDamageBoost(random.nextInt(2) + 1.5);
		this.setEvasion(random.nextInt(15) + 4);
	}
	
	//takes in player object and the type of player attack where 'P' = physical and 'M' (or nothing) equals magic
	public void takeDamage(Player player, char type)
	{
		int rollEvasion = random.nextInt(100) + 1;
		//checks if the enemy evaded the player's attack
		if(rollEvasion <= this.getEvasion()/2)
		{
			//if the attack is not physical (aka magic attack), consume mana points
			System.out.println(this.getName() + " Evaded the Attack");
			if(type != 'P')
			{
				player.setMp(player.getMp() - 3);
			}
			return;
		}
		
		int totalDamage;
		
		//chooses what type of damage method the player uses based on what type of attack was chosen
		if(type == 'P')
		{
			totalDamage = player.dealDamage();
		}
		else
		{
			totalDamage = player.dealMagicDamage();
		}
		
		//if the attack did no damage, mention it
		if(totalDamage <= 0)
		{
			System.out.println(player.getName() + " Attacked to No effect");
		}
		//attack summary
		else
		{
			this.setHealth(this.getHealth() - totalDamage);
			System.out.println(player.getName() + " Attacked " + this.getName() + " and did " + totalDamage + " damage");
			Terminal.sleep(1);
			System.out.println(this.getName() + " has " + this.getHealth() + " health remaining");
		}
	}
	
	//drop gold used for death of enemy
	public int dropGold()
	{
		int goldDropped = random.nextInt(200) + 1;
		
		System.out.println(this.getName() + " Dropped " + goldDropped + " gold");
		
		return goldDropped;
	}
	
	//drop xp used for death of enemy
	public int dropXp()
	{
		int xpDropped = random.nextInt(28) + 25;
		
		System.out.println(this.getName() + " Dropped " + xpDropped + " XP");
		
		return xpDropped;
	}
	
	//scale algorithm used to have the enemy keep up with player stat progression
	public void scale(int scaleLevel)
	{
		this.setHealth(this.getHealth() * scaleLevel);
		this.setMaxHealth(this.getHealth());
		this.setStrength(this.getStrength() * scaleLevel);
	}
	
	//static method used to generate an arraylist of enemies used for the battle class. makes more sense to have it here
	public static ArrayList<Enemy> generateEnemy(int amount)
	{
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		
		for(int i = 0; i < amount; i++)
		{
			enemies.add(new Enemy());
		}
		
		return enemies;
	}
	
	//displays the enemies on the battle screen
	public static void displayEnemies(ArrayList<Enemy> enemies)
	{
		for(int i = 0; i < enemies.size(); i++)
		{
			//algorithm used to determine how many spaces are needed to keep the name, health, and damage of the same enemy object aligned
			String nameOutput = enemies.get(i).getName();
			int spacesWanted = 20;
			int spaceDifference = spacesWanted - nameOutput.length();
			
			System.out.print(nameOutput);
			
			for(int j = 0; j < spaceDifference; j++)
			{
				System.out.print(" ");
			}
		}
		
		System.out.println();
		
		for(int i = 0; i < enemies.size(); i++)
		{
			String healthOutput = enemies.get(i).getHealth() + "/" + enemies.get(i).getMaxHealth() + " Health";
			int spacesWanted = 20;
			int spaceDifference = spacesWanted - healthOutput.length();
			
			System.out.print(healthOutput);
			
			for(int j = 0; j < spaceDifference; j++)
			{
				System.out.print(" ");
			}
		}
		
		System.out.println();
		
		for(int i = 0; i < enemies.size(); i++)
		{
			String damageOutput = enemies.get(i).getStrength() + " Damage";
			int spacesWanted = 20;
			int spaceDifference = spacesWanted - damageOutput.length();
			
			System.out.print(damageOutput);
			
			for(int j = 0; j < spaceDifference; j++)
			{
				System.out.print(" ");
			}
		}
		
		System.out.println();
		System.out.println();
	}
}
