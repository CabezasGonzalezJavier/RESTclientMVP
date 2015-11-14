package com.thedeveloperworldisyours.restclientmvp;

import com.thedeveloperworldisyours.restclientmvp.model.LoginResponse;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by javiergonzalezcabezas on 14/11/15.
 */
public class LoginResponseTest {

    @Test
    public void checkStatusSuccessfulTest() {
        LoginResponse mockloginResponse = new LoginResponse();
        int status = 200;
        Boolean bool = mockloginResponse.checkStatusCode(status);
        assertNotNull(bool);
        assertTrue(bool);
    }

    @Test
    public void checkStatusFailTest() {
        LoginResponse mockloginResponse = new LoginResponse();
        int status = 100;
        Boolean bool = mockloginResponse.checkStatusCode(status);
        assertNotNull(bool);
        assertFalse(bool);
    }

}
