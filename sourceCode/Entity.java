import java.util.Random;
/* 
Code by Joshua Knight
Made for CS&141 class at Olympic College
*/
public class Entity 
{
	private int health;
	private int maxHealth;
	private int strength;
	private String name;
	private double critChance;
	private double critDamageBoost;
	private int evasion;
	Random random = new Random();
	
	public Entity(String name, int health, int strength, double critChance, double critDamageBoost, int evasion)
	{
		this.setName(name);
		this.setHealth(health);
		this.setMaxHealth(health);
		this.setStrength(strength);
		this.setCritChance(critChance);
		this.setCritDamageBoost(critDamageBoost);
		this.setEvasion(evasion);
	}
	
	public void setHealth(int health)
	{
		if(health >= 0)
		{
			this.health = health;
		}
		else
		{
			this.health = 0;
		}
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	public void setMaxHealth(int maxHealth)
	{
		if(maxHealth >= 0)
		{
			this.maxHealth = maxHealth;
		}
		else
		{
			this.maxHealth = 0;
		}
	}
	
	public int getMaxHealth()
	{
		return this.maxHealth;
	}
	
	public void setStrength(int strength)
	{
		if(strength >= 0)
		{
			this.strength = strength;
		}
		else
		{
			this.strength = 0;
		}
	}
	
	public int getStrength()
	{
		return this.strength;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setCritChance(double critChance)
	{
		if(critChance > 0)
		{
			this.critChance = critChance;
		}
		else
		{
			this.critChance = 0;
		}
	}
	
	public double getCritChance()
	{
		return this.critChance;
	}
	
	public void setCritDamageBoost(double critDamage)
	{
		if(critDamage > 0)
		{
			this.critDamageBoost = critDamage;
		}
		else
		{
			this.critDamageBoost = 0;
		}
	}
	
	public double getCritDamageBoost()
	{
		return this.critDamageBoost;
	}
	
	public void setEvasion(int evasion)
	{
		if(evasion > 0)
		{
			this.evasion = evasion;
		}
		else
		{
			this.evasion = 0;
		}
	}
	
	public int getEvasion()
	{
		return this.evasion;
	}
	
	public int dealDamage()
	{
		int totalDamage = this.getStrength();
		int critRoll = random.nextInt(100) + 1;
		
		//rolls for critical hit, if it hits, then the damage is equal to strength stat * damage boost
		if(critRoll <= this.getCritChance())
		{
			Terminal.sleep(1);
			System.out.println("Critical Hit!");
			totalDamage = (int)(totalDamage * this.getCritDamageBoost());
		}
		
		return totalDamage;
	}
	
	//takes in another entity object that deals the damage that this entity takes
	public void takeDamage(Entity enemy)
	{
		int totalDamage = enemy.dealDamage();
		
		if(totalDamage <= 0)
		{
			Terminal.sleep(1);
			System.out.println(enemy.getName() + " Attacked to No effect");
		}
		else
		{
			this.health -= totalDamage;
			Terminal.sleep(1);
			System.out.println(enemy.getName() + " Attacked and did " + totalDamage + " damage");
			Terminal.sleep(1);
			System.out.println(this.name + " has " + this.getHealth() + " health remaining");
		}
	}
}
