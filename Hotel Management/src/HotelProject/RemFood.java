package HotelProject;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RemFood implements ActionListener{
    private JFrame f;
    private JLabel title,foodName,foodID;
    private JTextField foodNameText,foodIDText;
    private JButton removebtn,back;
    static Connection conn=null;
    RemFood(){
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
        title=new JLabel("Remove Food");
        title.setFont(new Font("Times new Roman",Font.BOLD,50));
        title.setForeground(Color.orange);
        title.setBounds(250,150,500,50);

        foodID=new JLabel("Food Type");
        foodID.setFont(new Font("Arial",Font.ITALIC,30));
        foodID.setForeground(Color.WHITE);
        foodID.setBounds(275,300,300,40);

        foodName=new JLabel("Food Name");
        foodName.setFont(new Font("Arial",Font.ITALIC,30));
        foodName.setForeground(Color.WHITE);
        foodName.setBounds(275,380,300,40);

        //Textfield
        foodIDText=new JTextField();
        foodIDText.setBounds(550,310,150,30);
        foodNameText=new JTextField();
        foodNameText.setBounds(550,390,150,30);

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
        f.add(foodID);
        f.add(foodName);
        f.add(foodIDText);
        f.add(foodNameText);
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
            if(foodNameText.getText().equals("") || foodIDText.getText().equals("")){
                JOptionPane.showMessageDialog(f,"Enter both details","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    String query="delete from Food where type=? and name=?";
                    PreparedStatement pst = conn.prepareStatement(query);
                    pst.setString(1,foodIDText.getText());
                    pst.setString(2,foodNameText.getText());
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
