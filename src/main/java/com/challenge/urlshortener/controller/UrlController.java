package com.challenge.urlshortener.controller;

import com.challenge.urlshortener.dto.UrlRequest;
import com.challenge.urlshortener.service.IUrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlController {

    private final IUrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> createShortenedUrl(@Valid @RequestBody UrlRequest urlrequest) {
        return ResponseEntity.ok(urlService.createShortenedUrl(urlrequest));
        // TODO Ver status created
      //  return ResponseEntity.created(new URI(urlService.createShortenedUrl(urlrequest))).body("URL curta criada com sucesso!");
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl){
        return ResponseEntity.ok(urlService.getOriginalUrl(shortUrl));
    }
}
