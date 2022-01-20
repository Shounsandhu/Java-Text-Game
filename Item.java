
public class Item {
	
	
	String itemName;
	String itemType;
	int spawnChance;
	Room itemRoom;
	
	boolean isInInventory;
	public Item(String itemName,String itemType,int spawnChance){//constructor
		this.itemName=itemName;
		this.itemType=itemType;
		
		this.spawnChance=spawnChance;
		
	}
	
	
	public void printInfo() {
		System.out.println("Name:"+Style.yellow(itemName));
		System.out.println("Type:"+Style.yellow(itemType));
		
		if(isInInventory==true) {
			System.out.println(itemName+" is currently in your inventory");
		}
	}
	
	
}
