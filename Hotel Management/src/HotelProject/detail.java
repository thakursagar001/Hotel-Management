package HotelProject;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class detail  implements ActionListener{

    JLabel x;
    private JTextField IDCOUNTtextField_7;
    private JTextField textField;
    private JTextField COUNTtextField_1;
    private JTextField STATEtextField_2;
    private JTextField CITYtextField_3;
    private JTextField ADDtextField_4;
    private JTextField LASTtextField_5;
    private JTextField FIRSTtextField_6;
    private JTextField CUSTtextField_7;
    private JFrame f;
    private JButton btnNewButton;
    private JButton back,pay;
    String cus_Id;
    static Connection conn=null;

    public detail(String s) {

        conn=SQLITE.dbconnector();
        //frame
        cus_Id=s;

        f=new JFrame();
        f.setContentPane(new JLabel(new ImageIcon("F:\\SagarProject\\src\\HotelProject\\bgimage1.jpg")));
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        f.setBounds(0, 0, screenSize.width, screenSize.height);
        f.setResizable(false);
        f.setTitle("GRAPHIC ERA HOTEL AND RESTAURANT");
        f.setLayout(null);
       f.setVisible(false);
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


       //DETAILS LABELS
        JLabel lblDetail = new JLabel("DETAIL");
        lblDetail.setFont(new Font("Book Antiqua", Font.PLAIN, 30));
        lblDetail.setForeground(Color.CYAN);
        lblDetail.setBounds(200, 115, 179, 60);
        f.add(lblDetail);

        JLabel lblNewLabel = new JLabel("FIRST NAME");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNewLabel.setForeground(Color.white);
        lblNewLabel.setBounds(120, 252, 103, 28);
        f.add(lblNewLabel);

        JLabel lblLastName = new JLabel("LAST NAME");
        lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblLastName.setForeground(Color.WHITE);
        lblLastName.setBounds(120, 315, 103, 28);
        f.add(lblLastName);

        JLabel lblCustomerId = new JLabel("CUSTOMER ID");
        lblCustomerId.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblCustomerId.setForeground(Color.WHITE);
        lblCustomerId.setBounds(120, 201, 118, 28);
        f.add(lblCustomerId);

        JLabel lblAddress = new JLabel("ADDRESS");
        lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblAddress.setForeground(Color.WHITE);
        lblAddress.setBounds(120, 368, 103, 28);
        f.add(lblAddress);

        JLabel lblCity = new JLabel("CITY");
        lblCity.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblCity.setForeground(Color.WHITE);
        lblCity.setBounds(120, 417, 103, 28);
        f.add(lblCity);

        JLabel lblCity_1 = new JLabel("STATE");
        lblCity_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblCity_1.setForeground(Color.WHITE);
        lblCity_1.setBounds(120, 456, 103, 28);
        f.add(lblCity_1);

        JLabel lblCountry = new JLabel("COUNTRY");
        lblCountry.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblCountry.setForeground(Color.white);
        lblCountry.setBounds(120, 511, 103, 28);
        f.add(lblCountry);

        JLabel lblIdProff = new JLabel("ID PROFF");
        lblIdProff.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblIdProff.setForeground(Color.white);
        lblIdProff.setBounds(120, 574, 103, 28);
        f.add(lblIdProff);

        //TEXTFIELD
        IDCOUNTtextField_7 = new JTextField();
        IDCOUNTtextField_7.setEditable(false);
        IDCOUNTtextField_7.setBounds(250, 572, 244, 34);
        f.add(IDCOUNTtextField_7);

        JLabel lblBiling = new JLabel("BILL");
        lblBiling.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblBiling.setForeground(Color.cyan);
        lblBiling.setBounds(900, 120, 300, 28);
        f.add(lblBiling);

        textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds(800, 156, 300, 232);
        textField.setFont(new Font("ARIAL",Font.BOLD | Font.ITALIC,30));
        f.add(textField);
        pay=new JButton("pay");
        pay.setFont(new Font("Arial",Font.BOLD,25));
        pay.setForeground(Color.black);
        pay.setBounds(850,420,150,40);
        f.add(pay);
        pay.addActionListener(this);

        //back button
        back=new JButton();
        back.setBounds(120,50,90,50);
        ImageIcon backimg1=new ImageIcon("F:\\SagarProject\\src\\HotelProject\\back.png");
        back.setIcon(backimg1);
        f.add(back);
        back.addActionListener(this);


        //JBUTTON
        btnNewButton = new JButton("ADD ROOM");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    String query3="select room_no from customer where cus_Id=?";
                    PreparedStatement pst3=conn.prepareStatement(query3);
                    pst3.setString(1,cus_Id);
                    ResultSet rst3=pst3.executeQuery();
                    if(rst3.next()){
                        if(rst3.getString("room_no").equals("0")){
                            try{
                                conn.close();
                                new EmpHotel(cus_Id);
                                f.setVisible(false);
                            }
                            catch(Exception e){

                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Room already booked","error",JOptionPane.ERROR_MESSAGE);
                            conn.close();
                            new detail(cus_Id);
                            f.setVisible(false);
                        }
                    }
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnNewButton.setBounds(600, 500, 179, 49);
        f.add(btnNewButton);

        JButton button = new JButton("ADD FOOD");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    conn.close();
                    new EmpFood(cus_Id);
                    f.setVisible(false);
                }
                catch(Exception e){

                }
            }
        });
        button.setFont(new Font("Times New Roman", Font.BOLD, 16));
        button.setBounds(900, 500, 179, 49);
        f.add(button);

        //TEXTFIELD
        COUNTtextField_1 = new JTextField();
        COUNTtextField_1.setEditable(false);
        COUNTtextField_1.setBounds(250, 511, 244, 34);
        f.add(COUNTtextField_1);

        STATEtextField_2 = new JTextField();
        STATEtextField_2.setEditable(false);
        STATEtextField_2.setBounds(250, 454, 244, 34);
        f.add(STATEtextField_2);

        CITYtextField_3 = new JTextField();
        CITYtextField_3.setEditable(false);
        CITYtextField_3.setBounds(250, 415, 244, 34);
        f.add(CITYtextField_3);


        ADDtextField_4 = new JTextField();
        ADDtextField_4.setEditable(false);
        ADDtextField_4.setBounds(250, 366, 244, 34);
        f.add(ADDtextField_4);

        LASTtextField_5 = new JTextField();
        LASTtextField_5.setEditable(false);
        LASTtextField_5.setBounds(250, 313, 244, 34);
        f.add(LASTtextField_5);

        FIRSTtextField_6 = new JTextField();
        FIRSTtextField_6.setEditable(false);
        FIRSTtextField_6.setBounds(250, 257, 244, 34);
        f.add(FIRSTtextField_6);

        CUSTtextField_7 = new JTextField();
        CUSTtextField_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
        CUSTtextField_7.setEditable(false);
        CUSTtextField_7.setBounds(250, 199, 244, 34);
        f.add(CUSTtextField_7);

        try{
            String query="select * from Customer where cus_Id=?";
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setString(1,cus_Id);
            ResultSet rst=pst.executeQuery();
            if(rst.next()){
                CUSTtextField_7.setText(rst.getString(1));
                FIRSTtextField_6.setText(rst.getString(2));
                LASTtextField_5.setText(rst.getString(3));
                ADDtextField_4.setText(rst.getString(4));
                CITYtextField_3.setText(rst.getString(5));
                STATEtextField_2.setText(rst.getString(6));
                COUNTtextField_1.setText(rst.getString(7));
                IDCOUNTtextField_7.setText(rst.getString(8));
                textField.setText("Your bill is "+rst.getString(9));
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
        }
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new CustomerDetails();
            f.setVisible(false);
        }
        if(e.getSource()==pay){
            String query2="";
            try{
                String query="select room_no from customer where cus_Id=?";
                PreparedStatement pst=conn.prepareStatement(query);
                pst.setString(1,cus_Id);
                ResultSet rst=pst.executeQuery();
                if(rst.next()){
                    query2=rst.getString("room_no");

                }

                query="update Hotel set check_in=?,check_out=? where room_no=?";
                pst=conn.prepareStatement(query);
                pst.setString(1,"00");
                pst.setString(2,"00");
                pst.setString(3,query2);
                pst.executeUpdate();
                conn.close();
                new CustomerDetails();
                f.setVisible(false);
            }
            catch(Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}

