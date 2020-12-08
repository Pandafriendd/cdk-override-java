package com.myorg;

import java.util.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Duration;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;

import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.elasticloadbalancing.LoadBalancer;
import software.amazon.awscdk.services.elasticloadbalancing.LoadBalancerListener;
import software.amazon.awscdk.services.elasticloadbalancing.CfnLoadBalancer;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.CfnBucket;
//import software.amazon.awscdk.services.elasticloadbalancing.PoliciesProperty;  // cannot find symbol!!!

public class CDKS3Stack extends Stack {
    public CDKS3Stack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public CDKS3Stack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);
      
      // https://github.com/aws-samples/aws-cdk-examples/blob/70537a3f3e2333501315ee4740ca13fbd8f25ddb/java/resource-overrides/src/main/java/software/amazon/awscdk/examples/ResourceOverridesStack.java
      
      
	  Bucket bucket = Bucket.Builder.create(this, "MyFirstBucket")
      .bucketName("my-first-cdk-java-s3-bucket")
      .versioned(true).build();
      
      // Get the AWS CloudFormation resource
     CfnBucket cfnBucket = (CfnBucket)bucket.getNode().getDefaultChild();
      
      // use index (0 here) to address an element of a list
    //cfnBucket.addOverride("Properties.Tags.0.Value", "NewValue");
    
    //cfnBucket.addOverride("Properties.Tags", "[ { \"Value\": \"NewValue\" }, { \"SecondValue\": \"NewValue\" } ]");
      
      //System.out.println("[ { \"Value\": \"NewValue\" }, { \"SecondValue\": \"NewValue\" } ]");
      
      /*
      cfnBucket.setAnalyticsConfigurations(
        Arrays.asList(new HashMap<String, String>() {{
            put("Id", "Config");
            put("storageClassAnalysis", Arrays.asList (new HashMap<String, String>() {{
                put("DataExport", "Config");
            }}));
            }});
            
        }}));
        */
        
        
    
    /*
        cfnBucket.setAnalyticsConfigurations(
        Collections.singletonList(
            ImmutableMap.builder()
                .put("id", "config1")
                .put(
                    "storageClassAnalysis",
                    ImmutableMap.of(
                        "dataExport",
                        ImmutableMap.builder()
                            .put("outputSchemaVersion", "1")
                            .put(
                                "destination",
                                ImmutableMap.builder()
                                    .put("format", "html")
                                    // using L2 construct's method will work as expected
                                    .put("bucketArn", "sadasdsadadsadasd")
                                    .build())
                            .build()))
                .build()));
    */
                
        /*
        "AnalyticsConfigurations": [
          {
            "Id": "config1",
            "StorageClassAnalysis": {
              "DataExport": {
                "Destination": {
                  "BucketArn": "sadasdsadadsadasd",
                  "Format": "html"
                },
                "OutputSchemaVersion": "1"
              }
            }
          }
        ],
        */
    
    }
}