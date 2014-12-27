import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Mateusz Kamiñski
 */
public class Column {
	
	private String columnName = "Default_column_name";
	
	private List<Constraint> constraintsList = new ArrayList<Constraint>();
	
	private DataType dataType;
	
	private List<String> generatedData;
	
	public Column(String columnName, DataType dataType, List<Constraint> constraintsList) {
		if (columnName != null) {
			this.columnName = columnName;
		}
		this.dataType = dataType;
		if (constraintsList != null) {
			this.constraintsList = constraintsList;
		}
		generatedData = new ArrayList<>();
	}
	
	public String getColumnName() {
		return columnName;
	}
	
	public void addColumnConstraint(Constraint constraint) {
		constraintsList.add(constraint);
	}
	
	public void setColumnConstraints(List<Constraint> constraintsList) {
		this.constraintsList = constraintsList;
	}
	
	public List<Constraint> getColumnConstraints() {
		return constraintsList;
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
	
	public String generateDataUnit() {
		if (dataType == null) {
			throw new RuntimeException("Error: data type for column with name " + columnName + " not set.");
		}
		System.out.println(this.columnName);
		String data = dataType.getData();
		generatedData.add(data);
		return data;
	}
}
