package compress;

import code.BitUtils;
import code.Utils;

public class Binary_Interpolative_Code {
	public static void Encode(int num,int lo,int hi,byte[] output) {
		int f = hi-lo;
		System.out.println(num);
		if(f == 0)
			return ; 
//		String f_binary = Integer.toBinaryString(f);
//		int f_length = Utils.binary_length(f);
////		String binary = Integer.toBinaryString(num-lo);
//		int binary_length = Utils.binary_length(num-lo);
////		while(binary_length < f_length)
//		for(int i = 0 ;i< f_length-binary_length;i++)
//			BitUtils.bitWrite(output, 0);
//		BitUtils.bitWrite(output, num-lo);
		
		int length = Utils.binary_length(f);
		BitUtils.bitWrite(output, num-lo,length);
		
//		System.out.print(num);
//		System.out.print(lo);
//		System.out.print(hi);
//		System.out.println(binary);
	}
	public static String Decode(String s ,int lo,int hi,int num[]) {
		if (lo == hi) {
		
		}
		return null;
		
	}
}
