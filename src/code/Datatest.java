package code;

import dataSet.ClusteredDataGenerator;
public class Datatest {
//	public static int[][] creat() {         //随机数生成，二维数组[5][65535],递增
//		amount =65535;					//[65535]
//		max = 100000;					//随机数范围最大值
//	    int[][]randomNumbers = new int[5][amount];
//	    double delta = max / (float)amount;
//	    Random random = new Random();
//	    for(int i = 0;i<5;i++){
//	    	for (int j = 0; j < randomNumbers[i].length; j++) {
//		        randomNumbers[i][j] = (int)Math.round(j*delta + random.nextDouble() * delta); 
//		        if(j != 0)
//		        	if(randomNumbers[i][j] == randomNumbers[i][j-1] )
//		        		j--;
//		    }
//	    	while(randomNumbers[i][0]==0)
//	    		randomNumbers[i][0] = (int)Math.round(random.nextDouble() * delta); 
//	    }
//	    return randomNumbers;
//	}
	public static int[][] creat() {
		 int[][]randomNumbers = new int[5][amount];
		 for(int i = 0;i<5;i++){
			 ClusteredDataGenerator cdg=new ClusteredDataGenerator();
			 randomNumbers[i] = cdg.generateClustered(amount, max);
		 }
		 return randomNumbers;
	}
	public static int max = 1000000;
	public static int amount = 65535;
}
