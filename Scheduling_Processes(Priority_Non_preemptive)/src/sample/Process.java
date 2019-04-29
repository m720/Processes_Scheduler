package sample;

public class Process {
    public  int arrival_time;
    public int burst_time;
    public int priority;
    public  int response_time;
    public int waiting_time;
    public int turn_around_time;
    public int start_time;
    public int process_number;
    public int ending_time;
    public boolean processed;

    public Process(){}
    public Process(int arrival_time ,int BurstTime,int Priority ){
        this.burst_time=BurstTime;
        this.priority=Priority;
        this.arrival_time=arrival_time;
        this.response_time =0;
        this.waiting_time =0;
        this.turn_around_time=0;
        this.start_time=0;
        this.processed= false;
    }
}
