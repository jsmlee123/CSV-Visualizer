package Commands;

import Model.Country;
import Model.Util;
import java.util.ArrayList;
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

    List<Integer> dataNumList = new ArrayList<>();
    List<Integer> dataPredList = new ArrayList<>();

    for (int i = Integer.parseInt(dataList.get(0)) ; i <= Integer.parseInt(dataList.get(1)); i++) {
      dataNumList.add(i);
    }

    for (int i = Integer.parseInt(predList.get(0)) ; i <= Integer.parseInt(predList.get(1)); i++) {
      dataPredList.add(i);
    }

    int n = dataList.size();


    for (Country co: model) {
      float x1 = 0, y1 = 0, x2 = 0, y2 = 0, xy  = 0;
      float a = 0;
      float b = 0;

      for (int x: dataNumList) {
        float y = co.getMap().get(x);

        x1 += x;
        y1 += y;
        x2 += x * x;
        y2 += y * y;
        xy += x * y;

      }

      a = (y1 * x2 - x1 * xy)/(n* x2 - x1 * x1);
      b = (n * xy - x1 * y1)/(n * x2  -x1 * x1);


      for (int future:dataPredList) {
        float prediction = future * b + a;
        co.addPoint(future,prediction);
      }
    }

  }
}
