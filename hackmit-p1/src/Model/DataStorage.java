package Model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DataStorage {
  List<Float[]> plottable();
  String getName();

  String getMeasurement();

  Map<Integer,Float> getMap();

  void addPoint(int parsedFut, float prediction);
}
