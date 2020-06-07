package compress;

import code.BitUtils;
import code.Utils;

public class Rice {
	public static void Encode(int number,int m,byte[] output){
		int length = 0;
		int q = number / m;
		int r = number & (m - 1);
		for(int i=0;i<q;i++)
			BitUtils.bitWrite(output, 1);
		BitUtils.bitWrite(output, 0);
//		if(q<30){
//			int code = 0;
//			code = Unary.Encode(q);
//			BitUtils.bitWrite(output, code);
//		}else{
//			long code = 0;
//			code =Unary.Encode_long(number);
//			BitUtils.bitWrite(output, code);
//		}
		if(Utils.isPowerOf2(m)){
			length = Utils.log_down(m);
		}else{
			System.out.println("error : m is not the power of 2 !");
			System.exit(0);
		}
		BitUtils.bitWrite(output, r,length);
	}
	public static int Decode(byte[] output,int m){
		int offset = 1;
		int index = 0;
		int number = 0;
		int q= 0;
		int r = 0;
		while(offset != 0){
			offset = BitUtils.bitRead(output, 1) & 1;
			index++;
		}
		q = index-1;
		r = BitUtils.bitRead(output, Utils.binary_length(m)-1);
		number = m*q+r;
		return number;
	}
	
}
