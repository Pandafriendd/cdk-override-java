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

public class CdkClbJavaStack extends Stack {
    public CdkClbJavaStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public CdkClbJavaStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);
        
	  Vpc vpc = Vpc.Builder.create(this, "newdevvpc").cidr("10.0.0.0/16").build();
				
		LoadBalancer loadBalancer = LoadBalancer.Builder.create(this,"LB")
                .vpc(vpc)
                .build();
        /*        
	    List<Object> attributes = new ArrayList<>();
        attributes.add("ELBSecurityPolicy-TLS-1-2-2017-01");
        
        PoliciesProperty.builder()
            .policyName("My-SSLNegotiation-Policy")
            .policyType("SSLNegotiationPolicyType")
            .attributes(attributes)
            .build();
        */
        
        List<String> policyName = new ArrayList<>();
        policyName.add("My-SSLNegotiation-Policy");
        
        
        // listener var might be not necessary
		loadBalancer.addListener(LoadBalancerListener.builder()
		                                  .externalPort(443)
		                                  .policyNames(policyName)
		                                  .build());
		
		// override by low level construct
		CfnLoadBalancer cfnLb = (CfnLoadBalancer)loadBalancer.getNode().getDefaultChild();
		/*
		cfnLb.addOverride("Properties.Policies.PolicyName", "My-SSLNegotiation-Policy");
		cfnLb.addOverride("Properties.Policies.PolicyType", "SSLNegotiationPolicyType");
		cfnLb.addOverride("Properties.Policies.Attributes.Name", "Reference-Security-Policy");
		cfnLb.addOverride("Properties.Policies.Attributes.Value", "ELBSecurityPolicy-TLS-1-2-2017-01");
		*/
		/*
		"Policies": {
          "PolicyName": "My-SSLNegotiation-Policy",
          "PolicyType": "SSLNegotiationPolicyType",
          "Attributes": {
            "Name": "Reference-Security-Policy",
            "Value": "ELBSecurityPolicy-TLS-1-2-2017-01"
          }
        },
		*/
		/*
		cfnLb.addPropertyOverride("Policies.0.PolicyName", "My-SSLNegotiation-Policy");
		cfnLb.addPropertyOverride("Policies.0.PolicyType", "SSLNegotiationPolicyType");
		cfnLb.addPropertyOverride("Policies.0.Attributes.0.Name", "Reference-Security-Policy");
		cfnLb.addPropertyOverride("Policies.0.Attributes.0.Value", "ELBSecurityPolicy-TLS-1-2-2017-01");
		*/
		
		//cfnLb.addOverride("Properties.Tags.0.Value", "NewValue");
		
		
		
		cfnLb.setPolicies(
		 Collections.singletonList(
            ImmutableMap.builder()
                .put("policyName", "My-SSLNegotiation-Policy")
                .put("policyType", "SSLNegotiationPolicyType")
                .put(
                    "attributes",
                    ImmutableList.of(
                        ImmutableMap.builder()
                            .put("Name", "Reference-Security-Policy")
                            .put("Value", "ELBSecurityPolicy-TLS-1-2-2017-01") 
                        .build()))
            .build()));
            
        /*
        synth:
        
        "Policies": [
          {
            "Attributes": [
              {
                "Name": "Reference-Security-Policy",
                "Value": "ELBSecurityPolicy-TLS-1-2-2017-01"
              }
            ],
            "PolicyName": "My-SSLNegotiation-Policy",
            "PolicyType": "SSLNegotiationPolicyType"
          }
        ],
        
        expect:
        
        "Policies": [{
            "PolicyName": "My-SSLNegotiation-Policy",
            "PolicyType": "SSLNegotiationPolicyType",
            "Attributes": [{
                "Name": "Reference-Security-Policy",
                "Value": "ELBSecurityPolicy-TLS-1-2-2017-01"
            }]
        }]
        
        */
		
		/*
		cfnLb.setPolicies(
            Arrays.asList(new HashMap<String, String>() {{
                put("PolicyName", "My-SSLNegotiation-Policy");
                put("PolicyType", "SSLNegotiationPolicyType");
                put("Attributes", Arrays.asList(new HashMap<String, String>() {{
                    put("Name", "Reference-Security-Policy");
                    put("Value", "ELBSecurityPolicy-TLS-1-2-2017-01");
                }}));
            }}
        ));
        */		
    }
}
