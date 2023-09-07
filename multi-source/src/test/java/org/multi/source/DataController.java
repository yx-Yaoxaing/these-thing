package org.multi.source;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.multi.source.domain.CjSubject;
import org.multi.source.util.HttpclientUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataController {

    @Autowired
    private CjPoetryMapper cjPoetryMapper;

    @Autowired
    private CjSubjectMapper cjSubjectMapper;

    @Autowired
    private CjSubPoeMapper cjSubPoeMapper;

   @Test
    public void importData(){
        selenium("http://lib.xcz.im/library");
    }


    public  void selenium(String url) {
        // 设置 chromedirver 的存放位置
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(url);
        // List<WebElement> webElements = webDriver.findElements(By.xpath("//div[@class='subtitle']"));
        // AtomicInteger atomicInteger = new AtomicInteger(1);

        for (int i = 5; i <= 30; i++) { //*[@id="app"]/div[2]/div[5]
            String xpathPrefix = "//*[@id=\"app\"]/div[2]/div[" + i + "]";
            String xpathPrefix_pr = "//*[@id=\"app\"]/div[2]/div[" + (i+1) + "]";
            String text = webDriver.findElement(By.xpath(xpathPrefix)).getText().split(" ")[0];


            if (!cjSubjectMapper.findBySujectName(text)) {
                CjSubject cjSubject = new CjSubject();
                cjSubject.setPid(0L);
                cjSubject.setSubjectName(text);
                cjSubject.setType(0);
               // cjSubjectMapper.save(cjSubject);
            }
            Long subjectId = cjSubjectMapper.findIdBySujectName(text);

            for (int j = 1; j <= 50; j++) { //*[@id="app"]/div[2]/div[6]/div[1]
                try {
                    String xpath = xpathPrefix_pr + "/div[" + j + "]";
                    // Do something with the element selected by the XPath expression
                    WebElement element = webDriver.findElement(By.xpath(xpath));

                    if (!cjSubjectMapper.findBySujectName(text)) {
                        CjSubject cjSubject = new CjSubject();
                        cjSubject.setPid(subjectId);
                        cjSubject.setSubjectName(text);
                        cjSubject.setType(0);
                       // cjSubjectMapper.save(cjSubject);
                    }
                    Long subId = cjSubjectMapper.findIdBySujectName(text);
                    webDriver.findElement(By.xpath(xpath)).click();
                    // 获取当前页的网页地址
                    String currentUrl = webDriver.getCurrentUrl();
                    String id = url.substring(url.lastIndexOf("/") + 1);
                    String sendUrl = "https://avoscloud.com/1.1/call/getWorksByCollection";
                    Map<String, String> headers = new HashMap<>();
                    headers.put("x-lc-id","9pq709je4y36ubi10xphdpovula77enqrz27idozgry7x644");
                    headers.put("x-lc-sign","d8bd3eae6ba520cfc8b6223f3a50cda8,1682049526553");
                    Map<String, String> params = new HashMap<>();
                    headers.put("collectionId","id");
                    headers.put("page","1");
                    headers.put("perPage","500");
                    String post = HttpclientUtils.doPost(sendUrl, headers, params);


                    System.err.println(currentUrl);
                    // 回到上一个页面
                    webDriver.navigate().back();
                } catch (Exception e) {

                }
            }
        }

        webDriver.quit();
    }

}
