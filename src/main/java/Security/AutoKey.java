package Security;

public class AutoKey {
    public String analyse(String plainText, String cipherText) {
        plainText = plainText.toLowerCase();
        cipherText = cipherText.toLowerCase();
        StringBuilder fullKey = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char p = plainText.charAt(i);
            char c = cipherText.charAt(i);

            char k = (char) ((c - p + 26) % 26 + 'a');
            fullKey.append(k);
        }
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < fullKey.length(); i++) {
            key.append(fullKey.charAt(i));
            String generated = key.toString() +
                    plainText.substring(0, plainText.length() - key.length());
            if (generated.equals(fullKey.toString())) {
                break;
            }
        }
        return key.toString();
    }
//    public String decrypt(String cipherText, String key) {
//        // Students should complete this part
//        cipherText = cipherText.toLowerCase();
//        key = key.toLowerCase();
//
//        encrypt(key , cipherText);
//        return null;
//    }

    public String decrypt(String cipherText, String key) {
        cipherText = cipherText.toLowerCase();
        key = key.toLowerCase();

        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);

            char k;
            if (i < key.length()) {
                // use original key
                k = key.charAt(i);
            } else {
                // use previously decrypted plaintext
                k = plainText.charAt(i - key.length());
            }

            char p = (char) ((c - k + 26) % 26 + 'a');
            plainText.append(p);
        }

        return plainText.toString();
    }
    public String encrypt(String plainText, String key) {
        plainText = plainText.toLowerCase();
        key = key.toLowerCase();
        int len = plainText.length();

        // Extend key using the plaintext
        StringBuilder autoKey = new StringBuilder(key);
        if (autoKey.length() < len) {
            int diffLen = len - autoKey.length();
            for (int i = 0; i < diffLen; i++) {
                autoKey.append(plainText.charAt(i));
            }
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int p = plainText.charAt(i) - 'a';
            int k = autoKey.charAt(i) - 'a';
            cipherText.append((char) (((p + k) % 26) + 'a'));
        }
        return cipherText.toString();
    }
}
