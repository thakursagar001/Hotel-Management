package HotelProject;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import net.proteanit.sql.DbUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpHotel  {

    private JFrame f;
    private JTable table_1;
    private JScrollPane scrollPane;
    private JButton ORDbtnNewButton,back;
    private JLabel label;
    private JLabel label_1;
    private JTextField CUSTtextField;
    private JTextField ROOMtextField_1,checkinText,checkoutText;
    private JLabel  title,checkin,checkout;
    String cus_Id;
    static Connection conn=null;

    public EmpHotel(String s) {
        cus_Id=s;
        conn=SQLITE.dbconnector();
        //frame
        f=new JFrame();
        f.setContentPane(new JLabel(new ImageIcon("F:\\SagarProject\\src\\HotelProject\\bgimage1.jpg")));
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        f.setBounds(0, 0, screenSize.width, screenSize.height);
        f.setResizable(false);
        f.setTitle("GRAPHIC ERA HOTEL AND RESTAURANT");
        f.setLayout(null);
        f.setVisible(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title=new JLabel("HOTEL DETAILS");
        title.setFont(new Font("Times New Roman",Font.BOLD,25));
        title.setBounds(320,50,290,40);
        f.add(title);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(220, 120, 850, 400);
        f.add(scrollPane);

        table_1 = new JTable();
        scrollPane.setViewportView(table_1);

        try{
            String query="select * from Hotel";
            PreparedStatement pst=conn.prepareStatement(query);
            ResultSet rst=pst.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(rst));

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
        }

        ORDbtnNewButton = new JButton("BOOK");
        ORDbtnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ORDbtnNewButton.setBounds(887, 650, 191, 29);
        f.add(ORDbtnNewButton);

        label = new JLabel("CUSTOMER ID");
        label.setFont(new Font("Times New Roman", Font.BOLD, 15));
        label.setForeground(Color.white);
        label.setBounds(387, 540, 135, 40);
        f.add(label);

        label_1 = new JLabel("ROOM NO.");
        label_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        label_1.setBounds(387, 580, 135, 40);
        label_1.setForeground(Color.white);
        f.add(label_1);

        checkin=new JLabel("Check In");
        checkin.setFont(new Font("Times New Roman", Font.BOLD, 15));
        checkin.setBounds(387, 620, 135, 40);
        checkin.setForeground(Color.white);
        f.add(checkin);

        checkout=new JLabel("Check Out");
        checkout.setFont(new Font("Times New Roman", Font.BOLD, 15));
        checkout.setBounds(387, 660, 135, 40);
        checkout.setForeground(Color.white);
        f.add(checkout);

        checkinText = new JTextField();
        checkinText.setBounds(578, 620, 208, 29);
        f.add(checkinText);

        checkoutText = new JTextField();
        checkoutText.setBounds(578, 660, 208, 29);
        f.add(checkoutText);


        CUSTtextField = new JTextField();
        CUSTtextField.setBounds(578, 540, 208, 29);
        CUSTtextField.setEditable(false);
        CUSTtextField.setText(cus_Id);
        f.add(CUSTtextField);

        ROOMtextField_1 = new JTextField();
        ROOMtextField_1.setColumns(10);
        ROOMtextField_1.setBounds(576, 580, 208, 29);
        f.add(ROOMtextField_1);

        //back button
        back=new JButton();
        back.setBounds(120,50,90,50);
        ImageIcon backimg1=new ImageIcon("F:\\SagarProject\\src\\HotelProject\\back.png");
        back.setIcon(backimg1);
        f.add(back);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    conn.close();
                }
                catch(Exception e){

                }
                new detail(cus_Id);
                f.setVisible(false);

            }
        });
        ORDbtnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    String query="update customer set room_no=? where cus_Id=? ";
                    PreparedStatement pst=conn.prepareStatement(query);
                    pst.setString(1,ROOMtextField_1.getText());
                    pst.setString(2,cus_Id);
                    pst.executeUpdate();
                    query="update Hotel set check_in=?,check_out=? where room_no=?";
                    pst=conn.prepareStatement(query);
                    pst.setString(1,checkinText.getText());
                    pst.setString(2,checkoutText.getText());
                    pst.setString(3,ROOMtextField_1.getText());
                    pst.executeUpdate();




                    int bill=0;
                    String query1="select Billing from customer where cus_Id=?";
                    PreparedStatement pst1=conn.prepareStatement(query1);
                    pst1.setString(1,cus_Id);
                    ResultSet rst=pst1.executeQuery();
                    if(rst.next()){
                        bill+=Integer.parseInt(rst.getString("Billing"));
                    }
                    query1="select * from Hotel where room_no=?";
                    pst1=conn.prepareStatement(query1);
                    pst1.setString(1,ROOMtextField_1.getText());
                    rst=pst1.executeQuery();

                    if(rst.next()){
                        bill+=Integer.parseInt(rst.getString(3));
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Room Not Found","error",JOptionPane.ERROR_MESSAGE);
                        conn.close();
                        new EmpHotel(cus_Id);
                        f.setVisible(false);
                    }
                    query1="update customer set Billing=? where cus_Id=?";
                    pst1=conn.prepareStatement(query1);
                    pst1.setString(1,bill+"");
                    pst1.setString(2,cus_Id);
                    pst1.executeUpdate();





                    conn.close();
                    new detail(cus_Id);
                    f.setVisible(false);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        f.setVisible(true);
    }

}
