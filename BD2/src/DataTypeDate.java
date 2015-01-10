import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Mateusz Kaminski
 */
public class DataTypeDate implements DataType {

	private SimpleDateFormat simpleDateFormat;
	private boolean mustBeUnique;
	private boolean isNullable;
	private Timestamp biggerThan;
	private Timestamp lessThan;
	private long timeDifferenceFromAnotherColumn;
	private long timeDifferenceToAnotherColumn;
	private Column column;
	private Column FKindexColumn;

	private boolean dateFromColumn;

	private Random random = new Random();
	private boolean dateBasedOnFK;

	public DataTypeDate(SimpleDateFormat simpleDateFormat,
			Timestamp biggerThan, Timestamp lessThan, Column columnWithDate, Column FKindexColumn,
			long timeDifferenceFromAnotherColumn,
			long timeDifferenceToAnotherColumn, boolean mustBeUnique,
			boolean isNullable) {
		this.simpleDateFormat = simpleDateFormat;
		this.biggerThan = biggerThan;
		this.lessThan = lessThan;
		this.column = columnWithDate;
		this.FKindexColumn = FKindexColumn;
		this.timeDifferenceFromAnotherColumn = timeDifferenceFromAnotherColumn;
		this.timeDifferenceToAnotherColumn = timeDifferenceToAnotherColumn;
		this.mustBeUnique = mustBeUnique;
		this.isNullable = isNullable;
		if (column != null && column.getColumnDataType() instanceof DataTypeDate) {
			dateFromColumn = true;
		}
		if (FKindexColumn != null && FKindexColumn.getColumnDataType() instanceof DataTypeFK) {
			dateBasedOnFK = true;
		}
	}

	@Override
	public List<String> getData(int numberOfTuples) {
		List<String> dataList = new ArrayList<String>();
		List<String> dataFromAnotherColumn = null;
		List<String> FKData = null;
		if (dateFromColumn) {
			dataFromAnotherColumn = column.getGeneratedData();
		}
		if (dateBasedOnFK) {
			FKData = FKindexColumn.getGeneratedData();
		}
		for (int row = 0; row < numberOfTuples; row++) {
			long newTime = 0;
			if (dateFromColumn) {
				String date = null;
				if (dateBasedOnFK) {
					String idx = FKData.get(row);
					Integer idxFK = Integer.valueOf(idx);
					date = dataFromAnotherColumn.get(idxFK % dataFromAnotherColumn.size());
				} else {
					date = dataFromAnotherColumn.get(row);
				}
				Date parsedDate = null;
				try {
					parsedDate = simpleDateFormat.parse(date.replace("'", ""));
				} catch (ParseException e) {
					new RuntimeException("DataTypeDate: nie udalo sie sparsowac daty");
					e.printStackTrace();
				}
				newTime = random.nextLong() % (timeDifferenceToAnotherColumn - timeDifferenceFromAnotherColumn);
				if (newTime < 0) {
					newTime =-newTime;
				}
				newTime = parsedDate.getTime() + timeDifferenceFromAnotherColumn + newTime;
			} else {
				newTime = random.nextLong() % (biggerThan.getTime() - lessThan.getTime());
				if (newTime < 0) {
					newTime = -newTime;
				}
				newTime += lessThan.getTime();
			}
			dataList.add("'" + simpleDateFormat.format(newTime) + "'");
		}

		return dataList;
	}
}
