
package yazLab_12;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;


public class gui extends javax.swing.JFrame implements ActionListener{

    DefaultTableModel PeopleCountModel=new DefaultTableModel();
    DefaultTableModel ElevatorModel=new DefaultTableModel();
    public static ArrayList<Elevator> elevators=new ArrayList<>();
    Timer timer=new Timer(100,this);
     mall Mall=new mall();
     Elevator elevator=new Elevator("Asansör-1");
     control Control=new control();
    public gui() {
        initComponents();
        timer.start();
        PeopleCountModel=(DefaultTableModel) peopleCountTable.getModel();
        ElevatorModel=(DefaultTableModel) ElevatorTable.getModel();
         Mall.start();
         
         elevator.start();
         elevator.active=true;
         elevators.add(new Elevator("Asansör-2"));
         elevators.add(new Elevator("Asansör-3"));
         elevators.add(new Elevator("Asansör-4"));
         elevators.add(new Elevator("Asansör-5"));
         for(int i=0;i<4;i++)
           elevators.get(i).start();
         
         Control.start();
         UpdatePeopleCountTable();
         UpdateElevatorTable();
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        UpdateElevatorTable();
        UpdatePeopleCountTable();
    }
    void UpdatePeopleCountTable(){
        PeopleCountModel.setRowCount(0);
     //   System.out.println("Kuyrukk"+mall.queueEnter + " Çıkış Kuyruk"+ mall.queueExit);
        Object[] rowData={"Asansör Sırası",mall.queueEnter.get(0)+mall.queueEnter.get(1)+mall.queueEnter.get(2)+mall.queueEnter.get(3)+mall.queueEnter.get(4),mall.queueExit.get(1),mall.queueExit.get(2),mall.queueExit.get(3),mall.queueExit.get(4)};
        PeopleCountModel.addRow(rowData);
        Object[] rowData2={"Katlardaki İnsanlar",mall.floorPeople.get(0),mall.floorPeople.get(1),mall.floorPeople.get(2),mall.floorPeople.get(3),mall.floorPeople.get(4)};
        PeopleCountModel.addRow(rowData2);

    }
    void UpdateElevatorTable(){
        synchronized(mall.lockB){
        ElevatorModel.setRowCount(0);
        String directionS,Active;
        if(elevator.direction==1) directionS= "Yukarı"; else directionS= "Aşağı";
        if(elevator.active) Active= "Aktif/Çalışıyor"; else Active= "Pasif/Beklemede";
        
        Object[] rowData={elevator.name,Active,elevator.floor+".kat",directionS,elevator.passengers_count+"/10",elevator.passengers};
        ElevatorModel.addRow(rowData);
       
        for(int i=0;i<4;i++){
            if(elevators.get(i).direction==1) directionS= "Yukarı"; else directionS= "Aşağı";
            if(elevators.get(i).active) Active= "Aktif/Çalışıyor"; else Active= "Pasif/Beklemede";
        
            Object[] rowData2={elevators.get(i).name,Active,elevators.get(i).floor+".kat",directionS,elevators.get(i).passengers_count+"/10",elevators.get(i).passengers};
            ElevatorModel.addRow(rowData2);
        }
      
       }  
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        peopleCountTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ElevatorTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(28, 2, 71));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ASANSÖR iZLEME PANELİ");

        peopleCountTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        peopleCountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "", "Zemin", "1.Kat", "2.Kat", "3.Kat", "4.Kat"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        peopleCountTable.setRowHeight(40);
        jScrollPane1.setViewportView(peopleCountTable);
        if (peopleCountTable.getColumnModel().getColumnCount() > 0) {
            peopleCountTable.getColumnModel().getColumn(1).setResizable(false);
            peopleCountTable.getColumnModel().getColumn(2).setResizable(false);
            peopleCountTable.getColumnModel().getColumn(3).setResizable(false);
            peopleCountTable.getColumnModel().getColumn(4).setResizable(false);
            peopleCountTable.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kat Bilgileri");

        ElevatorTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ElevatorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "", "Durum/Mod", "Lokasyon", "Yön", "Kapasite", "Yolcular"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ElevatorTable.setRowHeight(40);
        jScrollPane2.setViewportView(ElevatorTable);
        if (ElevatorTable.getColumnModel().getColumnCount() > 0) {
            ElevatorTable.getColumnModel().getColumn(0).setResizable(false);
            ElevatorTable.getColumnModel().getColumn(1).setResizable(false);
            ElevatorTable.getColumnModel().getColumn(2).setResizable(false);
            ElevatorTable.getColumnModel().getColumn(3).setResizable(false);
            ElevatorTable.getColumnModel().getColumn(4).setResizable(false);
            ElevatorTable.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(317, 317, 317)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(81, 81, 81)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ElevatorTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable peopleCountTable;
    // End of variables declaration//GEN-END:variables
}
