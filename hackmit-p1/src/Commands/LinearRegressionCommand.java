package Commands;

import Model.Country;
import Model.Util;
import java.util.List;
import view.DataView;

public class LinearRegressionCommand extends AbstractCommand{
  String data;
  String pred;

  public LinearRegressionCommand(List<Country> model, DataView view, String data, String pred) {
    super(model, view);
    this.data = data;
    this.pred = pred;
  }

  @Override
  public void run() {
    data = data.replaceAll("\\s","");
    pred = pred.replaceAll("\\s","");

    List<String> dataList = Util.getRecordFromLine(data);
    List<String> predList = Util.getRecordFromLine(pred);

    int n = dataList.size();


    for (Country co: model) {
      float x1 = 0, y1 = 0, x2 = 0, y2 = 0, xy  = 0;
      for (String numStr: dataList) {
        float x = Float.parseFloat(numStr);
        float y = co.getMap().get(x);

        x1 += x;
        y1 += y;
        x2 += x * x;
        y2 += y * y;
        xy += x * y;

        

      }
    }

  }
}
