package com.atguigu.atcrowdfunding.util;

import com.github.pagehelper.PageInfo;

public class AjaxPageResult<T> {

    private boolean issuccess;

    private String message;

    private PageInfo<T> pageInfo;

    public AjaxPageResult() {
    }

    public AjaxPageResult(boolean issuccess, String message, PageInfo<T> pageInfo) {
        this.issuccess = issuccess;
        this.message = message;
        this.pageInfo = pageInfo;
    }

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

    public PageInfo<T> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }
}
