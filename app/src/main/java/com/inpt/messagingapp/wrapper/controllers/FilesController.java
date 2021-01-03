package com.inpt.messagingapp.wrapper.controllers;

import android.net.Uri;
import android.widget.Toast;

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
                });
            }
        });


    }
    public interface OnAfterUploading{
        public void OnCallBack(String file_url);
    }
}
