package introsde.rest.user;

import java.io.IOException;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.ClientProtocolException;
import org.glassfish.jersey.client.ClientConfig;
import org.json.*;

public class UserInterface {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		Scanner input = new Scanner(System.in);
		int choice = -1;

		System.out.println("WELCOME TO HEALTH APP!\n");

		while (choice != 0) {
			System.out.println("HEALTH MENU\n");
			System.out.println("0 - Close the APP");
			System.out.println("1 - Print All The Current Goals ");
			System.out.println("2 - Print All The Current Life Status ");
			System.out.println("3 - Print My User Details ");
			System.out.println("4 - Update Goal ");
			System.out.println("5 - Update Life Status ");
			System.out.println("6 - Did You Reach Your Goal?");
			System.out.print("\nPlease chose one of the options above to continue?\n");
			choice = Integer.parseInt(input.nextLine());

			if (choice < 0 || choice > 6) {
				System.out.print("\nPlease enter a number between 0 and 6!\n\n");
			}

			switch (choice) {
				case 0:
					System.out.println("\nSee you soon! Do not forget to enter you daily goal results!");
					break;
				
                case 1:
                	UserInterface.getGoals();
                    break;
			     		
                case 2:
                	UserInterface.getLifeStatus();
                	break;
			
                case 3:
                	UserInterface.getUserDetails();
                	break;

                case 4:
                	UserInterface.getGoals();
                	System.out.println("\nWhich goal do you want to update?");
                	System.out.println("\nPlease enter the ID of your goal listed above!");
                	String goalId = input.nextLine(); 	
                	System.out.println("Insert the name of your new goal : ");
		  			String goalname = input.nextLine();
                	System.out.println("Insert the value of your new goal : ");
		  			String goalvalue = input.nextLine();
		  			UserInterface.updateGoal(goalId, goalvalue, goalname);
		  			break;
			
                case 5:
                	UserInterface.getLifeStatus();
                	System.out.println("\nWhich life status do you want to update?");
                	System.out.println("\nPlease enter the ID of your life status listed above!");
                	String LsId = input.nextLine(); 	
                	System.out.println("Insert the name of your new life status : ");
		  			String measureName = input.nextLine();
                	System.out.println("Insert the value of your new life status: ");
		  			String measureValue = input.nextLine();
		  			UserInterface.updateLS(LsId, measureName, measureValue);
		  			break;
		  			
                case 6:
                	System.out.println("Did you reach any of your goal today? Check them now!");
                	String goals = UserInterface.getGoals();
                	System.out.println("Please enter the ID of your goal listed above!");
                	String goalId2 = input.nextLine(); 	
                	JSONArray o = new JSONArray(goals);
                	String measure = o.getJSONObject(Integer.parseInt(goalId2)-1).getString("measureType");
                	String type = o.getJSONObject(Integer.parseInt(goalId2)-1).getString("goalName");
                	System.out.println("Please enter how many " +measure+ " did you " + type + " today?");
		  			String achievement = input.nextLine();
		  			UserInterface.checkGoal(goalId2, achievement);                	
                	break;
			
						}// End of switch
			    }// End of while
		
			 input.close();
		}// End of main
	
	// take life status and print
	public static void getLifeStatus() throws IOException
		{
		String ENDPOINT = "https://immense-mountain-93541.herokuapp.com/introsde/user/getLifeStatus";
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(ENDPOINT);
		Response response = service.request().accept(MediaType.APPLICATION_JSON).get();
		String body = response.readEntity(String.class);
		JSONArray o = new JSONArray(body);
			if(response.getStatus() == 200 ){
            System.out.println("-----------------------------------------------");
            System.out.println("YOUR CURRENT LIFE STATUS");
            System.out.println("-----------------------------------------------");
            for(int i = 0; i < o.length(); i++){
            	System.out.println("ID: " + o.getJSONObject(i).getInt("idMeasure"));
            	System.out.println("Measure Name: " + o.getJSONObject(i).getString("measureName"));
            	System.out.println("Measure Value: " + o.getJSONObject(i).getString("value") + "\n");
            	}
        	}		
		}// End of Get LS
	
	// take goals and print
	public static String getGoals() throws IOException
		{
    	String ENDPOINT = "https://immense-mountain-93541.herokuapp.com/introsde/user/getGoals";
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(ENDPOINT);
		Response response = service.request().accept(MediaType.APPLICATION_JSON).get();
		String body = response.readEntity(String.class);
		JSONArray o = new JSONArray(body);
			if(response.getStatus() == 200 ){
        	System.out.println("-----------------------------------------------");
            System.out.println("YOUR CURRENT GOALS");
            System.out.println("-----------------------------------------------");
            for(int i = 0; i < o.length(); i++){
                System.out.println("ID: "+o.getJSONObject(i).getInt("idGoal"));
                System.out.println("Goal Name: " + o.getJSONObject(i).getString("goalName"));
                System.out.println("Goal Value: " + o.getJSONObject(i).getString("goalValue"));
                System.out.println("Measure Type: " + o.getJSONObject(i).getString("measureType") + "\n");
            	}
        	}
		return body;
		}// End of Get Goals
	
	// take user details and print 
	public static void getUserDetails() throws IOException
		{
		String ENDPOINT = "https://immense-mountain-93541.herokuapp.com/introsde/user/getDetail";
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(ENDPOINT);
		Response response = service.request().accept(MediaType.APPLICATION_JSON).get();
		String body = response.readEntity(String.class);
		JSONObject o = new JSONObject(body);
			if(response.getStatus() == 200 ){
        	System.out.println("-----------------------------------------------");
            System.out.println("YOUR USER INFORMATION");
            System.out.println("-----------------------------------------------");
            System.out.println("Your Name: " + o.getString("name"));
            System.out.println("Your Lastname: " + o.getString("lastname"));                            
            System.out.println("Your Age: " + o.getInt("age"));
            System.out.println("Your Gender: " + o.getString("gender"));
            System.out.println("Your Email: " + o.getString("email"));
            System.out.println("");
        	}		
		}// End of User Details
	
	// update goals
	public static void updateGoal(String goalid, String value, String name) throws IOException
	{
	String ENDPOINT = "https://secure-refuge-96261.herokuapp.com/introsde/user/updategoal/"+goalid;
	ClientConfig clientConfig = new ClientConfig();
	Client client = ClientBuilder.newClient(clientConfig);
	WebTarget service = client.target(ENDPOINT);
	Response response = null;
	String putResp = null;
	String updateGoal ="{" + "\"goalValue\": " + value + "," + "\"goalName\": \"" + name + "\"" + "}";
	response = service.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(updateGoal));
	putResp = response.readEntity(String.class);
	System.out.println("\n" + updateGoal);
		if(response.getStatus() != 200 ){
			System.out.println("ERROR while updating goal! Type=" +response.getStatus());
			}
		else{
			System.out.println("Your goal updated successfully! \n");
			}
	}// End of Goal Update

	// update life status
	public static void updateLS(String LsId, String LsName, String LsValue) throws IOException
	{
	String ENDPOINT = "https://secure-refuge-96261.herokuapp.com/introsde/user/updateLs/"+LsId;
	ClientConfig clientConfig = new ClientConfig();
	Client client = ClientBuilder.newClient(clientConfig);
	WebTarget service = client.target(ENDPOINT);
	Response response = null;
	String putResp = null;
	String updateLStatus ="{" + "\"value\": " + LsValue + "," + "\"measureName\": \"" + LsName + "\"" + "}";	
	response = service.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(updateLStatus));
	putResp = response.readEntity(String.class);
	System.out.println(updateLStatus);
		if(response.getStatus() != 200 ){
			System.out.println("ERROR while updating life status! Type="+response.getStatus());
			}
		else{
			System.out.println("Your life status updated successfully!");
			System.out.println("According to your new life status, the system automatically set your new Bmi value!");
			System.out.println("According to your new life status, the system automatically set a new goal for you!");
			System.out.println("Check your new first goal!\n");
			}
	}// End of updateLS
	
	// check if you reached the goal if yes take congrats if no take motivational message
	public static void checkGoal(String goalid, String value) throws IOException
		{
		String ENDPOINT = "https://immense-mountain-93541.herokuapp.com/introsde/user/getQuote";
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(ENDPOINT);
		Response response = service.request().accept(MediaType.APPLICATION_JSON).get();
		String body = response.readEntity(String.class);
		JSONObject ob = new JSONObject(body);
        String message = ob.getString("quote");
        String author = ob.getString("author");
        String glist= UserInterface.getGoals();
        JSONArray o = new JSONArray(glist);
        String goalValue = o.getJSONObject(Integer.parseInt(goalid)-1).getString("goalValue");
        String measureType = o.getJSONObject(Integer.parseInt(goalid)-1).getString("measureType");
        int val1 = Integer.parseInt(value);
        int val2 = Integer.parseInt(goalValue);
        	if(val1 >= val2)
        	{
        	 System.out.println("CONGRATULATIONS YOU HAVE REACHED TO YOUR GOAL!\n");
        	}
        	else{        
        	System.out.println("DONT GIVE UP! YOU CAN DO IT!\n");
        	System.out.println("YOUR MOTIVAVION MESSAGE IS:\n" + message +"\nby author: " +author);
        	System.out.println("JUST " + (val2-val1) + " " + measureType + " LEFT TO REACH YOUR GOAL" );
        	}
		}// End of Check goal
	
}//End of class

