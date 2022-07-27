package Commands;

import Model.Country;
import Model.Util;
import java.util.HashMap;
import java.util.List;
import view.DataView;

public class AddCountry extends AbstractCommand{
  List<Country> hidden;
  String countries;

  public AddCountry(List<Country> model, List<Country> hidden, DataView view, String countries) {
    super(model, view);
    this.hidden = hidden;
    this.countries = countries;
  }

  @Override
  public void run() {
    System.out.println(this.hidden.size());
    countries = countries.replaceAll("\\s","");
    List<String> addList = Util.getRecordFromLine(countries);


    for (String co: addList) {
      Country badCountry = new Country(co,"",new HashMap<>());
      if (this.hidden.contains(badCountry)) {

        this.model.add(this.hidden.remove(this.hidden.indexOf(badCountry)));
      }
    }


  }
}
