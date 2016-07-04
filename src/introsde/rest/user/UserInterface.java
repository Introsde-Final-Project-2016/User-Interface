package introsde.rest.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.glassfish.jersey.client.ClientConfig;

import org.json.*;

public class UserInterface {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		Scanner input = new Scanner(System.in);
		int choice = -1;

		System.out.println("WELCOME TO HEALTH APP!\n");

		while (choice != 0) {
			System.out.println("HEALTH MENU'\n");
			System.out.println("0 - Close the APP");
			System.out.println("1 - Print All The Current Goals ");
			System.out.println("2 - Print All The Current Life Status ");
			System.out.println("3 - Print My User Details ");
			System.out.println("4 - Update Goal ");
			System.out.println("5 - Update Life Status ");
			System.out.println("6 - Did You Reach Your Goal?");
			System.out.print("\nHow do you want to proceed?\n");
			choice = Integer.parseInt(input.nextLine());

			if (choice < 0 || choice > 6) {
				System.out.print("\nPlease enter a number between 0 and 6!\n\n");
			}

			switch (choice) {
				case 0:
					System.out.println("\nHope to see you soon!");
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
                	System.out.println("Which goal do you want to update?");
                	UserInterface.getGoals();
                	System.out.println("Please Enter the id of the Goal");
                	String goalId = input.nextLine(); 	
                	System.out.println("Insert new goal name: ");
		  			String goalname = input.nextLine();
                	System.out.println("Insert new goal value: ");
		  			String goalvalue = input.nextLine();
		  			UserInterface.updateGoal(goalId, goalvalue, goalname);
		  			break;
			
                case 5:
                	System.out.println("Which LS do you want to update?");
                	UserInterface.getLifeStatus();
                	System.out.println("Please Enter the id of the LS");
                	String LsId = input.nextLine(); 	
                	System.out.println("Insert new LS name: ");
		  			String measureName = input.nextLine();
                	System.out.println("Insert new LS value: ");
		  			String measureValue = input.nextLine();
		  			UserInterface.updateLS(LsId, measureName, measureValue);
		  			break;
		  			
                case 6:
                	System.out.println("Did you reach any of your goal?");
                	UserInterface.getGoals();
                	System.out.println("Please Enter the id of the Goal");
                	String goalId2 = input.nextLine(); 	
                	System.out.println("How much are you done? (kg/hours/km");
		  			String achievement = input.nextLine();
		  			UserInterface.checkGoal(goalId2, achievement);                	
                	break;
			
			
			
			}// End of switch
			    	}// End of while
		
			 input.close();
			 }// End of main
	
	public static void getLifeStatus() throws IOException
	{
		String ENDPOINT2 = "https://immense-mountain-93541.herokuapp.com/introsde/user/getLifeStatus";
        DefaultHttpClient client2 = new DefaultHttpClient();
        HttpGet request2 = new HttpGet(ENDPOINT2);
        HttpResponse response2 = client2.execute(request2);
        BufferedReader rd2 = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
        StringBuffer result2 = new StringBuffer();
        String line2 = "";
        while ((line2 = rd2.readLine()) != null) {
            result2.append(line2);
        }
        JSONArray o2 = new JSONArray(result2.toString());
        if (response2.getStatusLine().getStatusCode() == 200) {
            System.out.println("***********************************************");
            System.out.println("USER'S GOALS");
            System.out.println("***********************************************");
            for(int i = 0; i < o2.length(); i++){
            	System.out.println("idmeasure: "+o2.getJSONObject(i).getInt("idMeasure"));
                System.out.println("measureName: "+o2.getJSONObject(i).getString("measureName"));
                System.out.println("value: "+o2.getJSONObject(i).getString("value"));
                System.out.println("");
            }
        }		
	}// End of Get LS
	
	public static String getGoals() throws IOException
	{
    	String ENDPOINT = "https://immense-mountain-93541.herokuapp.com/introsde/user/getGoals";

        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(ENDPOINT);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        JSONArray o = new JSONArray(result.toString());
        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println("***********************************************");
            System.out.println("USER'S GOALS");
            System.out.println("***********************************************");
            for(int i = 0; i < o.length(); i++){
                System.out.println("idGoal: "+o.getJSONObject(i).getInt("idGoal"));
                System.out.println("goalName: "+o.getJSONObject(i).getString("goalName"));
                System.out.println("measureType: "+o.getJSONObject(i).getString("measureType"));
                System.out.println("goalValue: "+o.getJSONObject(i).getString("goalValue"));
                System.out.println("");
            }
        }
		return result.toString();
		
	}// End of Get Goals
	
	public static void getUserDetails() throws IOException
	{
		String ENDPOINT3 = "https://immense-mountain-93541.herokuapp.com/introsde/user/getDetail";

        DefaultHttpClient client3 = new DefaultHttpClient();
        HttpGet request3 = new HttpGet(ENDPOINT3);
        HttpResponse response3 = client3.execute(request3);
        BufferedReader rd3 = new BufferedReader(new InputStreamReader(response3.getEntity().getContent()));
        StringBuffer result3 = new StringBuffer();
        String line3 = "";
        while ((line3 = rd3.readLine()) != null) {
            result3.append(line3);
        }
        JSONObject o3 = new JSONObject(result3.toString());
        if (response3.getStatusLine().getStatusCode() == 200) {
            System.out.println("***********************************************");
            System.out.println("USER'S GOALS");
            System.out.println("***********************************************");
            
                System.out.println("MeasureNames: "+o3.getInt("age"));
                System.out.println("MeasureNames: "+o3.getString("gender"));
                System.out.println("MeasureNames: "+o3.getString("email"));
                System.out.println("MeasureNames: "+o3.getString("name"));
                System.out.println("MeasureNames: "+o3.getString("lastname"));                            
                System.out.println("");
            
        }
	
		
	}// End of User Details
	
	
	public static void updateGoal(String goalid, String value, String name) throws IOException
	{
	String ENDPOINT = "https://secure-refuge-96261.herokuapp.com/introsde/user/updategoal/"+goalid;
	ClientConfig clientConfig4 = new ClientConfig();
	Client client4 = ClientBuilder.newClient(clientConfig4);
	WebTarget service4 = client4.target(ENDPOINT);
	Response res4 = null;
	String putResp4 = null;
	String updateGoal ="{" + "\"goalValue\": " + value + "," + "\"goalName\": \"" + name + "\"" + "}";
	
	res4 = service4.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(updateGoal));
	putResp4 = res4.readEntity(String.class);
	System.out.println(updateGoal);

	if(res4.getStatus() != 200 ){
		System.out.println("ERROR while updating! Please, try again!");
	}else{
		System.out.println("Goal updated successfully!");
	}
}// End of Goal Update

	public static void updateLS(String LsId, String LsName, String LsValue) throws IOException
	{
	String ENDPOINT5 = "https://secure-refuge-96261.herokuapp.com/introsde/user/updateLs/"+LsId;
	ClientConfig clientConfig5 = new ClientConfig();
	Client client5 = ClientBuilder.newClient(clientConfig5);
	WebTarget service5 = client5.target(ENDPOINT5);
	Response res5 = null;
	String putResp5 = null;
	String updateLStatus ="{" + "\"value\": " + LsValue + "," + "\"measureName\": \"" + LsName + "\"" + "}";	

	res5 = service5.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(updateLStatus));
	putResp5 = res5.readEntity(String.class);
	System.out.println(updateLStatus);


	if(res5.getStatus() != 200 ){
		System.out.println("ERROR while updating!"+res5.getStatus());
	}else{
		System.out.println("Goal updated successfully!");
		System.out.println("The system automatically put a new goal for you check the first goal!!");
	}//End of else
}// End of updateLS
	
	public static void checkGoal(String goalid, String value) throws IOException
	{
		String ENDPOINT = "https://immense-mountain-93541.herokuapp.com/introsde/user/getQuote";

        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(ENDPOINT);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        
        JSONObject o2 = new JSONObject(result.toString());
        String message = o2.getString("quote");
        String author = o2.getString("author");
        String glist= UserInterface.getGoals();
        JSONArray o = new JSONArray(glist);
        String goalValue = o.getJSONObject(Integer.parseInt(goalid)-1).getString("goalValue");
        if(Integer.parseInt(value)>=Integer.parseInt(goalValue))
        {
        	 System.out.println("CONGRATULATIONS YOU DID IT");
        }
        else{        
        System.out.println("YOU CAN DO IT");
        System.out.println("YOUR MOTIVAVION MESSAGE IS" + message +" by author:" +author);
        System.out.println((Integer.parseInt(goalValue)-Integer.parseInt(value))+o.getJSONObject(Integer.parseInt(goalid)-1).getString("measureType")+"LEFT" );
        }
}// End of Check goal
	
	
	
	
}//End of class

