package yazLab_12;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class mall extends Thread{
    int entry_time,exit_time,entry_number,exit_number;
    public static ArrayList<Integer> queueEnter=new ArrayList<>();
    public static ArrayList<Integer> queueExit=new ArrayList<>();
    public static ArrayList<Integer> floorPeople=new ArrayList<>();
    static Object lockA=new Object();
    static Object lockB=new Object();
    Random random=new Random();
    public mall() {
        this.entry_time=500; // 500 ms aralık değeri giriş yapanlar kuyruğu için
        this.exit_time=1000; // 1000 ms aralık değeri çıkış yapanlar kuyruğu için
        this.entry_number=10;// random max gelebilecek kişi sayısı 
        this.exit_number=10; // random max çıkabilicek kişi sayısı
        queueEnter.add(0);
        queueEnter.add(0);
        queueEnter.add(0);
        queueEnter.add(0);
        queueEnter.add(0);
        queueExit.add(0);
        queueExit.add(0);
        queueExit.add(0);
        queueExit.add(0);
        queueExit.add(0);
        floorPeople.add(0);
        floorPeople.add(0);
        floorPeople.add(0);
        floorPeople.add(0);
        floorPeople.add(0);
    }
    
    @Override
    public void run() {
           mall_entry_thread.start();
           mall_exit_thread.start();
    }
    
    Thread mall_entry_thread=new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
            int entry_numberr=random.nextInt(entry_number);
            boolean[] floor_check={false,false,false,false};
            while(entry_numberr!=0){
                if(floor_check[0]&&floor_check[1]&&floor_check[2]&&floor_check[3]){
                    entry_numberr=0;
                }
                int floor_num=random.nextInt(4)+1;
                    if(floor_check[floor_num-1])
                        continue;
                floor_check[floor_num-1]=true;
                int num=random.nextInt(entry_numberr);
                queueEnter.set(floor_num,queueEnter.get(floor_num)+num);
                entry_numberr-=num;
               
                
            }
               
            try {
                Thread.sleep(entry_time); // girilen değer kadar thread bekler.
            } catch (InterruptedException ex) {
                Logger.getLogger(mall.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        }
    });
    Thread mall_exit_thread=new Thread(new Runnable() {
        @Override
        public void run() {
         while(true){    
             int exit_num=random.nextInt(exit_number)+1;
            for(int i=1;i<5;i++){
                if(exit_num!=0 && floorPeople.get(i)!=0){
                   
                int floor_num=random.nextInt(exit_num)+1;
                    if(floor_num>floorPeople.get(i))
                        floor_num=floorPeople.get(i);
                    
                    queueExit.set(i,queueExit.get(i)+floor_num);
                //    floorPeople.set(i, floorPeople.get(i)-floor_num);
                    exit_num-=floor_num;
                }
            }
            try {
                Thread.sleep(exit_time);// girilen değer kadar ms cinsinden thread bekler.
            } catch (InterruptedException ex) {
                Logger.getLogger(mall.class.getName()).log(Level.SEVERE, null, ex);
            }
          }  
        }
    });

    

}
