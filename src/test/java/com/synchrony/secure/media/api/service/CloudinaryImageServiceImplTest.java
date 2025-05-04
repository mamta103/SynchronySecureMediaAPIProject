package com.synchrony.secure.media.api.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CloudinaryImageServiceImplTest {

    @Mock
    private Cloudinary cloudinary;

    @Mock
    private MultipartFile file;

    @InjectMocks
    private CloudinaryImageServiceImpl service;

   @BeforeEach
   void setUp() {
       MockitoAnnotations.openMocks(this);
       Uploader uploader = mock(Uploader.class);
       when(cloudinary.uploader()).thenReturn(uploader);
   }

    @Nested
    @DisplayName("upload method")
    class Upload {

        @Test
        @DisplayName("Returns secure URL when upload is successful")
        void returnsSecureUrlWhenUploadIsSuccessful() throws IOException {
            Map<String, String> mockResult = new HashMap<>();
            mockResult.put("secure_url", "https://example.com/image.jpg");
            when(cloudinary.uploader().upload(any(byte[].class), eq(ObjectUtils.emptyMap()))).thenReturn(mockResult);
            when(file.getBytes()).thenReturn(new byte[]{});

            Map<String, String> result = service.upload(file);

            assertEquals("https://example.com/image.jpg", result.get("secure_url"));
            verify(cloudinary.uploader(), times(1)).upload(any(byte[].class), eq(ObjectUtils.emptyMap()));
        }

        @Test
        @DisplayName("Throws IOException when upload fails")
        void throwsIOExceptionWhenUploadFails() throws IOException {
            when(cloudinary.uploader().upload(any(byte[].class), eq(ObjectUtils.emptyMap()))).thenThrow(new IOException());
            when(file.getBytes()).thenReturn(new byte[]{});

            assertThrows(IOException.class, () -> service.upload(file));
        }
    }

    @Nested
    @DisplayName("delete method")
    class Delete {

        @Test
        @DisplayName("Returns result when deletion is successful")
        void returnsResultWhenDeletionIsSuccessful() throws IOException {
            Map<String, String> mockResult = new HashMap<>();
            mockResult.put("result", "ok");
            when(cloudinary.uploader().destroy(eq("publicId"), eq(ObjectUtils.emptyMap()))).thenReturn(mockResult);

            String result = service.delete("publicId");

            assertEquals("ok", result);
            verify(cloudinary.uploader(), times(1)).destroy(eq("publicId"), eq(ObjectUtils.emptyMap()));
        }

        @Test
        @DisplayName("Throws IOException when deletion fails")
        void throwsIOExceptionWhenDeletionFails() throws IOException {
            when(cloudinary.uploader().destroy(eq("publicId"), eq(ObjectUtils.emptyMap()))).thenThrow(new IOException());

            assertThrows(IOException.class, () -> service.delete("publicId"));
        }
    }
}