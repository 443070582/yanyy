package com.test.myvisen.lina.json;

public class DataBean
{
	 private String classid;
     private String classname;
     private String classimg;
     public DataBean(String classid,String classname,String classimg){
	    this.classid=classid;
	    this.classimg=classimg;
	    this.classname=classname;
     }
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
