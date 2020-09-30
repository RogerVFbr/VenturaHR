package com.soundlab.dockerizedjavaapi.services.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ValueVaultService {
    protected static final Logger LOG = LogManager.getLogger(StringResourcesService.class);
    protected static final String DEFAULT_VALUE = "N.A.";
    protected static final int RENEW_TIME_IN_SECONDS = 30;
    protected static final Map<String, String> RESOURCES = new ConcurrentHashMap<>();
    protected static Thread WORKER;

    protected ValueVaultService() {
        updateResources();
    }

    protected String getResource(String key) {
        return RESOURCES.getOrDefault(key, DEFAULT_VALUE);
    }

    private void updateResources() {
        if (WORKER == null) {
            WORKER = new Thread(() -> {
                while (true) {
                    fetchData();
                    try {
                        Thread.sleep(RENEW_TIME_IN_SECONDS *1000);
                    } catch (InterruptedException e) {
                        LOG.error(e);
                    }
                }
            });
            WORKER.setDaemon(true);
            WORKER.start();
        }

        while (RESOURCES.isEmpty()) {}
    }

    private void fetchData() {
        RESOURCES.put("MEMBER_BENEFITS", "Sed ut perspiciatis unde omnis iste natus " +
            "error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa " +
            "quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. " +
            "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia " +
            "consequuntur magni dolores eos velit esse quam nihil molestiae consequatur, " +
            "vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?");
        RESOURCES.put("SYSTEM_MESSAGES", "Lembre-se que é possível recomendar uma vaga aos seus " +
            "amigos, basta informar os seus e-mails.");

        LOG.info("StringResources updated. Resources fetched: {}", RESOURCES.size());
    }
}
