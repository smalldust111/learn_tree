package com.me.practice.t0009_javabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author jia
 * @since 2020/7/5 17:35
 */
public class 文件操作Api {
    public static void main(String[] args) {
        // 1.创建文件夹
        createDir();
        // 2.创建文件
        createFile();
        // 3.删除文件夹
        deleteDir();
        // 4.删除文件
        deleteFile();
        // 5.删除一个文件夹下所有的文件夹
        deleteUnderDir();
        // 6.清空文件夹
        clearDir();
        // 7.读取文件
        readFile();
        // 8.写入文件
        writeFile();
        // 9.写入随机文件
        writeRandomFile();
        // 10.读取文件属性
        getFileInfo();
        // 11.写入属性
        setReadOnly();
        // 12.枚举一个文件夹中的所有文件
        String filename = getDirAllFile();
        // 13.提取扩展名
        String str20 = filename.substring(filename.lastIndexOf(".") + 1);
    }

    private static String getDirAllFile() {
        String filename = "test.txt";
        LinkedList<String> folderList = new LinkedList<>();
        while (folderList.size() > 0) {
            File file = new File(folderList.peek());
            folderList.removeLast();
            File[] files = file.listFiles();
            ArrayList<File> fileList = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    folderList.add(files[i].getPath());
                } else {
                    fileList.add(files[i]);
                }
            }
            for (File f : fileList) {
                folderList.add(f.getAbsolutePath());
            }
        }
        return filename;
    }

    private static void setReadOnly() {
        String filename = "test.txt";
        File filereadonly = new File(filename);
        try {
            boolean b = filereadonly.setReadOnly();
        } catch (Exception e) {
            System.out.println("拒绝写访问：" + e.getMessage());
        }
    }

    private static void getFileInfo() {
        // 文件属性的取得
        String filename = "test.txt";
        File f = new File(filename);
        if (f.exists()) {
            System.out.println(f.getName() + "的属性如下： 文件长度为：" + f.length());
            System.out.println(f.isFile() ? "是文件" : "不是文件");
            System.out.println(f.isDirectory() ? "是目录" : "不是目录");
            System.out.println(f.canRead() ? "可读取" : "不");
            System.out.println(f.canWrite() ? "是隐藏文件" : "");
            System.out.println("文件夹的最后修改日期为：" + new Date(f.lastModified()));
        }
    }

    private static void writeRandomFile() {
        String filename = "test.txt";
        long pos = 0;
        int size = 2048;
        try {
            RandomAccessFile logFile = new RandomAccessFile(filename, "rw");
            long lg = logFile.length();
            logFile.seek(pos);
            logFile.writeByte(size);
        } catch (IOException ioe) {
            System.out.println("无法写入文件：" + ioe.getMessage());
        }
    }

    private static void writeFile() {
        // 将数据写入文件
        String filenameOrigin = "test2";
        String filename = "test";
        try {
            FileWriter fw = new FileWriter(filenameOrigin);
            fw.write(filename);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearDir() {
        String dirname = "test";
        File delfilefolder = new File(dirname);
        try {
            if (delfilefolder.exists()) {
                delfilefolder.delete();
            }
            delfilefolder.mkdir();
        } catch (Exception e) {
            System.out.println("清空目录操作出错");
            e.printStackTrace();
        }
    }

    private static void deleteUnderDir() {
        String dirname = "test";
        File delfile = new File(dirname);
        File[] files = delfile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                files[i].delete();
            }
        }
    }

    private static void deleteFile() {
        String filename = "test.txt";
        File myDelFile = new File(filename);
        try {
            myDelFile.delete();
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
    }

    private static void deleteDir() {
        String dirname = "test";
        File delFolderPath = new File(dirname);
        try {
            delFolderPath.delete(); //删除空文件夹
        } catch (Exception e) {
            System.out.println("删除文件夹操作出错");
            e.printStackTrace();
        }
    }

    private static void createFile() {
        String filename = "test.txt";
        String str2_1 = "";
        File myFilePath = new File(filename);
        try {
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            myFile.println(str2_1);
            resultFile.close();
        } catch (Exception e) {
            System.out.println("新建文件操作出错");
            e.printStackTrace();
        }
    }

    private static void createDir() {
        String dirname = "test";
        File myFolderPath = new File(dirname);
        try {
            if (!myFolderPath.exists()) {
                myFolderPath.mkdir();
            }
        } catch (Exception e) {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
    }

    private static void readFile() {
        // 逐行读取数据
        String filename = "test.txt";
        FileReader fr = null;
        BufferedReader br = null;
        String str2 = null;
        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            str2 = br.readLine();
            while (str2 != null) {
                str2 = br.readLine();
                System.out.println(str2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
