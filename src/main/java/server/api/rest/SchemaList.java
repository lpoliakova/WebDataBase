package server.api.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

@XmlRootElement
public class SchemaList {

    @XmlElement
    private List<String> schemas;

    private SchemaList() {}

    public SchemaList(String[] schemas) {
        this.schemas = Arrays.asList(schemas);
    }

    public String[] getSchemas() {
        return schemas.toArray(new String[0]);
    }
}
