package com.exampleproject.web.rest.server;

import com.exampleproject.engine.DataBaseTest;
import com.exampleproject.model.shared.TestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestExample {

    private final ApplicationContext applicationContext;
    private final DataBaseTest dataBaseTest;

    @Autowired
    public RestExample(ApplicationContext applicationContext, DataBaseTest dataBaseTest) {
        this.applicationContext = applicationContext;
        this.dataBaseTest = dataBaseTest;
    }

    @RequestMapping("/test")
    public TestDto test() {
        TestDto dto = createDto();
        dto.setMessage("It's a test string from server");
        return dto;
    }

    @RequestMapping("/testDb")
    public boolean testDB() {
        try {
            dataBaseTest.tryIt();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @RequestMapping("/test/{someText}")
    public TestDto testAdditional(@PathVariable String someText) {
        TestDto dto = createDto();
        dto.setMessage("It's a test string from server and you've given me " + someText);
        return dto;
    }

    protected TestDto createDto() {
        return applicationContext.getBean(TestDto.class);
    }

}
