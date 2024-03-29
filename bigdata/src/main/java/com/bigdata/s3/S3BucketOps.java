package com.bigdata.s3;

//snippet-sourcedescription:[S3BucketOps.java demonstrates how to create, list and delete S3 buckets.]
//snippet-keyword:[SDK for Java 2.0]
//snippet-keyword:[Code Sample]
//snippet-service:[s3]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[]
//snippet-sourceauthor:[soo-aws]
/*
 * Copyright 2011-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://aws.amazon.com/apache2.0
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 */
// snippet-start:[s3.java.s3_bucket_ops.complete]
// snippet-start:[s3.java.s3_bucket_ops.import]

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

// snippet-end:[s3.java.s3_bucket_ops.import]
// snippet-start:[s3.java.s3_bucket_ops.main]
public class S3BucketOps {

    public static void main(String[] args) {


        // snippet-start:[s3.java.s3_bucket_ops.create_bucket]
        // snippet-start:[s3.java.s3_bucket_ops.region]

        Region region = Region.AP_SOUTHEAST_1;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();
        // snippet-end:[s3.java.s3_bucket_ops.region]
        String bucket = "bucket" + System.currentTimeMillis();
        System.out.println(bucket);

        // Create bucket
        CreateBucketRequest createBucketRequest = CreateBucketRequest
                .builder()
                .bucket(bucket)
                .createBucketConfiguration(CreateBucketConfiguration.builder()
                        .locationConstraint(region.id())
                        .build())
                .build();
        s3.createBucket(createBucketRequest);
        // snippet-end:[s3.java.s3_bucket_ops.create_bucket]

        // snippet-start:[s3.java.s3_bucket_ops.list_bucket]
        // List buckets
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
        listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));
        // snippet-end:[s3.java.s3_bucket_ops.list_bucket]

        // Delete empty bucket
        // snippet-start:[s3.java.s3_bucket_ops.delete_bucket]
        DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder().bucket(bucket).build();
        s3.deleteBucket(deleteBucketRequest);
        // snippet-end:[s3.java.s3_bucket_ops.delete_bucket]
    }
}

// snippet-end:[s3.java.s3_bucket_ops.main]
// snippet-end:[s3.java.s3_bucket_ops.complete]