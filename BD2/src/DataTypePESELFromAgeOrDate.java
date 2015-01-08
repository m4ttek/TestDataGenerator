import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Generuje numer PESEL na podstawie wieku lub daty z pobranej kolumny.
 *
 * @author Mateusz Kaminski
 */
public class DataTypePESELFromAgeOrDate implements DataType {

	boolean dataFromAge;

	private Column thatWillHaveGeneratedData;

	private boolean mustBeUnique;

	private List<String> anotherColumnGeneratedData;

	Random random = new Random();

	private int thisYear;

	public DataTypePESELFromAgeOrDate(Column thatWillHaveGeneratedData, boolean mustBeUnique) {
		this.thatWillHaveGeneratedData = thatWillHaveGeneratedData;
		this.mustBeUnique = mustBeUnique;
		if (thatWillHaveGeneratedData.getColumnDataType() instanceof DataTypeNumber) {
			dataFromAge = true;
		}
		thisYear = new Date(System.currentTimeMillis()).getYear();
	}

	@Override
	public List<String> getData(int numberOfTuples) {
		List<String> dataList = new ArrayList<>();
		anotherColumnGeneratedData = new ArrayList<>(thatWillHaveGeneratedData.getGeneratedData());
		for (int row = 0; row < numberOfTuples; row++) {
			String data = "";
			if (dataFromAge) {
				int rand = random.nextInt(anotherColumnGeneratedData.size());
				Integer age = Integer.valueOf(anotherColumnGeneratedData.get(row));
				int year = thisYear - age;
				int month = random.nextInt(12) + 1;
				int day = random.nextInt(30) + 1;
				StringBuilder peselBuilder = new StringBuilder();
				peselBuilder.append(year);
				if (month < 10) {
					peselBuilder.append('0');
				}
				peselBuilder.append(month);
				if (day < 10) {
					peselBuilder.append('0');
				}
				peselBuilder.append(day);
				for (int i = 0; i < 5; i++) {
					peselBuilder.append(random.nextInt(10));
				}
				data = peselBuilder.toString();
			} else {

			}
			dataList.add(data);
		}
		return dataList;
	}

}
