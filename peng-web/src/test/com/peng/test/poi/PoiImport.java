package com.peng.test.poi;


import com.peng.demo.modal.Student;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * poi 导入导出
 * @Auther: daipeng
 * @Date: 2019/5/21 14:40
 * @Description:
 *
 *         //5、关闭流
 *         workbook.close();
 */
public class PoiImport {

    //导入
    @Test
    public void importExcel(){
        XSSFWorkbook workbook = null;
        try {
            //1、获取文件输入流
            InputStream inputStream = new FileInputStream("/Users/apple/Desktop/资产统计.xlsx");
            //2、获取Excel工作簿对象
            //2007版本（包含2007）以后的扩展名为.xlsx需要用XSSFWorkbook类操作
            workbook = new XSSFWorkbook(inputStream);
            //2003版本（包含2003）以前的扩展名为.xls需要用HSSFWorkbook类操作
            //HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            //3、得到Excel工作表 对象 工作表从0开始
            XSSFSheet sheet = workbook.getSheetAt(0);
            //4、循环读取表格数据
            for (Row row : sheet) {
                //首行（即表头）不读取
                 if (row.getRowNum() == 0) {
                    continue;
                 }
                 //读取当前行中单元格数据，索引从0开始
                 //班级名称
                double className = row.getCell(0).getNumericCellValue();
                //使用学校电脑个数
                double num = row.getCell(1).getNumericCellValue();
                 //姓名
                 String userName = row.getCell(2).getStringCellValue();
                 //插排
                double chaPai = row.getCell(3).getNumericCellValue();
                 //交换机
                double jhj = row.getCell(4).getNumericCellValue();

//                System.out.println(className);

            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            //5、关闭流
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //导出
    public void exportExcel(HttpServletRequest request,HttpServletResponse response){

        //查询数据库得到学生信息
        List<Student> studentList = new ArrayList<Student>();
        Student student = new Student();
        student.setStuId(1);
        student.setStuAge(11);
        student.setStuDate(new Date());
        student.setStuName("张三");
        Student student1 = new Student();
        student.setStuId(2);
        student1.setStuAge(11);
        student1.setStuDate(new Date());
        student1.setStuName("李四");
        studentList.add(student1);



        //1.在内存中创建一个excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //2.创建工作簿
        HSSFSheet sheet = hssfWorkbook.createSheet();
        //3.创建标题行
        HSSFRow titlerRow = sheet.createRow(0);
        titlerRow.createCell(0).setCellValue("id");
        titlerRow.createCell(1).setCellValue("姓名");
        titlerRow.createCell(2).setCellValue("年龄");
        titlerRow.createCell(3).setCellValue("生日");

        //4.遍历数据,创建数据行
        for (Student stu : studentList) {
            //获取最后一行的行号
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(stu.getStuId());
            dataRow.createCell(1).setCellValue(stu.getStuName());
            dataRow.createCell(2).setCellValue(stu.getStuAge());
            dataRow.createCell(3).setCellValue(stu.getStuDate());
        }
        //5.创建文件名
        String fileName = "学生信息列表.xls";
        //6.获取输出流对象
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();


            //7.获取mimeType
            ServletContext servletContext = request.getServletContext();
            String mimeType = servletContext.getMimeType(fileName);
    //        8.获取浏览器信息,对文件名进行重新编码
    //        fileName = filenameEncoding(fileName, request);

            //9.设置信息头
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition","attachment;filename="+fileName);
            //10.写出文件,关闭流
            hssfWorkbook.write(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                hssfWorkbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
