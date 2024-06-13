package com.serkanguner.constant;

public class EndPoints {
    public static final String VERSION="/v1";
    //profiller:
    public static final String API="/api";
    public static final String DEV="/dev";
    public static final String TEST="/test";

    public static final String ROOT=API+VERSION;


    //entities:
    public static final String CART=ROOT+"/cart";
    public static final String CONFIRMATION ="confirmation" ;


    //methods:
    public static final String REGISTER="/register";
    public static final String SAVE="/save";
    public static final String UPDATE="/update";
    public static final String DELETE="/delete";
    public static final String SOFTDELETE="/softdelete";
    public static final String FINDALL="/findall";
    public static final String FINDBYID="/findbyid";

    public static final String LOGIN = "/login";
    public static final String ACTIVATEACCOUNT = "/activateaccount";

    public static final String CONFIRMATIONBYUSERID ="/confirmationbyuserid";
    public static final String CONFIRMATIONDTO = "/confirmationdto";
    ;
}
