package com.example.projectgame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectgame.R;
import com.example.projectgame.model.User;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context mContext;
    private User[] mUsers;

    public UserAdapter(Context context, User[] users) {
        this.mContext = context;
        this.mUsers = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = mUsers[position];

        holder.fullNameTextView.setText(user.name);
        holder.scoreText.setText(user.score);



    }

    @Override
    public int getItemCount() {
        return mUsers.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fullNameTextView;
        TextView scoreText;
        ImageView genderImageView;
        TextView singleTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.fullNameTextView = itemView.findViewById(R.id.full_name_text_view);
            this.scoreText = itemView.findViewById(R.id.score_text);
            this.genderImageView = itemView.findViewById(R.id.image_view);
            this.singleTextView = itemView.findViewById(R.id.text_view);
        }
    }
}