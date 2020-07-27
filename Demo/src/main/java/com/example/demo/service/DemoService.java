package com.example.demo.service;

import com.example.demo.dao.request.ArbitrationModel;
import com.example.demo.dao.request.DemoRequest;
import com.example.demo.dao.request.ImportExampleModel;
import com.example.demo.dao.request.IndictmentModel;

import java.io.File;

public interface DemoService {

    File generateWordFile(DemoRequest request, String path);

    void handleTtemplateFile(ImportExampleModel model, String fontPath);

    void handleSueFile(IndictmentModel model);

    void handleArbrationFile(ArbitrationModel model);
}
