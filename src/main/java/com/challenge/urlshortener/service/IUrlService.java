package com.challenge.urlshortener.service;

import com.challenge.urlshortener.dto.UrlRequest;

public interface IUrlService {

    String createShortenedUrl(final UrlRequest urlRequest);

    String getOriginalUrl(String shortUrl);
}

