package com.test.myvisen.lina.json;

import java.util.List;

/**
 * Created by LinHaiMing on 2016/10/9.
 */
public class zhiye
{

    /**
     * classid : 9
     * classname : 职业资格
     * classimg : http://m.kesion.com/Template3G/Style01/phone_ui/icon/icon1.png
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String classid;
        private String classname;
        private String classimg;

        public String getClassid() {
            return classid;
        }

        public void setClassid(String classid) {
            this.classid = classid;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }

        public String getClassimg() {
            return classimg;
        }

        public void setClassimg(String classimg) {
            this.classimg = classimg;
        }
    }
}
