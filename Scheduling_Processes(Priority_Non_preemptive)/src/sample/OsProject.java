package sample;

import java.lang.reflect.Array;
import java.util.Scanner;
import sample.Process;

public class OsProject {


    public  static  int avarage_turnaround_time;
    public  static  int avarage_waiting_time;
    public  static  int avarage_response_time;


    public static Process[] processFunction (Process [] pr ){
       int  num_of_processes = pr.length;
       Process temp;
       for (int i = 0; i < num_of_processes - 1; i++) {
            for (int j = i + 1; j < num_of_processes; j++) {
                if (pr[i].arrival_time == pr[j].arrival_time){
                 if (pr[i].priority < pr[j].priority){

                     temp = pr[i];
                     pr[i] = pr[j];
                     pr[j] = temp;
                 }   
                }
               else  if (pr[i].arrival_time > pr[j].arrival_time) {
                    temp = pr[i];
                    pr[i] = pr[j];
                    pr[j] = temp;
                }
            }
        }

        int time=pr[0].arrival_time,i=0;
        boolean finish=false;
         temp=pr[0];
        while (true){
            temp.start_time=time;
            temp.ending_time=time+temp.burst_time;
            temp.processed=true;
            time+=temp.burst_time;

            int min=2147483647,target=0,q=0;
            for(int k=0;k<num_of_processes;k++) {
                if (pr[k].priority < min&&pr[k].arrival_time<=time&&!pr[k].processed){
                    min = pr[k].priority;
                    target = k;
                    q++;
                }
            }
            temp=pr[target];
            if(q==0) {
                for (int k = 0; k < num_of_processes; k++) {
                    if (!pr[k].processed) {
                        time=pr[k].arrival_time;
                        temp=pr[k];
                        break;
                    }
                }
            }
            for(int k=0;k<num_of_processes;k++)
                if(!pr[k].processed)
                    break;
                else if(k==num_of_processes-1)
                    finish=true;
            if(finish)
                break;
        }

        for (i = 0; i < num_of_processes - 1; i++) {
            for (int j = i + 1; j < num_of_processes; j++) {
                if (pr[i].start_time > pr[j].start_time) {
                    temp = pr[i];
                    pr[i] = pr[j];
                    pr[j] = temp;
                }
            }
            pr[i].turn_around_time= pr[i].ending_time-pr[i].arrival_time;
            pr[i].waiting_time=pr[i].turn_around_time-pr[i].burst_time;
            pr[i].response_time=pr[i].start_time;
            avarage_turnaround_time+= pr[i].turn_around_time;
            avarage_waiting_time+=pr[i].waiting_time;
            avarage_response_time+=pr[i].response_time;
        }
        pr[num_of_processes-1].turn_around_time= pr[num_of_processes-1].ending_time-pr[num_of_processes-1].arrival_time;
        pr[num_of_processes-1].waiting_time=pr[num_of_processes-1].turn_around_time-pr[num_of_processes-1].burst_time;
        pr[num_of_processes-1].response_time=pr[num_of_processes-1].start_time;
        avarage_turnaround_time+= pr[num_of_processes-1].turn_around_time;
        avarage_waiting_time+=pr[num_of_processes-1].waiting_time;
        avarage_response_time+=pr[num_of_processes-1].response_time;
        avarage_turnaround_time/=num_of_processes;
        avarage_waiting_time/=num_of_processes;
        avarage_response_time/=num_of_processes;

        return  pr;
    }
}
