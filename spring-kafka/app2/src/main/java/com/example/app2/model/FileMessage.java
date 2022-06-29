package com.example.app2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spectra.attic.coreasset.share.util.JsonSerializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FileMessage extends Message {

    private String text;
}
