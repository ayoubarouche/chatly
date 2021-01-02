package com.inpt.messagingapp.project.teacher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.helpers.sqliteHelpers.SqliteConnector;
import com.inpt.messagingapp.project.shared.CoursActivity;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.Message;
import com.inpt.messagingapp.wrapper.models.User;

import java.util.HashMap;

public class AjouterCourActivity extends AppCompatActivity {
    private static final int PICK_FILE = 1 ;
    private DatabaseReference databaseReference;
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
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserOne");
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
    public void FileUpload(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,PICK_FILE);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_FILE){
            if(resultCode == RESULT_OK){
                Uri FileUri = data.getData();
                StorageReference Folder = FirebaseStorage.getInstance().getReference().child("Files");
                final StorageReference file_name = Folder.child("file"+FileUri.getLastPathSegment());
                file_name.putFile(FileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        file_name.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("filelink", String.valueOf(uri));
                                databaseReference.setValue(hashMap);
                                Toast.makeText(AjouterCourActivity.this, "File Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }
    }

}
