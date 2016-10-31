package com.test.myvisen.lina.json;

import java.util.List;

/**
 * Created by LinHaiMing on 2016/10/9.
 */
public class ceshi {


    /**
     * courseid : 322
     * name : 测试测试课程
     * photourl :
     * price_member : 499.00
     * lookcount : 38
     * periodid : 0
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String courseid;
        private String name;
        private String photourl;
        private String price_member;
        private String lookcount;
        private String periodid;

        public String getCourseid() {
            return courseid;
        }

        public void setCourseid(String courseid) {
            this.courseid = courseid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhotourl() {
            return photourl;
        }

        public void setPhotourl(String photourl) {
            this.photourl = photourl;
        }

        public String getPrice_member() {
            return price_member;
        }

        public void setPrice_member(String price_member) {
            this.price_member = price_member;
        }

        public String getLookcount() {
            return lookcount;
        }

        public void setLookcount(String lookcount) {
            this.lookcount = lookcount;
        }

        public String getPeriodid() {
            return periodid;
        }

        public void setPeriodid(String periodid) {
            this.periodid = periodid;
        }
    }
}
