package com.thedeveloperworldisyours.restclientmvp.presenter;

import android.os.AsyncTask;

import com.thedeveloperworldisyours.restclientmvp.model.LoginResponse;
import com.thedeveloperworldisyours.restclientmvp.utils.Constants;
import com.thedeveloperworldisyours.restclientmvp.view.LoginView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by javiergonzalezcabezas on 9/11/15.
 */
public class LoginPresenterImpl extends AsyncTask<String, Void, LoginResponse> implements LoginPresenter {

    private LoginView mLoginView;
    public LoginPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
    }

    @Override
    public void executeAsync(String url, String type) {
        this.execute(url, type);
    }
    @Override
    protected LoginResponse doInBackground(String... params) {

        final String username = params[0];
        final String password = params[1];

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.URL_LOGIN);
        stringBuilder.append("?username=");
        stringBuilder.append(username);
        stringBuilder.append("&password=");
        stringBuilder.append(password);

        return callClient(stringBuilder.toString(), Constants.TYPE);
    }

    public LoginResponse callClient(final String url, final String type) {

        LoginResponse responseModel = new LoginResponse();
        String responseJson;

        try {
            URL obj = new URL(url);

            HttpURLConnection connection = HttpURLConnectionFactory.getHttpURLConnection(obj);

            connection.setRequestProperty("X-Parse-Application-Id", Constants.PARSE_APPLICATION_ID);
            connection.setRequestProperty("X-Parse-REST-API-Key", Constants.PARSE_CLIENT_KEY);

            connection.setRequestMethod(type);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            responseJson = response.toString();


            responseModel.setCode(connection.getResponseCode());
            responseModel.setInfo(responseJson);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
            responseJson = e.toString();
            responseModel.setInterneterror(true);
        }

        return responseModel;
    }

    @Override
    protected void onPostExecute(LoginResponse response) {
        super.onPostExecute(response);

//        Log.v("onPostExecute", response.getCreatedAt().toString());

        if (response.isInterneterror()) {

            mLoginView.onGeneralError();

        } else {
            if (response.checkStatusCode(response.getCode())) {
                mLoginView.onRequestSuccess(response);
            } else {
                mLoginView.onRequestError(response);
            }

        }

    }
}
