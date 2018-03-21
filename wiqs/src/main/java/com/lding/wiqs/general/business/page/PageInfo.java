package com.lding.wiqs.general.business.page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.github.pagehelper.Page;
/**
 * 为兼容easyui所作PageInfo
 * 依赖 com.github.pagehelper
 * @author yangling
 *
 * @param <T>
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class PageInfo<T> implements Serializable  {
	private static final long serialVersionUID = 1L;
	/* 当前页 */
    private int pageNum;
    /* 页面大小（每页记录数）*/
    private int pageSize;
    /* 当前页记录数 */
    private int size;
    /* 排序 */
    private String orderBy;
    /* 总页数 */
    private int pages;
    private boolean isFirstPage = false;
    private boolean isLastPage = false;
    private boolean hasPreviousPage = false;
    private boolean hasNextPage = false;
    /* 导航页码数 */
    private int navigatePages;
    /* 总记录数 */
    private long total;
    /* 分页结果集合 */
    private List<T> rows;

    public PageInfo() {
    }
    
    public PageInfo(List<T> list) {
    	if(list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.orderBy = page.getOrderBy();
            this.pages = page.getPages();
            this.size = page.size();
            this.total = page.getTotal();
            this.rows = page;
    	}else if(list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();
            this.pages = 1;
            this.rows = list;
            this.size = list.size();
            this.total = list.size();
    	}
        if (list instanceof Collection) {
            //判断页面边界
            judgePageBoudary();
        }
    }

    private void judgePageBoudary() {
        isFirstPage = pageNum == 1;
        isLastPage = pageNum == pages;
        hasPreviousPage = pageNum > 1;
        hasNextPage = pageNum < pages;
    }
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> list) {
        this.rows = list;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", rows=").append(rows);
        sb.append(", isFirstPage=").append(isFirstPage);
        sb.append(", isLastPage=").append(isLastPage);
        sb.append(", hasPreviousPage=").append(hasPreviousPage);
        sb.append(", hasNextPage=").append(hasNextPage);
        sb.append(", navigatePages=").append(navigatePages);
        sb.append('}');
        return sb.toString();
    }    
}
