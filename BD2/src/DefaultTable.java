import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

	List<List<Column>> additionalSections = new ArrayList<List<Column>>();

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
		System.out.println(columnNames);
		StringBuilder sb = new StringBuilder("\n-- Generated data for table: ");
		sb.append(tableName)
		  .append("\n\n");
		Map<String, List<String>> collectedData = new HashMap<String, List<String>>();
		for (Column column : columnList) {
			collectedData.put(column.getColumnName(), column.generateDataForColumn());
		}

		List<Column> currentColumnList = columnList;
		Iterator<List<Column>> iterator = additionalSections.iterator();
		for (int rowNumber = 0, relativeRowNumber = 0; rowNumber < numberOfRowsToGenerate; rowNumber++, relativeRowNumber++) {
			sb.append("INSERT INTO ")
			  .append(tableName)
			  .append(" ")
			  .append(columnNames)
			  .append(" VALUES (");
			for (Column column : currentColumnList) {
				List<String> list = collectedData.get(column.getColumnName());
				String data = list.get(relativeRowNumber);
				if (data.isEmpty()) {
					sb.append("null");
				} else {
					sb.append(data);
				}
				sb.append(", ");
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append(" );\n");
			if (!additionalSections.isEmpty() && currentColumnList.get(0).getNumberOfTuples() < relativeRowNumber + 2) {
				relativeRowNumber = 0;
				if (iterator.hasNext()) {
					currentColumnList = iterator.next();
					collectedData = new HashMap<String, List<String>>();
					System.out.println("generacjk");
					for (Column column : currentColumnList) {
						collectedData.put(column.getColumnName(), column.generateDataForColumn());
					}
				} else {
					System.out.println(sb.toString());
					return sb.toString();
				}
			}
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

	@Override
	public void addAdditionalSection(List<Column> columnList) {
		int i = 0;
		for(Column column : columnList) {
			if (!column.getColumnName().equals(columnList.get(i++).getColumnName())) {
				throw new RuntimeException("DefaultTable : additional section columns vary from standard column list!");
			}
		}
		additionalSections.add(columnList);
	}
}
