package com.caicedonarvaez.francisco.ozgstcalculator.classes;

/**
 * Created by Francisco on 13/08/2014.
 */
public class GSTCalculator {


    private Double amount;
    private Double gst;
    private Double subTotal;
    private Double total;

    private static final Double GST_RATE_ON_TOP = 11.0;
    private static final Double GST_RATE = 1.1;

    public GSTCalculator (Double amount) {

        this.amount = amount;
        this.subTotal = 0.0;
        this.gst = 0.0;
        this.total = 0.0;
    }

    public void calculateGST() {

        this.gst = amount / GST_RATE_ON_TOP;
        this.subTotal = this.amount - this.gst;
        this.total = this.getSubTotal() + this.getGst();

    }


    public void addGSTtoTotal(){

        this.subTotal = this.amount;
        this.total = amount * GST_RATE;
        this.gst = this.total - this.subTotal;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getGst() {
        return gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "GSTCalculator{" +
                "amount=" + amount +
                ", gst=" + gst +
                ", subTotal=" + subTotal +
                ", total=" + total +
                '}';
    }
}
