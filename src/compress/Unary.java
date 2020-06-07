package compress;

import code.Utils;

/*
 * �˴����� Unary ���뷽��Ϊ��
 * num�ı���Ϊ num��1���0��
 * �磺5��Unary����Ϊ 111110��
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
