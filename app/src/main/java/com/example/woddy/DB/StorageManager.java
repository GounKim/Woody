package com.example.woddy.DB;

import static android.content.ContentValues.TAG;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StorageManager {
    private final FirebaseStorage stroage;
    private final StorageReference storageRef;
    private final FirestoreManager manager;

    public StorageManager()  {
        this.manager = new FirestoreManager();
        this.stroage = FirebaseStorage.getInstance();
        this.storageRef = stroage.getReference();
    }

    public void setProfileImage(String userNick, String uriPath) {
        String filename = userNick + "_profile.jpg"; // 파일명 생성: 사용자의 NickName_profile.jpg

        Uri file = Uri.fromFile(new File(uriPath));     // 절대경로(uri)를 file에 할당
        StorageReference riversRef = storageRef.child("UserProfileImages/" + filename);
        UploadTask uploadTask = riversRef.putFile(file);

        delPostingImade(userNick);  // 이미지가 존재하면 기존 이미지 삭제 후 진행할 수 있도록 삭제해준다.

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Uploading Image to stroage has failed!");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d(TAG, "Uploading Image to stroage has successed!");

                Map<String, Object> uriData = new HashMap<>();
                uriData.put("userImage", "UserProfileImages/" + filename);
                manager.updateUser(userNick, uriData);
            }
        });
    }

    public void delPostingImade(String fileUri) {
        StorageReference desertRef = storageRef.child(fileUri);

        desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "deleting Image in stroage has successed!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "deleting Image in stroage has failed!");
            }
        });
    }

    public void addPostingImage(String postingNum, ArrayList<String> uriPath) {
        String stroagePath = "BoardName/TagName/PostingImages/" + postingNum + "/";

        for (int index = 0; index < uriPath.size(); index++) {
            Uri file = Uri.fromFile(new File(uriPath.get(index)));     // 절대경로(uri)를 file에 할당
            StorageReference riversRef = storageRef.child(stroagePath + file.getLastPathSegment());
            UploadTask uploadTask = riversRef.putFile(file);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "Uploading Image to stroage has failed!");
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d(TAG, "Uploading Image to stroage has successed!");
                }
            });
        }
    }

    public void delPostingImage(String fileUri) {
        StorageReference desertRef = storageRef.child(fileUri);

        desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "deleting Image in stroage has successed!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "deleting Image in stroage has failed!");
            }
        });
    }
}