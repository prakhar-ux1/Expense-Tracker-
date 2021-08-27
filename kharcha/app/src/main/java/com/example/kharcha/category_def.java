package com.example.kharcha;

public class category_def {
    private int img;
    private String name;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public category_def(int img, String name, int type) {
        this.img = img;
        this.name = name;
        this.type = type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;

}
