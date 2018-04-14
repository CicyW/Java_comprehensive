package com.tw;

import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import static org.fest.assertions.api.Assertions.assertThat;

public class StudentTest {

    @Test
    public void should_student_have_name_number_and_course() throws Exception {
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("语文",90.0);
        Student student = new Student("张三", "201801", map);
        assertThat(student.getName()).isEqualTo("张三");
        assertThat(student.getNumber()).isEqualTo("201801");
        assertThat(student.getCourse()).isEqualTo(map);
    }

    public void should_student_have_name_number_and_courses() throws Exception {
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("语文",90.0);
        map.put("数学",80.0);
        Student student = new Student("张三", "201801", map);
        assertThat(student.getName()).isEqualTo("张三");
        assertThat(student.getNumber()).isEqualTo("201801");
        assertThat(student.getCourse()).isEqualTo(map);
    }
}
