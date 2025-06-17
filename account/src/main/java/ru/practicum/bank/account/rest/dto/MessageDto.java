package ru.practicum.bank.account.rest.dto;

public class MessageDto {

    private String caption;
    private String message;

    private Recipient recipient;

    public String getCaption() {
        return caption;
    }

    public MessageDto setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessageDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public MessageDto setRecipient(Recipient recipient) {
        this.recipient = recipient;
        return this;
    }

    public static class Recipient {

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
