package da3_java;
import java.net.URL;
import da3_java.Connection_Util;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HistoryController implements Initializable {
	@FXML
    private ScrollPane pane;

	Stage dialogStage = new Stage();
    Scene scene;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
 
    public HistoryController() {
        connection = Connection_Util.connectdb();
        String sql = "SELECT * FROM converter";
        try {
        	preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	public void shw(ActionEvent event){
		String op="Converted: \n----------------------------------\n";
		try {
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
			String temp="";
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) temp+=" = ";
		        String columnValue = resultSet.getString(i);
		        temp+=columnValue;
		    }
		    temp+=("\n");
		    op+=temp;
		}
		TextArea textArea = new TextArea();
		textArea.setText(op);
		pane.setContent(textArea);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	        
    } 

}