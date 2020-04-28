package org.techtown.project;

public class TelDTO {

    private  String name;
    private  String tel;

 // 단축키 Alt + insert
    public TelDTO(String name, String tel) {
        this.name = name;
        this.tel = tel;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }



}
