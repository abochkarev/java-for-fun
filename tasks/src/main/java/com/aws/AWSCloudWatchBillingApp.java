package com.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.Datapoint;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsRequest;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsResult;

import java.util.Collections;
import java.util.Date;

public class AWSCloudWatchBillingApp {

    private static AmazonCloudWatch acw = AmazonCloudWatchClientBuilder
            .standard()
            .withRegion(Regions.US_WEST_2)
            .build();

    public static void main(String[] args) {
        long twoWeeks = 1000 * 60 * 60 * 24 * 15;
        int twelveHours = 60 * 60 * 12;
        GetMetricStatisticsRequest request = new GetMetricStatisticsRequest()
                .withStartTime(new Date(new Date().getTime() - twoWeeks))
                .withNamespace("AWS/Billing")
                .withPeriod(twelveHours)
                .withDimensions(new Dimension().withName("Currency").withValue("USD"))
                .withMetricName("EstimatedCharges")
                .withStatistics("Average", "Maximum")
                .withEndTime(new Date());
        GetMetricStatisticsResult result = acw.getMetricStatistics(request);
        Collections.sort(result.getDatapoints(), (Datapoint dp1, Datapoint dp2) -> dp1.getTimestamp().compareTo(dp2.getTimestamp()));
        System.out.println(result);
    }

}
