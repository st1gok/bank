package ru.practicum.bank.transfer.models;

//import jakarta.validation.constraints.Email;

public class Message {

    private String caption;
    private String message;

    private Recipient recipient;

    public String getCaption() {
        return caption;
    }

    public Message setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public Message setRecipient(Recipient recipient) {
        this.recipient = recipient;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Message setMessage(String message) {
        this.message = message;
        return this;
    }

    public static class Recipient {

//        @Email
        private String email;

        private String firstName;
        private String lastName;

        public String getEmail() {
            return email;
        }

        public Recipient setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getFirstName() {
            return firstName;
        }

        public Recipient setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public Recipient setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
    }
}
