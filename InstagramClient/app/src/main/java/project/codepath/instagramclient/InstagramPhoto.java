package project.codepath.instagramclient;

import java.util.ArrayList;

/**
 * Created by jnagaraj on 2/2/16.
 */
public class InstagramPhoto {

    public String username;
    public String caption;
    public String imageURL;
    public int imageHeight;
    public int likesCount;
    public int commentsCount;
    public String fullName;
    public String profilePicURL;
    public long created_time;

    public ArrayList<InstagramComment> comments;
}
