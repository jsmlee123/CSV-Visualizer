package Commands;

import Model.Country;
import Model.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import view.DataView;

public class OnlyCountries extends AbstractCommand{

  List<Country> hidden;
  String countries;

  public OnlyCountries(List<Country> model, List<Country> hidden, DataView view, String countries) {
    super(model, view);
    this.hidden = hidden;
    this.countries = countries;
  }


  @Override
  public void run() {
    countries = countries.replaceAll("\\s","");
    List<String> removeList = Util.getRecordFromLine(countries);

    List<Country> badList = new ArrayList<>();
    for (String coStr : removeList) {
      badList.add(new Country(coStr,"", new HashMap<>()));
    }


    List<Country> newModel = new ArrayList<>(this.model);
    for (Country co: newModel) {
      if (!(badList.contains(co))) {

        new removeCountry(this.model,this.hidden,this.view,co.getName()).run();
      }
    }

    newModel = new ArrayList<>(this.hidden);
    for (Country co: newModel) {
      if (badList.contains(co)) {
        new addCountry(this.model,this.hidden,this.view,co.getName()).run();
      }
    }
  }
}
