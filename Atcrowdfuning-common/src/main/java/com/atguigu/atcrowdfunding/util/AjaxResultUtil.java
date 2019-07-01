package com.atguigu.atcrowdfunding.util;

public class AjaxResultUtil {


    //判断是否成功
    private boolean issuccess;

    private Page page;


    private AjaxMsg ajaxMsg;

    public AjaxMsg getAjaxMsg() {
        return ajaxMsg;
    }

    public void setAjaxMsg(AjaxMsg ajaxMsg) {
        this.ajaxMsg = ajaxMsg;
    }

    //获取ajax的提示信息
    private String message;

    public boolean isIssuccess() {
        return issuccess;
    }
    public void setIssuccess(boolean issuccess) {
        this.issuccess = issuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
