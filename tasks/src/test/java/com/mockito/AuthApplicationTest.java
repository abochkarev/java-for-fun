package com.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

interface AuthenticatorInterface {
    boolean authenticateUser(String username, String password) throws EmptyCredentialsException;

    void foo();
}

class AuthenticatorApplication implements AuthenticatorInterface {

    private AuthenticatorInterface authenticatorInterface;

    public AuthenticatorApplication(AuthenticatorInterface authenticatorInterface) {
        this.authenticatorInterface = authenticatorInterface;
    }

    @Override
    public boolean authenticateUser(String username, String password) throws EmptyCredentialsException {
        return authenticatorInterface.authenticateUser(username, password);
    }

    @Override
    public void foo() {
        authenticatorInterface.foo();
    }

}

class EmptyCredentialsException extends Exception {
    public EmptyCredentialsException() {
        super("Empty credentials!");
    }
}

@RunWith(MockitoJUnitRunner.class)
public class AuthApplicationTest {

    @Mock
    Stack<Long> stack;

    @Captor
    ArgumentCaptor<Long> captor;

    @Mock
    AuthenticatorInterface authenticatorMock;
    @InjectMocks
    AuthenticatorApplication authenticator;

    @Test(expected = EmptyCredentialsException.class)
    public void testAuthenticate() throws EmptyCredentialsException {
        String username = "java";
        String password = "s3cret";
        when(authenticator.authenticateUser(username, password)).thenReturn(false);

        authenticator.foo();
        boolean actual = authenticator.authenticateUser(username, password);
        assertFalse(actual);

        verify(authenticatorMock).authenticateUser(username, password);

        // this test will fail
        //verify(authenticatorMock).authenticateUser(username, "some other password");

        verify(authenticatorMock, times(1)).authenticateUser(username, password);
        verify(authenticatorMock, atLeastOnce()).authenticateUser(username, password);
        verify(authenticatorMock, atLeast(1)).authenticateUser(username, password);
        verify(authenticatorMock, atMost(1)).authenticateUser(username, password);

        InOrder inOrder = inOrder(authenticatorMock);
        inOrder.verify(authenticatorMock).foo();
        inOrder.verify(authenticatorMock).authenticateUser(username, password);

        verify(authenticatorMock, timeout(100).times(1)).authenticateUser(username, password);

        when(authenticatorMock.authenticateUser("", ""))
                .thenThrow(new EmptyCredentialsException());

        authenticator.authenticateUser("", "");


        when(authenticatorMock.authenticateUser(username, password)).thenReturn(true);

        actual = authenticator.authenticateUser(username, password);
        assertTrue(actual);
    }

    @Test
    public void simpleTest() {
        List<String> mockedList = mock(List.class);
        mockedList.add("string");
        when(mockedList.get(0)).thenReturn("string");
        assertEquals("string", mockedList.get(0));


        HashMap<String, Integer> mockedMap = spy(new HashMap<>());
        mockedMap.put("1", 1);
        when(mockedMap.get("2")).thenReturn(2);
        int actual = mockedMap.get("2");
        assertEquals(2, actual);
        stack.add(0L);

        verify(stack).add(captor.capture());
        assertEquals((Long)0L, captor.getValue());
    }
}
