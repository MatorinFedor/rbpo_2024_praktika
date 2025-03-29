package ru.mtuci.rbpo_2024_praktika.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

@RestController
@RequestMapping("/test/binary")
public class BinaryDataController {

    @GetMapping(produces = MediaType.MULTIPART_MIXED_VALUE)
    public ResponseEntity<MultiValueMap<String, Object>> getMultiValue() {
        return buildMultipartResponse(new byte[1], new byte[1]);
    }

    private ResponseEntity<MultiValueMap<String, Object>> buildMultipartResponse(byte [] manifest, byte[] data) {
        ByteArrayResource manifestRes = new ByteArrayResource(manifest) {
            @Override
            public String getFilename() {
                return "manifest.bin";
            }
        };

        ByteArrayResource dataRes = new ByteArrayResource(manifest) {
            @Override
            public String getFilename() {
                return "data.bin";
            }
        };

        LinkedMultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("manifest", new HttpEntity<>(manifestRes, createHeaders("manifest.bin")));
        parts.add("data", new HttpEntity<>(dataRes, createHeaders("data.bin")));

        return ResponseEntity.ok().contentType(MediaType.parseMediaType("multipart/mixed")).body(parts);
    }

    private HttpHeaders createHeaders(String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().filename(filename).build());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return headers;
    }

    @GetMapping("/byte_stream")
    public ResponseEntity<byte[]> getSmth() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        writeLongLE(baos, 1234667L);
        writeUuidLE(baos, UUID.randomUUID());
        writeStringLE(baos, "HELLO");
        baos.toByteArray();
        return ResponseEntity.ok(baos.toByteArray());
    }

    private void writeUuidLE(ByteArrayOutputStream baos, UUID uuid) {
        writeLongLE(baos, uuid.getMostSignificantBits());
        writeLongLE(baos, uuid.getLeastSignificantBits());
    }

    private void writeLongLE(ByteArrayOutputStream baos, long value) {
        baos.write((byte) (value & 0xFF));
        baos.write((byte) (value >> 8 & 0xFF));
        baos.write((byte) (value >> 16 & 0xFF));
        baos.write((byte) (value >> 24 & 0xFF));
        baos.write((byte) (value >> 32 & 0xFF));
        baos.write((byte) (value >> 40 & 0xFF));
        baos.write((byte) (value >> 48 & 0xFF));
        baos.write((byte) (value >> 56 & 0xFF));
    }

    private void writeIntLE(ByteArrayOutputStream baos, int value) {
        baos.write((byte) (value & 0xFF));
        baos.write((byte) (value >> 8 & 0xFF));
        baos.write((byte) (value >> 16 & 0xFF));
        baos.write((byte) (value >> 24 & 0xFF));
    }

    private void writeStringLE(ByteArrayOutputStream baos, String value) {
        byte[] bytes = value.getBytes();
        writeIntLE(baos, bytes.length);
        baos.writeBytes(bytes);
    }
}
