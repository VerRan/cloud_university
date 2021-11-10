package com.lht.ivs;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ivs.AmazonIVS;
import com.amazonaws.services.ivs.AmazonIVSClientBuilder;
import com.amazonaws.services.ivs.model.CreateChannelRequest;
import com.amazonaws.services.ivs.model.CreateChannelResult;

public class IVSTest {

    public static void main(String args[]){
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (~/.aws/credentials), and is in valid format.",
                    e);
        }

        /**初始化Amaozn IVS */
        AmazonIVS amazonIVS = AmazonIVSClientBuilder.standard()
                .withCredentials(
                new AWSStaticCredentialsProvider(credentials))
                .withRegion("us-west-2")
                .build();

        /**创建Channel */
        CreateChannelRequest createChannelRequest =new CreateChannelRequest();

        createChannelRequest.setName("test");
//        createChannelRequest.setType();
        CreateChannelResult createChannelResult =  amazonIVS.createChannel(createChannelRequest);
        System.out.println(createChannelResult.getStreamKey());
        System.out.println(createChannelResult.getChannel().getArn());
    }
}
