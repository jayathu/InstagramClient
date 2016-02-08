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

import butterknife.Bind;
import butterknife.ButterKnife;

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

        ViewHolder holder;
        InstagramPhoto photo = getItem(position);

        if(convertView != null) {

            holder = (ViewHolder)convertView.getTag();

        }else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo_beautiful, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.ivProfilePhoto.setImageResource(0);



        holder.tvLikesCount.setText(photo.likesCount + " Likes");
        holder.tvCaption.setText(photo.caption);
        holder.tvFullname.setText(photo.fullName);
        holder.ivPhoto.setImageResource(0);

        CharSequence val = DateUtils.getRelativeTimeSpanString(photo.created_time * 1000, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        holder.tvPostedSince.setText(val);

        if(photo.comments != null && photo.comments.size() > 1) {



            holder.tvCommentedBy.setText(photo.comments.get(0).commentedBy);
            holder.tvComment.setText(photo.comments.get(0).comment);

            holder.tvCommentsCount.setText(photo.comments.size() + " Comments");

            holder.tvCommented2By.setText(photo.comments.get(1).commentedBy);
            holder.tvComment2.setText(photo.comments.get(1).comment);

        }

        Picasso.with(getContext()).load(photo.imageURL).fit().centerInside().placeholder(R.drawable.placeholder1).into(holder.ivPhoto);


        Picasso.with(getContext()).load(photo.profilePicURL).fit().centerCrop().placeholder(R.drawable.placeholderwoman).into(holder.ivProfilePhoto);

        return convertView;

    }

    static class ViewHolder {

        @Bind(R.id.tvLikesCount) TextView tvLikesCount;
        @Bind(R.id.ivPhotoBeautiful)ImageView ivPhoto;
        @Bind(R.id.tvCaption) TextView tvCaption;
        @Bind(R.id.tvFullName) TextView tvFullname;
        @Bind(R.id.ivProfilePhoto)ImageView ivProfilePhoto;
        @Bind(R.id.tvPostedSince)TextView tvPostedSince;
        @Bind(R.id.tvCommentByUser) TextView tvCommentedBy;
        @Bind(R.id.tvComment)TextView tvComment;
        @Bind(R.id.tvCommentsCount)TextView tvCommentsCount;
        @Bind(R.id.tvComment2ByUser)TextView tvCommented2By;
        @Bind(R.id.tv2Comment)TextView tvComment2;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
