package compress;

import code.BitUtils;
import code.Utils;

public class Delta {
	public static void Encode(int number,byte[] output){
		int length = Utils.binary_length(number);
		Gamma.Encode(length,output);
		int offset = Utils.removeMSB(number);
		BitUtils.bitWrite(output, offset,length-1);
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
