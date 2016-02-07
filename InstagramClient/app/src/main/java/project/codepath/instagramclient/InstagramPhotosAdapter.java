package project.codepath.instagramclient;

import android.content.Context;
import android.text.format.DateUtils;
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
        TextView tvPostedSince = (TextView)convertView.findViewById(R.id.tvPostedSince);

        CharSequence val = DateUtils.getRelativeTimeSpanString(photo.created_time * 1000, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        tvPostedSince.setText(val);

        if(photo.comments != null && photo.comments.size() > 1) {

            TextView tvCommentedBy = (TextView) convertView.findViewById(R.id.tvCommentByUser);
            TextView tvComment = (TextView) convertView.findViewById(R.id.tvComment);

            TextView tvCommentsCount = (TextView)convertView.findViewById(R.id.tvCommentsCount);

            tvCommentedBy.setText(photo.comments.get(0).commentedBy);
            tvComment.setText(photo.comments.get(0).comment);

            tvCommentsCount.setText(photo.comments.size() + " Comments");

            TextView tvCommented2By = (TextView) convertView.findViewById(R.id.tvComment2ByUser);
            TextView tvComment2 = (TextView) convertView.findViewById(R.id.tv2Comment);
            tvCommented2By.setText(photo.comments.get(1).commentedBy);
            tvComment2.setText(photo.comments.get(1).comment);

        }

        Picasso.with(getContext()).load(photo.imageURL).fit().centerInside().placeholder(R.drawable.placeholder1).into(ivPhoto);


        Picasso.with(getContext()).load(photo.profilePicURL).fit().centerCrop().placeholder(R.drawable.placeholderwoman).into(ivProfilePhoto);

        return convertView;

    }

}
