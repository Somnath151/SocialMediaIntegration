package com.choudhary.socialmediaintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {


   private  CircleImageView profileImage;
   private  Button signOut;
   private  TextView email, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileImage = findViewById(R.id.profile_image);
        email = findViewById(R.id.display_email);
        name = findViewById(R.id.displayname_id);
        signOut = findViewById(R.id.signout_button);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email.setText(user.getEmail());
        Glide.with(this).load(user.getPhotoUrl()).into(profileImage);
        name.setText(user.getDisplayName());

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Signed out Succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });


    try {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account.getAccount() != null) {
            name.setText(account.getDisplayName());
            Glide.with(this)
                    .load(account.getPhotoUrl())
                    .into(profileImage);
        }
    }
    catch (Exception e){

       }






    }
}