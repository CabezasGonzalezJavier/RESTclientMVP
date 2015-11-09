package com.thedeveloperworldisyours.restclientmvp.view;

import android.content.Context;

/**
 * Created by javiergonzalezcabezas on 9/11/15.
 */
public interface LoginView {
    void showLoading(boolean state);
    void onRequestSuccess(Object object);
    void onRequestError(Object object);
    void onGeneralError();

    Context getContext();
}
