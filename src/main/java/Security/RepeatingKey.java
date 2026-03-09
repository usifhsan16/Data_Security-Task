package Security;

public class RepeatingKey {
    public String analyse(String plainText, String cipherText) {
        // Students should complete this part
        plainText=plainText.toLowerCase();
        cipherText=cipherText.toLowerCase();
        String key="";
        int val=0;
        for (int i = 0; i < plainText.length(); i++) {
            val=cipherText.charAt(i)-plainText.charAt(i);
            if(val<0){
                val+=26;
            }
            val%=26;
            key=key+(char)(val+'a');
        }
        String trueKey="";
        String substring;
        int counter;
        for(int i=1;i<key.length();i++){
            trueKey=key.substring(0,i);
            substring="";
            counter=0;
            while(substring.length()<key.length()){
                substring+=trueKey.charAt((counter%trueKey.length()));
                counter++;
            }
            if(substring.equals(key)){
                return trueKey;
            }
        }
        return key;
    }

    public String decrypt(String cipherText, String key) {
        // Students should complete this part
        String plain="";
        int val=0;
        int loop=0;
        key=key.toLowerCase();
        String realKey=key;
        cipherText=cipherText.toLowerCase();
        while (key.length()<cipherText.length()){
            key=key + realKey.charAt((loop%realKey.length()));
            loop++;
        }
        for (int i = 0; i < cipherText.length(); i++) {
            val=cipherText.charAt(i)-key.charAt(i);
            if (val<0){
                val+=26;
            }
            plain+=(char)(val+'a');
        }
        return plain;
    }

    public String encrypt(String plainText, String key) {
        plainText = plainText.toLowerCase();
        key = key.toLowerCase();
        int plainLen = plainText.length();

        // Repeat key to match plaintext length
        StringBuilder extendedKey = new StringBuilder(key);
        while (extendedKey.length() < plainLen) {
            extendedKey.append(extendedKey.charAt(extendedKey.length() - key.length()));
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < plainLen; i++) {
            int p = plainText.charAt(i) - 'a';
            int k = extendedKey.charAt(i) - 'a';
            cipherText.append((char) (((p + k) % 26) + 'a'));
        }

        return cipherText.toString();
    }
}
