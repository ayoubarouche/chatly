package com.inpt.messagingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Message;

import java.util.List;

public class ChatViewAdapter extends RecyclerView.Adapter<ChatViewAdapter.ViewHolder> {

   private List<Message> messages ;
   private Context context;
   private GlobalApplication application ;

    public ChatViewAdapter(List<Message> messages, Context context,GlobalApplication application) {
        this.messages = messages;
        this.context = context;
        this.application = application;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v ;
        switch (viewType){
            case 0 :
                v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.send_to_me_card_view, parent, false);
                    break;
            case 1 :
                v  = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.send_from_me_card_view, parent, false);
                    break;
            default: v=null;
                    break;
        }
        return new ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if(message.getFrom().equals(application.getUser().getIdUser()))
            return 1;
        return 0;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.textViewName.setText("ayoub : ");

        holder.textViewMessage.setText(message.getMessage());
    }
    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName;
        public TextView textViewInfo;
        public TextView textViewMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.send_frome);

            textViewMessage = (TextView) itemView.findViewById(R.id.message);
        }
    }
}
