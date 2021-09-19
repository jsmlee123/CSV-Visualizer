package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Country implements DataStorage {
  private final String name;
  private Map<Integer, java.lang.Float> carbonData ; //year: carbon emissions
  private final String measurement;

  public Country(String name, String measurement, Map<Integer, Float> carbonData) {
    this.name = name;
    this.measurement = measurement;
    this.carbonData = carbonData;
  }

  /**
   * Takes this carbonData and turns it into plottable points
   *
   * @return plottable points
   */
  public List<java.lang.Float[]> plottable() {
    List<java.lang.Float[]> list2d = new ArrayList<>();

    List<Integer> sortedSet = new ArrayList<>(this.carbonData.keySet());
    Collections.sort(sortedSet);
    for (Integer keys : sortedSet) {

      list2d.add(new java.lang.Float[] {new java.lang.Float(keys), this.carbonData.get(keys)});
    }
    return list2d;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getMeasurement() {
    return this.measurement;
  }

  @Override
  public Map<Integer,Float> getMap() {
    return new HashMap<Integer,Float>(this.carbonData);
  }

  @Override
  public void addPoint(int parsedFut, float prediction) {
    this.carbonData.putIfAbsent(parsedFut,prediction);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Country) {
      Country otherCountry = (Country) other;
      String c1 = this.name.replaceAll("\\s", "");
      String c2 = otherCountry.name.replaceAll("\\s", "");

      return c1.equalsIgnoreCase(c2);
    } else {
      return false;
    }
  }


}
