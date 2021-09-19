package view;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class DataView extends JFrame {

  //show image
  private final JLabel imageLabel;

  private final JMenuItem fileOpenButton;

  private final JMenuItem fileSaveButton;

  private final JMenuItem plotLinear;

  private final JMenuItem removeCountry;

  private final JMenuItem addCountry;

  private final JMenuItem onlyCountry;

  private final JMenuItem allCountry;

  private final JMenuItem addCountries;

  private final JMenuItem plotBar;

  private final JMenuItem plotBar3D;

  private final JMenuItem linearRegression;

  public DataView() {
    //init general stuff about gui.
    super();
    this.setTitle("Carbon Analytics");
    this.setSize(5000, 5000);
    this.setPreferredSize(new Dimension(1440, 1024));
    this.setLocation(250, 0);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setResizable(false);

    //main panel
    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    JScrollPane mainScrollPanel = new JScrollPane(mainPanel);
    this.add(mainScrollPanel);

    //image panel
    //panel for showing the image
    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Top Layer Image"));
    imagePanel.setPreferredSize(new Dimension(1000, 1000));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel);

    //image label to place on the panel
    imageLabel = new JLabel();
    JScrollPane imageScrollPanel = new JScrollPane(imageLabel);
    imageScrollPanel.setPreferredSize(new Dimension(1000, 1000));
    imagePanel.add(imageScrollPanel);

    //general menu
    //menu bar
    JMenuBar menuBar = new JMenuBar();


    //menu for file operations
    JMenu file = new JMenu("File");
    menuBar.add(file);
    this.setJMenuBar(menuBar);

    //load file
    fileOpenButton = new JMenuItem("Open");
    fileOpenButton.setActionCommand("Open");
    file.add(fileOpenButton);

    //SAve file
    fileSaveButton = new JMenuItem("Save");
    fileSaveButton.setActionCommand("Save");
    file.add(fileSaveButton);

    //menu for plot operations
    JMenu plot = new JMenu("Plot");
    menuBar.add(plot);

    //Plot line
    plotLinear = new JMenuItem("Plot Linear");
    plotLinear.setActionCommand("Plot Linear");
    plot.add(plotLinear);

    //Plot bar
    plotBar = new JMenuItem("Plot Bar");
    plotBar.setActionCommand("Plot Bar");
    plot.add(plotBar);

    //Plot bar
    plotBar3D = new JMenuItem("Plot Bar 3D");
    plotBar3D.setActionCommand("Plot Bar 3D");
    plot.add(plotBar3D);


    //menu for data manipulation
    JMenu manipulateData = new JMenu("Manipulate data");
    menuBar.add(manipulateData);

    //remove countries from data
    removeCountry = new JMenuItem("Remove Countries");
    removeCountry.setActionCommand("Remove");
    manipulateData.add(removeCountry);

    //add back removed countries
    addCountry = new JMenuItem("Add Back");
    addCountry.setActionCommand("Add Back");
    manipulateData.add(addCountry);

    onlyCountry = new JMenuItem("Only Countries");
    onlyCountry.setActionCommand("Only");
    manipulateData.add(onlyCountry);

    allCountry = new JMenuItem("All Countries");
    allCountry.setActionCommand("All");
    manipulateData.add(allCountry);

    addCountries = new JMenuItem("Add Countries");
    addCountries.setActionCommand("Add All");
    manipulateData.add(addCountries);

    //menu for regression operations
    JMenu regression = new JMenu("Regression");
    menuBar.add(regression);

    //linear regression on data
    linearRegression = new JMenuItem("Linear");
    linearRegression.setActionCommand("Linear Regression");
    regression.add(linearRegression);



    this.pack();
  }

  public void setListener(ActionListener listener) {
    fileOpenButton.addActionListener(listener);
    fileSaveButton.addActionListener(listener);
    plotLinear.addActionListener(listener);
    plotBar.addActionListener(listener);
    plotBar3D.addActionListener(listener);
    removeCountry.addActionListener(listener);
    addCountry.addActionListener(listener);
    onlyCountry.addActionListener(listener);
    allCountry.addActionListener(listener);
    addCountries.addActionListener(listener);
    linearRegression.addActionListener(listener);
  }

  public void setImage(BufferedImage bufferedImage) {
    if (bufferedImage != null) {
      imageLabel.setIcon(new ImageIcon(bufferedImage));
    }
  }

  public String saveImage() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(DataView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return "";
  }


  public String getCSVPath() {
    final JFileChooser fchooser = new JFileChooser(".");
    fchooser.setAcceptAllFileFilterUsed(false);
    fchooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int retvalue = fchooser.showOpenDialog(DataView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return "";
  }

  public String getText(String message) {
    return JOptionPane.showInputDialog(message);
  }

  public String chooseType(String[] strings) {

    int returnVal = JOptionPane.showOptionDialog(null,
        "Choose a type of Graph to save:",
        "Image Type",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, strings, strings[0]);

    return strings[returnVal];
  }
}
