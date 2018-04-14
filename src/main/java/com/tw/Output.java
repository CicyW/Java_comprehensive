package com.tw;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Output {

    public String tipsForAddStudent = "请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：";

    public String falseInputForAddStudent = "请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：";

    public String tipsForGetReportCard = "请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：";

    public String falseInputForGetReportCard = "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：";

    public void outPutMainPage(){
        System.out.print("1. 添加学生\n");
        System.out.print("2. 生成成绩单\n");
        System.out.print("3. 退出\n");
    }

    public void outPutReportCard(List<Student> students){
        System.out.print("成绩单\n");
        System.out.print("========================\n");
        List<String> coursenames = new ArrayList<String>();
        List<Double> sumscores = new ArrayList<Double>();
        if(students.size()!=0){
            System.out.print("姓名|");
            for (String key : students.get(0).getCourse().keySet()) {
                coursenames.add(key);
                System.out.print(key+"|");
            }
            System.out.print("平均分|总分\n");
        }
        for(Student student:students){
            System.out.print(student.getName()+"|");
            Double sumscore = 0.0;
            for(String course:coursenames){
                System.out.print(student.getCourse().get(course)+"|");
                sumscore = sumscore + student.getCourse().get(course);
            }
            System.out.print(sumscore/coursenames.size()+"|"+sumscore+"\n");
            sumscores.add(sumscore);
        }
        System.out.print("========================\n");
        System.out.print("全班总分平均数："+getAverageScore(sumscores)+"\n");
        System.out.print("全班总分中位数："+getCenterScore( sumscores)+"\n\n");
    }

    public Double getAverageScore(List<Double> sumscores){
        Double sum = 0.0;
        for(Double sumscore:sumscores){
            sum = sum + sumscore;
        }
        return sum/sumscores.size();
    }

    public Double getCenterScore(List<Double> sumscores){
        Collections.sort(sumscores);
        if (sumscores.size()%2==0)
            return (sumscores.get(sumscores.size()/2-1)+sumscores.get(sumscores.size()/2))/2;
        else
            return sumscores.get(sumscores.size()/2);
    }
}
