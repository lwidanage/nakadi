package org.zalando.nakadi.service;

import org.springframework.web.util.UriComponentsBuilder;
import org.zalando.nakadi.domain.PaginationLinks;
import org.zalando.nakadi.repository.db.SubscriptionTokenLister;

import java.util.Optional;
import java.util.Set;

public class SubscriptionsUriHelper {

    public static PaginationLinks.Link createSubscriptionListLink(
            final Optional<String> owningApplication, final Set<String> eventTypes, final int offset,
            final Optional<SubscriptionTokenLister.Token> token, final int limit, final boolean showStatus) {

        final UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromPath("/subscriptions");
        if (!eventTypes.isEmpty()) {
            urlBuilder.queryParam("event_type", eventTypes.toArray());
        }
        owningApplication.ifPresent(owningApp -> urlBuilder.queryParam("owning_application", owningApp));
        if (showStatus) {
            urlBuilder.queryParam("show_status", "true");
        }
        if (token.isPresent()) {
            urlBuilder.queryParam("token", token.get());
        } else {
            urlBuilder.queryParam("offset", offset);
        }
        return new PaginationLinks.Link(urlBuilder
                .queryParam("limit", limit)
                .build()
                .toString());
    }
}