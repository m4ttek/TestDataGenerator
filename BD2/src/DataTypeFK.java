import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Mateusz Kamiñski
 */
public class DataTypeFK implements DataType {

	private Column thatWillHaveGeneratedData;
	
	private boolean mustBeUnique;

	private List<String> generatedData;

	private boolean isNullable;
	
	private Random rand = new Random(new Date().getTime());
	
	public DataTypeFK(Column thatWillHaveGeneratedData, boolean mustBeUnique, boolean isNullable) {
		this.thatWillHaveGeneratedData = thatWillHaveGeneratedData;
		this.mustBeUnique = mustBeUnique;
		this.isNullable = isNullable;
	}

	@Override
	public String getData() {
		if (generatedData == null) {
			generatedData = new ArrayList<String>(thatWillHaveGeneratedData.getGeneratedData());
			if (generatedData == null || generatedData.isEmpty()) {
				throw new RuntimeException(
						"DataTypeFK: generated data from another table is not available - check for possible improper order of data generation or column uniquity.");
			}
		}
		int random = 0;
		String data = null;
		if (isNullable) {
			random = rand.nextInt() % (generatedData.size() + 1);
		} else {
			random = rand.nextInt() % generatedData.size();
		}
		if (random < generatedData.size()) {
			data = generatedData.get(random);
		}
		if (mustBeUnique && data != null) {
			generatedData.remove(random);
		}
		return data;
	}
}