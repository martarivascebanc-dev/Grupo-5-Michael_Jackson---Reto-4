package Java_Challenge_4;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import io.github.cdimascio.dotenv.Dotenv;
/*Iniciando la interfaz e inicializar las librerias */
public class VentanaPrincipal {
    private static Dotenv dotenv = Dotenv.load(); 

    static String url = dotenv.get("DB_URL").trim(); 
/*Botones para conectar con la base de datos */
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("DataCenter");
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JLabel databaseLabel = new JLabel("DataCenter​");
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
        /*Botones que se muestran el la interfaz */ 
        JButton tableButton = new JButton("Mostrar Sensores");
        tableButton.setBounds(775, 150, 175, 50);
        tableButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tableButton.setBackground(Color.lightGray);
        tableButton.setFocusable(false);
        tableButton.setVisible(false);
        panel.add(tableButton);
        
        JButton tableButton1 = new JButton("Mostrar Energia");
        tableButton1.setBounds(775, 210, 175, 50);
        tableButton1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tableButton1.setBackground(Color.lightGray);
        tableButton1.setFocusable(false);
        tableButton1.setVisible(false);
        panel.add(tableButton1);

        // Ibon
        JButton tableButton2 = new JButton("Sensores + Energia");
        tableButton2.setBounds(775, 270, 175, 50);
        tableButton2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        tableButton2.setBackground(Color.lightGray);
        tableButton2.setFocusable(false);
        tableButton2.setVisible(false);
        panel.add(tableButton2);

        JButton filtrarButton = new JButton("Filtrar");
        filtrarButton.setBounds(775, 150, 175, 50);
        filtrarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        filtrarButton.setBackground(Color.ORANGE);
        filtrarButton.setFocusable(false);
        filtrarButton.setVisible(false);
        panel.add(filtrarButton);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(775, 210, 175, 50);
        eliminarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        eliminarButton.setBackground(Color.CYAN);
        eliminarButton.setFocusable(false);
        eliminarButton.setVisible(false);
        panel.add(eliminarButton);

        // Ibon 
        JButton volverButton = new JButton("Volver");
        volverButton.setBounds(775, 270, 175, 50);
        volverButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        volverButton.setBackground(Color.lightGray);
        volverButton.setFocusable(false);
        volverButton.setVisible(false);
        panel.add(volverButton);

        // Ibon: guarda la query activa para saber sobre que tabla filtrar
        String[] tablaActiva = {""};

        // Ibon
        JLabel columnaLabel = new JLabel("Columna:");
        columnaLabel.setBounds(70, 310, 80, 30);
        columnaLabel.setVisible(false);
        panel.add(columnaLabel);

        JTextField columnaField = new JTextField();
        columnaField.setBounds(150, 310, 150, 30);
        columnaField.setVisible(false);
        panel.add(columnaField);

        JLabel condicionLabel = new JLabel("Condicion:");
        condicionLabel.setBounds(320, 310, 80, 30);
        condicionLabel.setVisible(false);
        panel.add(condicionLabel);

        JTextField condicionField = new JTextField();
        condicionField.setBounds(400, 310, 150, 30);
        condicionField.setVisible(false);
        panel.add(condicionField);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(560, 310, 100, 30);
        buscarButton.setVisible(false);
        panel.add(buscarButton);

        //Marta
        JLabel columnaLabelEliminar = new JLabel("Columna:");
        columnaLabelEliminar.setBounds(70, 310, 80, 30);
        columnaLabelEliminar.setVisible(false);
        panel.add(columnaLabelEliminar);

        JTextField columnaFieldEliminar = new JTextField();
        columnaFieldEliminar.setBounds(150, 310, 150, 30);
        columnaFieldEliminar.setVisible(false);
        panel.add(columnaFieldEliminar);

        JLabel condicionLabelEliminar = new JLabel("Condicion:");
        condicionLabelEliminar.setBounds(320, 310, 80, 30);
        condicionLabelEliminar.setVisible(false);
        panel.add(condicionLabelEliminar);

        JTextField condicionFieldEliminar = new JTextField();
        condicionFieldEliminar.setBounds(400, 310, 150, 30);
        condicionFieldEliminar.setVisible(false);
        panel.add(condicionFieldEliminar);

        JButton buscarButtonEliminar = new JButton("Buscar");
        buscarButtonEliminar.setBounds(560, 310, 100, 30);
        buscarButtonEliminar.setVisible(false);
        panel.add(buscarButtonEliminar);

        // Ibon scroll activo, se reemplaza cada vez que se muestra una tabla nueva (CHATGPT)
        JScrollPane[] scrollActivo = {null};

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

        // Ibon
        Runnable mostrarSubBotones = () -> {
            tableButton.setVisible(false);
            tableButton1.setVisible(false);
            tableButton2.setVisible(false);
            filtrarButton.setVisible(true);
            eliminarButton.setVisible(true);
            volverButton.setVisible(true);
            columnaLabel.setVisible(false);
            columnaField.setVisible(false);
            condicionLabel.setVisible(false);
            condicionField.setVisible(false);
            buscarButton.setVisible(false);
            columnaField.setText("");
            condicionField.setText("");
        };

        // Ibon
        filtrarButton.addActionListener(e -> {
            columnaLabel.setVisible(true);
            columnaField.setVisible(true);
            condicionLabel.setVisible(true);
            condicionField.setVisible(true);
            buscarButton.setVisible(true);

            columnaField.setText("");
            condicionField.setText("");

            columnaField.requestFocusInWindow();
        });

        tableButton.addActionListener(e -> {
            try {
                tablaActiva[0] = "SELECT * FROM sensores"; // Ibon
                Connection conn = dbConnection.conectar();
                Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = statement.executeQuery("SELECT * FROM sensores");

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
                if (scrollActivo[0] != null) panel.remove(scrollActivo[0]);
                JTable table = new JTable(data, cabezera);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(70, 100, 600, 200);
                panel.add(scrollPane);
                scrollActivo[0] = scrollPane;
                panel.revalidate();
                panel.repaint();

                mostrarSubBotones.run();

            } catch (Exception ex) {
                ex.printStackTrace();
            }     
        });

        tableButton1.addActionListener(e -> {
            try {
                tablaActiva[0] = "SELECT * FROM energia"; 
                Connection conn = dbConnection.conectar();
                Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = statement.executeQuery("SELECT * FROM energia");

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
                if (scrollActivo[0] != null) panel.remove(scrollActivo[0]);
                JTable table = new JTable(data, cabezera);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(70, 100, 600, 200);
                panel.add(scrollPane);
                scrollActivo[0] = scrollPane;
                panel.revalidate();
                panel.repaint();

                // Ibon
                mostrarSubBotones.run();

            } catch (Exception ex) {
                ex.printStackTrace();
            }       
        });

        // Ibon: mostrar inner join y cambiar botones
        tableButton2.addActionListener(e -> {
            try {
                // Ibon
                tablaActiva[0] = "SELECT s.id_sensor, s.fecha, s.lugar, s.valor_medido, s.unidad_medida, s.tipo_sensor, " +
                    "e.id_energia, e.consumo_total, e.coste_total, e.voltaje " +
                    "FROM sensores s " +
                    "INNER JOIN centro_de_datos c ON c.id_sensores = s.id_sensor " +
                    "INNER JOIN energia e ON e.id_centro_de_datos = c.id_centro_de_datos";
                Connection conn = dbConnection.conectar();
                Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = statement.executeQuery(
                    "SELECT s.id_sensor, s.fecha, s.lugar, s.valor_medido, s.unidad_medida, s.tipo_sensor, " +
                    "e.id_energia, e.consumo_total, e.coste_total, e.voltaje " +
                    "FROM sensores s " +
                    "INNER JOIN centro_de_datos c ON c.id_sensores = s.id_sensor " +
                    "INNER JOIN energia e ON e.id_centro_de_datos = c.id_centro_de_datos"
                );

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
                if (scrollActivo[0] != null) panel.remove(scrollActivo[0]);
                JTable table = new JTable(data, cabezera);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(70, 100, 600, 200);
                panel.add(scrollPane);
                scrollActivo[0] = scrollPane;
                panel.revalidate();
                panel.repaint();

                // Ibon
                mostrarSubBotones.run();

            } catch (Exception ex) {
                ex.printStackTrace();
            }     
        });

        // Ibon
        volverButton.addActionListener(e -> {
            filtrarButton.setVisible(false);
            eliminarButton.setVisible(false);
            volverButton.setVisible(false);
            tableButton.setVisible(true);
            tableButton1.setVisible(true);
            tableButton2.setVisible(true);
            // Ibon
            columnaLabel.setVisible(false);
            columnaField.setVisible(false);
            condicionLabel.setVisible(false);
            condicionField.setVisible(false);
            buscarButton.setVisible(false);
            columnaField.setText("");
            condicionField.setText("");
            columnaLabelEliminar.setVisible(false);
            columnaFieldEliminar.setVisible(false);
            condicionLabelEliminar.setVisible(false);
            condicionFieldEliminar.setVisible(false);
            buscarButtonEliminar.setVisible(false);
            columnaFieldEliminar.setText("");
            condicionFieldEliminar.setText("");
            if (scrollActivo[0] != null) {
                panel.remove(scrollActivo[0]);
                scrollActivo[0] = null;
                panel.revalidate();
                panel.repaint();
            }
        });

        // Ibon (CHATGPT)
    
            buscarButton.addActionListener(e -> {
                try {
                    String valor = condicionField.getText().trim();
                    String query;
                    if (tablaActiva[0].contains("DELETE")) {
                        tablaActiva[0] = tablaActiva[0].replace("DELETE", "SELECT");
                    }
                    try {
                        Double.parseDouble(valor);
                        query = tablaActiva[0] + " WHERE " + columnaField.getText().trim() + " = " + valor;
                    } catch (NumberFormatException ex2) {
                        query = tablaActiva[0] + " WHERE " + columnaField.getText().trim() + " = '" + valor + "'";
                    }
                    Connection conn = dbConnection.conectar();
                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery(query);

                    int columnas = rs.getMetaData().getColumnCount();
                    String[] cabezera = new String[columnas];
                    for (int i = 0; i < columnas; i++)
                        cabezera[i] = rs.getMetaData().getColumnName(i+1);

                    // Ibon scroll activo, se reemplaza cada vez que se muestra una tabla nueva (CHATGPT)
                    java.util.List<Object[]> filas = new java.util.ArrayList<>();
                    while (rs.next()) {
                        Object[] fila = new Object[columnas];
                        for (int j = 1; j <= columnas; j++)
                            fila[j-1] = rs.getString(j);
                        filas.add(fila);
                    }
                    Object[][] data = filas.toArray(new Object[0][]);

                    if (scrollActivo[0] != null) panel.remove(scrollActivo[0]);
                    JTable table = new JTable(data, cabezera);
                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setBounds(70, 100, 600, 200);
                    panel.add(scrollPane);
                    scrollActivo[0] = scrollPane;
                    panel.revalidate();
                    panel.repaint();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            });
//Marta e Ibai
            eliminarButton.addActionListener(e -> {
                columnaLabel.setVisible(false);
                columnaField.setVisible(false);
                condicionLabel.setVisible(false);
                condicionField.setVisible(false);
                buscarButton.setVisible(false);

                columnaLabelEliminar.setVisible(true);
                columnaFieldEliminar.setVisible(true);
                condicionLabelEliminar.setVisible(true);
                condicionFieldEliminar.setVisible(true);
                buscarButtonEliminar.setVisible(true);

                columnaFieldEliminar.setText("");
                condicionFieldEliminar.setText("");
                columnaField.setText("");
                condicionField.setText("");

                columnaFieldEliminar.requestFocusInWindow();
            });
            
            
            buscarButtonEliminar.addActionListener(e -> {
                String columna = columnaFieldEliminar.getText();
                String valor = condicionFieldEliminar.getText();
                if (tablaActiva[0].contains("SELECT")) {
                    tablaActiva[0] = tablaActiva[0].replace("SELECT *", "DELETE");
                    System.out.println(tablaActiva[0]);
                }
                try {
                    Connection conn1 = dbConnection.conectar();
                    Statement statement1 = conn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String query = tablaActiva[0]+" WHERE " +columna+ " = "+"'"+ valor+"'";
                    statement1.executeUpdate(query);
                    
                    if (tablaActiva[0].contains("DELETE")) {
                        tablaActiva[0] = tablaActiva[0].replace("DELETE", "SELECT *");
                    }
                    ResultSet rs = statement1.executeQuery(tablaActiva[0]);
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
                    if (scrollActivo[0] != null) panel.remove(scrollActivo[0]);
                    JTable table = new JTable(data, cabezera);
                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setBounds(70, 100, 600, 200);
                    panel.add(scrollPane);
                    scrollActivo[0] = scrollPane;
                    panel.revalidate();
                    panel.repaint();

                    mostrarSubBotones.run();

                } catch (Exception e1) {
                    e1.printStackTrace();
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