
class Weapon extends Item {
	int criticalChance;
	int attackDamage;
	public Weapon(String itemName, String itemType,int spawnChance,int attackDamage,int criticalChance) {
		super(itemName, itemType,spawnChance);
		this.attackDamage=attackDamage;
		this.criticalChance=criticalChance;
	}
	
	public void printInfo() {
		super.printInfo();
		System.out.println("Attack Damage:"+Style.yellow(String.valueOf(attackDamage)));
		System.out.println("Critical chance:"+Style.yellow(String.valueOf(criticalChance)));
		
		
	
	}
}
