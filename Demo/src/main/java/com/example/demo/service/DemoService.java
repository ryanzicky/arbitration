package com.example.demo.service;

import com.example.demo.dao.request.*;

import java.io.File;

public interface DemoService {

    File generateWordFile(DemoRequest request, String path);

    void handleTtemplateFile(ImportExampleModel model, String fontPath);

    void handleSueFile(IndictmentModel model);

    void handleArbrationFile(ArbitrationModel model);

    void handleContractDisputeFile(ContractDisputeModel model);
}
