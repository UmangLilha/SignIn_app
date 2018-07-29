package com.example.umang.ui_design;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText name,password;
    Button search;
    DatabaseReference databaseReference;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name);
        //add=(Button)findViewById(R.id.add);
        search=(Button)findViewById(R.id.searchh);
        password=(EditText) findViewById(R.id.password);
        tv=(TextView)findViewById(R.id.tv);
        String text="Not having an account? Sign Up";
        SpannableString spannableString=new SpannableString(text);
        ForegroundColorSpan red=new ForegroundColorSpan(Color.RED);


        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent=new Intent(MainActivity.this,Signup_activity.class);
                startActivity(intent);
            }
        };

        spannableString.setSpan(clickableSpan,23,30,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv.setText(spannableString);
        tv.setMovementMethod(LinkMovementMethod.getInstance());



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData(name.getText().toString(),password.getText().toString());
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }



    public void searchData(final String names, final String passw)
    {

           Query query=FirebaseDatabase.getInstance().getReference("Data")
                   .orderByChild("name")
                   .equalTo(names);

           query.addListenerForSingleValueEvent(new ValueEventListener() {

               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {

                   if (dataSnapshot.exists()) {
                       // dataSnapshot is the "issue" node with all children with id 0

                       for (DataSnapshot user : dataSnapshot.getChildren()) {
                           // do something with the individual "issues"
                           Artist artist = user.getValue(Artist.class);

                           if (artist.getGeneres().toString().equals(passw)) {

                               Toast.makeText(getApplicationContext(), "matched", Toast.LENGTH_LONG).show();


                              Intent intent=new Intent(MainActivity.this,Intent_activity.class);
                              intent.putExtra("message",names);
                              startActivity(intent);
                           }
                           else {
                               Toast.makeText(getApplicationContext(), "Password is wrong", Toast.LENGTH_LONG).show();
                           }

                       }
                       name.setText(null);
                       password.setText(null);
                   } else {
                       Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                   }
               }

               @Override
               public void onCancelled(DatabaseError databaseError) {


               }
           });


    }
}
