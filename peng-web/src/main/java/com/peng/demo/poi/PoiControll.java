package com.peng.demo.poi;

import com.peng.demo.modal.Student;
import com.peng.demo.utils.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.Region;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: daipeng
 * @Date: 2019/5/21 21:16
 * @Description:
 */
@Controller
@RequestMapping("poi")
public class PoiControll {

    //导出
    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response){

        //查询数据库得到学生信息
        List<Student> studentList = new ArrayList<Student>();
        Student student = new Student();
        student.setStuId(1);
        student.setStuAge(11);
        student.setStuDate(new Date());
        student.setStuName("张三");
        studentList.add(student);
        Student student1 = new Student();
        student1.setStuId(2);
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


        //得到所有区域
        sheet.getNumMergedRegions();


        //4.遍历数据,创建数据行
        for (Student stu : studentList) {
            //获取最后一行的行号
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(stu.getStuId());
            dataRow.createCell(1).setCellValue(stu.getStuName());

            if(stu.getStuId() % 2 ==0){
                //设置行高列宽  256分之一个字符宽度，所以宽度就是字符数X256
                sheet.setColumnWidth((short)1,(short)15.56*256);
                //HSSFRow.Height和HeightInPoints，这两个属性的区别在于HeightInPoints的单位是点，而Height的单位是1/20个点，所所以Height的值永远是HeightInPoints的20倍
                dataRow.setHeightInPoints((float) 20);
            }

            dataRow.createCell(2).setCellValue(stu.getStuAge());
            dataRow.createCell(3).setCellValue(stu.getStuDate());
        }


        Region region = new Region((short)1,(short)3,(short)1, (short)4);//合并从第rowFrom行columnFrom列
        sheet.addMergedRegion(region);// 到rowTo行columnTo的区域


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
            fileName = FileUtils.filenameEncoding(fileName, request);

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
