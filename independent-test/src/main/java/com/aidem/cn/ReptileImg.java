package com.aidem.cn;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aidem
 * @date 2021-04-20
 * @description code
 */
public class ReptileImg {
//
//    public static void main(String[] args) {
//        //爬取网站的url路径
//        String url = "https://wallhaven.cc/hot?page=2";
//        try {
//            //使用Jsoup解析url
//            Document document = Jsoup.parse(new URL(url), 10000);
//            //通过标签id获取网页包含图片的标签
//            Elements elements = document.getElementsByClass("thumb-listing-page");
//            if (CollectionUtils.isEmpty(elements)){
//                return;
//            }
//            for (Element element : elements) {
//                //获取img标签
//                Elements imgs = element.getElementsByTag("img");
//                int id = 0;
//                //循环遍历把图片src属性遍历出来
//                for (Element img : imgs) {
//                    String src = img.attr("data-src");
//                    System.out.println(src);
//                    id++;
//                    URL target = new URL(src);
//                    URLConnection urlConnection = target.openConnection();
//                    urlConnection.setRequestProperty("Accept", "*/*");
//                    urlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA)"); //模拟ie浏览器
//                    urlConnection.setRequestProperty("Accept-Language", "zh-cn");
//                    Document imgDoc = Jsoup.parse(new URL(src), 10000);
//                    System.out.println(imgDoc);
//                    break;
//
//
//                    //获取输入流
//                    InputStream inputStream = urlConnection.getInputStream();
//
////                    //获取输出流 这里是下载保存图片到本地的路径
//                    OutputStream outputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\爬虫\\" + id + ".jpg");
//                    int temp = 0;
//                    while ((temp = inputStream.read()) != -1) {
//                        outputStream.write(temp);
//
//                    }
//                    System.out.println(id + ".jpg下载完毕!!!");
////                    outputStream.close();
//                    inputStream.close();
//                }
//            }
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//

    public static void main(String[] args) throws IOException {
        Connection connection = Jsoup.connect("https://th.wallhaven.cc/small/9m/9moovd.jpg");
        Map<String, String> header = new HashMap<String, String>();
        header.put(
                "Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        header.put("Accept-Encoding", "gzip, deflate, br");
        header.put("Accept-Language", "zh-CN,zh;q=0.9");
        header.put("Cache-Control", "max-age=0");
        header.put(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36");
        Document doc = connection.ignoreContentType(true).headers(header)
                .timeout(30000).get();
        //使用Jsoup解析url
//        Document document = Jsoup.parse(new URL("https://th.wallhaven.cc/small/9m/9moovd.jpg"), 10000);
        System.out.println(doc);
    }
}
