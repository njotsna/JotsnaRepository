package com.cucumber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class File_Details_Reader {

	public static void main(String[] args) throws IOException {

		File dir = new File("C:\\workspace\\VehicleDetails");

		String fileName = "";

		for (File file : dir.listFiles()) {
			fileName = file.getName();
			System.out.println("File Name: " + fileName);
			System.out.println("File Size: " + file.length());
			System.out.println("File Mime Type: " + Files.probeContentType(file.toPath()));
			System.out.println("File Extension: " + fileName.substring(fileName.lastIndexOf(".") + 1));

			System.out.println("file path " + file.getPath());
			if ("csv".equals(fileName.substring(fileName.lastIndexOf(".") + 1))) {

				BufferedReader br = null;
				String line = "";
				String cvsSplitBy = ",";
				try {

					br = new BufferedReader(new FileReader(file.getPath()));
					while ((line = br.readLine()) != null) {

						// use comma as separator
						String[] vehicleDtls = line.split(cvsSplitBy);

						System.out.println("vehicle number " + vehicleDtls[0]);

					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

			}

		}

	}

}
