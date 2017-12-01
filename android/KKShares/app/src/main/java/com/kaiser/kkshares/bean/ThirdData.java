package com.kaiser.kkshares.bean;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/8/16.
 */
public class ThirdData implements Comparator<ThirdData>{
    private String symbol;
    private String name;
    private double trade;
    private double pricechange;
    private double changepercent;
    private String buy;
    private String sell;
    private String settlement;
    private String open;
    private String high;
    private String low;
    private int volume;
    private String amount;
    private String code;
    private String ticktime;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTrade() {
        return trade;
    }

    public void setTrade(double trade) {
        this.trade = trade;
    }

    public double getPricechange() {
        return pricechange;
    }

    public void setPricechange(double pricechange) {
        this.pricechange = pricechange;
    }

    public double getChangepercent() {
        return changepercent;
    }

    public void setChangepercent(double changepercent) {
        this.changepercent = changepercent;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTicktime() {
        return ticktime;
    }

    public void setTicktime(String ticktime) {
        this.ticktime = ticktime;
    }

    @Override
    public String toString() {
        return "ThirdData{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", trade='" + trade + '\'' +
                ", pricechange='" + pricechange + '\'' +
                ", changepercent='" + changepercent + '\'' +
                ", buy='" + buy + '\'' +
                ", sell='" + sell + '\'' +
                ", settlement='" + settlement + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", volume='" + volume + '\'' +
                ", amount='" + amount + '\'' +
                ", code='" + code + '\'' +
                ", ticktime='" + ticktime + '\'' +
                '}';
    }



    @Override
    public int compare(ThirdData o1, ThirdData o2) {

        return 0;
    }
}
