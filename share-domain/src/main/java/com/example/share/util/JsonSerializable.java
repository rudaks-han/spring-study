package com.example.share.util;

public interface JsonSerializable {
    default String toJson() {
        return JsonUtil.toJson(this);
    }
}
