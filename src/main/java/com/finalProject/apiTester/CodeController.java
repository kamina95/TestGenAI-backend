package com.finalProject.apiTester;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CodeController {
    @PostMapping("/submit")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> submitCode(@RequestBody CodeSubmission submission) throws IOException {
        // Placeholder: process the code, run JaCoCo, call OpenAI API
        System.out.println(submission.toString());
        String response = CompileCode.startCompile(submission);
        return ResponseEntity.ok(response);
    }
}
