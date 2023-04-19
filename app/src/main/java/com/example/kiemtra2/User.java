package com.example.kiemtra2;

public class User {
    int id;

    public User(int id, String tendangnhap, String matkhau, String email, int soquyen) {
        this.id = id;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.email = email;
        this.soquyen = soquyen;
    }
    public User()
    {

    }

    String tendangnhap;
    String matkhau;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSoquyen() {
        return soquyen;
    }

    public void setSoquyen(int soquyen) {
        this.soquyen = soquyen;
    }

    String email;
    int soquyen;

}
