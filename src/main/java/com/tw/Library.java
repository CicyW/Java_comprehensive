package com.tw;

import java.util.*;

public class Library {

    public Input input;
    public Output output;
    public List<Student> students;

    public Library(Input input,Output output,List<Student> students){
        this.input = input;
        this.output = output;
        this.students =students;
    }
    public  boolean addStudentFunc()
    {
        System.out.print(output.tipsForAddStudent+"\n");
        String studentStr = input.readInput();
        while(!studentInforFormatCheck(studentStr)){
            System.out.print(output.falseInputForAddStudent+"\n");
            studentStr = input.readInput();
        }
        return addOneStudent(studentStr);
    }
    public  boolean studentInforFormatCheck(String studentStr){
        try{
            String[] student = studentStr.split("，");
            if(student.length>=3){
                for(int i=2;i<student.length;i++){
                    String course = student[i];
                    if (course.indexOf("：")!=-1) {
                        if(!isDouble(course.substring(course.indexOf("：") + 1, course.length())))
                            return false;
                    }else
                        return false;
                }
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
    public boolean isDouble(String score){
        try
        {
            Double dScore = Double.parseDouble(score);
            if (dScore instanceof Double == false)
                return false;
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    public boolean addOneStudent(String studentStr){
        try{
            String[] studentInfor = studentStr.split("，");
            String name = studentInfor[0];
            String number = studentInfor[1];
            Map<String,Double> course = new HashMap<String,Double>();
            for(int i=2;i<studentInfor.length;i++){
                String str = studentInfor[i];
                course.put(str.substring(0,str.indexOf("：")),Double.parseDouble(str.substring(str.indexOf("：")+1,str.length())));
            }
            students.add(new Student(name,number,course));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public  boolean getReportCard(){
        System.out.println(output.tipsForGetReportCard);
        String numbersStr = input.readInput();
        List<Student> studentList = new ArrayList<Student>();
        while(numbersFormatCheck(numbersStr)==null){
            System.out.println(output.falseInputForAddStudent);
            numbersStr = input.readInput();
        }
        for(String number:numbersFormatCheck(numbersStr)){
            if(findStudent (students, number)!=null)
                studentList.add(findStudent (students, number));
        }
        output.outPutReportCard(studentList);
        return true;
    }

    public Student findStudent (List<Student> students, String number){
        for(Student student:students){
            if(student.number.equals(number))
                return student;
        }
        return null;
    }

    public  String[] numbersFormatCheck(String numbersStr){
        try{
            return numbersStr.split(",");
        }catch (Exception e){
            return null;
        }
    }

    public boolean funcSelectCheck(String input){
            return input.matches("[0-9]{1,}");
    }

    public  void func(){
        boolean quit = false;
        while (!quit){
            output.outPutMainPage();
            String str = input.readInput();
            if (funcSelectCheck(str)) {
                Integer type = Integer.parseInt(str);
                if (type == 3) {
                   quit = true;
                } else if (type == 1) {
                    addStudentFunc();
                } else if (type == 2) {
                    getReportCard();
                }
            }
       }
    }
}
