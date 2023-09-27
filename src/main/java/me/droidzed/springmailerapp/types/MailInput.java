package me.droidzed.springmailerapp.types;

import java.util.Objects;

public class MailInput {
    private String from;
    private String name;
    private String subject;
    private String body;

    public MailInput(String from, String name, String subject, String body) {
        this.from = from;
        this.name = name;
        this.subject = subject;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof MailInput mailInput)) return false;
        if (this == o) return true;
        return Objects.equals(this.from, mailInput.getFrom()) && Objects.equals(this.name, mailInput.getName()) && Objects.equals(this.subject, mailInput.getSubject()) && Objects.equals(this.body, mailInput.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, name, subject, body);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
