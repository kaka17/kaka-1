package com.kaka.base;

import java.math.BigDecimal;


public class BigDecimalUtil {
	//默认除法运算精度    
    public static final int DEF_DIV_SCALE = 10;
  //默认除法运算精度    
    public static final int DEF_DIV_SCALE_TWO = 2;
       
    //所有方法均用静态方法实现，不允许实例化    
    private BigDecimalUtil() {}    
    
    /**  
     * 实现浮点数的加法运算功能  
     * @param v1 加数1  
     * @param v2 加数2  
     * @return v1+v2的和  
     */    
    public static double add(double v1,double v2) {    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.add(b2).doubleValue();    
    }
    
    /**
     *  实现浮点数组的加法运算功能  v[1]+v[2]+~~~~
     * @Title: add 
     * @Description: 
     * @param v2  
     * @return 参数说明
     * @return double    返回类型
     */
    public static double add(double [] v) {
    	BigDecimal sum=new BigDecimal("0.0");
    	if(v==null || v.length==0){
    		return sum.doubleValue();	
    	}
    	for(double d:v){
    		BigDecimal b = new BigDecimal(Double.toString(d));
    		sum= sum.add(b);
    	}
        return sum.doubleValue();    
    }
    
    
    
    /**  
     * 实现浮点数的加法运算功能    返回BigDecimal类型
     * @param v1 加数1  
     * @param v2 加数2  
     * @return v1+v2的和  
     */    
    public static BigDecimal addB(double v1,double v2) {    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.add(b2);    
    }   
    
    /**  
     * 实现浮点数的减法运算功能  
     * @param v1 被减数  
     * @param v2 减数  
     * @return v1-v2的差  
     */    
    public static double sub(double v1,double v2) {    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.subtract(b2).doubleValue();    
    }    
    
    /**  
     * 实现浮点数的减法运算功能   返回BigDecimal类型
     * @param v1 被减数  
     * @param v2 减数  
     * @return v1-v2的差  
     */    
    public static BigDecimal subB(double v1,double v2) {    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.subtract(b2);    
    } 
    
    /**  
     * 实现浮点数的乘法运算功能  
     * @param v1 被乘数  
     * @param v2 乘数  
     * @return v1×v2的积  
     */    
    public static double multi(double v1,double v2) {    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.multiply(b2).doubleValue();    
    }    
    
    /**  
     * 实现浮点数的除法运算功能  
     * 当发生除不尽的情况时，精确到小数点以后DEF_DIV_SCALE位(默认为10位)，后面的位数进行四舍五入。  
     * @param v1 被除数  
     * @param v2 除数  
     * @return v1/v2的商  
     */    
    public static double div(double v1,double v2) {    
     BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();    
    }    
    
    /**  
     * 实现浮点数的除法运算功能  
     * 当发生除不尽的情况时，精确到小数点以后scale位，后面的位数进行四舍五入。  
     * @param v1 被除数  
     * @param v2 除数  
     * @param scale 表示需要精确到小数点以后几位  
     * @return v1/v2的商  
     */    
    public static double div(double v1,double v2,int scale) {    
        if (scale < 0) {    
            throw new IllegalArgumentException(    
                "The scale must be a positive integer or zero");    
        }    
        BigDecimal b1 = new BigDecimal(Double.toString(v1));    
        BigDecimal b2 = new BigDecimal(Double.toString(v2));    
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();    
    }    
    
    /**  
     * 提供精确的小数位四舍五入功能  
     * @param v 需要四舍五入的数字  
     * @param scale 小数点后保留几位  
     * @return 四舍五入后的结果  
     */    
    public static double round(double v,int scale) {    
        if (scale < 0) {    
            throw new IllegalArgumentException(    
                "The scale must be a positive integer or zero");    
        }    
        BigDecimal b = new BigDecimal(Double.toString(v));    
        BigDecimal one = new BigDecimal("1");    
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();    
    }
    /**
     * @Title: mod 
     * @Description: 取模
     * @param v1
     * @param v2
     * @return 参数说明
     * @return int    返回类型
     */
    public static int mod(double v1,double v2){
    	BigDecimal bd_v1 = BigDecimal.valueOf (v2);
        BigDecimal bd_v2 = BigDecimal.valueOf (v1);
        BigDecimal bigDecimal  = bd_v2.divideAndRemainder (bd_v1)[1];
        return bigDecimal.intValue();
    }

    /**
	 * 
	 * @Title: sub 
	 * @Description:减法运算   a-b-c 
	 * @param a
	 * @param b
	 * @param c
	 * @return 参数说明
	 * @return double    返回类型
	 */
    public static double subAll(double a, double b, double c) {
		return BigDecimalUtil.sub(BigDecimalUtil.sub(a, b), c);
	}
}
