package base.listeners;

import base.BaseTests;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utils.random.RandomGen;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestListener extends BaseTests implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver webDriver = ((BaseTests) testClass).getDriver();

        try {
            getFullScreenShot(webDriver);
        } catch (IOException e) {
        }
    }

    protected byte[] getFullScreenShot(WebDriver driver) throws IOException {
        int number = RandomGen.getNumberBetween(100, 1000);
        String fileName = number + ".png";
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        File resourcesFolder = new File("screenshots");
        if (!resourcesFolder.exists()) resourcesFolder.mkdir();
        File file = new File("screenshots/" + fileName);
        ImageIO.write(screenshot.getImage(), "PNG", file);
        return Files.readAllBytes(file.toPath());
    }
}
