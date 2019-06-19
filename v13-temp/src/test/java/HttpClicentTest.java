import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class HttpClicentTest {
    @Test
    public void grapHTMLTest() {
        try {
            //打开浏览器
            CloseableHttpClient client = HttpClients.createDefault();

            //输入网址
            String url = "https://www.baidu.com/";
            HttpGet httpGet = new HttpGet(url);

            //敲回车
            CloseableHttpResponse execute = client.execute(httpGet);

            //解析服务器响应的信息
            int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode == 200) {
//                //请求成功
//                //获取响应体信息
//                HttpEntity entity = execute.getEntity();
//                //获取输入流，这样我们就可以读取远程服务给我们反馈的信息
//                InputStream inputStream = entity.getContent();
//                //IO流
//                int len;
//                byte[] data = new byte[1024];
//                while ((len = inputStream.read(data)) != -1) {
//                    System.out.println(new String(data, 0, len));
//                }
                //如果只是获取内容，有简化的方法
                EntityUtils.toString(execute.getEntity());
            } else {
                System.out.println(statusCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
