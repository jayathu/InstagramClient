package project.codepath.instagramclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jnagaraj on 2/2/16.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    //Constructor
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        InstagramPhoto photo = getItem(position);

        if(convertView==null){
            //convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo_beautiful, parent, false);


        }

        TextView tvCaption = (TextView)convertView.findViewById(R.id.tvCaptionBeautiful);
        ImageView ivPhoto = (ImageView)convertView.findViewById(R.id.ivPhotoBeautiful);
        tvCaption.setText(photo.caption);
        ivPhoto.setImageResource(0);

        /*TextView tvCaption = (TextView)convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView)convertView.findViewById(R.id.ivPhoto);
        tvCaption.setText(photo.caption);
        ivPhoto.setImageResource(0);*/
        //Picasso.with(getContext()).load(photo.imageURL).into(ivPhoto);

        Picasso.with(getContext()).load(photo.imageURL).fit().centerCrop().placeholder(R.mipmap.ic_launcher).into(ivPhoto);

        return convertView;

    }
}
