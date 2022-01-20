import java.util.*;
public class MainClass {
	
	public static void main(String[] args) 
	{
		for(int i=0;i<50;i++) {
			System.out.println();
		}
		
		
		String input;//stores raw input
		String[] splitinput;//string to store the contents of input as an array
		Scanner sc= new Scanner(System.in); 
		MapGenerator mapgen=new MapGenerator(3);
		
		
		ArrayList<ArrayList<Room>>floors=new ArrayList<ArrayList<Room>>();//stores all the floors
		
		ArrayList<Room> rooms1 = new ArrayList<Room>();//array of rooms for first floor
		ArrayList<Room> rooms2 = new ArrayList<Room>();
//		ArrayList<Room> rooms3 = new ArrayList<Room>();
		
		
		
		int numberOfRooms=15;
		int maxEnemiesPerRoom=5;
		int maxItemsPerRoom=5;
		int playerStartingHealth=150;
		
		
		
		Container container=new Container();//contains different enemy objects and item objects.
		container.initEnemies1();
		container.initItems1();
		container.initEnemies2();
		container.initItems2();
		
		rooms1=mapgen.generateFloor(container.worldEnemies1, container.worldItems1, numberOfRooms,maxEnemiesPerRoom,maxItemsPerRoom,1);//enemies,items,numofRooms
		rooms2=mapgen.generateFloor(container.worldEnemies2, container.worldItems2, numberOfRooms, maxEnemiesPerRoom, maxItemsPerRoom,2);
		Player player=new Player(rooms1.get(0),playerStartingHealth,10);//make a player(spawnroom, and health and xp needed to level up)
		player.debugAddItem(container.CopperKnife);
		player.debugAddItem(container.Bandage);
		System.out.println(FlavourText.spawn);
		
		
		
		
		rooms1.get(0).roomEnemies.clear();//to make sure spawn room has no enemies
		
		
		
		floors.addAll(Arrays.asList(rooms1,rooms2));
		System.out.println("---------------------------------------------------");
		player.lookAround(rooms1);
		
		while(player.isAlive) 
		{
			
			if((player.currentRoom.roomEnemies.size()!=0)&&(player.encounteredEnemies==false)){//check if this is the first time meeting a monster, if so generate flavour text
				player.encounteredEnemies=true;
				System.out.println(FlavourText.firstMonsterEncounter);
			}
			
			
			System.out.println("---------------------------------------------------");
			System.out.println("Current" +Style.red(" health ")+Style.yellow(String.valueOf(player.hp))+"/"+Style.yellow(String.valueOf(player.maxHp))
								+" Current"+Style.blue(" XP ")+Style.yellow(String.valueOf(player.currentXp))+"/"+Style.yellow(String.valueOf(player.xpNeededForLevelUp))
								+" Level:"+Style.yellow(String.valueOf(player.level))+" Floor: "+Style.yellow(String.valueOf(player.floor)));
			System.out.print(">");
			
			input=sc.nextLine();
			for(int x=0;x<30;x++) {
				System.out.println();
			}
			System.out.println("---------------------------------------------------");
			input=input.toLowerCase();
			splitinput=input.split(" ");
			
			
			//this function below is a debug function ised to check the health of an enemy without fighitng it
			if(splitinput[0].equals("debug")) {
				Enemy targetEnemy=player.currentRoom.findEnemyInRoom(splitinput[1]);
				player.checkEnemyHealth(targetEnemy);
			}
			if (splitinput[0].equals("room_enemies")) {
				for(int i=0;i<player.currentRoom.roomEnemies.size();i++) {
					System.out.print(player.currentRoom.roomEnemies.get(i).enemyName);
					System.out.println(" "+player.currentRoom.roomEnemies.get(i).hp);
				}
				
			}
				
			
			
			
			
			
			
			
			
			
			
			
			
			if(splitinput[0].equals("help"))//if help is typed then show all commands
			{
				System.out.println("Commands");
				System.out.println("'take' to take an item");
				System.out.println("'drop' to drop an item");
				System.out.println("'examine' to examine aen item");
				System.out.println("'examine self' to check your health and hunger");
				System.out.println("'look' to look around the room");
				System.out.println("'north/east/south/west' to go in a direction");
				System.out.println("'attack' to attack");
				System.out.println("'use' to use a consumable item");
				System.out.println("'equip' to equip a weapon");
				
				
				
			}
			
			
			
			//handles take item function
			if((splitinput[0].equals("take"))&&(splitinput.length<2)) {//check if there is any item after take
				System.out.println("take what?");//if no noun after verb
				
				
			}
			else if (splitinput[0].equals("take")) {//perform take method
				
				player.takeItem(splitinput[1]);//player that is taking the item and the name of the item he typed after take
			}
			
			
			//handles drop item function
			if((splitinput[0].equals("drop"))&&(splitinput.length<2)) {//check if there is any item after take
				System.out.print("drop what?");//if no noun after verb
				
				
			}
			else if (splitinput[0].equals("drop")) {//perform take method
				
				player.dropItem(splitinput[1]);
			}
				
			
			
		//handles movement function, very messy right now
			
			if(splitinput[0].equals("down")) {
				player.moveDown(floors.get(player.floor-1),floors.get(player.floor));//old floor and new floor
				player.lookAround(floors.get(player.floor-1));
			}
			if(splitinput[0].equals("up")) {
				player.moveUp(floors.get(player.floor-1), floors.get(player.floor-2));
				player.lookAround(floors.get(player.floor-1));
			}
			
			if((splitinput[0].equals("north"))||(splitinput[0].equals("east"))||(splitinput[0].equals("west"))||(splitinput[0].equals("south"))||(splitinput[0].equals("n"))||(splitinput[0].equals("e"))||(splitinput[0].equals("w"))||(splitinput[0].equals("s"))) 
			{
				player.move(splitinput[0],floors.get(player.floor-1));//gives the move function the direction and a list of rooms. 
				//System.out.print();//List of rooms is needed to find out what available paths there are when looking around
				
				player.lookAround(floors.get(player.floor-1));
				//System.out.print();
			}
			
			
				

		//handles display of inventory
			if(splitinput[0].equals("inventory")) {
				player.displayInventory();
			}
			
			
		//handles looking around
			if(splitinput[0].equals("look")) {
				player.lookAround(floors.get(player.floor-1));
			}
			
			if(splitinput[0].equals("examine")&&(splitinput.length<2)) {
				System.out.println("examine what");
				String itemToExamine=sc.nextLine();
				if(itemToExamine.equals("self")) {
					player.selfCheck();
				}
				else {
					player.examine(itemToExamine);
				}
				
				
			}
			else if((splitinput[0].equals("examine"))&&(splitinput[1].equals("self"))){
				player.selfCheck();
			}
			else if(splitinput[0].equals("examine")) {
				player.examine(splitinput[1]);
			}
			
			if(splitinput[0].equals("equip")) {
				if(splitinput.length==1) {
					System.out.println("Equip what?");
					player.equipWeapon(sc.nextLine());
				}
				else {
					player.equipWeapon(splitinput[1]);
				}
				
			}
			
			if(splitinput[0].equals("attack")) {
				if(splitinput.length<2){
					//if they didnt type anything after attack
					System.out.println("Attack what?");
					player.attack(sc.nextLine());
					
				}
				
				else {
					player.attack(splitinput[1]);
				}
				
			}
			if((splitinput[0].equals("consume"))||splitinput[0].equals("use")) {
				if(splitinput.length==1) {
					System.out.println("Consume what?");
					player.consume(sc.nextLine());
				}
				else {
					player.consume(splitinput[1]);
				}
				
				
			}
			
			
		}
	
	}
	
	
}





