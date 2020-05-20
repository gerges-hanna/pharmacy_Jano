/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.v3.pkg0;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 *
 * @author gerges Fci-H
 */
public class Mac {
    
    public String getMac() throws Exception
    {
        InetAddress adress=InetAddress.getLocalHost();        
        NetworkInterface ni=NetworkInterface.getByInetAddress(adress);
        byte[] mac= ni.getHardwareAddress();
        String m=null;        
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02x%s", mac[i],(i<mac.length-1)?"-":""));
        }
        return sb.toString();
    }
}
