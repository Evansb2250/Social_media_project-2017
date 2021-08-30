
package musicinfo;
import java.io.Serializable;

public class Musicinfo implements Serializable{

    private String mp3FileName;
    private String songName;
    private String artistName;
    private String user;
    
    public Musicinfo(String data){
        filterInfo(data);
    }
    private void filterInfo(String data){
        String[] tokens = data.split("/");
        this.mp3FileName = tokens[0];
        this.songName = tokens[1];
        this.artistName = tokens[2];
        this.user = tokens[3];
    }
  
    public String getMp3FileName(){
        return this.mp3FileName;
    }
    public String getSongName(){
        return this.songName;
    }
    public String getArtistName(){
        return this.artistName;
    }
    public String getuserWhoUploaded(){
        return this.user;
    }
   
   
    
}