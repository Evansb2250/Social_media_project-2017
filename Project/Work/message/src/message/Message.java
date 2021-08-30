/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import post.Post;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;
import userProfile.userProfile;
/**
 *
 * @author samue
 */
public class Message implements Serializable {

    /**
     * @param args the command line arguments
     */
   
   
   public userProfile activeUser;
   public Post post = new Post();
   public  String newUserDetails;
   public  String tempUserName;
   public  String tempPassword;
   public  String clientRequest2Server;
   public  String nameOfPersonToBefriend;
   public  String nameOfRejectedRequest;
   public  String friendPair;
   
   public boolean correctLoginDetails = false;
  
   
   //info pertaining to the site
   public Vector<userProfile> usersOnline = new Vector<userProfile>();

   public ArrayList<Post> totalPost = new ArrayList<>();
   
   public Message(){  }
   
   
   
   public void makeFriend(String pair){
       this.friendPair = pair;
   }
   
  public Message(String request){
      this.clientRequest2Server = request;
  }
    
    
   public String getRequest(){
       return clientRequest2Server;
   }
 
    
    public void setUserNameAndPassword(String userName, String password){
        this.tempUserName = userName;
        this.tempPassword = password;
    }
    
    public void setUserProfileDetails(userProfile currentAccount){
        this.activeUser = currentAccount;
}
    
    
  public void registerNewUser(String data){
          this.newUserDetails = data;
  }
  
  
  public void setBefriendVariable( String to){
      nameOfPersonToBefriend = to;
  }
  
  
  public void getActiveUsers( Vector<userProfile> usersOnline){
      this.usersOnline = usersOnline;
  }
  
  public void getAllPost(ArrayList allPost){
      this.totalPost = allPost;
  }
 
    
}
