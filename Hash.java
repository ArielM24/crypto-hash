import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Hash {
	public static int A = 0x5a82;
	public static int B = 0x6ed9;
	public static int C = 0x8f1b;

	public String fillWord(String word, int length){
		String aux = word;
		for(int i = 0; i < length - word.length(); i++){
			aux = "0" + aux;
		}
		return aux;
	}

	public String toBase(String num, int b1, int b2){
		return Integer.toString(Integer.parseInt(num, b1),b2);
	}

	public String getHash(String str){
		int hash = 0;
		int l = str.length();
		int rest = l % 6;
		int w3 = (((l*8) & 0xff00) | ((l*8) & 0xff));

		for(int i = 0; i < l/6; i ++){
			int aux = hashX(str.substring(i*6 + 0, i*6 + 6), w3);
			hash = ((i == 0) ? aux : (hash ^ aux));
		}

		if(rest > 0){
			String last = fillWord(str.substring(l - rest,l),6);
			int aux = hashX(last, w3);
			hash = (hash ^ aux);
		}

		return toBase(""+hash,10,16);
	}

	public int hashX(String str6, int w3){
		int w2 = ((str6.charAt(0) << 8) | str6.charAt(1));
		int w1 = ((str6.charAt(2) << 8) | str6.charAt(3));
		int w0 = ((str6.charAt(4) << 8) | str6.charAt(5));
		return ((w0 & A) ^ (w3 & C) ^ (w2 & B) ^ negate(w1));
	}

	public int negate(int num){
		return 65535 - num;
	}
	
}