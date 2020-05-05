package top.gerrytang.r4seo.service;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.gerrytang.r4seo.container.DriverContainer;

@Service
@CacheConfig(cacheNames = "pageCache")
public class RenderService {

    @Autowired
    DriverContainer driverContainer;

    @Cacheable(value="pageCache",key="#url")
    public String render(String url){
        RemoteWebDriver driver = null;
        String ret = null;
        try{
            driver = driverContainer.get();
            driver.get(url);
            ret = driver.getPageSource();
        }
        catch (Exception e){

        }
        finally {
            if(driver!=null) {
                driverContainer.release(driver);
            }
        }
        if(ret == null){
            throw new RuntimeException("fail to load the page");
        }
        else{
            return ret;
        }
    }

    @CacheEvict(value="pageCache",allEntries = true)
    public void removeAllCache(){

    }

    @CacheEvict(value="pageCache",allEntries = false,key="#url")
    public void removeCache(String url){

    }
}
