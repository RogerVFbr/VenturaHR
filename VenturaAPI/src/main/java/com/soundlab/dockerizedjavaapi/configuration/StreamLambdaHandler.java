package com.soundlab.dockerizedjavaapi.configuration;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.soundlab.dockerizedjavaapi.DockerizedJavaApiApplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamLambdaHandler implements RequestStreamHandler {
    private static final Logger LOG = LogManager.getLogger(StreamLambdaHandler.class);

    private static SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static {
        try {
            handler = SpringLambdaContainerHandler.getAwsProxyHandler(DockerizedJavaApiApplication.class);
            // If you are using HTTP APIs with the version 2.0 of the proxy model, use the getHttpApiV2ProxyHandler
            // method: handler = SpringLambdaContainerHandler.getHttpApiV2ProxyHandler(PetStoreSpringAppConfig.class);
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring framework", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
        throws IOException {
        if (isLambdaWarmupPluginCall(context)) {
            LOG.warn("Lambda warming mechanism triggered.");
            return;
        }
        handler.proxyStream(inputStream, outputStream, context);
    }

    private boolean isLambdaWarmupPluginCall(Context context) {
        if (context.getClientContext() != null &&
            context.getClientContext().getCustom() != null &&
            context.getClientContext().getCustom().getOrDefault("source", "")
                .equals("serverless-plugin-warmup")) {
            return true;
        }
        return false;
    }
}