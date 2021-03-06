package compress;

import java.util.Arrays;

import code.BitUtils;
import code.Utils;

public class Interpolative {
	public static void Encode(int num[],int f,int lo,int hi,byte[] output){
		if(num.length == 1){
			Binary_Interpolative_Code.Encode(num[0], lo, hi,output);
			return ;
		}
		if(num.length == 0)
			return;
		int h = (f+1)/2;
		int f1 = h-1;
		int f2 = f-h;
		int[] num1 = Arrays.copyOfRange(num, 0, h-1);
		int[] num2 = Arrays.copyOfRange(num, h, f);
		Binary_Interpolative_Code.Encode(num[h-1],lo+f1,hi-f2,output) ;
		Interpolative.Encode(num1, f1, lo, num[h-1]-1,output) ;
		Interpolative.Encode(num2, f2, num[h-1]+1,hi,output);
	}
	
	public static void Decode(int f,int lo,int hi,int[] numarray,byte[] code) {
		if(f == 0 )
			return ;
		int h = (f+1)/2;
		int f1 = h-1;   //前面有几个
		int f2 = f-h;    //后面有几个
		int hi1 = hi-f2-lo-f1;           //中间的范围
		int length = Utils.binary_length(hi1);
		if(hi1 ==0)
			length = 0;
		int number = BitUtils.bitRead(code, length);
		number += lo+f1;
		numarray[BitUtils.inpos++] = number;
		Interpolative.Decode(f1, lo, number-1,numarray,code);
		Interpolative.Decode(f2, number+1,hi,numarray,code);
	}
}
