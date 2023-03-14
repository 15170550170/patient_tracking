package com.liyouling.patient_track.common;

/**
 * @title: NoLoginException
 * @Author LiYouling
 * @Date: 2023/3/12 9:50
 * @Version 1.0
 */
public class NoLoginException extends RuntimeException {
    public NoLoginException (){

    }

    public NoLoginException (String message) {
        super(message);
    }

    /*
    * 抛出一个异常
    * */
    public static void fail(String message) {
        throw new NoLoginException(message);
    }
}
