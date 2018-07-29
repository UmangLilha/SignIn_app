package com.example.umang.ui_design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Intent_activity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_activity);
        tv=(TextView)findViewById(R.id.tv);
        Bundle data=getIntent().getExtras();
        String mess=data.getString("message");
        tv.setText("Hi "+mess);


    }
}
