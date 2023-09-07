package org.multi.source;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.multi.source.domain.Poetry;
import org.multi.source.util.HttpclientUtils;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map<String, String> headers = new HashMap<>();
        headers.put("x-lc-id","9pq709je4y36ubi10xphdpovula77enqrz27idozgry7x644");
        headers.put("x-lc-sign","d8bd3eae6ba520cfc8b6223f3a50cda8,1682049526553");
        Map<String, String> params = new HashMap<>();
        params.put("kind","shi");
        params.put("page","1");
        params.put("perPage","10000");
        String post = HttpclientUtils.doPost("https://avoscloud.com/1.1/call/getWorksIncludeCountByGenreKind", headers, params);
        JSONObject jsonObject = JSONObject.parseObject(post);
        String count = jsonObject.getString("count");
        long total = Long.parseLong(count);
        for (int i = 1;i<total/1000;i++) {
            Map<String, String> params_01 = new HashMap<>();
            params.put("kind","shi");
            params.put("page","1");
            params.put("perPage","10000");
            String post_01 = HttpclientUtils.doPost("https://avoscloud.com/1.1/call/getWorksIncludeCountByGenreKind", headers, params);
            JSONObject worksJson = JSONObject.parseObject(post_01);
            JSONArray works = worksJson.getJSONArray("works");
            for (int j = 0;j<works.size();j++){
                String string = JSONObject.toJSONString(works.get(j));
                JSONObject dataObject = JSONObject.parseObject(string);
                String authorName = dataObject.getString("authorName");
                String title = dataObject.getString("title");
                String content = dataObject.getString("content");
                String annotation = dataObject.getString("annotation");
                String translation = dataObject.getString("translation");
                String masterComment = dataObject.getString("masterComment");
                String intro = dataObject.getString("intro");

                Poetry poetry = new Poetry();
                poetry.setAnnotation(annotation);
                poetry.setContent(content);
                poetry.setMasterComment(masterComment);
                poetry.setTitle(title);
                poetry.setTranslation(translation);
                poetry.setAuthor(authorName);
                poetry.setType(1);
                poetry.setIntro(intro);
            }
        }

    }

}
