package org.example.batchexample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NaverFinance {
    private static final String URL = "https://finance.naver.com/sise/lastsearch2.naver";

    public static void crawling() {
        try {
            Document doc;
            doc = Jsoup.connect(URL)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .get();
            Elements numberElements = doc.select("td.no");
            Elements titleElements = doc.select("a.tltle");
            Elements priceElements = doc.select("td.number:nth-of-type(4)");

            //데이터를 담을 List
            List<Map<Object, String>> list = new ArrayList<>();

            //데이터 담기
            for (int i = 0; i < numberElements.size(); i++) {
                Element numberElement = numberElements.get(i);
                Element titleElement = titleElements.get(i);
                Element priceElement = priceElements.get(i);

                String number = numberElement.text();
                String title = titleElement.text();
                String currentPrice = priceElement.text();

                Map<Object, String> map = new HashMap<>();
                map.put("number", number);
                map.put("title", title);
                map.put("currentPrice", currentPrice);
                list.add(map);
            }

            System.out.println(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
