/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

/**
 *
 * @author Lenovo
 */
public class Session {
    private static String id; 
    private static String id2;
    private static String id3;
    private static String id4;
    private static String id5;
    private static String id_user;
    private static String akses;
    
    public static void setId_user(String id_user){
        Session.id_user=id_user;
    }
    public static String getId_user(){
        return id_user;
    }
    public static void setAkses(String akses){
        Session.akses=akses;
    }
    public static String getAkses(){
        return akses;
    }
    
    
    public static String getId(){
        return id;
    }
    public static void setId(String id){
        Session.id=id;
    }
    public static String getId2(){
        return id2;
    }
    public static void setId2(String id2){
        Session.id2=id2;
    }
    public static String getId3(){
        return id3;
    }
    public static void setId3(String id3){
        Session.id3=id3;
    }
    public static String getId4(){
        return id4;
    }
    public static void setId4(String id4){
        Session.id4=id4;
    }
    public static String getId5(){
        return id5;
    }
    public static void setId5(String id5){
        Session.id5=id5;
    }

}

