package com.test.myvisen.lina.json;

public class ZxBean
{
	 private String courseid;
     private String name;
     private String photourl;
     private String price_member;
     private String lookcount;
     private String periodid;
    


	public  ZxBean(String name, String photourl,String courseid) {
		this.photourl=photourl;
     	this.name=name;
     	this.courseid = courseid;
	}


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
