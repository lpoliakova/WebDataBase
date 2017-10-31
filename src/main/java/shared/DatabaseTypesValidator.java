package shared;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

class DatabaseTypesValidator {
    static boolean validateEntry(Entry entry) {
        for (Map.Entry<Attribute, String> value : entry.getEntry().entrySet()) {
            if (!validate(value.getKey(), value.getValue())) {
                return false;
            }
        }
        return true;
    }

    static boolean validate(Map<Attribute, String> values) {
        for (Map.Entry<Attribute, String> value : values.entrySet()) {
            if (!validate(value.getKey(), value.getValue())) {
                return false;
            }
        }
        return true;
    }

    static boolean validate(Attribute attribute, String value) {
        if (attribute.getType() == DatabaseTypes.INT) {
            return validateInt(value);
        } else if (attribute.getType() == DatabaseTypes.REAL) {
            return validateReal(value);
        } else if (attribute.getType() == DatabaseTypes.JPEG) {
            return validateJpeg(value);
        } else if (attribute.getType() == DatabaseTypes.HTML) {
            return validateHtml(value);
        } else if (attribute.getType() == DatabaseTypes.CHAR) {
            return true;
        }
        throw new IllegalArgumentException("Type " + attribute.getType() + " is not supported");
    }

    private static boolean validateInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private static boolean validateReal(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private static boolean validateJpeg(String value) {
        Path path = Paths.get(value);
        return path.isAbsolute() && value.endsWith(".jpeg");
    }

    private static boolean validateHtml(String value) {
        Path path = Paths.get(value);
        if (path.isAbsolute() && value.endsWith(".html")) {
            return true;
        }
        URI uri;
        try {
            uri = URI.create(value);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return uri.isAbsolute() && value.endsWith(".html");
    }
}
