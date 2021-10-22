//package com.bigdata.kinesis;
//
//import software.amazon.awssdk.services.kinesis.model.PutRecordsRequest;
//import software.amazon.awssdk.services.kinesis.model.PutRecordsRequestEntry;
//
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//import java.util.List;
//
//public class KPL_ProducerTest {
//    public static void main(String args[]){
//        AmazonKinesisClientBuilder clientBuilder = AmazonKinesisClientBuilder.standard();
//
//        clientBuilder.setRegion(regionName);
//        clientBuilder.setCredentials(credentialsProvider);
//        clientBuilder.setClientConfiguration(config);
//
//        AmazonKinesis kinesisClient = clientBuilder.build();
//
//        PutRecordsRequest putRecordsRequest  = new PutRecordsRequest();
//        putRecordsRequest.setStreamName(streamName);
//        List<PutRecordsRequestEntry> putRecordsRequestEntryList  = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            PutRecordsRequestEntry putRecordsRequestEntry  = new PutRecordsRequestEntry();
//            putRecordsRequestEntry.setData(ByteBuffer.wrap(String.valueOf(i).getBytes()));
//            putRecordsRequestEntry.setPartitionKey(String.format("partitionKey-%d", i));
//            putRecordsRequestEntryList.add(putRecordsRequestEntry);
//        }
//
//        putRecordsRequest.setRecords(putRecordsRequestEntryList);
//        PutRecordsResult putRecordsResult  = kinesisClient.putRecords(putRecordsRequest);
//        System.out.println("Put Result" + putRecordsResult);
//    }
//}
