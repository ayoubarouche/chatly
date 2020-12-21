package com.inpt.messagingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import android.widget.TextView;

import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.R;

public class CoursViewAdapter extends RecyclerView.Adapter<CoursViewAdapter.ViewHolder> {

   private List<Cour> cours ;
   private Context context;

    public CoursViewAdapter(List<Cour> cours, Context context) {
        this.cours = cours;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cour_card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cour cour = cours.get(position);
        holder.textViewHead.setText(cour.getTitre());
        holder.textViewDesc.setText(cour.getDescription());

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
}
