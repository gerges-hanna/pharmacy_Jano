package pharmacy.v3.pkg0;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author gerges Fci-H
 */
public class EXP extends javax.swing.JFrame {

    ArrayList<QuantityModel> list=new ArrayList<QuantityModel>();
    ArrayList<QuantityModel> list2=new ArrayList<QuantityModel>();
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    Statement stmt = null;
    /**
     * Creates new form EXP
     */
    public int flagQuantity=0,flagReapet=0,FlagZero=0,flagList2=0;
    
    public EXP() {
        con=sqlite.connectDB();
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("hospital.png")));
        setLocationRelativeTo(null);
        FindRemainderQuantity();
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        //showData();
        
                
    }
    public void showData()
    {
        try {
            String q="select id,en_name,ar_name,effect,quantity"
                    + ",Tablets,Tablets_per_box,sell,discount,retail,exp,company,idope from medicine";
            pst=con.prepareStatement(q);
            rs=pst.executeQuery();
            tabExp.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void FindRemainderQuantity()
    {
        try {
            String q="select exp,en_name,idope,quantity,Tablets from medicine";
            pst=con.prepareStatement(q);
            rs=pst.executeQuery();
            Date.setModel(DbUtils.resultSetToTableModel(rs));
            //String remind=rs.getString("exp");
            //lbl.setText(remind);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            
        }
       
        
    }
    public void addRow(Object [] dataRow)
    {
        DefaultTableModel m1=(DefaultTableModel) tabExp.getModel();
        m1.addRow(dataRow);
    }
    private  int TabletShow;
    public void quantityDown()
    {
        if(txt1.getText().equals(""))
        {
             TabletShow=3;
        }else
        {
            TabletShow=Integer.parseInt(txt1.getText());
        }
//        addRow(new Object[]{getID_ope,getEN,Quantity,Tables} );
        try {
            DefaultTableModel Model =(DefaultTableModel) Date.getModel();
            int rowCount = Date.getRowCount();  
            for (int i = 0; i < rowCount; i++) {
                String getEN=(String) Model.getValueAt(i,1);
                String getID_ope=String.valueOf(Model.getValueAt(i,2)) ;
                String getQuantity=(String) Model.getValueAt(i,3);
                String getTables=(String) Model.getValueAt(i,4);
                double Quantity=Double.parseDouble(getQuantity);
                double Tables=Double.parseDouble(getTables);
                list.add(new QuantityModel(getEN, getID_ope, getQuantity, getTables, Quantity, Tables));
                
            }
            double QuantityLoop=0;
            double TabletLoop=0;
            int CountLoop=0;
            for (int i = 0; i < list.size(); i++) {
//                
//                list.get(i).getID_ope,list.get(i).getEN,list.get(i).Quantity,list.get(i).Tables
              
                    for (int j = i; j < list.size(); j++) {
                        
                        
                            if(list.get(i).getEN.equalsIgnoreCase(list.get(j).getEN))
                            {
                                QuantityLoop=QuantityLoop+list.get(j).Quantity;
                                TabletLoop=TabletLoop+list.get(j).Tables;
                                
                            }
                        
                    }
               
                list2.add(new QuantityModel(list.get(i).getEN, list.get(i).getID_ope, null, null, QuantityLoop, TabletLoop));
//                addRow(new Object[]{list2.get(i).getID_ope,list2.get(i).getEN,
//                        QuantityLoop,TabletLoop} );
                QuantityLoop=0;
                TabletLoop=0;
                
                
            }
            for (int i = 0; i < list2.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    
                    if(j<i)
                    {
                        if(list2.get(i).getEN.equalsIgnoreCase(list2.get(j).getEN))
                        {
                            CountLoop++;
                        }
                    }
                        
                    
                }
                if(CountLoop==0 && list2.get(i).Tables<=TabletShow)
                {
                    addRow(new Object[]{list2.get(i).getID_ope,list2.get(i).getEN,
                                       list2.get(i).Quantity,list2.get(i).Tables} );
                }
                CountLoop=0;
                
            }
                
                
        } catch (Exception e) {
        }
            
//                if (Tables<=3 && flagQuantity==0) {

                    
//                    if(Tables==0 || Tables==0.0)
//                    {
//                        for (int j = 0; j < list.size(); j++) {
//                            flagReapet=0;
//                            if(getEN.equalsIgnoreCase(list.get(i)))
//                            {
//                                flagReapet=1;
//                                break;
//                            }
//                           
//                                
//                        }
//                        if (flagReapet==0) {
//                            addRow(new Object[]{getID_ope,getEN,Quantity,Tables} );
//                        }
//                        
//                        list.add(getEN);
//                    }else if(Tables<=3 && Tables>0)
//                    {
//                     addRow(new Object[]{getID_ope,getEN,Quantity,Tables} );   
//                    }
//                 if(Tables<1)
//                 {
//                     flagReapet=0;
//                     if(list.size()!=0)
//                     {
//                         for (int j = 0; j < list.size(); j++) {
//                             
//                             if(getEN.equalsIgnoreCase(list.get(j)))
//                             {
//                                 flagReapet=1;
//                                 break;
//                             }
//                            
//                         }
//                         if (flagReapet==0 && FlagZero==0) {
//                             list.add(getEN);
//                             addRow(new Object[]{getID_ope,getEN,Quantity,Tables} );
//                         }
//                     }else if(FlagZero==0)
//                     {
//                         list.add(getEN);
//                        addRow(new Object[]{getID_ope,getEN,Quantity,Tables} );
//                     }
//                     
//                 }else if (Tables<=3) {
//                     list.add(getEN);                    
//                     if(list2.size()==0)
//                     {
//                         list2.add(getEN);
//                         addRow(new Object[]{getID_ope,getEN,Quantity,Tables} );
//                     }else
//                     {
//                         flagList2=0;
//                         for (int j = 0; j < list2.size(); j++) {
//                             
//                             if (list2.get(j).equalsIgnoreCase(getEN)) {
//                                 flagList2=1;
//                                 break;
//                             }
//                         }
//                         if (flagList2==0) {
//                            list2.add(getEN);
//                            addRow(new Object[]{getID_ope,getEN,Quantity,Tables} );                          
//                         }
//                     }
//                     
//                     
//                    }
//  
//                }
//              flagQuantity=0;
//              FlagZero=0;
//            }
//            
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e);
//     }
        
            
    }
    public void expDate()
    {
        String idop="";
        
            DefaultTableModel Model =(DefaultTableModel) Date.getModel();
            Date d=new Date();
            SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
            String Date1=s.format(d);
            String[] currentDate=Date1.split("-");    

            int rowCount = Date.getRowCount();

            for (int i = 0; i < rowCount; i++) {
                try {
                String getExp=(String) Model.getValueAt(i,0);
                String getEN=(String) Model.getValueAt(i,1);
                String getID_ope=String.valueOf(Model.getValueAt(i,2)) ;
                
                String Type="";
                int days = 0;
                if(getExp.equalsIgnoreCase(""))
                    continue;            
                String []ExpDate=getExp.split("/");
                idop=getID_ope;
                //System.out.println(ExpDate[0]+" "+ExpDate[1]+" "+ExpDate[2]+" "
                //+currentDate[0]+" "+currentDate[1]+" "+currentDate[2]+" ");
                if(Integer.parseInt(currentDate[2])>Integer.parseInt(ExpDate[2]))
                {
                    Type="Expired";
                    //addRow(new Object[]{s1,s2,s3,s5,s8,s6,s7,s9,Date1,Time,lbl_tablePerBox.getText()} );
                }
                else
                {
                    if(Integer.parseInt(currentDate[2])==Integer.parseInt(ExpDate[2]) &&
                       Integer.parseInt(currentDate[1])>Integer.parseInt(ExpDate[1]))
                    {
                        Type="Expired";
                    }
                    else{
                        if (Integer.parseInt(currentDate[2])==Integer.parseInt(ExpDate[2]) &&
                            Integer.parseInt(currentDate[1])==Integer.parseInt(ExpDate[1]) &&
                            Integer.parseInt(currentDate[0])>Integer.parseInt(ExpDate[0])) 
                        {
                           Type="Expired";  
                        }
                        else
                        {
                            int months=Integer.parseInt(ExpDate[1]),years=Integer.parseInt(ExpDate[2]);                        
                            if(Integer.parseInt(currentDate[0])>Integer.parseInt(ExpDate[0]))
                                    {
                                        days=(Integer.parseInt(ExpDate[0])+30)-Integer.parseInt(currentDate[0]);
                                        if(Integer.parseInt(ExpDate[1])>=2)
                                        {
                                          months=Integer.parseInt(ExpDate[1])-1;    
                                        }
                                        else
                                        {
                                            months=12;
                                            years--;
                                        }

                                    }
                            else{
                                days=Integer.parseInt(ExpDate[0])-Integer.parseInt(currentDate[0]);                            
                            }
                            if(months<Integer.parseInt(currentDate[1]))
                                {
                                    months=(months+12)-Integer.parseInt(currentDate[1]);
                                    years=(years-1)-Integer.parseInt(currentDate[2]);
                                }
                            else
                            {
                                months=months-Integer.parseInt(currentDate[1]);
                                years=years-Integer.parseInt(currentDate[2]);
                            }
                            days=days+months*30+years*365;
                            Type=String.valueOf(days)+" to EXP";

                        }
                    }
                }
                if (days<=30 || Type.equalsIgnoreCase("Expired")) {
                    addRow(new Object[]{getID_ope,getEN,getExp,Type} );
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e +"  in ID_operation==> "+idop);
            
        }
            }
        
        
    }
    public void deleteAllRow()
    {
        DefaultTableModel dm = (DefaultTableModel) tabExp.getModel();
        int rowCount = dm.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
            }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabExp = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Date = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        cbox = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        txt1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EXP & Quantity");

        tabExp.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tabExp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OperatinID", "EN Name", "Exp Dates / Quantity", "Remainder To Exp / Tablets"
            }
        ));
        tabExp.setRowHeight(30);
        jScrollPane1.setViewportView(tabExp);

        Date.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(Date);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-back-40.png"))); // NOI18N
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        cbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EXP", "Quantity" }));

        jButton1.setText("get");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addGap(74, 74, 74)
                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbox, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 610, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        home h1=new home();
        h1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       list.clear();
       list2.clear();
        String get=(String) cbox.getSelectedItem();
        if (get.equals("EXP")) {
            deleteAllRow();
            expDate();
            
        }
        else if (get.equals("Quantity")) { 
            
            deleteAllRow();
            quantityDown();
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1ActionPerformed

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
            java.util.logging.Logger.getLogger(EXP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EXP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EXP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EXP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EXP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Date;
    private javax.swing.JComboBox cbox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabExp;
    private javax.swing.JTextField txt1;
    // End of variables declaration//GEN-END:variables
}
