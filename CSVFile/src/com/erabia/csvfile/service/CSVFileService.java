package com.erabia.csvfile.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.erabia.csvfile.bean.UpdateData;
import com.erabia.csvfile.bean.UpdateRecordData;
import com.erabia.csvfile.exception.CSVFileException;

import jdk.nashorn.internal.ir.Optimistic;


public interface CSVFileService {

   // public boolean isFileExist(String path);

    public Optional<List<List<String>>> getData(String path, String sp, boolean isHeader) throws CSVFileException;

    public void add(String path, String sp, boolean isHeader, List<String> item) throws CSVFileException;

    public Optional<List<List<String>>> findAll(String path, String sp, boolean isHeader, String... keys) throws CSVFileException;

    public Optional<List<List<String>>> find(String path, String sp, boolean isHeader, Map<Integer, String> keys) throws CSVFileException;

   // public boolean isExist(String path, String sp, boolean isHeader, List<String> item);

    public void remove(String path, String sp, boolean isHeader, Map<Integer, String> keys) throws CSVFileException;

   // public boolean removeIndex(String path, String sp, boolean isHeader, int index);

   // public void addAt(String path, String sp, boolean isHeader, int index, List<String> item);

    public void update(String path, String sp, boolean isHeader, Map<Integer, UpdateData> keys ) throws CSVFileException;

    public void updateRecord(String path, String sp, boolean isHeader,  Map<Integer, UpdateRecordData> keys)  throws CSVFileException;

    public void clear(String path);
}

