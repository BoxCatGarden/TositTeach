package com.tositteach.service;


import java.util.List;

public interface GpService {
    /*byte create(String groName, String claId, String proId);
    int addStuInto(String claId, byte groId, List<String> stuIds);*/
    /* create a group and add students into it
     * return 1 if succeed, or 0 */
    int makeGroup(String groName, String claId, String proId, List<String> stuIds);
}
