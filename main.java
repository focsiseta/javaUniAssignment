import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        Post x0 = new Post(0,"Giovanni","Ciao mi chiamoGiovanni0");
        Post x1 = new Post(1,"Stefania","Ciao mi chiamoStefania1");
        Post x2 = new Post(2,"Alfredo","Ciao mi chiamoAlfredo2");
        SocialNetwork net = new SocialNetwork();
        x0.follow("Pancrazio");
        x0.follow("Lorenzo");
        x0.follow("Stefania");
        x0.follow("Alfredo");
        x1.follow("Giovanni");


        List<Post> postList = new ArrayList<Post>();
        postList.add(x0);
        postList.add(x1);
        postList.add(x2);
        
        net.addUser("Alfredo");
        net.addUser("Giovanni");
        net.addUser("Pippo");
        net.addUser("Pancrazio");
        net.addUser("Lorenzo");
        System.out.println(net.guessFollowers(postList).toString());
        //assert "{Giovanni=[Pancrazio], Alfredo=[Giovanni], Stefania=[Lorenzo]}".toString().equals(net.guessFollowers(postList).toString()) : true;

        assert net.follow("Alfredo", "Giovanni") : true; // Giovanni segue alfredo
        assert net.follow("Giovanni","Alfredo") : true;  //Alfredo segue Giovanni
        assert net.follow("Giovanni","Pippo") : true;    // Pippo segue Giovanni
        Vector<String> test = new Vector<String>();
        test.add("Giovanni");
        assert net.influencers().equals(test) : true;
        
        System.out.println(net.influencers(net.guessFollowers(postList)));
        //System.out.println(net.influencers());
        //System.out.println(shitbook.getMentionedUsers(postList).toString());
        //System.out.println(shitbook.writtenBy(postList, "Alfredo").toString());
        //System.out.println(shitbook.writtenBy("Stefania").toString());
        //x0.follow("Alfredo");
        Map<String,Set<String>> ohg = net.guessFollowers(postList);
        //System.out.println(net.influencers(ohg).toString());


    }
}
