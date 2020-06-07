package compress;

import code.BitUtils;
import code.Utils;

public class Gamma {
	public static void Encode(int number,byte[] output){
		int length = Utils.binary_length(number);
		for(int i =0;i<length;i++)
			BitUtils.bitWrite(output, 1);
		BitUtils.bitWrite(output, 0);
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
		number = Utils.addMSB(offset,index-2);
		return number;
	}
}
