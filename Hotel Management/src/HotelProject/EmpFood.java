package HotelProject;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class EmpFood  {
    private JFrame f;
    private JTable table_1;
    private JScrollPane scrollPane;
    private JButton ORDbtnNewButton,back;
    private JLabel label;
    private JLabel label_1;
    private JTextField CUSTtextField;
    private JTextField ROOMtextField_1;
    private JLabel  title;
    static Connection conn=null;
    int bill=0;
    String cus_Id;



    public EmpFood(String s) {

        conn=SQLITE.dbconnector();
        cus_Id=s;
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

        title=new JLabel("FOOD DETAILS");
        title.setFont(new Font("Times New Roman",Font.BOLD,25));
        title.setBounds(320,50,290,40);
        f.add(title);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(220, 120, 850, 400);
        f.add(scrollPane);

        table_1 = new JTable();
        scrollPane.setViewportView(table_1);

        try{
            String query="select * from Food";
            PreparedStatement pst=conn.prepareStatement(query);
            ResultSet rst=pst.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(rst));

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
        }

        ORDbtnNewButton = new JButton("ORDER");
        ORDbtnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ORDbtnNewButton.setBounds(887, 648, 191, 29);
        f.add(ORDbtnNewButton);



        ORDbtnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                try{
                    String query="select Billing from customer where cus_Id=?";
                    PreparedStatement pst=conn.prepareStatement(query);
                    pst.setString(1,cus_Id);
                    ResultSet rst=pst.executeQuery();
                    if(rst.next()){
                        bill+=Integer.parseInt(rst.getString("Billing"));
                    }
                    query="select * from Food where name=?";
                    pst=conn.prepareStatement(query);
                    pst.setString(1,ROOMtextField_1.getText());
                    rst=pst.executeQuery();

                    if(rst.next()){
                        bill+=Integer.parseInt(rst.getString(3));
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Food Not Found","error",JOptionPane.ERROR_MESSAGE);
                        conn.close();
                        new EmpFood(cus_Id);
                        f.setVisible(false);
                    }

                    query="update customer set Billing=? where cus_Id=?";
                    pst=conn.prepareStatement(query);
                    pst.setString(1,bill+"");
                    pst.setString(2,cus_Id);
                    pst.executeUpdate();
                    conn.close();
                    new detail(cus_Id);
                    f.setVisible(false);



                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        label = new JLabel("CUSTOMER ID");
        label.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label.setForeground(Color.white);
        label.setBounds(387, 583, 185, 40);
        f.add(label);

        label_1 = new JLabel("Food name");
        label_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        label_1.setBounds(387, 643, 185, 40);
        label_1.setForeground(Color.white);
        f.add(label_1);

        CUSTtextField = new JTextField();
        CUSTtextField.setColumns(10);
        CUSTtextField.setBounds(578, 594, 208, 29);
        CUSTtextField.setEditable(false);
        f.add(CUSTtextField);

        ROOMtextField_1 = new JTextField();
        ROOMtextField_1.setColumns(10);
        ROOMtextField_1.setBounds(576, 654, 208, 29);
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
                new detail("s");
                f.setVisible(false);
            }
        });
        CUSTtextField.setText(cus_Id);
        f.setVisible(true);
    }
}
