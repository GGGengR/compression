package code;

import code.BitUtils;
import compress.Gamma;

public class GammaCompression {
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
				Gamma.Encode(input[i][inpos],output[i]);
			}
			output[i][BitUtils.outpos] <<= 8-BitUtils.pos;
			System.out.println("Gamma编码性能："+8*BitUtils.outpos+"  Bits");
			BitUtils.pos=0;
			BitUtils.outpos=0;
			BitUtils.inpos = 0;
			while(BitUtils.outpos < output[i].length){
				number[i][BitUtils.inpos++]=Gamma.Decode(output[i]);
				if(BitUtils.bitRead(output[i], 1)==0)
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
			for(int j = 0;j< number[i].length;j++){
				if(number[i][j] != input[i][j])
					System.out.println("WRONG"+j+" "+number[i][j] +" "+ input[i][j]);
			}
		}
	}
//	public static void main(String[] args) {
//		int[][] input = Datatest.creat();      //input[5][65535]
//		InputStream is=System.in;
//		int m = 4;    //Golomb Rice m;
//		int index = 0;
//		int compression_id = 0 ;
//		int[] num;
//		String fileName = "";
//		String file_con ="";
//		String code = "";
//		try {
//			BufferedReader bre=new BufferedReader(new InputStreamReader(is));
//		while (true) {
//			String str= bre.readLine();
//			if(str.equalsIgnoreCase("exit")||str.isEmpty())
//				break;
//			index = str.indexOf(" ");
//			compression_id = Integer.parseInt(str.substring(0,index));
//			fileName = str.substring(index+1);
//			file_con = FileUtil.read(fileName);
//			switch(compression_id) {
//			case 1:
//				num = Util.StringtoNumarray(file_con);
//				for(int i =0;i<num.length;i++) 
//					code += Gamma.Encode(num[i]);
//				FileUtil.write(code, fileName.substring(0,fileName.indexOf("."))+"_GammaEncode"+fileName.substring(fileName.indexOf(".")));
//				break;
//			case 2:
//				num = Util.StringtoNumarray(file_con);
//				for(int i =0;i<num.length;i++) 
//					code += Delta.Encode(num[i]);
//				FileUtil.write(code, fileName.substring(0,fileName.indexOf("."))+"_DeltaEncode"+fileName.substring(fileName.indexOf(".")));
//				break;
//			case 3:
//				num = Util.StringtoNumarray(file_con);
//				for(int i =0;i<num.length;i++) 
//					code += Golomb.Encode(num[i],m);
//				FileUtil.write(code, fileName.substring(0,fileName.indexOf("."))+"_GolombEncode"+fileName.substring(fileName.indexOf(".")));
//				break;
//			case 4:
//				num = Util.StringtoNumarray(file_con);
//				for(int i =0;i<num.length;i++) 
//					code += Rice.Encode(num[i],m);
//				FileUtil.write(code, fileName.substring(0,fileName.indexOf("."))+"_RiceEncode"+fileName.substring(fileName.indexOf(".")));
//				break;
//			case 5:
//				num = Util.StringtoNumarray(file_con);
//				System.out.println("请输入最小值：");
//				int lo = Integer.parseInt(bre.readLine());
//				System.out.println("请输入最大值：");
//				int hi = Integer.parseInt(bre.readLine());
//				Interpolative.s = Interpolative.Encode(num, num.length, lo, hi);
//				FileUtil.write(Unary.Encode(num.length)+Interpolative.s, fileName.substring(0,fileName.indexOf("."))+"_InterpolativeEncode"+fileName.substring(fileName.indexOf(".")));
//				break;
//			case 11:
//				code = Gamma.Decode(file_con);
//				num = Util.StringtoNumarray(code);
//				FileUtil.write(num, fileName.replace("En", "De"));
//				break;
//			case 22:
//				code = Delta.Decode(file_con);
//				num = Util.StringtoNumarray(code);
//				FileUtil.write(num, fileName.replace("En", "De"));
//				break;
//			case 33:
//				code = Golomb.Decode(file_con,m);
//				num = Util.StringtoNumarray(code);
//				FileUtil.write(num, fileName.replace("En", "De"));
//				break;
//			case 44:
//				code = Rice.Decode(file_con,m);
//				num = Util.StringtoNumarray(code);
//				FileUtil.write(num, fileName.replace("En", "De"));
//				break;
//			case 55:
//				index = file_con.indexOf("0");
//				Interpolative.s = file_con.substring(index+1);
//				System.out.println("请输入最小值：");
//				int low = Integer.parseInt(bre.readLine());
//				System.out.println("请输入最大值：");
//				int high = Integer.parseInt(bre.readLine());
//				code = Interpolative.Decode(index+1,low,high);
//				num = Util.StringtoNumarray(code);
//				FileUtil.write(num, fileName.replace("En", "De"));
//				break;
//			}
//		}
//		}catch(IOException e) {
//			System.out.println(e);
//		}
//	}
}
