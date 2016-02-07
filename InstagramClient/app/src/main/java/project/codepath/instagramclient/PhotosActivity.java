package project.codepath.instagramclient;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {

    public static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";

    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotosAdapter aPhotos;
    private SwipeRefreshLayout swipeContainer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        //SEND OUT API REQUEST TO POPULAR PHOTOS
        photos = new ArrayList<>();

        aPhotos = new InstagramPhotosAdapter(this, photos);

        ListView lvPhotos = (ListView)findViewById(R.id.lvPhotos);

        /*

        lvPhotos.setOnScrollListener(new AbsListView.OnScrollListener() {

            int mScrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {

                mScrollState = scrollState;

            }


            @Override
            public void onScroll(AbsListView absListView, int firstItem, int visibleCount, int totalCount) {

                if(mScrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
                    return;

                for(int i = 0; i < visibleCount; i++) {
                    View listItem = absListView.getChildAt(i);
                    if(listItem == null)
                        break;

                    LinearLayout layout = (LinearLayout)findViewById(R.id.llTopBar);

                    int topMargin = 0;
                    if (i == 0) {
                        int top = listItem.getTop();
                        int height = listItem.getHeight();

                        // if top is negative, the list item has scrolled up.
                        // if the title view falls within the container's visible portion,
                        //     set the top margin to be the (inverse) scrolled amount of the container.
                        // else
                        //     set the top margin to be the difference between the heights.
                        if (top < 0)
                            topMargin = layout.getHeight() < (top + height) ? -top : (height - layout.getHeight());
                    }

                    // set the margin.
                    ((ViewGroup.MarginLayoutParams) layout.getLayoutParams()).topMargin = topMargin;

                    // request Android to layout again.
                    listItem.requestLayout();
                }

            }
        });*/


        lvPhotos.setAdapter(aPhotos);
        fetchTimelineAsync();


        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTimelineAsync();
            }
        });

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


    }



    String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;

    public void fetchTimelineAsync() {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);


                // Remember to CLEAR OUT old items before appending in the new ones
                aPhotos.clear();
                JSONArray photosJSON = null;
                try {
                    photosJSON = response.getJSONArray("data");
                    for (int i = 0; i < photosJSON.length(); i++) {
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        //decode the attributes of the json into datamodel
                        InstagramPhoto photo = new InstagramPhoto();
                        photo.comments = new ArrayList<>();
                        photo.username = photoJSON.getJSONObject("user").getString("username");

                        if (photoJSON.getJSONObject("caption") != null) {
                            photo.caption = photoJSON.getJSONObject("caption").getString("text");
                        }


                        photo.imageURL = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        photo.likesCount = photoJSON.getJSONObject("likes").getInt("count");
                        photo.imageHeight = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");

                        photo.fullName = photoJSON.getJSONObject("user").getString("full_name");
                        photo.profilePicURL = photoJSON.getJSONObject("user").getString("profile_picture");

                        photo.created_time = photoJSON.getLong("created_time");

                        if (photoJSON.getJSONObject("comments") != null) {
                            JSONObject commentsJSON = photoJSON.getJSONObject("comments");
                            JSONArray comments = null;


                            try {
                                comments = commentsJSON.getJSONArray("data");
                                for (int j = 0; j < comments.length(); j++) {

                                    JSONObject commentJSON = comments.getJSONObject(j);
                                    InstagramComment comment = new InstagramComment();

                                    comment.comment = commentJSON.getString("text");
                                    comment.commentedBy = commentJSON.getJSONObject("from").getString("full_name");
                                    comment.commenterProfilePicUrl = commentJSON.getJSONObject("from").getString("profile_picture");
                                    photo.comments.add(comment);


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            photos.add(photo);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

                aPhotos.addAll(photos);
                aPhotos.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }
        });
    }
}
