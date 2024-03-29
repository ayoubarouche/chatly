package com.inpt.messagingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.project.student.StudentCourOperationsActivity;
import com.inpt.messagingapp.project.teacher.TeacherCourOperationsActivity;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.UserType;

import java.util.List;

public class CoursViewAdapter extends RecyclerView.Adapter<CoursViewAdapter.ViewHolder> {

    private List<Cour> cours;
    private Context context;
    private GlobalApplication application;

    public CoursViewAdapter(List<Cour> cours, Context context, GlobalApplication application) {
        this.cours = cours;
        this.context = context;
        this.application = application;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cour_card_view, parent, false);
       if(application.getUser()!=null) v.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                TextView textViewCour = view.findViewById(R.id.id_of_the_cour);
                goToPage(textViewCour.getText().toString());
            }
        });
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cour cour = cours.get(position);
        holder.textViewHead.setText(cour.getTitre());
        holder.textViewDesc.setText(cour.getDescription());
        holder.textViewIdCour.setText(cour.getIdCour());

    }

    @Override
    public int getItemCount() {
        return cours.size();
    }

    public void goToPage(String id_of_the_cour) {
        Intent intent = new Intent(context, application.getUser().getType() == UserType.teacher ? TeacherCourOperationsActivity.class : StudentCourOperationsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("id_cour", id_of_the_cour);
        context.startActivity(intent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDesc;
        public TextView textViewIdCour;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewHead = itemView.findViewById(R.id.header);
            textViewDesc = itemView.findViewById(R.id.description);
            textViewIdCour = itemView.findViewById(R.id.id_of_the_cour);
        }
    }

}
