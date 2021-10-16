package com.app.bo.impl;
import java.awt.AlphaComposite;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import com.app.bo.FileManagerBO;
import com.app.exception.FileNameNotFoundException;
import com.app.exception.AlphaNumericOnly;
import com.app.model.FileDetail;

public  class FileManagerBOImpl implements FileManagerBO
{
	private File sach;

	public FileManagerBOImpl(String sach) {
		this.sach = new File(sach);
		try {
			this.sach.mkdirs();
		} catch (SecurityException e) {
			System.out.println("Please provide access to C:\\");
			System.exit(0);
		}
	}

	//@Override
	public FileDetail createFile(String name) throws IOException, SecurityException,AlphaNumericOnly
	{
		if(name.contains(".")) {
			if(!name.substring(0,name.lastIndexOf(".")).toLowerCase().matches("^[A-Za-z0-9\\s]+$")) {
				throw new AlphaNumericOnly("Only alphanumeric allowed");
			}
		}else {
			if(!name.toLowerCase().matches("^[A-Za-z0-9\\s]+$")) {
				throw new AlphaNumericOnly("Only alphanumeric allowed");
			}
		}		
		String path = sach.getAbsolutePath();
		File newFIle = new File(path + "\\" + name);
		newFIle.createNewFile();
		return new FileDetail(newFIle.getName(), newFIle.getParent(), newFIle.length(), newFIle.getPath());

	}

	//@Override
	public boolean deleteFile(String name) throws FileNameNotFoundException {
		String path = sach.getAbsolutePath();
		File fIleToDelete = new File(path + "\\" + name);
		if(!fIleToDelete.exists()) {
			throw new FileNameNotFoundException(name);
		}
		if (fIleToDelete.exists()) {
			return fIleToDelete.delete();
		} else {
			throw new FileNameNotFoundException(name);
		}

	}

	//@Override
	public List<FileDetail> getAllFiles() {
		File Files[] = this.sach.listFiles();
		List<FileDetail> ListFileDetail = new LinkedList();
		for (File file : Files) {
			ListFileDetail.add(new FileDetail(file.getName(), file.getParent(), file.length(), file.getPath()));
		}

		return ListFileDetail;
	}

	//@Override
	public boolean searchFile(String name) {
		String path = sach.getAbsolutePath();
		File fIleToSearch = new File(path + "\\" + name);
		return fIleToSearch.exists();
	}

	public List<FileDetail> getAllfiles() {
		return null;
	}

}
