package com.tasks;

class User {
    private String name;
    private String patronymicName;
    private String surname;

    private User() {

    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public String getName() {
        return name;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public String getSurname() {
        return surname;
    }

    public class Builder {

        private Builder() {

        }

        public Builder setName(String name) {
            User.this.name = name;
            return this;
        }

        public Builder setPatronymicName(String patronymicName) {
            User.this.patronymicName = patronymicName;
            return this;
        }

        public Builder setSurname(String surname) {
            User.this.surname = surname;
            return this;
        }

        public User build() {
            return User.this;
        }

    }

}
