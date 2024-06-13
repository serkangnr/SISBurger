package com.serkanguner.constant;

public class EndPoints {
    public static final String VERSION="/v1";
    //profiller:
    public static final String API="/api";
    public static final String DEV="/dev";
    public static final String TEST="/test";

    public static final String ROOT=API+VERSION;


    //entities:
    public static final String ADMIN=ROOT+"/admin";
    public static final String ADDRESS =ROOT+"/address" ;
    public static final String USER =ROOT+"/user";
    public static final String BURGER =ROOT+"/burger" ;
    public static final String PATATES =ROOT+"/patates";
    public static final String SNACK =ROOT+"/snack";
    public static final String CATEGORIES =ROOT+"/categories"; ;
    public static final String MENUS =ROOT+"/menus" ;
    public static final String MENUITEM =ROOT+"/menuitem" ;
    public static final String COMPONENT =ROOT+"/component" ;
    public static final String OPTIONS =ROOT+"/options" ;
    public static final String ORDER = ROOT+"/order";


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


    public static final String CATEGORIESLIST ="/categorieslist" ;
    public static final String MENUSLIST = "/menuslist";
    public static final String URUNSEC ="/urunsec" ;
    public static final String URUNLISTELE ="/urunlistele" ;
}
