/*
package com.wb.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

*/
/**
 * @ClassName IndexConfig
 * @Deacription 本地开发打开浏览器跳转首页
 * @Author W
 * @Date 2019/12/12
 * @Version 1.0
 *//*


//@Configuration
public class IndexConfig {
    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        String url = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
        System.setProperty("webdriver.chrome.driver", url);//指定驱动路径
        WebDriver driver = new ChromeDriver();
        //打开首页
        driver.get("http://localhost:9527/index");
        System.out.println(" Open " + driver.getCurrentUrl());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        //点击“新闻链接”
//        driver.findElement(By.linkText("新闻")).click();
//        System.out.println("chick 新闻");
//        Thread.sleep(2000);
//        //执行浏览器后退
//        driver.navigate().back();
//        System.out.println("back... this www.baidu.com");
//        Thread.sleep(2000);
//        //执行浏览器返回
//        driver.navigate().forward();
//        System.out.println("forward..."+driver.getCurrentUrl ());
//        Thread.sleep(2000);
//        //关闭浏览器
//        driver.quit();

//        System.out.println("应用已经准备就绪 ... 启动浏览器");
//        String url = "http://localhost:9527/index";
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}*/
