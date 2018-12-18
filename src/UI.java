
public class UI {
	public static String s = "          ";
	public static String left = "  |";
	
	public static void printTitle() {
		/*System.out.println(s + " _____          _         __ _     _   \r\n" + 
				s +"/__   \\___   __| | ___   / /(_)___| |_ \r\n" + 
				s + "  / /\\/ _ \\ / _  |/ _ \\ / / | / __| __|\r\n" + 
				s + " / / | (_) | (_| | (_) / /__| \\__ \\ |_ \r\n" + 
				s + " \\/   \\___/ \\__,_|\\___/\\____/_|___/\\__|\r\n" + 
				"                                       ");*/
		
		System.out.println(s +" _____        ___        __ _     _   \r\n" + 
				s +"/__   \\___   /   \\___   / /(_)___| |_ \r\n" + 
				s +"  / /\\/ _ \\ / /\\ / _ \\ / / | / __| __|\r\n" + 
				s +" / / | (_) / /_// (_) / /__| \\__ \\ |_ \r\n" + 
				s +" \\/   \\___/___,' \\___/\\____/_|___/\\__|\r\n" + 
				"                                      ");
		System.out.println("//-------------------------------------------------------------");
		System.out.println("//-------------------------------------------------------------");
	}
	
	
	
	
	public static void bottom(String num, String abb) {
		System.out.println("  |        |                                               |");
		System.out.println("  |   " + num + "   |  " + abb + "   |");
		System.out.println("  |        |                                               |");
		System.out.println("  ----------------------------------------------------------");
	}
	
	public static void top(String num, String abb) {
		System.out.println("  ----------------------------------------------------------");
		System.out.println("  |        |                                               |");
		System.out.println("  |   " + num + "   |  " + abb + "   |");
		System.out.println("  |        |                                               |");
		System.out.println("  ----------------------------------------------------------");
	}
	
	
	public static void bottomH(String num, String abb, String date) {
		System.out.println("  |        |                                               |                         |");
		System.out.println("  |   " + num + "   |  " + abb + "   |   " + date + "   |");
		System.out.println("  |        |                                               |                         |");
		System.out.println("  ------------------------------------------------------------------------------------");
	}
	
	public static void topH(String num, String abb, String date) {
		System.out.println("  ------------------------------------------------------------------------------------");
		System.out.println("  |        |                                               |                         |");
		System.out.println("  |   " + num + "   |  " + abb + "   |   " + date + "   |");
		System.out.println("  |        |                                               |                         |");
		System.out.println("  ------------------------------------------------------------------------------------");
	}
	
	
	public static void printTop() {
		System.out.println("  |                                                          |");
	}
	
	
	public static void printInstructions() {
		System.out.println("     -----------------------------------------------------");
		System.out.println("     |No for details - A to add - D to delete - E to edit|     ");
		System.out.println("     -----------------------------------------------------");
	}
	
	public static void printAddInstructions() {
		System.out.println("     -----------------------------------------------------");
		System.out.println("     |   Add a Todo by entering its Description first    |     ");
		System.out.println("     |   add a priority to move it up or down the list   |     ");
		System.out.println("     -----------------------------------------------------");
	}
	
	public static void blank() {
		System.out.println("");
	}
	public static void clear() {
		 System.out.print("\033[H\033[2J");
	} 
	public static void printInput() {
		System.out.println("#################################################################");
	}
	
}
