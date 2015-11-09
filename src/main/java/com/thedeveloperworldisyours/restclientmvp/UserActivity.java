package com.thedeveloperworldisyours.restclientmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }


    /**
     * this method was created for animation between activities
     */
    public void cameback()
    {
        finish();
        overridePendingTransition(R.anim.right, R.anim.left);
    }

    @Override
    public void onBackPressed()
    {
        cameback();
    }
}
