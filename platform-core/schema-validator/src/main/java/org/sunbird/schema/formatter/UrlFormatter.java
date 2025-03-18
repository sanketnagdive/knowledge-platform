package org.sunbird.schema.formatter;

import io.github.pixee.security.HostValidator;
import io.github.pixee.security.Urls;
import org.leadpony.justify.api.InstanceType;
import org.leadpony.justify.spi.FormatAttribute;

import javax.json.JsonString;
import javax.json.JsonValue;
import java.net.URL;

public class UrlFormatter implements FormatAttribute {

    @Override
    public String name() {
        return "url";
    }

    @Override
    public InstanceType valueType() {
        return InstanceType.STRING;
    }

    @Override
    public boolean test(JsonValue value) {
        String str = ((JsonString) value).getString();
        try {
            //TODO: Change it to Head Call.
            Urls.create(str, Urls.HTTP_PROTOCOLS, HostValidator.DENY_COMMON_INFRASTRUCTURE_TARGETS).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
