package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Util {
  /**
   * Reads information from a text file and returns the contents of the text file as a String.
   * Removes comments from the String starting with #.
   *
   * @param filename name of text File to read
   * @return the String contents of the file.
   * @throws IllegalArgumentException if file is null/ not found
   */
  public static List<Country> readTextFile(String filename)
      throws IllegalArgumentException, FileNotFoundException {


    if (filename == null) {
      throw new IllegalArgumentException("Null File Name");
    }

    if (!(filename.substring(filename.lastIndexOf(".") + 1).equals("csv"))) {
      throw new IllegalArgumentException("Not a CSV");
    }

    List<List<String>> records = new ArrayList<>();
    try (Scanner scanner = new Scanner(new File(filename));) {
      while (scanner.hasNextLine()) {
        records.add(getRecordFromLine(scanner.nextLine()));
      }
    } catch (Exception e) {
      throw new IllegalStateException("Parsing failed");
    }

    int k = 0;
    for (int i = 0; i < records.size(); i++) {
      if ((records.get(i).get(0).equals("Unit"))) {
        k = i;
        break;
      }
    }


    String unit = (records.get(k).get(3) + records.get(k).get(2)).replace("\"", " ");
    k +=1;
    List<String> strYears = new ArrayList<>(records.get(k).subList(1, records.get(k).size()));

    List<Integer> numYears = new ArrayList<>();
    for (String year : strYears) {
      try {
        numYears.add(Integer.parseInt(year));
      } catch(NumberFormatException e) {
        continue;
      }
    }

    k += 2;


    List<Country> countries = new ArrayList<>();
    for (int i = k; i < records.size(); i++) {
      String country = "";



      country = records.get(i).get(0).substring(records.get(i).get(0).indexOf(":") + 1);

      List<Float> numWaste = new ArrayList<>();
      List<String> subList = records.get(i).subList(1,records.get(i).size());
      for (int p = 1; p < subList.size(); p++) {
        String temp = "";
        if (subList.get(p).contains("\"") && !subList.get(p).contains(".")) {
          temp += subList.get(p) + subList.get(p + 1);
          p +=1;
        } else {
          temp += subList.get(p);
        }

        temp = temp.replace("\"","");

        try {
          numWaste.add(Float.parseFloat(temp));
        } catch (NumberFormatException e) {
          continue;
        }
      }

      Map<Integer,Float> yearMap = new HashMap<>();

      for (int z = 0; z < numWaste.size(); z ++) {
        yearMap.put(numYears.get(z),numWaste.get(z));
      }

      Country addition = new Country(country,unit,yearMap);
      countries.add(addition);
    }

    return countries;
  }

  public static List<String> getRecordFromLine(String line) {
    List<String> values = new ArrayList<>();
    try (Scanner rowScanner = new Scanner(line)) {
      rowScanner.useDelimiter(",");
      while (rowScanner.hasNext()) {
        values.add(rowScanner.next());
      }
    }
    return values;
  }

  public static String getString(List<Country> hiddenModel) {
    String str = "";
    List<Country> hidden = new ArrayList<>(hiddenModel);

    if (!(hidden.isEmpty())) {
      str += hidden.get(0).getName();
    }

    for (int i = 1;i < hidden.size(); i ++) {
      str += ", " + hidden.get(i).getName();

      if (i%10 == 0) {
        str += "\n";
      }
    }

    return str;
  }

  public static void writeImage(BufferedImage model, String file)
      throws IllegalArgumentException, IOException{
        File outputfile = new File(file);
        ImageIO.write(model, "png", outputfile);
  }

}
