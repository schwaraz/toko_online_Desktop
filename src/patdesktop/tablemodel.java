package patdesktop;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class tablemodel extends AbstractTableModel{
        static int row;
        private final String[] judul = {"NOMOR_TRANSAKSI","BERAS","MINYAK","GULA","GARAM","TELUR","TOTAL PEMBELIAN","ALAMAT","ALAMAT LANJUT", "DATE"};
        private static List<table> data= new ArrayList<table>();
        public tablemodel(List<table> data){
            this.data = data;
        }
        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return judul.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            row = rowIndex;
            System.out.println(rowIndex);
            return switch (columnIndex){
                case 0 -> data.get(rowIndex).getId_transaksi();
                case 1 -> data.get(rowIndex).getBERAS();
                case 2 -> data.get(rowIndex).getMINYAK();
                case 3 -> data.get(rowIndex).getGULA();
                case 4 -> data.get(rowIndex).getGARAM();
                case 5 -> data.get(rowIndex).getTELUR();
                case 6 -> data.get(rowIndex).getTOTAL_PEMBELIAN();
                case 7 -> data.get(rowIndex).getALAMAT();
                case 8 -> data.get(rowIndex).getALAMATLANJUT();
                case 9 -> data.get(rowIndex).getDATE();
                default -> "-";
            };
        }

        @Override
        public String getColumnName(int columnIndex) {
            return judul[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(getValueAt(0, columnIndex) != null){
                return getValueAt(0,columnIndex).getClass();
            }
            else {
                return Object.class;
            }
        }
    public static int row() {
        return row;
    }

    public static String addUser(int row) {
            String tes;
        tes = data.get(row).getId_transaksi();
        return tes;
    }

    }

