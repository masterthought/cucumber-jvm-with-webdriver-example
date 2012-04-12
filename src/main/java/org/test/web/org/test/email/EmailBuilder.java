package org.test.web.org.test.email;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;

import static com.natpryce.makeiteasy.Property.newProperty;


public class EmailBuilder {

    public static final String DEFAULT_RECIPIENT = "test@test.com";
    public static final String DEFAULT_SUBJECT = "Default subject";
    public static final String DEFAULT_MAIL_BODY="Just testing...";

    public static final Property<Email, String> recipient = newProperty();

    public static final Property<Email, String> subject = newProperty();

    public static final Property<Email, String> body = newProperty();

    public static final Instantiator<Email> Email = new Instantiator<Email>() {
        public Email instantiate(PropertyLookup<Email> lookup) {
            Email email = new Email();
            email.setRecipient(lookup.valueOf(recipient, DEFAULT_RECIPIENT));
            return email;
        }
    };

}
