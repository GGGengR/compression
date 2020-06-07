package code;

import java.util.Arrays;
import code.Utils;
import compress.Unary;
import compress.Delta;
import compress.Gamma;
import compress.Golomb;
import compress.Interpolative;
import compress.Rice;
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int n=5;
//		int outpos = 0;
//		int pos = 0;
//		byte[] b = new byte [4];
//		for(int i = 0;i<4;i++){
//			b[outpos++]= (byte) ((n>>i*8)&255);
//		}
//		b[0] = (byte) (n  &255);
//		b[1] = (byte) ((n >> 8 )&255);
//		byte[] b = Utils.intToBinary(n);
//		for(int i = 0;i<4;i++){
//			System.out.println(Integer.toBinaryString(b[i]));
//		}
//		int number = Utils.binaryToInt(b);
//		System.out.println(number);
//		int n = 4;
//		int r = 0;
//		while(n != 0){
//			n = n>>1;
//			r++;
//		}
//		int r = Golomb.Encode(4, 5);
//		System.out.print(Integer.toBinaryString(r));
		
		
//		BitUtils.pos=0;
//		BitUtils.outpos=0; 
//		int m =5;
//		int[] num = new int[]{3,8,9,11,12,13,17};
//		byte[] output = new byte[28];
////		for(int i = 0;i<num.length;i++){
////			System.out.print(Integer.toBinaryString(num[i]));
////			Golomb.Encode(num[i], m, output);
////		}
//		Interpolative.Encode(num, 7, 1, 20, output);
//		System.out.println();
//		for(int i = 0;i<=BitUtils.outpos;i++){
//			System.out.print(output[i]>>7 & 1);
//			System.out.print(output[i]>>6 & 1);
//			System.out.print(output[i]>>5 & 1);
//			System.out.print(output[i]>>4 & 1);
//			System.out.print(output[i]>>3 & 1);
//			System.out.print(output[i]>>2 & 1);
//			System.out.print(output[i]>>1 & 1);
//			System.out.print(output[i] & 1);
//			System.out.println();
//		}
//		BitUtils.pos=0;
//		BitUtils.outpos=0; 
//		System.out.println(BitUtils.bitRead(output,9));
//		int [] num = new int[31];
//		for(int i = 0;i<31;i++)
//			{
//			num[i]=Unary.Encode(i);
//			if(Unary.Decode(num[i]) != i)
//				System.out.print("ERROR" + i);
//			}
//		int[] num = new int[3];
//		num[0]=1;
//		System.out.print(num[2]);
//		int num = 1 <<9 ;
//		num += 3;
//		byte[] code = new byte[4];
//		BitUtils.bitWrite(code, num);
//		System.out.println(Integer.toBinaryString(code[0]));
//		System.out.println(Integer.toBinaryString(code[1]));
//		System.out.print(Integer.toBinaryString(num));
		
//		BitUtils.pos=0;
//		BitUtils.outpos=0;
//		int[] num = new int[]{0,1,4,7,8,9,6};
//		byte[] output = new byte[28];
//		for(int i = 0;i<num.length;i++){
//			Gamma.Encode(num[i], output);
//		}
//		output[BitUtils.outpos] <<= 8-BitUtils.pos;
//		for(int i = 0;i<=BitUtils.outpos;i++){
//			System.out.print(output[i]>>7 & 1);
//			System.out.print(output[i]>>6 & 1);
//			System.out.print(output[i]>>5 & 1);
//			System.out.print(output[i]>>4 & 1);
//			System.out.print(output[i]>>3 & 1);
//			System.out.print(output[i]>>2 & 1);
//			System.out.print(output[i]>>1 & 1);
//			System.out.print(output[i] & 1);
//			System.out.println();
//		}
//		byte[] code = Arrays.copyOf(output, BitUtils.outpos+1);
//		BitUtils.pos=0;
//		BitUtils.outpos=0;
//		while(BitUtils.outpos<5){
//			System.out.println(Gamma.Decode(code));
//		}
//		System.out.println(Gamma.Decode(57));
//		System.out.println(Integer.toBinaryString(57));
		
//		int[] num = new int[]{2,5,4,7,8,9,6};
//		byte[] output = new byte[28];
//		for(int i = 0;i<num.length;i++){
//			Delta.Encode(num[i], output);
//		}
//		output[BitUtils.outpos] <<= 8-BitUtils.pos;
//		for(int i = 0;i<=BitUtils.outpos;i++){
//			System.out.print(output[i]>>7 & 1);
//			System.out.print(output[i]>>6 & 1);
//			System.out.print(output[i]>>5 & 1);
//			System.out.print(output[i]>>4 & 1);
//			System.out.print(output[i]>>3 & 1);
//			System.out.print(output[i]>>2 & 1);
//			System.out.print(output[i]>>1 & 1);
//			System.out.print(output[i] & 1);
//			System.out.println();
//		}
//		byte[] code = Arrays.copyOf(output, BitUtils.outpos+1);
//		BitUtils.pos=0;
//		BitUtils.outpos=0;
//		int i = 1;
//		while(BitUtils.outpos<6){
//			System.out.println(Delta.Decode(code));
////			System.out.println(i++);
//			if(BitUtils.bitRead(code, 1)==0)
//				break;
//			else
//				if(BitUtils.pos == 0){
//					BitUtils.outpos--;
//					BitUtils.pos=8;
//				}else
//					BitUtils.pos--;
//					
//		}
		
//		int[] num = new int[]{2,5,4,7,8,9,6};
//		byte[] output = new byte[28];
//		int m = 5;
//		BitUtils.pos=0;
//		BitUtils.outpos=1;
//		output[0]=(byte) m;
//		for(int i = 0;i<num.length;i++){
//			Golomb.Encode(num[i],m, output);
//		}
//		output[BitUtils.outpos] <<= 8-BitUtils.pos;
//		output[BitUtils.outpos] |= (1<<8-BitUtils.pos )- 1;
//		for(int i = 0;i<=BitUtils.outpos;i++){
//			System.out.print(output[i]>>7 & 1);
//			System.out.print(output[i]>>6 & 1);
//			System.out.print(output[i]>>5 & 1);
//			System.out.print(output[i]>>4 & 1);
//			System.out.print(output[i]>>3 & 1);
//			System.out.print(output[i]>>2 & 1);
//			System.out.print(output[i]>>1 & 1);
//			System.out.print(output[i] & 1);
//			System.out.println();
//		}
//		byte[] code = Arrays.copyOf(output, BitUtils.outpos+1);
//		int n = code[0];
//		BitUtils.pos=0;
//		BitUtils.outpos=1;
////		int i = 1;
//		while(BitUtils.outpos<6){
//			int numb = Golomb.Decode(code,n);
//			System.out.println(numb);
//	}
		
//		int[] num = new int[]{2,5,4,7,8,9,6};
//		byte[] output = new byte[28];
//		int m = 4;
//		BitUtils.pos=0;
//		BitUtils.outpos=1;
//		output[0]=(byte) m;
//		for(int i = 0;i<num.length;i++){
//			Rice.Encode(num[i],m, output);
//		}
//		output[BitUtils.outpos] <<= 8-BitUtils.pos;
//		output[BitUtils.outpos] |= (1<<8-BitUtils.pos )- 1;
//		for(int i = 0;i<=BitUtils.outpos;i++){
//			System.out.print(output[i]>>7 & 1);
//			System.out.print(output[i]>>6 & 1);
//			System.out.print(output[i]>>5 & 1);
//			System.out.print(output[i]>>4 & 1);
//			System.out.print(output[i]>>3 & 1);
//			System.out.print(output[i]>>2 & 1);
//			System.out.print(output[i]>>1 & 1);
//			System.out.print(output[i] & 1);
//			System.out.println();
//		}
//		byte[] code = Arrays.copyOf(output, BitUtils.outpos+1);
//		int n = code[0];
//		BitUtils.pos=0;
//		BitUtils.outpos=1;
////		int i = 1;
//		while(BitUtils.outpos<6){
//			int numb = Rice.Decode(code,n);
//			System.out.println(numb);
//		}
		
//		int[] num = new int[]{2,4,7,12,13,14,17};
//		byte[] output = new byte[28];
//		int m = 4;
//		BitUtils.pos=0;
//		BitUtils.outpos=1;
//		output[0]=(byte) m;
//		Interpolative.Encode(num, 7, 1, 20, output);
//		output[BitUtils.outpos] <<= 8-BitUtils.pos;
////		output[BitUtils.outpos] |= (1<<8-BitUtils.pos )- 1;
//		for(int i = 0;i<=BitUtils.outpos;i++){
//			System.out.print(output[i]>>7 & 1);
//			System.out.print(output[i]>>6 & 1);
//			System.out.print(output[i]>>5 & 1);
//			System.out.print(output[i]>>4 & 1);
//			System.out.print(output[i]>>3 & 1);
//			System.out.print(output[i]>>2 & 1);
//			System.out.print(output[i]>>1 & 1);
//			System.out.print(output[i] & 1);
//			System.out.println();
//		}
//		byte[] code = Arrays.copyOf(output, BitUtils.outpos+1);
//		int n = code[0];
//		BitUtils.pos=0;
//		BitUtils.outpos=1;
//		int[] numarray = new int[7];
//		while(BitUtils.outpos<5){
//			BitUtils.inpos = 0;
//			Interpolative.Decode(7, 1, 20, numarray, code);
//			Arrays.sort(numarray);
//			Utils.print(numarray);
//			break;
//		}
//		int k =1;
//		int[][] input = Datatest.creat();      //input[5][50]
//		for(int i = 0;i<5;i++)
//			for(int j = 0;j<input[i].length;j++)
//				if(input[i][j] - input[i][j++] ==1)
//					System.out.print(k++);
//		byte[] code = new byte[24];
//		BitUtils.bitWrite(code, 1024);
//		int[] number = new int[1024];
//		int[][] input = Datatest.creat();      //input[5][50]
//		int[][] d_gap = new int[input.length][input[0].length];
//		
//		long length = 0;
//		for(int m =2;m<100;m++){
//			length = 0;
//			for(int i = 0;i<input.length;i++){
//				for(int j = input[i].length-1;j > 0;j--)
//					d_gap[i][j] = input[i][j] - input[i][j-1];// d-gap
//				d_gap[i][0]=input[i][0];
//				for(int j = 0;j<d_gap[i].length;j++)
//					length += Golomb.Encode(d_gap[i][j], m);
//			
//		
//			}
//			System.out.println(length+" "+m);
//		}
//		for(int i =1;i<20;i++){
//			System.out.print(i+" "+Utils.log_up(i));
//			System.out.println();
//		}
		BitUtils.inpos=0;
		BitUtils.outpos=0;
		BitUtils.pos=0;
				
		byte[] output = new byte[20];
		Golomb.Encode(1,2, output);
		System.out.println(BitUtils.pos);
		for(int i = 0;i<=BitUtils.outpos;i++){
			System.out.print(output[i]>>7 & 1);
			System.out.print(output[i]>>6 & 1);
			System.out.print(output[i]>>5 & 1);
			System.out.print(output[i]>>4 & 1);
			System.out.print(output[i]>>3 & 1);
			System.out.print(output[i]>>2 & 1);
			System.out.print(output[i]>>1 & 1);
			System.out.print(output[i] & 1);
			System.out.println();
		}
	}
}
