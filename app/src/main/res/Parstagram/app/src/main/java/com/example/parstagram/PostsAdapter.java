package com.example.parstagram;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_post_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAuthor;
        private ImageView ivPost;
        private TextView tvCaption;
        private TextView tvCreatedAt;
        private ImageView ivProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            ivPost = itemView.findViewById(R.id.ivPost);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            ivProfile = itemView.findViewById(R.id.ivProfilePhoto);
        }

        public void bind(Post post) {
            tvCaption.setText(post.getDescription());
            tvAuthor.setText(post.getUser().getUsername());
            tvCreatedAt.setText(post.getCreatedAt().toString());
            ParseFile image = post.getImage();
            ParseFile profileimage = ((User)post.getUser()).getProfilePhoto();
            if (image != null) {Glide.with(context).load(image.getUrl()).into(ivPost);}
            if (profileimage != null) {Glide.with(context).load(profileimage.getUrl()).circleCrop().into(ivProfile);}



            ivPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, PostDetailActivity.class);
                    //i.putExtra("post", Parcels.wrap(post));
                    i.putExtra("post", Parcels.wrap(post));
                    context.startActivity(i);
                }
            });
            ivProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity activity = (MainActivity) context;
                    activity.goToProfileTab((User)post.getUser());
                }
            });
        }
    }
}
