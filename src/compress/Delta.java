package compress;

import code.BitUtils;
import code.Utils;

public class Delta {
	public static void Encode(int number,byte[] output){
		int code = 0;
		int length = Utils.binary_length(number);
		code = Gamma.Encode(length);
		int offset = Utils.removeMSB(number);
		code = code << length-1 | offset;
		BitUtils.bitWrite(output, code);
	}
	
	public static int Decode(byte[] output){
		int offset = 1;
		int index = 0;
		int number = 0;
		while(offset != 0){
			offset = BitUtils.bitRead(output, 1) & 1;
			index++;
		}
		offset =BitUtils.bitRead(output, index-2);
		int p=(1<<index-2) | offset;
		offset =BitUtils.bitRead(output, p-1);
		number = Utils.addMSB(offset,p-1);
		return number;
	}
}
