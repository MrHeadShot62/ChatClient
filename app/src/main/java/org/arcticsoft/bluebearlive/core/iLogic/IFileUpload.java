package org.arcticsoft.bluebearlive.core.iLogic;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public interface IFileUpload {

    void checkFileSize();

    void checkFileType();

    void getUserUpload();

    void checkUserUpload();

    void getFileName();

    void checkFileName();

    void fileRename();

    void getError();

    void statusFile();
}
