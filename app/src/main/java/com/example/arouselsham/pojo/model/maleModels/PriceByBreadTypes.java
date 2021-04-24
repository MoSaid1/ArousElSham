package com.example.arouselsham.pojo.model.maleModels;

public class PriceByBreadTypes {

    private double syrianPrice;
    private double frenchPrice;
    private double saro5Price;

    public PriceByBreadTypes() {
    }

    public PriceByBreadTypes(double syrianPrice, double frenchPrice, double saro5Price) {
        this.syrianPrice = syrianPrice;
        this.frenchPrice = frenchPrice;
        this.saro5Price = saro5Price;
    }

    public double getSyrianPrice() {
        return syrianPrice;
    }

    public double getFrenchPrice() {
        return frenchPrice;
    }

    public double getSaro5Price() {
        return saro5Price;
    }

    public void setSyrianPrice(double syrianPrice) {
        this.syrianPrice = syrianPrice;
    }

    public void setFrenchPrice(double frenchPrice) {
        this.frenchPrice = frenchPrice;
    }

    public void setSaro5Price(double saro5Price) {
        this.saro5Price = saro5Price;
    }
}
