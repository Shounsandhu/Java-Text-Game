import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player {
	Scanner sc= new Scanner(System.in); 
	int[]newCoordinates= {0,0};//temporary variable that holds the new coordinates that player travels to after moving, these 
	Room currentRoom;		   //coordinates are then checked against a list of coordinates to ensure that the direction they are travelling in is valifd
	ArrayList<Item> inventory = new ArrayList<Item>();
	int level=1;
	boolean encounteredEnemies=false;//flag used to generate flavour text once player meets a monster for the first time
	int maxHp;
	int hp;
	int currentXp;
	int xpNeededForLevelUp;
	int floor=1;
	boolean isAlive=true;
	Weapon equippedWeapon;
	int bonusDamage=0;
	float xpLimitMultiplier=2.0f;//determines what value is multiplied by xp required to level up during a level up
	public Player(Room currentRoom,int maxHp,int xpNeededForLevelUp) {
		
		this.currentRoom=currentRoom;
		this.maxHp=maxHp;
		this.hp=maxHp;
		this.xpNeededForLevelUp=xpNeededForLevelUp;
	}
	public void equipWeapon(String itemName){
		Item weapon=findItemInInventory(itemName);
		if(weapon instanceof Weapon) {//item exists and is a weapon
			equippedWeapon=(Weapon) weapon;
			System.out.println(Style.cyan("I am now holding "+itemName));
		}
		else if(weapon!=null){//item exists but is not a weapon
			System.out.println(itemName+" is not a weapon");
		
		}
		else {//doesnt exist 
			System.out.println("Item not found");
		}
	}
	public void takeItem(String itemName) {
		Item item=currentRoom.findItemInRoom(itemName);
		
		
		if (currentRoom.findItemInRoom(itemName)!=null) {
			if(currentRoom.roomEnemies.size()==0) {//if the room has no enemies
				if(item instanceof Weapon) {
					System.out.println(FlavourText.weaponPickUp1);
				}
				else if(item instanceof Consumable) {
					System.out.println(FlavourText.consumablePickUp1);
				}
				else {
					System.out.println(FlavourText.itemPickUp1);
				}
				inventory.add(item);
				item.isInInventory=true;
				currentRoom.removeItem(item);
				System.out.println("Added "+Style.red(item.itemName)+" to inventory");//searches for item in room and removes it from room and adds it to player inventory
			}
			else {
				System.out.println(Style.cyan("I need to clear the room before I pick that up"));
			}
			
			
		}
		else {
			System.out.println(Style.red(itemName)+" not found in this room");
		}
		
	}

	public void dropItem(String itemName) {
		Item item=findItemInInventory(itemName);
		if (item!=null) {
			inventory.remove(inventory.indexOf(item));
			item.isInInventory=false;
			currentRoom.addItem(item);
			System.out.println("Dropped "+ Style.red(item.itemName)+" in "+Style.green(currentRoom.roomName));//searches for item in player inventory and removes it and places it in room
			if(item==equippedWeapon) {
				equippedWeapon=null;
			}
		}
		else {
			System.out.println(Style.red(itemName)+" not found in inventory");
		}
	}
	
	
	public void displayInventory() {
		System.out.println("Player inventory contains:");
		for (int x=0;x<inventory.size();x++) {
			Item item=inventory.get(x);
			
			
			
			
			if(item==equippedWeapon) {
				System.out.println(Style.red(item.itemName+" (equipped)"));
			}
			else {
				System.out.println(Style.red(item.itemName));
			}
			
			
		}
		
		
		
	}
	
	
	
	public Item findItemInInventory(String itemName) {
		Item currentItem;
		String currentItemName;
		//when taking an item, use the user inputted string to search for the index of the item in the arraylist of inventory
		for(int i=0;i<inventory.size();i++) {
			currentItem=inventory.get(i);
			currentItemName=currentItem.itemName;
			
			if(currentItemName.equals(itemName)) {
				
				return currentItem;//return item if item is found in inventory
			}
		}
		return null;//return null if item is not found
	}
	
	public void moveDown(List<Room>oldFloor,List<Room>newFloor) {
		if(currentRoom.hasHatch) {
			floor++;
			currentRoom=newFloor.get(0);
			System.out.println(Style.cyan("You carefully climb down"));
			
			
			
		}
		
		else {
			System.out.println("No Hatch Found");
		}
	
	
	}
	public void moveUp(List<Room>oldFloor,List<Room>newFloor) {
		if(currentRoom.hasLadder) {
			switch(floor){
				case 2:
					currentRoom=Container.hatchRoom1;
				
				
			}
			floor--;
			
			
			
			
			
			System.out.println(Style.cyan("You carefully climb up"));
			
			
			
		}
		
		else {
			System.out.println("No ladder Found");
		}
	
	
	}
	public void move(String dir,List<Room> rooms) {//dir is a parameter that represents the direction that the player will take
		//temporary variable to hold new coordinates
		newCoordinates=currentRoom.roomcoordinates.clone();//making new coordinates equal to current coordinates
		
		//this switch puts the changed coordinates in the newCoordinates
		switch(dir) {//adds to new coordinates depending on direction typed
			case "north":
				newCoordinates[1]++;
				break;
			case "east":
				newCoordinates[0]++;
				break;
			case "south":
				newCoordinates[1]--;
				break;
			case "west":
				newCoordinates[0]--;
				break;
			case "n":
				newCoordinates[1]++;
				break;
			case "e":
				newCoordinates[0]++;
				break;
			case "s":
				newCoordinates[1]--;
				break;
			case "w":
				newCoordinates[0]--;
				break;
			
				
				
			
				
		}
		
		//this for loop checks if new coordinates exists in the map, if it exists change the players current room to the room with the new coordinates
		for(int i=0;i<rooms.size();i++) {
			if(Arrays.equals(rooms.get(i).roomcoordinates, newCoordinates)) {//if this is true then it means that there is a match between one of 
																//the room coordinates and the new coordinates that the player wants to move to
				currentRoom=rooms.get(i);//changes currentroom that the player is in
				return;
			}	
		}
		System.out.println(Style.em(Style.blue("That path is blocked")));//if the for loop completed, then there were no matches
		
	}
	
	public void selfCheck() {
		System.out.println("Health: "+Style.yellow(String.valueOf(hp)));//self explanatory
		System.out.println("Xp: "+Style.yellow(String.valueOf(currentXp)));
	}
	public void examine(String item) {
		
		if((currentRoom.findItemInRoom(item)==null)&&(findItemInInventory(item)==null)){//findItem function uses item name to find it in the room or inventory
			System.out.println("Item not found in inventory or room");
			return;
		}
		else if(findItemInInventory(item)!=null) {//this else if only runs if room or inventory has the item, if this statement is true then that means the item is in your inventory
			findItemInInventory(item).printInfo();
		}

		else {//if the item exists in room or inventory and you know it is not inventory then search for the item in the room 
			currentRoom.findItemInRoom(item).printInfo();
			
			
		}
	}
	public void lookAround(List<Room> rooms) {//shows items in current room and paths leading out of room
		System.out.println("You are currently in a"+Style.green(currentRoom.roomName));
		currentRoom.displayFlavourText();
		currentRoom.displayItems();
		currentRoom.displayPaths(rooms,this);//by right it does not make sense for player class to have access to a list of rooms so i might change this later
		currentRoom.displayEnemies();
	}
	

	public void consume(String itemName) {
		Item consumable;
		if(findItemInInventory(itemName)==null) {// this if else checks if item exists
			System.out.println("item not found in inventory");
			return;
		}
		else {
			consumable=findItemInInventory(itemName);
		}
		
		
		
		
		if(consumable instanceof Consumable) {
			consumable=(Consumable)consumable;
			((Consumable) consumable).applyEffect(this);
		}
		else {
			System.out.println("this item is not consumable");
			return;
		}
		
	
	}
	public void checkEnemyHealth(Enemy enemy) {
		enemy.debugExamine();
	}
	
	
	
	
	
	
	public void debugAddItem(Item item){
		//adds item regardless of whether or not it is in the room
		inventory.add(item);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void attack(String enemyName) {//attack function is a bit messy at the moment, maybe handle weapon selection and error checking in main and make this purely for damage calculations
		String exitFlagInput;
		String weaponName;
		boolean hasWeapon=false;//used to check if player even has a weapon to fight with
		Weapon weapon;
		Enemy enemy=currentRoom.findEnemyInRoom(enemyName);
		weapon=equippedWeapon;
		if(enemy==null) {
			System.out.println(Style.cyan("I don't see a "+enemyName+" in this room"));
			return;
		}
		if(weapon==null) {
			System.out.println(Style.cyan("I haven't equipped a weapon yet"));
			return;
		}
		
		System.out.println(Style.cyan("Holding "+weapon.itemName+" I dash at the "+enemyName));
		
		
		
		
		while(true) {//this loop allows player to easily continue combat
			
			
				int varyAtk =(int) Math.round((Math.random() * (weapon.attackDamage/2))+(weapon.attackDamage*3/4)); //this math is messed up but it picks a number with range between 75% attack and 125% attack
				varyAtk+=bonusDamage;
				if(Math.floor(Math.random()*100)<weapon.criticalChance) {//critical hit
					enemy.hp-=varyAtk+Integer.valueOf((varyAtk)/2);//increases damage by 50%
					System.out.println(Style.em(Style.purple("Critical hit!")));
					System.out.println("You did "+Style.yellow(String.valueOf(varyAtk+Integer.valueOf((varyAtk)/2)))+" damage to "+Style.red(enemyName));
				}
				else {//non critical hit
					enemy.hp-=varyAtk;
					System.out.println("You did "+Style.yellow(String.valueOf(varyAtk))+" damage to "+Style.red(enemyName));
				}
				
				if(enemy.hp<=0) {
					System.out.println(enemy.enemyName+" has died");
					enemy.dropLoot(this);
					gainXp(enemy.xp);
					currentRoom.removeEnemy(enemy);
					//what im going to write below is painfully ugly but I hope its a temporary solution
					//basically, right now when i create 2 of the same enemies in a room, fighting one reduced the health of its counterpart as well since they are technically the same object
					//since the player can only fight one at a time, ill just replinish the health of all its counterparts when one of them dies
					//disgusting I know
//					currentRoom.findEnemyInRoom(enemyName).hp=currentRoom.findEnemyInRoom(enemyName).maxHp;
					return;
					
					
				}
				else {
					System.out.println(Style.red(enemy.enemyName)+" now has "+Style.yellow(String.valueOf(enemy.hp))+"hp");
					enemy.attack(this);//counter attack from enemy object
					if(hp<0) {
						System.out.println("You now have "+Style.yellow("0")+"hp");
						System.out.println(Style.red("YOU HAVE DIED"));
						isAlive=false;
						return;
					}
					System.out.println("You now have "+Style.yellow(String.valueOf(hp))+"hp");
					System.out.println("press enter to continue combat,type anything else to exit");
					exitFlagInput=sc.nextLine();
					if(exitFlagInput.isEmpty())
					{
						System.out.println("continuing combat");
						
					}
					else {
						System.out.println("exiting");
						return;
					}
					
				}
				
				
			
		}
		
		
	}
	public void gainXp(int xp) {
		currentXp+=xp;//adds xp to this player
		System.out.println("You gained " +Style.cyan(String.valueOf(xp)+"xp ")+"and you	currently have "+Style.cyan(String.valueOf(currentXp)+"xp"));
		checkIfLevelledUp();
	}
	public void checkIfLevelledUp() {
		if(currentXp>=xpNeededForLevelUp) {
			levelUp();
			
		}
		
		return;
		
	}
	public void levelUp() {
		level++;
		System.out.println(Style.cyan("You levelled up! You are now level "+level));
		System.out.println("You now do extra damage and your health has also increased");
		xpNeededForLevelUp*=xpLimitMultiplier;//increase xp needed to level up more
		maxHp+=20;//effect of level up
		hp+=40;//when levelling up you restore a bit of your current health
		if(hp>maxHp) {
			hp=maxHp;
		}
		bonusDamage+=1;//everytime you level up you do 3 extra damage
		checkIfLevelledUp();//in case xp overflows into next level
		
		
	}
	
}
	


