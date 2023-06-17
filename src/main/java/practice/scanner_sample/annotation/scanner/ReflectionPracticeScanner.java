package practice.scanner_sample.annotation.scanner;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import practice.scanner_sample.annotation.ReflectionPractice;
import practice.scanner_sample.controller.ReflectionController;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ReflectionPracticeScanner {

    private final Map<String, Method> methodMap = new HashMap<>();

    @PostConstruct
    public void scanningAnnotation() {
        Method[] methods = ReflectionController.class.getDeclaredMethods();
        Arrays.stream(methods)
                .filter(method -> method.getDeclaredAnnotation(ReflectionPractice.class) != null)
                .forEach(method -> methodMap.put(method.getDeclaredAnnotation(ReflectionPractice.class).value(), method));
    }

    public Method getMethod(String key) {
        return methodMap.get(key);
    }

    public int mapSize() {
        return methodMap.size();
    }

}
