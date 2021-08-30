/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userProfile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author samue
 */
public class userProfile implements Serializable{
    
        
    private String username;
    private String password;
    private String name;
    private String age;
    private ArrayList musicPref = new ArrayList();
    public  ArrayList <userProfile>friends = new ArrayList();
    public  ArrayList <userProfile>recieveRequests = new ArrayList();
 
  
    

        public userProfile(){
        
    }

    
    public userProfile(String data){
        filterInfo(data);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public ArrayList getMusicPref() {
        return musicPref;
    }

    public void setMusicPref(ArrayList musicPref) {
        this.musicPref = musicPref;
    }
    
    /*
     public ArrayList getFriends(){
        return friends;
    }
    
    public void setFriends(ArrayList friends){
        this.friends = friends;
    }
    
*/
    // adds a request to be friends in the arraylist
    public void addToRequest(userProfile user){
        recieveRequests.add(user);
    }
    
    public void removeRequest(int i){
        recieveRequests.remove(i);
    }
    
    
    /*
    public ArrayList getFriendRequest(){
        return recieveRequests;
    }
    
*/
    
    private void filterInfo(String originalData){
        String[] tokens = originalData.split(", ");
        if (tokens.length == 1){
            username = tokens[0];
        }
        else{
            username = tokens[0].trim();
            password = tokens[1].trim();
            name = tokens[2].trim();
            age = tokens[3].trim();
        }
        
        
        for(int i = 4; i<tokens.length; i++){
            musicPref.add(tokens[i]);
        }
    }
    
}