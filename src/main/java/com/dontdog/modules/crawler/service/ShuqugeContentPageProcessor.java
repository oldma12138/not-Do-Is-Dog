package com.dontdog.modules.crawler.service;

import cn.hutool.core.io.FileUtil;
import lombok.extern.log4j.Log4j2;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author yaocanhong
 * @version 1.0.0
 * @data 23:58
 */
@Log4j2
public class ShuqugeContentPageProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        String pageTitle = page.getHtml().$(".content").$("h1", "text").toString();
        Selectable selector = page.getHtml().$(".showtxt", "text");
        log.info("标题:" + pageTitle);
        FileUtil.writeString(selector.toString(), "D://元尊/" + pageTitle, "UTF-8");

    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000);
    }
}
