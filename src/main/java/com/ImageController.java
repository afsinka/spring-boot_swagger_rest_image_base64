package com;

import io.swagger.api.ImagesApi;
import io.swagger.model.GetImagesResponse;
import io.swagger.model.Image;
import java.io.InputStream;
import java.util.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController implements ImagesApi {

  @Override
  public ResponseEntity<GetImagesResponse> getImages() {
    try {
      final GetImagesResponse response = new GetImagesResponse();

      final InputStream inputStream = new ClassPathResource("images/bojack.jpg").getInputStream();
      final byte[] bytes = IOUtils.toByteArray(inputStream);
      final String encodedImage = Base64.getEncoder().encodeToString(bytes);

      final Image image = new Image();
      image.setValue(encodedImage);

      response.add(image);

      return new ResponseEntity<GetImagesResponse>(response, HttpStatus.OK);

    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new RuntimeException("oops!");
  }
}
