package com.inpt.messagingapp.project.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.inpt.messagingapp.R;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView choice_other = (TextView) findViewById(R.id.choice_other);
        choice_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToLogin();
            }
        });

    }

    public void changeToLogin(){
        Intent intent = new Intent(this , LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        this.finish();
    }
}
