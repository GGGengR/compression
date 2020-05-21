package compress;

import code.BitUtils;
import code.Utils;

public class Binary_Interpolative_Code {
	public static void Encode(int num,int lo,int hi,byte[] output) {
		int f = hi-lo;
		System.out.println(num);
		if(f == 0)
			return ; 
		int length = Utils.binary_length(f);
		BitUtils.bitWrite(output, num-lo,length);
	}
}
