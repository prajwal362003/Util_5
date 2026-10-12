package com.sbi.zip.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipService implements AutoCloseable {

	private ZipOutputStream zipOutputStream;
	
/*	public void addFolder(Path folder) throws IOException{
		Files.walk(folder)
				.filter(Files::isRegularFile)
				.forEach(file->{
				});
	}*/
	
	// added to avoid extra folder
	public void addFolder(Path folder) throws IOException {
	    Files.walk(folder)
	        .filter(Files::isRegularFile)
	        .forEach(file -> {
	            try {
	                String entryName = folder.relativize(file)
	                        .toString()
	                        .replace("\\", "/");
	                zipOutputStream.putNextEntry(new ZipEntry(entryName) );
	                Files.copy(file, zipOutputStream);
	                zipOutputStream.closeEntry();

	            } catch (IOException e) {
	                throw new RuntimeException(e);
	            }
	        });
	}
	
	@Override
	public void close() throws Exception {
		
	}

}
