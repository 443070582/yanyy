package com.test.myvisen.lina.json;

import java.util.List;

/**
 * Created by LinHaiMing on 2016/10/10.
 */
public class Daohang {


    /**
     * courseid : 10
     * name : After Effects影视后期特效进阶班
     * photourl : http://e.my.kesion.com/UploadFiles/2015-7/75/H130813308323842.jpg
     * price_member : 5.00
     * lookcount : 1434
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
