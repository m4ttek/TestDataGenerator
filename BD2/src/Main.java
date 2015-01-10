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
			RODZAJE_SERWISOW_SLOWNIK_TABLE, RODZAJE_WYKROCZEN_SLOWNIK_TABLE, SERWISY_TABLE,
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
		Column rodzajeSerwisow = new Column("id", new DataTypeNumber(0, 14, 7, 0, true, false, true), 14);
		columnList.add(rodzajeSerwisow);
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
		table = new DefaultTable(KIEROWCY_TABLE, 80);
		columnList = new ArrayList<>();
		Column kierowcy = new Column("id", new DataTypeNumber(0, 80, 10, 0, true, false, true), 80);
		columnList.add(kierowcy);
		columnList.add(new Column("imie", new DataTypeLoadedFromFile("imiona.txt", false, false), 80));
		columnList.add(new Column("nazwisko", new DataTypeLoadedFromFile("nazwiska.txt", false, false), 80));
		Column wiek = new Column("wiek", new DataTypeNumber(20, 55, 35, 0, false, false, false), 80);
		columnList.add(wiek);
		columnList.add(new Column("pesel", new DataTypePESELFromAgeOrDate(wiek, true), 80));
		columnList.add(new Column("telefon", new DataTypeNumber(500000000, 800000000, 600500000, 0, true, false, false), 80));
		columnList.add(new Column("Kierowca_id", new DataTypeFK(kierowcy, false, true, true), 80));
		columnList.add(new Column("Uprawnienie_id", new DataTypeFK(uprawnienieId, false, false, false), 80));
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
		table = new DefaultTable(POJAZDY_TABLE, 2100);

		//pojazdy osobowe
		columnList = new ArrayList<>();
		Column rejestracjaOsobowe = new Column("Rejestracja", new DataTypeLiteral(null, "ABCDEFGHIJKLMNOPRSTUWXYZ1234567890", 7, 7,true, false), 100);
		columnList.add(rejestracjaOsobowe);
		columnList.add(new Column("vin", new DataTypeLiteral(null, "ABCDEFGHIJKLMNOPRSTUWXYZ1234567890", 14, 14,true, false), 100));
		columnList.add(new Column("masa_wlasna", new DataTypeNumber(500, 1000, 35, 0, false, false, false), 100));
		columnList.add(new Column("model", new DataTypeLoadedFromFile("pojazdy.txt", false, false), 100));
		columnList.add(new Column("marka", new DataTypeLoadedFromFile("pojazdy.txt", false, false), 100));
		columnList.add(new Column("rok_produkcji", new DataTypeNumber(1970, 2004, 1999, 0, false, false, false), 100));
		columnList.add(new Column("uciag", new DataTypeFillNull(), 100));
		columnList.add(new Column("masa_ladunku", new DataTypeFillNull(), 100));
		columnList.add(new Column("rodzaj_ladunku", new DataTypeFK(rodzajeLadunkow, false, false, false), 100));
		columnList.add(new Column("liczba_pasazerow", new DataTypeNumber(5, 15, 10, 0, false, true, false), 100));
		table.setColumns(columnList);

		// samochody dostawcze
		columnList = new ArrayList<>();
		Column rejestracjaDostawcze = new Column("Rejestracja", new DataTypeLiteral(null, "ABCDEFGHIJKLMNOPRSTUWXYZ1234567890", 7, 7,true, false), 1200);
		columnList.add(rejestracjaDostawcze);
		columnList.add(new Column("vin", new DataTypeLiteral(null, "ABCDEFGHIJKLMNOPRSTUWXYZ1234567890", 14, 14,true, false), 1200));
		columnList.add(new Column("masa_wlasna", new DataTypeNumber(1000, 2000, 35, 0, false, false, false), 1200));
		columnList.add(new Column("model", new DataTypeLoadedFromFile("pojazdy.txt", false, false), 1200));
		columnList.add(new Column("marka", new DataTypeLoadedFromFile("pojazdy.txt", false, false), 1200));
		columnList.add(new Column("rok_produkcji", new DataTypeNumber(1970, 2004, 1999, 0, false, false, false), 1200));
		columnList.add(new Column("uciag", new DataTypeNumber(10000, 50000, 20000, 0, false, true, false), 1200));
		columnList.add(new Column("masa_ladunku", new DataTypeNumber(10000, 200000, 80000, 0, false, true, false), 1200));
		columnList.add(new Column("rodzaj_ladunku", new DataTypeFK(rodzajeLadunkow, false, false, false), 1200));
		columnList.add(new Column("liczba_pasazerow", new DataTypeFillNull(), 1200));
		table.addAdditionalSection(columnList);

		// pojazdy ciężarowe
		columnList = new ArrayList<>();
		Column rejestracjaCiężarowe = new Column("Rejestracja", new DataTypeLiteral(null, "ABCDEFGHIJKLMNOPRSTUWXYZ1234567890", 7, 7,true, false), 800);
		columnList.add(rejestracjaCiężarowe);
		columnList.add(new Column("vin", new DataTypeLiteral(null, "ABCDEFGHIJKLMNOPRSTUWXYZ1234567890", 14, 14,true, false), 800));
		columnList.add(new Column("masa_wlasna", new DataTypeNumber(4000, 8000, 35, 0, false, false, false), 800));
		columnList.add(new Column("model", new DataTypeLoadedFromFile("pojazdy.txt", false, false), 800));
		columnList.add(new Column("marka", new DataTypeLoadedFromFile("pojazdy.txt", false, false), 800));
		columnList.add(new Column("rok_produkcji", new DataTypeNumber(1970, 2004, 1999, 0, false, false, false), 800));
		columnList.add(new Column("uciag", new DataTypeNumber(10000, 50000, 20000, 0, false, true, false), 800));
		columnList.add(new Column("masa_ladunku", new DataTypeNumber(20000, 500000, 80000, 0, false, true, false), 800));
		columnList.add(new Column("rodzaj_ladunku", new DataTypeFK(rodzajeLadunkow, false, false, false), 800));
		columnList.add(new Column("liczba_pasazerow", new DataTypeFillNull(), 800));
		table.addAdditionalSection(columnList);

		tableList.add(table);

		List<Column> wszystkiePojazdyRejestracje = new ArrayList<Column>();
		wszystkiePojazdyRejestracje.add(rejestracjaOsobowe);
		wszystkiePojazdyRejestracje.add(rejestracjaDostawcze);
		wszystkiePojazdyRejestracje.add(rejestracjaCiężarowe);

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
		table = new DefaultTable(PRZEGLADY_TECHNICZNE_TABLE, 2000);
		columnList = new ArrayList<>();
		columnList.add(new Column("numer", new DataTypeNumber(0, 2000, 100, 0, true, false, true), 2000));
		Column MOTDate = new Column("data", new DataTypeDate(simpleDateFormat, new Timestamp(25l * 365 * 24 * 60 * 60 * 1000l), new Timestamp(34l * 365 * 24 * 60 * 60 * 1000l), null, null, 0, 0, false, false), 2000);
		columnList.add(MOTDate);
		columnList.add(new Column("koszt_przegladu", new DataTypeNumber(50, 5000, 1000, 2, false, false, false), 2000));
		columnList.add(new Column("waznosc_przegladu", new DataTypeDate(simpleDateFormat, null, null, MOTDate,
				null, 365 * 24 * 60 * 60 * 1000l, 395 * 24 * 60 * 60 * 1000l, false, false), 2000));
		columnList.add(new Column("rezultat", new DataTypeLiteral(null, "PN", 1, 1, false, false), 2000));
		columnList.add(new Column("Pojazd_Rejestracja", new DataTypeFK(
				wszystkiePojazdyRejestracje, false, false, false), 2000));
		table.setColumns(columnList);
		tableList.add(table);

		/* Przyczepy
		    rejestracja varchar2(20)  NOT NULL,
		    masa_ladunku integer  NOT NULL,
		    rodzaj_ladunku integer  NOT NULL,
		    Pojazd_Rejestracja nvarchar2(15)  NULL,
		    CONSTRAINT Przyczepa_PK PRIMARY KEY (rejestracja)
		*/
		table = new DefaultTable(PRZYCZEPY_TABLE, 1000);
		columnList = new ArrayList<>();
		columnList.add(new Column("rejestracja", new DataTypeLiteral(null, "ABCDEFGHIJKLMNOPRSTUWXYZ1234567890", 7, 7,true, false),1000));
		columnList.add(new Column("masa_ladunku", new DataTypeNumber(10000, 200000, 80000, 0, false, true, false), 1000));
		columnList.add(new Column("rodzaj_ladunku", new DataTypeFK(rodzajeLadunkow, false, false, false), 1000));
		columnList.add(new Column("Pojazd_Rejestracja", new DataTypeFK(rejestracjaCiężarowe, false, false, false), 1000));
		table.setColumns(columnList);
		tableList.add(table);

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
		table = new DefaultTable(PRZEJAZDY_TABLE, 10000);
		columnList = new ArrayList<>();
		Column przejazd = new Column("id", new DataTypeNumber(0, 10000, 50, 0, true, false, true), 10000);
		columnList.add(przejazd);
		columnList.add(new Column("miejsce_poczatkowe", new DataTypeLoadedFromFile("miasta.txt", false, false), 10000));
		columnList.add(new Column("miejsce_docelowe", new DataTypeLoadedFromFile("miasta.txt", false, false), 10000));
		columnList.add(new Column("odleglosc", new DataTypeNumber(1, 500, 250, 0, false, false, false), 10000));
		columnList.add(new Column("ilosc_paliwa", new DataTypeNumber(1, 500, 250, 0, false, false, false), 10000));
		columnList.add(new Column("Pojazd_Rejestracja", new DataTypeFK(wszystkiePojazdyRejestracje, false, false, false), 10000));
		Column dataPrzejazdu = new Column("data", new DataTypeDate(simpleDateFormat, new Timestamp(24l * 365 * 24 * 60 * 60 * 1000l), new Timestamp(34l * 365 * 24 * 60 * 60 * 1000l),  null, null, 0, 0, false, false), 10000);
		columnList.add(dataPrzejazdu);
		columnList.add(new Column("wynagrodzenie", new DataTypeNumber(300, 5000, 1000, 0, false, false, false), 10000));
		columnList.add(new Column("priorytet", new DataTypeNumber(1, 5, 3, 0, false, false, false), 10000));
		columnList.add(new Column("status", new DataTypeFK(statusyPrzejazdow, false, false, false), 10000));
		columnList.add(new Column("Kierowca_id", new DataTypeFK(kierowcy, false, false, false), 10000));
		table.setColumns(columnList);
		tableList.add(table);

		/* Ladunki
		    id integer  NOT NULL,
		    masa integer  NOT NULL,
		    Przejazd_id integer  NOT NULL,
		    opis nvarchar2(255)  NOT NULL,
		    rodzaj_ladunku integer  NOT NULL,
		    CONSTRAINT Ladunek_PK PRIMARY KEY (id)
		*/
		table = new DefaultTable(LADUNKI_TABLE, 1000);
		columnList = new ArrayList<>();
		columnList.add(new Column("id", new DataTypeNumber(0, 1000, 50, 0, true, false, true), 1000));
		columnList.add(new Column("masa", new DataTypeNumber(100, 20000, 10000, 0, false, false, false), 1000));
		columnList.add(new Column("Przejazd_id", new DataTypeFK(przejazd, false, false, false), 1000));
		columnList.add(new Column("opis", new DataTypeLiteral(null, "QWE RTYUIOOPAS DFGHJKLZXCVBNMqwe rtyuiopasdfgh jklzxcvbnm ", 30, 100, false, false), 1000));
		columnList.add(new Column("rodzaj_ladunku", new DataTypeFK(rodzajeLadunkow, false, false, false), 1000));
		table.setColumns(columnList);
		tableList.add(table);

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
		table = new DefaultTable(MANDATY_TABLE, 2000);
		columnList = new ArrayList<>();
		columnList.add(new Column("id", new DataTypeNumber(0, 2000, 100, 0, true, false, true), 2000));
		Column mandatyPrzejazdFK = new Column("Przejazd_id", new DataTypeFK(przejazd, false, false, false), 2000);
		columnList.add(mandatyPrzejazdFK);
		columnList.add(new Column("data_wystawienia", new DataTypeDate(simpleDateFormat, null, null, dataPrzejazdu, mandatyPrzejazdFK, 4 * 60 * 60 * 1000l, 2 * 24 * 60 * 60 * 1000l, false, false), 2000));
		columnList.add(new Column("punkty_karne", new DataTypeNumber(1, 24, 6, 0, false, false, false), 2000));
		columnList.add(new Column("kwota", new DataTypeNumber(0, 1000, 500, 0, false, false, false), 2000));
		columnList.add(new Column("rodzaj_wykroczenia", new DataTypeFK(rodzajeWykroczen, false, false, false), 2000));
		table.setColumns(columnList);
		tableList.add(table);

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
		table = new DefaultTable(SERWISY_TABLE, 30000);

		// serwisy wykonywane dla przejazdów
		columnList = new ArrayList<>();
		Column serwisPodczasPrzejazdu = new Column("id", new DataTypeNumber(0, 10000, 100, 0, true, false, true), 10000);
		columnList.add(serwisPodczasPrzejazdu);
		Column serwisyPrzejazdFK = new Column("Przejazd_id", new DataTypeFK(przejazd, false, false, false), 10000);
		columnList.add(serwisyPrzejazdFK);
		Column dataSerwisuPrzejazdu = new Column("data_wykonania", new DataTypeDate(simpleDateFormat, null, null, dataPrzejazdu, serwisyPrzejazdFK, 24 * 60 * 60 * 1000l, 30 * 24 * 60 * 60 * 1000l, false, false), 10000);
		columnList.add(dataSerwisuPrzejazdu);
		columnList.add(new Column("miejsce_serwisowania", new DataTypeLoadedFromFile("miasta.txt", false, false), 10000));
		columnList.add(new Column("koszt_serwisu", new DataTypeNumber(100, 4000, 2000, 0, false, false, false), 10000));
		columnList.add(new Column("rodzaj_serwisu", new DataTypeFK(rodzajeSerwisow, false, false, false), 10000));
		columnList.add(new Column("Pojazd_Rejestracja1", new DataTypeFillNull(), 10000));
		table.setColumns(columnList);

		// serwisy wykonywane dla pojazdu
		columnList = new ArrayList<Column>();
		Column serwisDlaPojazdu = new Column("id", new DataTypeNumber(10000, 20000, 100, 0, true, false, true), 10000);
		columnList.add(serwisDlaPojazdu);
		Column serwisyPrzejazdNullFK = new Column("Przejazd_id", new DataTypeFillNull(), 10000);
		columnList.add(serwisyPrzejazdNullFK);
		Column dataSerwisuPojazdu = new Column("data_wykonania", new DataTypeDate(simpleDateFormat, null, null, dataPrzejazdu, null, 24 * 60 * 60 * 1000l, 30 * 24 * 60 * 60 * 1000l, false, false), 10000);
		columnList.add(dataSerwisuPojazdu);
		columnList.add(new Column("miejsce_serwisowania", new DataTypeLoadedFromFile("miasta.txt", false, false), 10000));
		columnList.add(new Column("koszt_serwisu", new DataTypeNumber(100, 4000, 2000, 0, false, false, false), 10000));
		columnList.add(new Column("rodzaj_serwisu", new DataTypeFK(rodzajeSerwisow, false, false, false), 10000));
		columnList.add(new Column("Pojazd_Rejestracja1", new DataTypeFK(wszystkiePojazdyRejestracje, false, false, false), 10000));
		table.addAdditionalSection(columnList);
		tableList.add(table);

		List<Column> wszystkieSerwisy = new ArrayList<Column>();
		wszystkieSerwisy.add(serwisPodczasPrzejazdu);
		wszystkieSerwisy.add(serwisDlaPojazdu);

		List<Column> wszystkieDatySerwisow = new ArrayList<Column>();
		wszystkieDatySerwisow.add(dataSerwisuPrzejazdu);
		wszystkieDatySerwisow.add(dataSerwisuPojazdu);

		/* Materialy_eksploatacyjne
		    id integer  NOT NULL,
		    koszt integer  NOT NULL,
		    data_zakupu date  NOT NULL,
		    ilosc integer  NOT NULL,
		    Serwis_id integer  NOT NULL,
		    typ integer  NOT NULL,
		    CONSTRAINT Material_eksploatacyjny_PK PRIMARY KEY (id)
		*/
		table = new DefaultTable(MATERIALY_EKSPLOATACYJNE_TABLE, 20000);
		columnList = new ArrayList<>();
		columnList.add(new Column("id", new DataTypeNumber(0, 20000, 100, 0, true, false, true), 20000));
		columnList.add(new Column("koszt", new DataTypeNumber(20, 10000, 1000, 0, false, false, false), 20000));
		Column materialySerwisFK = new Column("Serwis_id", new DataTypeFK(wszystkieSerwisy, false, false, false), 20000);
		columnList.add(materialySerwisFK);
		columnList.add(new Column("data_zakupu", new DataTypeDate(simpleDateFormat, null, null, dataSerwisuPojazdu, materialySerwisFK, 24 * 60 * 60 * 1000l, 30 * 24 * 60 * 60 * 1000l, false, false), 20000));
		columnList.add(new Column("ilosc", new DataTypeNumber(0, 2000, 1000, 0, false, false, false), 20000));
		columnList.add(new Column("typ", new DataTypeFK(typyMaterialow, false, false, false), 20000));
		table.setColumns(columnList);
		tableList.add(table);

		dataGeneratorManager.setTables(tableList);

		dataGeneratorManager.setTableListToGenerateData(TABLE_NAMES);

		dataGeneratorManager.generateDataFile("no.txt");
	}
}
