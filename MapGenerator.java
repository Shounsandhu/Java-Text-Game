import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//this class randomly generates coordinates for room objects and places them together, I may put random spawners for items and enemies in here as well
public class MapGenerator 
{
	int difficulty;
	
	public MapGenerator(int difficulty) {
		this.difficulty=difficulty;
	}
	//room can be assumed to be a vertex
	public ArrayList<Room> generateFloor(List <Enemy> enemies, List<Item>items,int numberOfRooms,int maxEnemiesPerRoom,int maxItemsPerRoom,int floor) {
		
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		
		for(int i=0;i<numberOfRooms;i++) {//creates set of rooms
			Room room=new Room();//strings here are empty because next function randomly fills them in
			
			
			rooms.add(room);
		}

		if(floor!=1) {
			rooms.get(0).hasLadder=true;
		}
		generateHatch(rooms,floor);
		AssignRoomCoordinates(rooms);//generates coordinates
		spawnItems(rooms,items);//spawns items
		spawnEnemies(rooms,enemies,maxEnemiesPerRoom);//spawns enemies
		generateRoomDetails(rooms);//generates flavor text
		return rooms;
		
		
	}
	public void generateHatch(List<Room>rooms,int floor) {
		Room chosenRoom=rooms.get((int)Math.floor(Math.random()*rooms.size()));//chooses which room to give a hatch too that leads to the next floor
		switch(floor) {
		case 1:
			Container.hatchRoom1=chosenRoom;
			break;
		case 2:
			Container.hatchRoom2=chosenRoom;
			break;
		}
		
		
		chosenRoom.hasHatch=true;
	}
	
	
	
	
	
	
	
	
	public ArrayList<int[]> generateRoomCoordinates(int loops) {//
		int[] origin= {0,0};
		ArrayList<int[]> roomCoords = new ArrayList<int[]>();
		roomCoords.add(origin);
		int side;
		int roomIndex;
		for(int i=0;i<loops;i++) {
			
			side = (int)Math.floor(Math.random()*(4));//generates 0,1,2 or 3 to represent the 4 different sides
			//int roomIndex=(int)Math.floor(Math.random()*roomCoords.size());//choose a random index from the list of rooms
			//roomIndex=(int)Math.floor(Math.random()*(roomCoords.size()));
			
			roomIndex=roomCoords.size()-1;
			while(checkIfSurrounded(roomCoords,roomCoords.get(roomIndex))){
				i=(int)Math.floor(Math.random()*roomCoords.size());//if the current room is surrounded choose anotheer room
				roomIndex=i;
			}
			switch(side) {
				case(0)://north
					int []relNorth= {roomCoords.get(roomIndex)[0],roomCoords.get(roomIndex)[1]+1};
					if(isInside(roomCoords,relNorth)) {
						i--;
						
						break;
					}
					roomCoords.add(relNorth);
					
					break;
				
				case(1)://east
					int []relEast= {roomCoords.get(roomIndex)[0]+1,roomCoords.get(roomIndex)[1]};
					if(isInside(roomCoords,relEast)) {
						i--;
						
						break;
					}
					roomCoords.add(relEast);
					
					break;
				
				case(2)://south
					int []relSouth= {roomCoords.get(roomIndex)[0],roomCoords.get(roomIndex)[1]-1};
					if(isInside(roomCoords,relSouth)) {
						i--;
						
						break;
					}
					roomCoords.add(relSouth);
					
					break;
				
				case(3)://west
					int []relWest= {roomCoords.get(roomIndex)[0]-1,roomCoords.get(roomIndex)[1]};
					if(isInside(roomCoords,relWest)) {
						i--;
						
						break;
					}
					roomCoords.add(relWest);
					
					break;
					
				
				
			}
		}
		
		
		
		return roomCoords;
	}
	public boolean checkIfSurrounded(List <int[]> roomCoordArray,int[] roomCoord) {//this function is horribly designed but im not good enough with java to make it nicer 
		int [] newRoomCoord=roomCoord.clone(); //used to store coordinates of surroudning rooms of target room
		
		newRoomCoord[0]++;
		if((isInside(roomCoordArray,newRoomCoord))) {
			newRoomCoord[0]-=2;
			if(isInside(roomCoordArray,newRoomCoord)) {
				newRoomCoord[0]++;
				newRoomCoord[1]++;
				if(isInside(roomCoordArray,newRoomCoord)) {
					newRoomCoord[1]-=2;
					if(isInside(roomCoordArray,newRoomCoord)) {
						return true;
					}
				}
			}
			
		}
		return false;
	}
	public boolean isInside(List<int[]> coordArr,int[] coord) {// this function is used in room generation to check if the new generated coordinate already exists
		//checks if a given array is in a given array list
		for(int i=0;i<coordArr.size();i++) {
			if(Arrays.equals(coordArr.get(i), coord)) {
				return true;//return true is there is a match
			}
		}
		return false;
	}
	public void AssignRoomCoordinates(List<Room>rooms) {
		ArrayList<int[]> rc;
		rc=generateRoomCoordinates(rooms.size()-1);
		
		for(int i=0;i<rooms.size();i++) {
			rooms.get(i).roomcoordinates=rc.get(i);
			
			
			
		}
		
//		player.currentRoom=spawnRoom;
//		player.lookAround(rooms);
	}
	public void spawnItems(List<Room> rooms,List<Item> items) {

		Item itemToAdd;
		int sample=(int)Math.floor(Math.random()*100);
		
		for(int i=0;i<rooms.size();i++) {
			
			sample=(int)Math.floor(Math.random()*100);
			
			
			
			for(int x=0;x<items.size();x++) {
				itemToAdd=items.get(x);
				
				
				
				
				if(sample<itemToAdd.spawnChance) {//if the random number generated is smaller than spawn chance then spawn the item
					rooms.get(i).addItem(itemToAdd);
					
				}
				else {
					System.out.println();
				}
				System.out.println();
				
				
			}
			
			
		}
	}
	public void removeAllItemsFromWorld(List<Room> rooms) {
		for(int i=0;i<rooms.size();i++) {
			rooms.get(i).roomItems.clear();//removed items from each room
		}
	}
	
	
	
	public void spawnEnemies(List<Room>rooms,List<Enemy>enemies, int maxNumberOfEnemiesToSpawn) {
		int enemyIndex;
		int numberOfEnemiesToSpawn;
		
		
		for(int i=0;i<rooms.size();i++) {
			numberOfEnemiesToSpawn=(int)Math.floor(Math.random()*(maxNumberOfEnemiesToSpawn+1));
			for(int x=0;x<numberOfEnemiesToSpawn;x++) {
				enemyIndex=(int)Math.floor(Math.random()*enemies.size());
				
				
				rooms.get(i).addEnemy(enemies.get(enemyIndex));
				enemies.get(enemyIndex).spawnRoom=rooms.get(i);
				
				
			}
			
		}
	}
	public void removeEnemiesFromWorld(List<Room>rooms) {
		for(int i=0;i<rooms.size();i++) {
			rooms.get(i).roomEnemies.clear();//removed items from each room
		}
	
	}
	public void generateRoomDetails(List <Room>rooms) {
		String adjective;
		String roomName;
		String description;
		String roomDetails;
		String [] adjectives= {"arid","candle-lit","cool","cramped","dripping","dusty","frigid","high","humid","long","low","narrow","phosphorescent","pitch-black","shadowy","sunlit","sweltering","warm","water-filled","vast"};
		String [] roomNames= {" burrow"," catacomb"," cell"," chamber"," chamber"," compartment"," crypt"," grave"," grotto"," hall"," hole"," mausoleum"," mine"," room"," sepulcher"," storeroom"," tomb"," tunnel"," vault"," warren"};
		String [] descriptions= {" adorned with crude paintings"," bearing animal tracks"," caked with mud"," choked with debris"," covered in cobwebs"," crawling with insects"," filled with flapping bats"," in which a creature sleeps"," in which echoes sound"," in which you hear breathing"," inscribed with runes"," littered with bones"," overgrown with fungus"," pulsing with arcane energy"," smelling of dung"," smelling of incense"," smelling of perfume"," swarming with serpents"," that has been ransacked"," that was used for a ritual"};
		for(int i=0;i<rooms.size();i++) {
			adjective=adjectives[(int)(Math.floor(Math.random()*adjectives.length))];
			roomName=roomNames[(int)(Math.floor(Math.random()*(roomNames.length)))];
			description=descriptions[(int)(Math.floor(Math.random()*(descriptions.length)))];//assigns adjective, roomname and description randomly from respective lists
			roomDetails="You enter a " + adjective+roomName+description;
			
			rooms.get(i).flavourText=roomDetails;
			rooms.get(i).roomName=roomName;
		}
		
		
		
	
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	

