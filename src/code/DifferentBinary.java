package code;

import code.Utils;
public class DifferentBinary {
	public static int Encode(int number,int m){
		int code =  0;
		int offset = Utils.removeMSB(m);
		if(offset == 0)
			code = number;
		else{
			
		}
		return code;
	}
}
