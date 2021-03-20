package com.example.bluetoothdemo.custom_class;

public class ItemModel {

    public static final int REMOTE_DEVICE = 1;
    public static final int LOCAL_DEVICE = 2;
    private int type;
    private Object object;

    public ItemModel(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
