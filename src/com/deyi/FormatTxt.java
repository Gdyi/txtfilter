package com.deyi;

import java.io.*;

import static com.deyi.Constants.*;

/**
 * @program: txtfilter
 * @description: 按照指定规则格式化文本
 * @author: Deyi
 * @create: 2018-05-30 17:27
 **/
public class FormatTxt {
    public static String[] list = new String[1024];
    public static Integer[] num = new Integer[1024];

    public static void formatTxt() throws IOException{
        int i =0;
        int j=0;
        int flag = 0;
        String tag;
        FileReader fr = new FileReader(new File(FR_PATH));
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter(new File(FW_PATH));
        FileWriter count = new FileWriter(new File(COUNT_PATH));
        while ((tag = br.readLine())!= null){
            if(tag.contains(KEYWORD)){
                tag = tag.substring(87);
                System.out.println(tag);
                fw.write(tag + "\n");
                for (int k=0;k<1024;k++){
                    if(list[k] == null){
                        list[k] = "x";
                    }
                    if(num[k] == null){
                        num[k] = 0;
                    }
                    if((list[k]).contains(tag)){
                        num[k] +=1;
                        flag = 0;
                        break;
                    }
                    else{
                        flag = 1;
                    }
                }
                if(flag == 1) {
                    list[i] = tag;
                    i = i + 1;
                }
            }
        }
        for(j=0;j<1024;j++)
            count.write(list[j] + "\t" + num[j] + "\n");
        br.close();
        fr.close();
        fw.flush();
        fw.close();
        count.flush();
        count.close();
    }

}
