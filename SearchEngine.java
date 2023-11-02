package com.sgu.scp.search;

import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
// import org.apache.lucene.search.Filter;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchEngine {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner inp = new Scanner(System.in, "utf-8"); // Сканер
        String str = inp.nextLine(); // Обработка входной строки
        Query query = new MultiFieldQueryParser(new String[]{"object_number_text", // Использование полей
                "object_class_text",
                "object_condition_text",
                "object_info_text",
                "url",
                "title",
        },
                CreateIndex.createAnalyser()).parse(str); // Анализатор
        IndexReader indexReader = DirectoryReader.open(CreateIndex.getFsDirectory()); // Считывание индекса
        IndexSearcher searcher = new IndexSearcher(indexReader); // Поисковик
        TopDocs topDocs = searcher.search(query, 10); // Ограничение на вывод
        TopDocs results = searcher.search(query, 10);
        List<Document> recordList = new ArrayList<>();
        ArrayList list = new ArrayList(); // Новый документ-результат
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            recordList.add(searcher.doc(scoreDoc.doc));
            list.add(searcher.explain(query, scoreDoc.doc));
        }
        System.out.println(str);
        for (Document document : recordList) {
            System.out.println(document.get("url"));
        }
        File file = new File("C:\\Users\\UselessAqua\\IdeaProjects\\output.txt"); // Путь до файла вывыода
        PrintWriter pw = new PrintWriter(file);
        for (int i=0; i < list.size(); i++) {
            pw.println(recordList.get(i).get("url") + " " + list.get(i));
        }
        pw.close();
    }
}

