package com.server.coinbase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SourceDTO {
    private String title;
    private String region;
    private String domain;
    private String path;
    private String type;
}
