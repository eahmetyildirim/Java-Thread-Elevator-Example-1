
package yazLab_12;


public class control extends Thread{
     private int count,active_elevator;
    @Override
    public  void run() {
       while(true){
          synchronized(mall.lockA){
           count=0;
           active_elevator=0;
           for(int i=0;i<5;i++){
           count+=mall.queueEnter.get(i);
           count+=mall.queueExit.get(i);
               
           }
           for(int i=0;i<4;i++){
               if(gui.elevators.get(i).active==true)
                   active_elevator++;
           }
           if(count < active_elevator*10){
           gui.elevators.get(active_elevator-1).active=false;
           }
           if(count> (active_elevator+1)*20 && active_elevator<4){
           gui.elevators.get(active_elevator).active=true;
           }
           
           } 
       }
    }
    
    
    
}
