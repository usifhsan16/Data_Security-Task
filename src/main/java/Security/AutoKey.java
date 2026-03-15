package Security;

public class AutoKey {
    public String analyse(String plainText, String cipherText) {
        // Students should complete this part
        String key = "";
        for (int i = 0; i < plainText.length(); i++) {

            char p = Character.toLowerCase(plainText.charAt(i));
            char c = Character.toLowerCase(cipherText.charAt(i));

            key += (char) ((c - p + 26) % 26 + 'a');
        }

        StringBuilder analysis = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char p = plainText.charAt(i);
            char c = cipherText.charAt(i);
            analysis.append(p).append(" -> ").append(c).append("\n");
        }
        return analysis.toString();
    }

    public String decrypt(String cipherText, String key) {
        // Students should complete this part
        cipherText = cipherText.toLowerCase();
        key = key.toLowerCase();

        encrypt(key , cipherText);
        return null;
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
