import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Mateusz Kami�ski
 */
public class DataTypeFK implements DataType {

	private Column thatWillHaveGeneratedData;

	private boolean mustBeUnique;

	private List<String> generatedData;

	private boolean isNullable;

	private Random rand = new Random(new Date().getTime());

	private boolean fromTheSameTable;

	public DataTypeFK(Column thatWillHaveGeneratedData, boolean mustBeUnique, boolean isNullable, boolean fromTheSameTable) {
		this.thatWillHaveGeneratedData = thatWillHaveGeneratedData;
		this.mustBeUnique = mustBeUnique;
		this.isNullable = isNullable;
		this.fromTheSameTable = fromTheSameTable;
	}

	@Override
	public List<String> getData(int numberOfTuples) {
		List<String> dataList = new ArrayList<String>();
		if (generatedData == null) {
			generatedData = new ArrayList<String>(thatWillHaveGeneratedData.getGeneratedData());
			if (generatedData == null || generatedData.isEmpty()) {
				throw new RuntimeException(
						"DataTypeFK: generated data from another table is not available - check for possible improper order of data generation or column uniquity.");
			}
		}
		int random = 0;
		for (int row = 0; row < numberOfTuples; row++) {
			String data = "";
			int range = 0;
			if (fromTheSameTable) {
				range = row;
			} else {
				range = generatedData.size();
			}
			if (isNullable) {
				range++;
			}
			if (range != 0) {
				random = rand.nextInt(range);
			}
			if (random < generatedData.size()) {
				data = generatedData.get(random);
			}
			if (mustBeUnique && data != null) {
				generatedData.remove(random);
			}

			if (fromTheSameTable && row == random) {
				dataList.add("null");
			} else {
				dataList.add(data);
			}
		}
		return dataList;
	}
}