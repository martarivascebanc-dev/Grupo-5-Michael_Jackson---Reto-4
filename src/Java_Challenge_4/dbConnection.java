package src.Java_Challenge_4;


import java.sql.Connection;
import java.sql.DriverManager;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import io.github.cdimascio.dotenv.Dotenv;

public class dbConnection {

    // Carga las variables de entorno desde .env
    private static Dotenv dotenv = Dotenv.load();

    static String url = dotenv.get("DB_URL").trim();
    static String user = dotenv.get("DB_USER").trim();
    static String passwordCifrada = dotenv.get("DB_PASSWORD_CIFRADA").trim();

    // Convertimos la clave Base64 del .env a SecretKey
    static SecretKey key = new SecretKeySpec(
            Base64.getDecoder().decode(dotenv.get("AES_KEY").trim()), "AES");

    public static Connection conectar() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Descifra la contraseña usando AES256
            String descifrado = AES256.decrypt(passwordCifrada, key);

            // Conexión a MySQL
            con = DriverManager.getConnection(url, user, descifrado);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}