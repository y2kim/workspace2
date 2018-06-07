package api;
// 데이터 구조 
// xml 
// json
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Naver_pratice {

	public static void main(String[] args) {
		String clientId = "OhGPbryQ4tWYpbuzP2vF";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "GAhKbbBJju";//애플리케이션 클라이언트 시크릿값";
        //api 사용할려면 해당 회사에서  요구 해주는값을 등록해줘야 한다
        try {
            String text = URLEncoder.encode("삼성증권", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/news.xml?query="+ text+"&sort=date"; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"UTF8"));
            }
            String inputLine;
            //StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
            	System.out.println(inputLine.replaceAll(">", ">\n"));
               // response.append(inputLine);
            }
            br.close();
           // System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
	}

}
