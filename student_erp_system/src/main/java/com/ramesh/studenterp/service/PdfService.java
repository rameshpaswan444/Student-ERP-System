package com.ramesh.studenterp.service;

import java.io.ByteArrayInputStream;

public interface PdfService {

    ByteArrayInputStream generateTranscript(Long studentId);
}
