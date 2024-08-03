package com.challenge.urlshortener.service;

import com.challenge.urlshortener.dto.UrlRequest;
import com.challenge.urlshortener.exception.ErrorMessageConstants;
import com.challenge.urlshortener.exception.ObjectAlreadyExistsException;
import com.challenge.urlshortener.domain.model.Url;
import com.challenge.urlshortener.repository.IUrlRepository;
import com.challenge.urlshortener.domain.UrlShortConverter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements IUrlService {

    private IUrlRepository urlRepository;
    private UrlShortGenerateService urlShortGenerateService;

    public UrlServiceImpl(final IUrlRepository urlRepository, final UrlShortGenerateService urlShortGenerateService){
        this.urlRepository = urlRepository;
        this.urlShortGenerateService = urlShortGenerateService;
    }
    @Override
    public String createShortenedUrl(final UrlRequest urlRequest) {

        validateOriginalUrlExist(urlRequest);
        String shortenedUrl = urlShortGenerateService.generate(urlRequest);
        Url url = UrlShortConverter.convertToUrl(urlRequest, shortenedUrl);
        urlRepository.save(url);
        return shortenedUrl;
    }

    private void validateOriginalUrlExist(final UrlRequest urlRequest) {
        Optional<Url> optionalUrl = urlRepository.findByOriginalUrl(urlRequest.getOriginalUrl());
        if(optionalUrl.isPresent()){
            throw new ObjectAlreadyExistsException(String.format(ErrorMessageConstants.MESSAGE_01_ORIGINAL_URL_ALREADY_EXIST, optionalUrl.get().getOriginalUrl(), optionalUrl.get().getShortUrl()));
        }
    }
}
