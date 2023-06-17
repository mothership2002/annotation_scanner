package practice.scanner_sample.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import practice.scanner_sample.annotation.ReflectionPractice;

@Component
@Slf4j
public class ReflectionController {

    @ReflectionPractice("/hello")
    public Long methodOne() {
        log.info("Hello World");
        return 1L;
    }

    @ReflectionPractice("/world")
    public void methodTwo() {

    }

    @ReflectionPractice("/reflection")
    public void methodThree() {

    }

    @ReflectionPractice("/four")
    public void methodFour() {

    }

    @ReflectionPractice("/five")
    public void methodFive() {

    }

}
