package sample;

public class Process {
    public  int arrival_time;
    public int burst_time;
    public int priority;
    public  int resposnse_time;
    public int wating_time;
    public int turn_around_time;
    public int start_time;
    public int process_number;
    public  String responseTime;
    public String waitingTime;
    public String turnAroundTime;
    public String startTime;
    public String processNumber;

    public Process(){}
    public Process(int arrival_time ,int BurstTime,int Priority ){
        this.burst_time=BurstTime;
        this.priority=Priority;
        this.arrival_time=arrival_time;
        this.resposnse_time =0;
        this.wating_time=0;
        this.turn_around_time=0;
        this.start_time=0;
    }
    public void createStrings(){
        /*switch (name){
            case  "process_number": return Integer.toString(process_number);
            case  "start_time": return Integer.toString(start_time);
            case  "turn_around_time": return Integer.toString(turn_around_time);
            case  "wating_time": return Integer.toString(wating_time);
            case  "resposnse_time": return Integer.toString(resposnse_time);
        }
        return  "no";*/

        responseTime= Integer.toString(resposnse_time);
        waitingTime= Integer.toString(wating_time);
        turnAroundTime= Integer.toString(turn_around_time);
        startTime= Integer.toString(start_time);
        processNumber= Integer.toString(process_number);
    }
}
