/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.v3.pkg0;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author gerges Fci-H
 */
public class sell extends javax.swing.JFrame {

    private int SendListNumber;
    /**
     * Creates new form sell
     */
    ArrayList<SellModel> list=new ArrayList<SellModel>();
    
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    Statement stmt = null;
    
    //  String billNo="";
    Double totalAmount=0.0;
    Double cash=0.0;
    Double balance=0.0;
    Double bHeight=0.0;
    int fontP=7;
    
    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<String> quantity = new ArrayList<>();
    ArrayList<String> itemPrice = new ArrayList<>();
    ArrayList<String> subtotal = new ArrayList<>();
    
    public sell()  {
        
        con=sqlite.connectDB();
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("hospital.png")));
        setLocationRelativeTo(null);
        showData();
        txt1.requestFocus();
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        
        
        
    }
     public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double bodyHeight = bHeight;  
    double headerHeight = 5.0;                  
    double footerHeight = 5.0;        
    double width = cm_to_pp(8); 
    double height = cm_to_pp(headerHeight+bodyHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(0,10,width,height - cm_to_pp(1));  
            
    pf.setOrientation(PageFormat.PORTRAIT);  
    pf.setPaper(paper);    

    return pf;
}
      protected static double cm_to_pp(double cm)
    {            
	        return toPPI(cm * 0.393600787);            
    }
 
protected static double toPPI(double inch)
    {            
	        return inch * 72d;            
    }

    public void SimpleOperations(java.awt.event.KeyEvent evt,JTextField txtWant,int NumberCheck)
    {
        if(evt.getKeyCode()==KeyEvent.VK_F12)
        {
            txt7.requestFocus(); 
        }
        if(evt.getKeyCode()==KeyEvent.VK_DELETE)
        {
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            if(!txt5.getText().equals("1"))
            {
                txt5.setText("1");
            }
            txt6.setText("");
            txt1.requestFocus();
        }
        if(evt.getKeyCode()==KeyEvent.VK_SHIFT)
        {
            if(cb.getSelectedItem()=="Box")
                cb.setSelectedItem("Tablet");
            else if(cb.getSelectedItem()=="Tablet")
                cb.setSelectedItem("Box");
        }
        if(!txtWant.getText().equalsIgnoreCase(""))
        {
            int flag=0,flagTab=0;
            int QuantityNeed=Integer.parseInt(txt5.getText());
            String CheckID;
             
            if(evt.getKeyCode()==KeyEvent.VK_CONTROL){
                for (int i = 0; i < tab1.getRowCount(); i++) {
                    int TabletsHave=Integer.parseInt((String) tab1.getValueAt(i, 5));
                    int Qua=Integer.parseInt((String) tab1.getValueAt(i, 4));
                    CheckID=String.valueOf((String) tab1.getValueAt(i, NumberCheck));
                    if(cb.getSelectedItem().equals("Box")&&Qua>=QuantityNeed && CheckID.equalsIgnoreCase(txtWant.getText()))
                    {
                        flag=1;
                        txt1.setText((String) tab1.getValueAt(i,0));
                        txt2.setText((String) tab1.getValueAt(i,1));
                        txt3.setText((String) tab1.getValueAt(i,2));
                        txt4.setText((String) tab1.getValueAt(i,3));
                        txt6.setText((String) tab1.getValueAt(i,7));
                        Retail.setText((String) tab1.getValueAt(i,9));
                        lbl_tablePerBox.setText((String) tab1.getValueAt(i,6));


                        

                        String s1=txt1.getText();
                        String s2=txt2.getText();
                        String s3=txt3.getText();
                        String s4=txt4.getText();
                        String s5=txt5.getText();
                        String s7=txt7.getText();

                        String s8=String.valueOf(
                                Double.parseDouble(txt6.getText()));
                        double format_s8=Double.parseDouble(s8);
                        s8=String.format("%.2f", format_s8);

                        int s9=(int) tab1.getValueAt(i, 12);

                        //Date
                        Date d=new Date();
                        SimpleDateFormat s=new SimpleDateFormat("dd/MM/yyyy");
                        String Date1=s.format(d);
                        //Time
                        Calendar cal=new GregorianCalendar();
                        int hour=cal.get(Calendar.HOUR_OF_DAY);
                        int minute=cal.get(Calendar.MINUTE);
                        int second=cal.get(Calendar.SECOND);
                        String Time=String.valueOf(hour+":"+minute+":"+second);



                       if(cb.getSelectedItem().equals("Box"))
                        {

                                double price =Double.parseDouble(txt6.getText())*Double.parseDouble(txt5.getText());
                                String s6=String.valueOf(price-((price*Double.parseDouble(s7)/100)));
                                double format_s6=Double.parseDouble(s6);
                                s6=String.format("%.2f", format_s6);
                                int moreThan1=Integer.parseInt((String) tab1.getValueAt(i, 4));



                                  String ret=String.valueOf(Double.parseDouble(Retail.getText())
                                          *Double.parseDouble(txt5.getText()));
                                  double format_ret=Double.parseDouble(ret);
                                  ret=String.format("%.2f", format_ret);
                                  String AllQuantity=(String) tab1.getValueAt(i,4);
                                  String qs=String.valueOf(Integer.parseInt(AllQuantity)- Integer.parseInt(s5));
                                  int totalTab=(Integer.parseInt((String) tab1.getValueAt(i,5)))
                                          -(Integer.parseInt((String) tab1.getValueAt(i,6))*Integer.parseInt(s5));
                                  String tables=String.valueOf(totalTab);
                                  int idop=(int) tab1.getValueAt(i,12);

                                  try {
                                        String q="UPDATE medicine SET quantity='"+qs+"'"
                                                + " , Tablets='"+tables+"'  WHERE idope="+idop+" ";
                                        pst=con.prepareStatement(q);
                                        pst.executeUpdate();
                                      } catch (Exception e) {
                                        JOptionPane.showMessageDialog(null, e);
                                      }finally
                                        {
                                            try {
                                                pst.close();
                                            } catch (Exception e) {
                                             JOptionPane.showMessageDialog(null, e);   
                                            }
                                        }
                                        showData();
                                        s5=s5+"-B";
                                        addRow(new Object[]{s1,s2,s3,s5,s8,s6,s7,s9,Date1,Time,lbl_tablePerBox.getText()} );
                                        try {
                                            stmt=con.createStatement();
                                        } catch (SQLException ex) {
                                            Logger.getLogger(sell.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        try {
                                            String query="INSERT INTO sellReview (ID,En_Name,Ar_Name,Quantity,"
                                        + "Price,Date,Time,User,Retail,company)"
                                        + " VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s5+"'"
                                                    + ",'"+s6+"','"+Date1+"','"+Time+"','"+new logInfo().getName()+"',"
                                                    + "'"+ret+"','"+tab1.getValueAt(i, 11)+"' )";
                                            stmt.executeUpdate(query);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(sell.class.getName()).log(Level.SEVERE, null, ex);
                                        }finally
                                            {
                                                try {
                                                    pst.close();
                                                    rs.close();
                                                } catch (Exception e) {
                                                 JOptionPane.showMessageDialog(null, e);   
                                                }
                                            }

                                txt1.setText("");
                                txt2.setText("");
                                txt3.setText("");
                                txt4.setText("");
                                if(!txt5.getText().equals("1"))
                                {
                                    txt5.setText("1");
                                }
                                txt6.setText("");
                                txt1.requestFocus();

                        }
                        sumAllprice();
                        break;
                        

                    }
                    else if(cb.getSelectedItem().equals("Tablet")&&TabletsHave>=QuantityNeed && CheckID.equalsIgnoreCase(txtWant.getText())){
                        flagTab=1;
                        txt1.setText((String) tab1.getValueAt(i,0));
                        txt2.setText((String) tab1.getValueAt(i,1));
                        txt3.setText((String) tab1.getValueAt(i,2));
                        txt4.setText((String) tab1.getValueAt(i,3));
                        txt6.setText((String) tab1.getValueAt(i,7));
                        Retail.setText((String) tab1.getValueAt(i,9));
                        lbl_tablePerBox.setText((String) tab1.getValueAt(i,6));


                        

                        String s1=txt1.getText();
                        String s2=txt2.getText();
                        String s3=txt3.getText();
                        String s4=txt4.getText();
                        String s5=txt5.getText();
                        String s7=txt7.getText();

                        String s8=String.valueOf(
                                Double.parseDouble(txt6.getText()));
                        double format_s8=Double.parseDouble(s8);
                        s8=String.format("%.2f", format_s8);

                        int s9=(int) tab1.getValueAt(i, 12);

                        //Date
                        Date d=new Date();
                        SimpleDateFormat s=new SimpleDateFormat("dd/MM/yyyy");
                        String Date1=s.format(d);
                        //Time
                        Calendar cal=new GregorianCalendar();
                        int hour=cal.get(Calendar.HOUR_OF_DAY);
                        int minute=cal.get(Calendar.MINUTE);
                        int second=cal.get(Calendar.SECOND);
                        String Time=String.valueOf(hour+":"+minute+":"+second);

                        
                        
                        
                        if(cb.getSelectedItem().equals("Tablet"))
                        {
                            
                                int tableNumber=Integer.parseInt((String) tab1.getValueAt(i, 5));
                                if (Integer.parseInt(s5)<=tableNumber && Integer.parseInt(s5) !=0) {
                                    double tableRetail=Double.parseDouble(Retail.getText())/Double.parseDouble(lbl_tablePerBox.getText());
                                    double tableprice=Double.parseDouble(txt6.getText())/Double.parseDouble(lbl_tablePerBox.getText());

                                    String ret=String.valueOf(tableRetail*Double.parseDouble(txt5.getText()));
                                    double format_ret=Double.parseDouble(ret);
                                     ret=String.format("%.2f", format_ret);

                                    double price =tableprice*Double.parseDouble(txt5.getText());
                                    String s6=String.valueOf(price-((price*Double.parseDouble(s7)/100)));
                                    double format_s6=Double.parseDouble(s6);
                                    s6=String.format("%.2f", format_s6);

                                    String AllTable=String.valueOf(tableNumber);
                                    String tablesRe=String.valueOf(tableNumber- Integer.parseInt(s5));
                                    int quantityRe=Integer.parseInt(tablesRe)/Integer.parseInt((String) tab1.getValueAt(i, 6));
                                    int idop=(int) tab1.getValueAt(i,12);
                                    try {
                                        String q="UPDATE medicine SET quantity='"+quantityRe+"' , "
                                                + "Tablets='"+tablesRe+"'  WHERE idope="+idop+" ";
                                        pst=con.prepareStatement(q);
                                        pst.executeUpdate();
                                      } catch (Exception e) {
                                        JOptionPane.showMessageDialog(null, e);
                                      }finally
                                        {
                                            try {
                                                pst.close();
                                                rs.close();
                                            } catch (Exception e) {
                                             JOptionPane.showMessageDialog(null, e);   
                                            }
                                        }
                                        showData();
                                        s5=s5+"-T";
                                        addRow(new Object[]{s1,s2,s3,s5,String.format("%.2f", tableprice)
                                                ,s6,s7,s9,Date1,Time,lbl_tablePerBox.getText()} );
                                        try {
                                            stmt=con.createStatement();
                                        } catch (SQLException ex) {
                                            Logger.getLogger(sign_up.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        try {
                                            String query="INSERT INTO sellReview (ID,En_Name,Ar_Name,Quantity,"
                                        + "Price,Date,Time,User,Retail,company)"
                                        + " VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s5+"',"
                                                    + "'"+s6+"','"+Date1+"','"+Time+"','"+new logInfo().getName()+"',"
                                                    + "'"+ret+"','"+tab1.getValueAt(i, 11)+"' )";
                                            stmt.executeUpdate(query);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(sell.class.getName()).log(Level.SEVERE, null, ex);
                                        }finally
                                        {
                                            try {
                                                pst.close();
                                                rs.close();
                                            } catch (Exception e) {
                                             JOptionPane.showMessageDialog(null, e);   
                                            }
                                        }

                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(this, "quantity over");
                                }
                                txt1.setText("");
                                txt2.setText("");
                                txt3.setText("");
                                txt4.setText("");
                                if(!txt5.getText().equals("1"))
                                {
                                    txt5.setText("1");
                                }
                                txt6.setText("");
                                txt1.requestFocus();
                            
                            sumAllprice();
                            break;
                        }
                    }

                }
                if(cb.getSelectedItem().equals("Box") && flag==0)
                {
                 JOptionPane.showMessageDialog(this, "Not found");   
                }
                else if(cb.getSelectedItem().equals("Tablet") && flagTab==0)
                {
                 JOptionPane.showMessageDialog(this, "Not found");   
                }
                flag=0;
                flagTab=0;
            }
        }
    }
    public void quantitysum()
    {
        int q = Integer.parseUnsignedInt(txt5.getText());
        q++;
        txt5.setText(String.valueOf(q));   
    }
    public void quantityMin()
    {
        int q = Integer.parseUnsignedInt(txt5.getText());
        if(q>0)
        {
        q--;
        txt5.setText(String.valueOf(q)); 
        }
    }
    
    
    public void showData()
    {
        try {
            String q="select id,en_name,ar_name,effect"
                    + ",quantity,Tablets,Tablets_per_box,sell"
                    + ",discount,retail,exp,company,idope from medicine";
            pst=con.prepareStatement(q);
            rs=pst.executeQuery();
            tab1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        finally
            {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);   
                }
            }
    }
    public void loadTable()
    {
        if(txt1.getText().equalsIgnoreCase(""))
        {
            showData();
        }
        
    }
    public void addRow(Object [] dataRow)
    {
        DefaultTableModel m1=(DefaultTableModel) tab2.getModel();
        m1.addRow(dataRow);
    }
    public void getinfo(int col,TextField txt)
    {
            txt.setText((String) tab1.getValueAt(0, col));
    }
    public void FindRemainderQuantity(int operation)
    {
        try {
            String q="select * from medicine where idope="+operation+" ";
            pst=con.prepareStatement(q);
            rs=pst.executeQuery();
            String remind=rs.getString("quantity");
            operation_lbl.setText(remind);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            
        }finally
            {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);   
                }
            }
       
        
    }
    public void FindRemainderTables(int operation)
    {
        try {
            String q="select * from medicine where idope="+operation+" ";
            pst=con.prepareStatement(q);
            rs=pst.executeQuery();
            String remind=rs.getString("Tablets");
            lbl_tableRe.setText(remind);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            
        }finally
            {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);   
                }
            }
       
        
    }
    
    public void deleteRow(int r)
    {
        DefaultTableModel m1=(DefaultTableModel) tab2.getModel();
        m1.removeRow(r);
    }
    
    public void sumAllprice()
    {
        double sum=0;
        for (int i = 0; i < tab2.getRowCount(); i++) {
            double total=Double.parseDouble((String) tab2.getValueAt(i, 5));
            sum=sum+total;
           
        }
        lbl_SumAll.setText(String.format("%.2f", sum));
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton10 = new javax.swing.JButton();
        txt1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt6 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt5 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt7 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lbl9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab1 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        txt4 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        lbl_tableRe = new javax.swing.JLabel();
        lbl_tablePerBox = new javax.swing.JLabel();
        operation_lbl = new javax.swing.JLabel();
        Retail = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab2 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cb = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_bill = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbl_SumAll = new javax.swing.JLabel();
        txt_sum = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtFont = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sell_Medicine");

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-back-40.png"))); // NOI18N
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        txt1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txt1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt1FocusGained(evt);
            }
        });
        txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1ActionPerformed(evt);
            }
        });
        txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt1KeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("ID:");

        txt3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt3ActionPerformed(evt);
            }
        });
        txt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt3KeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Ar Name:");

        txt2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt2KeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("EN Name:");

        txt6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txt6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt6ActionPerformed(evt);
            }
        });
        txt6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt6KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt6KeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("price:");

        txt5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txt5.setText("1");
        txt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt5ActionPerformed(evt);
            }
        });
        txt5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt5KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt5KeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setText("Quantity:");

        txt7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txt7.setText("0");
        txt7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt7FocusLost(evt);
            }
        });
        txt7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt7ActionPerformed(evt);
            }
        });
        txt7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt7KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt7KeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setText("Discount:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel7.setText("%");

        jButton1.setBackground(new java.awt.Color(51, 204, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText("Add");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbl9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbl9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl9.setText("0.0");

        tab1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tab1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab1.setRowHeight(30);
        tab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab1);
        if (tab1.getColumnModel().getColumnCount() > 0) {
            tab1.getColumnModel().getColumn(6).setMinWidth(0);
            tab1.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setText("Effective Material:");

        txt4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt4ActionPerformed(evt);
            }
        });
        txt4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt4KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt4KeyTyped(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 204, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3.setText("Sell");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lbl_tableRe.setText("jLabel2");

        lbl_tablePerBox.setText("Table");

        Retail.setText("Retail");

        tab2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tab2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "English", "Arabic", "quantity", "Price", "Total price", "Discount", "ID_Operation", "Date", "Time", "Tablet_per_box"
            }
        ));
        tab2.setRowHeight(26);
        jScrollPane1.setViewportView(tab2);
        if (tab2.getColumnModel().getColumnCount() > 0) {
            tab2.getColumnModel().getColumn(2).setMinWidth(0);
            tab2.getColumnModel().getColumn(2).setMaxWidth(0);
            tab2.getColumnModel().getColumn(7).setMinWidth(0);
            tab2.getColumnModel().getColumn(7).setMaxWidth(0);
            tab2.getColumnModel().getColumn(8).setMinWidth(0);
            tab2.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        jButton4.setBackground(new java.awt.Color(255, 0, 0));
        jButton4.setText("Remove");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Select:");

        cb.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Box", "Tablet" }));
        cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActionPerformed(evt);
            }
        });
        cb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbKeyPressed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/refresh.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        txt_bill.setColumns(20);
        txt_bill.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        txt_bill.setRows(5);
        jScrollPane3.setViewportView(txt_bill);

        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Total=");

        lbl_SumAll.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_SumAll.setText("0.0");

        jButton6.setText("Ok");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4.setText("Sum:");

        txtFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFontActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton10)
                                    .addComponent(Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(operation_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(lbl_tablePerBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel1))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(155, 155, 155)
                                        .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cb, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(221, 221, 221)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(223, 223, 223)
                                        .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbl9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel16)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_sum, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton6))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(lbl_tableRe, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_SumAll, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(301, 301, 301))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFont, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(jButton4)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Retail, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(lbl_tablePerBox, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(lbl_tableRe, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(153, 153, 153)
                                .addComponent(operation_lbl)
                                .addGap(143, 143, 143))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(lbl_SumAll)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txt_sum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton6)
                                                .addComponent(jLabel4))
                                            .addComponent(jLabel1)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(lbl9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        
        home h1=new home();
        h1.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txt6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt6ActionPerformed

    private void txt6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt6KeyReleased
        double x=Double.parseDouble(txt6.getText());
        if(txt7.getText().equals("0"))
        {
            lbl9.setText(String.format("%.2f", x));
        }
        
    }//GEN-LAST:event_txt6KeyReleased

    private void txt5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt5ActionPerformed
        
    }//GEN-LAST:event_txt5ActionPerformed

    private void txt7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt7ActionPerformed

    }//GEN-LAST:event_txt7ActionPerformed

    private void txt7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt7KeyReleased
        double price=Double.parseDouble(txt6.getText());
        double dis=Double.parseDouble(txt7.getText());
        dis=(price*dis)/100;
        price=price-dis;
        lbl9.setText(String.format("%.2f", price));

    }//GEN-LAST:event_txt7KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       /* String select=(String)cb.getSelectedItem();
        if(select.equalsIgnoreCase("Box"))
        {
            txt1.setText(select);
        }
        else if(select.equalsIgnoreCase("Table"))
        {
            txt1.setText(select);
        }*/
        
        String s1=txt1.getText();
        String s2=txt2.getText();
        String s3=txt3.getText();
        String s4=txt4.getText();
        String s5=txt5.getText();
        String s7=txt7.getText();
        
        String s8=String.valueOf(
                Double.parseDouble(txt6.getText()));
        double format_s8=Double.parseDouble(s8);
        s8=String.format("%.2f", format_s8);
        int se=tab1.getSelectedRow();
        int s9=(int) tab1.getValueAt(se, 12);
        
        //Date
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("dd/MM/yyyy");
        String Date1=s.format(d);
        //Time
        Calendar cal=new GregorianCalendar();
        int hour=cal.get(Calendar.HOUR_OF_DAY);
        int minute=cal.get(Calendar.MINUTE);
        int second=cal.get(Calendar.SECOND);
        String Time=String.valueOf(hour+":"+minute+":"+second);
        
        
        
       if(cb.getSelectedItem().equals("Box"))
        {
            if(tab1.getSelectedRowCount()==1)
            {
                double price =Double.parseDouble(txt6.getText())*Double.parseDouble(txt5.getText());
                String s6=String.valueOf(price-((price*Double.parseDouble(s7)/100)));
                double format_s6=Double.parseDouble(s6);
                s6=String.format("%.2f", format_s6);
                int moreThan1=Integer.parseInt((String) tab1.getValueAt(se, 4));
                
                
                if(Integer.parseInt(s5)<=moreThan1 && Integer.parseInt(s5) !=0)
                {
                  String ret=String.valueOf(Double.parseDouble(Retail.getText())
                          *Double.parseDouble(txt5.getText()));
                  double format_ret=Double.parseDouble(ret);
                  ret=String.format("%.2f", format_ret);
                  String AllQuantity=(String) tab1.getValueAt(se,4);
                  String qs=String.valueOf(Integer.parseInt(AllQuantity)- Integer.parseInt(s5));
                  int totalTab=(Integer.parseInt((String) tab1.getValueAt(se,5)))
                          -(Integer.parseInt((String) tab1.getValueAt(se,6))*Integer.parseInt(s5));
                  String tables=String.valueOf(totalTab);
                  int idop=(int) tab1.getValueAt(se,12);

                  try {
                        String q="UPDATE medicine SET quantity='"+qs+"'"
                                + " , Tablets='"+tables+"'  WHERE idope="+idop+" ";
                        pst=con.prepareStatement(q);
                        pst.executeUpdate();
                      } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                      }finally
                        {
                            try {
                                pst.close();
                            } catch (Exception e) {
                             JOptionPane.showMessageDialog(null, e);   
                            }
                        }
                        showData();
                        s5=s5+"-B";
                        addRow(new Object[]{s1,s2,s3,s5,s8,s6,s7,s9,Date1,Time,lbl_tablePerBox.getText()} );
                        try {
                            stmt=con.createStatement();
                        } catch (SQLException ex) {
                            Logger.getLogger(sell.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            String query="INSERT INTO sellReview (ID,En_Name,Ar_Name,Quantity,"
                        + "Price,Date,Time,User,Retail,company)"
                        + " VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s5+"'"
                                    + ",'"+s6+"','"+Date1+"','"+Time+"','"+new logInfo().getName()+"',"
                                    + "'"+ret+"','"+tab1.getValueAt(se, 11)+"' )";
                            stmt.executeUpdate(query);
                        } catch (SQLException ex) {
                            Logger.getLogger(sell.class.getName()).log(Level.SEVERE, null, ex);
                        }finally
                            {
                                try {
                                    pst.close();
                                    rs.close();
                                } catch (Exception e) {
                                 JOptionPane.showMessageDialog(null, e);   
                                }
                            }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "quantity over");
                }
                txt1.setText("");
                txt2.setText("");
                txt3.setText("");
                txt4.setText("");
                if(!txt5.getText().equals("1"))
                {
                    txt5.setText("1");
                }
                txt6.setText("");
                txt1.requestFocus();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"you must Select one Row");
            }
        }
        else if(cb.getSelectedItem().equals("Tablet"))
        {
            if (tab1.getSelectedRowCount()==1) {
                int tableNumber=Integer.parseInt((String) tab1.getValueAt(se, 5));
                
                if (Integer.parseInt(s5)<=tableNumber && Integer.parseInt(s5) !=0) {
                    double tableRetail=Double.parseDouble(Retail.getText())/Double.parseDouble(lbl_tablePerBox.getText());
                    double tableprice=Double.parseDouble(txt6.getText())/Double.parseDouble(lbl_tablePerBox.getText());
                    
                    String ret=String.valueOf(tableRetail*Double.parseDouble(txt5.getText()));
                    double format_ret=Double.parseDouble(ret);
                     ret=String.format("%.2f", format_ret);
                  
                    double price =tableprice*Double.parseDouble(txt5.getText());
                    String s6=String.valueOf(price-((price*Double.parseDouble(s7)/100)));
                    double format_s6=Double.parseDouble(s6);
                    s6=String.format("%.2f", format_s6);
                    
                    String AllTable=String.valueOf(tableNumber);
                    String tablesRe=String.valueOf(tableNumber- Integer.parseInt(s5));
                    int quantityRe=Integer.parseInt(tablesRe)/Integer.parseInt((String) tab1.getValueAt(se, 6));
                    int idop=(int) tab1.getValueAt(se,12);
                    try {
                        String q="UPDATE medicine SET quantity='"+quantityRe+"' , "
                                + "Tablets='"+tablesRe+"'  WHERE idope="+idop+" ";
                        pst=con.prepareStatement(q);
                        pst.executeUpdate();
                      } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                      }finally
                        {
                            try {
                                pst.close();
                                rs.close();
                            } catch (Exception e) {
                             JOptionPane.showMessageDialog(null, e);   
                            }
                        }
                        showData();
                        s5=s5+"-T";
                        addRow(new Object[]{s1,s2,s3,s5,String.format("%.2f", tableprice),s6,s7,s9,Date1,Time,lbl_tablePerBox.getText()} );
                        try {
                            stmt=con.createStatement();
                        } catch (SQLException ex) {
                            Logger.getLogger(sign_up.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            String query="INSERT INTO sellReview (ID,En_Name,Ar_Name,Quantity,"
                        + "Price,Date,Time,User,Retail,company)"
                        + " VALUES ('"+s1+"','"+s2+"','"+s3+"','"+s5+"',"
                                    + "'"+s6+"','"+Date1+"','"+Time+"','"+new logInfo().getName()+"','"+ret+"','"+tab1.getValueAt(se, 11)+"' )";
                            stmt.executeUpdate(query);
                        } catch (SQLException ex) {
                            Logger.getLogger(sell.class.getName()).log(Level.SEVERE, null, ex);
                        }finally
                        {
                            try {
                                pst.close();
                                rs.close();
                            } catch (Exception e) {
                             JOptionPane.showMessageDialog(null, e);   
                            }
                        }
                        
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "quantity over");
                }
                txt1.setText("");
                txt2.setText("");
                txt3.setText("");
                txt4.setText("");
                if(!txt5.getText().equals("1"))
                {
                    txt5.setText("1");
                }
                txt6.setText("");
                txt1.requestFocus();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"you must Select one Row");
            }
        }
       sumAllprice();
        //int moreThan=Integer.parseInt((String) tab1.getValueAt(0, 4));
        /*if(tab1.getSelectedRow()>0)
        {
            
            int moreThan1=Integer.parseInt((String) tab1.getValueAt(se, 4));
            if(moreThan1>0)
            {
                if(Integer.parseInt(s5)<=moreThan1)
                {
                  String AllQuantity=(String) tab1.getValueAt(se,4);
          
                  String qs=String.valueOf(Integer.parseInt(AllQuantity)- Integer.parseInt(s5));
                  int idop=(int) tab1.getValueAt(se,12);
                  
                  try {
                        String q="UPDATE medicine SET quantity='"+qs+"'  WHERE idope="+idop+" ";
                        pst=con.prepareStatement(q);
                        pst.executeUpdate();
                      } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                      }
                        showData();
                        addRow(new Object[]{s1,s2,s3,s5,s8,s6,s7} );
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "quantity over");
                }
            
            }
            else
            {
                JOptionPane.showMessageDialog(this, "no select");
            }*/
        //}
       /* else{
           
            if(moreThan>0 && tab1.getRowCount()<=3)
            {
                String AllQuantity=(String) tab1.getValueAt(0,4);
                String qs=String.valueOf(Integer.parseInt(AllQuantity)- Integer.parseInt(s5));
                int idop=(int) tab1.getValueAt(0,12);
                try {
                        String q="UPDATE medicine SET quantity='"+qs+"'  WHERE idope="+idop+" ";
                        pst=con.prepareStatement(q);
                        pst.executeUpdate();
                      } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                      }
                addRow(new Object[]{s1,s2,s3,s5,s8,s6,s7} );
                tab1.setValueAt(qs, 0, 4);
            }
            else
            {
                System.out.println("error");
            }
        }*/
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt4ActionPerformed
        
    }//GEN-LAST:event_txt4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        for (int i=(tab2.getRowCount()-1) ; i >=0; i--) {
            deleteRow(i);
        }
        lbl_SumAll.setText("0.0");
        
                
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt5KeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_RIGHT){quantitysum();}
         else if(evt.getKeyCode()==KeyEvent.VK_LEFT){quantityMin();}
         else if(evt.getKeyCode()==KeyEvent.VK_DOWN){txt6.requestFocus();}
         else if(evt.getKeyCode()==KeyEvent.VK_UP){txt4.requestFocus();}
         SimpleOperations(evt,txt1,0);
         
    }//GEN-LAST:event_txt5KeyPressed

    private void txt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt3ActionPerformed

    private void txt3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){quantitysum();}
         else if(evt.getKeyCode()==KeyEvent.VK_LEFT){quantityMin();}
         else if(evt.getKeyCode()==KeyEvent.VK_DOWN){txt4.requestFocus();}
         else if(evt.getKeyCode()==KeyEvent.VK_UP){txt2.requestFocus();}
        SimpleOperations(evt,txt3,2);
        
    }//GEN-LAST:event_txt3KeyPressed

    private void txt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyPressed
        
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){quantitysum();}
         else if(evt.getKeyCode()==KeyEvent.VK_LEFT){quantityMin();}
         else if(evt.getKeyCode()==KeyEvent.VK_DOWN){txt2.requestFocus();}
        
        SimpleOperations(evt,txt1,0);
           
        
        
    }//GEN-LAST:event_txt1KeyPressed

    private void txt1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1FocusGained

    }//GEN-LAST:event_txt1FocusGained

    private void txt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){quantitysum();}
         else if(evt.getKeyCode()==KeyEvent.VK_LEFT){quantityMin();}
         else if(evt.getKeyCode()==KeyEvent.VK_DOWN){txt3.requestFocus();}
         else if(evt.getKeyCode()==KeyEvent.VK_UP){txt1.requestFocus();
            loadTable();
         }
        SimpleOperations(evt,txt2,1);
        
    }//GEN-LAST:event_txt2KeyPressed

    private void txt4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt4KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){quantitysum();}
         else if(evt.getKeyCode()==KeyEvent.VK_LEFT){quantityMin();}
         else if(evt.getKeyCode()==KeyEvent.VK_DOWN){txt5.requestFocus();}
         else if(evt.getKeyCode()==KeyEvent.VK_UP){txt3.requestFocus();}
        SimpleOperations(evt,txt1,0);
    }//GEN-LAST:event_txt4KeyPressed

    private void txt6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt6KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){quantitysum();}
         else if(evt.getKeyCode()==KeyEvent.VK_LEFT){quantityMin();}
         else if(evt.getKeyCode()==KeyEvent.VK_DOWN){txt7.requestFocus();}
         else if(evt.getKeyCode()==KeyEvent.VK_UP){txt5.requestFocus();}
        SimpleOperations(evt,txt1,0);
    }//GEN-LAST:event_txt6KeyPressed

    private void txt7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt7KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){quantitysum();}
         else if(evt.getKeyCode()==KeyEvent.VK_LEFT){quantityMin();}
         else if(evt.getKeyCode()==KeyEvent.VK_UP){txt6.requestFocus();}
        SimpleOperations(evt,txt1,0);
    }//GEN-LAST:event_txt7KeyPressed

    private void txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1ActionPerformed

    }//GEN-LAST:event_txt1ActionPerformed

    private void txt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyReleased

        try {
            String q="select id,en_name,ar_name,effect,quantity,Tablets"
                    + ",Tablets_per_box,sell,discount,retail,exp,company,idope"
                    + " from medicine where id like\"%"+txt1.getText()+"%\"";
            pst=con.prepareStatement(q);
            rs=pst.executeQuery();
            tab1.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }finally
            {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);   
                }
            }
        
        
    }//GEN-LAST:event_txt1KeyReleased

    private void cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActionPerformed
        
    }//GEN-LAST:event_cbActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        showData();
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txt2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt2KeyReleased
        try {
            String q="select id,en_name,ar_name,effect,quantity,Tablets"
                    + ",Tablets_per_box,sell,discount,retail,exp,company,idope"
                    + " from medicine where en_name like\"%"+txt2.getText()+"%\"";
            pst=con.prepareStatement(q);
            rs=pst.executeQuery();
            tab1.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }finally
            {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);   
                }
            }
    }//GEN-LAST:event_txt2KeyReleased

    private void txt3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt3KeyReleased
        try {
            String q="select id,en_name,ar_name,effect,quantity,Tablets"
                    + ",Tablets_per_box,sell,discount,retail,exp,company,idope"
                    + " from medicine where ar_name like\"%"+txt3.getText()+"%\"";
            pst=con.prepareStatement(q);
            rs=pst.executeQuery();
            tab1.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }finally
            {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);   
                }
            }
    }//GEN-LAST:event_txt3KeyReleased

    private void cbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_RIGHT){quantitysum();}
         else if(evt.getKeyCode()==KeyEvent.VK_LEFT){quantityMin();}
    }//GEN-LAST:event_cbKeyPressed

    private void txt5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt5KeyReleased
        
    }//GEN-LAST:event_txt5KeyReleased

    private void txt7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt7FocusLost
        if(txt7.getText().equals(""))
        {
           txt7.setText("0");
        }
        
    }//GEN-LAST:event_txt7FocusLost

    private void txt7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt7FocusGained
        txt7.setText("");
       
    }//GEN-LAST:event_txt7FocusGained

    private void tab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseClicked
        DefaultTableModel Model =(DefaultTableModel)tab1.getModel();
        int SelectedRow =tab1.getSelectedRow();
        txt1.setText((String) Model.getValueAt(SelectedRow,0));
        txt2.setText((String) Model.getValueAt(SelectedRow,1));
        txt3.setText((String) Model.getValueAt(SelectedRow,2));
        txt4.setText((String) Model.getValueAt(SelectedRow,3));
        txt6.setText((String) Model.getValueAt(SelectedRow,7));
        Retail.setText((String) Model.getValueAt(SelectedRow,9));
        lbl_tablePerBox.setText((String) Model.getValueAt(SelectedRow,6));
        txt1.requestFocus();
        
    }//GEN-LAST:event_tab1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        if(!txt5.getText().equals("1"))
        {
            txt5.setText("1");
        }
        txt6.setText("");
        txt1.requestFocus();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt4KeyReleased
            try {
            String q="select id,en_name,ar_name,effect,quantity,"
                    + "Tablets,Tablets_per_box,sell,discount,retail"
                    + ",exp,company,idope from medicine where effect like \"%"+txt4.getText()+"%\"";
            pst=con.prepareStatement(q);
            rs=pst.executeQuery();
            tab1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
            finally
                {
                    try {
                        pst.close();
                        rs.close();
                    } catch (Exception e) {
                     JOptionPane.showMessageDialog(null, e);   
                    }
                }

    }//GEN-LAST:event_txt4KeyReleased

    private void txt4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt4KeyTyped
        
    }//GEN-LAST:event_txt4KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int re=tab2.getSelectedRow();
        int operation=(int) tab2.getValueAt(re, 7);
        String quan=(String) tab2.getValueAt(re, 3);
        String []Type=quan.split("-");
        String Date2=(String) tab2.getValueAt(re, 8);
        String Time2=(String) tab2.getValueAt(re, 9);
        String ID=(String) tab2.getValueAt(re, 0);
        int ta=Integer.parseInt((String) tab2.getValueAt(re, 10));
        
        
        /*int totalTab=TotalQuantitiy * Integer.parseInt((String) tab1.getValueAt(se,6));
        String tables=String.valueOf(totalTab);*/
        if(Type[1].equals("B"))
        {
            int quanInt=Integer.parseInt(Type[0]);
            FindRemainderQuantity(operation);
            int RemainQ=Integer.parseInt(operation_lbl.getText());
            int TotalQuantitiy = RemainQ +quanInt;
            FindRemainderTables(operation);
            int RemainTable=Integer.parseInt(lbl_tableRe.getText());
           // int TotaTables=RemainTable+Integer.parseInt(Type[0])*Integer.parseInt((String) tab2.getValueAt(re, 10));
            int TotaTables=RemainTable+Integer.parseInt(Type[0])*ta;
            try {
            String q="UPDATE medicine SET quantity='"+TotalQuantitiy+"',"
                    + "Tablets='"+TotaTables+"'  WHERE idope="+operation+" ";
                pst=con.prepareStatement(q);
                pst.executeUpdate();
                pst.close();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }finally
                {
                    try {
                        pst.close();
                        rs.close();
                    } catch (Exception e) {
                     JOptionPane.showMessageDialog(null, e);   
                    }
                }
            deleteRow(re);
            showData();
            operation_lbl.setText("0");
            try {
                String q="DELETE FROM sellReview WHERE Date='"+Date2+"' AND Time='"+Time2+"' AND ID ='"+ID+"' ";
                pst=con.prepareStatement(q);
                pst.executeUpdate();
                pst.close();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }finally
            {
                try {
                    pst.close();
                    
                    
                } catch (Exception e) {
                    
                }
            }
        }
        else if(Type[1].equals("T"))
        {
            
            
            FindRemainderTables(operation);
            int RemainTable=Integer.parseInt(lbl_tableRe.getText());
            int TotaTables=RemainTable+Integer.parseInt(Type[0]);
            FindRemainderQuantity(operation);
            int RemainQ=Integer.parseInt(operation_lbl.getText());
            int ty=Integer.parseInt(Type[0]);
            int Quantity=ty/ta;
            int TotalQuantitiy = TotaTables/ta ;            
            try {
            String q="UPDATE medicine SET quantity='"+TotalQuantitiy+"',Tablets='"+TotaTables+"'  WHERE idope="+operation+" ";
                pst=con.prepareStatement(q);
                pst.executeUpdate();
                pst.close();
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            finally
            {
                try {
                    pst.close();
                    rs.close();
                } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);   
                }
            }
            deleteRow(re);
            showData();
            operation_lbl.setText("0");
            try {
                String q="DELETE FROM sellReview WHERE Date='"+Date2+"' "
                        + "AND Time='"+Time2+"' AND ID ='"+ID+"' ";
                pst=con.prepareStatement(q);
                pst.executeUpdate();
                pst.close();
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }finally
            {
                try {
                    pst.close();
                    
                    
                } catch (Exception e) {
                    
                }
            }
            
            
          
        }
        
        sumAllprice();
        

        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
//        //Date
//        Date d=new Date();
//        SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
//        String Date1=s.format(d);
//        //Time
//        Calendar cal=new GregorianCalendar();
//        int hour=cal.get(Calendar.HOUR_OF_DAY);
//        int minute=cal.get(Calendar.MINUTE);
//        int second=cal.get(Calendar.SECOND);
//        String Time=String.valueOf(hour+":"+minute+":"+second);
//        
//        String printBill="            Mina mekhaeel PHARMACY\n"; 
//        printBill=printBill+"-----------------------------------------\n";
//        
//        printBill=printBill+"\n"+"Name             Quantity          price\n";
//        for (int i = 0; i < tab2.getRowCount(); i++) {
//            
//            String en=(String) tab2.getValueAt(i, 1);
//            en=String.format("%-20s",en);
//            String quantity=(String) tab2.getValueAt(i, 3);
//            quantity=String.format("%-8s",quantity);
//            String price=(String) tab2.getValueAt(i, 5);
//            double pp=Double.parseDouble(price);
//            price=String.format("%-7.2f",pp);
//            printBill=printBill+"\n"+en+""+quantity+"       "+price+"\n_________________________________________\n";
//        }
//        double tot=Double.parseDouble(lbl_SumAll.getText());
//        String Tot=String.format("%.2f", tot);
//        printBill=printBill+"\n----------------"+"\nTOTAl="+Tot+"\n----------------"+"\n\nTime "
//                +Time+"\nDate "+Date1+"\n"+"\nبرجاء الأحتفاظ بالفاتورة فى\n حالة الأستبدال أو الأسترجاع خلال 72 ساعة";
//        txt_bill.setText(printBill);
//        try {
//            txt_bill.print();
//        } catch (PrinterException ex) {
//            Logger.getLogger(sell.class.getName()).log(Level.SEVERE, null, ex);
//        }
//               
//        for (int i=(tab2.getRowCount()-1) ; i >=0; i--) {
//            deleteRow(i);
//        } 
//        lbl_SumAll.setText("0.0");
        
        //***************************************************************************************************//
        if(!txtFont.getText().trim().equalsIgnoreCase(""))
        {
            fontP=Integer.parseInt(txtFont.getText().trim());
        }
        bHeight = Double.valueOf(tab2.getRowCount());
        //JOptionPane.showMessageDialog(rootPane, bHeight);
        
        PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
             pj.print();
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
        for (int i=(tab2.getRowCount()-1) ; i >=0; i--) {
            deleteRow(i);
        }
        lbl_SumAll.setText("0.0");
  
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int yesSelected=JOptionPane.showConfirmDialog(this, "Are you Sure ?!", 
                "Delete selcted Row",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        //messeage
        if(yesSelected==JOptionPane.YES_OPTION)
        {
            DefaultTableModel Model =(DefaultTableModel)tab1.getModel();
            int SelectedRow =tab1.getSelectedRow();
            
            int quaNew=Integer.parseInt(txt_sum.getText());
            
            int idoperation=(int) Model.getValueAt(SelectedRow,12);
            int tpBox=Integer.parseInt((String) Model.getValueAt(SelectedRow,6));
            
                
                if(cb.getSelectedItem().equals("Box"))
                {
                   int quaNow=Integer.parseInt((String) Model.getValueAt(SelectedRow,4));
                    
                   if(quaNew<=quaNow)
                    {
                        JOptionPane.showMessageDialog(this, "Update Failed");
                    }
                    else
                    {
                        
                        int TableRem=Integer.parseInt((String) Model.getValueAt(SelectedRow,5));
                        
                        int min=quaNew-quaNow;
                        min=min*tpBox;
                        int allTable=TableRem+min;
                        
                        try {
                        stmt=con.createStatement();
                         String query="UPdate medicine set  quantity='"+quaNew+"' ,Tablets='"+allTable+"'  WHERE idope="+idoperation+" "
                                + " ";

                            stmt.executeUpdate(query);
                        } catch (SQLException ex) {
                            Logger.getLogger(sign_up.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                else if(cb.getSelectedItem().equals("Tablet"))
                {
                    int quaNow=Integer.parseInt((String) Model.getValueAt(SelectedRow,5));
                    
                    
                            
                    if(quaNew<=quaNow)
                    {
                        JOptionPane.showMessageDialog(this, "Update Failed");
                    }
                    else
                    {
                        int boxRem=Integer.parseInt((String) Model.getValueAt(SelectedRow,4));
                        int box=quaNew/tpBox;
                        
                        
                        
                        try {
                        stmt=con.createStatement();
                         String query="UPdate medicine set  Tablets='"+quaNew+"', quantity='"+box+"' WHERE idope="+idoperation+" "
                                + " ";

                            stmt.executeUpdate(query);
                        } catch (SQLException ex) {
                            Logger.getLogger(sign_up.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }    
                }
            
        }
        txt_sum.setText("");
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        if(!txt5.getText().equals("1"))
        {
            txt5.setText("1");
        }
        txt6.setText("");
        txt1.requestFocus();
        showData();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFontActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFontActionPerformed

    public class BillPrintable implements Printable {
    
   
    
    
        public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
      //Date
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
        String Date1=s.format(d);
        //Time
        Calendar cal=new GregorianCalendar();
        int hour=cal.get(Calendar.HOUR_OF_DAY);
        int minute=cal.get(Calendar.MINUTE);
        int second=cal.get(Calendar.SECOND);
        String Time=String.valueOf(hour+":"+minute+":"+second);
      
      int r= itemName.size();
      //ImageIcon icon=new ImageIcon("C:UsersccsDocumentsNetBeansProjectsvideo TestPOSInvoicesrcposinvoicemylogo.jpg"); 
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    
            double width = pageFormat.getImageableWidth();                               
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 



          //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
        
        try{
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
           // int headerRectHeighta=40;
            
                
            g2d.setFont(new Font("Monospaced",Font.PLAIN,fontP));
            //g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);y+=yShift+30;
            g2d.drawString("-------------------------------------",12,y);y+=yShift;
            g2d.drawString("         Mina mekhaeel PHARMACY      ",12,y);y+=yShift;
            g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;
            g2d.drawString(" Item Name     Quantity         Price",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
     
//            for(int s=0; s<r; s++)
//            {
//            g2d.drawString(" "+itemName.get(s)+"                            ",10,y);y+=yShift;
//            g2d.drawString("      "+quantity.get(s)+" * "+itemPrice.get(s),10,y); g2d.drawString(subtotal.get(s),160,y);y+=yShift;
//
//            }
            //***********************
            for (int i = 0; i < tab2.getRowCount(); i++) {
            
            String en=(String) tab2.getValueAt(i, 1);
            //en=String.format("%-20s",en);
            String quantity=(String) tab2.getValueAt(i, 3);
            quantity=String.format("%-8s",quantity);
            String price=(String) tab2.getValueAt(i, 5);
            double pp=Double.parseDouble(price);
            price=String.format("%-7.2f",pp);
            
            g2d.drawString(" "+en+"                            ",10,y);y+=yShift;
            g2d.drawString("             "+quantity,25,y);
            g2d.drawString(price,140,y);y+=yShift;
            //g2d.drawString(subtotal.get(s),160,y);y+=yShift;
        }
        double tot=Double.parseDouble(lbl_SumAll.getText());
        String Tot=String.format("%.2f", tot);
          //**************************************
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Total amount: "+tot+"   ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("Date:"+Date1+"   Time:"+Time,10,y);y+=yShift;
            g2d.drawString("       THANK YOU COME AGAIN            ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("   3ش عمر محمود-المنشيه-ميدان الساعه ",10,y);y+=yShift;
            g2d.drawString("  01111786669-01022856274-37829357   ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("برجاء الأحتفاظ بالفاتوره فى حاله      ",10,y);y+=yShift;
            g2d.drawString("الأستبدال أو الأسترجاع خلال72 ساعة",10,y);y+=yShift;       
           

    }
    catch(Exception e){
    e.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
   }
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
            java.util.logging.Logger.getLogger(sell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sell.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sell().setVisible(true);
                
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Retail;
    private javax.swing.JComboBox cb;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl9;
    private javax.swing.JLabel lbl_SumAll;
    private javax.swing.JLabel lbl_tablePerBox;
    private javax.swing.JLabel lbl_tableRe;
    private javax.swing.JLabel operation_lbl;
    private javax.swing.JTable tab1;
    private javax.swing.JTable tab2;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt6;
    private javax.swing.JTextField txt7;
    private javax.swing.JTextField txtFont;
    private javax.swing.JTextArea txt_bill;
    private javax.swing.JTextField txt_sum;
    // End of variables declaration//GEN-END:variables

    private void getinfo(int i, JTextField txt1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
