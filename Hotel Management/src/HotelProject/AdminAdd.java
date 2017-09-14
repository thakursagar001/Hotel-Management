package HotelProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminAdd implements ActionListener{

    private JFrame f;
    private JTextField searchText=new JTextField();
    private JLabel emp,food,hotelroom,empDetails;
    static Connection conn=null;
    private JButton addemp,rememp,addfood,remfood,addhotelrm,remhotelrm,search,logOut,updateEmp,updatefood,updatehotelrm;
    AdminAdd(){
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

        //labels
        Font fonts=new Font("Times new Roman",Font.BOLD,30);
        emp=new JLabel("Employee");
        emp.setBounds(200,150,200,50);
        emp.setForeground(Color.WHITE);
        emp.setFont(fonts);
        food=new JLabel("Food Item");
        food.setBounds(200,250,200,50);
        food.setForeground(Color.WHITE);
        food.setFont(fonts);
        hotelroom=new JLabel("Hotel Room");
        hotelroom.setBounds(200,350,200,50);
        hotelroom.setForeground(Color.WHITE);
        hotelroom.setFont(fonts);
        empDetails=new JLabel("Employee Id");
        empDetails.setBounds(200,450,250,50);
        empDetails.setForeground(Color.cyan);
        empDetails.setFont(fonts);
        searchText.setBounds(450,450,200,40);


        //buttons
        logOut=new JButton("LOGOUT");
        logOut.setFont(new Font("Arial",Font.BOLD,20));
        logOut.setBounds(900,50,200,50);



        addemp=new JButton("Add ");
        addemp.setBounds(400,150,100,50);
        rememp=new JButton("Remove");
        rememp.setBounds(550,150,100,50);
        updateEmp=new JButton("Update");
        updateEmp.setBounds(700,150,100,50);

        addfood=new JButton("Add");
        addfood.setBounds(400,250,100,50);
        remfood=new JButton("Remove");
        remfood.setBounds(550,250,100,50);
        updatefood=new JButton("Update");
        updatefood.setBounds(700,250,100,50);

        addhotelrm=new JButton("Add");
        addhotelrm.setBounds(400,350,100,50);
        remhotelrm=new JButton("Remove");
        remhotelrm.setBounds(550,350,100,50);
        updatehotelrm=new JButton("Update");
        updatehotelrm.setBounds(700,350,100,50);


        search=new JButton("search");
        search.setBounds(480,540,150,40);


        //add
        f.add(emp);
        f.add(food);
        f.add(hotelroom);

        f.add(addemp);
        f.add(addfood);
        f.add(addhotelrm);

        f.add(rememp);
        f.add(remfood);
        f.add(remhotelrm);
        f.add(updateEmp);
        f.add(updatefood);
        f.add(updatehotelrm);

        f.add(empDetails);
        f.add(searchText);
        f.add(search);
        f.add(logOut);




        logOut.addActionListener(this);
        addemp.addActionListener(this);
        rememp.addActionListener(this);
        updateEmp.addActionListener(this);
        addfood.addActionListener(this);
        remfood.addActionListener(this);
        updatefood.addActionListener(this);
        addhotelrm.addActionListener(this);
        remhotelrm.addActionListener(this);
        updatehotelrm.addActionListener(this);
        search.addActionListener(this);
        f.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==search){
            if(searchText.getText().equals("")){
                JOptionPane.showMessageDialog(null,"enter employee id","error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    String query="select * from Emp where emp_id=?";
                    PreparedStatement pst=conn.prepareStatement(query);
                    pst.setString(1,searchText.getText());
                    ResultSet rst=pst.executeQuery();
                    if(rst.next()){
                        conn.close();
                        new EmpSearch(searchText.getText());
                        f.setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Invalid Emp id","error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(e.getSource()==logOut){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new LoginPage();
            f.setVisible(false);
        }
        if(e.getSource()==addemp){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new AddEmployee();
            f.setVisible(false);
        }
        if(e.getSource()==rememp){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new RemoveEmployee();
            f.setVisible(false);
        }

        if(e.getSource()==updateEmp){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new UpdateEmployee();
            f.setVisible(false);
        }
        if(e.getSource()==addfood){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new AddFood();
            f.setVisible(false);
        }
        if(e.getSource()==remfood){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new RemFood();
            f.setVisible(false);
        }
        if(e.getSource()==updatefood){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new UpdateFood();
            f.setVisible(false);
        }
        if(e.getSource()==addhotelrm){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new AddHotel();
            f.setVisible(false);
        }
        if(e.getSource()==remhotelrm){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new RemHotel();
            f.setVisible(false);
        }
        if(e.getSource()==updatehotelrm){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new UpdateHotel();
            f.setVisible(false);
        }
    }
}
