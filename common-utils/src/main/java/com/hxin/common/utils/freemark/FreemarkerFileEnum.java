package com.hxin.common.utils.freemark;

/**
 * 模板工具
 * @author hxin
 */
public enum FreemarkerFileEnum {

    MODAL("mould-modal.ftl"),
    DAO("mould-dao.ftl"),
    DAOIMPL("mould-dao-impl.ftl"),
    MAPPER("mould-mapper.ftl"),
    MAPPERXML("mould-mapper-xml.ftl");

    private String url;

    private FreemarkerFileEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
