import java.util.List;

/**
 * Interfejs dla klasy reprezentuj¹cej tabelê i operacje, które mo¿na na niej wykonac do generacji danych.
 * 
 * @author Mateusz Kamiñski
 */
public interface Table {

	String getTableName();
	
	void setColumns(List<Column> columnList);
		
	void setColumnOptions(String columnName, DataType dataType, List<Constraint> constraintList);
	
	List<String> getGeneratedDataFromColumn(String columnName);
	
	String generateDataForTable();
}
