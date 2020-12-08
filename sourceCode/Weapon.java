import java.util.ArrayList;
import java.util.Random;
/* 
Code by Joshua Knight
Made for CS&141 class at Olympic College
*/
public class Weapon 
{
	private String name;
	private int damage;
	private int health;
	private int mp;
	private int strength;
	private int magic;
	private double critChance;
	private double critDamageBoost;
	private int evasion;
	private int value;
	private String[] stats = {"health", "mp", "strength", "magic", "critChance", "critDamageBoost", "evasion"};
	//I love Ymir, his skull is comfy
	private String[] prefix = {"Tyr's", "Odin's", "Thor's", "Freya's", "Ymir's", "Loki's", "Jormungandr's", "Fenrir's", "Heimdallr's", "Baldur's"};
	//Weapon type is purely for flavor only, there are no special differences
	private String[] weaponType = {"Blade of", "Spear of", "Dagger of", "Shield of", "Axe of", "Flail of", "Bow of", "Hammer of"};
	//REMOVED is there because I had an armor system that I scrapped near the end of development because it was too broken and impossible to balance. Removing it from array would cause problems due to the generate name switch statement
	private String[] largestStatName = {"Eternal Youth", "Seidr", "REMOVED", "Might", "Wisdom", "Fortune", "Hidden Strength", "Blinding Speed"};
	Random rand = new Random();
	
	public Weapon(String name, int damage, int health, int mp, int strength, int magic, double critChance, double critDamageBoost, int evasion, int value)
	{
		this.setName(name);
		this.setDamage(damage);
		this.setHealth(health);
		this.setMp(mp);
		this.setStrength(strength);
		this.setMagic(magic);
		this.setCritChance(critChance);
		this.setCritDamageBoost(critDamageBoost);
		this.setEvasion(evasion);
	}
	
	public Weapon()
	{
		//generates random weapon, this is the way the class is intended to be used. Top constructor is used for default weapon and debugging and testing
		this(null, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		generateStats();
		generateName();
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getDamage() 
	{
		return damage;
	}

	public void setDamage(int damage) 
	{
		if(damage >= 0)
		{
			this.damage = damage;
		}
		else
		{
			this.damage = 1;
		}
	}

	public int getHealth() 
	{
		return health;
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

	public int getMp() 
	{
		return mp;
	}

	public void setMp(int mp) 
	{
		if(mp >= 0)
		{
			this.mp = mp;
		}
		else
		{
			this.mp = 0;
		}
	}

	public int getStrength() 
	{
		return strength;
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

	public int getMagic() 
	{
		return magic;
	}

	public void setMagic(int magic) 
	{
		if(magic >= 0)
		{
			this.magic = magic;
		}
		else
		{
			this.magic = 0;
		}
	}

	public double getCritChance() 
	{
		return critChance;
	}

	public void setCritChance(double critChance) 
	{
		if(critChance >= 0)
		{
			this.critChance = critChance;
		}
		else
		{
			this.critChance = 0;
		}
	}

	public double getCritDamageBoost() 
	{
		return critDamageBoost;
	}

	public void setCritDamageBoost(double critDamageBoost) 
	{
		if(critDamageBoost >= 0)
		{
			this.critDamageBoost = critDamageBoost;
		}
		else
		{
			this.critDamageBoost = 0;
		}
	}

	public int getEvasion() 
	{
		return evasion;
	}

	public void setEvasion(int evasion) 
	{
		if(evasion >= 0)
		{
			this.evasion = evasion;
		}
		else
		{
			this.evasion = 0;
		}
	}
	
	public int getValue() 
	{
		return value;
	}

	public void setValue(int value) 
	{
		if(value >= 0)
		{
			this.value = value;
		}
		else
		{
			this.value = 0;
		}
	}

	private void generateName()
	{
		//algorithm that chooses last part of weapon name based on the highest stat
		double largestStat = this.health;
		int largestStatArrayIndex = 0;
		
		if(this.mp > largestStat)
		{
			largestStat = this.mp;
			largestStatArrayIndex = 1;
		}
		
		if(this.strength > largestStat)
		{
			largestStat = this.strength;
			largestStatArrayIndex = 3;
		}
		if(this.magic > largestStat)
		{
			largestStat = this.magic;
			largestStatArrayIndex = 4;
		}
		//crit stats weighted because they are reduced in stat distribution
		if(this.critChance * 5 > largestStat)
		{
			largestStat = this.critChance;
			largestStatArrayIndex = 5;
		}
		if(this.critDamageBoost * 10 > largestStat)
		{
			largestStat = this.critDamageBoost;
			largestStatArrayIndex = 6;
		}
		if(this.evasion > largestStat)
		{
			largestStat = this.evasion;
			largestStatArrayIndex = 7;
		}
		
		this.name = prefix[rand.nextInt(prefix.length)] + " " + weaponType[rand.nextInt(weaponType.length)] + " " + largestStatName[largestStatArrayIndex];
	}
	
	private void generateStats()
	{
		// 4 rounds of stat distributions
		int statDistributionsLeft = 4;
		//calculates total amount of stat points distributed
		int totalStats = 0;
		//generates damage and adds amount to total stats
		this.damage = rand.nextInt(5) + 1;
		totalStats += this.damage;
		//runs as long as there are stat distributions left
		for(int i = statDistributionsLeft; i > 0; i--)
		{
			//selects stat to increase
			int statChoice = rand.nextInt(stats.length);
			//chooses how much the stat will increase by
			int statAmount = rand.nextInt(5) + 1;
			//find stat to increase then adds it to the total stats
			switch(statChoice)
			{
			case 0:
				this.health += statAmount;
				totalStats += statAmount;
				break;
			case 1:
				this.mp += statAmount;
				totalStats += statAmount;
				break;
			case 2:
				this.strength += statAmount;
				totalStats += statAmount;
				break;
			case 3:
				this.magic += statAmount;
				totalStats += statAmount;
				break;
			case 4:
				//altered for balance, adding up to a 5% crit chance per distribution would be busted, same for damage boost.
				this.critChance += (double)statAmount/5;
				totalStats += statAmount;
				break;
			case 5:
				this.critDamageBoost += (double)statAmount/10;
				totalStats += statAmount;
				break;
			case 6:
				this.evasion += statAmount;
				totalStats += statAmount;
				break;
			}
		}
		//generates 10 points of value per stat point distributed
		this.value = totalStats * 10;
	}
	
	public void upgrade()
	{
		//only increases stats that are generated with the weapon
		this.damage += 1;
		
		if(this.getHealth() > 0)
		{
			this.health += 3;
		}
		if(this.getMp() > 0)
		{
			this.mp += 1;
		}
		if(this.getStrength() > 0)
		{
			this.strength += 1;
		}
		if(this.getMagic() > 0)
		{
			this.magic += 1;
		}
		if(this.getCritChance() > 0)
		{
			this.critChance += .2;
		}
		if(this.getCritDamageBoost() > 0)
		{
			this.critDamageBoost += .2;
		}
		if(this.getEvasion() > 0)
		{
			this.evasion += 1;
		}
		
		this.value += 175;
	}
	//similar to enemy class, it made sense to have this method here instead of elsewhere
	public static ArrayList<Weapon> generateWeapons(int size)
	{
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		
		for(int i = 0; i < weapons.size(); i++)
		{
			weapons.add(new Weapon());
		}
		
		return weapons;
	}
	//toString only shows stats that the weapon has
	public String toString()
	{
		String displayWeapon = this.getName() + "\n" + this.getDamage() + " Damage\n";
		
		if(this.getHealth() > 0)
		{
			displayWeapon = displayWeapon + "+" + this.getHealth() + " Health\n";
		}
		if(this.getMp() > 0)
		{
			displayWeapon = displayWeapon + "+" + this.getMp() + " MP\n";
		}
		if(this.getStrength() > 0)
		{
			displayWeapon = displayWeapon + "+" + this.getStrength() + " Strength\n";
		}
		if(this.getMagic() > 0)
		{
			displayWeapon = displayWeapon + "+" + this.getMagic() + " Magic\n";
		}
		if(this.getCritChance() > 0)
		{
			displayWeapon = displayWeapon + "+" + this.getCritChance() + " Crit Chance\n";
		}
		if(this.getCritDamageBoost() > 0)
		{
			displayWeapon = displayWeapon + "+" + this.getCritDamageBoost() + " Crit Damage Boost\n";
		}
		if(this.getEvasion() > 0)
		{
			displayWeapon = displayWeapon + "+" + this.getEvasion() + " Evasion\n";
		}
		
		displayWeapon = displayWeapon + this.getValue() + " Gold";
		
		return displayWeapon;
	}
}