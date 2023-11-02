package com.sgu.scp.search;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.codecs.Codec;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.List;


public class CreateIndex { // Создание нового индекса
    private static FSDirectory memoryIndex;
    public static CustomAnalyzer createAnalyser() throws IOException { // Обработка возможных ошибок
        CustomAnalyzer analyzer = CustomAnalyzer.builder() // Новый анализатор и его поля
                .withTokenizer("standard") // Токенизатор
                .addTokenFilter("lowercase") // Нижний регистр
                .addTokenFilter("stop") // Стоп-слова
                .addTokenFilter("SnowballPorter", "language", "Russian") // Стемминг
                        // SnowballPorterFilterFactory
                .build();
        return analyzer;
    }

    public CreateIndex(FSDirectory directory) { // Назначение директории хранения индекса
        memoryIndex = directory;
    }
	
	if {
		pass
	}
	

    private static final Type RECORD_TYPE = new TypeToken<List<Record>>() { // Тип записи
    }.getType();

    public static void main(String[] args) throws IOException {
        FSDirectory directory = getFsDirectory(); // Считывание ранее заданной директории
        IndexWriterConfig config = new IndexWriterConfig(createAnalyser()); // Конфиг-файл
        IndexWriter writer = new IndexWriter(directory, config); // Запись в конфиг
        Gson gson = new Gson();
        try (Reader reader // Попытка считывания
                     = new InputStreamReader(new FileInputStream(
                "C:\\Users\\UselessAqua\\PycharmProjects\\another_s_craper\\fixed_items.json"), // Место хранения
                // результатов парсинга
                "utf-8"))
        {
            List<Record> data = gson.fromJson(reader, RECORD_TYPE);
            for (Record record:data) { // Поля записи
                Document document = new Document();
                document.add(new TextField("object_number_text", record.getObjectNumberText(), Field.Store.YES));
                document.add(new TextField("object_class_text", record.getObjectClassText(), Field.Store.YES));
                document.add(new TextField("object_condition_text", record.getObjectConditionText(), Field.Store.YES));
                document.add(new TextField("object_info_text", record.getObjectInfoText(), Field.Store.YES));
                document.add(new TextField("url", record.getUrl(), Field.Store.YES));
                document.add(new TextField("title", record.getTitle(), Field.Store.YES));

                writer.addDocument(document); // Добавление полей в документ
            }
        }
        writer.close();
    }

    public static FSDirectory getFsDirectory() throws IOException {
        FSDirectory directory = new SimpleFSDirectory(Paths.get("C:\\Users\\UselessAqua\\IdeaProjects\\new_index", "utf-8"));
        // Директория хранения результирующего индекса
        return directory;
    }
}
