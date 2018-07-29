package com.example.umang.ui_design;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread xyz=new Thread()
        {
            public void run()
            {
                try {
                    sleep(3000);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent abc=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(abc);
                }
            }




        };
        xyz.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
