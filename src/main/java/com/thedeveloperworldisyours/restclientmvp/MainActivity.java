package com.thedeveloperworldisyours.restclientmvp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thedeveloperworldisyours.restclientmvp.presenter.LoginPresenter;
import com.thedeveloperworldisyours.restclientmvp.presenter.LoginPresenterImpl;
import com.thedeveloperworldisyours.restclientmvp.utils.Utils;
import com.thedeveloperworldisyours.restclientmvp.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView,View.OnClickListener {

    private EditText mUser, mPassword;
    private Button mLoginButton;
    private View mLoading;
    private String mUsernameString, mPasswordString;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoginPresenter= new LoginPresenterImpl(this);
        initiateUI();

    }

    private void initiateUI() {
        mUser = (EditText) findViewById(R.id.activity_main_username_edit_text);
        mPassword = (EditText) findViewById(R.id.activity_main_password_edit_text);
        mLoginButton = (Button) findViewById(R.id.activity_main_login_button);
        mLoading = findViewById(R.id.activity_main_loading);
        mLoading.setVisibility(View.GONE);
        events();
    }

    private void events() {
        mLoginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_login_button:
                if (validate()) {
                    login();
                }
                break;
        }

    }

    public void login() {
        mLoginPresenter.executeAsync(mUsernameString, mPasswordString);
    }

    @Override
    public void showLoading(boolean state) {
        int visibility= (state)?(View.VISIBLE):(View.GONE);
        mLoading.setVisibility(visibility);
    }

    @Override
    public void onRequestSuccess(Object object) {
        showLoading(false);
        gotoUser();
    }

    @Override
    public void onRequestError(Object object) {
        showLoading(false);
        Toast.makeText(this, R.string.activity_main_on_request_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGeneralError() {
        showLoading(false);
        if (Utils.isOnline(this)){
            Toast.makeText(this, R.string.activity_main_general_error, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, R.string.activity_main_no_internet, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    private boolean validate() {

        mUsernameString = mUser.getText().toString().trim();
        mPasswordString = mPassword.getText().toString().trim();

        mUser.setError(null);
        mPassword.setError(null);
        if(mUsernameString.isEmpty())
        {
            mUser.setError(getString(R.string.activity_main_set_field));
            return false;
        }
        if(mPasswordString.isEmpty())
        {
            mPassword.setError(getString(R.string.activity_main_set_field));
            return false;
        }
        return true;
    }
    private void gotoUser() {
        startActivity(new Intent(this,UserActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
