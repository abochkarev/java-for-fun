package com.mockito.voidmethod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;

interface Authenticator {
    void authenticate(String username, String password) throws NoAuthenticationException;
}

class NoAuthenticationException extends Exception {
    public NoAuthenticationException() {
        super("Couldn't authenticate");
    }
}

class AuthenticatorImpl implements Authenticator {

    private Authenticator authenticator;

    public AuthenticatorImpl(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public void authenticate(String username, String password) throws NoAuthenticationException {
        authenticator.authenticate(username, password);
    }
}

@RunWith(MockitoJUnitRunner.class)
public class AuthApplicationTest {

    @Mock
    Authenticator authenticatorMock;

    @InjectMocks
    AuthenticatorImpl authenticator;

    @Test(expected = NoAuthenticationException.class)
    public void testAuthentication() throws NoAuthenticationException {
        String username = "username";
        String password = "password";

        doThrow(new NoAuthenticationException())
                .when(authenticatorMock)
                .authenticate(username, password);

        authenticator.authenticate(username, password);
    }

}
