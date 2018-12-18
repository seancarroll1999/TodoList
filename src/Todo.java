import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Todo {
	public String description = "";
	public String abbr = "";
	public int priority = 0;
	String createdDate = "";
	String completedDate = "";
	
	public Todo(int priorityVal, String descriptionVal){
		priority = priorityVal;
		description = descriptionVal;
		abbr = generateAbbr(description);
		createdDate = setDateTime();
	}
	
	
	public String getDescription() {
		return description;
	}
	public String getAbbr() {
		return abbr;
	}
	public int getPriority() {
		return priority;
	}
	
	public void setDescription(String str) {
		description = str;
	}
	public void setAbbr(String str) {
		abbr = str;
	}
	public void setPriority(int num) {
		priority = num;
	}
	
	public String generateAbbr(String str) {
		int characters = 41;
		String[] words = str.split(" ");
		String abb = "";
		String check = "";
		int count = 0;
		
		if(str.length() == 42) {
			return str;
		}
		
		if(str.length() < 42) {
			int num = 42 - str.length();
			String space = "";
			
			for(int i = 0; i < num; i++) {
				space = space + " ";
			}
			
			return str + space;
		}else {
			while(true) {
				check = words[count];
				if(check.length() + abb.length() + 1 >= 39) {
					abb = abb + "...  ";	
					return abb;
				}else {
					if(count == 0) {
						abb = check;
					}else {
						abb = abb + " " + check;
					}		
				}
				count++;
			}
		}
	}
	
	public String setDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateTime = dtf.format(now);
		return dateTime;
	}
	
	public String getCreatedDateTime() {
		return createdDate;
	}
	public String getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(String dateTime) {
		completedDate = dateTime;
	}
	
	
}
