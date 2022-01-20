import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
public class Container {//define different enemies and items here

	//arrays of these objects
	ArrayList<Item> worldItems1 = new ArrayList<Item>();//list that stores the different items in the first floor
	ArrayList<Enemy> worldEnemies1 = new ArrayList<Enemy>();//list that stores the different enemies in the world of first floor
	
	ArrayList<Item> worldItems2 = new ArrayList<Item>();//list that stores the different items in the second floor
	ArrayList<Enemy> worldEnemies2 = new ArrayList<Enemy>();//list that stores the different enemies in the world of second floor
	
	ArrayList<Item> worldItems3 = new ArrayList<Item>();//list that stores the different items in the third floor
	ArrayList<Enemy> worldEnemies3 = new ArrayList<Enemy>();//list that stores the different enemies in the world of third floor
	
//	ArrayList<Item> worldItems4 = new ArrayList<Item>();//list that stores the different items in the first floor
//	ArrayList<Enemy> worldEnemies4 = new ArrayList<Enemy>();//list that stores the different enemies in the world of first floor
//	
//	ArrayList<Item> worldItems5 = new ArrayList<Item>();//list that stores the different items in the first floor
//	ArrayList<Enemy> worldEnemies5 = new ArrayList<Enemy>();//list that stores the different enemies in the world of first floor
	
	
	
	
	//Consumables
	Consumable Bandage=new Consumable("bandage","consumable",80,"restoreHealth",30);//name,type,weight,type of consumable and mangitude
	Consumable FirstAidKit=new Consumable("first_aid_kit","consumable",30,"restoreHealth",90);
	Consumable MaxHealthPotion=new Consumable("max_health_potion","consumable",30,"increaseMaxHealth",50);
	Consumable FullRestorePotion=new Consumable("full_restore_potion","consumable",25,"fullRestore",0);
	Consumable XpPotion=new Consumable("xp_potion","consumable",40,"increaseXp",30);

	
	Consumable CombatPotion=new Consumable("combat_potion","consumable",3,"increaseDamage",10);
	Consumable Adrenaline=new Consumable("adrenaline","consumable",25,"increaseCritChance",50);
	
	
	
	
	
	//player starting weapon
	Weapon CopperKnife=new Weapon("copper_knife","weapon",0,3,0);//name, type,spawn chance,damage and crit chance
	
	
	
	//weapon for floor 1
	
	
	
	Weapon IronKnife=new Weapon("iron_knife","weapon",15,7,35);
	
	
	
	
	
	//weapons for floor 2
	Weapon Talwar=new Weapon("talwar","weapon",15,30,10);
	
	//weapons for floor 3
	Weapon Blunderbuss=new Weapon("blunderbuss","weapon",7,70,3);
	
	
	
	
	
	
	
	//enemy loot for floor 1
	
	Weapon BoneKnife=new Weapon("bone_knife","weapon",0,7 ,45);//dropped by skeleton
	
	//enemy loot for floor 2
	Weapon RatWhip=new Weapon("rats_tail_whip","weapon",0,15,25);//dropped by giant rat
	Weapon GiantClub=new Weapon("giant_club","weapon",0,40,10);
	Weapon GoblinDagger=new Weapon("goblin_dagger","weapon",0,25,40);
	
	
	
	
	
	
	
	
	
	
	
	//enemies for floor 1

	Enemy Slime=new Enemy(5, 3,null,5,"slime");//hp atk loot xp and name
	
	
	//enemies for 1 and 2
	Enemy Skeleton=new Enemy(15, 5,BoneKnife,5,"skeleton");
	
	
	
	
	
	//enemies for floor 2 
	Enemy Rat=new Enemy(20, 10,RatWhip,10,"giant_rat");
	Enemy SkeletonArcher=new Enemy(20,15,null,20,"skeleton_archer");
	
	Enemy SkeletonBrute=new Enemy(50, 25,GiantClub,60,"skeleton_brute");
	Enemy Goblin=new Enemy(20,20,GoblinDagger,20,"goblin");
	
	
	
	
	
	public void initEnemies1() {//add enemies and items for floor 1 here
		worldEnemies1.addAll(Arrays.asList(Slime,Skeleton));
	}
	public void initItems1() {
		worldItems1.addAll(Arrays.asList(IronKnife,Bandage,FullRestorePotion,XpPotion));
		
	}
	
	
	
	public void initEnemies2() {
		worldEnemies2.addAll(Arrays.asList(SkeletonArcher,Skeleton,Rat,SkeletonBrute,Goblin));
	}
	public void initItems2() {
		worldItems2.addAll(Arrays.asList(FirstAidKit,Bandage,MaxHealthPotion,Talwar,Blunderbuss,FullRestorePotion,XpPotion));
	}
	
	
	
//	public void initEnemies3() {
//		worldEnemies3.addAll(Arrays.asList(SkeletonBrute,SkeletonArcher,Skeleton));
//	}
//	public void initItems3() {
//		worldItems3.addAll(Arrays.asList(BoneKnife,Bandage,MaxHealthPotion,Blunderbuss));
//	}
	
	
	
//	public void initEnemies4() {
//		worldEnemies4.addAll(Arrays.asList(Slime,Skeleton,Rat,PunchingBag));
//	}
//	public void initItems4() {
//		worldItems4.addAll(Arrays.asList(ZombieArm,Skull,CopperSword,RatWhip,Bandage,MaxHealthPotion,Feather));
//	}
//	
//	
//	
//	public void initEnemies5() {
//		worldEnemies5.addAll(Arrays.asList(Slime,Skeleton,Rat,PunchingBag));
//	}
//	public void initItems5() {
//		worldItems5.addAll(Arrays.asList(ZombieArm,Skull,CopperSword,RatWhip,Bandage,MaxHealthPotion,Feather));
//	}
	
	
	
	
	
	public static Room hatchRoom1;
	public static Room hatchRoom2;
	
	
	
	
	
	
	
	
	
	
	
}
