
public class FlavourText {//contains various flavourText to be displayed during certain events e.g first kill, first death, first spawn etc
	
	final static String spawn="You wake up with your head throbbing \n"+
							Style.cyan(Style.em("where am I? What is this place?"))+
							"\nYou think to yourself. You find yourself surrounded by mossy bricks, a pungent smell wafting through the air\n"+
							"You find a chipped copper knife by your side along with a bandage\n"+
							Style.cyan(Style.em("I should look around, try to get an idea of where I am\n"))+
							"Your uncertainty echoes of the walls as you pick up the knife"
							;


	final static String firstMonsterEncounter=Style.em(Style.cyan("What is this thing, it looks terrifying\n"
									+ "while it is cautious of me, it doesn't seem like it'll attack unprovoked."));
	
	
	final static String itemPickUp1=Style.em(Style.cyan("This should be useful")); 
	final static String itemPickUp2=Style.em(Style.cyan("This should help"));
	
	final static String consumablePickUp1=Style.em(Style.cyan("This could be helpful in a pinch"));
	
	final static String weaponPickUp1=Style.em(Style.cyan("Something to defend myself with"));

	
	


}


