package com.inpt.messagingapp.wrapper.controllers;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.inpt.messagingapp.project.teacher.AjouterCourActivity;

public class FilesController {
    FirebaseStorage firebaseStorage ;

    public FilesController(FirebaseStorage firebaseStorage) {
        this.firebaseStorage = firebaseStorage;
    }

    public void addToStorage(Uri uri, String where_to_save, final OnAfterUploading onAfterUploading){


        StorageReference Folder = firebaseStorage.getReference().child("Courses");
        final StorageReference file_name = Folder.child(where_to_save+"/"+uri.getLastPathSegment());
        file_name.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                file_name.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        onAfterUploading.OnCallBack(String.valueOf(uri));
                        }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        onAfterUploading.OnErreur();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onAfterUploading.OnErreur();
            }
        });


    }
    public long downloadFile(Context context, String fileName, String fileExtension, String url) {
        String destinationDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        return downloadmanager.enqueue(request);
    }
    public interface OnAfterUploading{
        public void OnCallBack(String file_url);
        void OnErreur();
    }
}
