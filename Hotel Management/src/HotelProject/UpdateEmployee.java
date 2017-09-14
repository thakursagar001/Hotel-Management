package HotelProject;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateEmployee implements ActionListener{
    private JFrame f;
    private JLabel title,empDetails, empId,permanentAdd,contactNo;
    private JTextField searchText;
    private JButton search,update,back;
    private JTextField empIdText,permanentAddText,contactText;
    static Connection conn=null;

    UpdateEmployee(){

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

        //label
        title=new JLabel("Update Employee Details");
        title.setFont(new Font("Times new Roman",Font.BOLD,50));
        title.setForeground(Color.orange);
        title.setBounds(300,70,700,50);

        //employee Search
        empDetails=new JLabel("Employee ID");
        empDetails.setBounds(200,150,250,50);
        empDetails.setForeground(Color.cyan);
        empDetails.setFont(new Font("Times new Roman",Font.BOLD,30));
        searchText=new JTextField();
        searchText.setBounds(450,160,150,30);
        search=new JButton("search");
        search.setBounds(450,200,100,40);

        //employee details
        Font labelFont=new Font("Arial",Font.ITALIC,30);
        empId=new JLabel("Emp Id");
        empId.setBounds(200,270,190,50);
        empId.setForeground(Color.WHITE);
        empId.setFont(labelFont);
        permanentAdd=new JLabel("Permanent Address");
        permanentAdd.setBounds(200,350,300,50);
        permanentAdd.setForeground(Color.WHITE);
        permanentAdd.setFont(labelFont);
        contactNo=new JLabel("Contact NO.");
        contactNo.setBounds(200,430,190,50);
        contactNo.setForeground(Color.WHITE);
        contactNo.setFont(labelFont);


        empIdText=new JTextField();
        empIdText.setBounds(500,270,150,30);
        empIdText.setEditable(false);
        permanentAddText=new JTextField();
        permanentAddText.setBounds(500,350,150,30);
        contactText=new JTextField();
        contactText.setBounds(500,430,150,30);

        //back button
        back=new JButton();
        back.setBounds(120,50,90,50);
        ImageIcon backimg1=new ImageIcon("F:\\SagarProject\\src\\HotelProject\\back.png");
        back.setIcon(backimg1);

        //update button
        update=new JButton("UPDATE");
        update.setBounds(450,500,150,40);
        update.setFont(new Font("Arial",Font.BOLD,20));

        f.add(title);
        f.add(empIdText);
        f.add(empDetails);
        f.add(searchText);
        f.add(search);
        f.add(empId);
        f.add(permanentAdd);
        f.add(contactNo);
        f.add(permanentAddText);
        f.add(contactText);
        f.add(update);
        f.add(back);

        update.addActionListener(this);
        back.addActionListener(this);
        search.addActionListener(this);
        f.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==update){
            if(empIdText.getText().equals("")){
                JOptionPane.showMessageDialog(f,"Enter employee id","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{

                try{
                    String query="update Emp set permanent_address=?,contact_No=? where emp_id=?";
                    PreparedStatement pst=conn.prepareStatement(query);
                    pst.setString(1,permanentAddText.getText());
                    pst.setString(2,contactText.getText());
                    pst.setString(3,searchText.getText());
                    pst.executeUpdate();
                    conn.close();
                    new AdminAdd();
                    f.setVisible(false);
                }
                catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(e.getSource()==back){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new AdminAdd();
            f.setVisible(false);
        }
        if(e.getSource()==search){
            try{
                String query="select * from Emp where emp_id=?";
                PreparedStatement pst=conn.prepareStatement(query);
                pst.setString(1,searchText.getText());
                ResultSet rst=pst.executeQuery();
                if(rst.next()){
                    empIdText.setText(searchText.getText());
                }
                else{
                    JOptionPane.showMessageDialog(null,"incorrect Emp id","error",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
