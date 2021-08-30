/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetworkgui;

/**
 *
 * @author samue
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import userProfile.userProfile;
import message.Message;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;


/**
 *
 * @author samue
 */
public class GUI extends Thread {
      
    
        public static void main(String[] args) {
            
            GUI user = new GUI();
            user.buildLoginFrame();   
             user.start();
             
        }
    
   
    public userProfile currentUser = new userProfile();

    public Socket appSocket;
    private boolean loggedOn = false;

    
    String userName = "e ";
    protected JFrame frame, mainFrame, regFrame;
    protected JTextField loginField, passwordField, ageField, nameField,
                         loginFieldR, passwordFieldR;
    protected JTextArea postBox, timeLine, informationBox;

    protected JLabel aLabelR, nLabelR, uLabelL, uLabelR,
                     pLabelL, pLabelR,friendLabel, informationLabel,
                     sharedMP3Label, musicLabel, pplOnlineLabel , friendRequestLabel,
                     friendsPostLabel, postLabel, userNameLabel;

 
    protected  JButton addButton , removeButton , loginButton,
                       registerButton, cancelButton, registerButtonR,
                       request , chat , accept ,
                       refuse,  sendButton , playButton;

   
          DefaultListModel genreTypes = new DefaultListModel();
          DefaultListModel yourFriends = new DefaultListModel();
          DefaultListModel friendshipRequests = new DefaultListModel();
          DefaultListModel yourSongList = new DefaultListModel();
          DefaultListModel activeUsersList = new DefaultListModel();
    
    protected JList preferenceList;
    protected JList friendsList;
    protected JList sharedSongsList;
    protected JList activeUserLst;
    protected JList requestList ;
   
     protected Set<String>  genrePreference;
    

        protected ArrayList<String> information = new ArrayList<String>();
    

             protected void initializeVariables(){
                    
                     activeUsersList.addElement(" ");
                     friendshipRequests.addElement(" ");
                     yourSongList.addElement(" ");
                     yourFriends.addElement(" ");
                     activeUsersList.addElement(" ");

         }

    
    
    
    
    
    public GUI(){
     yourFriends.addElement(" ");
     friendshipRequests.addElement(" ");
     yourSongList.addElement(" ");
     activeUsersList.addElement(" ");
     
    }

  
    private void buildLoginFrame(){
        frame = new JFrame();
        frame.setSize(500,300);
        frame.setTitle("login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true);

        //Frame
     
     
      JPanel  loginPanel = new JPanel();
      loginPanel.setLayout(new BorderLayout());
      
       JPanel rightContainer = new JPanel();
   

        //Inserting code for the right section
        rightContainer.setLayout(new GridBagLayout());
     
       
        uLabelL = new JLabel("username");
        pLabelL = new JLabel("password");
        
         loginButton  = new JButton("Submit"); 
         registerButton = new JButton("Register");
        
       // Creating TextFields for Login
         loginField = new JTextField(15);
         passwordField = new JPasswordField(15);
         
         //adding contents to rightContainer
   
         rightContainer.add(uLabelL, grid(0,0,10,5,0,10));

         rightContainer.add(loginField, grid(1,0,0,0,0,0));
 
         rightContainer.add(pLabelL, grid(0,1,0,0,0,0));
        
         rightContainer.add(passwordField, grid(1,1,0,0,0,0));
         rightContainer.add(loginButton, grid(1,2,10,0,0,80));
         rightContainer.add(registerButton, grid(1,2,10,80,0,0));

         
         loginButton.addActionListener(new ActionListener() {
        
             public void actionPerformed(ActionEvent e){
              
                 
                 information.add(loginField.getText().toString().trim());
                 information.add(passwordField.getText().toString().trim());

              userLogin();
        
             }

    });

                registerButton.addActionListener(new ActionListener(){
                 
                 public void actionPerformed(ActionEvent e){
                    frame.dispose();
                     buildRegistration();
                 }
             });
        

         loginPanel.add(rightContainer);
         frame.add(loginPanel);
         frame.setVisible(true);
  
    }

    private void buildRegistration(){
    regFrame = new JFrame();
    regFrame.setSize(800,500);
    regFrame.setTitle("Registration Page");
    regFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    regFrame.setLayout(new GridLayout(1,2));
   
    JPanel RegfoundationLayout = new JPanel();
    RegfoundationLayout.setLayout(new GridLayout(1,2));
    

     BuildRegPart1();
     BuildRegPart2();

    regFrame.setVisible(true);
 
    }
    

    private void BuildRegPart1(){
    GridBagConstraints mod = new GridBagConstraints();
     

    //Creating first Section of the registration GUI
     JPanel firstHalf = new JPanel();
     firstHalf.setLayout(new GridBagLayout());
     
    aLabelR = new JLabel("age");
    nLabelR = new JLabel("name");
    uLabelR = new JLabel("username");
    pLabelR = new JLabel("password");
     
     
  cancelButton = new JButton("Cancel");
  registerButtonR = new JButton("Register");
     
     //INITIALIZING JTEXTFIELDS
    loginFieldR = new JTextField(15);
    passwordFieldR = new JTextField(15);
    nameField = new JTextField(10);
    ageField = new JTextField(10);
    
    //INITIALIZING JBUTTONS
 
     
    
    //ADDING COMPONENTS TO THE LAYOUT
    firstHalf.add(uLabelR, grid(0,0,0,0,0,0));
    firstHalf.add(loginFieldR,grid(1,0,0,0,0,0));
    firstHalf.add(pLabelR, grid(0,1,0,0,0,0));
    firstHalf.add(passwordFieldR, grid(1,1,0,0,0,0));     
    firstHalf.add(nLabelR, grid(0,2,0,0,0,0));
    firstHalf.add(nameField, grid(1,2,0,0,0,0));
    firstHalf.add(aLabelR, grid(0,3,0,0,0,0));
    firstHalf.add(ageField, grid(1,3,0,0,0,0));
    firstHalf.add(registerButtonR, grid(0,7,60,0,0,0) );
    firstHalf.add(cancelButton, grid(1,7,60,0,0,40) );
    

    // Creating event
    registerButtonR.addActionListener(new ActionListener(){
   public void actionPerformed(ActionEvent e){
       // CALL A METHOD That will store the values
                 
                 information.clear();

                 information.add(loginFieldR.getText().toString().toLowerCase());
                 information.add(passwordFieldR.getText().toString());
                 information.add(nameField.getText().toString());
                 information.add(ageField.getText().toString());
                 
                 for(int i = 0; i < genrePreference.size(); ++i){
                     information.add(genrePreference.toArray()[i].toString());
                 }
                 CreateUser();
                 
                 regFrame.dispose();  
                 buildLoginFrame();
       
   }
    });
    

    cancelButton.addActionListener(new ActionListener(){
     
        @Override
        public void actionPerformed(ActionEvent e){
                 regFrame.dispose();  
                 buildLoginFrame();
        }
        
    });
    

   regFrame.add(firstHalf);

    }
    
    
    
    
    private void BuildRegPart2(){
        JPanel secondHalf = new JPanel();
        secondHalf.setLayout(new GridBagLayout());
        GridBagConstraints mod = new GridBagConstraints();
        genrePreference = new HashSet<String>();
        genreTypes = new DefaultListModel();
        preferenceList = new JList(genreTypes);
        //initializing variables
     String  genre[] = {"Jazz","Rap","Pop", "Country", "Indie","Metal"};
     JComboBox choice = new JComboBox(genre);
    
         musicLabel = new JLabel("Music Profile");
         addButton = new JButton("Add");
         removeButton = new JButton("remove");

     
         
        //initializing JCOMPONENTS
   
        
        
        
        secondHalf.add(musicLabel, grid(0,0,0,0,60,0));
        secondHalf.add(choice, grid(0,0,0,0,0,0));
        secondHalf.add(addButton, grid(1,0,0,10,0,0));
        secondHalf.add(removeButton, grid(2,0,0,5,0,0));
        
        
       
        mod.gridx = 0;
        mod.gridy = 2;
        mod.gridwidth= 0;
        mod.insets = new Insets(0,0,70,0);
           
           
        // MODIFICATION TO THE WIDTH OF THE JLIST   
        preferenceList.setFixedCellWidth(300);  
        secondHalf.add(preferenceList,mod);


        addButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(null,choice.getSelectedItem() + " was added");
        genrePreference.add(choice.getSelectedItem().toString());
         genreTypes.removeAllElements();  
        for(int i = 0; i < genrePreference.size(); i++){
              
         genreTypes.addElement(genrePreference.toArray()[i]);
         
              }

              
            }
        });
        

        
               removeButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                                    JOptionPane.showMessageDialog(null, preferenceList.getSelectedValue().toString() + " was removed");
                                   genrePreference.remove(preferenceList.getSelectedValue().toString());
                                   genreTypes.removeAllElements();  
                                                            
                                                     for(int i = 0; i < genrePreference.size(); i++){ genreTypes.addElement(genrePreference.toArray()[i]);}

                                                            }
                    });
         
      regFrame.add(secondHalf);
     
    }
    
    

    
    
    private void buildMainFrame(){
        //destroyAndRebuildFrame(900,900,"Social Frame");
        mainFrame = new JFrame();
        mainFrame.setSize(900, 900);
        mainFrame.setLayout(new GridLayout(4,0));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        userNameLabel = new JLabel(userName);
   
       // userName.setText(currentUser.getUsername());
       mainFrame.setTitle(userName);
        JButton logoutButton = new JButton("Logout");
       
        
      
       

        
  
  
       // initializeVariables();
       //Creating each row in seperate functions.
       mainFrameRow1();
       mainFrameRow2();
       mainFrameRow3();
        
       
       mainFrame.setVisible(true);
      
    }
    
    

    
    
    
    
    
    
    
    
    

    private void mainFrameRow1(){
        
       
        JPanel topLayer = new JPanel();
        topLayer.setLayout(new GridBagLayout());
         informationBox = new JTextArea(5,14); 
        //Creating the labels for each row
         playButton = new JButton("Play");
         JButton logoutButton = new JButton("Logout");
       
        friendLabel = new JLabel("Friends");
        informationBox .setEditable(false);
      
        friendsList = new JList( yourFriends);
    
         sharedSongsList = new JList(yourSongList);
         sharedMP3Label = new JLabel("Shared Songs");
         informationLabel = new JLabel("Information");
 
        friendsList.setFixedCellWidth(250);
        sharedSongsList.setFixedCellWidth(250);
        
        //Grid(Top, Left, DOWN, Right)
        topLayer.add(friendLabel, grid(0,0,0,0,10,0));
        topLayer.add(friendsList, grid(0,1,0,0,0,0));
        topLayer.add(logoutButton, grid(0,2,20,0,0,0));
        
        topLayer.add(informationLabel,grid(1,0,0,0,0,0));
        
        topLayer.add( informationBox , grid(1,1,0,10,0,0));
    
        topLayer.add(sharedMP3Label, grid(2,0,0,0,0,0));
        topLayer.add(sharedSongsList,grid(2,1,0,10,0,0));
         
         
         
         topLayer.add(playButton, grid(2,2,0,0,0,0)); 


        playButton.addActionListener(new ActionListener(){
           
           public void actionPerformed(ActionEvent event){
             
           }
       });

        
        
        logoutButton.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent Event){
                Message logOutUser = new Message("logout");
                logOutUser.activeUser = currentUser;
                yourFriends.clear();
                LogOutUser(logOutUser);
                loggedOn = false;
                mainFrame.dispose();
                buildLoginFrame();
                
            }
        });
        
       friendsList.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent evt){
               JList friendsList = (JList)evt.getSource();
               String name = "blank";
               String age = "15";
               String City = "Pensacola";
               if(evt.getClickCount() == 1){
                   JOptionPane.showMessageDialog(mainFrame, " was clicked " + friendsList.getSelectedValue() );
                   for(int i = 0; i < currentUser.friends.size(); i++){
                       if(currentUser.friends.get(i).getUsername().equals(friendsList.getSelectedValue())){
                           name = currentUser.friends.get(i).getName();
                           age = currentUser.friends.get(i).getAge();
                         break;
                       }
                   }
                
                   informationBox.setText("name: " + name + "\nAge: " + age +"\nLocation: "
                           + City);
               }
           }
       }
       );
        mainFrame.add(topLayer);
           
            
    }
    

    private void mainFrameRow2(){
      GridBagConstraints mod = new GridBagConstraints();
      JPanel middleLayer = new JPanel();
      JPanel textFrame = new JPanel(new BorderLayout());
      
      middleLayer.setLayout(new GridBagLayout());
      timeLine = new JTextArea(8,60);
      postBox = new JTextArea(1,43);
    
      
      timeLine.setText("");
      textFrame.add(postBox, BorderLayout.NORTH);
      
      friendsPostLabel = new JLabel("Friend Posts");
    
      postLabel = new JLabel("Post : ");
      sendButton = new JButton("Send");
        
      sendButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              Message createPost = new Message("timeline post");
             createPost.post.setFrom(currentUser.getUsername());
             createPost.post.setPost(postBox.getText());
             timeLine.setText(postBox.getText());
             postBox.setText("");
             
             sendMessage(createPost);
            
            }
        });
        
        
        
        
   
      middleLayer.add( friendsPostLabel, grid(0,0,0,0,0,0));
      middleLayer.add(timeLine, grid(0,1,10,0,0,0));
     
      middleLayer.add(postLabel,grid(0,2,10,0,0,600));
      
      middleLayer.add(textFrame, grid(0,2,10,0,0,80));
      
       middleLayer.add(sendButton, grid(0,2, 10,500,0,0));

     
      mainFrame.add(middleLayer);
    }
    
    
    
    

     private void mainFrameRow3(){
        GridBagConstraints mod = new GridBagConstraints();
        JPanel topLayer = new JPanel();
        topLayer.setLayout(new GridBagLayout());

        
        activeUserLst = new JList(activeUsersList);
        requestList = new JList( friendshipRequests);

        pplOnlineLabel = new JLabel("List of connected people");
        friendRequestLabel = new JLabel("Friendship Request from");
        
        
        
        
        
        request = new JButton("Request \nFriendship");
        chat = new JButton("Chat");
        accept = new JButton("Accept");
        refuse = new JButton("Refuse");
        
        request.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Retrieves the name from the list of active users
                String targetedFriend = activeUserLst.getSelectedValue().toString();
                // removes the block tags
              targetedFriend = targetedFriend.substring(0, targetedFriend.length());
             
                //Creates a new object
                Message sendFriendRequest = new Message("friend request");
                
               // Entering the name of the person who sent the request as the active user 
                sendFriendRequest.activeUser =  currentUser;
                
                //Sets the name of the person to befriend
                sendFriendRequest.setBefriendVariable(targetedFriend);
                
                sendMessage(sendFriendRequest);
             
            }
        });

        
         accept.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
             String nameOfAccount = requestList.getSelectedValue().toString();
             requestList.clearSelection();
                 
             JOptionPane.showMessageDialog(frame, nameOfAccount  + " was selected" );
             
           
             Message FriendAcceptedRequest = new Message("accept request");
           FriendAcceptedRequest.activeUser = currentUser;
           FriendAcceptedRequest.nameOfPersonToBefriend = nameOfAccount;
            FriendAcceptedRequest.makeFriend(nameOfAccount );
              
           sendMessage(FriendAcceptedRequest);
             
            }
        });

         
        
        
        refuse.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
              String nameOfAccount = requestList.getSelectedValue().toString();
             requestList.clearSelection();
                 
             JOptionPane.showMessageDialog(frame, nameOfAccount  + " was selected to be removed" );
             
           
              Message FriendRequestRefused = new Message("refuse request");
              
              FriendRequestRefused.activeUser = currentUser;
              
              FriendRequestRefused.nameOfRejectedRequest = nameOfAccount;
              
              sendMessage(FriendRequestRefused);
              
                
                
            }
        });
        
   
        
        
        
         
    
        mod.insets = new Insets(0,20,15,20);
        
       activeUserLst.setFixedCellWidth(200);
       requestList.setFixedCellWidth(200);
        
        activeUserLst.setFixedCellHeight(30);
        requestList.setFixedCellHeight(40);
        
        topLayer.add(pplOnlineLabel, grid(0,0,0,0,0,0));
        
        topLayer.add(activeUserLst, grid(0,1,10,0,0,0));
        
        topLayer.add(requestList, grid(2,1,0,20,0,20)); 
        
        topLayer.add(request, grid(1,1,0,20,60,0));
        
        topLayer.add(chat, grid(1,1,0,0,0,0));
          
        topLayer.add(accept, grid(3,1,0,0,60,0));
        
        topLayer.add(refuse, grid(3,1,0,0,0,0));
        
        topLayer.add(friendRequestLabel, grid(2,0,0,0,0,0));

        
        mainFrame.add(topLayer);
        
         
     }
  

    private  GridBagConstraints grid(int x, int y, int TOP, int LEFT, int DOWN, int RIGHT){
    GridBagConstraints mod = new GridBagConstraints();
     mod.insets = new Insets(TOP,LEFT,DOWN,RIGHT);
     
        mod.gridx = x;
        mod.gridy = y;
        
        return mod;
    }

    //This method is doin a pull request from the database
    public void CreateUser(){
        boolean completed;
        String combineInfo= "";
        for(int i = 0; i < information.size(); i++){
        combineInfo += " " + information.get(i) + " ,";
   }
       completed = registeringAccount(combineInfo);
         if(completed == true){
             JOptionPane.showMessageDialog(frame, "Details Sent");
         }
         else
             JOptionPane.showMessageDialog(frame, "Error Detected");
 }


    public void userLogin(){
   
            
                         Message retrievedMessage = sendLoginCredentials(loginField.getText().toString().toLowerCase().trim(), passwordField.getText().toString().trim());
                
                                 if(retrievedMessage.correctLoginDetails == true){
                                       JOptionPane.showMessageDialog(frame, "Login Correct");
                                       
                                        currentUser = retrievedMessage.activeUser;
                                        userName =  currentUser.getUsername();
                                      
                                        frame.dispose();
                                       buildMainFrame();
                                 }
                                 else {
                                      JOptionPane.showMessageDialog(frame, "Login Incorrect");
                                 }

                                  System.out.println("connection Finished");
      
   
        }
    


    
    
        public Message sendLoginCredentials(String username, String password){
         Message messageFrmServerCopy = new Message("login");
         messageFrmServerCopy.correctLoginDetails = false;
         
                try{
                appSocket = new Socket("localhost", 5000);
                Message myLoginDetails = new Message("login");
                ObjectOutputStream obMessage = new ObjectOutputStream(new PrintStream (appSocket.getOutputStream()));

                 System.out.println("Writing");
                myLoginDetails.setUserNameAndPassword(username, password);
                obMessage.writeObject(myLoginDetails);    
                obMessage.flush();
               
                System.out.println("Message sent");
                ObjectInputStream messageFromServer = new ObjectInputStream(appSocket.getInputStream());
                messageFrmServerCopy = new Message();

                messageFrmServerCopy = (Message)messageFromServer.readObject();
             
         
          
             
                                return messageFrmServerCopy;
                                
                                
                }catch(IOException   | NullPointerException | ClassNotFoundException e){
               System.out.println("Error  at the end "  +  e.getMessage());
              }
            
          return messageFrmServerCopy;

}
      
   
      public void LogOutUser(Message userInfo){

                try{
                appSocket = new Socket("localhost", 5000);
    
                ObjectOutputStream obMessage = new ObjectOutputStream(new PrintStream (appSocket.getOutputStream()));
                  
                obMessage.writeObject(userInfo);    
                obMessage.flush();
                
                      
                }catch(IOException   e){
               System.out.println("Error  at the end "  +  e.getMessage());
              }
            


}
      

      
      private void updateGuiInfo(Message serverData){
      //This section can be seen by anyone logged in
          activeUsersList.clear();
          timeLine = new JTextArea(8, 60);
       /*      if(timeLine.getText().length() > 0){
              timeLine.setText("");
           }
          */
          System.out.println(serverData.totalPost.size());
          for(int i = 0; i < serverData.usersOnline.size(); i++){
              activeUsersList.addElement(serverData.usersOnline.get(i).getUsername());
           
              
              
       
           for(int post = 0; post < serverData.totalPost.size(); post++){
               timeLine.setText(serverData.totalPost.get(post).getFrom() + " : " + serverData.totalPost.get(post).getPost());
              
           } 
            
           //This loop grabs all the relevent information on the current user and stores it in the GUI
              if(serverData.usersOnline.get(i).getUsername().equals(userName)){
                  System.out.println(" you are " + serverData.usersOnline.get(i).getUsername());
                  
                  currentUser = serverData.usersOnline.get(i);
                 
                   //This section checks to see if there have been any request made    
                  if(serverData.usersOnline.get(i).recieveRequests.isEmpty() == false){
                      //displayFriendRequest(serverData.activeUser);
                       friendshipRequests.clear();
                              
                       //This section loops the different friend request and puts them into a variable
                       for(userProfile myAccount: serverData.usersOnline.get(i).recieveRequests){
                         
                           
                                    String name = myAccount.getUsername();
                                 
   
                                    friendshipRequests.addElement(name);
                                    
                                                                }
                       
                                                }
                  else{
                       friendshipRequests.clear();
                       friendshipRequests.addElement(" ");
                               }

// End of displaying request function
                                                                //This section loads your friends into the GUI
                                                                
                                                                   if(serverData.usersOnline.get(i).friends.isEmpty() == false){
                                                                    //displayFriendRequest(serverData.activeUser);
                                                                     yourFriends.clear();
                                                                       
                                                                     
                                                                     //THeres a bug in the code where it is duplicating the same user Therefore
                                                                    //I will iterate through the array and add it to a set.
                                                                       
                                                                     //This section loops the different friend request and puts them into a variable
                                                                     for(int b = 0; b < serverData.usersOnline.get(i).friends.size(); b++){
                                                                                  //String name = serverData.usersOnline.get(i).getFriends().toString();
                                                                               //   name = name.substring(1, name.length()-1);
                                                                          
                                                                              
                                                                                  yourFriends.addElement(serverData.usersOnline.get(i).friends.get(b).getUsername());
                                                                                  
                                                                                 
                                                                                                              }
                                 

                                                                                      } 

          }          
      }
      }
      

      
      
      
      public boolean registeringAccount(String accountDetails){
             try{
                                 appSocket = new Socket("localhost", 5000);
                                 System.out.println("Prepare Message class to register a new user");
                                 Message newUserDetails = new Message("new user");
                                 
                                 // set information into message
                                 newUserDetails.registerNewUser(accountDetails);
                                 System.out.println("Preparing to send Message");
                                 ObjectOutputStream obMessage = new ObjectOutputStream(appSocket.getOutputStream());
                                 
                                 
                                 obMessage.writeObject(newUserDetails);
                                 obMessage.flush();
                                 obMessage.close();
                                
                                 
                                  System.out.println("connection finished");

        }catch(IOException e){ return false;}
             return true;
      }
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      

      public void run(){
       
         
           try{ 
               
                          
            while(true){
                Message messageFrmServerCopy = new Message("update");
                appSocket = new Socket("localhost", 5000);
                ObjectOutputStream routineMessage = new ObjectOutputStream(new PrintStream (appSocket.getOutputStream()));    
                routineMessage.writeObject(messageFrmServerCopy);
                routineMessage.flush();
               
                
                System.out.println("Message sent");
                ObjectInputStream messageFromServer = new ObjectInputStream(appSocket.getInputStream());
                messageFrmServerCopy = new Message();
                messageFrmServerCopy = (Message)messageFromServer.readObject();
                System.out.println("Update Received");
                updateGuiInfo(messageFrmServerCopy);
                
                sleep(10000);
     
            }
           }catch(IOException | ClassNotFoundException | InterruptedException e){ System.out.println("THread interrupted " + e.getMessage()  );}
          
      }
      

       public void  sendMessage(Message sendRequest){

               try{
                appSocket = new Socket("localhost", 5000);
                ObjectOutputStream outPutMessage = new ObjectOutputStream(new PrintStream (appSocket.getOutputStream()));
                  
                outPutMessage.writeObject(sendRequest);    
                outPutMessage.flush();
                
                      
                }catch(IOException   e){
               System.out.println("Error  at the end "  +  e.getMessage());
              }
            
            }
            
       
       
       
       
  
 
    
  

 
      
}