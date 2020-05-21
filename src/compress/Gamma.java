package compress;

import code.BitUtils;
import code.Utils;

public class Gamma {
	public static void Encode(int number,byte[] output){
		int code = 0;
		int length = Utils.binary_length(number);
		code = Unary.Encode(length);
		int offset = Utils.removeMSB(number);
		code = code << length-1 | offset;
		BitUtils.bitWrite(output, code);
	}
	public static int Encode(int number){
		int code = 0;
		int length = Utils.binary_length(number);
		code = Unary.Encode(length);
		int offset = Utils.removeMSB(number);
		code = code << length-1 | offset;
		return code;
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
	public static int Decode(int num){
		int offset = 1;
		int num_binary_length = Utils.binary_length(num);
		int index = num_binary_length;
		int number = 0;
		while(offset != 0){
			offset = num>> --index & 1;
		}
		index = num_binary_length-index-2;
		offset = num & (1<<index)-1;
		number = Utils.addMSB(offset,index);
		return number;
	}
}
