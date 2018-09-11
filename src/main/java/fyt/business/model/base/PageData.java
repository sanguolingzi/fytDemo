package fyt.business.model.base;

import java.util.List;

public class PageData<T> {

    Long totalRecord;

    List<T> listData;

    public PageData(){

    }

    public PageData(Long totalRecord,List<T> listData){
        this.listData = listData;
        this.totalRecord = totalRecord;
    }

    public Long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<T> getListData() {
        return listData;
    }

    public void setListData(List<T> listData) {
        this.listData = listData;
    }
}
