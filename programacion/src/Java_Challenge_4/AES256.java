package src.Java_Challenge_4;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
/**
 * La clase {@code AES256} proporciona métodos para generar claves AES,
 * cifrar y descifrar la contraseña de tu MySQL utilizando AES (Advanced
 * Encryption Standard).
 *<p>
 * Incluye un ejemplo de uso en el método {@link #main(String[])} que genera
 * una clave, cifra el password y lo descifra.
 * </p>
 * <p>
 * Esta clase utiliza cifrado simétrico AES con un tamaño de clave de 128 bits
 * </p>
 * 
 * @author Ibai
 * @version 1.0
 * @since 2026-03-04
 */
public class AES256 {

    /** Genera una clave secreta AES de 128 bits.
     * 
     * @return {@link SecretKey} La clave AES generada.
     * @throws Exception si ocurre un error durante la generación de la clave.
     */
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 128 bits es suficiente para este ejemplo
        return keyGen.generateKey();
    }

    /** Cifra el password utilizando AES y la clave proporcionada
     * 
     * @param plaintext Texto plano que desea cifrar
     * @param key Clave {@link SecretKey} utilizada para el cifrado.
     * @return String Texto cifrado codificado en Base64.
     * @throws Exception si ocurre un error durante el cifrado.
     */
    public static String encrypt(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES"); // AES básico
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * Descifra un texto cifrado en Base64 utilizando AES y la clave proporcionada.
     * @param ciphertextBase64 Texto cifrado en Base64.
     * @param key Clave {@link SecretKey} utilizada para el descifrado.
     * @return String Texto descifrado en formato plano.
     * @throws Exception si ocurre un error durante el descifrado.
     */
    public static String decrypt(String ciphertextBase64, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(ciphertextBase64);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }
    /**
     * Método principal para generar la clave AES y el password cifrado.
     * @param args Argumentos de línea de comandos (no se utilizan).
     * @throws Exception si ocurre un error en la generación de clave, cifrado o descifrado.
     */
    public static void main(String[] args) throws Exception {
        // Generamos la clave
        SecretKey key = generateKey();
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("AES_KEY=" + base64Key); // -> aqui recibiras la AES_KEY

        // Texto a cifrar
        String password = "password"; // -> aqui debes colocar tu password del MySQL
        String cifrado = encrypt(password, key);
        System.out.println("DB_PASSWORD_CIFRADA=" + cifrado); // -> aqui recibiras el password cifrado

        // Texto a descifrado
        String descifrado = decrypt(cifrado, key);
        System.out.println("Descifrado=" + descifrado);
    }
}
