package shared;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.*;

public class Entry implements Serializable{
    @XmlElement
    private Map<Attribute, String> values = new HashMap<>();

    private Entry() {}

    public static Entry create(Map<Attribute, String> values) {
        if (!DatabaseTypesValidator.validate(values)) {
            throw new IllegalArgumentException("entry values do not correspond to their types");
        }
        return new Entry(values);
    }

    private Entry(Map<Attribute, String> values) {
        this.values = values;
    }

    public Set<Attribute> getAttributes() {
        return values.<Attribute>keySet();
    }

    public Collection<String> getValues() {
        return values.<String>values();
    }

    public Map<Attribute, String> getEntry() {
        return values;
    }

    public String getValueByAttribute(Attribute attribute){
        return values.get(attribute);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        return values != null ? values.equals(entry.values) : entry.values == null;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }
}
