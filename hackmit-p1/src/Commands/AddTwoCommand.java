package Commands;

import Model.Country;
import Model.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import view.DataView;

public class AddTwoCommand extends AbstractCommand{
  String countries;
  List<Country> hidden;

  public AddTwoCommand(List<Country> model, List<Country> hidden, DataView view,String countries) {
    super(model, view);
    this.countries = countries;
    this.hidden = hidden;
  }

  @Override
  public void run() {
    countries = countries.replaceAll("\\s","");
    List<String> coList = Util.getRecordFromLine(countries);
    String combinedName = "";
    String measurement = "";

    List<Country> totalList = new ArrayList<>(this.model);
    totalList.addAll(this.hidden);

    if(totalList.size() > 0) {
      measurement = totalList.get(0).getMeasurement();
    }



    if (coList.size() > 0) {
      combinedName += coList.get(0);


    }

    for (int i = 1;i < coList.size(); i++) {
        combinedName += " + " + coList.get(i);
    }



    List<Country> badList = new ArrayList<>();
    for (String coStr : coList) {
      badList.add(new Country(coStr,"", new HashMap<>()));
    }

    HashMap<Integer,Float> newMap = new HashMap<>();
    for (Integer val: totalList.get(0).getMap().keySet()) {
      newMap.put(val, (float) 0);
    }


    for (Country co: badList) {
      if (totalList.contains(co)) {
        Country tempCo = totalList.get(totalList.indexOf(co));
        for (Integer key: tempCo.getMap().keySet()) {
          newMap.put(key, newMap.get(key) + tempCo.getMap().get(key));
        }
      }
    }

    this.model.add(new Country(combinedName,measurement,newMap));


  }
}
