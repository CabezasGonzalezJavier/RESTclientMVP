package com.thedeveloperworldisyours.restclientmvp.presenter;

import com.thedeveloperworldisyours.restclientmvp.model.LoginResponse;

/**
 * Created by javiergonzalezcabezas on 9/11/15.
 */
public interface LoginPresenter {
    void executeAsync(String url, String type);
    LoginResponse callClient(final String url, final String type);
}
