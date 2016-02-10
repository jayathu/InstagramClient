package project.codepath.instagramclient;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;

import java.util.ArrayList;
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
        if(photo.commentsCount > 0) {
            holder.tvCommentsCount.setText(photo.commentsCount + " Comments");
        }

        LinearLayout list = (LinearLayout) convertView.findViewById(R.id.list_comments);
        list.removeAllViews();

        for(InstagramComment comment : photo.comments)
        {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_comments, null);

            TextView tvCommentFullName = (TextView) view.findViewById(R.id.tv_comment_full_name);
            TextView tvCommentDesc = (TextView) view.findViewById(R.id.tv_comment_desc);
            ImageView ivCommentProfilePic = (ImageView)view.findViewById(R.id.iv_comment_profilePic);

            tvCommentFullName.setText(comment.commentedBy);
            tvCommentDesc.setText(comment.comment);

            Picasso.with(getContext()).load(comment.commenterProfilePicUrl).fit().centerInside().placeholder(R.drawable.icon).into(ivCommentProfilePic);

            list.addView(view);

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
        @Bind(R.id.tvCommentsCount)TextView tvCommentsCount;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }



    }

}
