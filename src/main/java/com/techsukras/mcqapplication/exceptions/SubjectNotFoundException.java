package com.techsukras.mcqapplication.exceptions;

import com.techsukras.mcqapplication.repositories.SubjectRepository;

public class SubjectNotFoundException extends RuntimeException{
    public SubjectNotFoundException(String message){
        super(message);
    }
}
