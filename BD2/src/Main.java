import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	public static String KIEROWCY_TABLE = "Kierowcy";
	
	public static String LADUNKI_TABLE = "Ladunki";
	
	public static String MANDATY_TABLE = "Mandaty";
	
	public static String MATERIALY_EKSPLOATACYJNE_TABLE = "Materialy_eksploatacyjne";
	
	public static String POJAZDY_TABLE = "Pojazdy";
	
	public static String PRZEGLADY_TECHNICZNE_TABLE = "Przeglady_techniczne";
	
	public static String PRZEJAZDY_TABLE = "Przejazdy";
	
	public static String PRZYCZEPY_TABLE = "Przyczepy";
	
	public static String RODZAJE_LADUNKOW_SLOWNIK_TABLE = "Rodzaje_ladunkow_slownik";
	
	public static String RODZAJE_SERWISOW_SLOWNIK_TABLE = "Rodzaje_serwisow_slownik";
	
	public static String RODZAJE_WYKROCZEN_SLOWNIK_TABLE = "Rodzaje_wykroczen_slownik";
	
	public static String SERWISY_TABLE = "Serwisy";
	
	public static String STATUSY_PRZEJAZDOW_SLOWNIK_TABLE = "Statusy_przejazdow_slownik";
	
	public static String TYPY_MATERIALOW_SLOWNIK_TABLE = "Typy_materialow_slownik";
	
	public static String UPRAWNIENIA_TABLE = "Uprawnienia";
	
	public static List<String> TABLE_NAMES = Arrays.asList(KIEROWCY_TABLE,
			LADUNKI_TABLE, MANDATY_TABLE, MATERIALY_EKSPLOATACYJNE_TABLE,
			POJAZDY_TABLE, PRZEGLADY_TECHNICZNE_TABLE, PRZEJAZDY_TABLE,
			PRZYCZEPY_TABLE, RODZAJE_LADUNKOW_SLOWNIK_TABLE,
			RODZAJE_SERWISOW_SLOWNIK_TABLE, RODZAJE_WYKROCZEN_SLOWNIK_TABLE);
	
	public static void main(String[] args) {
		DataGeneratorManager dataGeneratorManager = new DataGeneratorManagerImpl();
		
		List<Table> tableList = new ArrayList<Table>();
		
		Table table = new DefaultTable(RODZAJE_LADUNKOW_SLOWNIK_TABLE, 3);
		List<Column> columnList = new ArrayList<>();
		columnList.add(new Column("id", new DataTypeNumber(0, 3, 2, 0, true, false, true), null));
		columnList.add(new Column("opis", new DataTypeLoadedFromFile("rodzaje_ladunkow.txt", true, false), null));
		table.setColumns(columnList);
		tableList.add(table);
		
		dataGeneratorManager.setTables(tableList);
		
		dataGeneratorManager.setTableListToGenerateData(TABLE_NAMES);
		
		dataGeneratorManager.generateDataFile("test.txt");
	}
}
