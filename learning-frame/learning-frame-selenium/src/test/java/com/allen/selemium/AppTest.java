package com.allen.selemium;


import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Java Robot类
     * 自动化测试
     */
    @Test
    public void shouldAnswerWithTrue() {
        try {
            Robot robot = new Robot();

            /*获取颜色*/
            Color pixelColor = robot.getPixelColor(100, 100);
            System.out.println(pixelColor);

            /*滚动滑轮*/
            robot.mouseWheel(20);

            /*移动鼠标*/
            robot.mouseMove(100, 100);

            /*按下、释放鼠标*/
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            /*按下、释放键盘*/
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);

            /*等待时间列表执行完*/
            robot.waitForIdle();

            System.out.println("开始截图。。。");

            /*延迟*/
            robot.delay(2000);

            /*截图*/
            BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(0, 0, 500, 500));
            ImageIO.write(screenCapture, "jpg", new File("D:/cap.jpg"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selecnium() {
        System.setProperty("webdriver.chrome.driver", "E:\\Program Files\\IT\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("E:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        WebDriver driver = new ChromeDriver(chromeOptions);
        try {

//        driver.manage().window().setSize(new org.openqa.selenium.Dimension(800, 450));
            driver.manage().window().setPosition(new org.openqa.selenium.Point(0, 0));
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

            driver.get("https://cn.pornhub.com/login");
            Thread.sleep(2000);

            driver.findElement(By.id("username")).sendKeys("allenjiawen@gmail.com");
            Thread.sleep(2000);

            driver.findElement(By.id("password")).sendKeys("d9863711h");
            Thread.sleep(2000);

            driver.findElement(By.id("submit")).sendKeys(Keys.ENTER);


//            driver.get("https://cn.pornhub.com");

//            new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>(){
//                @NullableDecl
//                @Override
//                public WebElement apply(@NullableDecl WebDriver webDriver) {
//                    return webDriver.findElement(By.id("submit"));
//                }
//            }).click();

//        driver.manage().addCookie(new Cookie("ua", "b4046c447614dcbe06d949fe3062a42a"));
//        driver.manage().addCookie(new Cookie("platform", "pc"));
//        driver.manage().addCookie(new Cookie("ss", "463855900513603253"));
//        driver.manage().addCookie(new Cookie("bs", "%s"));
//        driver.manage().addCookie(new Cookie("RNLBSERVERID", "ded6729"));
//        driver.manage().addCookie(new Cookie("FastPopSessionRequestNumber", "1"));
//        driver.manage().addCookie(new Cookie("FPSRN", "1"));
//        driver.manage().addCookie(new Cookie("performance_timing", "home"));
//        driver.manage().addCookie(new Cookie("RNKEY", "40859743*68067497,1190152786,3363277230,1"));
//        driver.manage().addCookie(new Cookie("il", "v1lqEenPUZsxsSe3SDl38ibM1mJ9DIYaaC1swQn9jQAKAxNTk2MDI2MjMzYWdyNmFNUzdoTnFqa2VaNWEwQnc3SWZldlZKZWpWU09RRk9uSkdCdQ.. "));

//            Thread.sleep(3000);
//            driver.get("https://ed.phncdn.com/videos/202004/28/308275921/1080P_4000K_308275921.mp4?validfrom=1592555364&validto=1592562564&rate=50000k&burst=50000k&ip=45.78.7.163&hash=bcNyn%2FP71yeH2tGc89TJO%2BPEt%2Bw%3D");


            Thread.sleep(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
