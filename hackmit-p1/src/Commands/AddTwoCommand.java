package Commands;

import Model.Country;
import Model.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import view.DataView;

public class AddTwoCommand extends AbstractCommand{
  String countries;

  public AddTwoCommand(List<Country> model, DataView view,String countries) {
    super(model, view);
    this.countries = countries;
  }

  @Override
  public void run() {
    countries = countries.replaceAll("\\s","");
    List<String> coList = Util.getRecordFromLine(countries);
    String combinedName = "";

    if (coList.size() > 0) {
      combinedName += coList.get(0);
    }

    for (int i = 1;i < coList.size(); i++) {
        combinedName += coList.get(i) + " + ";
    }



    List<Country> badList = new ArrayList<>();
    for (String coStr : coList) {
      badList.add(new Country(coStr,"", new HashMap<>()));
    }

    for () {

    }


  }
}
