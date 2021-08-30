/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetworkdatabase;

import post.Post;
import userProfile.userProfile;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import userProfile.userProfile;
import message.Message;

/**
 *                                                                                                                                                                                                                                                                                                                                                 
 * @author samue
 */
public class SocialNetworkDatabase implements Runnable{
    
      Socket classSocket;
    
      static ArrayList<Post> totalPost = new ArrayList<>();
      static database db;
    
    
      
  public SocialNetworkDatabase(Socket socket){
         this.classSocket = socket;
         
       
}
      
      
      
      public static void main(String args[]) throws Exception { 
      ServerSocket serverSocket = new ServerSocket(5000);
     
     
   
      System.out.println("listening for a connection");
      db = new database();
   try{ 
       while(true){
    
       
       Socket  clientSocket = serverSocket.accept();
       System.out.println("connected");
       new Thread(new SocialNetworkDatabase(clientSocket)).start();
       
       System.out.println("Connected to back end");
     /*
       sendUPDate();
         sleep(5000);
         Thread.join();
       
       */
     
   
       }

    }catch(IOException  e){}
    }

      
        public void sendData(Message sendMessage){
            try{
                  ObjectOutputStream deliviringMessage;
                  deliviringMessage = new ObjectOutputStream(new PrintStream (classSocket.getOutputStream()));
                //  System.out.println("Preparing to send " + sendMessage.activeUser.getUsername());
                  deliviringMessage.writeObject(sendMessage);
                  
                  deliviringMessage.flush();
              
            }catch(IOException   e){  System.out.println("Error in send Data "  +  e.getMessage());}
            
        }
    
        
        public boolean isOnline(String name){
             boolean online = false;         
                  for(int i = 0; i < db.activeUsers.size(); i++ ){
                      if(name.equals(db.activeUsers.get(i).getUsername())){
                       online = true;
                      }  
        }
                  return online;
        }
        
 
        
        
    
    @Override
    public void run() {
    

        // ObjectInputStream messageCarrier;
   
        userProfile user;
        try{
            
        
              
              
            while(true){
           
           
   
            
            System.out.println("Waiting for message from client");
        
             ObjectInputStream messageCarrier = new ObjectInputStream(classSocket.getInputStream());
             Message messageFromClient = (Message)messageCarrier.readObject();
            
           
            
            if(messageFromClient.getRequest().equalsIgnoreCase("login")){
                    System.out.println("Checking if user exist");
                    System.out.println(messageFromClient.tempUserName  + "  " +  messageFromClient.tempPassword );
                    user = db.login(messageFromClient.tempUserName, messageFromClient.tempPassword);
           
                    
                    
                    if(user.getUsername().equalsIgnoreCase("invalid")){
                         messageFromClient.correctLoginDetails = false;
                         sendData(messageFromClient);
                         }else {
           
                                System.out.println("Login Successfull");
                                messageFromClient.correctLoginDetails = true;

                                                        if(isOnline(user.getUsername()) == false){
                                                 
                                                        System.out.println("adding user to vector");
                                                        db.activeUsers.add(user);
                                                        System.out.println("created copy");
                                                        messageFromClient.activeUser = user;
                                                        sendData(messageFromClient);
                                                     
                                                        System.out.println("MESSAGE SENT");
                                                        
                                                        }
                                                        
                                                        
                                                        else {  System.out.println("User is already Online");
                                                                sendData(messageFromClient);
                                                      
                                                        }}
         
}
           
  
              if(messageFromClient.getRequest().equalsIgnoreCase("new user")){   db.registerUser(messageFromClient.newUserDetails);  }
              
              if(messageFromClient.getRequest().equalsIgnoreCase("logout")){
                  System.out.println(messageFromClient.activeUser.getUsername() + "  logging out");
                 
                  
                  
                  for(int i = 0; i < db.activeUsers.size(); i++ ){
                      if(messageFromClient.activeUser.getUsername().equals(db.activeUsers.get(i).getUsername())){
                          db.activeUsers.remove(i);
                      }
                  }
                }
              
              
              
              if(messageFromClient.getRequest().equalsIgnoreCase("friend request")){
                  //find out who sent it
                System.out.println(messageFromClient.activeUser.getUsername() + " wants to make friends with " + messageFromClient.nameOfPersonToBefriend) ;
                  //find out who they want to beFriend
                  for(int i =0; i < db.activeUsers.size(); i++){
                      if(db.activeUsers.get(i).getUsername().equalsIgnoreCase(messageFromClient.nameOfPersonToBefriend)){
                       
                         System.out.println("---------"+messageFromClient.activeUser.recieveRequests.size()+"----------");
                         userProfile tempUser = db.sendRequest(messageFromClient.activeUser, messageFromClient.nameOfPersonToBefriend) ;
                         System.out.println("---------"+tempUser.recieveRequests.size()+"----------");
                         db.activeUsers.remove(i);
                         db.activeUsers.add(tempUser);
                      }
                  }
                  
              }
              
                  if(messageFromClient.getRequest().equalsIgnoreCase("refuse request")){
                 
                  //find out who they want to beFriend
                  for(int i =0; i < db.activeUsers.size(); i++){
                      if(db.activeUsers.get(i).getUsername().equalsIgnoreCase(messageFromClient.activeUser.getUsername())){
                            System.out.println("---------"+messageFromClient.activeUser.getUsername()+"----------");
                            System.out.println("---------"+messageFromClient.activeUser.recieveRequests.size()+"----------");
                            System.out.println("**---------"+messageFromClient.nameOfRejectedRequest+"----------**");
                            userProfile tempUser = db.removeRequest(messageFromClient.activeUser, messageFromClient.nameOfRejectedRequest);
                            System.out.println("---------"+tempUser.recieveRequests.size()+"----------");
                            db.activeUsers.remove(i);
                            db.activeUsers.add(tempUser);
                      }
                  }
                  
              }
              
              
              
                  
                  
               
              if(messageFromClient.getRequest().equalsIgnoreCase("timeline post")){
                    synchronized(this){ 
                  totalPost.add(messageFromClient.post);
                    }
              }
                
              
              
              if(messageFromClient.getRequest().equalsIgnoreCase("update")){
                  Message systemUpdates = new Message();
           //       db.updateAllFriends();
              
                  systemUpdates.getActiveUsers(db.activeUsers);
                  
                  if(totalPost.isEmpty()  == false){
                  System.out.println(totalPost.size() + " post");
                  systemUpdates.getAllPost(totalPost);
                  for(int i = 0; i < totalPost.size(); i++){
                      System.out.println(totalPost.get(i).getFrom() + ": " + totalPost.get(i).getPost() );
                  }
                  }
                  sendData(systemUpdates);
                  
              }
           
         
              
              
             
              if(messageFromClient.getRequest().equalsIgnoreCase("accept request")){
               //adding friend to the users account
               db.addFriend( messageFromClient.activeUser, messageFromClient.nameOfPersonToBefriend);
               //removing request from the users account
               userProfile tempUser = db.removeRequest(messageFromClient.activeUser, messageFromClient.nameOfPersonToBefriend);
               for(int i =0; i < db.activeUsers.size(); i++){
                   if(db.activeUsers.get(i).getUsername().equalsIgnoreCase(tempUser.getUsername())){
                       db.activeUsers.remove(i);
                       db.activeUsers.add(tempUser);
                   }
               }
               System.out.println("---**---friend of active user: " + messageFromClient.activeUser.friends.get(0).getName());
         
              }
            }
            
            
     
             
        }catch(IOException  | ClassNotFoundException | NullPointerException e){
         System.out.println("Error From  "  +  e.getMessage());
        }
  

        
    }
    
}
