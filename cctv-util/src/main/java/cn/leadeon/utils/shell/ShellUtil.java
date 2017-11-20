/**
 * 
 */
package cn.leadeon.utils.shell;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * @author billy
 * @date 2017年1月17日
 */
public class ShellUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private ShellUtil(){}
	
	public static String exec(String shell, String... params) throws IOException{
		
		String[] command = new String[]{};
		command[0] = "sh "+shell;
		if(null != params && params.length > 0){
			for(int i=0; i< params.length; i++){
				command[i+1] = params[i];
			}
		}
		ProcessBuilder procBuilder = new ProcessBuilder(command);
		Process proc = procBuilder.start();
		
		
		LineNumberReader lineInput = new LineNumberReader(new InputStreamReader(proc.getInputStream()));
		
		String lineSep = "\n";
		StringBuilder result = new StringBuilder();
		String line = null;
		while(null != (line = lineInput.readLine())){
			result.append(line).append(lineSep);
		}
		
		try {
			int status = proc.waitFor();
			System.out.println("waitFor:"+status);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString();
	}
	
}
