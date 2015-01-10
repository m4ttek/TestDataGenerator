import java.util.List;

/**
 * Interfejs dla klasy reprezentuj�cej tabel� i operacje, kt�re mo�na na niej wykonac do generacji danych.
 *
 * @author Mateusz Kami�ski
 */
public interface Table {

	String getTableName();

	void setColumns(List<Column> columnList);

	void addAdditionalSection(List<Column> columnList);

	List<String> getGeneratedDataFromColumn(String columnName);

	String generateDataForTable();
}
