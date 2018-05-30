package com.deyi;

import java.io.IOException;

import static com.deyi.Constants.*;

/**
 * @program: txtfilter
 * @description: 程序入口
 * @author: Deyi
 * @create: 2018-05-30 16:58
 **/
public class StartApplication {
    public static void main(String[] args) {
        MergeFile.mergeFiles(OUTFILE_PATH, INPUTFILE_PATH);
        try {
            FormatTxt.formatTxt();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
