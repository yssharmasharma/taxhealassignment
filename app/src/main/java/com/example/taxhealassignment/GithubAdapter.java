package com.example.taxhealassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class GithubAdapter  extends RecyclerView.Adapter<GithubAdapter.GithubViewHolder> {

   private Context context;
   private User data;
    public GithubAdapter(Context context,User data){

        this.context=context;
        this.data=data;
   }


    @NonNull
    @Override
    public GithubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_user_layout,parent,false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GithubViewHolder holder, int position) {
        final User user=data;
        holder.txtUser.setText(user.getLogin());
        Glide.with(holder.imgUser.getContext()).load(user.getAvatarUrl()).into(holder.imgUser);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,user.getLogin()+"was clicked",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class GithubViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUser;
        TextView txtUser;
        public GithubViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser=(ImageView)itemView.findViewById(R.id.imgUser);
            txtUser=(TextView) itemView.findViewById(R.id.txtUser);
        }
    }

}
