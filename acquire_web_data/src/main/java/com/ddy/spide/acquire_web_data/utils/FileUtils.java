package com.ddy.spide.acquire_web_data.utils;

import java.io.*;

public class FileUtils {

    private static final String ROOT_NAME ="data/";
    /**
     * @param parentName 根路径下data下的文件名
     * @param fileName 自己所在文件的名称
     * @param content 要往文件里写入的内容
     * 往文件里添加内容
     * */
    public static void appendWtiteRootFile(String parentName, String fileName, String content) throws IOException {
        parentName = ROOT_NAME + parentName;
        File file = new File(parentName);
        file.mkdirs();
        fileName = parentName + "/" + fileName;
        File subFile = new File(fileName);
        String  oldStr=getStringByFile(subFile);
        FileOutputStream fop = new FileOutputStream(subFile);
        // 构建FileOutputStream对象,文件不存在会自动新建
        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
        // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk
        writer.append(oldStr);
        writer.append("\r\n");
        writer.append(content);
        // 写入到缓冲区
        writer.append("\r\n");
        // 换行
        writer.close();
        // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
        fop.close();
        // 关闭输出流,释放系统资源
    }

    /**
     * @param parentName 根路径下data下的文件名
     * @param fileName 自己所在文件的名称
     * 读取文件里的内容
     * */
    public static String readRootFile(String parentName, String fileName) throws IOException {
        parentName = ROOT_NAME + parentName;
        File file = new File(parentName);
        file.mkdirs();
        fileName = parentName + "/" + fileName;
        File subFile = new File(fileName);
        return getStringByFile(subFile);
    }

    /**
     * @param parentName 根路径下data下的文件名
     * @param fileName 自己所在文件的名称
     * @param content 要往文件里写入的内容
     * 覆盖文件里的内容
     * */
    public static void wtiteRootFile(String parentName, String fileName, String content) throws IOException {
        parentName = ROOT_NAME + parentName;
        File file = new File(parentName);
        file.mkdirs();
        fileName = parentName + "/" + fileName;
        File subFile = new File(fileName);
        FileOutputStream fop = new FileOutputStream(subFile);
        // 构建FileOutputStream对象,文件不存在会自动新建
        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
        // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk
        writer.append(content);
        // 写入到缓冲区
        // 换行
        writer.close();
        // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
        fop.close();
        // 关闭输出流,释放系统资源
    }

    /**
     * 通过名称获取文件名字的数据
     * */
    public static String getStringByFileName(String fileStr) throws IOException {
        File file=new File(fileStr);
        return getStringByFile(file);
    }

    /**
     * 通过文件获取字符串
     * */
    public static String getStringByFile(File file) throws IOException {
        FileInputStream fip = new FileInputStream(file);
        // 构建FileInputStream对象
        InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
        // 构建InputStreamReader对象,编码与写入相同
        StringBuffer sb = new StringBuffer();
        while (reader.ready()) {
            sb.append((char) reader.read());
        }
        reader.close();
        return sb.toString();
    }

    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        try {
            appendWtiteRootFile("test1", "helloDdy1.data", "hello");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test1()throws IOException{
        
    }

}
