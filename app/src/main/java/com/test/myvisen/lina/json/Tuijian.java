package com.test.myvisen.lina.json;


import java.util.List;

public class Tuijian
{


    /**
     * courseid : 319
     * name : 【新东方】雅思口语7分强化班
     * photourl : http://e.my.kesion.com/UploadFiles/user/116726/01311226862887001.jpg
     * price_member : 10.00
     * lookcount : 130
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