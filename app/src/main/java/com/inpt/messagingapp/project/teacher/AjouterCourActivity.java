package com.inpt.messagingapp.project.teacher;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.inpt.messagingapp.wrapper.controllers.FilesController;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;
import com.inpt.messagingapp.wrapper.models.Devoir;
import com.inpt.messagingapp.wrapper.models.Message;
import com.inpt.messagingapp.wrapper.models.User;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class AjouterCourActivity extends AppCompatActivity {
    private static final int PICK_FILE = 1 ;
    int REQUEST_CODE = 12;
    private DatabaseReference databaseReference;
    EditText title_input, description_input, file_input;
    Button add_button, cancel_button;
    ImageView upFile;
    Uri uri;
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
        upFile = findViewById(R.id.imageView2);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_cour = new Cour(null, app.getUser().getIdUser(), null, null, null,description_input.getText().toString(),title_input.getText().toString(),file_input.getText().toString());
              if(uri!=null) AddCour();
            else Toast.makeText(getApplicationContext(),"please select a file",Toast.LENGTH_LONG).show();
            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        upFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    showFileChooser();
            }});
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



    public void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        // Update with mime types
        intent.setType("*/*");


        // Only pick openable and local files. Theoretically we could pull files from google drive
        // or other applications that have networked files, but that's unnecessary for this example.
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        // REQUEST_CODE = <some-integer>
        startActivityForResult(intent, REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // If the user doesn't pick a file just return
        if (requestCode != REQUEST_CODE || resultCode != RESULT_OK) {
            return;
        }

        // Import the file
        this.uri = data.getData();
        importFile(uri);
    }

    public void importFile(Uri uri) {
        String fileName = getFileName(uri);

        // The temp file could be whatever you want
       Toast.makeText(getApplicationContext(),fileName, Toast.LENGTH_LONG).show();

        // Done!
    }

    /**
     * Obtains the file name for a URI using content resolvers. Taken from the following link
     * https://developer.android.com/training/secure-file-sharing/retrieve-info.html#RetrieveFileInfo
     *
     * @param uri a uri to query
     * @return the file name with no path
     * @throws IllegalArgumentException if the query is null, empty, or the column doesn't exist
     */
    private String getFileName(Uri uri) throws IllegalArgumentException {
        // Obtain a cursor with information regarding this uri
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            throw new IllegalArgumentException("Can't obtain file name, cursor is empty");
        }

        cursor.moveToFirst();

        String fileName = cursor.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME));

        cursor.close();

        return fileName;
    }
        public void AddCour(){
            app.setFilesController();
            app.getFilesController().addToStorage(uri, "courses", new FilesController.OnAfterUploading() {
                @Override
                public void OnCallBack(String file_url) {
                    new_cour.setFile(String.valueOf(file_url));
                    Toast.makeText(getApplicationContext(),"the file is added",Toast.LENGTH_LONG).show();

                    addCour(new_cour);
                }
            });
            Toast.makeText(getApplicationContext(),"uploading file...",Toast.LENGTH_LONG).show();

        }

}
