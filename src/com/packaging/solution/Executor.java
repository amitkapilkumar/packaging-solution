package com.packaging.solution;

import com.packaging.solution.exception.InvalidCaseException;
import com.packaging.solution.processor.PackagingProcessor;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class Executor {

    public static void main(String[] args) throws IOException, ParseException {
        if(args.length == 0) {
            throw new InvalidCaseException("Input file path not supplied");
        }
        String inputFile = args[0];
        System.out.println("Input File : " + inputFile);
        File file = new File(inputFile);
        if(!file.exists()) {
            System.out.println("input file does not exists");
        }
        PackagingProcessor processor = new PackagingProcessor();
        processor.parseItems(file);
    }
}
