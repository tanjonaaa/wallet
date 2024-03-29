package org.wallet.Utilities;

import lombok.Getter;
import org.wallet.Utilities.Enums.PgQuery;

import java.util.List;

@Getter
public class QueryFormatterUtility {

    private final String tableName;
    private final List<String> columns;
    private static QueryFormatterUtility instance;

    private QueryFormatterUtility(String tableName, List<String> columns){
        this.tableName = tableName;
        this.columns = columns;
    }

    public static QueryFormatterUtility getInstance(String tableName, List<String> columns){
        return
                instance == null ? new QueryFormatterUtility(tableName, columns) : instance;
    }

    public String formatQuery(PgQuery query) {
        switch (query) {
            case SELECT -> {
                String columnsStr = String.join(", ", this.getColumns());
                return String.format(query.value, columnsStr, this.getTableName());
            }
            case INSERT -> {
                String columnsStr = String.join(", ", this.getColumns().subList(1, this.getColumns().size()));
                String placeholders = this.getColumns().subList(1, this.getColumns().size())
                        .stream()
                        .map(column -> "?")
                        .reduce((s1, s2) -> s1 + ", " + s2)
                        .orElse("");
                return String.format(query.value, this.getTableName(), columnsStr, placeholders);
            }
            case UPDATE -> {
                if (columns.isEmpty()) {
                    throw new IllegalArgumentException("Columns list must not be empty for UPDATE query");
                }
                String setClause = this.getColumns().subList(1, this.getColumns().size())
                        .stream()
                        .map(column -> column + " = ?")
                        .reduce((s1, s2) -> s1 + ", " + s2)
                        .orElse("");
                return String.format(query.value, this.getTableName(), setClause, this.getColumns().get(0));
            }
            default -> throw new UnsupportedOperationException("Unsupported PgQuery: " + query);
        }
    }
}
