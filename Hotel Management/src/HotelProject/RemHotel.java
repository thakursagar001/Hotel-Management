package HotelProject;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RemHotel implements ActionListener{
    private JFrame f;
    private JLabel title,type,roomNo;
    private JTextField typeText,roomNoText;
    private JButton removebtn,back;
    static Connection conn=null;
    RemHotel(){
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
        title=new JLabel("Remove Hotel Rooms");
        title.setFont(new Font("Times new Roman",Font.BOLD,50));
        title.setForeground(Color.orange);
        title.setBounds(250,150,500,50);

        roomNo=new JLabel("Room Type");
        roomNo.setFont(new Font("Arial",Font.ITALIC,30));
        roomNo.setForeground(Color.WHITE);
        roomNo.setBounds(275,300,300,40);

        type=new JLabel("Room No.");
        type.setFont(new Font("Arial",Font.ITALIC,30));
        type.setForeground(Color.WHITE);
        type.setBounds(275,380,300,40);

        //Textfield
        roomNoText=new JTextField();
        roomNoText.setBounds(550,390,150,30);
        typeText=new JTextField();
        typeText.setBounds(550,310,150,30);

        //Remove Button and back
        removebtn=new JButton();
        ImageIcon backimg=new ImageIcon("F:\\SagarProject\\src\\HotelProject\\removeBtn.jpg");
        removebtn.setIcon(backimg);
        removebtn.setBounds(520,460,150,50);
        back=new JButton();
        back.setBounds(120,50,90,50);
        ImageIcon backimg1=new ImageIcon("F:\\SagarProject\\src\\HotelProject\\back.png");
        back.setIcon(backimg1);


        //add
        f.add(title);
        f.add(roomNo);
        f.add(type);
        f.add(roomNoText);
        f.add(typeText);
        f.add(removebtn);
        f.add(back);
        f.setVisible(true);


        removebtn.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            try {
                conn.close();
            }
            catch(Exception e1){

            }
            new AdminAdd();
            f.setVisible(false);
        }
        if(e.getSource()==removebtn){
            if(typeText.getText().equals("") || roomNoText.getText().equals("")){
                JOptionPane.showMessageDialog(f,"Enter both details","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    String query="delete from Hotel where type=? and room_no=?";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1,typeText.getText());
                    pst.setString(2,roomNoText.getText());
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
        }

    }


