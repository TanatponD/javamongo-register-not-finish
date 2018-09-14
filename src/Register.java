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
    public static boolean booleancheckID = false;

    static MongoClientURI uri = new MongoClientURI("mongodb://writer:writer12345@ds133762.mlab.com:33762/javamongo");
    static MongoClient client = new MongoClient(uri);
    static MongoDatabase db = client.getDatabase(uri.getDatabase());
    static MongoCollection<Document> collection = db.getCollection("register");


    private JPanel mainPanel;
    private JTextField usernametextField;
    private JPasswordField passwordField;
    private JButton registerbtn;
    private JLabel lbluser;
    private JLabel lblpwd;
    private JPasswordField passwordField1;
    private JTextField emailField;
    private JLabel cfPWD;


    public Register() {
        registerbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmPass();
                checkID();
                checkEmpty();
                confirmPass();
                if (booleancheckID) {
                    JLabel label = new JLabel("มีไอดีนี้อยู่ในระบบแล้ว");
                    label.setFont(new Font("TH SarabunPSK", Font.BOLD, 18));
                    JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.WARNING_MESSAGE);

                    reset();
                } else {
                    if (checkEmpty()) {
                        JLabel label = new JLabel("กรุณากรอกข้อมูลให้ครบถ้วน");
                        label.setFont(new Font("TH SarabunPSK", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.WARNING_MESSAGE);
                        reset();

                    } else if (!checkEmpty()) {
                        if (!confirmPass()) {
                            JLabel label = new JLabel("Password ไม่ตรงกัน");
                            label.setFont(new Font("TH SarabunPSK ", Font.BOLD, 18));
                            JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.WARNING_MESSAGE);
                            reset();
                        } else {
                            submitRegister();
                            reset();
                        }

                    }
                }
            }
        });
    }

    public void checkID() {

        MongoClientURI uri = new MongoClientURI("mongodb://writer:writer12345@ds133762.mlab.com:33762/javamongo");
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());

        MongoCollection<Document> user = db.getCollection("register");

        try {

            Document myDoc = user.find(eq("ID", usernametextField.getText())).first();
            System.out.println(myDoc.toJson());
            booleancheckID = true;

        } catch (Exception e) {
            //ดักไอดีซ้ำและคืนค่า Boolean
            booleancheckID = false;
        }
    }

    public boolean checkEmpty() {
        if (usernametextField.getText().equals("") || passwordField.getText().equals("") || emailField.getText().equals("")) {

            return true;
        } else {
            return false;
        }
    }

    public void submitRegister() {
        MongoClientURI uri = new MongoClientURI("mongodb://writer:writer12345@ds133762.mlab.com:33762/javamongo");
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());

        MongoCollection<Document> user = db.getCollection("register");

        List<Document> list = new ArrayList<Document>();

        list.add(new Document("Username", usernametextField.getText())
                .append("Password", passwordField.getText())
                .append("E-mail", emailField.getText())

        );
        user.insertMany(list);
        JLabel label = new JLabel("สมัครเรียบร้อย");
        label.setFont(new Font("TH SarabunPSK", Font.BOLD, 18));
        JOptionPane.showMessageDialog(null, label);

        client.close();

    }

    public boolean confirmPass() {
        if (passwordField.getText().equals(passwordField1.getText())) {
            return true;
        } else {
            return false;
        }
    }

    public void reset() {

        usernametextField.setText("");
        passwordField.setText("");
        passwordField1.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setContentPane(new Register().mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        MongoCollection<Document> register = db.getCollection("registers");
        collection.find();
    }


}


