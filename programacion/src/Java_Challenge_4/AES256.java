package src.Java_Challenge_4;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AES256 {

    // Genera una clave AES de 128 bits
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 128 bits es suficiente para este ejemplo
        return keyGen.generateKey();
    }

    // Cifra un texto con la clave
    public static String encrypt(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES"); // AES básico
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Descifra un texto cifrado
    public static String decrypt(String ciphertextBase64, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(ciphertextBase64);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }

    public static void main(String[] args) throws Exception {
        // Generamos la clave
        SecretKey key = generateKey();
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("AES_KEY=" + base64Key); // -> aqui recibiras la AES_KEY

        // Texto a cifrar
        String password = "marta1"; // -> aqui debes colocar tu password del MySQL
        String cifrado = encrypt(password, key);
        System.out.println("DB_PASSWORD_CIFRADA=" + cifrado); // -> aqui recibiras el password cifrado

        // Prueba de descifrado
        String descifrado = decrypt(cifrado, key);
        System.out.println("Descifrado=" + descifrado);
    }
}