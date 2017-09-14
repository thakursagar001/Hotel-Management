package HotelProject;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class AddHotel implements ActionListener{

    private JFrame f;
    private JLabel rate,roomNo,type,title;
    private JTextField rateText,roomNoText,typeText;
    private JButton back,add;

    static Connection conn = null;
    AddHotel(){
        conn = SQLITE.dbconnector();
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

        //Labels
        title=new JLabel("ADD HOTEL ROOMS");
        title.setFont(new Font("Arial",Font.BOLD,50));
        title.setForeground(Color.cyan);
        title.setBounds(300,100,500,50);

        //hotel details lABEL
        Font labelFont=new Font("Arial",Font.ITALIC,30);
        type=new JLabel("Type");
        type.setBounds(250,220,190,50);
        type.setForeground(Color.WHITE);
        type.setFont(labelFont);
        roomNo=new JLabel("Room NO.");
        roomNo.setBounds(250,300,190,50);
        roomNo.setForeground(Color.WHITE);
        roomNo.setFont(labelFont);
        rate=new JLabel("Rate");
        rate.setBounds(250,380,190,50);
        rate.setForeground(Color.WHITE);
        rate.setFont(labelFont);

        //hotel details textfield
        typeText=new JTextField();
        typeText.setBounds(500,230,150,30);
        roomNoText=new JTextField();
        roomNoText.setBounds(500,310,150,30);
        rateText=new JTextField();
        rateText.setBounds(500,390,150,30);

        //back and add button
        back=new JButton();
        back.setBounds(120,50,90,50);
        ImageIcon backimg=new ImageIcon("F:\\SagarProject\\src\\HotelProject\\back.png");
        back.setIcon(backimg);
        add=new JButton("add");
        add.setBounds(300,480,100,40);



        f.add(title);
        f.add(roomNo);
        f.add(type);
        f.add(rate);
        f.add(roomNoText);
        f.add(typeText);
        f.add(rateText);
        f.add(add);
        f.add(back);
        f.setVisible(true);
        back.addActionListener(this);
        add.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==add){
            if((roomNoText.getText().equals("")) || (typeText.getText().equals("")) || (rateText.getText().equals("")) )
                JOptionPane.showMessageDialog(f,"Enter all the details","Error",JOptionPane.ERROR_MESSAGE);
            else
                try {
                    String query = "insert into Hotel(type,room_no,rate)" +
                            "values(?,?,?)";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1, typeText.getText());
                    pst.setString(2, roomNoText.getText());
                    pst.setString(3, rateText.getText());
                    pst.executeUpdate();
                    conn.close();
                    new AdminAdd();
                    f.setVisible(false);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
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
    }
}
