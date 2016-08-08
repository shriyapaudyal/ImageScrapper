/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shriya.ImageScrapper;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author shneha
 */
public class program {

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
       try {
            String link = "https://www.dreamstime.com/photos-images/model.html";
            URL url = new URL(link);
            // URLEncoder.encode(link,"UTF-8");
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));//, Charset.forName("UTF-8")
            String line = "";
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line);

            }
            reader.close();
            String regex ="<a href=\\\"(.*?)\\\" title=\\\"(.*?)\\\"><span><img id=\\\"(.*?)\\\" src=\\\"(.*?)\\\" alt=\\\"(.*?)\\\" title=\\\"(.*?)\\\" /></span><span id=\\\"(.*?)\\\">";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content.toString());
            while (matcher.find()) {
                String imageLink = matcher.group(4);
                URL imageUrl = new URL(imageLink);
                URLConnection conn1 = imageUrl.openConnection();
                InputStream is = conn1.getInputStream();

                String[] tokens = imageLink.split("/");
                String path = "C:\\Users\\shneha\\Desktop\\output\\";
                FileOutputStream os = new FileOutputStream(path + tokens[tokens.length - 1]);
                byte[] data = new byte[1024];
                int i = 0;
                while ((i = is.read(data)) != -1) {
                    os.write(data, 0, i);
                }
                os.close();
                is.close();
            }
        } catch (IOException ioe) {

            System.out.println(ioe.getMessage());
        }

    }
}