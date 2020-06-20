package com.erabia.csvfile.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.erabia.csvfile.bean.UpdateData;
import com.erabia.csvfile.bean.UpdateRecordData;
import com.erabia.csvfile.exception.CSVFileException;
import com.erabia.csvfile.exception.enums.CSVFileExceptionType;
import com.erabia.csvfile.service.CSVFileService;

public class CSVFileServiceImpl implements CSVFileService {

	private CSVFileServiceImpl() {
	}

	private static class CSVFileServiceImpHelper {
		private static final CSVFileServiceImpl csvFileObject = new CSVFileServiceImpl();
	}

	public static CSVFileServiceImpl getInstance() {
		return CSVFileServiceImpHelper.csvFileObject;
	}

	@Override
	public Optional<List<List<String>>> getData(String path, String sp, boolean isHeader) throws CSVFileException {

		if (path == null || path.trim().isEmpty()) {
			throw new IllegalArgumentException("path is null");
		}
		if (sp == null) {
			throw new IllegalArgumentException("sp is null");
		}

		List<List<String>> list = new LinkedList<>();
		File csvFile = new File(path);
		if (!csvFile.exists())
			throw new CSVFileException(CSVFileExceptionType.CSVFILE_NOT_FOUND,
					CSVFileExceptionType.CSVFILE_NOT_FOUND.getMessage(), null);
		if (!csvFile.canRead())
			throw new CSVFileException(CSVFileExceptionType.CSVFILE_CAN_READ,
					CSVFileExceptionType.CSVFILE_CAN_READ.getMessage(), null);

		try (Scanner in = new Scanner(csvFile)) {

			if (isHeader && in.hasNext())
				in.nextLine();

			while (in.hasNext()) {
				String line = in.nextLine();
				String[] data = line.split(sp);
				List<String> record = new LinkedList<>(Arrays.asList(data));

				list.add(record);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Optional.ofNullable(list);
	}

	@Override
	public void add(String path, String sp, boolean isHeader, List<String> item) throws CSVFileException {
		if (path == null || path.trim().isEmpty()) {
			throw new IllegalArgumentException("path is null");
		}
		if (sp == null) {
			throw new IllegalArgumentException("sp is null");
		}
		if (item == null) {
			throw new IllegalArgumentException("item is null");
		}

		Optional<List<List<String>>> opList = getData(path, sp, isHeader);
		List<List<String>> list;
		if (!opList.isPresent())
			list = new LinkedList<>();
		else
			list = opList.get();

		File csvFile = new File(path);
		if (!csvFile.canWrite())
			throw new CSVFileException(CSVFileExceptionType.CSVFILE_CAN_WRITE,
					CSVFileExceptionType.CSVFILE_CAN_WRITE.getMessage(), null);
		list.add(item);
		try (PrintWriter out = new PrintWriter(path)) {
			Iterator<List<String>> listIt = list.iterator();
			while (listIt.hasNext()) {
				List<String> record = listIt.next();
				Iterator<String> it = record.iterator();
				while (it.hasNext()) {
					out.print(it.next());
					if (it.hasNext())
						out.print(sp);

				}
				out.println();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	@Override
	public Optional<List<List<String>>> findAll(String path, String sp, boolean isHeader, String... keys)
			throws CSVFileException {
		Optional<List<List<String>>> opList = getData(path, sp, isHeader);
		List<List<String>> list;

		if (!opList.isPresent())
			throw new CSVFileException(CSVFileExceptionType.CSVFILE_EMPTY,
					CSVFileExceptionType.CSVFILE_EMPTY.getMessage(), null);
		else
			list = opList.get();

		if (keys == null)
			throw new IllegalArgumentException("Keys is null");

		List<List<String>> foundList = new LinkedList<>();
		Iterator<List<String>> listIt = list.iterator();
		boolean next = false;

		while (listIt.hasNext()) {
			for (int i = 0; i < keys.length; i++) {
				List<String> record = listIt.next();

				Iterator<String> it = record.iterator();

				while (it.hasNext()) {

					String value = it.next();

					if (value.equals(keys[i])) {
						foundList.add(record);
						next = true;
						break;
					}
				}
				if (next) {
					next = false;
					break;
				}

			}

		}

		return Optional.ofNullable(foundList);
	}

	@Override
	public Optional<List<List<String>>> find(String path, String sp, boolean isHeader, Map<Integer, String> keys)
			throws CSVFileException {
		Optional<List<List<String>>> opList = getData(path, sp, isHeader);
		List<List<String>> list;

		if (!opList.isPresent())
			throw new CSVFileException(CSVFileExceptionType.CSVFILE_EMPTY,
					CSVFileExceptionType.CSVFILE_EMPTY.getMessage(), null);
		else
			list = opList.get();

		if (keys == null)
			throw new IllegalArgumentException("Keys is null");
		List<List<String>> foundList = new LinkedList<>();
		Iterator<List<String>> listIt = list.iterator();
		while (listIt.hasNext()) {
			List<String> record = listIt.next();
			Iterator<String> it = record.iterator();
			int i = 0;
			while (it.hasNext()) {

				String value = it.next();
				if (keys.containsKey(i) && keys.get(i).equals(value)) {
					foundList.add(record);
					break;
				}

				i++;
			}

		}

		return Optional.ofNullable(foundList);

	}

	@Override
	public void remove(String path, String sp, boolean isHeader, Map<Integer, String> keys) throws CSVFileException {
		Optional<List<List<String>>> opList = getData(path, sp, isHeader);
		List<List<String>> list;

		if (!opList.isPresent())
			throw new CSVFileException(CSVFileExceptionType.CSVFILE_EMPTY,
					CSVFileExceptionType.CSVFILE_EMPTY.getMessage(), null);
		else
			list = opList.get();

		if (keys == null)
			throw new IllegalArgumentException("Keys is null");
		Iterator<List<String>> listIt = list.iterator();
		while (listIt.hasNext()) {
			List<String> record = listIt.next();
			Iterator<String> it = record.iterator();
			int i = 0;
			while (it.hasNext()) {

				String value = it.next();
				if (keys.containsKey(i) && keys.get(i).equals(value))
					listIt.remove();
				i++;
			}

		}
		clear(path);
		for (List<String> record : list) {
			add(path, sp, isHeader, record);
		}

	}

	@Override
	public void update(String path, String sp, boolean isHeader, Map<Integer, UpdateData> keys)
			throws CSVFileException {
		// TODO Auto-generated method stub
		Optional<List<List<String>>> opList = getData(path, sp, isHeader);
		List<List<String>> list;

		if (!opList.isPresent())
			throw new CSVFileException(CSVFileExceptionType.CSVFILE_EMPTY,
					CSVFileExceptionType.CSVFILE_EMPTY.getMessage(), null);
		else
			list = opList.get();

		if (keys == null)
			throw new IllegalArgumentException("Keys is null");
		Iterator<List<String>> listIt = list.iterator();
		while (listIt.hasNext()) {
			List<String> record = listIt.next();
			Iterator<String> it = record.iterator();
			int i = 0;
			while (it.hasNext()) {

				String value = it.next();
				if (keys.containsKey(i) && keys.get(i).getOldData().equals(value))
					record.set(i, keys.get(i).getNewData());

				i++;
			}

		}
		clear(path);
		for (List<String> record : list) {
			add(path, sp, isHeader, record);
		}
	}

	@Override
	public void updateRecord(String path, String sp, boolean isHeader, Map<Integer, UpdateRecordData> keys)
			throws CSVFileException {
		// TODO Auto-generated method stub
		Optional<List<List<String>>> opList = getData(path, sp, isHeader);
		List<List<String>> list;

		if (!opList.isPresent())
			throw new CSVFileException(CSVFileExceptionType.CSVFILE_EMPTY,
					CSVFileExceptionType.CSVFILE_EMPTY.getMessage(), null);
		else
			list = opList.get();

		if (list == null)
			throw new CSVFileException(CSVFileExceptionType.CSVFILE_EMPTY,
					CSVFileExceptionType.CSVFILE_EMPTY.getMessage(), null);
		if (keys == null)
			throw new IllegalArgumentException("Keys is null");
		Iterator<List<String>> listIt = list.iterator();
		int j=0;
		while (listIt.hasNext()) {
			List<String> record = listIt.next();
			Iterator<String> it = record.iterator();
		
			if (it.hasNext()) {
				String value = it.next();
				System.out.println(value+" "+j);
				for (int i : keys.keySet()) {
				
						System.out.println(keys.get(i).getNewRecord()+" "+keys.get(i).getOldKeyWord());
					if (keys.get(i).getOldKeyWord().equals(value)) {
						
						list.remove(j);
						list.add(keys.get(i).getNewRecord());
						clear(path);
						for (List<String> record2 : list) {
							add(path, sp, isHeader, record2);
						}
						return;

					}
				}
			

			}
			j++;

		}
		
	}

	@Override
	public void clear(String path) {

		try (PrintWriter out = new PrintWriter(path)) {
			out.print("");

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

}
