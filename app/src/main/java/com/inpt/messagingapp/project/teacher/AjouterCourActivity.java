package com.inpt.messagingapp.project.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.helpers.sqliteHelpers.SqliteConnector;
import com.inpt.messagingapp.project.shared.CoursActivity;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.Message;
import com.inpt.messagingapp.wrapper.models.User;

public class AjouterCourActivity extends AppCompatActivity {
    EditText title_input, description_input, file_input;
    Button add_button, cancel_button;
    Cour new_cour;
    GlobalApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_cour);
        app = (GlobalApplication)getApplication();
        title_input = findViewById(R.id.addtitre);
        description_input = findViewById(R.id.addDescription);
        file_input = findViewById(R.id.addfile);
        add_button = findViewById(R.id.ajouter);
        cancel_button = findViewById(R.id.cancel);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_cour = new Cour(null, app.getUser().getIdUser(), null, null, null,description_input.getText().toString(),title_input.getText().toString(),file_input.getText().toString());
                addCour(new_cour);

            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void addCour(Cour new_cour1){
        app.setTeacherCoursController();
        app.teacherCoursController.addCour(new_cour1, new TeacherCoursController.OnCourAdded() {
            @Override
            public void onCallBack(Cour cour) {
                new_cour = cour;
                /*SqliteConnector myDB = new SqliteConnector(AjouterCourActivity.this);
                myDB.addLocale(new_cour);
                */
                Toast.makeText(getApplicationContext() ,"the cour is added cour_id is : "+cour.getIdCour(),Toast.LENGTH_LONG).show();
                goToCours();
            }
        });
        Toast.makeText(getApplicationContext() ,"we are adding the cours",Toast.LENGTH_LONG).show();

    }
    public void goToCours(){
        Intent intent = new Intent(this, CoursActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        this.finish();
    }

}
