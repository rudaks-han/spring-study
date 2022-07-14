package com.example.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spectra.attic.coreasset.share.util.JsonSerializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TextMessage implements JsonSerializable {

    private String text;
}
