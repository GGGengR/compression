package code;

public class BitUtils {
//	public static void bitWrite(byte[] code,int number ){
//		int index =0;
//		int length = Utils.binary_length(number);
//		if(number == 0 ){
//			length = 1;
//			if(pos < 8){
//				code[outpos] = (byte) (code[outpos] << 1);
//				pos += 1;
//				}
//			else{
//				outpos += 1;
//				code[outpos] = (byte) (code[outpos] << 1) ;
//				pos = 1;
//				}	
//			return ;
//		}
//		while(number != 0){
//			index = 8-pos;
//			if(length < index){
//				code[outpos] = (byte) (code[outpos] << length | number);
//				pos = pos + length;
//				number = 0;
//				}
//			else{
//				code[outpos] = (byte) (code[outpos] << index | number >> length-index) ;
//				outpos ++;
//				pos = 0;
//				number = number - ((number >> length-index) << length-index);
//				length = length - index ; 
//				}	
//			}
//	}
	public static void bitWrite(byte[] code,int number ){
		int index =0;
		int length = Utils.binary_length(number);
		while(length != 0){
			index = 8-pos;
			if(length < index){
				code[outpos] = (byte) (code[outpos] << length | number);
				pos = pos + length;
				length = 0;
				}
			else{
				code[outpos] = (byte) (code[outpos] << index | number >> length-index) ;
				outpos ++;
				pos = 0;
				number = number - ((number >> length-index) << length-index);
				length = length - index ; 
				}	
			}
	}
	
	public static void bitWrite(byte[] code,int number,int length ){
		int index =0;
		while(length != 0){
			index = 8-pos;
			if(length < index){
				code[outpos] = (byte) (code[outpos] << length | number);
				pos = pos + length;
				length = 0;
				}
			else{
				code[outpos] = (byte) (code[outpos] << index | number >> length-index) ;
				outpos ++;
				pos = 0;
				number = number - ((number >> length-index) << length-index);
				length = length - index ; 
				}	
			}
	}
	
	public static int bitRead(byte[] code,int length ){
		int index =0;
		int number = 0;
		int offset = 0;
		if(length == 0 ){
			return number;
		}
		if(pos + length > 8  & outpos == code.length-1){
			System.out.println("No more bits to be read!");
			System.exit(0);
		}
		while(length != 0){
			index = 8-pos;
			if(length <= index){
				offset = (1<<length)-1 ;
				number |= code[outpos] >> index-length & offset;
				pos = pos + length;
//				System.out.println("------------");
				return number;
				}
			else{
				offset = (1<<index)-1;
				number = code[outpos++] & offset;
				pos = 0;
				number = number << length-index;
				length = length - index ; 
//				System.out.println("2");
				}	
			}
		return number;
	}
	public static int pos;
	public static int outpos;
}
