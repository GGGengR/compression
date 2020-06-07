package compress;

import code.BitUtils;
import code.Utils;

public class Golomb {
	public static void Encode(int number,int m,byte[] output){
		int offset = 0;
		int length = 0;
		int q = number / m;
		int r = number % m;
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
			offset = (1 << Utils.log_up(m)) - m;
			if(r < offset){
				length = Utils.log_down(m);	
			}else{
				length = Utils.log_up(m);
				r += offset;
			}
		}
		BitUtils.bitWrite(output, r,length);
	}
	
//	public static int Encode(int number,int m){
//		int code = 0;
//		int offset = 0;
//		int length = 0;
//		int q = number / m;
//		int r = number % m;
////		code = Unary.Encode(q);
////		BitUtils.bitWrite(output, code);
//		if(Utils.isPowerOf2(m)){
//			length = Utils.log_down(m);
//		}else{
//			offset = (1 << Utils.log_up(m)) - m;
//			if(r < offset){
//				length = Utils.log_down(m);	
//			}else{
//				length = Utils.log_up(m);
//				r += offset;
//			}
//		}
////		BitUtils.bitWrite(output, r,length);
////		System.out.println(q);
////		System.out.println(length);
//		int l = q +1 +length;
//		return l;
//	}
	
	
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
		if(Utils.isPowerOf2(m))
			r = BitUtils.bitRead(output, Utils.binary_length(m)-1);
		else{
			offset = (1 << Utils.log_up(m)) - m;
			r = BitUtils.bitRead(output, Utils.log_down(m));
			if(r >= offset){
				r = r << 1 | BitUtils.bitRead(output, 1) ;
				r -= offset;
			}
		}
		number = m*q+r;
		return number;
	}
}
