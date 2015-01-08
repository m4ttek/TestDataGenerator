import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Mateusz Kaminski
 */
public class DataTypeLiteral implements DataType {

	private List<String> listOfStringsToBeUsed;
	private boolean mustBeUnique;
	private boolean isNullable;
	private int minNumberOfSigns;
	private String alphabet;
	private int maxNumberOfSigns;

	private Random random = new Random();

	public DataTypeLiteral(List<String> listOfStringsToBeUsed, String alphabet, int minNumberOfSigns, int maxNumberOfSigns,
			boolean mustBeUnique, boolean isNullable) {
		this.minNumberOfSigns = minNumberOfSigns;
		this.maxNumberOfSigns = maxNumberOfSigns;
		this.mustBeUnique = mustBeUnique;
		this.isNullable = isNullable;
		this.alphabet = alphabet;
		if (listOfStringsToBeUsed != null) {
			this.listOfStringsToBeUsed = new ArrayList<>(listOfStringsToBeUsed);
		}
	}

	@Override
	public List<String> getData(int numberOfTuples) {
		List<String> listData = new ArrayList<>();
		String data = "";
		int numberOfSignsToGenerate = 0;
		if (maxNumberOfSigns - minNumberOfSigns > 0) {
			numberOfSignsToGenerate = random.nextInt(maxNumberOfSigns - minNumberOfSigns);
		}
		numberOfSignsToGenerate += minNumberOfSigns;
		for (int row = 0; row < numberOfTuples; row++) {
			if (listOfStringsToBeUsed != null) {
				int rand = random.nextInt(listOfStringsToBeUsed.size());
				data = "'" + listOfStringsToBeUsed.get(rand) + "'";
				if (mustBeUnique) {
					listOfStringsToBeUsed.remove(rand);
				}
			} else {
				if (alphabet != null && !alphabet.isEmpty()) {
					StringBuilder literalBuilder = new StringBuilder();
					for (int i = 0; i < numberOfSignsToGenerate; i++) {
						literalBuilder.append(alphabet.charAt(random.nextInt(alphabet.length())));
					}
					data = "'" + literalBuilder.append("'").toString();
				}
			}
			listData.add(data);
		}
		return listData;
	}

}
