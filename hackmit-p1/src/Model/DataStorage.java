package Model;


import java.util.List;

public interface DataStorage {
  List<Float[]> plottable();
  String getName();

  String getMeasurement();
}
