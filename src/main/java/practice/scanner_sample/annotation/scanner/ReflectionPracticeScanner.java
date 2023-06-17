package practice.scanner_sample.annotation.scanner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import practice.scanner_sample.annotation.ReflectionPractice;
import practice.scanner_sample.controller.ReflectionController;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ReflectionPracticeScanner {

    private final Map<String, Method> methodMap = new HashMap<>();
    private final ReflectionController controller;

    @PostConstruct
    public void scanningAnnotation() {
        Method[] methods = controller.getClass().getDeclaredMethods();
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

    // 사실 컴포넌트가 필요하진 않지 위에 선언되어 있는 걸 쓰면 되니
    public Object invokeMethod(String key, Object component) throws InvocationTargetException, IllegalAccessException {
        return invokeMethod(key, component, null);
    }

    public Object invokeMethod(String key, Object component, Object... arg) throws InvocationTargetException, IllegalAccessException {
        return methodMap.get(key).invoke(component, arg);
    }
}
