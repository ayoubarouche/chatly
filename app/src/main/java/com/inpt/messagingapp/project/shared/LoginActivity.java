package com.inpt.messagingapp.project.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.inpt.messagingapp.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView choice_other = findViewById(R.id.choice_other);
        choice_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToregister();
            }
        });
    }
    public void changeToregister(){
        Intent intent = new Intent(this , RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        this.finish();
    }
}
