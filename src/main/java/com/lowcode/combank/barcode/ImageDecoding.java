package com.lowcode.combank.barcode;

import java.io.*;

import com.dynamsoft.dbr.*;

/*
Get Barcode extraction
*/

public class ImageDecoding {

	public static String[] extract(InputStream stream,String licenseKey) {

		TextResult[] results = null;
		String[] barCodeData = null;
		try {

			BarcodeReader.initLicense(licenseKey);
			BarcodeReader dbr = BarcodeReader.getInstance();
			if (dbr == null) {
				throw new Exception("Get BarCode Instance Failed.");
			}

			results = dbr.decodeFileInMemory(stream, "");

			if (results != null && results.length > 0) {
				for (int i = 0; i < results.length; i++) {
					TextResult result = results[i];
					String s = result.barcodeText;
					barCodeData = s.split("\n");
					break;
				}
			} else {
				System.out.println("No BarCode data detected.");
			}

			dbr.recycle();
		} catch (BarcodeReaderException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return barCodeData;
	}
}
