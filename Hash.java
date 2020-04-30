import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 

public class Hash {
	public String getHash(String str) throws Exception {
		return toHexString(getSHA(str));
	}
	public String getHash(File f) throws Exception {
		return toHexString(getSHA(readFile(f)));
	}

	public String readFile(String file) throws Exception{ 
		File f = new File(file);
		String content = "";
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		while(line != null){
			content += ("\n"+line);
			line = br.readLine();
		}
		br.close();
		return content.substring(1,content.length());
	}

	public String readFile(File f) throws Exception {
		return readFile(f.getAbsolutePath());
	}

	public boolean checkHashFile(String file) throws Exception {
		String content = readFile(file);
		String[] str = splitHash(content);
		String hash = getHash(str[0]);
		return (hash.equals(str[1])) ? true : false;
	}

	public String[] splitHash(String str) {
		String content = "";
		String[] strs = str.split("\n");
		int i = 0;
		for(i = 0; i < strs.length - 1; i++){
			content += strs[i];
			if(i < strs.length - 2){
				content += "\n";
			}
		}
		String[] res = {content, strs[i]};
		return res;
	}

	public boolean checkHashFile(File f) throws Exception {
		return checkHashFile(f.getAbsolutePath());
	}

	public void generateHashFile(String file) throws Exception{
		File f = new File(file);
		String content = readFile(file);
		String hash = getHash(content);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file.replace(".txt","_h.txt")));
		for(int i = 0; i < content.length(); i++){
			if(content.charAt(i) == '\n'){
				bw.newLine();
			}else{
				bw.write(content.charAt(i));
			}
		}
		bw.newLine();
		bw.write(hash);
		bw.close();
	}

	public void generateHashFile(File f) throws Exception {
		generateHashFile(f.getAbsolutePath());
	}

	public static byte[] getSHA(String input) throws NoSuchAlgorithmException {  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    public static String toHexString(byte[] hash) { 
        BigInteger number = new BigInteger(1, hash);  
  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        while (hexString.length() < 32) {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
    }
	
}