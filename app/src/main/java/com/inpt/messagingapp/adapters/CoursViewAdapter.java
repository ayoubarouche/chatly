package com.inpt.messagingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import android.widget.TextView;
import android.widget.Toast;

import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.MainActivity;
import com.inpt.messagingapp.project.shared.ChatActivity;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.wrapper.models.UserType;

public class CoursViewAdapter extends RecyclerView.Adapter<CoursViewAdapter.ViewHolder> {

   private List<Cour> cours ;
   private Context context;
    private  GlobalApplication application ;
    public CoursViewAdapter(List<Cour> cours, Context context,GlobalApplication application) {
        this.cours = cours;
        this.context = context;
        this.application = application;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cour_card_view, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context , "you clicked a cours",Toast.LENGTH_LONG).show();

            goToPage();}
        });
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cour cour = cours.get(position);
        holder.textViewHead.setText(cour.getTitre());
        holder.textViewDesc.setText(cour.getDescription());
        application.setMessageController(cour);
    }

    @Override
    public int getItemCount() {
        return cours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDesc;


        public ViewHolder(View itemView) {
            super(itemView);
            textViewHead = (TextView) itemView.findViewById(R.id.header);
            textViewDesc = (TextView) itemView.findViewById(R.id.description);
        }
    }
    public void goToPage(){
        Intent intent = new Intent(context, ChatActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}
