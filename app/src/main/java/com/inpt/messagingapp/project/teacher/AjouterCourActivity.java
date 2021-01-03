package com.inpt.messagingapp.project.teacher;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.inpt.messagingapp.GlobalApplication;
import com.inpt.messagingapp.R;
import com.inpt.messagingapp.loadingDialog;
import com.inpt.messagingapp.project.shared.CoursActivity;
import com.inpt.messagingapp.wrapper.controllers.FilesController;
import com.inpt.messagingapp.wrapper.controllers.NotificationController;
import com.inpt.messagingapp.wrapper.controllers.teacher.TeacherCoursController;
import com.inpt.messagingapp.wrapper.models.Cour;

public class AjouterCourActivity extends AppCompatActivity {
    private static final int PICK_FILE = 1;
    int REQUEST_CODE = 12;
    EditText title_input, description_input, file_input;
    Button add_button, cancel_button;
    ImageView upFile;
    Uri uri;
    Cour new_cour;
    GlobalApplication app;
    loadingDialog loading_dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_cour);
        app = (GlobalApplication) getApplication();
        title_input = findViewById(R.id.addtitre);
        description_input = findViewById(R.id.addDescription);
        file_input = findViewById(R.id.addfile);
        add_button = findViewById(R.id.ajouter);
        cancel_button = findViewById(R.id.cancel);
        upFile = findViewById(R.id.imageView2);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_cour = new Cour(null, app.getUser().getIdUser(), null, null, null, description_input.getText().toString(), title_input.getText().toString(), file_input.getText().toString());
                if (uri != null) AddCour();
                else
                    Toast.makeText(getApplicationContext(), "please select a file", Toast.LENGTH_LONG).show();
            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CoursActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
        upFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
    }

    public void addCour(final Cour new_cour1) {
        app.setTeacherCoursController();
        app.setNotificationController();
        app.teacherCoursController.addCour(new_cour1, new TeacherCoursController.OnCourAdded() {
            @Override
            public void onCallBack(Cour cour) {
                new_cour = cour;
                app.getLocaldatabase().addLocale(cour);
                app.getNotificationController().registerToTopic(cour.getIdCour(), new NotificationController.OnTopicSubscribed() {
                    @Override
                    public void OnCallBack() {
                        loading_dialog.dismissdialog();
                        goToCours();
                    }

                    @Override
                    public void OnFailed() {
                        loading_dialog.dismissdialog();
                        confirmDialog();
                    }
                });
                loading_dialog.dismissdialog();

                goToCours();
            }

            @Override
            public void OnErreur() {
                loading_dialog.dismissdialog();
                confirmDialog();
            }
        });
        loading_dialog.startLoadingDialog("enregistrement du cour ....");
    }

    public void goToCours() {
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
        file_input.setText(fileName);
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

    public void AddCour() {
        loading_dialog = new loadingDialog(this);
        app.setFilesController();
        app.getFilesController().addToStorage(uri, "courses", new FilesController.OnAfterUploading() {
            @Override
            public void OnCallBack(String file_url) {
                new_cour.setFile(String.valueOf(file_url));
                Toast.makeText(getApplicationContext(), "le fichier est telecharger", Toast.LENGTH_LONG).show();

                addCour(new_cour);
            }

            @Override
            public void OnErreur() {
                loading_dialog.dismissdialog();
                confirmDialog();
            }
        });
        loading_dialog.startLoadingDialog(getString(R.string.msg_ajouter_cours));

    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Erreur");
        builder.setMessage(" problème de la connextion !  :");
        builder.setPositiveButton("d'accord", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }
}
