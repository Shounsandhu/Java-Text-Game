
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Room {

	ArrayList<Item> roomItems = new ArrayList<Item>();//list that stores the different items in the room
	ArrayList<Enemy> roomEnemies = new ArrayList<Enemy>();
	String roomName;
	String flavourText;
	boolean hasHatch;//determines if the room will have a hatch leading down to the next floor
	boolean hasLadder;
	int[] roomcoordinates=new int [2];
	
//	public Room(String roomName,String flavourText) {
//		this.roomName=roomName;
//		this.flavourText=flavourText;
////		this.roomcoordinates=roomcoordinates;
//	}
	
	
	public void addEnemy(Enemy enemy) {
		Enemy enemyToBeAdded=new Enemy(enemy.maxHp,enemy.atk,enemy.loot,enemy.xp,enemy.enemyName);//int maxHp, int atk,Item loot, String enemyName,int id)
		//by making an instance of the enemy to be added you make each of the indiviual enemies of the same type to be separate instances. If the above is 
		//not done then all enemies of the same type will share attributes
		//probably a better solution than this but I would have to rewrite a lot more than just one line
		roomEnemies.add(enemyToBeAdded);
		enemy.spawnRoom=this;
		
	}
	public void removeEnemy(Enemy enemy) {
		roomEnemies.remove(enemy);
	}
	public void addItem(Item item) {
		if(item instanceof Weapon) {
			
			Weapon weaponToBeAdded=new Weapon(item.itemName,item.itemType,item.spawnChance,((Weapon) item).attackDamage,((Weapon) item).criticalChance);
			roomItems.add(weaponToBeAdded);	
			item.itemRoom=this;
			return;
		}
		if(item instanceof Consumable) {
			
			Consumable consumableToBeAdded=new Consumable(item.itemName,item.itemType,item.spawnChance,((Consumable)item).itemEffect,((Consumable)item).itemEffectMagnitude);
			roomItems.add(consumableToBeAdded);	
			item.itemRoom=this;
			return;
		}
		else {
			Item itemToBeAdded=new Item(item.itemName,item.itemType,item.spawnChance);
			roomItems.add(itemToBeAdded);
			item.itemRoom=this;
			return;
		}
		
		
	}
	public void removeItem(Item item) {
		roomItems.remove(roomItems.indexOf(item));
	}
	public void displayFlavourText() {
		System.out.println(Style.cyan(Style.em(flavourText)));
	}
	public void displayItems() {
		
		
		if(roomItems.size()==0) {
			System.out.println("You see "+Style.red("nothing")+" you can pick up");
			return;
		}
		System.out.print("You see a ");
		//the below code only runs if there is more than 1 item in the room, it iterates through all of them and prints their name
		for (int x=0;x<roomItems.size();x++) {
			
			Item item=roomItems.get(x);
			if(roomItems.size()==1) {
				System.out.println(Style.red(roomItems.get(0).itemName));
				return;
			}
			
			
			if(x==roomItems.size()-1) {//most of these if statements are to maintain the sentence structure and grammar
				System.out.println(" and a "+Style.red(item.itemName));
				//System.out.print();
				return;
			}
			else if(x==roomItems.size()-2) {
				System.out.print(Style.red(item.itemName));
			}
			else {
				System.out.print(Style.red(item.itemName)+",");
			}
			
			
		}
		
	}
	public void displayEnemies() {
		if(roomEnemies.size()==0) {//checks if there are any enemies in the room
			return;
		}
		
		for(int i=0;i<roomEnemies.size();i++) {//if there are enemies then print their name
			Enemy enemy=roomEnemies.get(i);
			System.out.println("There is a " + Style.purple(enemy.enemyName));
		}
	}
	public Item findItemInRoom(String itemName) {
		String currentItemName;//placeholder variable used to store the names of tiem
		//when taking an item, use the user inputted string to search for the index of the item in the arraylist of roomitems
		for(int i=0;i<roomItems.size();i++) {
			currentItemName=roomItems.get(i).itemName;
			
			if(currentItemName.equals(itemName)) {
				
				return roomItems.get(i);//return item object if item is found in room
			}
		}
		return(null);//return null if the item is not found in the room
	}
	public void displayPaths(List<Room> rooms,Player player) {
		//this is all super messy
		int []currentcoords=this.roomcoordinates.clone();
		int []north= {currentcoords[0],currentcoords[1]+1};//finds out the rooms that is relatively north by one unit
		int []east= {currentcoords[0]+1,currentcoords[1]};
		int []south= {currentcoords[0],currentcoords[1]-1};
		int []west= {currentcoords[0]-1,currentcoords[1]};
		for(int i=0;i<rooms.size();i++) {
			if(Arrays.equals(rooms.get(i).roomcoordinates, north)) {
				System.out.println("There is a path to the "+Style.blue("north"));
			}
		}
		
		for(int i=0;i<rooms.size();i++) {
			if(Arrays.equals(rooms.get(i).roomcoordinates, east)) {
				System.out.println("There is a path to the "+Style.blue("east"));
			}
		}
		
		for(int i=0;i<rooms.size();i++) {
			if(Arrays.equals(rooms.get(i).roomcoordinates, south)) {
				System.out.println("There is a path to the "+Style.blue("south"));
			}
		}
		
		for(int i=0;i<rooms.size();i++) {
			if(Arrays.equals(rooms.get(i).roomcoordinates, west)) {
				System.out.println("There is a path to the "+Style.blue("west"));
			}
		}
		if(hasHatch==true) {
			System.out.println("There is a path "+Style.blue("down"));
		}
		if(hasLadder==true) {
			System.out.println("There is a path "+Style.blue("up"));
		}
		
	
	}
	public Enemy findEnemyInRoom(String enemyName) {
		Enemy currentEnemy;
		String currentEnemyName;
		//when taking an item, use the user inputted string to search for the index of the item in the arraylist of inventory
		for(int i=0;i<roomEnemies.size();i++) {
			currentEnemy=roomEnemies.get(i);
			currentEnemyName=currentEnemy.enemyName;
			
			if(currentEnemyName.equals(enemyName)) {
				return currentEnemy;//return item if item is found in inventory
			}
		}
		return null;//return null if item is not found
	}
	
	
}
