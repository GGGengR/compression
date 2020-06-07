package compress;

import code.Utils;

/*
 * 此代码中 Unary 编码方法为：
 * num的编码为 num个1后跟0；
 * 如：5的Unary编码为 111110；
 */

public class Unary {
	public static int Encode(int number){
		int code=0;
		if(number > 30){
			System.out.print("It's too big!!! /BYE" + number);
			System.exit(0);
		}
		if(number ==0)
			return code;
		code = (1 << number) - 1; 
		code <<= 1 ;
		return code;
	}
	
	public static int Decode(int number){
		if(number ==0)
			return 0;
		int code=Utils.log_down(number >> 1)+1;
		return code;
	}
}
