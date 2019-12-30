package com.jasper.zenil.jaspercurso;

import java.util.ArrayList;

import com.jasper.zenil.jaspercurso.bean.DataBean;

public class DataBeanList {

    public ArrayList<DataBean> getDataBeanList() {

        ArrayList<DataBean> dataBeanList = new ArrayList<DataBean>();

        dataBeanList.add(createDataBean("Dorian", "London"));
        dataBeanList.add(createDataBean("Wilde", "USA"));
        dataBeanList.add(createDataBean("Poe", "Irlanda"));
        dataBeanList.add(createDataBean("Nakamura", "Tokyo"));

        return dataBeanList;
    }

    private DataBean createDataBean(String name, String country) {
        DataBean dataBean = new DataBean();
        dataBean.setName(name);
        dataBean.setCountry(country);
        return dataBean;
    }
}