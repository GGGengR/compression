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
//		System.out.println(Integer.toBinaryString(code));
		BitUtils.bitWrite(output, code);
//		System.out.println(BitUtils.pos);
	}
	
	public static int Decode(byte[] output){
		int offset = 1;
		int index = 0;
		int number = 0;
		while(offset != 0){
			offset = BitUtils.bitRead(output, 1) & 1;
			index++;
		}
//		System.out.println(index);
		offset =BitUtils.bitRead(output, index-2);
		int p=(1<<index-2) | offset;
//		System.out.println(p);
		offset =BitUtils.bitRead(output, p-1);
//		System.out.println(Integer.toBinaryString(offset));
		number = Utils.addMSB(offset,p-1);
		return number;
	}
//	public static int Decode(byte[] output) {
//		int number = 0;
//		int offset = 0;
//		int index = 0;
//		BitUtils.pos=0;
//		BitUtils.outpos=0;
//		while(BitUtils.outpos < output.length){
//			index = 8 - BitUtils.pos;
//			while(index != 0 ){
//				offset = output[BitUtils.outpos] >> index & 1 ;
//				BitUtils.pos++;
//				index = 8 - BitUtils.pos;
//			}
//			BitUtils.outpos++;
//		}
//		if (Code != "") {                                         
//			int index = Code.indexOf("0"); // 用indexOf取得第一次出现0的下标
//			String str2 =  Code.substring(index, index*2+1);
//			int m = Integer.parseInt(str2,2);
//			int p=(1<<index)+m-1;
//			String str3 =  Code.substring(Code.length()-p);
//			String binary = "1" +str3;
//			number = Integer.parseInt(binary, 2);
//		}
//		return number; // 如果Code为空，返回0；
}
