import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static String s = "          ";
	public static String left = "  |";
	public int no = 58;
	
	public static ArrayList<Todo> todoList = new ArrayList<Todo>();
	public static ArrayList<Todo> historyList = new ArrayList<Todo>();
	
	public static void main(String args[]) throws IOException {
		Main val = new Main();
		val.readTodo();
		mainPage();
	}
	
	
	//READING AND WRITING TODO LISTS FOR PERSISTANCE
	
	public static void writeTodo() throws FileNotFoundException, UnsupportedEncodingException {
		
		PrintWriter writer = new PrintWriter("/home/sean/Todo.txt", "UTF-8");
		String content = "";
		for(int i = 0; i < todoList.size(); i++) {
			content = content + todoList.get(i).getPriority() + "~" + todoList.get(i).getDescription() + "`";
		}
		writer.println(content);
		writer.close();
	}
	
	public void readTodo() throws IOException{
		 InputStream is = getClass().getResourceAsStream("./Todo.txt");
		 InputStreamReader isr = new InputStreamReader(is);
		 BufferedReader br = new BufferedReader(isr);
		 String content = br.readLine();
		 br.close();
		 isr.close();
		 is.close();
		 
		 if(!content.equals("")) {
		 String[] fullTodos = content.split("`");
		
		for(int i = 0; i < fullTodos.length; i++) {
			String[] split = fullTodos[i].split("~");
			int pri = Integer.parseInt(split[0]);
			String des = split[1];
			Todo t = new Todo(pri, des);
			todoList.add(t);
		}
		 }
	}
	
	
	// PAGES OF THE PROGRAM
	public static void mainPage() throws FileNotFoundException, UnsupportedEncodingException {
		UI.clear();
		UI.printTitle();
		UI.printInstructions();
		printQuestions(todoList);
		UI.blank();
		UI.printInput();
		Scanner newScan = new Scanner(System.in);
		String input = newScan.nextLine();
		int choice = -1;
		
		
		try {
			choice = Integer.parseInt(input);
			displayTodo(choice);
		} catch(Exception e) {
			if(input.equals("a") || input.equals("A")) {
				addTodo();
			}
			if(input.equals("d") || input.equals("D")){
				System.out.print("Enter a number to Delete: ");
				ArrayList<Todo> deleteTodo = removeTodo();
				todoList = deleteTodo;
				mainPage();
			}
			/*if(input.equals("e") || input.equals("E")){
				editTodo();
				mainPage();
			}*/
			if(input.equals("t") || input.equals("T")) {
				System.out.println("Enter a number to Tick: ");
				Scanner scan = new Scanner(System.in);
				int num = scan.nextInt();
				Todo t = todoList.get(num - 1);
				ArrayList<Todo> list = removeTodoItem(t.getPriority());
				todoList = list;
				completed(t);
				historyList.add(t);
				mainPage();
			}
			if(input.equals("q") || input.equals("Q")) {
				writeTodo();
				System.exit(0);
			}
			if(input.equals("h") || input.equals("H")) {
				displayHistory();
			}
		}
	}
	
	public static void addTodo() throws FileNotFoundException, UnsupportedEncodingException {
		UI.clear();
		UI.printTitle();
		UI.printAddInstructions();
		UI.blank();
		UI.printInput();
		System.out.println("Enter The Todo: ");
		Scanner newScan = new Scanner(System.in);
		String description = newScan.nextLine();
		System.out.println("Enter the priority: ");
		newScan = new Scanner(System.in);
		String str = newScan.nextLine();
		if(str.equals("")) {
			Todo t = new Todo(todoList.size() + 1, description);
			todoList.add(t);
		}else {
			int priority = Integer.parseInt(str);
			Todo t = new Todo(priority, description);
			todoList = addTodoList(todoList, t);
		}
		mainPage();
	}
	
	public static ArrayList<Todo> removeTodo() {
		Scanner scan = new Scanner(System.in);
		int input = scan.nextInt();
		ArrayList<Todo> returnTodo = new ArrayList<Todo>();

		for(int i = 0; i < todoList.size(); i++) {
			if(input == todoList.get(i).getPriority()) {
				i++;
				while(true) {
					if(returnTodo.size() == todoList.size() -1) {
						return returnTodo;
					}
					Todo t = todoList.get(i);
					t.setPriority(todoList.get(i).getPriority() -1);
					returnTodo.add(t);
					i++;
				}
				
				
			}else {
				returnTodo.add(todoList.get(i));
			}
		}
		return returnTodo;
	}
	
	
	/*public static void editTodo(){
		System.out.println("Enter a number to Edit: ");
		Scanner scan = new Scanner(System.in);
		int pri = scan.nextInt();
	
		clear();
		printTitle();
		blank();
		System.out.println("Priority Number: " + todoList.get(pri -1).getPriority());
		System.out.println("____________________");
		blank();
		blank();
		System.out.println(todoList.get(pri - 1).getDescription());
		blank();
		blank();
		
		System.out.print("Enter a new Description: ");
		Scanner newScan = new Scanner(System.in);
		String str = newScan.nextLine();
		if(!str.equals("")) {
			Todo t = todoList.get(pri - 1);
			t.setDescription(str);
			todoList.add(t);
		}
	}*/
	
	public static void displayTodo(int pri) throws FileNotFoundException, UnsupportedEncodingException {
		UI.clear();
		UI.printTitle();
		UI.blank();
		System.out.println("Priority Number: " + todoList.get(pri -1).getPriority());
		System.out.println("____________________");
		UI.blank();
		UI.blank();
		System.out.println(todoList.get(pri - 1).getDescription());
		UI.blank();
		UI.blank();
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		
		if(str.equals("b") || str.equals("B")) {
			mainPage();
		}
		if(str.equals("q") || str.equals("Q")) {
			System.exit(0);
		}
		if(str.equals("t") || str.equals("T")) {
			Todo t = todoList.get(pri - 1);
			ArrayList<Todo> list = removeTodoItem(t.getPriority());
			todoList = list;
			completed(t);
			historyList.add(t);
			mainPage();
		}
	}
	
	
	public static void displayHistory() {
		UI.clear();
		UI.printTitle();
		UI.printInstructions();
		printHistory(historyList);
		UI.blank();
		UI.printInput();
		Scanner newScan = new Scanner(System.in);
		String input = newScan.nextLine();
		int choice = -1;
		//DISPLAYING GOOD, NEED TO ADD USER OPTIONS, CLEAR FUNCTION TO REMOVE ALL HISTORY, CREATE PERSISTENCE FILE FOR LISTS
	}
	
		
	//FUNCTIONS OF THE PROGRAM
	
	public static void completed(Todo t) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateTime = dtf.format(now);
		t.setCompletedDate(dateTime);
	}
	

	public static void printHistory(ArrayList<Todo> t) {
		if(t.size() != 0) {
			UI.topH("01", t.get(0).getAbbr(), t.get(0).getCompletedDate());
		for(int i = 1; i < t.size(); i++) {
			if(i >= 9) {
				UI.bottomH((Integer.toString(i + 1)) , t.get(i).getAbbr(), t.get(i).getCompletedDate());
			}
			else {
				UI.bottomH(("0"+ (Integer.toString(i + 1))), t.get(i).getAbbr(), t.get(i).getCompletedDate());
			}
		}
		}
	}
	
	
	public static void printQuestions(ArrayList<Todo> t) {
		if(t.size() != 0) {
			UI.top(("0" + t.get(0).getPriority()), t.get(0).getAbbr());
		for(int i = 1; i < t.size(); i++) {
			if(i >= 9) {
				UI.bottom((""+ t.get(i).getPriority()), t.get(i).getAbbr());
			}
			else {
				UI.bottom(("0"+ t.get(i).getPriority()), t.get(i).getAbbr());
			}
		}
		}
	}
	
	public static ArrayList<Todo> addTodoList(ArrayList<Todo> startingList, Todo newTodo){
		ArrayList<Todo> returnList = new ArrayList<Todo>();	
		for(int i = 0; i < startingList.size() + 1; i++) {
			if(newTodo.getPriority() == startingList.get(i).getPriority()) { //when it reaches it and prints the rest
				returnList.add(newTodo);
				int num = startingList.size() - returnList.size();
				while(true) {
					int priorityVal = startingList.get(i).getPriority() + 1;
					Todo t = new Todo(priorityVal, startingList.get(i).getDescription());
					returnList.add(t);
					if(i == startingList.size() -1) {
						return returnList;
					}
					i++;
				}
			}
			else {
				returnList.add(startingList.get(i));
			}
		}
		return startingList;
	}
	
	
	public static ArrayList<Todo> removeTodoItem(int pri) {
		ArrayList<Todo> returnTodo = new ArrayList<Todo>();
		
			for(int i = 0; i < todoList.size(); i++) {
				if(pri == todoList.get(i).getPriority()) {
					i++;
					while(true) {
						if(returnTodo.size() == todoList.size() -1) {
							return returnTodo;
						}
						Todo t = todoList.get(i);
						t.setPriority(todoList.get(i).getPriority() -1);
						returnTodo.add(t);
						i++;
					}
					
					
				}else {
					returnTodo.add(todoList.get(i));
				}
			}
			return returnTodo;
	}
	
	
}
