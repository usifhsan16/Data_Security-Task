package Security;

import java.util.*;

public class MonoalphabeticCipher {

    public String analyse(String plainText, String cipherText) {
        cipherText = cipherText.toLowerCase();
        plainText = plainText.toLowerCase();
        char[] key = new char[26];

        // Initialize with null characters
        Arrays.fill(key, '\0');

        // Map existing characters from plain to cipher
        for (int i = 0; i < plainText.length(); i++) {
            int m = plainText.charAt(i) - 'a';
            key[m] = cipherText.charAt(i);
        }

        // Fill remaining empty spots in the key with unused letters
        for (int i = 0; i < 26; i++) {
            if (key[i] == '\0') {
                for (int j = 0; j < 26; j++) {
                    char c = (char) (j + 'a');
                    boolean found = false;
                    for (int k = 0; k < 26; k++) {
                        if (key[k] == c) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        key[i] = c;
                        break;
                    }
                }
            }
        }
        return new String(key);
    }

    public String decrypt(String cipherText, String key) {
        cipherText = cipherText.toLowerCase();
        key = key.toLowerCase();
        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            char letter = cipherText.charAt(i);
            int j = key.indexOf(letter);

            if (j != -1) {
                char l = (char) (j + 'a');
                plainText.append(l);
            }
        }
        return plainText.toString();
    }

    public String encrypt(String plainText, String key) {
        plainText = plainText.toLowerCase();
        key = key.toLowerCase();
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            int index = plainText.charAt(i) - 'a';
            ciphertext.append(key.charAt(index));
        }
        return ciphertext.toString();
    }


    /// Frequency Information:
        /// E   12.51%
        /// T	9.25
        /// A	8.04
        /// O	7.60
        /// I	7.26
        /// N	7.09
        /// S	6.54
        /// R	6.12
        /// H	5.49
        /// L	4.14
        /// D	3.99
        /// C	3.06
        /// U	2.71
        /// M	2.53
        /// F	2.30
        /// P	2.00
        /// G	1.96
        /// W	1.92
        /// Y	1.73
        /// B	1.54
        /// V	0.99
        /// K	0.67
        /// X	0.19
        /// J	0.16
        /// Q	0.11
        /// Z	0.09
    public String analyseUsingCharFrequency(String cipher) {
        // Students should complete this part
        HashMap<Character, Integer> freq = new HashMap<>();

        char[] c = cipher.toLowerCase().toCharArray();
            for ( int i = 0; i < c.length;i++){
           
                freq.put(c[i], 0);

        }

        for ( int i = 0; i < c.length;i++){
            if (freq.containsKey(c[i])){

                 freq.put(c[i], freq.get(c[i]) + 1);

            }
            else{
                freq.put(c[i],  1);
            }

        }

            List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>(freq.entrySet());
    sortedEntries.sort((a, b) -> {
        int cmp = b.getValue() - a.getValue();
        if (cmp != 0) return cmp;
        return a.getKey() - b.getKey();
    });

        Set<Integer> numbers = new TreeSet<>();
char[] englishOrder = {
'e','t','a','o','i','n','s','r','h','l','d','c',
'u','m','f','p','g','w','y','b','v','k','x','j','q','z'
};


  LinkedHashMap<Character, Character> keyMap = new LinkedHashMap<>();
    for (int i = 0; i < sortedEntries.size() && i < englishOrder.length; i++) {
        keyMap.put(sortedEntries.get(i).getKey(),englishOrder[i]);
    }


    StringBuilder key = new StringBuilder();

for (int i=0 ;i<cipher.length();i++) {
    for (Map.Entry<Character, Character> entry : keyMap.entrySet()) {
      if (entry.getKey() == cipher.toLowerCase().charAt(i)) {
            key.append(entry.getValue());
            break;
        }
    }
}

return key.toString();
//  HashMap<Character, Integer> res = new HashMap<>();

//  for (int i=0;i<freq.size();i++){

//     freq.put(c[i], freq.get(c[i]) + 1

//  }


        




       
    }
}
