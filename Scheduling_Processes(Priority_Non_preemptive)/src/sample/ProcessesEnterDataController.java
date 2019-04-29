package sample;

import javafx.collections.FXCollections.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.*;
import javafx.collections.ObservableListBase;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import java.util.ArrayList;
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
          ProcessesEnterDataController.Processes = new ArrayList();
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
  public void createStringValues(){ //create the string values for the table
      for (int i=0;i<Processes.size();i++){
          Processes.get(i).createStrings();
      }
  }


  public void  OKClick(){
      createProcess();
      ProcessesEnterDetailsPane.setVisible(false);
      ProcessesNumberPane.setVisible(false);
      ProcessShowDataPane.setVisible(true);

      //after calculations
      createStringValues();

      //for Table
      responseTimeCol = new TableColumn<>("Response Time");
      responseTimeCol.setCellValueFactory(new PropertyValueFactory<Process , String>("responseTime"));

      watingTimeCol= new TableColumn<>("Waiting Time");
      watingTimeCol.setCellValueFactory(new PropertyValueFactory <Process , String>("waitingTime"));

      turnAroundTimeCol= new TableColumn<>("Turn Around Time");
      turnAroundTimeCol.setCellValueFactory(new PropertyValueFactory<Process , String>("turnAroundTime"));

      startTimeCol = new TableColumn<>("Start Time");
      startTimeCol.setCellValueFactory( new PropertyValueFactory<Process , String>("startTime"));

      processNumberCol =new TableColumn<>("Process Number");
      processNumberCol.setCellValueFactory(new PropertyValueFactory<Process , String>("processNumber"));
    //  resultTable.getColumns().addAll(responseTimeCol, watingTimeCol  ,turnAroundTimeCol , startTimeCol ,processNumberCol);
      ObservableList <Process> observableList =  FXCollections.observableArrayList(Processes);
      resultTable.setItems(observableList);


  }
  }




