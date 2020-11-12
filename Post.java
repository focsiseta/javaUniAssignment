import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.sql.Timestamp;

public class Post {

    private Integer id;
    private String author;
    private String message;
    private Timestamp timestamp;
    private HashSet<String> followers;

    public Post(Integer id,String author,String message){

        this.id = id;
        this.author = author;
        this.message = message;
        this.followers = new HashSet<String>();
        timestamp = Timestamp.from(Instant.now());
        

    }
    public void editPost(String newPost){
        this.message = newPost;
        timestamp = Timestamp.from(Instant.now());
    }
    public String getAuthor(){
        return this.author;
    }
    public Boolean follow(String user){
        return this.followers.add(user);
    }
    public HashSet<String> getFollowers()
    {
        return this.followers;
    }
    public String getPostCont(){
        return this.message;
    }



    
}
