/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andikscript.findlocationdevice;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author akunf
 */
public class Location {
    public static void main(String[] args) throws IOException, InterruptedException {
        Important i = new Important();
        
        SimpleDateFormat format = new SimpleDateFormat("E_yyyy-MM-dd_hh-mm-ss_a");
        boolean x = true;
        
        while(x){
            Date date = new Date();
            i.tanggal = format.format(date);
            
            i.cekKoneksi();
            //satu menit = 60000
            Thread.sleep(60 * 1000);
        }
    }
}
