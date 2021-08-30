/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetworkdatabase;

/**
 *
 * @author samue
 */
public class userFriends {
    
    private String userName;
    private String friend;
    
    public userFriends(String data){
        filterInfo(data);
    }

   
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }
    
    private void filterInfo(String originalData){
        String[] tokens = originalData.split(", ");
        userName = tokens[0];
        friend = tokens[1];
}
    
}
