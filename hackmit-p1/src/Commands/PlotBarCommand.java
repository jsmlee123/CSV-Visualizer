package Commands;

import Model.Country;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import view.DataView;

public class PlotBarCommand extends AbstractCommand{

  public PlotBarCommand(List<Country> model, DataView view) {
    super(model, view);
  }

  @Override
  public void run() {
    DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
    for (Country co : this.model) {
      for (Float[] pair : co.plottable()) {
        line_chart_dataset.addValue(pair[1], co.getName(), pair[0]);
      }
      JFreeChart barChart = ChartFactory.createBarChart(
          "CAR USAGE STATIStICS",
          "Category", "Score",
          line_chart_dataset, PlotOrientation.VERTICAL,
          true, true, false);

      this.view.setImage(barChart.createBufferedImage(1380, 900));
    }
  }
}
