package Security;
import java.util.*;

public class ColumnarCipher {

    public List<Integer> analyse(String plainText, String cipherText) {
        int cipherLen = cipherText.length();
        
        for (int keySize = 1; keySize <= cipherLen; keySize++) {
            if (cipherLen % keySize != 0) continue;

            int rows = cipherLen / keySize;
            
            StringBuilder paddedPt = new StringBuilder(plainText);
            while (paddedPt.length() < cipherLen) {
                paddedPt.append('x');
            }
            
            char[][] grid = new char[rows][keySize];
            int count = 0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < keySize; c++) {
                    grid[r][c] = paddedPt.charAt(count++);
                }
            }

            List<String> ptColumns = new ArrayList<>();
            for (int c = 0; c < keySize; c++) {
                StringBuilder col = new StringBuilder();
                for (int r = 0; r < rows; r++) {
                    col.append(grid[r][c]);
                }
                ptColumns.add(col.toString().toUpperCase());
            }

            List<String> ctChunks = new ArrayList<>();
            for (int i = 0; i < keySize; i++) {
                ctChunks.add(cipherText.substring(i * rows, (i + 1) * rows).toUpperCase());
            }

            Integer[] keyArray = new Integer[keySize];
            boolean[] used = new boolean[keySize];
            boolean possible = true;

            for (int i = 0; i < keySize; i++) {
                String chunk = ctChunks.get(i);
                int matchIdx = -1;
                for (int j = 0; j < ptColumns.size(); j++) {
                    if (!used[j] && ptColumns.get(j).equals(chunk)) {
                        matchIdx = j;
                        break;
                    }
                }

                if (matchIdx != -1) {
                    keyArray[matchIdx] = i + 1;
                    used[matchIdx] = true;
                } else {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                return Arrays.asList(keyArray);
            }
        }

        return new ArrayList<>();
    }

    public String decrypt(String cipherText, List<Integer> key) {
        int cipherSize = cipherText.length();
        int rows = (int) Math.ceil((double) cipherSize / key.size());
        char[][] grid = new char[rows][key.size()];
        int count = 0;

        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            keyMap.put(key.get(i) - 1, i);
        }

        int remainingCols = cipherSize % key.size();
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < rows; j++) {
                if (remainingCols != 0 && j == rows - 1 && keyMap.get(i) >= remainingCols) continue;
                grid[j][keyMap.get(i)] = cipherText.charAt(count++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.size(); j++) {
                result.append(grid[i][j]);
            }
        }
        return result.toString().toUpperCase().trim();
    }

    public String encrypt(String plainText, List<Integer> key) {
        int ptSize = plainText.length();
        int rows = (int) Math.ceil((double) ptSize / key.size());
        char[][] grid = new char[rows][key.size()];
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.size(); j++) {
                if (count >= ptSize) {
                    grid[i][j] = 'x';
                } else {
                    grid[i][j] = plainText.charAt(count++);
                }
            }
        }

        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            keyMap.put(key.get(i) - 1, i);
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < rows; j++) {
                cipherText.append(Character.toUpperCase(grid[j][keyMap.get(i)]));
            }
        }
        return cipherText.toString();
    }
}
