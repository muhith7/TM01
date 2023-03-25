package CONNECT;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class FirstConnectt extends JFrame implements ActionListener {
    private JTextField searchField;
    private JButton searchButton;
    private JLabel resultLabel;

    public FirstConnectt() {
        super("Search Harga");

        // set up components
        searchField = new JTextField(10);
        searchButton = new JButton("Cari");
        searchButton.addActionListener(this);
        resultLabel = new JLabel();

        // add components to content panel
        JPanel contentPane = new JPanel();
        contentPane.add(new JLabel("Masukkan harga:"));
        contentPane.add(searchField);
        contentPane.add(searchButton);
        contentPane.add(resultLabel);
        setContentPane(contentPane);

        // set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String searchPrice = searchField.getText();

        ConnectURI koneksisaya = new ConnectURI();
        URL myAddress = koneksisaya.buildURL("https://farmasi.mimoapps.xyz/mimoqss2auyqD1EAlkgZCOhiffSsFl6QqAEIGtM");
        String response = null;
        try {
            response = koneksisaya.getRespondFrontHttpUrl(myAddress);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        assert response != null;
        JSONArray responJSON = new JSONArray(response);
        ArrayList<ResponseModel> respondModel = new ArrayList<>();
        for (int i = 0; i < responJSON.length(); i++) {
            ResponseModel resmodel = new ResponseModel();
            JSONObject myJSONObject = responJSON.getJSONObject(i);
            resmodel.setI_sell(myJSONObject.getString("i_sell"));
            respondModel.add(resmodel);
        }

        int count = 0;
        for (ResponseModel responsee : respondModel) {
            if (responsee.getI_sell().equals(searchPrice)) {
                count++;
            }
        }

        resultLabel.setText("Jumlah yang mempunyai harga " + searchPrice + ": " + count);
    }

    public static void main(String[] args) {
        new FirstConnectt();
    }
}
