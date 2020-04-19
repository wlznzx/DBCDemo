package cn.alauncher.dbcdmo;

import cn.alauncher.dbcdmo.dbc.CanCsvParser;
import cn.alauncher.dbcdmo.entity.CanDetailExcel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class DbcdmoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbcdmoApplication.class, args);

        //
        String path = "D:/GitCodeSource/vehicle-dbc/src/dbc/demo.dbc";
        List<CanDetailExcel> lists = new CanCsvParser().convertData(new File(path));
        for (CanDetailExcel excel : lists) {
            System.out.println(excel.toString());
        }
    }

}
