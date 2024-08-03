package com.challenge.urlshortener.domain;

import com.challenge.urlshortener.domain.model.Url;
import com.challenge.urlshortener.dto.UrlRequest;

import java.time.LocalDateTime;

public class UrlShortConverter {

    public static Url convertToUrl(final UrlRequest urlRequest, final String shortenedUrl) {
        return Url.builder()
                .originalUrl(urlRequest.getOriginalUrl())
                .shortUrl(shortenedUrl)
                .dateCreated(LocalDateTime.now())
                .build();
    }
}
