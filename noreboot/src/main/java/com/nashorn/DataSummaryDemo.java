package com.nashorn;


import com.nashorn.util.Constant;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * @author i324779
 * data summary, please see sales.txt.
 * you can change the data dynamically.
 * here not using Scanner class to keep not need to restart.
 * just illustrate one case that pass a file path and data summary.
 */
public class DataSummaryDemo {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get(Constant.FILE_PATH);
        Stream<String> lines = Files.lines(path);
        DoubleStream ds = lines.mapToDouble(a -> Double.parseDouble(a));
        System.out.println("Total: " + ds.sum());

        lines = Files.lines(path);
        ds = lines.mapToDouble(a -> Double.parseDouble(a));
        System.out.println("Average: " + ds.average().getAsDouble());

    }
}
