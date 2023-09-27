package me.droidzed.springmailerapp.types;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MailInput {
    private String from;
    private String name;
    private String subject;
    private String body;

    public Map<String, String> toMap() {
        final HashMap<String, String> mailInputMap = new HashMap<>();

        mailInputMap.put("from", from);
        mailInputMap.put("name", name);
        mailInputMap.put("subject", subject);
        mailInputMap.put("body", body);

        return mailInputMap;
    }
}
