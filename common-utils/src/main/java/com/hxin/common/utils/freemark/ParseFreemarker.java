package com.hxin.common.utils.freemark;

import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * freemarker 解析器
 * @author hxin
 */
@Component
public class ParseFreemarker {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    public String parseFtlFile(String path, Object data) {
        String htmlText = null;
        try {
            Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(path);
            htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return htmlText;
    }

}
