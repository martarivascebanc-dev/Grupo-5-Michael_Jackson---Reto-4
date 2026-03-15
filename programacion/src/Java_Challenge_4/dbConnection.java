package src.Java_Challenge_4;


import java.sql.Connection;
import java.sql.DriverManager;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Clase {@code dbConnection} que gestiona la conexión a la base de datos MySQL.
 * <p>
 * Utiliza variables de entorno cargadas del archivo .env
 * <ul>
 *     <li>DB_URL: URL de la base de datos</li>
 *     <li>DB_USER: usuario de la base de datos</li>
 *     <li>DB_PASSWORD_CIFRADA: Contraseña cifrada de la base de datos</li>
 *     <li>AES_KEY: Clase AES utilizada para descifrar la contraseña</li>
 * </ul>
 * </p>
 * <p>
 * La contraseña se descifra usando la clase {@link AES256} y luego se establece la conexión
 * a MySQL mediante {@link DriverManager}.
 * </p>
 * 
 * @author Ibai
 * @version 3.0
 * @since 2026-03-04
 */
public class dbConnection {

    /** Objeto Dotenv para cargar variables de entorno desde .env */
    private static Dotenv dotenv = Dotenv.load();

    /** URL de conexión a la base de datos obtenida del archivo .env */
    static String url = dotenv.get("DB_URL").trim();

    /** Usuario de la base de datos obtenido del archivo .env */
    static String user = dotenv.get("DB_USER").trim();

    /** Contraseña cifrada de la base de datos (Base64) obtenida del archivo .env */
    static String passwordCifrada = dotenv.get("DB_PASSWORD_CIFRADA").trim();

    /** Clave AES utilizada para descifrar la contraseña */
    static SecretKey key = new SecretKeySpec(
            Base64.getDecoder().decode(dotenv.get("AES_KEY").trim()), "AES");

    /**
     * Establece y devuelve una conexión a la base de datos MySQL.
     * <p>
     * Descifra la contraseña utilizando AES256 y la clave descifrada en {@link #key},
     * carga el driver MySQL y crea la conexión con {@link DriverManager#getConnection(String, String, String)}.
     * </p>
     * 
     * @return {@link Connection} objeto de conexión a la base de datos, o {@code null} si ocurre un error.
     */
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
