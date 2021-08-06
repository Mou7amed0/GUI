package sample;
/**
 * Sample Skeleton for 'sample.fxml' Controller Class
 */

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.Clock;
import java.util.ResourceBundle;
// JavaFX
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tfId"
    private TextField tfId; // Value injected by FXMLLoader

    @FXML // fx:id="tfTitre"
    private TextField tfTitre; // Value injected by FXMLLoader

    @FXML // fx:id="tfClient"
    private TextField tfClient; // Value injected by FXMLLoader

    @FXML // fx:id="tfIP"
    private TextField tfIP; // Value injected by FXMLLoader

    @FXML // fx:id="tfPath"
    private TextField tfPath; // Value injected by FXMLLoader

    @FXML // fx:id="tfDate"
    private TextField tfDate; // Value injected by FXMLLoader

    @FXML // fx:id="tViewDocs"
    private TableView<Files> tViewDocs; // Value injected by FXMLLoader

    @FXML // fx:id="colidDoc"
    private TableColumn<Files, Integer> colidDoc; // Value injected by FXMLLoader

    @FXML // fx:id="colTitre"
    private TableColumn<Files, String> colTitre; // Value injected by FXMLLoader

    @FXML // fx:id="colClient"
    private TableColumn<Files, String> colClient; // Value injected by FXMLLoader

    @FXML // fx:id="colipClient"
    private TableColumn<Files, String> colipClient; // Value injected by FXMLLoader

    @FXML // fx:id="colPath"
    private TableColumn<Files, String> colPath; // Value injected by FXMLLoader

    @FXML // fx:id="colDate"
    private TableColumn<Files, String> colDate; // Value injected by FXMLLoader

    @FXML // fx:id="btnInsert"
    private Button btnInsert; // Value injected by FXMLLoader

    @FXML // fx:id="btnUpdate"
    private Button btnUpdate; // Value injected by FXMLLoader

    @FXML // fx:id="btnDelete"
    private Button btnDelete; // Value injected by FXMLLoader

    @FXML
    public // This method is called by the FXMLLoader when initialization is complete
    void initialize(URL location, ResourceBundle resources) {
        showFiles();
        /*assert tfId != null : "fx:id=\"tfId\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfTitre != null : "fx:id=\"tfTitre\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfClient != null : "fx:id=\"tfClient\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfIP != null : "fx:id=\"tfIP\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfPath != null : "fx:id=\"tfPath\" was not injected: check your FXML file 'sample.fxml'.";
        assert tfDate != null : "fx:id=\"tfDate\" was not injected: check your FXML file 'sample.fxml'.";
        assert tViewDocs != null : "fx:id=\"tViewDocs\" was not injected: check your FXML file 'sample.fxml'.";
        assert colidDoc != null : "fx:id=\"colidDoc\" was not injected: check your FXML file 'sample.fxml'.";
        assert colTitre != null : "fx:id=\"colTitre\" was not injected: check your FXML file 'sample.fxml'.";
        assert colClient != null : "fx:id=\"colClient\" was not injected: check your FXML file 'sample.fxml'.";
        assert colipClient != null : "fx:id=\"colipClient\" was not injected: check your FXML file 'sample.fxml'.";
        assert colPath != null : "fx:id=\"colPath\" was not injected: check your FXML file 'sample.fxml'.";
        assert colDate != null : "fx:id=\"colDate\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnInsert != null : "fx:id=\"btnInsert\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'sample.fxml'.";
        */
    }
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnInsert){
            System.out.println("Appel de la methode insertRecord");
            inserRecord();
        } else if(event.getSource() == btnUpdate){
            System.out.println("Appel de la methode updateRecord");
            updateRecord();
        } else if (event.getSource() == btnDelete){
            System.out.println("Appel de la methode deleteRecord");
            deleteRecord();
        }
    }
    @FXML
    public void handleMouseAction(){
        Files file = tViewDocs.getSelectionModel().getSelectedItem();
        tfId.setText(""+file.getIdDoc());
        tfTitre.setText(file.getTitle());
        tfClient.setText(file.getClient());
        tfIP.setText(file.getIpClient());
        tfPath.setText(file.getPath());
        tfDate.setText(file.getDate());
    }
// Connection et récuperation de données
    public Connection getConnection() {
        String jdbcURL = "jdbc:postgresql://localhost:5432/files";
        String username = "postgres";
        String password = "Abc123";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            return  connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Files> getObservableList(){
        ObservableList<Files> files = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String sql = "SELECT * FROM files";
        Statement st;
        ResultSet rs;
        try{
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Files file = new Files( rs.getInt("idDoc"),
                                        rs.getString("titre"),
                                        rs.getString("client"),
                                        rs.getString("ip"),
                                        rs.getString("path"),
                                        rs.getString("date"));
                files.add(file);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return files;
    }

    public void showFiles(){
        ObservableList<Files> list = getObservableList();
        // colidDoc for the column in tableView and idDoc for attribute in Files Class
        colidDoc.setCellValueFactory(new PropertyValueFactory<Files, Integer>("idDoc"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Files, String>("title"));
        colClient.setCellValueFactory(new PropertyValueFactory<Files, String>("client"));
        colipClient.setCellValueFactory(new PropertyValueFactory<Files, String>("ipClient"));
        colPath.setCellValueFactory(new PropertyValueFactory<Files, String>("path"));
        colDate.setCellValueFactory(new PropertyValueFactory<Files, String>("date"));

        tViewDocs.setItems(list);
    }

    public void inserRecord(){
        String sql = "INSERT INTO files VALUES("
                +tfId.getText()+",'"+tfTitre.getText()+"','"+tfClient.getText()+"','"
                +tfIP.getText()+"','"+tfPath.getText()+"','"+tfDate.getText()+"')";
        executeQuery(sql);
        showFiles();
    }
    public void updateRecord(){
        String sql = "UPDATE files SET titre ='"+
                tfTitre.getText()+"', client = '"+
                tfClient.getText()+"', ip = '"+
                tfIP.getText()+"' WHERE \"idDoc\" = "+tfId.getText();
        executeQuery(sql);
        showFiles();
    }
    public void deleteRecord(){
        String sql = "DELETE FROM files WHERE \"idDoc\" = "+tfId.getText();
        executeQuery(sql);
        showFiles();
    }

    private void executeQuery(String sql) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(sql);
            showFiles();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

