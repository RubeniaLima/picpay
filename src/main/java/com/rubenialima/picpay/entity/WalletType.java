package com.rubenialima.picpay.entity;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_wallet_type")
public class WalletType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String desciption;



    public WalletType() {

    }

    public WalletType(Long id, String desciption) {
        this.id = id;
        this.desciption = desciption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public enum Enum{
        USER(1L,"user"),
        MERCHANT(2L,"merchant");

        Enum(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        private Long id;
        private String description;

        public WalletType get(){
            return new WalletType(id, description);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WalletType that = (WalletType) o;
        return Objects.equals(id, that.id) && Objects.equals(desciption, that.desciption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desciption);
    }
}
