package com.tositteach.service;

import com.tositteach.domain.entity.EngDoc;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

public interface EngDocService {

    /* return the total number of the matched records*/
    int total(String proName);

    /* query the records in Table DOC_ENGINEER ranging from st to nm.
    *  return: the recordset*/
    List<EngDoc> query(String proName, int st, int nm);

    /* save the upload file on the disk, and
    *  record its information into Table DOC_ENGINEER.
    *  return: 0, fail;
    *          1, succeed;
    *          2, duplicate doc_id
    *  Note: it may throws IOException if the file cannot be
    *  stored in the disk.*/
    int add(CommonsMultipartFile file, String proId, String userId) throws IOException;

    /* modify the record of an existing document.*/
    int modify(EngDoc doc);

    /* re-upload the file, which will delete the old file from and
    *  save the new file into the disk, changing the Attribute URL
    *  of its corresponding record in the DB.
    *  return: 0, fail;
    *          1, succeed*/
    int reupload(String doc_id, CommonsMultipartFile file) throws IOException;

    /* delete the document with the id of doc_id from the disk,
    *  which will also delete the record from the database.
    *  return: 0, fail;
    *          1, succeed*/
    int del(String doc_id) throws IOException;
}
