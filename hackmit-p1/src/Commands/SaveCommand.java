package Commands;

import Model.Country;
import Model.Util;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import view.DataView;

public class SaveCommand extends AbstractCommand{
  String type;
  String file;

  public SaveCommand(List<Country> model, DataView view, String type,String file) {
    super(model, view);
    this.type = type;
    this.file = file;

  }

  @Override
  public void run() {
    DefaultCategoryDataset chart_dataset = new DefaultCategoryDataset();
    String measurement = this.model.get(0).getMeasurement();
    for (Country co : this.model) {
      for (Float[] pair : co.plottable()) {
        chart_dataset.addValue(pair[1], co.getName(), pair[0]);
      }
    }

    JFreeChart chart = null;
    if (type.equals("Linear")) {
      chart = ChartFactory.createLineChart(
          "CO2 Emissions Yearly", "Years",
          measurement,
          chart_dataset, PlotOrientation.VERTICAL,
          true, true, false);
    } else if (type.equals("Bar Plot")) {
      chart = ChartFactory.createBarChart(
          "CO2 Emissions Yearly",
          "Years", measurement,
          chart_dataset, PlotOrientation.VERTICAL,
          true, true, false);
    } else if (type.equals("Bar Plot 3D")) {
      chart = ChartFactory.createBarChart3D(
          "CO2 Emissions Yearly",
          "Years", measurement,
          chart_dataset,
          PlotOrientation.VERTICAL,
          true, true, false);
    }

    BufferedImage imageSave = chart.createBufferedImage(1380,900);


    try {
      Util.writeImage(imageSave, file);

    } catch (IOException e) {

      System.out.println("Saving failed");
    }
  }

}
