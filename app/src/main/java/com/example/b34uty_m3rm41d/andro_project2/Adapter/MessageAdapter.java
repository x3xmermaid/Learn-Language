package com.example.b34uty_m3rm41d.andro_project2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.b34uty_m3rm41d.andro_project2.MessageActivity;
import com.example.b34uty_m3rm41d.andro_project2.Model.Chat;
import com.example.b34uty_m3rm41d.andro_project2.Model.User;
import com.example.b34uty_m3rm41d.andro_project2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    public static final int MSG_TYPE_LEFT =0;
    public static final int MSG_TYPE_RIGHT =1;
    private Context uaContext;
    private List<Chat> uaChat;
    private String imageURL;
    FirebaseUser fUser;

    public MessageAdapter(Context uaContext, List<Chat> uaChat, String imageURL){
        this.uaContext = uaContext;
        this.uaChat = uaChat;
        this.imageURL = imageURL;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT){
            View view = LayoutInflater.from(uaContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);

        }else{
            View view = LayoutInflater.from(uaContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {

        Chat chat  = uaChat.get(position);

        holder.showMessage.setText(chat.getMessage());

        if(imageURL.equals("default")){
            holder.profile_Image.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(uaContext).load(imageURL).into(holder.profile_Image);

        }

        if(position == uaChat.size()-1){
            if(chat.isIsseen()){
                holder.txt_seen.setText("seen");

            }else{
                holder.txt_seen.setText("Delivered");
            }
        }else{
            holder.txt_seen.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return uaChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView showMessage;
        public ImageView profile_Image;

        public TextView txt_seen;

        public ViewHolder(View itemView) {
            super(itemView);

            showMessage = itemView.findViewById(R.id.showPesan);
            profile_Image = itemView.findViewById(R.id.profile_image);
            txt_seen = itemView.findViewById(R.id.txt_seen);
        }
    }


    @Override
    public int getItemViewType(int position) {
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        if(uaChat.get(position).getSender().equals(fUser.getUid())){
            return MSG_TYPE_RIGHT;

        }else {
            return MSG_TYPE_LEFT;

        }
    }
}
