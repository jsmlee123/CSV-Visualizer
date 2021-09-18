package Commands;

import Model.Country;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import view.DataView;

public class PlotLineCommand extends AbstractCommand{


  public PlotLineCommand(List<Country> model, DataView view) {
    super(model, view);
  }

  @Override
  public void run() {
    DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
    for (Country co: this.model) {
      for (Float[] pair:co.plottable()) {
        line_chart_dataset.addValue( pair[1] , co.getName() , pair[0] );
      }
      JFreeChart lineChartObject = ChartFactory.createLineChart(
          "CO2 Emissions Yearly","Years",
          co.getMeasurement(),
          line_chart_dataset, PlotOrientation.VERTICAL,
          true,true,false);

      this.view.setImage(lineChartObject.createBufferedImage(1380,900));


    }
  }
}
