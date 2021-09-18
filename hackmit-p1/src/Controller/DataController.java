package Controller;

import Commands.AddTwoCommand;
import Commands.AllCommand;
import Commands.DataCommand;
import Commands.OnlyCountries;
import Commands.PlotBarCommand;
import Commands.PlotLineCommand;
import Commands.ReadCSV;
import Commands.addCountry;
import Commands.removeCountry;
import Model.Country;
import Model.Util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import view.DataView;

public class DataController implements ActionListener {
  private List<Country> model;
  private List<Country> hidden;

  private final DataView view;

  private BufferedImage currentGraph;

  private final Map<String, Supplier<DataCommand>> commandMap;

  public DataController(List<Country> model, DataView view) {
    this.model = model;
    this.view = view;
    this.hidden = new ArrayList<>();

    this.commandMap = new HashMap<>();
    commandMap.putIfAbsent("Plot Linear", () -> new PlotLineCommand(model, view));
    commandMap.putIfAbsent("Plot Bar", () -> new PlotBarCommand(model, view));
    commandMap.putIfAbsent("Open", () -> new ReadCSV(model,view, view.getCSVPath()));
    commandMap.putIfAbsent("Remove", () -> new removeCountry(model,hidden,view,
        view.getText("Input Countries to remove, separated by comma")));

    commandMap.putIfAbsent("Add Back", () -> new addCountry(model,hidden,view,
        view.getText("Input Countries to add back, separated by comma, Allowed Countries: \n" + Util.getString(this.hidden))));


    commandMap.putIfAbsent("Only", () -> new OnlyCountries(model,hidden,view,
        view.getText("Input Countries to only include, separated by comma, Allowed Countries: \n"
            + Util.getString(this.hidden)  +Util.getString(this.model))));

    commandMap.putIfAbsent("All", () -> new AllCommand(model,hidden,view));

    commandMap.putIfAbsent("Add All", () -> new AddTwoCommand(model,hidden,view,
        view.getText("Input Countries to only include, separated by comma, Allowed Countries: \n"
            + Util.getString(this.hidden)  +Util.getString(this.model))));

    view.setListener(this);
    view.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    DataCommand c;
    String input = e.getActionCommand();
    Supplier<DataCommand> command = commandMap.getOrDefault(input, null);
    if (command != null) {
      c = command.get();
      c.run();
    }
  }



}
