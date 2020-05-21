package code;

import java.util.Arrays;

import compress.Delta;
import compress.Gamma;
import compress.Golomb;
import compress.Rice;
import compress.Interpolative;

public class compression {
	public static void main(String[] args) {
		int[][] input = Datatest.creat();      //input[5][50]
		byte[][] output = new byte[input.length][input[0].length * 4];
		int[][] doc_id = new int[input.length][input[0].length];
//		int[][] doc_id = input;
		int[][] number = new int[input.length][input[0].length];
		for(int i = 0 ; i < input.length ; i++){
			int inpos = 0;
			int m = 4;
//			doc_id[i] = input[i];
//			Utils.print(doc_id[i]);
			for(int j = input[i].length-1;j > 0;j--)
				doc_id[i][j] = input[i][j] - input[i][j-1];          // doc_id
			doc_id[i][0]=input[i][0];
			//////////////////////////////////////////////////////////////////Gamma code
			Gamma(doc_id[i],output[i],number[i]);
			////////////////////////////////////////////////////////////////////Delta code
			Delta(doc_id[i],output[i],number[i]);
			///////////////////////////////////////////////////////////////////////Golomb code
			Golomb(doc_id[i],output[i],number[i]);
			///////////////////////////////////////////////////////////////////////Rice code
			Rice(doc_id[i],output[i],number[i]);
			////////////////////////////////////////////////////////////////////////Interpolative code
			Interpolative(input[i],output[i],number[i]);
		}
	}
	public static void Gamma(int[] input,byte[] output,int[] number){
		BitUtils.pos=0;
		BitUtils.outpos=0;
		int inpos = 0;
		for(inpos = 0; inpos < input.length;inpos++){
			Gamma.Encode(input[inpos],output);
		}
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		System.out.println("Gamma编码性能："+8*BitUtils.outpos+"  Bits");
		BitUtils.pos=0;
		BitUtils.outpos=0;
		BitUtils.inpos = 0;
		while(BitUtils.outpos < output.length){
			number[BitUtils.inpos++]=Gamma.Decode(output);
			if(BitUtils.bitRead(output, 1)==0)
				break;
			else
				if(BitUtils.pos == 0){
					BitUtils.outpos--;
					BitUtils.pos=8;
				}else
					BitUtils.pos--;		
		}
		System.out.println("Gamma解码完成,如有解码错误信息，将在下方显示。");
		System.out.println();
		for(int j = 0;j< number.length;j++){
			if(number[j] != input[j])
				System.out.println("WRONG"+j+" "+number[j] +" "+ input[j]);
		}
		System.out.println();
	}
	public static void Delta(int[] input,byte[] output,int[] number){
		BitUtils.pos=0;
		BitUtils.outpos=0;
		int inpos = 0;
		for(inpos = 0; inpos < input.length;inpos++){
			Delta.Encode(input[inpos],output);
		}
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		System.out.println("Delta编码性能："+8*BitUtils.outpos+"  Bits");
		BitUtils.pos=0;
		BitUtils.outpos=0;
		BitUtils.inpos = 0;
		while(BitUtils.outpos < output.length){
			number[BitUtils.inpos++]=Delta.Decode(output);
			if(BitUtils.bitRead(output, 1)==0)
				break;
			else
				if(BitUtils.pos == 0){
					BitUtils.outpos--;
					BitUtils.pos=8;
				}else
					BitUtils.pos--;		
		}
		System.out.println("Delta解码完成,如有解码错误信息，将在下方显示。");
		System.out.println();
		for(int j = 0;j< number.length;j++){
			if(number[j] != input[j])
				System.out.println("WRONG"+j+" "+number[j] +" "+ input[j]);
		}
		System.out.println();
	}
	public static void Golomb(int[] input,byte[] output,int[] number){
		BitUtils.pos=0;
		BitUtils.outpos=1;
		int inpos = 0;
		int m = 4;
		output[0]=(byte) m;
		for(inpos = 0; inpos < input.length;inpos++){
			Golomb.Encode(input[inpos],m,output);
		}
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		output[BitUtils.outpos] |= (1<<8-BitUtils.pos )- 1;
		System.out.println("Golomb编码性能："+8*BitUtils.outpos+"  Bits");
		byte[] code = Arrays.copyOf(output, BitUtils.outpos+1);
		int n = code[0];
		int num = 0;
		BitUtils.pos=0;
		BitUtils.outpos=1;
		BitUtils.inpos = 0;
		while(inpos < 65535){
			num=Golomb.Decode(code,n);
			number[BitUtils.inpos++] = num;
		}
		System.out.println("Golomb解码完成,如有解码错误信息，将在下方显示。");
		System.out.println();
		for(int j = 0;j< number.length;j++){
			if(number[j] != input[j])
				System.out.println("WRONG"+j+" "+number[j] +" "+ input[j]);
		}
		System.out.println();
	}
	public static void Rice(int[] input,byte[] output,int[] number){
		BitUtils.pos=0;
		BitUtils.outpos=1;
		int inpos = 0;
		int m = 4;
		output[0]=(byte) m;
		for(inpos = 0; inpos < input.length;inpos++){
			Rice.Encode(input[inpos],m,output);
		}
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		output[BitUtils.outpos] |= (1<<8-BitUtils.pos )- 1;
		System.out.println("Rice编码性能："+8*BitUtils.outpos+"  Bits");
		byte[] code = Arrays.copyOf(output, BitUtils.outpos+1);
		int n = code[0];
		int num = 0;
		BitUtils.pos=0;
		BitUtils.outpos=1;
		BitUtils.inpos = 0;
		while(inpos < 65535){
			num=Rice.Decode(code,n);
			number[BitUtils.inpos++] = num;
		}
		System.out.println("Rice解码完成,如有解码错误信息，将在下方显示。");
		System.out.println();
		for(int j = 0;j< number.length;j++){
			if(number[j] != input[j])
				System.out.println("WRONG"+j+" "+number[j] +" "+ input[j]);
		}
		System.out.println();
	}
	public static void Interpolative(int[] input,byte[] output,int[] number){
		int high = Datatest.max;
		int low = 1;
		int amount = Datatest.amount;
		output[0] = (byte) Utils.binary_length(high);
		BitUtils.pos=0;
		BitUtils.outpos=1;
		BitUtils.bitWrite(output, high);
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		BitUtils.pos=0;
		BitUtils.outpos=5;
		Interpolative.Encode(input,amount,low,high,output);
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		System.out.println("Interpolative编码性能："+8*BitUtils.outpos+"  Bits");
		int length = output[0];
		BitUtils.pos=0;
		BitUtils.outpos=1;
		int high_read = BitUtils.bitRead(output, length);
		BitUtils.pos=0;
		BitUtils.outpos=5;
		BitUtils.inpos = 0;
		while(true){
			Interpolative.Decode(Datatest.amount, 1, high_read, number, output);
			Arrays.sort(number);
			break;
		}
		System.out.println("Interpolative解码完成,如有解码错误信息，将在下方显示。");
		System.out.println();
		for(int j = 0;j< number.length;j++){
			if(number[j] != input[j])
				System.out.println("WRONG"+j+" "+number[j] +" "+ input[j]);
		}
		System.out.println();
	}
}
