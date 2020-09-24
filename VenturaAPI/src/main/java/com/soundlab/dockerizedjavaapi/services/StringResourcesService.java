package com.soundlab.dockerizedjavaapi.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StringResourcesService {
    private static final Logger LOG = LogManager.getLogger(StringResourcesService.class);
    private static final String DEFAULT_VALUE = "N.A.";
    private static final int DEFAULT_RENEW_TIME_IN_SECONDS = 30;
    private static final Map<String, String> RESOURCES = new ConcurrentHashMap<>();
    private static Thread WORKER;

    public StringResourcesService() {
        updateResources();
    }

    public String getCompanyLogoText() {
        return RESOURCES.getOrDefault("COMPANY_LOGO_TEXT", DEFAULT_VALUE);
    }

    public String getPageFooterText() {
        return RESOURCES.getOrDefault("PAGE_FOOTER_TEXT", DEFAULT_VALUE);
    }

    public String getMemberBenefits() {
        return RESOURCES.getOrDefault("MEMBER_BENEFITS", DEFAULT_VALUE);
    }

    private void updateResources() {
        if (WORKER == null) {
            WORKER = new Thread(() -> {
                while (true) {
                    fetchData();
                    try {
                        Thread.sleep(DEFAULT_RENEW_TIME_IN_SECONDS*1000);
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
        RESOURCES.put("COMPANY_LOGO_TEXT", "Ventura HR");
        RESOURCES.put("PAGE_FOOTER_TEXT", "@Copyright Ventura HR.");
        RESOURCES.put("MEMBER_BENEFITS", "Sed ut perspiciatis unde omnis iste natus " +
            "error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa " +
            "quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. " +
            "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia " +
            "consequuntur magni dolores eos velit esse quam nihil molestiae consequatur, " +
            "vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?");

        LOG.info("StringResources updated. Resources fetched: {}", RESOURCES.size());
    }
}
