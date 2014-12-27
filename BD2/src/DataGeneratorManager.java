import java.util.List;

/**
 * Intefejs generatora danych dla bazy danych.
 * 
 * @author Mateusz Kami�ski
 */
public interface DataGeneratorManager {
	
	void generateDataFile(String fileName);
	
	void setTableListToGenerateData(List<String> tableList);
	
	void setTables(List<Table> tableList);
}
