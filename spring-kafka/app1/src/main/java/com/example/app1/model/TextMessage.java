package com.example.app1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TextMessage extends Message {

    private String text;
}
