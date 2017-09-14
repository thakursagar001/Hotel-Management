package HotelProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage implements ActionListener {
    private JLabel userName,passName,title1,title2;
    private JTextField userText;
    private JPasswordField passText;
    private JButton login,admin,employee;
    private JFrame frame=new JFrame();
    int i=0;
     static Connection conn=null;
    LoginPage(){

        conn=SQLITE.dbconnector();
        //Frame_Layout
        frame.setContentPane(new JLabel(new ImageIcon("F:\\SagarProject\\src\\HotelProject\\bgimage1.jpg")));
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(0, 0, screenSize.width, screenSize.height);
        frame.setResizable(false);
        frame.setTitle("GRAPHIC ERA HOTEL AND RESTAURANT");
        frame.setLayout(null);
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //title
        title1=new JLabel("GRAPHIC ERA HOTEL");
        title1.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,50));
        title1.setForeground(Color.cyan);
        title1.setBounds(220, 50, 900, 50);
        title2=new JLabel("AND RESTAURANT");
        title2.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,50));
        title2.setForeground(Color.cyan);
        title2.setBounds(700, 120, 700, 50);




        //admin and employee button
        admin=new JButton("Admin");
        admin.setBounds(200,240,200,40);
        admin.setFont(new Font("Times New Roman",Font.BOLD,25));
        admin.setBackground(Color.yellow);

        employee=new JButton("Employee");
        employee.setBounds(400,240,200,40);
        employee.setFont(new Font("Times New Roman",Font.BOLD,25));
        employee.setBackground(Color.gray);


        //Adding Labels
        userName=new JLabel("username");
        userName.setBounds(220, 300, 100, 50);
        userName.setFont(new Font("TIMES NEW ROMAN",Font.ITALIC,25));
        userName.setForeground(Color.WHITE);

        passName=new JLabel("password");
        passName.setBounds(220, 380, 100, 50);
        passName.setFont(new Font("TIMES NEW ROMAN",Font.ITALIC,25));
        passName.setForeground(Color.white);

        //Adding textfield
        userText=new JTextField();
        userText.setBounds(340,310,150,30);
        passText=new JPasswordField();
        passText.setBounds(340,390,150,30);

        //adding buttons
        login=new JButton("login");
        login.setBounds(220, 470, 80, 35);
        ImageIcon loginimg=new ImageIcon("F:\\SagarProject\\src\\HotelProject\\login.png");
        login.setIcon(loginimg);

        //adding components
        frame.add(title1);
        frame.add(title2);
        frame.add(admin);
        frame.add(employee);
        frame.add(userName);
        frame.add(passName);
        frame.add(userText);
        frame.add(passText);
        frame.add(login);
        frame.setVisible(true);


        //register button
        admin.addActionListener(this);
        employee.addActionListener(this);
        login.addActionListener(this);

    }



    public static void main(String [] arg){

        new LoginPage();
    }




    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==admin){
            admin.setBackground(Color.yellow);
            employee.setBackground(Color.gray);
            i=0;

        }
        if(e.getSource()==employee){
            admin.setBackground(Color.gray);
            employee.setBackground(Color.yellow);
            i=1;

        }
        if(e.getSource()==login && i==0){
            try{
                String query="select * from Admin where username=? and password=?";
                PreparedStatement pst=conn.prepareStatement(query);
                pst.setString(1,userText.getText());
                pst.setString(2,passText.getText());
                ResultSet rs=pst.executeQuery();
                if(rs.next()){
                    conn.close();
                    new AdminAdd();
                    frame.setVisible(false);

                }
                else{
                    JOptionPane.showMessageDialog(null,"username and password incorrect","error",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(Exception e1){

            }

        }
        if(e.getSource()==login && i==1){

            try{
                String query1="select * from Emp where emp_id=? and password=?";
                PreparedStatement pst1=conn.prepareStatement(query1);
                pst1.setString(1,userText.getText());
                pst1.setString(2,passText.getText());
                ResultSet rs1=pst1.executeQuery();

                if(rs1.next()){
                    conn.close();
                    new CustomerDetails();
                    frame.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null,"username and password incorrect","error",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(Exception e2){

            }
        }
    }
}
