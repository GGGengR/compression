package code;

import java.util.Random;
public class Datatest {
	public static int[][] creat() {         //��������ɣ���ά����[5][65535],����
		int amount = 65535;					//[65535]
		int max = 100000;					//�������Χ���ֵ
	    int[][]randomNumbers = new int[5][amount];
	    double delta = max / (float)amount;
	    Random random = new Random();
	    for(int i = 0;i<5;i++)
	    	for (int j = 0; j < randomNumbers.length; j++) {
		        randomNumbers[i][j] = (int)Math.round(i*delta + random.nextDouble() * delta);
		    }
	    return randomNumbers;
	}
}
