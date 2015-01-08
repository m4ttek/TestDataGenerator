import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
			RODZAJE_SERWISOW_SLOWNIK_TABLE, RODZAJE_WYKROCZEN_SLOWNIK_TABLE,
			UPRAWNIENIA_TABLE, TYPY_MATERIALOW_SLOWNIK_TABLE, STATUSY_PRZEJAZDOW_SLOWNIK_TABLE);

	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public static void main(String[] args) {
		DataGeneratorManager dataGeneratorManager = new DataGeneratorManagerImpl();

		List<Table> tableList = new ArrayList<Table>();

		Table table = new DefaultTable(RODZAJE_LADUNKOW_SLOWNIK_TABLE, 3);
		List<Column> columnList = new ArrayList<>();
		Column rodzajeLadunkow = new Column("id", new DataTypeNumber(0, 3, 2, 0, true, false, true), 3);
		columnList.add(rodzajeLadunkow);
		columnList.add(new Column("opis", new DataTypeLoadedFromFile("rodzaje_ladunkow.txt", true, false), 3));
		table.setColumns(columnList);
		tableList.add(table);

		table = new DefaultTable(UPRAWNIENIA_TABLE, 4);
		columnList = new ArrayList<>();
		Column uprawnienieId = new Column("id", new DataTypeNumber(0, 4, 2, 0, true, false, true), 4);
		columnList.add(uprawnienieId);
		columnList.add(new Column("nazwa", new DataTypeLoadedFromFile("uprawnienia.txt", true, false), 4));
		columnList.add(new Column("Uprawnienie_id", new DataTypeFK(uprawnienieId, false, true, true), 4));
		table.setColumns(columnList);
		tableList.add(table);

		/* Typy_materialow_slownik
    	id integer  NOT NULL,
    	opis nvarchar2(255)  NOT NULL,
    	CONSTRAINT Typy_materialow_slownik_pk PRIMARY KEY (id)
		 */
		table = new DefaultTable(TYPY_MATERIALOW_SLOWNIK_TABLE, 6);
		columnList = new ArrayList<>();
		Column typyMaterialow = new Column("id", new DataTypeNumber(0, 6, 3, 0, true, false, true), 6);
		columnList.add(typyMaterialow);
		columnList.add(new Column("opis", new DataTypeLoadedFromFile("typy_materialow.txt", true, false), 6));
		table.setColumns(columnList);
		tableList.add(table);

		/* Statusy_przejazdow_slownik
		id integer  NOT NULL,
		opis nvarchar2(255)  NOT NULL,
		CONSTRAINT Statusy_przejazdow_slownik_pk PRIMARY KEY (id)
		 */
		table = new DefaultTable(STATUSY_PRZEJAZDOW_SLOWNIK_TABLE, 3);
		columnList = new ArrayList<>();
		Column statusyPrzejazdow = new Column("id", new DataTypeNumber(0, 3, 2, 0, true, false, true), 3);
		columnList.add(statusyPrzejazdow);
		columnList.add(new Column("opis", new DataTypeLoadedFromFile("statusy_przejazdow.txt", true, false), 3));
		table.setColumns(columnList);
		tableList.add(table);


		/* Rodzaje_wykroczen_slownik
    	id integer  NOT NULL,
    	opis nvarchar2(255)  NOT NULL,
    	CONSTRAINT Rodzaje_wykroczen_slownik_pk PRIMARY KEY (id)
		 */
		table = new DefaultTable(RODZAJE_WYKROCZEN_SLOWNIK_TABLE, 30);
		columnList = new ArrayList<>();
		Column rodzajeWykroczen = new Column("id", new DataTypeNumber(0, 30, 15, 0, true, false, true), 30);
		columnList.add(rodzajeWykroczen);
		columnList.add(new Column("opis", new DataTypeLoadedFromFile("rodzaje_wykroczen.txt", true, false), 30));
		table.setColumns(columnList);
		tableList.add(table);

		/* Rodzaje_serwisow_slownik
		id integer  NOT NULL,
		opis nvarchar2(255)  NOT NULL,
		CONSTRAINT Rodzaje_serwisow_slownik_pk PRIMARY KEY (id)
		 */
		table = new DefaultTable(RODZAJE_SERWISOW_SLOWNIK_TABLE, 14);
		columnList = new ArrayList<>();
		columnList.add(new Column("id", new DataTypeNumber(0, 14, 7, 0, true, false, true), 14));
		columnList.add(new Column("opis", new DataTypeLoadedFromFile("rodzaje_serwisow.txt", true, false), 14));
		table.setColumns(columnList);
		tableList.add(table);

		/* KIEROWCA
		id integer  NOT NULL,
	    pesel number  NOT NULL,
	    imie nvarchar2(30)  NOT NULL,
	    nazwisko nvarchar2(30)  NOT NULL,
	    wiek number  NOT NULL,
	    telefon number  NOT NULL,
	    Kierowca_id integer  NULL,
	    Uprawnienie_id integer  NOT NULL,
	    CONSTRAINT pesel_unique UNIQUE (pesel),
	    CONSTRAINT telefon_unique UNIQUE (telefon),
	    CONSTRAINT zmiennik CHECK ((id<>Kierowca_id)),
	    CONSTRAINT Kierowca_PK PRIMARY KEY (id)
		*/
		table = new DefaultTable(KIEROWCY_TABLE, 20);
		columnList = new ArrayList<>();
		Column id = new Column("id", new DataTypeNumber(0, 20, 10, 0, true, false, true), 20);
		columnList.add(id);
		columnList.add(new Column("imie", new DataTypeLoadedFromFile("imiona.txt", false, false), 20));
		columnList.add(new Column("nazwisko", new DataTypeLoadedFromFile("nazwiska.txt", false, false), 20));
		Column wiek = new Column("wiek", new DataTypeNumber(20, 55, 35, 0, false, false, false), 20);
		columnList.add(wiek);
		columnList.add(new Column("pesel", new DataTypePESELFromAgeOrDate(wiek, true), 20));
		columnList.add(new Column("telefon", new DataTypeNumber(500000000, 800000000, 600500000, 0, true, false, false), 20));
		columnList.add(new Column("Kierowca_id", new DataTypeFK(id, false, true, true), 20));
		columnList.add(new Column("Uprawnienie_id", new DataTypeFK(uprawnienieId, false, false, false), 20));
		table.setColumns(columnList);
		tableList.add(table);

		/* Pojazdy
		    Rejestracja nvarchar2(15)  NOT NULL,
		    vin nvarchar2(17)  NOT NULL,
		    masa_wlasna integer  NOT NULL,
		    model nvarchar2(50)  NOT NULL,
		    marka nvarchar2(50)  NOT NULL,
		    rok_produkcji char(4)  NOT NULL,
		    uciag integer  NULL,
		    masa_ladunku integer  NULL,
		    rodzaj_ladunku integer  NOT NULL,
		    liczba_pasazerow integer  NULL,
		    CONSTRAINT vin_unique UNIQUE (vin),
		    CONSTRAINT Pojazd_PK PRIMARY KEY (Rejestracja)
		 */
		table = new DefaultTable(POJAZDY_TABLE, 50);
		columnList = new ArrayList<>();
		Column rejestracja = new Column("Rejestracja", new DataTypeLiteral(null, "ABCDEFGHIJKLMNOPRSTUWXYZ1234567890", 7, 7,true, false), 50);
		columnList.add(rejestracja);
		columnList.add(new Column("vin", new DataTypeLiteral(null, "ABCDEFGHIJKLMNOPRSTUWXYZ1234567890", 14, 14,true, false), 50));
		columnList.add(new Column("masa_wlasna", new DataTypeNumber(10, 55, 35, 0, false, false, false), 50));
		columnList.add(new Column("model", new DataTypeLoadedFromFile("pojazdy.txt", false, false), 50));
		columnList.add(new Column("marka", new DataTypeLoadedFromFile("pojazdy.txt", false, false), 50));
		columnList.add(new Column("rok_produkcji", new DataTypeNumber(1970, 2014, 1999, 0, false, false, false), 50));
		columnList.add(new Column("uciag", new DataTypeNumber(10000, 50000, 20000, 0, false, true, false), 50));
		columnList.add(new Column("masa_ladunku", new DataTypeNumber(10000, 200000, 80000, 0, false, true, false), 50));
		columnList.add(new Column("rodzaj_ladunku", new DataTypeFK(rodzajeLadunkow, false, false, false), 50));
		columnList.add(new Column("liczba_pasazerow", new DataTypeNumber(5, 15, 10, 0, false, true, false), 50));
		table.setColumns(columnList);
		tableList.add(table);

		/* Przeglady_techniczne
    	numer integer  NOT NULL,
    	data date  NOT NULL,
    	waznosc_przegladu date  NOT NULL,
    	koszt_przegladu integer  NOT NULL,
    	rezultat char(1)  NOT NULL,
    	Pojazd_Rejestracja nvarchar2(15)  NOT NULL,
    	CONSTRAINT daty CHECK ((data<waznosc_przegladu)),
    	CONSTRAINT Przeglad_techniczny_PK PRIMARY KEY (numer)
		 */
		table = new DefaultTable(PRZEGLADY_TECHNICZNE_TABLE, 200);
		columnList = new ArrayList<>();
		columnList.add(new Column("numer", new DataTypeNumber(0, 200, 100, 0, true, false, true), 200));
		Column MOTDate = new Column("data", new DataTypeDate(simpleDateFormat, new Timestamp(100, 6, 0, 0, 0, 0, 0), new Timestamp(115, 0, 0, 0, 0, 0, 0), null, 0, 0, false, false), 200);
		columnList.add(MOTDate);
		columnList.add(new Column("koszt_przegladu", new DataTypeNumber(50, 5000, 1000, 2, false, false, false), 200));
		columnList.add(new Column("waznosc_przegladu", new DataTypeDate(simpleDateFormat, null, null, MOTDate,
				new Timestamp(1, 0, 0, 0, 0, 0, 0).getTime(), new Timestamp(1, 1, 0, 0, 0, 0, 0).getTime(), false, false), 200));
		columnList.add(new Column("rezultat", new DataTypeLiteral(null, "PN", 1, 1, false, false), 200));
		columnList.add(new Column("Pojazd_Rejestracja", new DataTypeFK(rejestracja, false, false, false), 200));
		table.setColumns(columnList);
		tableList.add(table);

		/* Przyczepy
		    rejestracja varchar2(20)  NOT NULL,
		    masa_ladunku integer  NOT NULL,
		    rodzaj_ladunku integer  NOT NULL,
		    Pojazd_Rejestracja nvarchar2(15)  NULL,
		    CONSTRAINT Przyczepa_PK PRIMARY KEY (rejestracja)
		*/
		table = new DefaultTable(PRZYCZEPY_TABLE, 50);
		columnList = new ArrayList<>();
		columnList.add(new Column("rejestracja", new DataTypeLiteral(null, "ABCDEFGHIJKLMNOPRSTUWXYZ1234567890", 7, 7,true, false),50));
		columnList.add(new Column("masa_ladunku", new DataTypeNumber(10000, 200000, 80000, 0, false, true, false), 50));
		columnList.add(new Column("Pojazd_Re", ,50));

		/* Przejazdy
		    id integer  NOT NULL,
		    miejsce_poczatkowe nvarchar2(255)  NOT NULL,
		    miejsce_docelowe nvarchar2(255)  NOT NULL,
		    odleglosc integer  NOT NULL,
		    ilosc_paliwa integer  NOT NULL,
		    Pojazd_Rejestracja nvarchar2(15)  NOT NULL,
		    data date  NOT NULL,
		    wynagrodzenie integer  NOT NULL,
		    priorytet integer  NOT NULL,
		    status integer  NOT NULL,
		    Kierowca_id integer  NOT NULL,
		    CONSTRAINT Przejazd_PK PRIMARY KEY (id)
		*/

		/* Ladunki
		    id integer  NOT NULL,
		    masa integer  NOT NULL,
		    Przejazd_id integer  NOT NULL,
		    opis nvarchar2(255)  NOT NULL,
		    rodzaj_ladunku integer  NOT NULL,
		    CONSTRAINT Ladunek_PK PRIMARY KEY (id)
		*/

		/* Mandaty
		    id integer  NOT NULL,
		    data_wystawienia date  NOT NULL,
		    punkty_karne number  NOT NULL,
		    kwota number(2,7)  NOT NULL,
		    Przejazd_id integer  NOT NULL,
		    rodzaj_wykroczenia integer  NOT NULL,
		    CONSTRAINT punkty_karne_nieujemne CHECK ((punkty_karne>=0) AND (punkty_karne<100)),
		    CONSTRAINT Mandat_PK PRIMARY KEY (id)
		*/

		/* Serwisy
		    id integer  NOT NULL,
		    data_wykonania date  NOT NULL,
		    miejsce_serwisowania nvarchar2(255)  NULL,
		    koszt_serwisu integer  NOT NULL,
		    rodzaj_serwisu integer  NOT NULL,
		    Przejazd_id integer  NULL,
		    Pojazd_Rejestracja1 nvarchar2(15)  NULL,
		    CONSTRAINT FKArc_3 CHECK (( ( ( Pojazd_Rejestracja1 IS NOT NULL ) AND ( Przejazd_id IS NULL ) ) OR ( ( Przejazd_id IS NOT NULL ) AND ( Pojazd_Rejestracja1 IS NULL ) ) )),
		    CONSTRAINT Serwis_PK PRIMARY KEY (id)
		*/

		dataGeneratorManager.setTables(tableList);

		dataGeneratorManager.setTableListToGenerateData(TABLE_NAMES);

		dataGeneratorManager.generateDataFile("no.txt");
	}
}
