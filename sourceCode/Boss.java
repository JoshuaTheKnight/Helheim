import java.util.Random;
/* 
Code by Joshua Knight
Made for CS&141 class at Olympic College
*/
public class Boss extends Enemy
{
	private int timeToBossAttack = 3;
	//names for random name generation
	private String[] bossNames = {"Kraken, Colossus of Midgard", "Magni Thorsson", "Modi Thorsson", "Nidhoggr, Serpent of Chaos", "Fafnir, Fallen Prince", "Skadi, Giant of the Hunt", "Vidar Odinsson", "Mimir, Headless One", "Hymir, Cauldron Bearer", "Garmr, Guard Dog of Helheim"};
	private String[] finalBossName = {"Thor", "Odin", "Freya", "Tyr", "Baldur"};
	
	Random random = new Random();
	
	public Boss(String name, int health, int strength, double critChance, double critDamageBoost, int evasion)
	{
		super(name, health, strength, critChance, critDamageBoost, evasion);
	}
	
	public Boss()
	{
		this(null, 0, 0, 0, 0, 0);
		//generates name and stats on an empty constructor
		super.generateName(bossNames);
		this.generateStats();
	}
	
	public int getTimeToBossAttack()
	{
		return this.timeToBossAttack;
	}
	
	//randomly generates boss stats
	private void generateStats()
	{
		this.setHealth(80 + random.nextInt(30));
		this.setStrength(10 + random.nextInt(7));
		this.setCritChance(random.nextInt(5));
		this.setCritDamageBoost(2);
		this.setEvasion(random.nextInt(15) + 1);
	}
	
	public int dealDamage()
	{
		//checks if the super attack is ready, if so, then the attack does 3 times the strength stat
		if(timeToBossAttack == 0)
		{
			timeToBossAttack = 3;
			return this.getStrength() * 3;
		}
		else
		{
			timeToBossAttack--;
			return super.dealDamage();
		}
	}
	
	//override so the boss drops significantly more gold and XP points than common enemy
	public int dropGold()
	{
		int goldDropped = random.nextInt(650) + 450;
		
		System.out.println(this.getName() + " Dropped " + goldDropped + " gold");
		
		return goldDropped;
	}
	
	public int dropXp()
	{
		int xpDropped = random.nextInt(85) + 45;
		
		System.out.println(this.getName() + " Dropped " + xpDropped + " XP");
		
		return xpDropped;
	}
	
	//methods to turn boss into final boss. Simpler than creating a whole new final boss class
	public void finalBoss()
	{
		this.generateNameFinalBoss();
		this.generateStatsFinalBoss();
	}
	
	private void generateStatsFinalBoss()
	{
		this.setHealth(90 + random.nextInt(30));
		this.setStrength(12 + random.nextInt(7));
		this.setCritChance(random.nextInt(5));
		this.setCritDamageBoost(2);
		this.setEvasion(random.nextInt(15) + 1);
	}
	
	private void generateNameFinalBoss()
	{
		this.setName(this.finalBossName[random.nextInt(this.finalBossName.length)]);
	}
}
