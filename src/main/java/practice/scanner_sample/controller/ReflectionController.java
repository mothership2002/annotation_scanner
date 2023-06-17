package practice.scanner_sample.controller;


import org.springframework.stereotype.Component;
import practice.scanner_sample.annotation.ReflectionPractice;

//@Component
public class ReflectionController {

    @ReflectionPractice("/hello")
    public void methodOne() {

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
