package com.inpt.messagingapp.wrapper.views.teacher;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.adapters.CoursViewAdapter;
import com.inpt.messagingapp.wrapper.models.Cour;
import java.util.ArrayList;
import java.util.List;

public class ConsulterCoursTeacher extends AppCompatActivity {
    private static final String TAG = "ConsulterCoursTeacher";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Cour> cours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulter_cours_teacher);
        Button ajouter = findViewById(R.id.ajouter_button);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cours = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            Cour listItem =    new Cour(null,"hello world : "+i,"description "+i,null);
            cours.add(listItem);
        }
 ajouter.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {

         Log.d(TAG,"you clicked add cours");
     }
 });

        adapter = new CoursViewAdapter(cours, this);
        recyclerView.setAdapter(adapter);
    }


}
