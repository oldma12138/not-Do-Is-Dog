package com.dontdog.modules.crawler.service;

import lombok.extern.log4j.Log4j2;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author yaocanhong
 * @version 1.0.0
 * @data 23:24
 */
@Log4j2
public class ShuqugeTitlePageProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        List<Selectable> selectableList = (page.getHtml().$(".listmain").$("dd").nodes());
        for (Selectable selectable: selectableList){
            String pageUrl = selectable.$("a", "href").toString();
            String pageName = selectable.$("a", "text").toString();
            log.info(pageUrl);
            pageUrl = "http://www.shuquge.com/txt/5809/" + pageUrl;
            Spider.create(new ShuqugeContentPageProcessor()).addUrl(pageUrl).run();
        }

    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000);
    }

    public static void main(String[] args) {
        Spider.create(new ShuqugeTitlePageProcessor()).addUrl("http://www.shuquge.com/txt/5809/index.html").run();
    }
}
