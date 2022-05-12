package com.nowcoder.community.entity.common;

/**
 * @author mary
 * @version 1.1
 * @love
 */
public class Page {
    // 当前页码
    private int current = 1;
    // 单页数据条数
    private int limit = 10;
    // 总数据量
    private int rows;
    // 请求的url
    private String url;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current < 1) {
            this.current = 1;
        } else this.current = Math.min(current, (rows % limit == 0 ? rows / limit : rows / limit + 1));
//        System.out.println("setCurrent方法被执行了......");
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit < 1 || limit > 100 ? 10 : limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return 获取当前页的数据偏移量
     */
    public int getOffset() {
        return (this.current - 1) * this.limit;
    }

    /**
     * @return 获取总的页数
     */
    public int getTotal() {
        return rows % limit == 0 ? rows / limit : rows / limit + 1;
    }

    /**
     *
     * @return 获取导航开头页
     */
    public int getFrom() {
        return current - 2 <= 0 ? 1 : current - 2;
    }

    public int getTo() {
        return Math.min(current + 2, getTotal());
    }

}
