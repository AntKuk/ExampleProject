package com.exampleproject.engine;

import com.exampleproject.database.DataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;

@Component
public class DataBaseTest {

    private final DataBase<Map<String, ? super Object>> dataBase;

    @Autowired
    public DataBaseTest(DataBase<Map<String, ? super Object>> dataBase) {
        this.dataBase = dataBase;
    }

    public void tryIt() {
        dataBase.select("select * from all_tables", new LinkedList<Object>());
    }
}
