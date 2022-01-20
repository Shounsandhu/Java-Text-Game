
class Consumable extends Item{
	String itemEffect;//Healing,Mana boosting,BoostMaxHealth,BoostMaxMana,BoostXp
	int itemEffectMagnitude;//determines mangitude of effect
	
	public Consumable(String itemName, String itemType,int spawnChance,String itemEffect,int itemEffectMagnitude) {
		super(itemName,itemType,spawnChance);
		this.itemEffect=itemEffect;
		this.itemEffectMagnitude=itemEffectMagnitude;
		
	}
	public void applyEffect(Player player) {
		
		switch(itemEffect) {
		case("fullRestore"):
			player.hp=player.maxHp;
			System.out.println("Fully restored health");
		case("restoreHealth"):
			player.hp+=itemEffectMagnitude;
			if (player.hp>player.maxHp) {//makes sure overheal does not occur
				player.hp=player.maxHp;
			}
			System.out.println("restored "+Style.red(String.valueOf(itemEffectMagnitude))+" Health");
		
			break;
		case("increaseMaxHealth"):
			player.maxHp+=itemEffectMagnitude;
			System.out.println("Increased max health by "+Style.red(String.valueOf(itemEffectMagnitude)));
			break;
		case("increaseXp"):
			player.gainXp(itemEffectMagnitude);
			
		}
		player.inventory.remove(this);
	}
	public void printInfo() {
		super.printInfo();
		System.out.println("Effect:"+Style.yellow(itemEffect));
		System.out.println("Mangitude of Effect" +":"+Style.yellow(String.valueOf(itemEffectMagnitude)));
		
		
	
	}
	
	


}
