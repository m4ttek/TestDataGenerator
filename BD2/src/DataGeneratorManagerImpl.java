import java.util.List;

/**
 * Domyœlna implementacja DataGeneratorManager.
 * 
 * @author Mateusz Kamiñski
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
		System.out.println(content.toString());
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
