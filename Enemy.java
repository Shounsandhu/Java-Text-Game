
public class Enemy {
	
	int maxHp;
	int atk;
	int hp;
	Room spawnRoom;
	Item loot;
	String enemyName;
	int xp;
	public Enemy(int maxHp, int atk,Item loot, int xp, String enemyName) {
		this.maxHp=maxHp;
		this.hp=maxHp;
		this.atk=atk;
		if(loot==null) {
			
		}
		else if(loot instanceof Weapon) {
			
			this.loot=new Weapon(loot.itemName,loot.itemType,loot.spawnChance,((Weapon)loot).attackDamage,((Weapon)loot).criticalChance);
			
		}
		else if(loot instanceof Consumable) {
			
			this.loot=new Consumable(loot.itemName,loot.itemType,loot.spawnChance,((Consumable)loot).itemEffect,((Consumable)loot).itemEffectMagnitude);
		}
		else {
			this.loot=new Item(loot.itemName,loot.itemType,loot.spawnChance);
		}
		
		this.enemyName=enemyName;
		this.xp=xp;
	}
	
	
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void attack(Player player) {
		int atkAfterVary =(int) Math.round((Math.random() * (atk/2))+(atk*3/4)); //this math is messed up but it picks a number with range between 75% attack and 125% attack 
		System.out.println(Style.red(enemyName)+" attacks you and does "+Style.yellow(String.valueOf(atkAfterVary))+" damage");
		player.hp-=atkAfterVary;
		
	}
	public void dropLoot(Player player) {
		if(loot==null) {//if there is no loot then do nothing
			return;
		}
		
		else {
			player.inventory.add(loot);//drops directly in inventory
			System.out.println(Style.purple(enemyName)+" dropped a "+Style.red(loot.itemName));
			
		}
	}
	public void debugExamine() {
		System.out.println("Enemy has: "+hp+"hp");
		System.out.println("Enemy does: "+atk+" base damage");
	}
	
	
	
	
}
