package practice.scanner_sample;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import practice.scanner_sample.annotation.ReflectionPractice;
import practice.scanner_sample.annotation.scanner.ReflectionPracticeScanner;
import practice.scanner_sample.controller.ReflectionController;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class AnnotationTest {

    @Autowired
    ReflectionPracticeScanner reflectionPracticeScanner;

    @Test
    void checkMapData() {
        Map<String, Method> methodMap = reflectionPracticeScanner.getMethodMap();
        int methodCount = (int) Arrays.stream(ReflectionController.class.getMethods())
                .filter(method -> method.getDeclaredAnnotation(ReflectionPractice.class) != null)
                .count();
        int methodMapSize = methodMap.size();

//        methodMap.forEach((k,v) -> log.info("key = {}, value = {}", k, v.getName()));
        assertThat(methodCount).isEqualTo(methodMapSize);

    }
}
