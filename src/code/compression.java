package code;

import java.io.*;
import code.BitUtils;

import compress.Gamma;

public class compression {
	public static void main(String[] args) {
		int[][] input = Datatest.creat();      //input[5][65535]
		byte[][] output = new byte[input.length][input[0].length * 4];
		for(int i = 0 ; i < input.length ; i++){
			int code = 0;
			int m = 4;
			int inpos = 0;
//			int outpos = 0;
//			int pos = 0;
			for(inpos = inpos; inpos < input[0].length;inpos++){
				Gamma.Encode(input[i][inpos],output[i]);
			}
			System.out.print(BitUtils.bitRead(output[i], output[i].length));
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
