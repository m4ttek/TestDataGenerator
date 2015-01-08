import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Domy�lna implementacja DataGeneratorManager.
 *
 * @author Mateusz Kami�ski
 */
public class DataGeneratorManagerImpl implements DataGeneratorManager {

	private List<Table> tableList;

	private List<String> generationTableList;

	@Override
	public void generateDataFile(String fileName) {
		StringBuilder content = new StringBuilder();
		for (Table table : tableList) {
			if (generationTableList.contains(table.getTableName())) {
				content.append(table.generateDataForTable());
			}
		}
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
			writer.println(content.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (writer != null) {
			writer.close();
		}
	}

	@Override
	public void setTableListToGenerateData(List<String> tableList) {
		generationTableList = tableList;
	}

	@Override
	public void setTables(List<Table> tableList) {
		this.tableList = tableList;
	}

}
