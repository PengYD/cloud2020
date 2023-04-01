package com.atguigu.springcloud.entities;

/**
 * @author pengyd
 */
public class TaskDO {

   private String id;

   private String cron;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
