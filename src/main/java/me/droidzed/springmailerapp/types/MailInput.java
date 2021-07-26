package me.droidzed.springmailerapp.types;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailInput {

    private String from;
    private String name;
    private String subject;
    private String body;
}
