package Java_Challenge_4;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import io.github.cdimascio.dotenv.Dotenv;

public class VentanaPrincipal {
    private static Dotenv dotenv = Dotenv.load();

    static String url = dotenv.get("DB_URL").trim();
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("DataCenter");
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JLabel databaseLabel = new JLabel("DataCenter    ➡️​");
        databaseLabel.setBounds(50,10,250,50);
        panel.add(databaseLabel);
        
        JLabel name_databaseLabel = new JLabel("null");
        name_databaseLabel.setBounds(155,10,250,50);
        panel.add(name_databaseLabel);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setBounds(765, 140, 195, 190);
        botonesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonesPanel.setVisible(false);
        

        JButton exitButton = new JButton("X");
        exitButton.setBounds(775, 15, 175, 50);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        exitButton.setBackground(Color.RED);
        exitButton.setFocusable(false);
        panel.add(exitButton);

        JButton connectDataBaseButton = new JButton("Conectar a la base de datos");
        connectDataBaseButton.setBounds(50, 350, 175, 50);
        connectDataBaseButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        connectDataBaseButton.setFocusable(false);
        connectDataBaseButton.setBackground(Color.GREEN);
        panel.add(connectDataBaseButton);

        ///////////////////////////////
        ///          SQL            ///
        ///////////////////////////////
        String showTableSql = "select * from manager_dpto"; // -> SQL del boton: tableButton

        



        JButton tableButton = new JButton("Mostrar tabla x");
        tableButton.setBounds(775, 150, 175, 50);
        tableButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tableButton.setBackground(Color.lightGray);
        tableButton.setFocusable(false);
        tableButton.setVisible(false);
        panel.add(tableButton);
        
        JButton tableButton1 = new JButton("Mostrar tabla x");
        tableButton1.setBounds(775, 210, 175, 50);
        tableButton1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tableButton1.setBackground(Color.lightGray);
        tableButton1.setFocusable(false);
        tableButton1.setVisible(false);
        panel.add(tableButton1);

        JButton tableButton2 = new JButton("Mostrar tabla x");
        tableButton2.setBounds(775, 270, 175, 50);
        tableButton2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tableButton2.setBackground(Color.lightGray);
        tableButton2.setFocusable(false);
        tableButton2.setVisible(false);
        panel.add(tableButton2);
        
        connectDataBaseButton.addActionListener(e -> {
            try{
                panel.add(botonesPanel);
                botonesPanel.setVisible(true);
                tableButton.setVisible(true);
                tableButton1.setVisible(true);
                tableButton2.setVisible(true);
                
                Connection con = dbConnection.conectar();
                String nombreDataBase = con.getCatalog();
                name_databaseLabel.setText(nombreDataBase);
                System.out.println("Base de datos conectada: "+nombreDataBase);
                
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        tableButton.addActionListener(e -> {
            try {
                Connection conn = dbConnection.conectar();
                Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = statement.executeQuery(showTableSql);
            

                String [] cabezera = {};
                Object[][] data = {};
                
                int columnas = rs.getMetaData().getColumnCount();
                rs.last();
                int registros = rs.getRow();
                cabezera = new String [columnas];
                for (int i = 0; i < columnas; i++) {
                    cabezera[i] = rs.getMetaData().getColumnName(i+1);
                }
                int i = 0;
                data = new Object[registros][columnas];
                rs.first();
                while(rs.next()){
                    for (int j = 1; j <= columnas; j++) {
                        data[i][j-1] = rs.getString(j);
                    }
                i++;
                }
                JTable table = new JTable(data, cabezera);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(70, 100, 600, 200);
                panel.add(scrollPane);
                System.out.println();
            } catch (Exception ex) {
                ex.printStackTrace();
            }     
        });

        frame.setVisible(true);
    }

    int getQueryRowCount(Connection conn, String query) throws SQLException {
    try (Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet scrollableRS = statement.executeQuery(query)) {
        scrollableRS.last();
        return scrollableRS.getRow();
    }
}
}
