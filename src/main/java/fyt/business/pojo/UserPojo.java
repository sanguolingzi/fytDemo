package fyt.business.pojo;

import fyt.business.model.base.PageModel;

import javax.persistence.*;

@Table(name = "userinfo")
public class UserPojo extends PageModel{
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_phone")
    private Long userPhone;

    @Column(name = "user_mail")
    private String userMail;

    @Column(name = "user_sex")
    private String userSex;

    @Column(name = "user_delete")
    private Integer userDelete;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return user_password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * @return user_phone
     */
    public Long getUserPhone() {
        return userPhone;
    }

    /**
     * @param userPhone
     */
    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * @return user_mail
     */
    public String getUserMail() {
        return userMail;
    }

    /**
     * @param userMail
     */
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    /**
     * @return user_sex
     */
    public String getUserSex() {
        return userSex;
    }

    /**
     * @param userSex
     */
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    /**
     * @return user_delete
     */
    public Integer getUserDelete() {
        return userDelete;
    }

    /**
     * @param userDelete
     */
    public void setUserDelete(Integer userDelete) {
        this.userDelete = userDelete;
    }
}