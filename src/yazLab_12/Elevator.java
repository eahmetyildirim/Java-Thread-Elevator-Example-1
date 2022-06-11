package yazLab_12;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Elevator extends Thread{
    public String name;
    int direction; // 1 ise yukarı -1 ise aşağı gidiyor.
    public  ArrayList<Integer> passengers=new ArrayList<>();
    int passengers_count;
    boolean active;
    int transition_time;
    int floor;
    Random random =new Random();
    public Elevator(String name) {
        this.name = name;
        for(int i=0;i<5;i++)
        passengers.add(0);
        
        active=false;
        transition_time=200;
        passengers_count=0;
        floor=0;
        direction=1;
        
    }

  

    
    @Override
    public void run() {
        while(true){
            System.out.println(name);// sakın silme bozuluyor
            if(this.active==false && passengers_count==0){
            continue;
            }
                passengers_count=0;
               for(int i=0;i<5;i++){
               passengers_count+=passengers.get(i);
               }
                download();
                if(this.active)//asansör aktifse yolcu alıyor sadece
                receiving();
                
                try {
                    Thread.sleep(transition_time);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Elevator.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
    }
    public void receiving(){  // katlardan yolcu almak için kullanılan fonksiyon.
    synchronized(mall.lockA){
     
    if(passengers_count<10){    // asansrdeki kişi sayısı 10 dan az ise çalışsın.
            
            
     if(floor==0){// Zemin katın kuyruğu farklı onun için oradan yapılan alımlar ayrı hesaplanıyor.
        
        int new_passengers=10-passengers_count; // asansöre alınabilicek kişi sayı
        boolean[] check={false,false,false,false,false};
            while(new_passengers!=0){
                
                int i=random.nextInt(4)+1;
               
                int x=random.nextInt(new_passengers)+1;
                if(x<=mall.queueEnter.get(i) && check[i]==false){
                passengers.set(i,passengers.get(i)+x);
                new_passengers-=x;
                mall.queueEnter.set(i,mall.queueEnter.get(i)-x);
                check[i]=true;
                }
                if(check[1]&&check[2]&&check[3]&&check[4]&&new_passengers!=0){
                    new_passengers=0;
                }

            }
         
        //}
      }else{// diğer katlardan yani çıkış kuyruktan yolcu alınan kısım
       /*  if(passengers_count+mall.queueExit.get(floor)<=10){
            passengers.set(0,passengers.get(floor)+mall.queueExit.get(floor));
            passengers_count+=mall.queueExit.get(floor);
            mall.floorPeople.set(floor, mall.floorPeople.get(floor)-mall.queueExit.get(floor));
            mall.queueExit.set(floor,0);
        
        }else{*/
            int new_passengers=10-passengers_count; // alınabilecek kişi sayısı
             System.out.println("New:"+new_passengers);
            if(new_passengers <= mall.floorPeople.get(floor) && new_passengers <= mall.queueExit.get(floor)){
                passengers.set(0,passengers.get(floor)+new_passengers);
                mall.floorPeople.set(floor, mall.floorPeople.get(floor)-new_passengers);
                mall.queueExit.set(floor,mall.queueExit.get(floor)-new_passengers);
                
            }
      //  }
      }
    }
    
      // ASANSÖRÜN YUKARI AŞAĞI GİTMESİ
      if(floor==0){
      floor++;   // zemin katta ise yukarı çıkması lazım
      direction=1;
      }else if(floor==4){
      floor--;// en üst katta ise aşağı inmesi lazım
      direction=-1;
      }else{ // burada da yolcu durumuna ve önceki gittiği yöne göre yeni yön belirliyor.
            int count=0;
             for(int i=floor;i<5;i++){
             count+=passengers.get(i);
             count+=mall.queueEnter.get(i);
             count+=mall.queueExit.get(i);
             }
             if(count==0 && direction==1){
             direction=-1;
             floor--;
             }else if(count>0 && direction==1){
             floor++;
             direction=1;
             }else if(direction==-1){
             floor--;
             direction=-1;
                     
             }
            
      }
     }
    }
    public void download(){// katlara yolcu indirmek için kullanılan fonksiyon
      synchronized(mall.lockB){  
        if(passengers.get(floor)!=0){
            mall.floorPeople.set(floor,mall.floorPeople.get(floor) +passengers.get(floor));
            mall.floorPeople.set(0,0);
            passengers.set(floor,0);
            
        }
       } 
    }

    
    
}
