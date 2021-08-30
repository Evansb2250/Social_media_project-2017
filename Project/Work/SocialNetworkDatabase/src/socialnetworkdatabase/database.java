/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetworkdatabase;

import userProfile.userProfile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author samue
 */
public class database {
    
        ArrayList friends = new ArrayList<>();
        FileReader loadFriendFile;
    
        static Vector<userProfile> activeUsers = new Vector<userProfile>();
        ArrayList<userProfile> users = new ArrayList<>();
        FileReader loadUserFile;
        Socket databaseSocket;
         
    public database(){
        //reading user table into array
        try{
  
            loadUserFile = new FileReader("C:\\Users\\James\\Documents\\System-Software\\Project\\SocialNetworkDatabase\\src\\UsersTable.txt");
            BufferedReader br = new BufferedReader(loadUserFile);
       
            String lines;
            while((lines = br.readLine()) != null){
                users.add(loadUsers(lines));
            }
            br.close();
            loadUserFile.close();
        }catch(IOException e){  System.out.println("Error reading file! "+ e.getMessage());}
        
       
        //loading friends table.
        /*try{
            loadFriendFile = new FileReader("C:\\Users\\James\\Desktop\\Project\\SocialNetworkDatabase\\friendsTable.txt");
            BufferedReader br = new BufferedReader(loadFriendFile);
            
            String lines;
            while((lines = br.readLine()) != null){
                friends.add(lines);
            }
            
            loadFriendFile.close();
            br.close();
            
            
        }catch(IOException e){
            System.out.println("Error reading file! "+ e.getMessage());
        } 
        */
       
}
    
    
    
 /*   
    public void reloadFriendFile(){
                try{
            loadFriendFile = new FileReader("friendsTable.txt");
            BufferedReader br = new BufferedReader(loadFriendFile);
            
            String lines;
            while((lines = br.readLine()) != null){
                friends.add(loadFriends(lines));
            }
            
            loadFriendFile.close();
            br.close();
        }catch(IOException e){
            System.out.println("Error reading file! "+ e.getMessage());
        }
    }
    
*/
    
    /*userFriends loadFriends(String line){

        userFriends existingFriends = new userFriends(line);
        
        return existingFriends;
    }*/
   
   
   
      
     userProfile loadUsers(String line){
        // NOT REAL USER
        userProfile existingUsers = new userProfile(line);
        
       //Read user file
       //input each line in
      //  userProfile existingUsers = new userProfile(String LINE);
        
        
        return existingUsers;
    }
     
    
     
     
    
    public void registerUser(String data){
        
          userProfile tempUser = new userProfile(data);
          
          if(userExist(tempUser.getUsername()) == true){
              System.out.println("User already exists!");
          }
          else
          {
              System.out.println("User is valid.");
              //Write a new user to the file
              users.add(tempUser);
              insertUser(data);
          }
              
      }
    
    
    public void insertUser(String data){
        try(FileWriter fw = new FileWriter("C:\\Users\\James\\Documents\\System-Software\\Project\\SocialNetworkDatabase\\src\\UsersTable.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
       
            
        {
            out.println(data);  
          System.out.println(data);
            out.close();
        } catch (IOException e) {
             System.out.println("Error writing to users table: " + e.getMessage());
        }
    }
    
    
    
    boolean userExist(String username){
        //checks the existing object
       
       for(int i =0; i< users.size(); i++){
           System.out.println("userName " + users.get(i).getUsername());
            System.out.println("password "+ users.get(i).getPassword());
           
           if(users.get(i).getUsername().equals(username)){
               return true;
           }
           
       }
       return false;
    }
    

    
    //Write a user object 
    userProfile login(String username, String password){
        
        
        ////My Version
        
        userProfile tempUser = new userProfile();
        tempUser.setUsername("invalid");
        
        for(int i =0; i< users.size(); i++){
         
           if(users.get(i).getUsername().equals(username)){
            
          
               if(users.get(i).getPassword().equals(password)){
                   return users.get(i);
               }else
               {
                   return tempUser;
                           }
           }
           
    }
         return tempUser; 
    }
    
    
    /**
     * @param args the command line arguments
     */

        // TODO code application logic here
        
        





public void getUsers(){
    for(int i = 0; i < users.size(); i++){
        System.out.println(users.get(i).getUsername());
    }
    
}

// This method checks to see if request already exist 
boolean nameAlreadyInRequest(userProfile account, String nameToCheck){
    boolean requestExist = false;
    
    for(userProfile requestFrom: account.recieveRequests){
        if(requestFrom.getUsername().equalsIgnoreCase(nameToCheck)){
            requestExist = true;
            break;
        }
    }
    
    return requestExist;
}


public userProfile sendRequest(userProfile currentUser, String otherUser){
    int i = 0;
    for (; i<users.size(); i++){
        if(users.get(i).getUsername().equals(otherUser)){
            //Name of person to be friends with and the name of the account that sent it
            if(nameAlreadyInRequest(users.get(i), currentUser.getUsername())== false)
              
            users.get(i).addToRequest(currentUser);
            break;
        }
    }
    
    return(users.get(i));
    
}



public userProfile removeRequest(userProfile currentUser, String name ){
    System.out.println("length: " + currentUser.recieveRequests.size());
    for (int i = 0; i< currentUser.recieveRequests.size();i++){
        System.out.println("name trying to remove: " + name);
        System.out.println("name checking against: " + currentUser.recieveRequests.get(i).getUsername());
        if(currentUser.recieveRequests.get(i).getUsername().equals(name)){
            System.out.println("removing " + currentUser.recieveRequests.get(i).getUsername() + "because matches " + name);
            currentUser.removeRequest(i);
        }
        else{
            System.out.println("didn't find the user");
        }
    }
    
    System.out.println("sending back " + currentUser.getUsername() + " ppl in the rejected list " + currentUser.recieveRequests.size());
    return(currentUser);
}


public void addFriend(userProfile currentUser, String newFriend){
    for (int i = 0; i<users.size(); i++){
        if(users.get(i).getUsername().equals(newFriend)){
            currentUser.friends.add(users.get(i));
            users.get(i).friends.add(currentUser);
            //writeToFriends(currentUser.getUsername() + " " + users.get(i).getUsername());
        }
    }
    for(int i = 0; i<users.size(); i++){
        if(users.get(i).getUsername().equals(currentUser.getUsername())){
            users.get(i).friends = currentUser.friends;
        }
    }
}

public void writeToFriends(String data){
    try(FileWriter fw = new FileWriter("friendsTable.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(data);  
        } catch (IOException e) {
             System.out.println("Error writing to users table: " + e.getMessage());
        }
}


        //Check to see if Friend file exist
            //if it does load the friend file
            //call loadUsers
         /*
            read file
             for each line
             vector<userProfile> users.add(loadUsers(LINE));
            BREAK WHEN DONE
            
            
            */ 
/* 

//updates the friends array for certain username, from the friends table.
public void updateFriends(String name){
    for(int i =0; i< users.size(); i++){
           
           if(users.get(i).getUsername().equals(name)){
               users.get(i).setFriends(getFriendsOf(name));
           }
       }
    
}

//updates every user class friends array, from the friends table.
public void updateAllFriends(){
    for(int i = 0; i< users.size(); i++){
        updateFriends(users.get(i).getUsername());
    }
}

//Outputs friends of a specific user.
public void outputFriends(String username){
    
    for(int i =0; i< users.size(); i++){
           
           if(users.get(i).getUsername().equals(username)){
               ArrayList friendsT = new ArrayList();
               friendsT = users.get(i).getFriends();
               for(int j = 0; j<friendsT.size();j++){
                   System.out.println(friendsT.get(j));
               }
           }
           
       }
}

*/


/*
//beginning of friend implementation
//Just outputs the friend table
public void getFriendsTable(){
    for(int i = 0; i<friends.size(); i++){
        System.out.println(friends.get(i).getUserName() + " - " + friends.get(i).getFriend());
    }
} 


//Will insert a new friedship if not already friends
public void insertFriends(String data){
    
    userFriends tempFriends = new userFriends(data);
    
    if(alreadyFriends(tempFriends.getUserName(), tempFriends.getFriend()) == true){
        System.out.println("Users are already friends!");
    }
    else{
        System.out.println("valid friend request!");
        //write to text file
        writeToFriends(data);
        
    }
    
}


//Checks too see if two people are already friends or not      
boolean alreadyFriends(String name1, String name2){
    
    ArrayList friendsOf1 = getFriendsOf(name1);
    for(int i = 0; i < friendsOf1.size(); i++){
        if(name2.equals(friendsOf1.get(i))){
            return true;
        }
    }
    
    return false;
}

//Returns all the friends of a user in an array list
public ArrayList getFriendsOf(String name){
    ArrayList theirFriends = new ArrayList();
    for(int i = 0; i<friends.size(); i++){
        if(friends.get(i).getUserName().equals(name)){
            theirFriends.add(friends.get(i).getFriend());
        }
        else if(friends.get(i).getFriend().equals(name)){
            theirFriends.add(friends.get(i).getUserName());
        }
    }
    
    return theirFriends;
}

      

 */
        
 
}
