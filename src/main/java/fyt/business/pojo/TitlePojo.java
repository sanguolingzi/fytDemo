package fyt.business.pojo;

import fyt.business.model.base.PageModel;

import javax.persistence.*;

@Table(name = "title")
public class TitlePojo extends PageModel {
    @Id
    @Column(name = "title_id")
    private Integer titleId;

    @Column(name = "title_name")
    private String titleName;

    @Column(name = "title_state")
    private String titleState;

    @Column(name = "title_delete")
    private Integer titleDelete;

    /**
     * @return title_id
     */
    public Integer getTitleId() {
        return titleId;
    }

    /**
     * @param titleId
     */
    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    /**
     * @return title_name
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * @param titleName
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    /**
     * @return title_state
     */
    public String getTitleState() {
        return titleState;
    }

    /**
     * @param titleState
     */
    public void setTitleState(String titleState) {
        this.titleState = titleState;
    }

    /**
     * @return title_delete
     */
    public Integer getTitleDelete() {
        return titleDelete;
    }

    /**
     * @param titleDelete
     */
    public void setTitleDelete(Integer titleDelete) {
        this.titleDelete = titleDelete;
    }
}