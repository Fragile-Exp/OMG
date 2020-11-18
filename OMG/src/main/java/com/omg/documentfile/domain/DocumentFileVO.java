package com.omg.documentfile.domain;

import com.omg.cmn.DTO;

public class DocumentFileVO extends DTO {

	private String fileCode;
	private String documenFileCode ;
	private String documentId;

	public DocumentFileVO() {}

	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getDocumenFileCode() {
		return documenFileCode;
	}

	public void setDocumenFileCode(String documenFileCode) {
		this.documenFileCode = documenFileCode;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public DocumentFileVO(String fileCode, String documenFileCode, String documentId) {
		super();
		this.fileCode = fileCode;
		this.documenFileCode = documenFileCode;
		this.documentId = documentId;
	}

	@Override
	public String toString() {
		return "DocumentFileVO [fileCode=" + fileCode + ", documenFileCode=" + documenFileCode + ", documentId="
				+ documentId + ", toString()=" + super.toString() + "]";
	}
	
	
}
