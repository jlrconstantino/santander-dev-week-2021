package com.example.SantanderDevWeekBackend.exceptions;

import com.example.SantanderDevWeekBackend.util.MessageUtils;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super(MessageUtils.NO_RECORDS_FOUND);
    }
}
