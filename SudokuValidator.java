// https://www.codewars.com/kata/529bf0e9bdf7657179000008/train/java
package mt.codewars;

import java.util.Arrays;

public class SudokuValidator {
	private static final int N = 9; // 9 x 9 
	private static final int SUM = 45;
	private static final int BLOCK_N = 3;
	public static boolean check(int[][] sudoku) {
		boolean res = true;
		int[] block = new int[N]; // 3 x 3 => 1 x 9
		int[] row = new int[N];
		int[] col = new int[N];
		int shift_col = 0;
		int shift_row = 0;
		for(int i = 0, k = 0; i < N; i++){
		  for(int j = 0; j < N; j++){
			  row[j] = sudoku[i][j];
				col[j] = sudoku[j][i];
				block[k++] = sudoku[j/BLOCK_N + shift_row][j%BLOCK_N + shift_col];
			}
			if ((check(row) && check(col) && check(block)) == false) {
				res = false;
				break;
			}
			k = 0;
			shift_col += BLOCK_N;
			if (i%BLOCK_N == 2) {shift_row += 3; shift_col= 0;}
		}
		
		return res;
  }
    
  private static boolean check(int[] data) {
	  return (Arrays.stream(data).distinct().sum() == SUM);
	}
}