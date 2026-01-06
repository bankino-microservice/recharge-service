package com.marouane.rechargeservice.utils;

public class PhoneUtils {

    public static String normalize(String phone) {
        if (phone.startsWith("0")) {
            return "+212" + phone.substring(1);
        }
        if (phone.startsWith("212")) {
            return "+" + phone;
        }
        return phone;
    }
}
