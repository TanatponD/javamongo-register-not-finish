import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.Arrays;

import com.mongodb.Block;

import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Updates.*;

import com.mongodb.client.result.UpdateResult;

import java.util.ArrayList;
import java.util.List;


public class Register {

    static MongoClientURI uri = new MongoClientURI("mongodb://writer:writer12345@ds133762.mlab.com:33762/javamongo");
    static MongoClient client = new MongoClient(uri);
    static MongoDatabase db = client.getDatabase(uri.getDatabase());
    static MongoCollection<Document> collection = db.getCollection("register");



    private  JPanel mainPanel;
    private  JTextField usernametextField;
    private  JPasswordField passwordField;
    private  JButton registerbtn;
    private  JLabel lbluser;
    private  JLabel lblpwd;
    private  JPasswordField passwordField1;
    private  JTextField emailField;
    private  JLabel cfPWD;


    public Register() {
        registerbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (usernametextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please insert username");
                }

                if (emailField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please insert email");
                }

                if (Arrays.equals(passwordField1.getPassword(), passwordField.getPassword())) {
                    JOptionPane.showMessageDialog(null, "Password match");
                } else {
                    JOptionPane.showMessageDialog(null, "Password doesn't match");
                }

            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setContentPane(new Register().mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        List<Document> list = new ArrayList<>();


        list.add(new Document("username", usernametextField.getText())
                .append("password", passwordField.getPassword())
                .append("email", this.emailField.getText())
        );

        MongoCollection<Document> register = db.getCollection("registers");
        collection.find();
    }


}


