package cs.suda.stdtest.entity;

import lombok.Data;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Data
public class AccessToken {
    private static final String corpId = "ww5f3c78aa1ea4bbdc";
    private static final String corpSecret = "76fjX2FNai30PXW6REeV6lF7j4dyQsNJLUjLtmNKCxo";
    private String accessToken;
    public void setAccessToken() {
        //构造获取AccessToken的请求链接
        String accessTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="
                + corpId +"&corpsecret=" + corpSecret;
        JSONObject jsonObject = null;
        jsonObject = getJson(accessTokenUrl);
        //将字符串转成json对象
        this.setAccessToken(jsonObject.getString("access_token"));
        //字典，根据key找到value,并且赋值给该对象的accessToken属性。
    }
    public JSONObject getUserInformation(String code){
        String getUserIdUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="
                + accessToken + "&code=" + code;
        JSONObject jsonObject = null;
        jsonObject = getJson(getUserIdUrl);
        //将字符串转成json对象
        String getUserInformationUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="
                + accessToken + "&userid=" + jsonObject.getString("UserId");
        //获取了UserId后，进一步获取User的JSON信息串。
        return getJson(getUserInformationUrl);
    }
    public JSONObject getJson(String urlString){
        JSONObject jsonObject = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //url.openConnection()得到时URLConnection对象。
            connection.setRequestMethod("GET");
            //设置请求方法为GET，默认为GET
            connection.setDoOutput(true);
            //使用URL链接进行输出，默认为false,(POST才会用到)
            connection.setDoInput(true);
            //使用URL链接进行输入，默认为true，(GET方法使用)
            connection.connect();
            //打开与此URL引用的资源的通信链接，如果此类连接尚未建立。
            InputStream inputStream = connection.getInputStream();
            //返回从此打开的链接读取的输入流。
            int size = inputStream.available();
            //返回从该输入流中可以读取（或跳过）的字节数的估计值，而不会被下一次调用此输入流的方法阻塞。
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            //从输入流中读取数据到bytes中去。
            String message = new String(bytes, StandardCharsets.UTF_8);
            //将读取的数据(实际上是个json对象)转为String
            jsonObject = JSONObject.fromObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
