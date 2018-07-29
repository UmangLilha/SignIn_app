package com.example.umang.ui_design;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Signup_activity extends AppCompatActivity {

    EditText usn,pass,npass;
    Button signup;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        usn=(EditText)findViewById(R.id.usn);
        pass=(EditText)findViewById(R.id.pass);
        npass=(EditText)findViewById(R.id.cpass);
        signup=(Button)findViewById(R.id.sign);
        databaseReference= FirebaseDatabase.getInstance().getReference("Data");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    update(usn.getText().toString(),pass.getText().toString());


            }
        });


    }


    public void update(final String names, final String pas)
    {
        if(TextUtils.isEmpty(names) || TextUtils.isEmpty(pas))
            Toast.makeText(getApplicationContext(),"Please fill the data",Toast.LENGTH_LONG).show();
        Query query=FirebaseDatabase.getInstance().getReference("Data")
                .orderByChild("name")
                .equalTo(names);

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0

                    Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_LONG).show();
                    usn.setText(null);
                    pass.setText(null);
                    npass.setText(null);

                } else {
                    if(pass.getText().toString().length()>=6) {
                        if (pass.getText().toString().equals(npass.getText().toString())) {
                            String id = databaseReference.push().getKey();
                            Artist artist = new Artist(id, names, pas);
                            databaseReference.child(id).setValue(artist);
                            Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_LONG).show();
                            usn.setText(null);
                            pass.setText(null);
                            npass.setText(null);
                            Intent intent = new Intent(Signup_activity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Passwords do not Match", Toast.LENGTH_LONG).show();
                            pass.setText(null);
                            npass.setText(null);

                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password should be of atleast 6 characters", Toast.LENGTH_LONG).show();
                        pass.setText(null);
                        npass.setText(null);
                    }
                    }


                }


            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });



    }
}
