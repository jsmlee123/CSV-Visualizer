package Commands;

import Model.Country;
import java.io.IOException;
import java.util.List;
import view.DataView;

public abstract class AbstractCommand implements DataCommand {
  protected final DataView view;
  protected List<Country> model;

  // creates a function object command of the given arguments
  public AbstractCommand(List<Country> model, DataView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public abstract void run();

}
