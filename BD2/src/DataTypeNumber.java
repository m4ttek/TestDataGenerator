/**
 * 
 * @author Mateusz Kamiñski
 */
public class DataTypeNumber implements DataType {

	private int min;
	
	private int max;
	
	private boolean mustBeUnique;
	
	private boolean isNullable;
	
	private int decimalPoint;
	
	private boolean isSequential;
	
	private int midPoint;

	private int seq;
	
	public DataTypeNumber(int min, int max, int midPoint, int decimalPoint, boolean mustBeUnique, boolean isNullable, boolean isSequential ) {
		this.midPoint = midPoint;
		this.decimalPoint = decimalPoint;
		this.mustBeUnique = mustBeUnique;
		this.isNullable = isNullable;
		this.isSequential = isSequential;
		if (min <= max) {
			this.min = min;
			this.max = max;
		} else {
			this.max = min;
			this.min = max;
		}
	}
	
	@Override
	public String getData() {
		int chosenNumber = 0;
		String data = null;
		if (isSequential) {
			chosenNumber = seq++;
			data = String.valueOf(chosenNumber);
		} else {
			
		}
		return data;
	}
}
