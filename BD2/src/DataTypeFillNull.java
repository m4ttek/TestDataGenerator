import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateusz Kamiński
 *
 */
public class DataTypeFillNull implements DataType{

	@Override
	public List<String> getData(int numberOfTuples) {
		List<String> dataList = new ArrayList<String>();

		for (int row = 0; row < numberOfTuples; row++) {
			dataList.add("null");
		}

		return dataList;
	}

}
