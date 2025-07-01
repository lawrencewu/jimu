package com.jimu.controller;

import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.common.DataType;
import io.milvus.v2.service.collection.request.AddFieldReq;
import io.milvus.v2.service.collection.request.CreateCollectionReq;
import io.milvus.v2.service.collection.request.DropCollectionReq;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lawrence
 * @Date 2025/6/23
 *
 * TODO remove
 */

@RestController
public class UtilController {
    @GetMapping("/collection/drop")
    public void dropCollection(){
        ConnectConfig config = ConnectConfig.builder()
                .uri("http://localhost:19530")
                .token("root:Milvus")
                .build();

        MilvusClientV2 client = new MilvusClientV2(config);

        DropCollectionReq dropCollectionReq = DropCollectionReq.builder().collectionName("vector_store").build();
        client.dropCollection(dropCollectionReq);
    }

    @GetMapping("/collection/create")
    public void createCollection() {
        ConnectConfig config = ConnectConfig.builder()
                .uri("http://localhost:19530")
                .token("root:Milvus")
                .build();

        MilvusClientV2 client = new MilvusClientV2(config);

        CreateCollectionReq.CollectionSchema collectionSchema = client.createSchema();

        collectionSchema.addField(AddFieldReq.builder().fieldName("vector").dataType(DataType.FloatVector).description("vector").dimension(1536).build());
        collectionSchema.addField(AddFieldReq.builder().fieldName("doc_id").dataType(DataType.Int64).autoID(Boolean.FALSE).description("doc id").isPrimaryKey(Boolean.TRUE).build());

        collectionSchema.addField(AddFieldReq.builder().fieldName("content").dataType(DataType.VarChar).description("content").build());

        collectionSchema.addField(AddFieldReq.builder().fieldName("metadata").dataType(DataType.VarChar).description("metadata").build());

        collectionSchema.addField(AddFieldReq.builder().fieldName("embedding").dataType(DataType.VarChar).description("embedding").build());

        collectionSchema.addField(AddFieldReq.builder().fieldName("score").dataType(DataType.Float).description("score").build());

        CreateCollectionReq createCollectionReq = CreateCollectionReq.builder().collectionSchema(collectionSchema)
                .collectionName("vector_store").dimension(1536).build();


        client.createCollection(createCollectionReq);


        System.out.println("创建milvus collection");
        client.close();
    }
}
