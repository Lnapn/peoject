package com.javaweb.bean;

public class Sel {

    public static final String[] NAME = {"Family","Lnapn","Lnaje"};
    public static final String[] TYPE = {"Éú»î","ÓéÀÖ","¶ÍÁ¶","Ó¦³ê"};

    private Sel() {
    }

    public static boolean judge_Name(String target){
        for (int i = 0; i < NAME.length; i++) {
            if (NAME[i].equals(target)){
                return true;
            }
        }
        return false;
    }

    public static boolean judge_Type(String target){
        for (int i = 0; i < TYPE.length; i++){
            if (TYPE[i].equals(target)){
                return true;
            }
        }
        return false;
    }

}
