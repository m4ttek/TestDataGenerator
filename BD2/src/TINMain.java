import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mateusz Kaminski
 *
 */
public class TINMain {

	public static final String USER_TABLE = "USER";

	public static final String ROOM_TABLE = "ROOM";

	public static final String RESERVATION_TABLE = "RESERVATION";

	public static final String MESSAGE_TABLE = "MESSAGE";

	public static final String ROLA_TABLE = "ROLA";

	public static final String TYPEROOM_TABLE = "TYPEROOM";

	public static final String STATUS_TABLE = "STATUS";


	/*
	 * Tabelki Many to Many
	 */
	public static final String RESERVATION_ROOM_TABLE = "RESERVATION_ROOM";
	public static final String USER_ROLE_TABLE = "USER_ROLE";
	public static final String USER_MESSAGE_TABLE = "USER_MESSAGE";


	public static List<String> TABLE_NAMES = Arrays.asList(USER_TABLE,
			ROOM_TABLE, RESERVATION_TABLE, MESSAGE_TABLE, ROLA_TABLE,
			TYPEROOM_TABLE, STATUS_TABLE, RESERVATION_ROOM_TABLE, USER_ROLE_TABLE, USER_MESSAGE_TABLE);

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


	public static void main(String[] args) {
		DataGeneratorManager dataGeneratorManager = new DataGeneratorManagerImpl();

		List<Table> tableList = new ArrayList<Table>();

		Table table = new DefaultTable(ROLA_TABLE, 3);
		List<Column> columnList = new ArrayList<Column>();
		Column rola = new Column("ID_ROLA", new DataTypeNumber(100, 400, 200, 0, true, false, true), 3);
		columnList.add(rola);
		columnList.add(new Column("NAME", new DataTypeLoadedFromFile("roles.txt", true, false), 3));
		table.setColumns(columnList);
		tableList.add(table);

		table = new DefaultTable(TYPEROOM_TABLE, 4);
		columnList = new ArrayList<Column>();
		Column roomType = new Column("ID_TYPE", new DataTypeNumber(100, 500, 300, 0, true, false, true), 4);
		columnList.add(roomType);
		columnList.add(new Column("NAME", new DataTypeLoadedFromFile("room_types.txt", true, false), 4));
		columnList.add(new Column("DESCRIPTION", new DataTypeLiteral(null, "abcd efgh ijklmno prstuw xyz ABCDEFGH IJKMLNOPRSTUWZ", 30, 100, false, false), 4));
		table.setColumns(columnList);
		tableList.add(table);

		table = new DefaultTable(STATUS_TABLE, 2);
		columnList = new ArrayList<Column>();
		Column status = new Column("ID_STATUS", new DataTypeNumber(100, 300, 300, 0, true, false, true), 2);
		columnList.add(status);
		columnList.add(new Column("DESCRIPTION", new DataTypeLoadedFromFile("states.txt", true, false), 2));
		table.setColumns(columnList);
		tableList.add(table);

		table = new DefaultTable(USER_TABLE, 50);
		columnList = new ArrayList<Column>();
		Column user = new Column("ID", new DataTypeNumber(1, 51, 25, 0, true, false, true), 50);
		columnList.add(user);
		columnList.add(new Column("NAME", new DataTypeLoadedFromFile("imiona.txt", false, false), 50));
		columnList.add(new Column("SURNAME", new DataTypeLoadedFromFile("nazwiska.txt", false, false), 50));
		columnList.add(new Column("MAIL", new DataTypeLiteral(Arrays.asList("m4ttekaminski@gmail.com", "123fokus@gmail.com", ""), null, 10, 40, false, false), 50));
		columnList.add(new Column("LOGIN", new DataTypeLiteral(null, "abcd efgh ijklmno prstuw xyz ABCDEFGH IJKMLNOPRSTUWZ", 8, 30, true, false), 50));
		columnList.add(new Column("BLOCKED", new DataTypeLiteral(null, "TF", 1, 1, false, false), 50));
		columnList.add(new Column("VERIFIED", new DataTypeLiteral(null, "TF", 1, 1, false, false), 50));
		columnList.add(new Column("PASSWORD", new DataTypeLiteral(Arrays.asList("123456"), null, 8, 30, false, false), 50));
		columnList.add(new Column("BIRTH_DATE", new DataTypeDate(DATE_FORMAT,
				new Timestamp(60, 0, 0, 0, 0, 0, 0), new Timestamp(93, 0, 0, 0, 0, 0, 0), null, 0, 0, false, false), 50));
		columnList.add(new Column("REGISTRATION_DATE",new DataTypeDate(DATE_FORMAT,
				new Timestamp(115, 0, 0, 0, 0, 0, 0), new Timestamp(115, 1, 0, 0, 0, 0, 0), null, 0, 0, false, false), 50));
		table.setColumns(columnList);
		tableList.add(table);

		table = new DefaultTable(USER_ROLE_TABLE, 50);
		columnList = new ArrayList<Column>();
		columnList.add(new Column("ID", new DataTypeFK(user, true, false, false), 50));
		columnList.add(new Column("ID_ROLA", new DataTypeFK(rola, false, false, false), 50));
		table.setColumns(columnList);
		tableList.add(table);

		table = new DefaultTable(MESSAGE_TABLE, 500);
		columnList = new ArrayList<Column>();
		Column message = new Column("ID_MESSAGE", new DataTypeNumber(1, 501, 251, 0, true, false, true), 500);
		columnList.add(message);
		columnList.add(new Column("MESSAGE", new DataTypeLiteral(null, "abcd efgh ijklmno prstuw xyz ABCDEFGH IJKMLNOPRSTUWZ", 30, 100, false, false), 500));
		columnList.add(new Column("SUBJECT", new DataTypeLiteral(null, "abcd efgh ijklmno prstuw xyz ABCDEFGH IJKMLNOPRSTUWZ", 10, 20, false, false), 500));
		columnList.add(new Column("ID_RECIPIENT", new DataTypeFK(user, false, false, false), 500));
		columnList.add(new Column("ID_AUTHOR", new DataTypeFK(user, false, false, false), 500));
		columnList.add(new Column("SEND_DATE", new DataTypeDate(DATE_FORMAT,
				new Timestamp(115, 0, 0, 0, 0, 0, 0), new Timestamp(115, 1, 0, 0, 0, 0, 0), null, 0, 0, false, false), 500));
		table.setColumns(columnList);
		tableList.add(table);

		table = new DefaultTable(ROOM_TABLE, 100);
		columnList = new ArrayList<Column>();
		Column room = new Column("ID_ROOM", new DataTypeNumber(1, 101, 50, 0, true, false, true), 100);
		columnList.add(room);
		columnList.add(new Column("AVAILABLE", new DataTypeLiteral(null, "TF", 1, 1, false, false), 100));
		columnList.add(new Column("HAS_SLIDE_PROJECTOR", new DataTypeLiteral(null, "TF", 1, 1, false, false), 100));
		columnList.add(new Column("DESCRIPTION", new DataTypeLiteral(null, "abcd efgh ijklmno prstuw xyz ABCDEFGH IJKMLNOPRSTUWZ", 30, 100, false, false), 100));
		columnList.add(new Column("NAME", new DataTypeLiteral(null, "abcd efgh ijklmno prstuw xyz ABCDEFGH IJKMLNOPRSTUWZ", 5, 30, false, false), 100));
		columnList.add(new Column("NUMBER_OF_BOARDS", new DataTypeNumber(1, 10, 4, 0, false, false, false), 100));
		columnList.add(new Column("NUMBER_OF_TABLES", new DataTypeNumber(6, 100, 30, 0, false, false, false), 100));
		columnList.add(new Column("SIZE", new DataTypeNumber(10, 100, 4, 0, false, false, false), 100));
		columnList.add(new Column("TYPE", new DataTypeFK(roomType, false, false, false), 100));
		table.setColumns(columnList);
		tableList.add(table);

		table = new DefaultTable(RESERVATION_TABLE, 1000);
		columnList = new ArrayList<Column>();
		Column reservation = new Column("ID_RESERVATION", new DataTypeNumber(1, 1001, 500, 0, true, false, true), 1000);
		columnList.add(reservation);
		columnList.add(new Column("ACCEPTATION", new DataTypeLiteral(null, "TF", 1, 1, false, false), 1000));
		Column dateFrom = new Column("DATE_FROM", new DataTypeDate(DATE_FORMAT, new Timestamp(115, 0, 0, 0, 0, 0, 0), new Timestamp(115, 6, 0, 0, 0, 0, 0), null, 0, 0, false, false), 1000);
		columnList.add(dateFrom);
		columnList.add(new Column("DATE_TO", new DataTypeDate(DATE_FORMAT, new Timestamp(115, 0, 0, 0, 0, 0, 0), new Timestamp(115, 6, 0, 0, 0, 0, 0),dateFrom,  1 * 60 * 60 * 1000, 10 * 60 * 60 * 1000, false, false), 1000));
		columnList.add(new Column("REGISTRATION_DATE", new DataTypeDate(DATE_FORMAT, new Timestamp(115, 0, 0, 0, 0, 0, 0), new Timestamp(115, 3, 0, 0, 0, 0, 0), null, 0, 0, false, false), 1000));
		columnList.add(new Column("ID_STATUS", new DataTypeFK(status, false, false, false), 1000));
		columnList.add(new Column("ID", new DataTypeFK(user, false, false, false), 1000));
		table.setColumns(columnList);
		tableList.add(table);

		table = new DefaultTable(RESERVATION_ROOM_TABLE, 1000);
		columnList = new ArrayList<Column>();
		columnList.add(new Column("ID_RESERVATION", new DataTypeNumber(1, 1001, 500, 0, true, false, true), 1000));
		columnList.add(new Column("ID_ROOM", new DataTypeFK(room, false, false, false), 1000));
		table.setColumns(columnList);
		tableList.add(table);

		dataGeneratorManager.setTables(tableList);

		dataGeneratorManager.setTableListToGenerateData(TABLE_NAMES);

		dataGeneratorManager.generateDataFile("test.txt");
	}
}
