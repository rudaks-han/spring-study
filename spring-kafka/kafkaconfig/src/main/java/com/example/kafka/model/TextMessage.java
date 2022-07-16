package com.example.kafka.model;

import com.example.share.util.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TextMessage implements JsonSerializable {

    private String text;
}
