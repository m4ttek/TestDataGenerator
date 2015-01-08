import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Mateusz Kami�ski
 */
public class DataTypeLoadedFromFile implements DataType{

	private String fileName;

	private boolean mustBeUnique;

	private boolean isNullable;

	private List<String> dataLoadedFromFile;

	Random rand = new Random(new Date().getTime());

	public DataTypeLoadedFromFile(String fileName, boolean mustBeUnique, boolean isNullable) {
		this.fileName = fileName;
		this.mustBeUnique = mustBeUnique;
		this.isNullable = isNullable;
	}

	@Override
	public List<String> getData(int numberOfTuples) {
		List<String> dataList = new ArrayList<String>();
		if (dataLoadedFromFile == null) {
			loadDataFromFile();
		}
		int random = 0;
		for (int row = 0; row < numberOfTuples; row++) {
			String data = "";
			if (isNullable) {
				random = rand.nextInt(dataLoadedFromFile.size() + 1);
			} else {
				random = rand.nextInt(dataLoadedFromFile.size());
			}
			if (random < dataLoadedFromFile.size()) {
				data = "'" + dataLoadedFromFile.get(random) + "'";
			}
			if (mustBeUnique && data != null) {
				dataLoadedFromFile.remove(random);
			}
			dataList.add(data);
		}

		return dataList;
	}

	private void loadDataFromFile() {
		File file = new File(fileName);
		System.out.println(file.getAbsolutePath());
		if (file.exists() && file.canRead()) {
			dataLoadedFromFile = new ArrayList<>();
			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNextLine()) {
					dataLoadedFromFile.add(scanner.nextLine());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("DataTypeLoadedFromFile: file not found: " + fileName);
			}
		} else {
			throw new RuntimeException("DataTypeLoadedFromFile: file not found: " + fileName);
		}
	}
}