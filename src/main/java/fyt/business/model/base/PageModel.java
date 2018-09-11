package fyt.business.model.base;

import java.io.Serializable;

public class PageModel implements Serializable{

    Integer currentPage = 1;

    Integer pageSize = 10;
    /**
     * 是否分页　0 不分页  1分页
     */
    Integer isLimit;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(Integer isLimit) {
        this.isLimit = isLimit;
    }
}
