package com.test.myvisen.lina.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wangshou_Datum
{

    @Expose
    private String courseid;
    @Expose
    private String name;
    @Expose
    private String photourl;
    @SerializedName("price_member")
    @Expose
    private String priceMember;
    @Expose
    private String lookcount;
    @Expose
    private String periodid;

    /**
     * 
     * @return
     *     The courseid
     */
    public String getCourseid() {
        return courseid;
    }

    /**
     * 
     * @param courseid
     *     The courseid
     */
    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The photourl
     */
    public String getPhotourl() {
        return photourl;
    }

    /**
     * 
     * @param photourl
     *     The photourl
     */
    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    /**
     * 
     * @return
     *     The priceMember
     */
    public String getPriceMember() {
        return priceMember;
    }

    /**
     * 
     * @param priceMember
     *     The price_member
     */
    public void setPriceMember(String priceMember) {
        this.priceMember = priceMember;
    }

    /**
     * 
     * @return
     *     The lookcount
     */
    public String getLookcount() {
        return lookcount;
    }

    /**
     * 
     * @param lookcount
     *     The lookcount
     */
    public void setLookcount(String lookcount) {
        this.lookcount = lookcount;
    }

    /**
     * 
     * @return
     *     The periodid
     */
    public String getPeriodid() {
        return periodid;
    }

    /**
     * 
     * @param periodid
     *     The periodid
     */
    public void setPeriodid(String periodid) {
        this.periodid = periodid;
    }

}
