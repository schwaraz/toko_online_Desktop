package patdesktop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
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
import java.util.ArrayList;
import java.util.List;

public class checkpesanan extends JFrame{
    private JPanel panel1;
    private JTable table1;
    private JButton button1;
    private JLabel row;
    private JButton subminTransaksiSelesaiButton;
    private JButton backButton;

    public checkpesanan(){
        this.setTitle("Login Page");
        this.setContentPane(panel1);
        this.setMaximumSize(new Dimension(450,475));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
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
        subminTransaksiSelesaiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    submitdata();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int a = tablemodel.row();
                String tes = tablemodel.addUser(a);
                row.setText(tes);
            }
        });
        try {
            getpesanana();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    private void submitdata() throws Exception{
        String url = "http://192.168.1.12:8000/submittransaksi/";
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


        jsonParam.put("idtransaksi", row.getText());

        byte[] jsData = jsonParam.toString().getBytes(StandardCharsets.UTF_8);
        OutputStream os = con.getOutputStream();
        os.write(jsData);

        int responseCode = con.getResponseCode();
        System.out.println("Send Post Request to : " + url);
        System.out.println("Response Code : " + responseCode);

        os.flush();
        os.close();
        try {
            getpesanana();
        } catch (Exception a) {
            throw new RuntimeException(a);
        }
    }

    private void getpesanana() throws Exception{
        String url = "http://192.168.1.12:8000/listtransaksidesktop/";
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

        jsonParam.put("id", sesion.getJSONObject(0).getInt("ID_TOKO"));

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
        String []id_transaksi= new String[myArray.length()];
        String[] beras= new String[myArray.length()];
        String[] minyak= new String[myArray.length()];
        String[] gula= new String[myArray.length()];
        String[] garam= new String[myArray.length()];
        String[] telur= new String[myArray.length()];
        String[] total= new String[myArray.length()];
        String[] alamat= new String[myArray.length()],alamatlanjut = new String[myArray.length()];
        String[] date = new String[myArray.length()];
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        new ObjectMapper().configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
       List<table> datamodeltable = new ArrayList<table>();
        table tablea = new table();
        for (int i=0; i < myArray.length();i++){
            JSONObject arrObj = myArray.getJSONObject(i);
            id_transaksi[i] = String.valueOf(arrObj.getInt("NOMER_TRANSAKSI"));
            beras[i]= String.valueOf(arrObj.getInt("BERAS"));
            minyak[i]= String.valueOf(arrObj.getInt("MINYAK"));
            gula[i]= String.valueOf(arrObj.getInt("GULA"));
            garam[i]= String.valueOf(arrObj.getInt("GARAM"));
            telur[i]= String.valueOf(arrObj.getInt("TELUR"));
            total[i]= String.valueOf(arrObj.getInt("TOTAL"));
            alamat[i]=arrObj.getString("ALAMAT_PEMBELI");
            alamatlanjut[i]=arrObj.getString("ALAMAT_LANJUT");
            date[i]=arrObj.getString("DATE");
            date[i].replace("\n", "");

            try {

                String test1 = "{\"NOMOR_TRANSAKSI\": \""+id_transaksi[i]+"\" , \"BERAS\" : \""+beras[i]+"\",\"MINYAK\" : \""+minyak[i]+"\", \"GULA\" : \""+gula[i]+"\", \"GARAM\":\""+garam[i]+"\", \"TELUR\":\""+telur[i]+"\", \"TOTAL_PEMBELIAN\":\""+total[i]+"\", \"ALAMAT\":\""+alamat[i]+"\", \"ALAMAT_LANJUT\":\""+alamatlanjut[i]+"\", \"DATE\":\""+date[i]+"\"}";
                //String test1 = "{\"NOMER_TRANSAKSI\": \"5\" , \"BERAS\" : \"5\",\"MINYAK\" : \"5\", \"GULA\" : \"5\", \"GARAM\":\"5\", \"TELUR\":\"5\", \"TOTAL\":\"5\", \"ALAMAT\":\"5\", \"ALAMAT_LANJUT\":\"5\", \"DATE\":\"5\"}";

                tablea =mapper.readValue(test1,table.class);

            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            datamodeltable.add(tablea);
        }

        os.flush();
        os.close();
        tablemodel model = new tablemodel(datamodeltable);
        table1.setModel(model);
    }

}
