import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;




public class SocialNetwork {
    
    private Map<String,Set<String>> network;//Set<String> follows Key
    private List<Post> allPosts;
    private Map<String,Set<String>> utentiSeguenti; //Key is following Set<String>

    public SocialNetwork(){
        network = new HashMap<>();
        allPosts = new Vector<>();
        utentiSeguenti = new HashMap<>();
    }

    
    //per ogni post crea una mappa che vede come chiave l'OP del post e tutte le persone che hanno seguito almeno un suo post
    public Map<String,Set<String>> guessFollowers(List<Post> ps){
        //Vector<String> isChecked = new Vector<String>();

        Map<String,Set<String>> net = new HashMap<String,Set<String>>();
        for (Post post : ps) {
            String auth = post.getAuthor();
            HashSet<String> followersPost = post.getFollowers();
            if(net.containsKey(auth)){
                Set<String> obj = net.get(auth);
                HashSet<String> mergedFollowers = superMerge(obj,followersPost);
                net.replace(auth,obj,mergedFollowers);
            
            }else{
            net.put(post.getAuthor(), followersPost);
            }

            
        }
        return net;

    }
    public List<String> influencers(){
      
        Vector<String> influencers = new Vector<>();
        for (Map.Entry<String,Set<String>> entry:this.network.entrySet() ) {
            Set<String> followers = entry.getValue();
            Set<String> followed = this.utentiSeguenti.get(entry.getKey());
            Integer followerI = 0;
            Integer followedI = 0;
            if(followers != null){
                followerI = followers.size();
            }
            if(followed != null){
                followedI = followed.size();
            }
            if(followerI > followedI){
                influencers.add(entry.getKey());
            }
        }
        return influencers;
    }
    public List<String> influencers(Map<String,Set<String>> follower){
        List<String> influencers = new Vector<String>();
        Map<String,Integer> peopleThatIFollow = new HashMap<>();
        for(Map.Entry<String,Set<String>> entry: follower.entrySet()){
            peopleThatIFollow.put(entry.getKey(),0);
            Integer counter = 0;
             for(String user : entry.getValue()){
                if(follower.get(user) != null ){
                    if(follower.get(user).contains(entry.getKey())){ 
                        counter++;
                    }
                }

             }
             peopleThatIFollow.put(entry.getKey(),counter);
        }
        for(Map.Entry<String,Integer> entry : peopleThatIFollow.entrySet()){
            //System.out.println(entry.getValue() + " " +follower.get(entry.getKey()).size());
            if( entry.getValue() < follower.get(entry.getKey()).size()){
                influencers.add(entry.getKey());
            }
        }
        return influencers;
    }



    public Boolean follow(String userToFollow,String follower){
        //Can't follow yourself dum dum
        if(userToFollow.equals(follower)){return false;}

        //Tutte le persone che seguono userToFollow
        Set<String> followers = network.get(userToFollow);
        //Tutte le persone che il follower segue
        Set<String> foll = utentiSeguenti.get(follower);
        
        return followers.add(follower) && foll.add(userToFollow);
    }
    //Diff piu' merge: mergia due List e se ci sono elementi uguali non li ripete
    private static HashSet<String> superMerge(Set<String> set1,Set<String> set2){
        HashSet<String> returnSet = new HashSet<String>();
        for (String string : set1) {
            returnSet.add(string);
        }
        for (String string : set2) {
            if(!returnSet.contains(string)){
                returnSet.add(string);
            }
        }
        return returnSet;

    }
    public void addPost(Post post){
        this.allPosts.add(post);
    }
    public void addUser(String user){
        network.put(user,new HashSet<String>());
        utentiSeguenti.put(user,new HashSet<String>());
    }
    public Set<String> getMentionedUsers(List<Post> ps){
        HashSet<String> mentionedUsers = new HashSet<String>();
        for(Post post: ps){
            mentionedUsers.add(post.getAuthor());
        }
        return mentionedUsers;
    }
    public Set<String> getMentionedUsers(){

        HashSet<String> mentionedUsers = new HashSet<String>();
        for(Post post : this.getPosts()){
            mentionedUsers.add(post.getAuthor());
        }
        return mentionedUsers;

    }
    public List<Post> getPosts(){
        return this.allPosts;
    }
    public List<Post> writtenBy(String username){
        Vector<Post> posts = new Vector<Post>();
        for(Post ps: this.getPosts()){
            if(ps.getAuthor().equals(username)){
                posts.add(ps);
            }
        }
        return posts;
    }
    public List<Post> writtenBy(List<Post> ps,String username){
        Vector<Post> posts = new Vector<Post>();
        for(Post pss: ps ){
            if(pss.getAuthor().equals(username)){
                posts.add(pss);
            }
        }
        return posts;
    }
    public List<Post> containing(List<String> words){
        Vector<Post> postList = new Vector<Post>();
        for(String word: words){
            for(Post ps: this.getPosts()){
                if(ps.getPostCont().contains(word)){
                    postList.add(ps);
                }
            }
        }
        return postList;
    }
}
