package com.thedeveloperworldisyours.restclientmvp.presenter;

import android.os.AsyncTask;
import android.util.Log;

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

    private LoginView loginView;
    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }




    @Override
    public void executeAsync(String url, String type) {
        this.execute(url, type);
    }
    @Override
    protected LoginResponse doInBackground(String... params) {

        final String url = params[0];
        final String type = params[1];

        return callClient(url, type);
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

//            int responseCode = 0;
//            responseCode = con.getResponseCode();


            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            responseJson = response.toString();


//            Log.v("Asyntask", String.valueOf(con.getResponseCode()));

            //con.getResponseCode() //set statusCode to Response obj
//            responseModel.setContent(connection.getContent()); // set the body to the T response
//            responseModel.setHeaders(connection.getHeaderFields());
            responseModel.setCode(connection.getResponseCode());
//            responseModel.setInfo(responseJson);
//            Log.v("Client",con.getResponseCode());
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

        Log.v("onPostExecute", response.getCreatedAt().toString());

        if (response.isInterneterror()) {

            loginView.onGeneralError();

        } else {
            if (response.checkStatusCode(response.getCode())) {
                loginView.onRequestSuccess(response);
            } else {
                loginView.onRequestError(response);
            }

        }

    }
}
