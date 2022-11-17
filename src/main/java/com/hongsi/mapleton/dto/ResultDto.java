package com.hongsi.mapleton.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDto<T> {
    private String resultCode;
    private String resultMessage;
    private T data;
}
