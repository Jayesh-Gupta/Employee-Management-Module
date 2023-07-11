package com.example.service;

import java.io.IOException;
import java.io.InputStream;

public interface SaveEmployeesService {
    void saveEmployees(InputStream inputStream) throws IOException;
}
