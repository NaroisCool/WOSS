package com.briup.Utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

public class ClearLogfile {
	private transient Logger logger=Logger.getLogger(ClearLogfile.class);
	public void clear(String path){
		try {
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
			bw.write("");
			bw.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
