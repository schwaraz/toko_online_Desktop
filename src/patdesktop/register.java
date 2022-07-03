package patdesktop;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class register extends JFrame{
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton button1;
    private JTextField textField2;
    private JLabel errorcheck;
    private JButton button2;

    public register(){
        this.setTitle("Login Page");
        this.setContentPane(panel1);
        this.setMaximumSize(new Dimension(450,475));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Mainmenu Mainmenu = new Mainmenu();
                Mainmenu.setSize(300,200);
                Mainmenu.setVisible(true);
                Mainmenu.setLocationRelativeTo(null);
                Mainmenu.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField1.getPassword().toString() != ""){
                    System.out.println(passwordField1.getPassword().toString());
                    System.out.println(passwordField1.getPassword().toString());
                    if(Arrays.equals(passwordField1.getPassword(), passwordField2.getPassword())){


                        if( (textField1.getText().length()!=0) && textField2.getText().length()!=0){

                            try {
                                postregist();
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else {
                            errorcheck.setText("username kosong");
                        }

                    }
                    else {
                        errorcheck.setText("password tidak sama");
                    }
                }
                else {
                    errorcheck.setText("password kosong");
                }
            }
        });
    }

    private void postregist() throws Exception{
        String url = "http://192.168.1.12:8000/createtoko/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST"); //PUT / DELETE
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.connect();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("username", textField1.getText());
        jsonParam.put("pass", String.valueOf(passwordField1.getPassword()));
        jsonParam.put("alamat", textField2.getText());
        byte[] jsData = jsonParam.toString().getBytes(StandardCharsets.UTF_8);
        OutputStream os = con.getOutputStream();
        os.write(jsData);

        int responseCode = con.getResponseCode();
        System.out.println("Send Post Request to : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );
        String input;
        StringBuilder response = new StringBuilder();
        while ((input = in.readLine()) != null) {
            response.append(input);
        }
        in.close();
        System.out.println("Data : \n" + response.toString());
//        JSONArray myArray = new JSONArray(response.toString());
            dispose();
            Mainmenu Mainmenu = new Mainmenu();
            Mainmenu.setSize(300,200);
            Mainmenu.setVisible(true);
            Mainmenu.setLocationRelativeTo(null);
            Mainmenu.setDefaultCloseOperation(EXIT_ON_CLOSE);


        os.flush();
        os.close();
    }
}
