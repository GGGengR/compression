package code;

public class Utils {
	public static byte[] intToBinary(int number){      //int转二进制 存入byte数组
		byte[] b = new byte[4];
		b[0]= (byte) (number & 255);
		b[1]= (byte) ( number >> 8 & 255);
		b[2]= (byte) ( number >>16 & 255);
		b[3]= (byte) ( number >>24 & 255);
		return b;
	}
	public static int binaryToInt(byte[] b){      //二进制转int
		int number = 0;
		number = number << 8 | b[3];
		number = number << 8 | b[2];
		number = number << 8 | b[1];
		number = number << 8 | b[0];
		return number;
	}
	public static int log_down(int value) {     //以2 为底log取下限
		int base = 2;
		return (int) ( Math.log(value)/Math.log(base));
	}
	public static int log_up(int value) {     //以2 为底log取上限
		int base = 2;
		int log = (int) Math.ceil( ( Math.log(value)/Math.log(base)));
		return log;
	}
	public static int binary_length(int number){    //求int数字的二进制位数
		int length = 0;
		if(number == 0)
			length = 1;
		while(number != 0){
			number = number >> 1;
			length++;
		}
		return length;
	}
	public static int removeMSB(int number){      //去除最高位
		int length = binary_length(number);
		int code = number & (1<<length-1)-1;
		return code;
	}
	public static int addMSB(int number){      //添加最高位
		if(number == 0)
			return 2;
		int length = binary_length(number);
		int code = number & (1<<length-1)-1;
		return code;
	}
	public static int addMSB(int number,int num){      //添加最高位
		int code = number | (1<<num);
		return code;
	}
	public static boolean isPowerOf2(int number){
		boolean bool = (number&(number-1)) == 0;
		return bool;
	}
	public static void print(int[] number){
		for(int i = 0;i<number.length;i++)
			System.out.println(number[i]);
	}
}
