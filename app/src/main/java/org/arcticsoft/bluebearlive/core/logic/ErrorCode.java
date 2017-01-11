package org.arcticsoft.bluebearlive.core.logic;

import android.support.v4.util.ArrayMap;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public class ErrorCode {

    private static ErrorCode instance = null;

    public ArrayMap<Integer, String> ErrorsCodeInProject = new ArrayMap<>();

    public static  ErrorCode getInstance(){
        if (instance == null){
            instance = new ErrorCode();
            return instance;

        }else {
            return instance;
        }
    }

    private ErrorCode() {
        ErrorsCodeInProject.put(3, "Успешно!");
        ErrorsCodeInProject.put(100, "Нет подключения к серверу");
        ErrorsCodeInProject.put(101, "Ошибка авторизации на сервере");
        ErrorsCodeInProject.put(102, "Ошибка получения данных");
        ErrorsCodeInProject.put(103, "Ошибка отправки данных");
        ErrorsCodeInProject.put(104, "Ошибка обновления данных");
        ErrorsCodeInProject.put(105, "Ваша сессия устарела");
        ErrorsCodeInProject.put(106, "У вас нет прав доступа");
        ErrorsCodeInProject.put(107, "Ошибка в работе приложения");
        ErrorsCodeInProject.put(108, "Ваш файл не поддерживается системой");
        ErrorsCodeInProject.put(109, "Вы находитесь в режиме оффлайн");
        ErrorsCodeInProject.put(110, "Сервер не отвечает");
        ErrorsCodeInProject.put(111, "Этот логин занят");
        ErrorsCodeInProject.put(112, "Некорректный пароль");
        ErrorsCodeInProject.put(113, "Ошибка при регистрации");
        ErrorsCodeInProject.put(114, "Пройдите авторизацию");
    }
}
