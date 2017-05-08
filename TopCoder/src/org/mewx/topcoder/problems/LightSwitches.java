package org.mewx.topcoder.problems;

public class LightSwitches {
    public static void main(String[] args) {
        System.out.println(new LightSwitches().countPossibleConfigurations(new String[] {"YYN",
            "NNY",
            "YYY",
            "NNN"}));
        System.out.println(new LightSwitches().countPossibleConfigurations(new String[] {"NNNNYYYNYYNYYYYYNYY"}));
        System.out.println(new LightSwitches().countPossibleConfigurations(new String[] 	
            {"NYNYNYN",
            "YNYNYNY",
            "YYNNNYN",
            "NNNYNYN",
            "YYYYYNN",
            "YNNNNYN"}));
        System.out.println(new LightSwitches().countPossibleConfigurations(new String[] {"YNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNN",
            "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNN"}));
        System.out.println(new LightSwitches().countPossibleConfigurations(new String[] {"NNY", "NYN"}));
    }


    public long countPossibleConfigurations(String[] switches) {
        int rows = switches.length;
        int columns = switches[0].length();

        // translate into boolean array
        boolean[][] table = new boolean[rows][columns];
        for (int i = 0; i < rows; i ++)
            for (int j = 0; j < columns; j ++)
                table[i][j] = switches[i].charAt(j) == 'Y';
        // System.out.println("[1]");
        // outputMatrix(table);
        
        // Gaussian Elimiation
        int count = 0; // effective row counts, as the beginning index of non-eliminated rows, too
        for (int c = 0; c < columns; c ++) {
            // move the line with non-empty[j] to top for easy comparison
            int nonEmpty = count;
            for (; nonEmpty < rows; nonEmpty ++)
                if (table[nonEmpty][c]) break;
            if (nonEmpty == rows) continue; // current column has no `true`

            // move the first row containing `true` to row `count`, from column `j` to column `count`
            for (int j = 0; j < columns && count != nonEmpty; j ++) {
                // swap trick: swap table[count][j] and table[nonEmpty][j], without using temp
                table[count][j] ^= table[nonEmpty][j];
                table[nonEmpty][j] ^= table[count][j];
                table[count][j] ^= table[nonEmpty][j];
            }
            // System.out.println("[2]");
            // outputMatrix(table);

            // elimination from table[count + 1][]
            for (int i = count + 1; i < rows; i ++) {
                if (table[i][c])
                for (int j = 0; j < columns; j ++) {
                    table[i][j] ^= table[count][j]; // table[count][] is accumulated elimination result
                }
            }
            count += 1;
        }
    
        // long res = 1;
        // for (int c = 0; c < columns; c ++) {
        //     for (int r = 0; r < rows; r ++) {
        //         if (!used[r] && table[r][c]) {
        //             used[r] = true;
        //             for (int i = 0; i < rows; i ++) {
        //                 // each row
        //                 if (table[i][c] && i != r) {
        //                     for (int j = 0; j < columns; j ++) {
        //                         table[i][j] ^= table[r][j];
        //                     }
        //                 }
        //             }
        //             res <<= 1;
        //             break;
        //         }
        //     }
        // }
        return 1L << count;
    }

    private void outputMatrix(boolean[][] mat) {
        for (int i = 0; i < mat.length; i ++) { 
            for (int j = 0; j < mat[i].length; j ++)
                System.out.print(mat[i][j] ? 1 : 0);
            System.out.println();
        }
        System.out.println();
    }
}
