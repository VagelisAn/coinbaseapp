package com.server.coinbase.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoNewsDTO {
//    private String kind;
//    private String domain;
//    private SourceDTO source;
    private String title;
    private String published_at;
//    private String slug;
    private String code;
    private List<CurrencyDTO> currencies;
    private int id;
    private String url;
    private String created_at;
    private VotesDTO votes;
}