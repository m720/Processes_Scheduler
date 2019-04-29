package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Character.forDigit;
import static java.lang.Character.isDigit;

public class ProcessesEnterDataController {
  public Pane ProcessesNumberPane; //the Processes Number
  public Pane ProcessesEnterDetailsPane; //the Processes Number
  public Pane ProcessShowDataPane; //Showing the Process Data
  public TextField ProcessesNumberTextField; //the Processes Number
  static int processesNumber; //the number of processes Entered by the User
  static ArrayList<Process> Processes; //the Processes Entered by the User

   //for enter data pane
    public  TextField ArrivalTimeTextField;
    public  TextField BurstTimeTextField;
    public  TextField PriorityTextField;
    public  static int EnteredNumber;
    public   Button OKButton;
    public Button NextButton;
    public Label ProcessEnterDataLabel;

    //for Showing DataPane
    public HBox GanttChart;
    public HBox TimeAxis;


    //setting up the Table
    public TableView resultTable;
    public TableColumn<Process,String> responseTimeCol;
    public TableColumn<Process,String> watingTimeCol;
    public TableColumn<Process,String> turnAroundTimeCol;
    public TableColumn<Process,String> startTimeCol;
    public TableColumn<Process,String> processNumberCol;





  public boolean validateNumber(String s){ //function to validate the number of Processes

    for(int i =0; i<s.length();i++){ //loop for checking if their is any character
      char c = s.charAt(i);
      if(!(isDigit(c))) return false;
    }
    int a=Integer.parseInt(s);

    if (a<=0)return false;
    return true;
  }

    public boolean validateArrival(String s){ //function to validate the number of Processes

        for(int i =0; i<s.length();i++){ //loop for checking if their is any character
            char c = s.charAt(i);
            if(!(isDigit(c))) return false;
        }
        int a=Integer.parseInt(s);

        if (a<0)return false;
        return true;
    }

  public void EnterProcessesNumber() {
      try{
      String text = ProcessesNumberTextField.getText();
      boolean isValid = validateNumber(text);
      if (isValid) {
          int number = Integer.parseInt(text);
          ProcessesEnterDataController.processesNumber = number;
          ProcessesEnterDataController.Processes = null;
          ProcessesEnterDataController.Processes = new ArrayList<>();
          ProcessesEnterDataController.EnteredNumber = 0;
          ProcessesNumberPane.setVisible(false);
          ProcessesEnterDetailsPane.setVisible(true);
          ProcessEnterDataLabel.setText("P : "+(EnteredNumber+1));



      } else {
          Alert a = new Alert(Alert.AlertType.ERROR, "Enter Valid  Number");
          a.showAndWait();

      }
  }catch(Exception e){
          Alert a = new Alert(Alert.AlertType.ERROR, "Please Enter Data !");
          a.showAndWait();}
  }

  public void nextClick(){
       try {
           createProcess();
           if (EnteredNumber >= processesNumber-1){
               NextButton.setVisible(false);
               OKButton.setVisible(true);
                        }
       }catch (Exception e){
          Alert a = new Alert(Alert.AlertType.ERROR , "Please Enter Data in All Fields");
          a.showAndWait();
      }

  }
  public void createProcess(){
      String arrivalTime = ArrivalTimeTextField.getText();
      String burstTime = BurstTimeTextField.getText();
      String priority = PriorityTextField.getText();

      if(!validateArrival(arrivalTime)){//if the ArrivalTime is not Valid
          Alert a = new Alert(Alert.AlertType.ERROR , "Enter valid arrival time!");
          a.showAndWait();
          return;
      }else{
          if(!validateNumber(burstTime)){//if the BurstTime is not Valid
              Alert a = new Alert(Alert.AlertType.ERROR , "Enter valid burst time!");
              a.showAndWait();
              return;
          }else{
              if (!validateNumber(priority)){ //if the priority is not Valid
                  Alert a = new Alert(Alert.AlertType.ERROR , "Enter valid priority!");
                  a.showAndWait();
                  return;
              }else {// all three are Valid

                  Process p = new Process(Integer.parseInt(arrivalTime) ,Integer.parseInt(burstTime)  , Integer.parseInt(priority));
                  p.process_number = EnteredNumber+1;
                  Processes.add(p);
                  EnteredNumber++;

                  ArrivalTimeTextField.clear();
                  BurstTimeTextField.clear();
                  PriorityTextField.clear();
                  ProcessEnterDataLabel.setText("P : "+(EnteredNumber+1));
              }
          }
      }
  }


  public void  OKClick(){
      createProcess();
        ProcessesNumberPane.setVisible(false);
        ProcessesEnterDetailsPane.setVisible(false);
        ProcessShowDataPane.setVisible(true);

        //calculations
       Object [] a =  Processes.toArray();
       Process [] b = new Process[a.length];
      for(int i =0 ; i<a.length;i++){
          b[i] = (Process) a[i];
      }

        b = OsProject.processFunction(b);
        Processes.clear();
        Processes.addAll(Arrays.asList(b));


      //for Table
      responseTimeCol = new TableColumn("Response Time");
      responseTimeCol.setCellValueFactory(new PropertyValueFactory<Process,String>("response_time"));

      watingTimeCol= new TableColumn("Waiting Time");
      watingTimeCol.setCellValueFactory(new PropertyValueFactory <Process,String>("waiting_time"));

      turnAroundTimeCol= new TableColumn("Turn Around Time");
      turnAroundTimeCol.setCellValueFactory(new PropertyValueFactory<Process,String>("turn_around_time"));

      startTimeCol = new TableColumn("Start Time");
      startTimeCol.setCellValueFactory( new PropertyValueFactory<Process,String>("start_time"));

      processNumberCol =new TableColumn("Process Number");
      processNumberCol.setCellValueFactory(new PropertyValueFactory<Process,String>("process_number"));
      resultTable.getColumns().addAll(responseTimeCol, watingTimeCol  ,turnAroundTimeCol , startTimeCol ,processNumberCol);


      ObservableList <Process> observableList =  FXCollections.observableArrayList();
      observableList.addAll(Processes);
      resultTable.setItems(observableList);


      //GanttChart
      ArrayList <Color> colors  = new ArrayList();
      colors.add(Color.RED);
      colors.add(Color.BLUE);
      colors.add(Color.YELLOW);
      colors.add(Color.PURPLE);

      //time Axis

      for(int i =0 ;i<Processes.get(Processes.size()).ending_time;i++){
          Label l = new Label(Integer.toString(i));
          l.setMinWidth(10);
          l.setMinHeight(17);
          TimeAxis.getChildren().add(l);
      }

      int Time = 0;
      for (int i=0;i<EnteredNumber;i++){
        Process p = Processes.get(i);

                while (Time<p.start_time){
                Label l = new Label("*");
                l.setMinHeight(80);
                l.setMinWidth(10);
                GanttChart.getChildren().add(l);
                Time+=1;
            }

          int seconds = p.burst_time;
          for (int j  =0 ;j<seconds;j++){
              Label l = new Label(Integer.toString(p.process_number));
              l.setMinHeight(80);
              l.setMinWidth(10);
              l.setBackground(new Background(new BackgroundFill(colors.get(i%4) , CornerRadii.EMPTY , Insets.EMPTY)));
              GanttChart.getChildren().add(l);
              Time+=1;
          }

      }
     }
  }




