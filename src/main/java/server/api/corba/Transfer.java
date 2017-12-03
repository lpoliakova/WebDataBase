package server.api.corba;

import server.api.corba.generated.MapEntry;
import server.api.corba.generated.TransferSchema;
import server.api.corba.generated.TransferTable;
import shared.*;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Transfer {

    public static TransferSchema fromSchema(Schema schema) {
        return new TransferSchema(schema.getName(),
                schema.getTables().values().stream().map(Transfer::fromTable).toArray(TransferTable[]::new));
    }

    public static Schema toSchema(TransferSchema transfer) {
        Schema schema = new Schema(transfer.name);
        Arrays.stream(transfer.tables).map(Transfer::toTable).forEach(t -> schema.addTable(t.getName(), t));
        return schema;
    }

    public static TransferTable fromTable(Table table) {
        return new TransferTable(table.getName(),
                table.getAttributes().stream().map(Attribute::toString).toArray(String[]::new),
                table.getEntries().stream().map(Transfer::fromEntry).toArray(MapEntry[][]::new));
    }

    private static MapEntry[] fromEntry(Entry entry) {
        return entry.getEntry().entrySet().stream().map(e -> new MapEntry(e.getKey().toString(), e.getValue())).toArray(MapEntry[]::new);
    }

    public static Table toTable(TransferTable transfer) {
        Table table = new Table(transfer.name,
                Arrays.stream(transfer.attributes).map(Transfer::toAttribute).collect(Collectors.toSet()));
        table.addAllEntries(Arrays.stream(transfer.entries).map(Transfer::toEntry).collect(Collectors.toList()));
        return table;
    }

    private static Attribute toAttribute(String description) {
        return new Attribute(description.substring(description.indexOf('\'') + 1, description.lastIndexOf('\'')),
                DatabaseTypes.valueOf(description.substring(description.lastIndexOf(' ') + 1, description.length())));
    }

    private static Entry toEntry(MapEntry[] map) {
        return Entry.create(Arrays.stream(map).collect(Collectors.toMap(m  -> toAttribute(m.key), m -> m.value)));
    }
}
