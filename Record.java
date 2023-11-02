package com.sgu.scp.search;

import com.google.gson.annotations.SerializedName;

public class Record { // класс "Запись"
    @SerializedName("object_number_text") // Поле "Номер объекта"
    private String objectNumberText;
    @SerializedName("object_class_text") // Поле "Класс объекта"
    private String objectClassText;
    @SerializedName("object_condition_text") // Поле "Условия содержания объекта"
    private String objectConditionText;
    @SerializedName("object_info_text") // Поле "Ифномрация об объекте"
    private String objectInfoText;
    @SerializedName("url") // Поле "URL"
    private String url;
    @SerializedName("title") // Поле "Заголовок"
    private String title;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() { // Создание записи
        return "Record{" +
                "objectNumberText='" + objectNumberText + '\'' +
                ", objectClassText='" + objectClassText + '\'' +
                ", objectConditionText='" + objectConditionText + '\'' +
                ", objectInfoText='" + objectInfoText + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    } // Get'ер и Set'ер для поля "Запись"

    public String getObjectNumberText() {
        return objectNumberText;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setObjectNumberText(String objectNumberText) {
        this.objectNumberText = objectNumberText;
    }

    public String getObjectClassText() {
        return objectClassText;
    } // Get'ер и Set'ер для поля "Класс объекта"

    public void setObjectClassText(String objectClassText) {
        this.objectClassText = objectClassText;
    }

    public String getObjectConditionText() {
        return objectConditionText;
    } // Get'ер и Set'ер для поля
    // "Условия содержания объекта"

    public void setObjectConditionText(String objectConditionText) {
        this.objectConditionText = objectConditionText;
    }

    public String getObjectInfoText() {
        return objectInfoText;
    } // Get'ер и Set'ер для поля "Информация об объекте"

    public void setObjectInfoText(String objectInfoText) {
        this.objectInfoText = objectInfoText;
    }
}
