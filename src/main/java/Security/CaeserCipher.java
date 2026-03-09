package Security;
import java.util.*;

public class CaeserCipher {
    public String encrypt(String plainText, int key) {
        plainText = plainText.toLowerCase();
        char[] charArr = plainText.toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            // Formula: E(x) = (x + k) mod 26
            charArr[i] = (char) ('a' + (charArr[i] - 'a' + key) % 26);
        }
        return new String(charArr);
    }

    public String decrypt(String cipherText, int key) {
        cipherText = cipherText.toLowerCase();
        char[] charArr = cipherText.toCharArray();

        for (int i = 0; i < charArr.length; i++) {
            // Formula: D(x) = (x - k + 26) mod 26
            charArr[i] = (char) ('a' + (charArr[i] - 'a' - key + 26) % 26);
        }
        return new String(charArr);
    }

    public int analyse(String plainText, String cipherText) {


        // TODO: Analyze the plainText and cipherText to determine the key(s)

        cipherText=cipherText.toLowerCase();
        plainText=plainText.toLowerCase();
          char[] charcipher = cipherText.toCharArray();
           char[] charPlain = plainText.toCharArray();

           int anyPlainChar =charPlain[0]-'a';

           int anycipherChar =charcipher[0]-'a';

           int formula =anycipherChar-anyPlainChar;

        
           return (formula+26)%26;


      
    }
}
