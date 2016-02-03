package project.codepath.instagramclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {

    public static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";

    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotosAdapter aPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        //SEND OUT API REQUEST TO POPULAR PHOTOS
        photos = new ArrayList<>();

        aPhotos = new InstagramPhotosAdapter(this, photos);

        ListView lvPhotos = (ListView)findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(aPhotos);
        fetchPopularPhotos();
    }

    String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
    public void fetchPopularPhotos() {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                //Log.i("DEBUG", response.toString());

                JSONArray photosJSON = null;
                try {
                    photosJSON = response.getJSONArray("data");
                    for (int i = 0; i < photosJSON.length(); i++) {
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        //decode the attributes of the json into datamodel
                        InstagramPhoto photo = new InstagramPhoto();
                        photo.username = photoJSON.getJSONObject("user").getString("username");
                        photo.caption = photoJSON.getJSONObject("caption").getString("text");
                        photo.imageURL = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        photo.likesCount = photoJSON.getJSONObject("likes").getInt("count");
                        photo.imageHeight = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");

                        photos.add(photo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

                aPhotos.notifyDataSetChanged();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }
        });
    }
}
