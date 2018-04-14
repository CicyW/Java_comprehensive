package com.tw;

import org.junit.Before;
import org.junit.Test;
import static org.fest.assertions.api.Assertions.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private Student student;

    public Student Student1(){
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("语文",85.5);
        map.put("数学",95.5);
        return new Student("张三", "201801", map);
    }

    public Student Student2(){
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("语文",90.0);
        map.put("数学",80.0);
        return new Student("李四", "201802", map);
    }
    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }
    @Test
    public void should_output_main_page(){
        Output output = new Output();
        output.outPutMainPage();
        assertThat(systemOut()).isEqualTo("1. 添加学生\n"+"2. 生成成绩单\n"+"3. 退出\n");
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void should_output_one_student_report_card(){
        Output output = new Output();
        List<Student> students = new ArrayList<Student>();
        students.add(Student1());
        output.outPutReportCard(students);
        String card = "成绩单\n";
        String str = "========================\n";
        String inforname = "姓名|数学|语文|平均分|总分\n";
        String infor = "张三|95.5|85.5|90.5|181.0\n";
        String sumAverageScore = "全班总分平均数：181.0\n";
        String sumMiddleScore = "全班总分中位数：181.0\n\n";
        assertThat(systemOut()).isEqualTo(card+str+inforname+infor+str+sumAverageScore+sumMiddleScore);
    }

    @Test
    public void should_output_two_student_report_card(){
        Output output = new Output();
        List<Student> students = new ArrayList<Student>();
        students.add(Student1());
        students.add(Student2());
        output.outPutReportCard(students);
        String card = "成绩单\n";
        String str = "========================\n";
        String inforname = "姓名|数学|语文|平均分|总分\n";
        String infor1 = "张三|95.5|85.5|90.5|181.0\n";
        String infor2 = "李四|80.0|90.0|85.0|170.0\n";
        String sumAverageScore = "全班总分平均数：175.5\n";
        String sumMiddleScore = "全班总分中位数：175.5\n\n";
        assertThat(systemOut()).isEqualTo(card+str+inforname+infor1+infor2+str+sumAverageScore+sumMiddleScore);
    }
}
