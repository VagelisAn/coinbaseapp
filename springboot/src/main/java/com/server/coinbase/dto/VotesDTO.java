package com.server.coinbase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotesDTO {
    private int negative;
    private int positive;
    private int important;
    private int liked;
    private int disliked;
    private int lol;
    private int toxic;
    private int saved;
    private int comments;
}
