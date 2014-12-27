import java.util.List;

/**
 * Interfejs dla klasy reprezentuj�cej tabel� i operacje, kt�re mo�na na niej wykonac do generacji danych.
 * 
 * @author Mateusz Kami�ski
 */
public interface Table {

	String getTableName();
	
	void setColumns(List<Column> columnList);
		
	void setColumnOptions(String columnName, DataType dataType, List<Constraint> constraintList);
	
	List<String> getGeneratedDataFromColumn(String columnName);
	
	String generateDataForTable();
}
