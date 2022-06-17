package com.example.app1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spectra.attic.coreasset.share.util.JsonSerializable;

@AllArgsConstructor
@Getter
public class FileMessage implements JsonSerializable {

    private String text;
}
