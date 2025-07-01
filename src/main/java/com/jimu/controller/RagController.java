package com.jimu.controller;

import com.mysql.cj.result.Field;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author lawrence
 * @Date 2025/6/23
 */
@RestController
public class RagController {

    @Autowired
    public VectorStore vectorStore;

    @Autowired
    ResourceLoader resourceLoader;

    /**
     * 上传文档接口
     */
    @GetMapping("/doc/upload")
    public void uploadDocument() throws IOException {
        List<Document> documents = new ArrayList<>();
        //文件暂时在项目目录下

        Resource resource = resourceLoader.getResource("classpath:docs");
        File fold = resource.getFile();
        if (fold.isDirectory()) {
           String[] files = fold.list();
           if (files != null) {
               for (String file : files) {
                   PagePdfDocumentReader pdfDocumentReader = new PagePdfDocumentReader("/docs/" + file);
                   vectorStore.add(pdfDocumentReader.get());
               }
           }
        }
    }

    /**
     * 从向量库内查询数据
     * @param searchContext 用户查询的内容
     * @return 返回内容
     */
    @GetMapping("/doc/get")
    public List<Document> getDocument(String searchContext) {

        SearchRequest sr = SearchRequest.builder()
                .similarityThreshold(0.5)
                .topK(3)
                .query(searchContext)
                .build();
       return vectorStore.similaritySearch(sr);
    }
}
