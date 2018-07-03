package com.deyi;

import java.io.*;
import java.util.Random;

import static com.deyi.Constants.*;

/**
 * @program: txtfilter
 * @description: 按照指定规则格式化文本
 * @author: Deyi
 * @create: 2018-05-30 17:27
 **/
public class FormatTxt {
    public static String[] list = new String[1024]; //保存视频名称
    public static Integer[] num = new Integer[1024];    //保存每个视频的播放次数

    public static void formatTxt() throws IOException{
        int i = 0;
        int j;
        int flag = 0;   //标记，当list[]不存在当前截取的视频名称时，flag为1，将按顺序将视频名称存入数组
        String lineBuffer; //用于缓存txt每行的数据
        String videoName;   //保存截取出的视频名称
        FileReader fr = new FileReader(new File(FR_PATH));  //读取合并后的文件
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter(new File(FW_PATH));  //生成格式化后的文件
        FileWriter count = new FileWriter(new File(COUNT_PATH));    //生成最后的统计文件
        while ((lineBuffer = br.readLine())!= null){
            if(lineBuffer.contains(KEYWORD)){
                videoName = lineBuffer.substring(87);
                System.out.println(videoName);
                fw.write(videoName + "\n");

                /*
                遍历字符串数组，先判断每个元素是否保存了视频名称，如果都没有，
                则将当前行截取的视频名称按i递增保存在数组list[]中，如果某个元素
                已经保存了视频名称，则将计数num[]加一并跳出循环
                */
                for (int k=0;k<1024;k++){

                    /*初始化数组*/
                    if(list[k] == null){
                        list[k] = "x";
                    }
                    if(num[k] == null){
                        num[k] = 0;
                        //Random rand = new Random();
                        //num[k] = rand.nextInt(20);
                    }

                    if((list[k]).contains(videoName)){
                        num[k] += 1;
                        flag = 0;
                        break;
                    }
                    else{
                        flag = 1;
                    }
                }
                if(flag == 1) {
                    list[i] = videoName;
                    num[i] += 1;
                    i = i + 1;
                }
            }
        }
        for(j = 0;j < 1024;j++)
            count.write(list[j] + "\t" + num[j] + "\n");
        br.close();
        fr.close();
        fw.flush();
        fw.close();
        count.flush();
        count.close();
    }

}
