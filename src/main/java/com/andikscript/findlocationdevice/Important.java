/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andikscript.findlocationdevice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Enumeration;

/**
 *
 * @author akunf
 */
public class Important {
    private String konek;
    String ipPublic;
    String ipLocal;
    String hostname;
    String netIp;
    int i = 0;
    String tanggal;
    
    //class StringBuffer
    StringBuffer a = new StringBuffer();
    
    void cekKoneksi() throws IOException{
        cekInternet();
        
        if (konek.equals("1")) {
            System.out.println("Internet Connected");
            cekIpPublic();
            cekIpLocal();
            tampilkanIpInterface();
        } else {
            System.out.println("Internet Not Connected");
        }
    }
    
    private void cekInternet(){
        try {
            URL url = new URL("https://google.com");
            URLConnection con = url.openConnection();
            con.connect();
            konek = "1";
        } catch (Exception e) {
            konek = "0";
        }
    }
        
    private void cekIpPublic() throws MalformedURLException, IOException{
        URL url = new URL("http://checkip.amazonaws.com");
        BufferedReader a = new BufferedReader(new InputStreamReader(url.openStream()));
        String ip = a.readLine();
        ipPublic = "IP Publik : "+ip;
    }
    
    private void cekIpLocal(){
        try {
            InetAddress local = InetAddress.getLocalHost();
            hostname = "Nama Komputer : "+local.getHostName();
            ipLocal = "IP Lokal : "+local.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void showIpInterface(NetworkInterface net){
        //merkInterface.append(net.getDisplayName()+ ", ");
        //namaInterface.append(net.getName()+ ", ");
        Enumeration<InetAddress> ip = net.getInetAddresses();
        for (InetAddress i : Collections.list(ip)) {
            //ipAddress.append(i +", ");
            a.append(net.getName() + " : " + i + " (Interface : " +net.getDisplayName() + ") , \n");
        }
    }
    
    private void tampilkanIpInterface() throws SocketException, IOException{
        Enumeration<NetworkInterface> net = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface j : Collections.list(net)) {
            showIpInterface(j);
        }
        
        netIp = String.valueOf(a);
        
        
        FileWriter bw = new FileWriter("c:\\lokasi\\"+tanggal+".txt");
        BufferedWriter file = new BufferedWriter(bw);
        
        file.write(ipPublic + "\n");
        file.newLine();
        file.write(hostname+ "\n");
        file.newLine();
        file.write(ipLocal+ "\n");
        file.newLine();
        file.write(netIp);
        file.close();
        
        a.delete(0, 900);
        i++;
    }
}
