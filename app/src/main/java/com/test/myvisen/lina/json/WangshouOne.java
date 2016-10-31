package com.test.myvisen.lina.json;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class WangshouOne
{

    @Expose
    private List<Wangshou_Datum> data = new ArrayList<Wangshou_Datum>();

    /**
     * 
     * @return
     *     The data
     */
    public List<Wangshou_Datum> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(List<Wangshou_Datum> data) {
        this.data = data;
    }

}
