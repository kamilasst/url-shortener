package com.challenge.urlshortener.service;

import com.challenge.urlshortener.dto.UrlRequest;
import org.springframework.stereotype.Service;

@Service
public class UrlShortGenerateService {

    public String generate(final UrlRequest urlRequest) {
        int hash = urlRequest.hashCode();

        // TODO Ver como esquipar / no find
        //return "www.shortenerurl.com/" + encodedHash;
        return Integer.toHexString(hash);
    }
}
