package practice.scanner_sample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import practice.scanner_sample.annotation.ReflectionPractice;
import practice.scanner_sample.annotation.scanner.ReflectionPracticeScanner;
import practice.scanner_sample.controller.ReflectionController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class AnnotationScanningTest {


    @Autowired
    private ReflectionController reflectionController;

    @Autowired
    private ReflectionPracticeScanner reflectionPracticeScanner;

    private final Class<ReflectionController> controllerClass = ReflectionController.class;
    private final Class<ReflectionPractice> annotationClass = ReflectionPractice.class;

    @Test
    void checkMapData() {
        Map<String, Method> methodMap = reflectionPracticeScanner.getMethodMap();
        int methodCount = (int) Arrays.stream(controllerClass.getMethods())
                .filter(method -> method.getDeclaredAnnotation(annotationClass) != null)
                .count();
        int methodMapSize = methodMap.size();

//        methodMap.forEach((k,v) -> log.info("key = {}, value = {}", k, v.getName()));
        assertThat(methodCount).isEqualTo(methodMapSize);

    }

    @Test
    void getMapMethod() throws InvocationTargetException, IllegalAccessException {
        Map<String, Method> methodMap = reflectionPracticeScanner.getMethodMap();

        Method method = methodMap.get("/hello");
        Long returnType = (Long) method.invoke(reflectionController);
        assertThat(returnType).isEqualTo(1L);
    }
}
