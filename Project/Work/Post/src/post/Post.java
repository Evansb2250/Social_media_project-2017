/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package post;

import java.io.Serializable;


public class Post implements Serializable{

 private String from = "Me";
 private String post = "Hello I am sooo bored right now";
  
  
  
  public void setFrom(String username){
      this.from = username;
  }
  
  public void setPost(String post){
      this.post = post;
  }
  
  
  public String getFrom(){
      return from;
  }
  public String getPost(){
      return post;
  }
}
