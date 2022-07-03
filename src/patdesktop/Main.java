package patdesktop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        login login = new login();
        login.setSize(300,200);
        login.setVisible(true);
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(EXIT_ON_CLOSE);

//        SwingUtilities.invokeLater(new Runnable(){
//            public void run() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        new ObjectMapper().configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
//        List<table> datamodeltable = new ArrayList<table>();
//        table tablea = new table();
//        try {
//
//          //   String test1 = "{\"NOMER_TRANSAKSI\": \""+id_transaksi[i]+"\" , \"BERAS\" : \""+beras[i]+"\",\"MINYAK\" : \""+minyak[i]+"\", \"GULA\" : \""+gula[i]+"\", \"GARAM\":\""+garam[i]+"\", \"TELUR\":\""+telur[i]+"\", \"TOTAL\":\""+total[i]+"\", \"ALAMAT\":\""+alamat[i]+"\", \"ALAMAT_LANJUT\":\""+alamatlanjut[i]+"\", \"DATE\":\""+date[i]+"\"}";
//          String test1 = "{\"NOMOR_TRANSAKSI\": \"5\" , \"BERAS\" : \"5\",\"MINYAK\" : \"5\", \"GULA\" : \"5\", \"GARAM\":\"5\", \"TELUR\":\"5\", \"TOTAL_PEMBELIAN\":\"5\", \"ALAMAT\":\"siwalanl kerto\", \"ALAMAT_LANJUT\":\"blabalblabaklsmdoiajwodiajd\", \"DATE\":\"2022-06-22T13:20:08.000Z\"}";
////            System.out.println(test1);
//            tablea = (table) mapper.readValue(test1,table.class);
//
//        } catch (JsonMappingException e) {
//            throw new RuntimeException(e);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        datamodeltable.add(tablea);
//        tablemodel model = new tablemodel(datamodeltable);
//        JTable table = new JTable(model) {
//            @Override
//            public Dimension getPreferredScrollableViewportSize() {
//                return new Dimension(1000, 1000);
//            }
//        };
//                JOptionPane.showMessageDialog(null, new JScrollPane(table));
//            }
//        });
    }
}

