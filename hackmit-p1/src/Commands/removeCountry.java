package Commands;

import Model.Country;
import Model.Util;
import java.util.HashMap;
import java.util.List;
import view.DataView;

public class removeCountry extends AbstractCommand{
  String countries;
  List<Country> hiddenModel;

  public removeCountry(List<Country> model, List<Country> hiddenModel, DataView view,
      String countries) {
    super(model, view);
    this.countries = countries;
    this.hiddenModel = hiddenModel;
  }

  @Override
  public void run() {
    countries = countries.replaceAll("\\s","");
    List<String> removeList = Util.getRecordFromLine(countries);

    for (String co: removeList) {
      Country badCountry = new Country(co,"",new HashMap<>());
      if (this.model.contains(badCountry)) {
        this.hiddenModel.add(this.model.remove(this.model.indexOf(badCountry)));
      }
    }



  }
}
