/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.v3.pkg0;

/**
 *
 * @author gerges Fci-H
 */
public class logInfo {
    
    private static String name;
    private static String type;
    private static int flag=0;

    public static int getFlag() {
        return flag;
    }

    public static void setFlag(int flag) {
        logInfo.flag = flag;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        logInfo.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
