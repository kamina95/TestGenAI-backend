package com.finalProject.apiTester;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.finalProject.apiTester.CompileCode.cleanAllFiles;

@RestController
@RequestMapping("/api")
public class CodeController {
    @PostMapping("/submit_gpt")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> submitCode(@RequestBody CodeSubmission submission) {
        // Placeholder: process the code, run JaCoCo, call OpenAI API
        System.out.println(submission.toString());
        String response = CompileCode.startCompile(submission);
        System.out.println(response);
        cleanAllFiles();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/submit_llama")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> submitCodeLlama(@RequestBody CodeSubmission submission) {
        // Placeholder: process the code, run JaCoCo, call OpenAI API
        System.out.println(submission.toString());
        String response = LlamaCompile.startCompile(submission);
        cleanAllFiles();
        return ResponseEntity.ok(response);
    }
}
