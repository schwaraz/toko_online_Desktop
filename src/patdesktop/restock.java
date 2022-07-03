package patdesktop;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class restock extends JFrame {
    private JTextField hberas;
    private JButton submitButton;
    private JTextField ttberas;
    private JLabel stbareas;
    private JLabel stgula;
    private JLabel stgaram;
    private JLabel stminyak;
    private JLabel sttelur;
    private JTextField hgula;
    private JTextField hgaram;
    private JTextField hminyak;
    private JTextField htelur;
    private JTextField ttgula;
    private JTextField ttgaram;
    private JTextField ttminyak;
    private JTextField tttelur;
    private JPanel panel1;
    private JButton backButton;

    public restock(){
        this.setTitle("Restock page");
        this.setContentPane(panel1);
        this.setMaximumSize(new Dimension(450,475));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        try {
            getstock();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    poststock();
                    dispose();
                    Mainmenu Mainmenu = new Mainmenu();
                    Mainmenu.setSize(300,200);
                    Mainmenu.setVisible(true);
                    Mainmenu.setLocationRelativeTo(null);
                    Mainmenu.setDefaultCloseOperation(EXIT_ON_CLOSE);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        backButton.addActionListener(new ActionListener() {
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

    }

    private void poststock() throws Exception{
        String url = "http://192.168.1.12:8000/restock/";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("PUT"); //PUT / DELETE
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.connect();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("beras", ttberas.getText());
        jsonParam.put("minyak", ttminyak.getText());
        jsonParam.put("gula", ttgula.getText());
        jsonParam.put("garam", ttgaram.getText());
        jsonParam.put("telur", tttelur.getText());
        jsonParam.put("htelur", htelur.getText());
        jsonParam.put("hberas", hberas.getText());
        jsonParam.put("hgula", hgula.getText());
        jsonParam.put("hgaram", hgaram.getText());
        jsonParam.put("hminyak", hminyak.getText());
        jsonParam.put("hminyak", hminyak.getText());
        String text = new String(Files.readAllBytes(Paths.get("tes.json")), StandardCharsets.UTF_8);
        System.out.println("Datareadnama : \n" + text);
        JSONArray sesion = new JSONArray(text);

        jsonParam.put("nama_toko", sesion.getJSONObject(0).getString("NAMA_TOKO"));
        byte[] jsData = jsonParam.toString().getBytes(StandardCharsets.UTF_8);
        OutputStream os = con.getOutputStream();
        os.write(jsData);
        int responseCode = con.getResponseCode();
        System.out.println("Send Post Request to : " + url);
        System.out.println("Response Code : " + responseCode);
    }

    private void getstock() throws Exception{
        String url = "http://192.168.1.12:8000/restock";
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
        String text = new String(Files.readAllBytes(Paths.get("tes.json")), StandardCharsets.UTF_8);
        System.out.println("Datareadnama : \n" + text);
        JSONArray sesion = new JSONArray(text);

        jsonParam.put("nama_toko", sesion.getJSONObject(0).getString("NAMA_TOKO"));

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
        JSONArray myArray = new JSONArray(response.toString());
        JSONObject arrObj = myArray.getJSONObject(0);
        stbareas.setText(String.valueOf(arrObj.getInt("BERAS")));
        stgaram.setText(String.valueOf(arrObj.getInt("GARAM")));
        stgula.setText(String.valueOf(arrObj.getInt("GULA")));
        stminyak.setText(String.valueOf(arrObj.getInt("MINYAK")));
        sttelur.setText(String.valueOf(arrObj.getInt("TELUR")));
        hberas.setText(String.valueOf(arrObj.getInt("HARGA_BERAS")));
        hgaram.setText(String.valueOf(arrObj.getInt("HARGA_GARAM")));
        hgula.setText(String.valueOf(arrObj.getInt("HARGA_GULA")));
        hminyak.setText(String.valueOf(arrObj.getInt("HARGA_MINYAK")));
        htelur.setText(String.valueOf(arrObj.getInt("HARGA_TELUR")));
        ttberas.setText(String.valueOf(arrObj.getInt("BERAS")));
        ttgaram.setText(String.valueOf(arrObj.getInt("GARAM")));
        ttgula.setText(String.valueOf(arrObj.getInt("GULA")));
        ttminyak.setText(String.valueOf(arrObj.getInt("MINYAK")));
        tttelur.setText(String.valueOf(arrObj.getInt("TELUR")));
        os.flush();
        os.close();
    }
}
