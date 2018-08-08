package com.tositteach.service.impl;

import com.tositteach.domain.mapper.StudentMapper;
import com.tositteach.domain.entity.Student;
import com.tositteach.service.StudentService;
import com.tositteach.util.PagingBody;
import com.tositteach.util.YearIdBuilder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public PagingBody query(String claId, int st, int nm) {
        PagingBody body = new PagingBody();
        body.setTotal(studentMapper.total(claId));
        body.setData(studentMapper.query(claId, st, nm));
        return body;
    }

    /*@Override
    public Student get(String stuId) {
        return studentMapper.get(stuId);
    }*/

    @Override
    public int add(String school, String id,
                   String name, byte sex, int grade) {
        Student stu = new Student();
        stu.setSchool(school);
        stu.setId(id);
        stu.setName(name);
        stu.setSex(sex);
        stu.setGrade(grade2Year(grade));
        synchronized (this) {
            stu.setUserId(YearIdBuilder.build(studentMapper.getMaxId(), "01", "0000000"));
            return studentMapper.add(stu);
        }
    }

    private String grade2Year(int grade) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        return "" + (month > 7 ? year - grade : year - grade - 1);
    }

    @Override
    public int batch(CommonsMultipartFile file) {
        int num = 0;
        try {
            List<Student> list = parseExcel(file);
            for (Student stu : list) {
                try {
                    synchronized (this) {
                        stu.setUserId(YearIdBuilder.build(studentMapper.getMaxId(), "01", "0000000"));
                        num += studentMapper.add(stu);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return num;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private Pattern idPattern = Pattern.compile("^[0-9]+$");
    private Pattern yearPattern = Pattern.compile("^[0-9]{4}$");

    private List<Student> parseExcel(CommonsMultipartFile file) throws IOException {
        List<Student> list = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        Workbook book = file.getOriginalFilename().endsWith(".xls")
                ? new HSSFWorkbook(inputStream)
                : new XSSFWorkbook(inputStream);
        Sheet sheet = book.getSheetAt(0);
        Iterator<Row> rowIt = sheet.rowIterator();

        if (!rowIt.hasNext()) return list;
        Row firstRow = rowIt.next(); //第一行表头，指明每一列的数据：学校，学号（数字），姓名，性别（男、女、其他），年级（2016）
        int[] titleTable = new int[]{5, 5, 5, 5, 5};
        String[] titleList = new String[]{"学校", "学号", "姓名", "性别", "年级"};
        for (int i = 0; i < 5; ++i) {
            String title = firstRow.getCell(i).getStringCellValue();
            int j;
            for (j = 0; j < 5 && !titleList[j].equals(title); ++j) ;
            if (j == 5) return list;
            if (titleTable[j] != 5) return list;
            titleTable[j] = i;
        }

        Row row;
        String school, id, name, sx, grade;
        byte sex;
        boolean nonNull;
        Cell cell;
        while (rowIt.hasNext()) {
            Student student = new Student();
            row = rowIt.next();
            nonNull = true;
            for (int i = 0; i < 5 && nonNull; ++i) {
                cell = row.getCell(i);
                if (cell != null) cell.setCellType(CellType.STRING);
                else nonNull = false;
            }
            if (!nonNull) continue;
            school = row.getCell(titleTable[0]).getStringCellValue();
            id = row.getCell(titleTable[1]).getStringCellValue();
            name = row.getCell(titleTable[2]).getStringCellValue();
            sx = row.getCell(titleTable[3]).getStringCellValue();
            grade = row.getCell(titleTable[4]).getStringCellValue();
            if (school.length() == 0) continue;
            if (!idPattern.matcher(id).matches()) continue;
            if (name.length() == 0) continue;
            sex = (byte) (sx.equals("男") ? 0 : sx.equals("女") ? 1 : sx.equals("其他") ? 2 : 3);
            if (sex == 3) continue;
            if (!yearPattern.matcher(grade).matches()) continue;
            else {
                int year = Calendar.getInstance().get(Calendar.YEAR);
                int gy = Integer.parseInt(grade);
                if (gy < year - 4 || year < gy) continue;
            }
            student.setSchool(school);
            student.setId(id);
            student.setName(name);
            student.setSex(sex);
            student.setGrade(grade);
            list.add(student);
        }

        book.close();
        return list;
    }
}
