package patdesktop;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Mainmenu extends JFrame{
    private JPanel panel1;
    private JButton regisbutton;
    private JButton restockTokoButton;
    private JButton checkPesananButton;
    private JLabel namatoko;
    private JButton logput;
    JSONObject obb;
    public Mainmenu(){
        this.setTitle("Main page");
        this.setContentPane(panel1);
        this.setMaximumSize(new Dimension(450,475));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        readnama();
        checkPesananButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                checkpesanan checkpesanan = new checkpesanan();
                checkpesanan.setSize(500,500);
                checkpesanan.setVisible(true);
                checkpesanan.setLocationRelativeTo(null);
                checkpesanan.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
        restockTokoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                restock restock = new restock();
                restock.setSize(500,500);
                restock.setVisible(true);
                restock.setLocationRelativeTo(null);
                restock.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
        logput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                login login = new login();
                login.setSize(300,200);
                login.setVisible(true);
                login.setLocationRelativeTo(null);
                login.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
        regisbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                register register = new register();
                register.setSize(500,500);
                register.setVisible(true);
                register.setLocationRelativeTo(null);
                register.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
    }

    private void readnama() {
        try{
            String text = new String(Files.readAllBytes(Paths.get("tes.json")), StandardCharsets.UTF_8);
            System.out.println("Datareadnama : \n" + text);
            JSONArray obj = new JSONArray(text);
            namatoko.setText(obj.getJSONObject(0).getString("NAMA_TOKO"));
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }


    }
}
