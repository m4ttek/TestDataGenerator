import java.util.List;

/**
 *
 * @author Mateusz Kami�ski
 */
public class Column {

	private String columnName = "Default_column_name";

	private DataType dataType;

	private List<String> generatedData;

	private int numberOfTuples;

	public Column(String columnName, DataType dataType, int numberOfTuples) {
		this.numberOfTuples = numberOfTuples;
		if (columnName != null) {
			this.columnName = columnName;
		}
		this.dataType = dataType;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public DataType getColumnDataType() {
		return dataType;
	}

	public List<String> getGeneratedData() {
		return generatedData;
	}

	public List<String> generateDataForColumn() {
		if (dataType == null) {
			throw new RuntimeException("Error: data type for column with name " + columnName + " not set.");
		}
		List<String> data = dataType.getData(numberOfTuples);
		generatedData = data;
		return data;
	}
}
