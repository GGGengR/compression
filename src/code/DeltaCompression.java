package code;

import compress.Delta;

public class DeltaCompression {
	public static void main(String[] args) {
		int[][] input = Datatest.creat();      //input[5][50]
		byte[][] output = new byte[input.length][input[0].length * 4];
//		int[][] doc_id = new int[input.length][input[0].length];
		int[][] number = new int[input.length][input[0].length];
		for(int i = 0 ; i < input.length ; i++){
			int inpos = 0;
			BitUtils.pos=0;
			BitUtils.outpos=0;
			for(int j = input[i].length-1;j > 0;j--)
				input[i][j] = input[i][j] - input[i][j-1];          // doc_id
			for(inpos = 0; inpos < input[0].length;inpos++){
				Delta.Encode(input[i][inpos],output[i]);
			}
			output[i][BitUtils.outpos] <<= 8-BitUtils.pos;
			System.out.println("Delta编码性能："+8*BitUtils.outpos+"  Bits");
			BitUtils.pos=0;
			BitUtils.outpos=0;
			BitUtils.inpos = 0;
			while(BitUtils.outpos < output[i].length){
				number[i][BitUtils.inpos++]=Delta.Decode(output[i]);
				if(BitUtils.bitRead(output[i], 1)==0)
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
			for(int j = 0;j< number[i].length;j++){
				if(number[i][j] != input[i][j])
					System.out.println("WRONG"+j+" "+number[i][j] +" "+ input[i][j]);
			}
		}
	}
}
