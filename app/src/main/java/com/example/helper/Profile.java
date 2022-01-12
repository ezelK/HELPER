package com.example.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Document;


public class Profile extends Fragment {

    private DatabaseReference dataReference;
    FirebaseFirestore fireFire= FirebaseFirestore.getInstance();
    ImageView image_profile;
    Menu options;
    TextView followers, performedJobs, calledHelpers,nameSurname,service,info,phoneNumber,mail;
    Button editProfile;
    FirebaseUser firebaseUser;
    String profileId;
    User user;


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        SharedPreferences pref= getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileId= pref.getString("id", "none");

        user = new User();
        image_profile= view.findViewById(R.id.image_profile);
        options= view.findViewById(R.menu.options_menu);
        followers= view.findViewById(R.id.txtFollowertCount);
        performedJobs= view.findViewById(R.id.txtJobsCount);
        calledHelpers= view.findViewById(R.id.txtHelpersCount);
        nameSurname= view.findViewById(R.id.txtProfileNameSurname);
        service= view.findViewById(R.id.txtService);
        info= view.findViewById(R.id.txtInfo);
        phoneNumber= view.findViewById(R.id.txtPhone);
        mail= view.findViewById(R.id.txtMailP);




        editProfile = view.findViewById(R.id.btnEditProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(),EditProfile.class);
                startActivity(intent);
            }
        });
        return view;
    }

}