package HotelProject;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateHotel implements ActionListener {
    private JFrame f;
    private JLabel title,hotelRoom, type,rate,hotelRoom1;
    private JTextField searchText;
    private JButton search,update,back;
    private JTextField hotelRoomText,typeText,rateText;
    static Connection conn=null;
    UpdateHotel(){
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
        title=new JLabel("Update Hotel Details");
        title.setFont(new Font("Times new Roman",Font.BOLD,50));
        title.setForeground(Color.orange);
        title.setBounds(300,70,700,50);

        //Food Search
        hotelRoom1=new JLabel("Room No");
        hotelRoom1.setBounds(200,150,250,50);
        hotelRoom1.setForeground(Color.cyan);
        hotelRoom1.setFont(new Font("Times new Roman",Font.BOLD,30));
        searchText=new JTextField();
        searchText.setBounds(450,160,150,30);
        search=new JButton("search");
        search.setBounds(450,200,100,40);

        //employee details
        Font labelFont=new Font("Arial",Font.ITALIC,30);
        hotelRoom=new JLabel("Room No.");
        hotelRoom.setBounds(200,270,190,50);
        hotelRoom.setForeground(Color.WHITE);
        hotelRoom.setFont(labelFont);
        type=new JLabel("Type");
        type.setBounds(200,350,300,50);
        type.setForeground(Color.WHITE);
        type.setFont(labelFont);
        rate=new JLabel("Rate");
        rate.setBounds(200,430,190,50);
        rate.setForeground(Color.WHITE);
        rate.setFont(labelFont);


        hotelRoomText=new JTextField();
        hotelRoomText.setBounds(500,270,150,30);
        hotelRoomText.setEditable(false);
        typeText=new JTextField();
        typeText.setBounds(500,350,150,30);
        rateText=new JTextField();
        rateText.setBounds(500,430,150,30);


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
        f.add(hotelRoom);
        f.add(searchText);
        f.add(search);
        f.add(back);
        f.add(update);
        f.add(hotelRoom);
        f.add(hotelRoomText);
        f.add(rate);
        f.add(rateText);
        f.add(typeText);
        f.add(type);
        f.add(hotelRoom1);
        f.setVisible(true);

        update.addActionListener(this);
        search.addActionListener(this);
        back.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==update){
            if(hotelRoomText.getText().equals("")){
                JOptionPane.showMessageDialog(f,"Enter Room no.","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    String query="update Hotel set type=?,rate=? where room_no=?";
                    PreparedStatement pst=conn.prepareStatement(query);
                    pst.setString(1,typeText.getText());
                    pst.setString(2,rateText.getText());
                    pst.setString(3,hotelRoomText.getText());
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
                String query="select * from Hotel where room_no=?";
                PreparedStatement pst=conn.prepareStatement(query);
                pst.setString(1,searchText.getText());
                ResultSet rst=pst.executeQuery();
                if(rst.next()){
                    hotelRoomText.setText(searchText.getText());

                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect Room no","error",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

