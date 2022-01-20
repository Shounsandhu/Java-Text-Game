
public class Style {
	
	final static String TEXT_RED = "\u001B[31m";
	final static String TEXT_EM="\033[3m";
	final static String TEXT_GREEN = "\u001B[32m"; 
	final static String TEXT_RESET = "\u001B[0m";
	final static String TEXT_YELLOW = "\u001B[33m";
	final static String TEXT_BLUE = "\u001B[34m";
	final static String TEXT_PURPLE = "\u001B[35m";
	final static String TEXT_CYAN = "\u001B[36m";
	
	
	public static String red(String text) {//makes text red                
		return(TEXT_RED+text+TEXT_RESET);
	}
	
	public static String blue(String text) {//makes text blue
		return(TEXT_BLUE+text+TEXT_RESET);
	}
	
	public static String green(String text) {//makes text green
		return(TEXT_GREEN+text+TEXT_RESET);
	}
	
	public static String em(String text) {//makes text italics
		return(TEXT_EM+text+TEXT_RESET);
	}
	
	public static String yellow(String text) {//makes text yellow
		return(TEXT_YELLOW+text+TEXT_RESET);
	}
	
	public static String purple(String text) {//makes text purple
		return(TEXT_PURPLE+text+TEXT_RESET);
	}
	public static String cyan(String text) {//makes text cyan
		return(TEXT_CYAN+text+TEXT_RESET);
	}
	
}
