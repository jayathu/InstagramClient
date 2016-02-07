package project.codepath.instagramclient;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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

        TextView tvLikesCount = (TextView)convertView.findViewById(R.id.tvLikesCount);
        TextView tvCaption = (TextView)convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView)convertView.findViewById(R.id.ivPhotoBeautiful);
        TextView tvFullname = (TextView) convertView.findViewById(R.id.tvFullName);

        ImageView ivProfilePhoto = (ImageView)convertView.findViewById(R.id.ivProfilePhoto);
        ivProfilePhoto.setImageResource(0);

        tvLikesCount.setText(photo.likesCount + " Likes");
        tvCaption.setText(photo.caption);
        tvFullname.setText(photo.fullName);
        ivPhoto.setImageResource(0);

        /*
        com.squareup.picasso.Transformation transformation = new RoundedTransformationBuilder()
                                        .borderColor(Color.BLACK)
                                        .borderWidthDp(3)
                                        .cornerRadiusDp(30)
                                        .oval(false)
                                        .build();

        ImageView iv_profile_pic = (ImageView)convertView.findViewById(R.id.iv_profile_pic);
        Picasso.with(getContext())
                .load(photo.imageURL)
                .fit()
                .transform(transformation)
                .into(iv_profile_pic);

                */
        /*TextView tvCaption = (TextView)convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView)convertView.findViewById(R.id.ivPhoto);
        tvCaption.setText(photo.caption);
        ivPhoto.setImageResource(0);*/
        //Picasso.with(getContext()).load(photo.imageURL).into(ivPhoto);

        Picasso.with(getContext()).load(photo.imageURL).fit().centerCrop().placeholder(R.mipmap.ic_launcher).into(ivPhoto);

        Picasso.with(getContext()).load(photo.profilePicURL).fit().centerCrop().placeholder(R.mipmap.ic_launcher).into(ivProfilePhoto);

        return convertView;

    }
}
