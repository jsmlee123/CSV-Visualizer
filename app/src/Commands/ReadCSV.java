package Commands;

import Model.Country;
import Model.Util;
import java.io.FileNotFoundException;
import java.util.List;
import view.DataView;

public class ReadCSV extends AbstractCommand{
  String file;

  public ReadCSV(List<Country> model, DataView view, String file) {
    super(model, view);
    this.file = file;
  }

  @Override
  public void run()  {
    try {
      this.model.clear();
      this.model.addAll(Util.readTextFile(file));
    } catch (FileNotFoundException e) {
      System.out.println("fail");

    }
  }
}
