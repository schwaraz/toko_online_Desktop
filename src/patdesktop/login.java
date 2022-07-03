package patdesktop;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class login extends JFrame{
    private JPanel panel1;
    private JTextField tFUsername;
    private JPasswordField pFPassword;
    private JButton button1;
    private JLabel errorcheck;
    String username,password;
    String sessionID;
    public login(){
        this.setTitle("Login page");
        this.setContentPane(panel1);
        this.setMaximumSize(new Dimension(450,475));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        removedata();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( (tFUsername.getText().length()!=0)||(pFPassword.getPassword().length!=0)) {
                    username = tFUsername.getText();
                    password = String.valueOf(pFPassword.getPassword());
                    clientPOST();
                }
                else{
                        errorcheck.setText("ada yang kosong");
                }
            }
        });
    }

    private void removedata() {
        try {
            FileWriter file = new FileWriter("tes.json");
            file.write(String.valueOf("{}"));
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void clientPOST() {
        try {
            GetAdminSessionID();



        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void GetAdminSessionID() throws Exception{
        String url = "http://192.168.1.12:8000/logintoko/";
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
        jsonParam.put("username", tFUsername.getText());
        jsonParam.put("password", String.valueOf(pFPassword.getPassword()));

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
        if(Objects.equals(response.toString(), "Login Gagal")){
            errorcheck.setText("passowrd atau username salah");
        }

        if(!Objects.equals(response.toString(), "Login Gagal")){
            try {

                FileWriter file = new FileWriter("tes.json");
                file.write(response.toString());
                file.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            dispose();
            Mainmenu Mainmenu = new Mainmenu();
            Mainmenu.setSize(300,200);
            Mainmenu.setVisible(true);
            Mainmenu.setLocationRelativeTo(null);
            Mainmenu.setDefaultCloseOperation(EXIT_ON_CLOSE);

        }
        os.flush();
        os.close();
    }
}
