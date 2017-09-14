package HotelProject;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateFood implements ActionListener {
    private JFrame f;
    private JLabel title,foodName, foodType,foodPrice,foodName1;
    private JTextField searchText;
    private JButton search,update,back;
    private JTextField foodNameText,foodTypeText,foodPriceText;
    static Connection conn=null;
    UpdateFood(){

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
        title=new JLabel("Update Food Details");
        title.setFont(new Font("Times new Roman",Font.BOLD,50));
        title.setForeground(Color.orange);
        title.setBounds(300,70,700,50);

        //Food Search
        foodName1=new JLabel("Food Name");
        foodName1.setBounds(200,150,250,50);
        foodName1.setForeground(Color.cyan);
        foodName1.setFont(new Font("Times new Roman",Font.BOLD,30));
        searchText=new JTextField();
        searchText.setBounds(450,160,150,30);
        search=new JButton("search");
        search.setBounds(450,200,100,40);

        //employee details
        Font labelFont=new Font("Arial",Font.ITALIC,30);
        foodName=new JLabel("Food Name");
        foodName.setBounds(200,270,190,50);
        foodName.setForeground(Color.WHITE);
        foodName.setFont(labelFont);
        foodType=new JLabel("Food Type");
        foodType.setBounds(200,350,300,50);
        foodType.setForeground(Color.WHITE);
        foodType.setFont(labelFont);
        foodPrice=new JLabel("Price");
        foodPrice.setBounds(200,430,190,50);
        foodPrice.setForeground(Color.WHITE);
        foodPrice.setFont(labelFont);


        foodNameText=new JTextField();
        foodNameText.setBounds(500,270,150,30);
        foodNameText.setEditable(false);
        foodTypeText=new JTextField();
        foodTypeText.setBounds(500,350,150,30);
        foodTypeText.setEditable(false);
        foodPriceText=new JTextField();
        foodPriceText.setBounds(500,430,150,30);


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
        f.add(foodName);
        f.add(searchText);
        f.add(search);
        f.add(back);
        f.add(update);
        f.add(foodName);
        f.add(foodNameText);
        f.add(foodPrice);
        f.add(foodPriceText);
        f.add(foodTypeText);
        f.add(foodType);
        f.add(foodName1);
        f.setVisible(true);

        update.addActionListener(this);
        back.addActionListener(this);
        search.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==update){
            if(foodNameText.getText().equals("")){
                JOptionPane.showMessageDialog(f,"Incorrect food name","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    String query="update Food set price=? where name=?";
                    PreparedStatement pst=conn.prepareStatement(query);
                    pst.setString(1,foodPriceText.getText());
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
                String query="select * from Food where name=?";
                PreparedStatement pst=conn.prepareStatement(query);
                pst.setString(1,searchText.getText());
                ResultSet rst=pst.executeQuery();
                if(rst.next()){
                    foodNameText.setText(searchText.getText());
                    foodTypeText.setText(rst.getString(1));
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect food name","error",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
