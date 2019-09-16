package com.ydy.vo.other;

import java.util.List;

public class FileDto {
	private String fileName;
	private String absolutePath;
	private String parentPath;
	private Integer isFile;
	private List<FileDto> subList;

	public String getFileName() {
		return fileName;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getIsFile() {
		return isFile;
	}

	public void setIsFile(Integer isFile) {
		this.isFile = isFile;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public List<FileDto> getSubList() {
		return subList;
	}

	public void setSubList(List<FileDto> subList) {
		this.subList = subList;
	}

}
