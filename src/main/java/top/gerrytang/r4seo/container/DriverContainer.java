package top.gerrytang.r4seo.container;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class DriverContainer {
    public LinkedList<RemoteWebDriver> chromeDrivers=new LinkedList<RemoteWebDriver>();
    public LinkedList<RemoteWebDriver> chromeDriversWaitList=new LinkedList<RemoteWebDriver>();

    public synchronized RemoteWebDriver get(){
        if(chromeDrivers.isEmpty()){
            return null;
        }
        else{
            RemoteWebDriver chromeDriver=chromeDrivers.getFirst();
            chromeDrivers.removeFirst();
            return chromeDriver;
        }
    }
    public void release(RemoteWebDriver chromeDriver){
        chromeDrivers.add(chromeDriver);
    }
    public DriverContainer(int count,String chromeUrl) throws MalformedURLException {
        for(int i=0;i<count;++i){
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            dc.setJavascriptEnabled(true);
            RemoteWebDriver driver = new RemoteWebDriver(new URL(chromeUrl), dc);
            driver.get("https://www.google.com");
            chromeDrivers.add(driver);
        }
    }

    protected void finalize() throws java.lang.Throwable {
        super.finalize();
        while(!chromeDrivers.isEmpty()){
            RemoteWebDriver chromeDriver=chromeDrivers.getFirst();
            chromeDriver.close();
            chromeDrivers.removeFirst();
        }
        while(!chromeDriversWaitList.isEmpty()){
            RemoteWebDriver chromeDriver=chromeDriversWaitList.getFirst();
            chromeDriver.close();
            chromeDriversWaitList.removeFirst();
        }
    }
}
