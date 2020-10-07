package library.Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class HashFunction {

    static String algorithm = "SHA-256";
    public static HashMap<String, String> hashMap = new HashMap<>();

    public HashFunction () throws NoSuchAlgorithmException {
        hashMap.put("sol",generateHash("sol", "SHA-256"));
    }

    public static String generateHash(String text, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        byte[] hash = digest.digest(text.getBytes());
        return bytesToStringHex(hash);
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String bytesToStringHex(byte[] bytes) {
        char [] hexChars = new char[bytes.length *2];
        for(int j = 0 ; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2]= hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static boolean checkPassword(String username, String password) throws NoSuchAlgorithmException {
        if (hashMap.containsKey(username)) {
            if(hashMap.get(username).equals(generateHash(password, "SHA-256"))){
                System.out.println("Correct!");
                return true;
            } else {
                System.out.println("Wrong Password!");
            }
        } else {
            System.out.println("Wrong Username!");
        }
        return false;
    }

    public static void main(String args[]) throws NoSuchAlgorithmException {

//        hashMap.put("Brook", generateHash("password", "SHA-256"));
//        hashMap.put("Brook K", generateHash("break", "SHA-256"));

//        System.out.println(checkPassword("Brook", "password"));
    }
}
