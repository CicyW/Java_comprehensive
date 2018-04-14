package com.tw;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class LibraryTest {

    @Mock
    private Input mockInput;

    @Mock
    private Output mockOutput;

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public Student getstudent1(){
        Map<String, Double> map = new HashMap<String, Double>();
        Student student = new Student("张三","201801",map);
        map.put("语文",90.0);
        map.put("数学",80.0);
        return student;
    }

    public Student getstudent2(){
        Map<String, Double> map = new HashMap<String, Double>();
        Student student = new Student("李四","201802",map);

        map.put("语文",90.0);
        map.put("数学",80.0);
        return student;
    }

  //  @Test
  //  public void testSomeLibraryMethod() {
  //      Library classUnderTest = new Library();
  //      assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());

    @Test
    public void should_input_is_digit() throws Exception {
        Mockito.when(mockInput.readInput()).thenReturn("1A");
        Library library = new Library(mockInput,mockOutput,new ArrayList<Student>());
        assertThat(library.funcSelectCheck("1A")).isEqualTo(false);

    }

    @Test
    public void should_select_exit() throws Exception {
        Mockito.when(mockInput.readInput()).thenReturn("3");

        Library library = new Library(mockInput,mockOutput,new ArrayList<Student>());
        library.func();
        assertThat(library.funcSelectCheck("3")).isEqualTo(true);
        Mockito.verify(mockOutput,Mockito.times(1)).outPutMainPage();
    }

    @Test
    public void should_student_infor_not_format() throws Exception {

        Library library = new Library(mockInput,mockOutput,new ArrayList<Student>());
        assertThat(library.studentInforFormatCheck("张三，201801")).isEqualTo(false);

    }

    @Test
    public void should_student_infor_is_format() throws Exception {

        Library library = new Library(mockInput,mockOutput,new ArrayList<Student>());
        assertThat(library.studentInforFormatCheck("张三，201801，语文：100，数学：80")).isEqualTo(true);

    }

    @Test
    public void should_add_one_student_fail() throws Exception {

        Library library = new Library(mockInput,mockOutput,new ArrayList<Student>());
        assertThat(library.addOneStudent("张三，201801，语文：100，数学80")).isEqualTo(false);
    }

    @Test
    public void should_add_one_student_success() throws Exception {

        Library library = new Library(mockInput,mockOutput,new ArrayList<Student>());
        assertThat(library.addOneStudent("张三，201801，语文：100，数学：80")).isEqualTo(true);
    }

    @Test
    public void should_add_one_student_isequl() throws Exception {

        Library library = new Library(mockInput,mockOutput,new ArrayList<Student>());
        library.addOneStudent("张三，201801，语文：90，数学：80");

        assertThat(library.students.get(0)).isEqualTo(getstudent1());
    }

    @Test
    public void should_add_one_student() throws Exception {
        Mockito.when(mockInput.readInput()).thenReturn("张三，2019，语文：100，数学：80");
        Library library = new Library(mockInput,mockOutput,new ArrayList<Student>());
        library.addStudentFunc();
        assertThat(library.studentInforFormatCheck("张三，2019，语文：100，数学：80")).isEqualTo(true);
        assertThat(library.addOneStudent("张三，2019，语文：100，数学：80")).isEqualTo(true);
    }

    @Test
    public void should_student_number_is_format() throws Exception {

        Library library = new Library(mockInput,mockOutput,new ArrayList<Student>());
        String[] numbers= {"201801","201802"};
        assertThat(library.numbersFormatCheck("201801,201802")).isEqualTo(numbers);

    }

    @Test
    public void should_find_student_by_number() throws Exception {

        List<Student> students = new ArrayList<Student>();
        students.add(getstudent1());
        students.add(getstudent2());

        Mockito.when(mockInput.readInput()).thenReturn("201801");

        Library library = new Library(mockInput,mockOutput,students);

        assertThat(library.findStudent(students,"201801")).isEqualTo(getstudent1());
        assertThat(library.findStudent(students,"201802")).isEqualTo(getstudent2());
    }


}
