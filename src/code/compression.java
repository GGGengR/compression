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
		int[][] d_gap = new int[input.length][input[0].length];
		int[][] number = new int[input.length][input[0].length];
		for(int i = 0 ; i < input.length ; i++){
			for(int j = input[i].length-1;j > 0;j--)
				d_gap[i][j] = input[i][j] - input[i][j-1];// d-gap
			d_gap[i][0]=input[i][0];
			////////////////////////////////////////////////////////////////Gamma code
			Gamma(d_gap[i],output[i],number[i]);
			////////////////////////////////////////////////////////////////////Delta code
			Delta(d_gap[i],output[i],number[i]);
			///////////////////////////////////////////////////////////////////////Golomb code
			Golomb(d_gap[i],output[i],number[i]);
			///////////////////////////////////////////////////////////////////////Rice code
			Rice(d_gap[i],output[i],number[i]);
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
		//编码
		int inpos = 0;
		int m = 5;
		output[0]=(byte) m;
		output[1]=(byte) Utils.binary_length(input.length);
		BitUtils.pos=0;
		BitUtils.outpos=2;
		BitUtils.bitWrite(output, input.length);
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		BitUtils.pos=0;
		BitUtils.outpos+=1;
		for(inpos = 0; inpos < input.length;inpos++){
			Golomb.Encode(input[inpos],m,output);
		}
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		System.out.println("Golomb编码性能："+8*BitUtils.outpos+"  Bits");
		//解码
		int n = output[0];
		int amount_length = output[1];
		BitUtils.pos=0;
		BitUtils.outpos=2;
		int amount = BitUtils.bitRead(output, amount_length); 
		int num = 0;
		BitUtils.pos=0;
		BitUtils.outpos+=1;
		BitUtils.inpos = 0;
		while(inpos < amount){
			num=Golomb.Decode(output,n);
			number[BitUtils.inpos++] = num;
		}
		//判断解码正确
		System.out.println("Golomb解码完成,如有解码错误信息，将在下方显示。");
		System.out.println();
		for(int j = 0;j< number.length;j++){
			if(number[j] != input[j])
				System.out.println("WRONG"+j+" "+number[j] +" "+ input[j]);
		}
		System.out.println();
	}
	public static void Rice(int[] input,byte[] output,int[] number){
		//编码
		int inpos = 0;
		int m = 4;
		output[0]=(byte) m;
		output[1]=(byte) Utils.binary_length(input.length);
		BitUtils.pos=0;
		BitUtils.outpos=2;
		BitUtils.bitWrite(output, input.length);
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		BitUtils.pos=0;
		BitUtils.outpos+=1;
		for(inpos = 0; inpos < input.length;inpos++){
			Rice.Encode(input[inpos],m,output);
		}
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		System.out.println("Rice编码性能："+8*BitUtils.outpos+"  Bits");
		//解码
		int n = output[0];
		int amount_length = output[1];
		BitUtils.pos=0;
		BitUtils.outpos=2;
		int amount = BitUtils.bitRead(output, amount_length); 
		int num = 0;
		BitUtils.pos=0;
		BitUtils.outpos+=1;
		BitUtils.inpos = 0;
		while(inpos < amount){
			num=Rice.Decode(output,n);
			number[BitUtils.inpos++] = num;
		}
		//判断解码正确
		System.out.println("Rice解码完成,如有解码错误信息，将在下方显示。");
		System.out.println();
		for(int j = 0;j< number.length;j++){
			if(number[j] != input[j])
				System.out.println("WRONG"+j+" "+number[j] +" "+ input[j]);
		}
		System.out.println();
	}
	public static void Interpolative(int[] input,byte[] output,int[] number){
		//编码
		int high = Datatest.max;
		int low = 1;
		int amount = Datatest.amount;
		output[0] = (byte) Utils.binary_length(amount);
		BitUtils.pos=0;
		BitUtils.outpos=1;
		BitUtils.bitWrite(output, amount);
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		BitUtils.pos=0;
		BitUtils.outpos+=1;
		output[BitUtils.outpos] = (byte) Utils.binary_length(high);
		BitUtils.outpos+=1;
		BitUtils.bitWrite(output, high);
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		BitUtils.pos=0;
		BitUtils.outpos+=1;
		Interpolative.Encode(input,amount,low,high,output);
		output[BitUtils.outpos] <<= 8-BitUtils.pos;
		System.out.println("Interpolative编码性能："+8*BitUtils.outpos+"  Bits");
		//解码
		int amount_length = output[0];
		BitUtils.pos=0;
		BitUtils.outpos=1;
		int amount_read = BitUtils.bitRead(output, amount_length);
		BitUtils.outpos+=1;
		int high_length = output[BitUtils.outpos];
		BitUtils.pos=0;
		BitUtils.outpos+=1;
		int high_read =  BitUtils.bitRead(output, high_length);
		BitUtils.pos=0;
		BitUtils.outpos+=1;
		BitUtils.inpos = 0;
		while(true){
			Interpolative.Decode(amount_read, 1, high_read, number, output);
			Arrays.sort(number);
			break;
		}
		//判断解码结果
		System.out.println("Interpolative解码完成,如有解码错误信息，将在下方显示。");
		System.out.println();
		for(int j = 0;j< number.length;j++){
			if(number[j] != input[j])
				System.out.println("WRONG"+j+" "+number[j] +" "+ input[j]);
		}
		System.out.println();
	}
}