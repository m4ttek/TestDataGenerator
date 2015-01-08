import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementacja domy�lnej tabeli generuj�cej odpowiednie dla niej dane.
 *
 * @author Mateusz Kami�ski
 */
public class DefaultTable implements Table {

	private final String tableName;

	List<Column> columnList;

	private int numberOfRowsToGenerate;

	public DefaultTable(String tableName, int numberOfRowsToGenerate) {
		this.tableName = tableName;
		this.numberOfRowsToGenerate = numberOfRowsToGenerate;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setColumns(List<Column> columnList) {
		this.columnList = columnList;
	}

	@Override
	public void setColumnOptions(String columnName, DataType dataType,
			List<Constraint> constraintList) {
		for (Column column : columnList) {
			if (column.getColumnName().equalsIgnoreCase(columnName)) {
				//columnToBeChanged = column;
			}
		}
	}

	@Override
	public List<String> getGeneratedDataFromColumn(String columnName) {
		for (Column column : columnList) {
			if (column.getColumnName().equalsIgnoreCase(columnName)) {
				return column.getGeneratedData();
			}
		}
		return null;
	}

	@Override
	public String generateDataForTable() {
		String columnNames = prepareColumnNames();

		StringBuilder sb = new StringBuilder("\n-- Generated data for table: ");
		sb.append(tableName)
		  .append("\n\n");
		Map<String, List<String>> collectedData = new HashMap<String, List<String>>();
		for (Column column : columnList) {
			collectedData.put(column.getColumnName(), column.generateDataForColumn());
		}

		for (int rowNumber = 0; rowNumber < numberOfRowsToGenerate; rowNumber++) {
			sb.append("INSERT INTO ")
			  .append(tableName)
			  .append(" ")
			  .append(columnNames)
			  .append(" VALUES (");
			for (Column column : columnList) {
				List<String> list = collectedData.get(column.getColumnName());
				String data = list.get(rowNumber);
				if (data.isEmpty()) {
					sb.append("null");
				} else {
					sb.append(data);
				}
				sb.append(", ");
				System.out.println(column.getColumnName());
			}
			System.out.println(sb.toString());
			sb.delete(sb.length() - 2, sb.length());
			sb.append(" );\n");
		}
		return sb.toString();
	}

	protected String prepareColumnNames() {
		StringBuilder sb = new StringBuilder("( ");
		for (Column column : columnList) {
			sb.append(column.getColumnName())
			  .append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(" )");
		return sb.toString();
	}
}
