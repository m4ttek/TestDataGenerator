import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Mateusz Kami�ski
 */
public class DataTypeNumber implements DataType {

	private long min;

	private long max;

	private boolean mustBeUnique;

	private boolean isNullable;

	private int decimalPoint;

	private boolean isSequential;

	private long midPoint;

	private long seq;

	private Random random = new Random();

	public DataTypeNumber(long min, long max, long midPoint, int decimalPoint, boolean mustBeUnique, boolean isNullable, boolean isSequential ) {
		this.midPoint = midPoint;
		this.decimalPoint = decimalPoint;
		this.mustBeUnique = mustBeUnique;
		this.isNullable = isNullable;
		this.isSequential = isSequential;
		if (min <= max) {
			this.min = min;
			this.max = max;
			seq = min;
		} else {
			this.max = min;
			this.min = max;
			seq = max;
		}
	}

	@Override
	public List<String> getData(int numberOfTuples) {
		long chosenNumber = 0;
		List<String> dataList = new ArrayList<String>();
		for (int row = 0; row < numberOfTuples; row++) {
			String data = "";
			if (isSequential) {
				chosenNumber = seq;
				seq += ((max - min) / numberOfTuples);
				data = String.valueOf(chosenNumber);
			} else {
				long nextLong = random.nextLong() % (max - min);
				if (nextLong < 0) {
					nextLong = -nextLong;
				}
				nextLong = nextLong + min;
				StringBuilder builder = new StringBuilder(String.valueOf(nextLong));
				if (decimalPoint > 0) {
					builder.append('.');
					for (int i = 0; i < decimalPoint; i++) {
						builder.append(random.nextInt(10));
					}
				}
				data = builder.toString();
			}
			dataList.add(data);
		}
		return dataList;
	}
}
