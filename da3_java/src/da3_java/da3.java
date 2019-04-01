package da3_java;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.*;
import javax.swing.*;
import javafx.application.Application;
class ConnectionUtil {
    Connection conn = null;
    public static Connection connectdb()
    {       try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/da3","root","");
            return conn;
        }
        catch(Exception e)
        {           JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
public class da3 extends JFrame
{
    private JPanel inputPanel;
    private JPanel resultPanel;
    private JPanel menuPanel;
    private JPanel menuPanel1;
    private JLabel promptLabel;
    private JLabel resultLabel;
    private JLabel selectLabel;
    private JLabel selectLabel1;
    private JLabel promptLabel1;
    private JMenu currencyMenu;
    private JMenuBar currencyMenuBar;
    private JMenu currencyMenu1;
    private JMenuBar currencyMenuBar1;
    private JTextField inputField;
    private JButton convertButton;
    private double euros_euros = 1;
    private double euros_rupees = 78.02;
    private double euros_usd = 1.13;
    private double euros_cd = 1.52;
    private double euros_yen = 124.60;
    private double rupees_euros = 0.013;
    private double rupees_rupees = 1;
    private double rupees_usd = 0.015;
    private double rupees_cd = 0.019;
    private double rupees_yen = 1.60;
    private double usd_euros = 0.88;
    private double usd_rupees = 68.92;
    private double usd_usd = 1;
    private double usd_cd = 1.34;
    private double usd_yen = 110.09;
    private double cd_euros = 0.66;
    private double cd_rupees = 51.33;
    private double cd_usd = 0.74;
    private double cd_cd = 1;
    private double cd_yen = 81.99;
    private double yen_euros = 0.0080;
    private double yen_rupees = 0.63;
    private double yen_usd = 0.0091;
    private double yen_cd = 0.012;
    private double yen_yen = 1;
	double calcTotal;
	Connection connection = null;
    PreparedStatement preparedStatement = null;
    int resultSet = 0;
    public da3()                      
    { 	super();    	 
    	connection = ConnectionUtil.connectdb();
        this.setSize(400, 300);                         
        this.setLayout(new GridLayout(3, 1));           
       this.setTitle("Currency Converter-16BCE1178");     
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.selectLabel = new JLabel("Select a currency from where to convert: ", JLabel.RIGHT);
        this.resultLabel = new JLabel(" ", JLabel.CENTER);
        this.currencyMenu = new JMenu("(Click me to select a currency)");
        JMenuItem Euros = new JMenuItem("Euros");                       
        Euros.addActionListener(new java.awt.event.ActionListener()   
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                menuChanged(evt);
            }
        });
        this.currencyMenu.add(Euros);
        JMenuItem JapaneseYen = new JMenuItem("Japanese Yen");          
        JapaneseYen.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuChanged(evt);
          }
        });
        this.currencyMenu.add(JapaneseYen);
        JMenuItem INR = new JMenuItem("Indian Rupees");           
        INR.addActionListener(new java.awt.event.ActionListener()  
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuChanged(evt);
            }
        });
        this.currencyMenu.add(INR);
        JMenuItem CD = new JMenuItem("Canadian Dollars");       

        CD.addActionListener(new java.awt.event.ActionListener()   
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	menuChanged(evt);
            }
        });
        this.currencyMenu.add(CD); 
        JMenuItem USD = new JMenuItem("US Dollars");                    
        USD.addActionListener(new java.awt.event.ActionListener()    
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                menuChanged(evt);
            }
        });
        this.currencyMenu.add(USD);        
        currencyMenuBar = new JMenuBar();           
        currencyMenuBar.add(currencyMenu);        
        this.menuPanel = new JPanel();
        this.menuPanel.add(this.selectLabel);
        this.menuPanel.add(this.currencyMenuBar);
        this.add(this.menuPanel);    
        this.selectLabel1 = new JLabel("Select a currency where to convert: ", JLabel.CENTER);
        this.currencyMenu1 = new JMenu("(Click me to select a currency)");;
        JMenuItem euros = new JMenuItem("euros");                     
        euros.addActionListener(new java.awt.event.ActionListener()     
        {
            public void actionPerformed(java.awt.event.ActionEvent evt1) 
            {
                menuChanged1(evt1);
            }
        });
        this.currencyMenu1.add(euros);
        JMenuItem japaneseYen = new JMenuItem("Japanese Yen");          
        japaneseYen.addActionListener(new java.awt.event.ActionListener()  
        {
            public void actionPerformed(java.awt.event.ActionEvent evt1)
            {
                menuChanged1(evt1);
          }
        });
        this.currencyMenu1.add(japaneseYen);
        JMenuItem inr = new JMenuItem("Indian Rupees");          

        inr.addActionListener(new java.awt.event.ActionListener()  
        {
            public void actionPerformed(java.awt.event.ActionEvent evt1)
            {
                menuChanged1(evt1);
            }
        });
        this.currencyMenu1.add(inr);
        JMenuItem CanDol = new JMenuItem("Canadian Dollars");          
        CanDol.addActionListener(new java.awt.event.ActionListener()   
        {
            public void actionPerformed(java.awt.event.ActionEvent evt1)
            {
                menuChanged1(evt1);
            }
        });
        this.currencyMenu1.add(CanDol);
        JMenuItem USDol = new JMenuItem("US Dollars");                   
        USDol.addActionListener(new java.awt.event.ActionListener()       
        {
            public void actionPerformed(java.awt.event.ActionEvent evt1)
            {
                menuChanged1(evt1);
            }
        });
        this.currencyMenu1.add(USDol);
        currencyMenuBar1 = new JMenuBar();              
        currencyMenuBar1.add(currencyMenu1);
        this.menuPanel1 = new JPanel();
        this.menuPanel1.add(this.selectLabel1);
        this.menuPanel1.add(this.currencyMenuBar1);
        this.add(this.menuPanel1);
        this.menuPanel1.add(this.currencyMenuBar1);
       this.resultLabel = new JLabel(" ", JLabel.CENTER);
       this.promptLabel = new JLabel("(Type Amount to Convert!) ", JLabel.RIGHT);
        this.inputField = new JTextField("", 12);
       this.convertButton = new JButton("Convert");
        convertButton.addActionListener(new java.awt.event.ActionListener()
        {
           public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                buttonclicked(evt);
            }
        });
        this.inputPanel = new JPanel();
        this.inputPanel.add(this.promptLabel);
        this.inputPanel.add(this.inputField);
        this.inputPanel.add(this.convertButton);
        this.add(this.inputPanel);
        this.resultPanel = new JPanel();
      this.resultPanel.add(this.resultLabel);
      this.add(this.resultPanel);
    }
    
    public void menuChanged(ActionEvent e)
    {
        if (e.getActionCommand().equals("Euros"))
        {
            currencyMenu.setText("Euros");
        }
        if (e.getActionCommand().equals("Japanese Yen")) {
            currencyMenu.setText("Japanese Yen");
        }
        if (e.getActionCommand().equals("Indian Rupees")) {
            currencyMenu.setText("Indian Rupees");
        }      
       if (e.getActionCommand().equals("Canadian Dollars")) {
            currencyMenu.setText("Canadian Dollars");
        }
        if (e.getActionCommand().equals("US Dollars")) {
            currencyMenu.setText("US Dollars");
        }
    }
    public void menuChanged1(ActionEvent e)
    {
        if (e.getActionCommand().equals("Euros"))
        {
            currencyMenu1.setText("Euros");
        }
        if (e.getActionCommand().equals("Japanese Yen")) {
            currencyMenu1.setText("Japanese Yen");
       }
        if (e.getActionCommand().equals("Indian Rupees")) {
            currencyMenu1.setText("Indian Rupees");
        }
   if (e.getActionCommand().equals("Canadian Dollars")) {
            currencyMenu1.setText("Canadian Dollars");
        }
   if (e.getActionCommand().equals("US Dollars")) {
            currencyMenu1.setText("US Dollars");
        }
    }
    public void buttonclicked(ActionEvent evt)
    {
        if((currencyMenu.getText().equals("Euros")) && (currencyMenu1.getText().equals("Euros")))
        {
            resultLabel.setText(inputField.getText() + " Euros is equal to " + EurosToEuros() + " Euros.");
        }
        if((currencyMenu.getText().equals("Euros")) && (currencyMenu1.getText().equals("Indian Rupees")))
        {
            resultLabel.setText(inputField.getText() + " Euros is equal to " + EurosToRupees() + " Rupees.");
        }
        if((currencyMenu.getText().equals("Euros")) && (currencyMenu1.getText().equals("Japanese Yen")))
        {
            resultLabel.setText(inputField.getText() + " Euros is equal to " + EurosToYen() + " Yen.");
        }
        if((currencyMenu.getText().equals("Euros")) && (currencyMenu1.getText().equals("Canadian Dollars")))
        {
            resultLabel.setText(inputField.getText() + " Euros is equal to " + EurosToCd() + " Canadian Dollars.");
        }
        if((currencyMenu.getText().equals("Euros")) && (currencyMenu1.getText().equals("US Dollars")))
        {
            resultLabel.setText(inputField.getText() + " Euros is equal to " + EurosToUsd() + " US Dollars.");
        }
        if((currencyMenu.getText().equals("Japanese Yen")) && (currencyMenu1.getText().equals("Euros")))
        {
            resultLabel.setText(inputField.getText() + " Yen is equal to " + YenToEuros() + " Euros.");
        }
        if((currencyMenu.getText().equals("Japanese Yen")) && (currencyMenu1.getText().equals("Indian Rupees")))
        {
            resultLabel.setText(inputField.getText() + " Yen is equal to " + YenToRupees() + " Rupees.");
        }
        if((currencyMenu.getText().equals("Japanese Yen")) && (currencyMenu1.getText().equals("Japanese Yen")))
        {
            resultLabel.setText(inputField.getText() + " Yen is equal to " + YenToYen() + " Yen.");
        }
        if((currencyMenu.getText().equals("Japanese Yen")) && (currencyMenu1.getText().equals("Canadian Dollars")))
        {
            resultLabel.setText(inputField.getText() + " Yen is equal to " + YenToCd() + " Canadian Dollars.");
        }
        if((currencyMenu.getText().equals("Japanese Yen")) && (currencyMenu1.getText().equals("US Dollars")))
        {
            resultLabel.setText(inputField.getText() + " Yen is equal to " + YenToUsd() + " US Dollars.");
        }
        if((currencyMenu.getText().equals("Indian Rupees")) && (currencyMenu1.getText().equals("Euros")))
        {
            resultLabel.setText(inputField.getText() + " Rupees is equal to " + InrToEuros() + " Euros.");
        }
        if((currencyMenu.getText().equals("Indian Rupees")) && (currencyMenu1.getText().equals("Indian Rupees")))
        {
            resultLabel.setText(inputField.getText() + " Rupees is equal to " + InrToRupees() + " Rupees.");
        }
        if((currencyMenu.getText().equals("Indian Rupees")) && (currencyMenu1.getText().equals("Japanese Yen")))
        {
            resultLabel.setText(inputField.getText() + "Rupees is equal to " + InrToYen() + " Yen.");
        }
        if((currencyMenu.getText().equals("Indian Rupees")) && (currencyMenu1.getText().equals("Canadian Dollars")))
        {
            resultLabel.setText(inputField.getText() + "Rupees is equal to " + InrToCd() + " Canadian Dollars.");
        }
        if((currencyMenu.getText().equals("Indian Rupees")) && (currencyMenu1.getText().equals("US Dollars")))
        {
            resultLabel.setText(inputField.getText() + "Rupees is equal to " + InrToUsd() + " US Dollars.");
        }
       if((currencyMenu.getText().equals("Canadian Dollars")) && (currencyMenu1.getText().equals("Euros")))
        {
            resultLabel.setText(inputField.getText() + " Canadian Dollars is equal to " + CdToEuros() + " Euros.");
        }
        if((currencyMenu.getText().equals("Canadian Dollars")) && (currencyMenu1.getText().equals("Indian Rupees")))
        {
            resultLabel.setText(inputField.getText() + " Canadian Dollars is equal to " + CdToRupees() + " Rupees.");
        }
       if((currencyMenu.getText().equals("Canadian Dollars")) && (currencyMenu1.getText().equals("Japanese Yen")))
        {
            resultLabel.setText(inputField.getText() + " Canadian Dollars is equal to " + CdToYen() + " Yen.");
        }
       if((currencyMenu.getText().equals("Canadian Dollars")) && (currencyMenu1.getText().equals("Canadian Dollars")))
        {
            resultLabel.setText(inputField.getText() + " Canadian Dollars is equal to " + CdToCd() + " Canadian Dollars.");
        }
        if((currencyMenu.getText().equals("Canadian Dollars")) && (currencyMenu1.getText().equals("US Dollars")))
        {
            resultLabel.setText(inputField.getText() + " Canadian Dollars is equal to " + CdToUsd() + " US Dollars.");
        }
        if((currencyMenu.getText().equals("US Dollars")) && (currencyMenu1.getText().equals("Euros")))
        {
            resultLabel.setText(inputField.getText() + " US Dollars is equal to " + UsdToEuros() + " Euros.");
        }
        if((currencyMenu.getText().equals("US Dollars")) && (currencyMenu1.getText().equals("Indian Rupees")))
        {
            resultLabel.setText(inputField.getText() + " US Dollars is equal to " + UsdToRupees() + " Rupees.");
        }
        if((currencyMenu.getText().equals("US Dollars")) && (currencyMenu1.getText().equals("Japanese Yen")))
        {
            resultLabel.setText(inputField.getText() + " US Dollars is equal to " + UsdToYen() + " Yen.");
        }
        if((currencyMenu.getText().equals("US Dollars")) && (currencyMenu1.getText().equals("Canadian Dollars")))
        {
            resultLabel.setText(inputField.getText() + " US Dollars is equal to " + UsdToCd() + " Canadian Dollars.");
        }
        if((currencyMenu.getText().equals("US Dollars")) && (currencyMenu1.getText().equals("US Dollars")))
        {
            resultLabel.setText(inputField.getText() + " US Dollars is equal to " + UsdToUsd() + " US Dollars.");
        }
        String s=resultLabel.getText().toString();
        String sql = "INSERT INTO converter(result) VALUES (?)";
        try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, s);
	        resultSet = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
    }
    double EurosToEuros()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * euros_euros;
        return calcTotal;
    }
    double EurosToRupees()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * euros_rupees;
        return calcTotal;
    }
    double EurosToCd()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * euros_cd;
        return calcTotal;
    }
    double EurosToYen()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * euros_yen;
        return calcTotal;
    }
    double EurosToUsd()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * euros_usd;
        return calcTotal;
    }
   double YenToYen()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * yen_yen;
        return calcTotal;
    }
  double YenToEuros()
   {
       double calcTotal = Double.parseDouble(inputField.getText()) * yen_euros;
       return calcTotal;
   }
   double YenToRupees()
   {
       double calcTotal = Double.parseDouble(inputField.getText()) * yen_rupees;
       return calcTotal;
   }
   double YenToCd()
   {
       double calcTotal = Double.parseDouble(inputField.getText()) * yen_cd;
       return calcTotal;
   }
   double YenToUsd()
   {
       double calcTotal = Double.parseDouble(inputField.getText()) * yen_usd;
       return calcTotal;
   }
    double InrToRupees()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * rupees_rupees;
        return calcTotal;
    }
    double InrToEuros()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * rupees_euros;
        return calcTotal;
    }
    double InrToYen()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * rupees_yen;
        return calcTotal;
    }
   double InrToCd()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * rupees_cd;
        return calcTotal;
    }
   double InrToUsd()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * rupees_usd;
        return calcTotal;
    }
    double UsdToUsd()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * usd_usd;
        return calcTotal;
    }
   double UsdToRupees()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * usd_rupees;
        return calcTotal;
    }
   double UsdToYen()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * usd_yen;
        return calcTotal;
    }
    double UsdToEuros()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * usd_euros;
        return calcTotal;
    }
   double UsdToCd()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * usd_cd;
        return calcTotal;
    }
    double CdToCd()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * cd_cd;
        return calcTotal;
    }
    double CdToUsd()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * cd_usd;
        return calcTotal;
    }
    double CdToEuros()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * cd_euros;
        return calcTotal;
    }
    double CdToRupees()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * cd_rupees;
        return calcTotal;
    }
   double CdToYen()
    {
        double calcTotal = Double.parseDouble(inputField.getText()) * cd_yen;
        return calcTotal;
    }
   public static void main(String[] args)

   {

       da3 CurCon = new da3();
       CurCon.setVisible(true);
   }
public void setclearButton(JButton convertButton) {

       this.convertButton = convertButton;

   }
   public JButton getConvertButton() {
       return convertButton;
   }
   public void setConvertButton(JButton convertButton) {
       this.convertButton = convertButton;
   }
}