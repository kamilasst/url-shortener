package com.challenge.urlshortener.service;

import com.challenge.urlshortener.dto.UrlRequest;
import com.challenge.urlshortener.exception.ErrorMessageConstantsException;
import com.challenge.urlshortener.exception.EntityAlreadyExistsException;
import com.challenge.urlshortener.domain.model.Url;
import com.challenge.urlshortener.repository.IUrlRepository;
import com.challenge.urlshortener.domain.UrlShortConverter;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public String getOriginalUrl(final String shortUrl) {
        Optional<Url> optionalUrl = urlRepository.findByShortUrl(shortUrl);
        if (optionalUrl.isPresent()){
            return optionalUrl.get().getOriginalUrl();
        }
        throw new EntityNotFoundException(ErrorMessageConstantsException.MESSAGE_02_NOT_FOUND);
    }

    private void validateOriginalUrlExist(final UrlRequest urlRequest) {
        Optional<Url> optionalUrl = urlRepository.findByOriginalUrl(urlRequest.getOriginalUrl());
        if(optionalUrl.isPresent()){
            throw new EntityAlreadyExistsException(String.format(ErrorMessageConstantsException.MESSAGE_01_ORIGINAL_URL_ALREADY_EXIST, optionalUrl.get().getOriginalUrl(), optionalUrl.get().getShortUrl()));
        }
    }
}
