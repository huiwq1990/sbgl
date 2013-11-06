package com.sbgl.util;
import java.io.File;
import java.io.FileFilter;
public class FileUtils
{
   public static void FileFilter(String filePath, final String suffix)
   {
    File file = new File(filePath);
    File [] fileList = file.listFiles(new FileFilter()
    {
     public boolean accept(File file)
     {
      return file.getName().endsWith(suffix);
     }
    }
    );
    for(File files : fileList)
    {
     System.out.println(files.getName());
    }
   }
 
 /**
  * @param args
  */
 public static void main(String[] args)
 {
  String filePath = "H:\\fsphyopfile\\image";
  final String suffix = ".jpg";
  FileUtils.FileFilter(filePath, suffix);
 }
}