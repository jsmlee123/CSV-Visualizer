package Commands;

import Model.Country;
import java.util.List;
import view.DataView;

public class AllCommand extends AbstractCommand{
  List<Country> hidden;

  public AllCommand(List<Country> model, List<Country> hidden, DataView view) {
    super(model, view);
    this.hidden = hidden;
  }

  @Override
  public void run() {
    this.model.addAll(hidden);
    this.hidden.clear();
  }
}
