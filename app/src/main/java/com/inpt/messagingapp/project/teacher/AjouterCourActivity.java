package com.inpt.messagingapp.project.teacher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.inpt.messagingapp.R;
import com.inpt.messagingapp.helpers.sqliteHelpers.SqliteConnector;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.Message;
import com.inpt.messagingapp.wrapper.models.User;

public class AjouterCourActivity extends AppCompatActivity {
    EditText title_input, description_input, file_input;
    Button add_button, cancel_button;
    Cour new_cour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_cour);

        title_input = findViewById(R.id.title_cour);
        description_input = findViewById(R.id.addDescription);
        file_input = findViewById(R.id.addfile);
        add_button = findViewById(R.id.ajouter);
        cancel_button = findViewById(R.id.cancel);
        new_cour = new Cour(null, null, null, null, null, description_input.getText().toString().trim(), title_input.getText().toString().trim(), file_input.getText().toString().trim());
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SqliteConnector myDB = new SqliteConnector(AjouterCourActivity.this);
                myDB.addLocale(new_cour);
            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
