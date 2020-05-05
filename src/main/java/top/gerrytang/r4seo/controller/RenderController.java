package top.gerrytang.r4seo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.gerrytang.r4seo.service.RenderService;

import java.net.MalformedURLException;

/**
 * manage units
 * @author gerry
 */
@Api(tags = "render",description = "Rest API to render")
@RestController
@RequestMapping("/render")
public class RenderController {

    protected static final Logger _logger = LoggerFactory.getLogger(RenderController.class);

    @Autowired
    RenderService renderService;

    private RenderController(){}

    @GetMapping("")
    @ApiOperation("render the page")
    public String render(
            @ApiParam(name = "url",required = true)
            @RequestParam(value = "url",required = true)String url
    )throws MalformedURLException {
        return renderService.render(url);
    }

    @DeleteMapping("")
    @ApiOperation("remvoe all cache")
    public String removeAllCache(
    ) {
        renderService.removeAllCache();
        return "success";
    }

    @DeleteMapping("/url")
    @ApiOperation("remvoe specified cache")
    public String removeAllCache(
            @ApiParam(name = "url",required = true)
            @RequestParam(value = "url",required = true)String url
    ) {
        renderService.removeCache(url);
        return "success";
    }
}
