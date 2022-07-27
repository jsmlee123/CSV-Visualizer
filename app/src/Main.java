import Controller.DataController;
import Model.Country;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import view.DataView;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    List<Country> model = new ArrayList<>();
    DataView view = new DataView();
    DataController controller = new DataController(model, view);
  }
}
