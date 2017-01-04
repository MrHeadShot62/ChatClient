package org.arcticsoft.bluebearlive.core.logic;

import android.support.v4.util.ArrayMap;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public class ErrorCode {

    private static ErrorCode instance = null;

    public static ArrayMap<Integer, String> ErrorsCodeInPoroject = new ArrayMap<>();

    public static synchronized  ErrorCode getInstance(){
        if (instance == null){
            return new ErrorCode();
        }else {
            return instance;
        }
    }

    private ErrorCode() {
        ErrorsCodeInPoroject.put(3, "Успешно!");
        ErrorsCodeInPoroject.put(100, "Нет подключения к серверу");
        ErrorsCodeInPoroject.put(101, "Ошибка авторизации на сервере");
        ErrorsCodeInPoroject.put(102, "Ошибка получения данных");
        ErrorsCodeInPoroject.put(103, "Ошибка отправки данных");
        ErrorsCodeInPoroject.put(104, "Ошибка обновления данных");
        ErrorsCodeInPoroject.put(105, "Ваша сессия устарела");
        ErrorsCodeInPoroject.put(106, "У вас нет прав доступа");
        ErrorsCodeInPoroject.put(107, "Ошибка в работе приложения");
        ErrorsCodeInPoroject.put(108, "Ваш файл не поддерживается системой");
        ErrorsCodeInPoroject.put(109, "Вы находитесь в режиме оффлайн");
        ErrorsCodeInPoroject.put(110, "Сервер не отвечает");
        ErrorsCodeInPoroject.put(111, "Этот логин занят");
        ErrorsCodeInPoroject.put(112, "Некорректный пароль");
        ErrorsCodeInPoroject.put(113, "Ошибка при регистрации");
        ErrorsCodeInPoroject.put(114, "Пройдите авторизацию");
    }
}
