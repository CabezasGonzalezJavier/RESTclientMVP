package com.thedeveloperworldisyours.restclientmvp;

import com.thedeveloperworldisyours.restclientmvp.model.LoginResponse;
import com.thedeveloperworldisyours.restclientmvp.presenter.HttpURLConnectionFactory;
import com.thedeveloperworldisyours.restclientmvp.presenter.LoginPresenter;
import com.thedeveloperworldisyours.restclientmvp.presenter.LoginPresenterImpl;
import com.thedeveloperworldisyours.restclientmvp.utils.Constants;
import com.thedeveloperworldisyours.restclientmvp.view.LoginView;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by javiergonzalezcabezas on 14/11/15.
 */
public class LoginPresenterTest {
    public LoginPresenter mLoginPresenter;
    private LoginView mLoginView;
    private HttpURLConnection mockConnection;

    String mUrl;
    String mType;

    @Before
    public void setup() {

        mLoginView = mock(LoginView.class);
        mLoginPresenter = new LoginPresenterImpl(mLoginView);

        mUrl = Constants.URL_LOGIN;
        mType = Constants.TYPE;

    }

    @Test
    public void setClientHTTPWithOnPostExecuteSuccess() {

        mockConnection = mock(HttpURLConnection.class);

        try {
            when(mockConnection.getInputStream()).thenReturn(new ByteArrayInputStream("Hello".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpURLConnectionFactory.setHttpURLConnection(mockConnection);

        LoginResponse response = mLoginPresenter.callClient(mUrl,mType);

        try {
            verify(mockConnection).setRequestMethod(mType);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        assertNotNull(response);
        assertFalse(response.isInterneterror());
        assertTrue(response.getInfo() != null);
        assertTrue(response.getInfo().equals("Hello"));
    }

    @Test
    public void setClientHTTPWithOnPostExecuteFailure() {

        mockConnection = mock(HttpURLConnection.class);

        try {
            when(mockConnection.getInputStream()).thenThrow(new IOException());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpURLConnectionFactory.setHttpURLConnection(mockConnection);



        LoginResponse response = mLoginPresenter.callClient(mUrl,mType);

        assertNotNull(response);
        assertTrue(response.isInterneterror());
        assertTrue(response.getInfo() == null);
    }

}
