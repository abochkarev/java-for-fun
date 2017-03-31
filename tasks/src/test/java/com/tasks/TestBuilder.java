package com.tasks;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBuilder {

    @Test
    public void test() {
        String name = "John";
        String patronymicName = "Johnovich";
        String surname = "Johnov";
        User user = User.newBuilder()
                .setName(name)
                .setPatronymicName(patronymicName)
                .setSurname(surname).build();
        assertEquals(name, user.getName());
        assertEquals(patronymicName, user.getPatronymicName());
        assertEquals(surname, user.getSurname());
    }

}
