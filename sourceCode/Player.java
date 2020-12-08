import java.util.Random;
/* 
Code by Joshua Knight
Made for CS&141 class at Olympic College
*/
public class Player extends Entity
{
	private String firstName;
	private int healthPotions;
	private int gold;
	private int healAmount = 15;
	private int mp;
	private int maxMp;
	private int magic;
	private int level;
	private int xp;
	private int xpToLevelUp = 40;
	private Weapon weapon;
	
	Random random = new Random();
	//Male and Female names
	private String[] namesM = {"Bjorn", "Erik", "Gorm", "Halfdan", "Harald", "Cnut", "Leif", "Torsten", "Ulf", "Brynjar", "Ragnar", "Sigurd", "Magnus", "Ivar"};
	private String[] namesF = {"Astrid", "Frida", "Gertrud", "Hilda", "Gunhild", "Helga", "Sigrid", "Sif", "Tora", "Yrsa", "Ingrid", "Solveig", "Erika", "Frida"};
	
	public Player(String name, int level, int health, int mp, int strength, int magic, double critChance, double critDamageBoost, int evasion, int healthPotions, int gold, Weapon weapon)
	{
		super(name, health, strength, critChance, critDamageBoost, evasion);
		this.setGold(gold);
		this.setMp(mp);
		this.setMaxMp(this.getMp());
		this.setMagic(magic);
		this.weapon = weapon;
		this.setHealthPotions(healthPotions);
		this.setLevel(level);
		this.setFirstName(name);
	}
	
	public Player()
	{
		//randomly generates player, this is the way this class is intended to be used. other constructor is mainly for debugging and test reasons
		this(null, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
		generateName();
		generateStats();
	}
	
	private void generateName()
	{
		//gender reveal party (without the forest fires)
		int mOrF = random.nextInt(2);
		
		if(mOrF == 0)
		{
			//sets the first name for storytelling reasons
			this.setFirstName(namesM[random.nextInt(namesM.length)]);
			this.setName(this.getFirstName() + " " + namesM[random.nextInt(namesM.length)] + "sson");
		}
		else
		{
			this.setFirstName(namesF[random.nextInt(namesF.length)]);
			this.setName(this.getFirstName() + " " + namesM[random.nextInt(namesM.length)] + "sdottir");
		}
	}
	
	private void generateStats()
	{
		//initial spawn weapon (good agains peanut butter)
		Weapon knife = new Weapon("Butter Knife", 1, 0, 0, 0, 0, 0, 0, 0, 0);
		
		//initial player stats
		this.setHealth(40 + random.nextInt(11));
		this.setMaxHealth(this.getHealth());
		this.setHealAmount(this.getMaxHealth() / 2);
		this.setMp(random.nextInt(10) + 6);
		this.setMaxMp(this.getMp());
		this.setStrength(random.nextInt(4) + 12);
		this.setMagic(random.nextInt(8) + 7);
		this.setCritChance(5);
		this.setCritDamageBoost(1.5);
		this.setEvasion(random.nextInt(15) + 5);
		this.setHealthPotions(3);
		this.setGold(50);
		this.equip(knife);
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

	public void setPotions(int potions)
	{
		this.healthPotions = potions;
	}
	//I managed to depricate my own class before releasing this. I have two setters and getters for potions but fixing that would require a lot of tedious work and testing
	public int getPotions()
	{
		return this.healthPotions;
	}
	
	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public void setGold(int gold)
	{
		if(gold >= 0)
		{
			this.gold = gold;
		}
		else
		{
			this.gold = 0;
		}
	}
	
	public int getGold()
	{
		return this.gold;
	}
	
	public int getHealthPotions() 
	{
		return healthPotions;
	}

	public void setHealthPotions(int healthPotions) 
	{
		this.healthPotions = healthPotions;
	}

	public int getHealAmount() 
	{
		return healAmount;
	}

	public void setHealAmount(int healAmount) 
	{
		if(healAmount >= 0)
		{
			this.healAmount = healAmount;
		}
		else
		{
			this.healAmount = 0;
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
			magic = 0;
		}
	}

	public int getLevel() 
	{
		return level;
	}

	public void setLevel(int level) 
	{
		if(level >= 0)
		{
			this.level = level;
		}
		else
		{
			this.level = 0;
		}
	}

	public int getXp() 
	{
		return xp;
	}

	public void setXp(int xp) 
	{
		if(xp >= 0)
		{
			this.xp = xp;
		}
		else
		{
			this.xp = 0;
		}
	}

	public int getXpToLevelUp() 
	{
		return xpToLevelUp;
	}

	public void setXpToLevelUp(int xpToLevelUp) 
	{
		if(xpToLevelUp >= 0)
		{
			this.xpToLevelUp = xpToLevelUp;
		}
		else
		{
			this.xpToLevelUp = 10;
		}
	}
	
	public int getMaxMp() 
	{
		return maxMp;
	}

	public void setMaxMp(int maxMp) 
	{
		if(maxMp >= 0)
		{
			this.maxMp = maxMp;
		}
		else
		{
			this.maxMp = 10;
		}
	}

	public int dealDamage()
	{
		//total damage is strength stat plus the damage stat of equipped weapon
		int totalDamage = this.weapon.getDamage() + this.getStrength();
		int critRoll = random.nextInt(100) + 1;
		//rolls for crit damage. If it hits, then it does total damage * crit damage boost
		if(critRoll <= this.getCritChance())
		{
			System.out.println("Critical Hit!");
			Terminal.sleep(1);
			totalDamage = (int)(totalDamage * this.getCritDamageBoost());
		}
		
		return totalDamage;
	}
	//similar to dealDamage method, but the algorithm is different for total damage and it consumes mana points
	public int dealMagicDamage()
	{
		int totalDamage = this.getMagic() * 2;
		int critRoll = random.nextInt(100) + 1;
		
		if(critRoll <= this.getCritChance())
		{
			System.out.println("Critical Hit! " + critRoll);
			Terminal.sleep(1);
			totalDamage = (int)(totalDamage * this.getCritDamageBoost());
		}
		
		this.setMp(this.getMp() - 3);
		return totalDamage;
	}
	
	//take damage method for player that takes in an enemy object for their deal damage method
	public void takeDamage(Enemy enemy)
	{
		int rollEvasion = random.nextInt(100) + 1;
		//rolls to see if player dodges the attack
		if(rollEvasion <= this.getEvasion()/2)
		{
			Terminal.sleep(1);
			System.out.println(this.getName() + " Evaded the Attack");
			return;
		}
		//calculates enemy damage from their deal damage method
		int totalDamage = enemy.dealDamage();
		//mention if attack did nothing
		if(totalDamage <= 0)
		{
			Terminal.sleep(1);
			System.out.println(enemy.getName() + " Attacked to No effect");
		}
		//attack summary
		else
		{
			this.setHealth(this.getHealth() - totalDamage);
			Terminal.sleep(1);
			System.out.println(enemy.getName() + " Attacked and did " + totalDamage + " damage");
			Terminal.sleep(1);
			System.out.println(this.getName() + " has " + this.getHealth() + " health remaining");
		}
		//if the enemy is a boss, mention that the boss special attack it happening in x turns.
		if(enemy instanceof Boss)
		{
			Boss boss = (Boss)enemy;
			
			Terminal.sleep(1);
			
			if(boss.getTimeToBossAttack() == 0)
			{
				System.out.println("Special Attack Next Turn");
			}
			else
			{
				System.out.println("Special Attack in " + boss.getTimeToBossAttack() + " Turns");
			}
		}
	}
	//heal method used in the battle and intermission classes
	public void heal()
	{
		if(this.getHealth() + this.healAmount > this.getMaxHealth() )
		{
			this.setHealth(this.getMaxHealth());
			this.setPotions(this.healthPotions - 1);
		}
		else
		{
			this.setHealth(this.getHealth() + healAmount);
			this.setPotions(this.healthPotions - 1);
		}
		
		System.out.println(this.getName() + " used a health potion and now has " + this.getHealth() + " health");
		Terminal.sleep(1);
		
		if(this.getPotions() == 1)
		{
			System.out.println(this.getName() + " has " + this.getPotions() + " potion remaining");
		}
		else
		{
			System.out.println(this.getName() + " has " + this.getPotions() + " potions remaining");
		}
		
		if(this.getHealth() == this.getMaxHealth())
		{
			Terminal.sleep(1);
			System.out.println(this.getName() + " has max health!");
		}
	}
	//magic heal method used in battle and intermission classes
	public void magicHeal()
	{
		int healAmount = this.getMagic() * 2;
		
		if(this.getHealth() + healAmount > this.getMaxHealth() )
		{
			this.setHealth(this.getMaxHealth());
			this.setMp(this.getMp() - 2);
		}
		else
		{
			this.setHealth(this.getHealth() + healAmount);
			this.setMp(this.getMp() - 2);
		}
		
		System.out.println(this.getName() + " casts a health spell and now has " + this.getHealth() + " health");
		Terminal.sleep(1);
	
		System.out.println(this.getName() + " has " + this.getMp() + " MP remaining");
		
		
		if(this.getHealth() == this.getMaxHealth())
		{
			Terminal.sleep(1);
			System.out.println(this.getName() + " has max health!");
		}
	}
	//level up method that takes in an int that represents a stat to increase
	public void levelUp(int statToLevel)
	{
		//each level up guarantees an increase to health, the potion heal amount, and a refill of MP
		this.setMaxHealth(this.getMaxHealth() + 5);
		this.setHealAmount(this.getMaxHealth() / 2);
		this.setMp(this.getMaxMp());
		
		switch(statToLevel)
		{
		case 1:
			this.setMaxMp(this.getMaxMp() + 3);
			this.setMp(this.getMaxMp());
			break;
		case 2:
			this.setStrength(this.getStrength() + 2);
			break;
		case 3:
			this.setMagic(this.getMagic() + 2);
			break;
		case 4:
			this.setCritChance(this.getCritChance() + .5);
			break;
		case 5:
			this.setCritDamageBoost(this.getCritDamageBoost() + .5);
			break;
		case 6:
			this.setEvasion(this.getEvasion() + 2);
		}
		//removes xp and increases level
		this.setXp(this.getXp() - this.getXpToLevelUp());
		this.setLevel(this.getLevel() + 1);
	}
	//method to equip weapon
	public void equip(Weapon weapon)
	{
		//applies weapon if there is none then applies the stats
		if(this.weapon == null)
		{
			this.weapon = weapon;
			applyStats();
		}
		//unapply the stat booster on previous weapon, get gold from the "sale", then set weapon to null and run method again
		else
		{
			unapplyStats();
			sell();
			this.weapon = null;
			equip(weapon);
		}
		
	}
	//upgrades equipped weaon then updates player stats
	public void upgradeWeapon()
	{
		this.unapplyStats();
		this.getWeapon().upgrade();
		this.applyStats();
	}
	
	public Weapon getWeapon()
	{
		return this.weapon;
	}
	//increases player stats by the stats of equipped weapon
	private void applyStats()
	{
		this.setHealth(this.getHealth() + this.weapon.getHealth());
		this.setMaxHealth(this.getMaxHealth() + this.weapon.getHealth());
		this.setHealAmount(this.getMaxHealth() / 2);
		this.setMp(this.getMp() + this.weapon.getMp());
		this.setMaxMp(this.getMaxMp() + this.weapon.getMp());
		this.setStrength(this.getStrength() + this.weapon.getStrength());
		this.setMagic(this.getMagic() + this.weapon.getMagic());
		this.setCritChance(this.getCritChance() + this.weapon.getCritChance());
		this.setCritDamageBoost(this.getCritDamageBoost() + this.weapon.getCritDamageBoost());
		this.setEvasion(this.getEvasion() + this.weapon.getEvasion());
	}
	//removes equipped weapon stats
	private void unapplyStats()
	{
		this.setHealth(this.getHealth() - this.weapon.getHealth());
		this.setMaxHealth(this.getMaxHealth() - this.weapon.getHealth());
		this.setMp(this.getMp() - this.weapon.getMp());
		this.setMaxMp(this.getMaxMp() - this.weapon.getMp());
		this.setStrength(this.getStrength() - this.weapon.getStrength());
		this.setMagic(this.getMagic() - this.weapon.getMagic());
		this.setCritChance(this.getCritChance() - this.weapon.getCritChance());
		this.setCritDamageBoost(this.getCritDamageBoost() - this.weapon.getCritDamageBoost());
		this.setEvasion(this.getEvasion() - this.weapon.getEvasion());
	}
	//give player gold based on the value of weapon
	private void sell()
	{
		this.setGold(this.getGold() + this.weapon.getValue());
		System.out.println("Sold weapon for " + (int)(this.weapon.getValue() * .8) + " gold");
		Terminal.sleep(1);
	}
	//heads up display at the top of the screen. allows players to know vital info about their characters at all times
	public void hud()
	{
		//sets double stats to 2 decimal places
		String formatCritChance = String.format("%.2f", this.getCritChance());
		String formatCritDamage = String.format("%.2f", this.getCritDamageBoost());
		
		System.out.println("Name: " + this.getName() + "     Level: " + this.getLevel() + "     XP: " + this.getXp() + "/" + this.getXpToLevelUp() + "     Health: "  + this.getHealth() + "/" + this.getMaxHealth() + "     MP: " + this.getMp() + "/" + this.getMaxMp() + "     Weapon Damage: " + this.weapon.getDamage() + "     Potions: " + this.getPotions() + "     Gold: " + this.getGold());
		System.out.println();
		System.out.println("Strength: " + this.getStrength() + "     Magic: " + this.getMagic() + "     Crit Chance: " + formatCritChance + "     Crit Damage Boost: " + formatCritDamage + "     Evasion: " + this.getEvasion());
		System.out.println();
		System.out.println();
	}
}